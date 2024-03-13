document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("sc-edprofile");
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");
    var passwordMismatchMessage = document.getElementById("passwordMatch");

    form.addEventListener("submit", function(event) {
        if (password.value !== confirmPassword.value) {
            // Prevent form submission
            event.preventDefault();

            // Show error message
            passwordMismatchMessage.style.display = "block";

            // Focus on the confirm password field
            confirmPassword.focus();
        }
    });

    // Add event listeners to the password and confirm password fields to hide the error message
    password.addEventListener("input", function() {
        passwordMismatchMessage.style.display = "none";
    });

    confirmPassword.addEventListener("input", function() {
        passwordMismatchMessage.style.display = "none";
    });
});
