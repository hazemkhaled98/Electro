// JavaScript code to send a request to /newsletter
document.addEventListener("DOMContentLoaded", function() {
    var newsletterForm = document.querySelector(".newsletter form");
    newsletterForm.addEventListener("submit", function(event) {
        event.preventDefault();
        var emailInput = document.querySelector(".newsletter input[type='email']");
        var email = emailInput.value;
        if (email === "") {
            return;
        }
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/newsletter");
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        console.log(email);
        xhr.send("email=" + encodeURIComponent(email));
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                console.log(xhr.responseText);
                console.log(xhr.status);
                if (xhr.status === 200) {
                    console.log("Email sent successfully");
                    var message = document.querySelector("#success");
                    var messageText = document.querySelector("#success p");
                    messageText.textContent = xhr.responseText;
                    message.style.display = "block";
                    emailInput.value = "";

                    setTimeout(() => {
                        message.style.display = "none";
                    },3000)
                } else {
                    console.log("Error sending email");
                    var message = document.querySelector("#error");
                    var messageText = document.querySelector("#error p");
                    messageText.textContent = xhr.responseText;
                    message.style.display = "block";
                    emailInput.value = "";

                    setTimeout(() => {
                        message.style.display = "none";
                    },3000)
                }
            }
        }
    });
});

