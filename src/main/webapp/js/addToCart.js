
// Function to hide message after 3 seconds
function hideMessage(messageId) {
    setTimeout(function() {
        document.getElementById(messageId).style.display = 'none';
    }, 3000); // 3 seconds delay
}

// Check if error message is initially displayed
if (document.getElementById('addToCartError').style.display === 'block') {
    // Call the function to hide error message after 3 seconds
    hideMessage('addToCartError');
}

// Check if success message is initially displayed
if (document.getElementById('addToCartSuccess').style.display === 'block') {
    // Call the function to hide success message after 3 seconds
    hideMessage('addToCartSuccess');
}



var req = null;

function addToCart(productName) {
    var productQuantity = document.getElementById("quantityNum").value;
    req = new XMLHttpRequest();
    req.open("post", "/addToCart", true);
    req.onreadystatechange = handleResponse;
    req.setRequestHeader("content-type", "application/x-www-form-urlencoded");

    req.send("productName=" + productName + "&quantity=" + productQuantity);
}

function handleResponse() {
    if (req.readyState === 4 && req.status === 200) {
        var jsonResponse = JSON.parse(req.responseText);
        if (jsonResponse.message === "success") {
            var successMessage = document.getElementById('addToCartSuccess');
            successMessage.style.display = 'block';
            hideMessage('addToCartSuccess');

            // Scroll to the success message
            successMessage.scrollIntoView({ behavior: 'smooth' });

            updateCartQuantity(jsonResponse.cartItemsCount);
            /*var cartQtyElement = document.getElementById("cartQty");
            cartQtyElement.innerText = jsonResponse.cartItemsCount;
*/

        } else if (jsonResponse.message === "error") {
            var errorMessage = document.getElementById('addToCartError');
            errorMessage.style.display = 'block';
            hideMessage('addToCartError');

            // Scroll to the error message
            errorMessage.scrollIntoView({ behavior: 'smooth' });
        }
    }
}
