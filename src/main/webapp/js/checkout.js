function checkout(event){
    event.preventDefault();
    let errorMessage = document.querySelector("#errorMessage");
    let successMessage = document.querySelector("#successMessage");
    let errorMessageTxt = document.querySelector("#errorMessageTxt");
    let successMessageTxt = document.querySelector("#successMessageTxt");
    let submitButton = document.querySelector("#submit-button");
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/checkout", true);
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            console.log(xhr.responseText);
            console.log(xhr.status);
            if(xhr.status === 200){
                successMessage.style.display = "block";
                errorMessage.style.display = "none";
                successMessageTxt.textContent = xhr.responseText;
                submitButton.disabled = true;
                loadCartData();
                setTimeout(() => {
                    window.location.href = "/home";
                }, 3000);
            }
            else {
                errorMessage.style.display = "block";
                successMessage.style.display = "none";
                errorMessageTxt.textContent = xhr.responseText;
                setTimeout(() => {
                    errorMessage.style.display = "none";
                }, 10000);
            }
        }
    };
    xhr.onerror = function(){
        errorMessage.textContent = "An error occurred while processing your order";
        errorMessage.style.display = "block";
    };
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send();
}

