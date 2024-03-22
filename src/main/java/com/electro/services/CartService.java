package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.CartItemRepository;
import com.electro.persistence.repositries.CustomerRepository;
import com.electro.persistence.repositries.ProductRepository;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.dto.CartItemProductDTO;
import com.electro.presentation.enums.SessionAttribute;
import com.electro.services.util.ImagesPathUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class CartService {
    public static Boolean addToCart(HttpSession session, String productName, int quantity) throws IllegalArgumentException {
//        System.out.println("first :"+quantity);//TODO
        try {
            Customer customer = (Customer) session.getAttribute("LOGGED_IN_CUSTOMER");
            List<CartItemDTO>  cartItems = (List<CartItemDTO>) session.getAttribute(SessionAttribute.CART_ITEMS.name());

            if (cartItems == null) {
                cartItems = new ArrayList<>();
                session.setAttribute(SessionAttribute.CART_ITEMS.name(), cartItems);
            }

            List<CartItemDTO> finalCartItems = cartItems;
            return Database.doInTransaction(em -> {
                CartItemRepository cartItemRepository = new CartItemRepository(em);
                ProductRepository productRepository = new ProductRepository(em);

                Optional<Product> product= productRepository.getByName(productName);

                if (product.isPresent()) {
                    Long totalQuantityOfProductInCartItems=cartItemRepository.getTotalQuantityOfProduct(product.get());

                    if(product.get().getStockQuantity()-totalQuantityOfProductInCartItems < quantity) {
//                        System.out.println("minus: "+(product.get().getStockQuantity()-totalQuantityOfProductInCartItems));
                        return false;
                    }

                    if(customer != null) {
                        addCartItemToDatabase(quantity, cartItemRepository, customer, product);
                    }

                    try {
                        addCartItemToSession(quantity, product, finalCartItems);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return true;
                }
                return false;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static void mergeCart(HttpSession session, Customer customer){
        try {
            List<CartItemDTO> cartItemsDTO = (List<CartItemDTO>) session.getAttribute(SessionAttribute.CART_ITEMS.name());
            if (cartItemsDTO != null && !cartItemsDTO.isEmpty()) {

                Database.doInTransactionWithoutResult(em->{
                    CartItemRepository cartItemRepository = new CartItemRepository(em);
                    CustomerRepository customerRepository = new CustomerRepository(em);
                    Set<CartItem> customerCartItems = customer.getCart().getCartItems();

                    for (CartItemDTO cartItemDTO : cartItemsDTO) {
                        boolean existsInCustomerCart = false;
                        for (CartItem cartItemEntity : customerCartItems) {
                            if (cartItemDTO.getItemProductDTO().getId().equals(cartItemEntity.getProduct().getId())) {
                                cartItemEntity.setQuantity(cartItemEntity.getQuantity()+cartItemDTO.getQuantity());
                                cartItemRepository.update(cartItemEntity);
                                existsInCustomerCart = true;
                                break;
                            }
                        }
                        if (!existsInCustomerCart) {
                            Product product =mapCartItemProductDTOToProduct(cartItemDTO.getItemProductDTO());

                            CartItem cartItemEntity = new CartItem();
                            cartItemEntity.setCart(customer.getCart());
                            cartItemEntity.setProduct(product);
                            cartItemEntity.setQuantity(cartItemDTO.getQuantity());
                            cartItemEntity.setAmount(BigDecimal.valueOf(20.0));
                            cartItemRepository.create(cartItemEntity);

                            customer.getCart().getCartItems().add(cartItemEntity);
                        }
                    }
                    customerRepository.update(customer);
                    cartItemsDTO.clear();
                    for (CartItem cartItemEntity: customer.getCart().getCartItems()){
                        CartItemProductDTO itemProductDTO = null;
                        try {
                            itemProductDTO = mapProductToCartItemProductDTO(cartItemEntity.getProduct());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        CartItemDTO cartItem = CartItemDTO.builder()
                                .CartItemId(cartItemEntity.getId())
                                .itemProductDTO(itemProductDTO)
                                .quantity(cartItemEntity.getQuantity())
                                .build();
                        cartItemsDTO.add(cartItem);
                    }
                    session.setAttribute(SessionAttribute.CART_ITEMS.name(), cartItemsDTO);
                });

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    private static void addCartItemToDatabase(int quantity, CartItemRepository cartItemRepository, Customer customer, Optional<Product> product) {

        Optional<CartItem> existingCartItem = cartItemRepository.getExistingCartItemForCustomer(product.get(),customer);

        if(existingCartItem.isPresent()){
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity()+quantity);
            cartItemRepository.update(existingCartItem.get());
        }
        else {
            CartItem cartItemEntity = new CartItem();
            cartItemEntity.setCart(customer.getCart());
            cartItemEntity.setProduct(product.get());
            cartItemEntity.setQuantity(quantity);
            cartItemEntity.setAmount(BigDecimal.valueOf(20.0));
            cartItemRepository.create(cartItemEntity);

            customer.getCart().getCartItems().add(cartItemEntity);
        }
    }

    private static void addCartItemToSession(int quantity, Optional<Product> product, List<CartItemDTO> cartItems) throws IOException {
//        System.out.println(quantity+" :in session function");//TODO
        for(CartItemDTO cI :cartItems){
            if(cI.getItemProductDTO().getProductName().equals(product.get().getProductName())){
                cI.setQuantity(cI.getQuantity()+quantity);
//                System.out.println(cI.getQuantity()+"the quatitiy finial"); //TODO
                return;
            }
        }
        CartItemProductDTO itemProductDTO = mapProductToCartItemProductDTO(product.get());
        CartItemDTO cartItem = CartItemDTO.builder()

                .itemProductDTO(itemProductDTO)
                .quantity(quantity)
                .build();
        cartItems.add(cartItem);

    }


    private static CartItemProductDTO mapProductToCartItemProductDTO(Product product) throws IOException {

        return CartItemProductDTO.builder()
                .id(product.getId())
                .productDescription(product.getProductDescription())
                .productPic(product.getProductPic())
                .Base64Image(ImagesPathUtil.encodeFileToBase64(product.getProductPic()))
                .mimeType(ImagesPathUtil.getMimeType(product.getProductPic()))
                .productPrice(product.getProductPrice())
                .stockQuantity(product.getStockQuantity())
                .productName(product.getProductName())
                .category(product.getCategory())
                .build();
    }

    private static Product mapCartItemProductDTOToProduct(CartItemProductDTO itemProductDTO) {
        Product product =new Product();
        product.setId(itemProductDTO.getId());
        product.setProductName(itemProductDTO.getProductName());
        product.setStockQuantity(itemProductDTO.getStockQuantity());
        product.setProductDescription(itemProductDTO.getProductDescription());
        product.setProductPic(itemProductDTO.getProductPic());
        product.setProductPrice(itemProductDTO.getProductPrice());
        product.setCategory(itemProductDTO.getCategory());

        return  product;
    }
}
