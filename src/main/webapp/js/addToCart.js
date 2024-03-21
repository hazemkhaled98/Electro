
var req = null;

function addToCart(productName) {
    req = new XMLHttpRequest();
    req.open("post", "/addToCart", true);
    req.onreadystatechange = handleResponse;
    req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    req.send("productName=" + productName +"&quantity=1");
}

function handleResponse() {
    if (req.readyState === 4 && req.status === 200) {
        var message = req.responseText;
        if (message === "success") {
            var cartQtyElement = document.getElementById("cartQty");
            cartQtyElement.innerText = parseInt(cartQtyElement.innerText) + 1;
        }
    }
}