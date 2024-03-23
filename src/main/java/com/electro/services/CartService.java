package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.CartItemRepository;
import com.electro.persistence.repositries.CartRepository;
import com.electro.persistence.repositries.CustomerRepository;
import com.electro.persistence.repositries.ProductRepository;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.dto.CartItemProductDTO;
import com.electro.presentation.dto.OrderCartItemDto;
import com.electro.presentation.enums.SessionAttribute;
import com.electro.services.util.ImagesPathUtil;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class CartService {
    public static Boolean addToCart(HttpSession session, String productName, int quantity) throws IllegalArgumentException {

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
                int id = 0;
                Optional<Product> product= productRepository.getByName(productName);

                if (product.isPresent()) {
                    int remainingQuantityOfCartItem = getRemainingQuantityOfCartItem(productName,
                                                        cartItemRepository, product, customer, finalCartItems);

                    if(remainingQuantityOfCartItem < quantity) {
                        return false;
                    }

                    if(customer != null) {
                        id = addCartItemToDatabase(quantity, cartItemRepository, customer, product);
                    }

                    try {
                        addCartItemToSession(quantity, product, finalCartItems , id);
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

    private static int getRemainingQuantityOfCartItem(String productName, CartItemRepository cartItemRepository, Optional<Product> product, Customer customer, List<CartItemDTO> finalCartItems) {
        Optional<CartItem> existingCartItemEntity = cartItemRepository.getExistingCartItemForCustomer(product.get(), customer);

        int quantityOfExistingCartItemEntity=0;
        if(existingCartItemEntity.isPresent()){
            quantityOfExistingCartItemEntity = existingCartItemEntity.get().getQuantity();
        }
        int quantityOfCartItemInSession=0;
        if(customer == null) {
            for (CartItemDTO cartItemDTO : finalCartItems) {
                if (cartItemDTO.getItemProductDTO().getProductName().equals(productName)) {
                    quantityOfCartItemInSession = cartItemDTO.getQuantity();
                    break;
                }
            }
        }

        return product.get().getStockQuantity()-(quantityOfExistingCartItemEntity+quantityOfCartItemInSession);
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
                            if (cartItemDTO.getItemProductDTO().getProductName().equals(cartItemEntity.getProduct().getProductName())) {

                                if((cartItemEntity.getQuantity() + cartItemDTO.getQuantity()) > cartItemEntity.getProduct().getStockQuantity()) {
                                    cartItemEntity.setQuantity(cartItemEntity.getProduct().getStockQuantity());
                                }
                                else {
                                    cartItemEntity.setQuantity(cartItemEntity.getQuantity() + cartItemDTO.getQuantity());
                                }
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




    private static int addCartItemToDatabase(int quantity, CartItemRepository cartItemRepository, Customer customer, Optional<Product> product) {
        int id = 0;
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
            id = cartItemEntity.getId();
            customer.getCart().getCartItems().add(cartItemEntity);
        }
        return id;
    }

    private static void addCartItemToSession(int quantity, Optional<Product> product, List<CartItemDTO> cartItems , int id) throws IOException {

        for(CartItemDTO cI :cartItems){
            if(cI.getItemProductDTO().getProductName().equals(product.get().getProductName())){
                cI.setQuantity(cI.getQuantity()+quantity);
                return;
            }
        }
        CartItemProductDTO itemProductDTO = mapProductToCartItemProductDTO(product.get());
        CartItemDTO cartItem = CartItemDTO.builder()
                .CartItemId(id)
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
    //------------------------------------------------------------------------

    public List<String> getCartItems(Customer customer) throws IOException {
        Cart cart = customer.getCart();
        Set<CartItem> cartItems = cart.getCartItems();
        List<String> cartItemsJson = new ArrayList<>();
        int i = 0;
        for (CartItem cartItem : cartItems) {
            cartItemsJson.add(productToJson(cartItem.getProduct(), cartItem.getQuantity(),i, cartItem.getId()));
            i++;
        }
        return cartItemsJson;
    }
    //new code after moataz
    public List<CartItemDTO> newgetCartItems(Customer customer) {
        Cart cart = customer.getCart();
        Set<CartItem> cartItems = cart.getCartItems();
        List<CartItemDTO> cartItemsDTO = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemDTO cartItemDTO = CartItemDTO.builder().build();
            cartItemDTO.setCartItemId(cartItem.getId());
            cartItemDTO.setQuantity(cartItem.getQuantity());
            CartItemProductDTO itemProductDTO = newMapProductToCartItemProductDTO(cartItem.getProduct());
            cartItemDTO.setItemProductDTO(itemProductDTO);
            cartItemsDTO.add(cartItemDTO);
        }
        return cartItemsDTO;
    }

    private String productToJson(Product product , int quantity , int order,int id) throws IOException {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("name", product.getProductName())
                .add("id", id)
                .add("price", product.getProductPrice())
                .add("quantity", quantity)
                .add("mimeType" , ImagesPathUtil.getMimeType(product.getProductPic()))
                .add("image", ImagesPathUtil.encodeFileToBase64(product.getProductPic()))
                .add("order", order);
        return jsonObjectBuilder.build().toString();
    }
    public void removeItemFromCart(int id){
        Database.doInTransaction(em -> {
            CartItemRepository cartItemRepository = new CartItemRepository(em);
            cartItemRepository.deleteById(id);
            return null;
        });
    }
    // new  code after moataz
    public CartItemProductDTO newMapProductToCartItemProductDTO(Product product) {
        CartItemProductDTO itemProductDTO = CartItemProductDTO.builder().build();
        itemProductDTO.setId(product.getId());
        itemProductDTO.setProductName(product.getProductName());
        itemProductDTO.setStockQuantity(product.getStockQuantity());
        itemProductDTO.setProductDescription(product.getProductDescription());
        itemProductDTO.setProductPic(product.getProductPic());
        itemProductDTO.setProductPrice(product.getProductPrice());
        itemProductDTO.setCategory(product.getCategory());
        return itemProductDTO;
    }



    private CartService() {
    }

    public static List<OrderCartItemDto> getCartItemsForOrder(Customer customer){
        return Database.doInTransaction(em -> {
            CartRepository cartRepository = new CartRepository(em);
            Cart cart = cartRepository.update(customer.getCart());
            Set<CartItem> cartItems = cart.getCartItems();
            return OrderCartItemDto.of(cartItems);
        });
    }

    public static List<OrderCartItemDto> getCartItemsForOrder(Cart c){
        return Database.doInTransaction(em -> {
            CartRepository cartRepository = new CartRepository(em);
            Cart cart = cartRepository.update(c);
            Set<CartItem> cartItems = cart.getCartItems();
            return OrderCartItemDto.of(cartItems);
        });
    }

    public static double getTotalPrice(List<OrderCartItemDto> orderCartItemDto){
        return orderCartItemDto.stream().mapToDouble(OrderCartItemDto::getPrice).sum();
    }
}
