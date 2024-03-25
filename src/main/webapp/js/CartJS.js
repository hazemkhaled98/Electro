function loadCartData() {
    console.log("gowa hena ")
    createXMLHttpRequest();
    req.open("GET", "/cart", true);
    req.onreadystatechange = updatePage;
    req.send(null);
}

function updatePage() {
    if (req.readyState === 4 && req.status === 200) {
        let cartItems = JSON.parse(req.responseText);
        let cartListDiv = document.querySelector(".cart-list");
        cartListDiv.innerHTML = ""; // Clear the cart list
        let cartQty = document.querySelector(".qty");
        let totalQuantity = 0;
        let totalPrice = 0;
        let uniqueItems = 0;
        let cartSummaryDiv = document.querySelector(".cart-summary");

        // Add each item to the cart list
        if (cartItems.length > 0) {
            cartItems.forEach(item => {
                uniqueItems++;
                totalPrice += item.price * item.quantity;
                totalQuantity += item.quantity;
                let productWidget = document.createElement('div');
                productWidget.classList.add('product-widget');
                productWidget.innerHTML = `
                    <div class="product-img">
                        <img src="${item.image}" alt="image">
                    </div>
                    <div class="product-body">
                        <h3 class="product-name"><a>${item.name}</a></h3>
                        <h4 class="product-price"><span class="qty">${item.quantity}x</span>$${item.price}</h4>
                        <input type="hidden" value="${item.order}">
                    </div>
                    <button class="delete"><i class="fa fa-close"></i></button>
                `;
                /* cartListDiv.innerHTML += `
                <div class="product-widget">
                    <div class="product-img">
                    <img src="data:${item.mimeType};base64,${item.image}" alt="">
                    </div>
                    <div class="product-body">
                        <h3 class="product-name"><a>${item.name}</a></h3>
                        <h4 class="product-price"><span class="qty">${item.quantity}x</span>$${item.price}</h4>
                        <input type="hidden" value="${item.order}">
                    </div>
                    <button class="delete"><i class="fa fa-close"></i></button>
                </div>
            `;*/
                let deleteButton = productWidget.querySelector('.delete');
                deleteButton.addEventListener('click', function() {
                    removeItem(item.id);
                });
                cartListDiv.appendChild(productWidget);

            });
            console.log(uniqueItems)
            cartQty.innerHTML = uniqueItems;
            // Update the cart summary
            cartSummaryDiv.innerHTML = `
            <small><span>${totalQuantity}</span> Item(s) in the cart</small>
            <h5>Total: $${totalPrice.toFixed(2)}</h5>
        `;
        }
        else {
            cartQty.style.display = "none";
            cartSummaryDiv.innerHTML = "<h5>Cart is empty</h5>";
        }
    }
}
function createXMLHttpRequest() {
    if (window.ActiveXObject)
        req = new ActiveXObject("Microsoft.XMLHTTP");
    else if (window.XMLHttpRequest)
        req = new XMLHttpRequest();
}
function removeItem(id) {
    createXMLHttpRequest();
    req.open("POST", "/cart", true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.onreadystatechange = deleteItem;
    req.send("id=" + id);
}
function deleteItem() {
    if (req.readyState === 4 && req.status === 200) {
        loadCartData();
    }
}
function updateCartQuantity(quantity) {
    console.log("gowa el update cart quantity" + quantity)
    let cartQty = document.querySelector(".qty");
    cartQty.innerHTML = quantity;
    if (quantity > 0)
    cartQty.style.display = "block";
    else
    cartQty.style.display = "none";
}
// Call loadCartData when the page loads
window.onload = loadCartData;