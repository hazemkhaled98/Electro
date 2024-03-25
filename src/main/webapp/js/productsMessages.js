document.addEventListener("DOMContentLoaded", function() {
    // Function to hide message after 3 seconds
    function hideMessage(messageId) {
        setTimeout(function() {
            document.getElementById(messageId).style.display = 'none';
        }, 5000); // 3 seconds delay
    }

    // Check if error message is initially displayed
    if (document.getElementById('errorMessage').style.display === 'block') {
        // Call the function to hide error message after 3 seconds
        hideMessage('errorMessage');
    }

    // Check if success message is initially displayed
    if (document.getElementById('successMessage').style.display === 'block') {
        // Call the function to hide success message after 3 seconds
        hideMessage('successMessage');
    }

    // Add event listener to monitor changes in display property for error message
    document.getElementById('errorMessage').addEventListener('transitionend', function(event) {
        // Check if the display property changed to 'block'
        if (event.propertyName === 'display' && event.target.style.display === 'block') {
            // Call the function to hide error message after 3 seconds
            hideMessage('errorMessage');
        }
    });

    // Add event listener to monitor changes in display property for success message
    document.getElementById('successMessage').addEventListener('transitionend', function(event) {
        // Check if the display property changed to 'block'
        if (event.propertyName === 'display' && event.target.style.display === 'block') {
            // Call the function to hide success message after 3 seconds
            hideMessage('successMessage');
        }
    });
});