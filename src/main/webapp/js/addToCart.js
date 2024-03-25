// Function to hide message after 3 seconds
function hideMessage(messageId) {
    setTimeout(function() {
        var messageElement = document.getElementById(messageId);
        if (messageElement) {
            messageElement.style.display = 'none';
        }
    }, 3000); // 3 seconds delay
}

document.addEventListener('DOMContentLoaded', function() {
    // Check if error message is initially displayed
    var errorElement = document.getElementById('addToCartError');
    if (errorElement && errorElement.style.display === 'block') {
        // Call the function to hide error message after 3 seconds
        hideMessage('addToCartError');
    }

    // Check if success message is initially displayed
    var successElement = document.getElementById('addToCartSuccess');
    if (successElement && successElement.style.display === 'block') {
        // Call the function to hide success message after 3 seconds
        hideMessage('addToCartSuccess');
    }
});

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
            console.log("success");
            var successMessage = document.getElementById('addToCartSuccess');
            if (successMessage) {
                successMessage.style.display = 'block';
                hideMessage('addToCartSuccess');

                // Scroll to the success message
                successMessage.scrollIntoView({ behavior: 'smooth' });

                updateCartQuantity(jsonResponse.cartItemsCount);
            }
        } else if (jsonResponse.message === "error") {
            console.log("fail");
            var errorMessage = document.getElementById('addToCartError');
            if (errorMessage) {
                errorMessage.style.display = 'block';
                hideMessage('addToCartError');

                // Scroll to the error message
                errorMessage.scrollIntoView({ behavior: 'smooth' });
            }
        }
    }
}
