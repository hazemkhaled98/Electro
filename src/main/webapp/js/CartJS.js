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
                cartListDiv.innerHTML += `
                <div class="product-widget">
                    <div class="product-img">
                    <img src="data:${item.mimeType};base64,${item.image}" alt="">
                    </div>
                    <div class="product-body">
                        <h3 class="product-name"><a href="#">${item.name}</a></h3>
                        <h4 class="product-price"><span class="qty">${item.quantity}x</span>$${item.price}</h4>
                    </div>
                    <button class="delete"><i class="fa fa-close"></i></button>
                </div>
            `;
            });
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

// Call loadCartData when the page loads
window.onload = loadCartData;