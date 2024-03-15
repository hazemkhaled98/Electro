let req = null;
let url = null;
let pagesCount = document.getElementById("pagesCount");
let currentPageNumber = document.getElementById("currentPageNumber");
let nextButton = document.getElementById("nextButton");
let previousButton = document.getElementById("previousButton");
let productTable = document.getElementById("productTable");
let msgDiv = "";
let table = document.getElementById("tbody");
let row = "";

document.onload = loadPageOfProducts(1);

previousButton.addEventListener("click", (e) => {
    currentPageNumber.value = parseInt(currentPageNumber.value) - 1;
    loadPageOfProducts(currentPageNumber.value);
})

nextButton.addEventListener("click", (e) => {
    currentPageNumber.value = parseInt(currentPageNumber.value) + 1
    loadPageOfProducts(currentPageNumber.value);
})

function handleButtons(num) {
    if (num === "1") {
        previousButton.classList.add("disabled")
    } else {
        previousButton.classList.remove("disabled")
    }
    if (num === pagesCount.value || pagesCount.value === "0") {
        nextButton.classList.add("disabled")
    } else {
        nextButton.classList.remove("disabled")
    }

}

function loadPageOfProducts(pageNumber) {
    createXMLHttpRequest();
    url = "/admin/products-page?pg=" + pageNumber + "&timeStamp=" + new Date().getTime();
    req.open("GET", url, true);
    req.onreadystatechange = handleStateChange;
    req.send();
}

function handleStateChange() {
    if (req.readyState === 4 && req.status === 200) {
        let list = JSON.parse(req.responseText);
        list.forEach(product => {
            row += ` <tr>
    <td class="py-2 pl-4 pr-3 text-sm text-gray-500 whitespace-nowrap sm:pl-6">${product.id}</td>
    <td class="px-2 py-2 text-sm font-medium text-gray-900">${product.name}</td>
    <td class="px-2 py-2 text-sm text-gray-900 ">${product.description}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${product.quantity}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">$${product.price}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${product.category}</td>
    <td class="relative py-2 pl-3 pr-4 text-sm cursor-pointer font-medium text-right whitespace-nowrap sm:pr-6">
        <a href="/admin/products/edit?productId=${product.id}" class="btn btn-primary">Edit</a>
    </td>
    <td class="relative py-2 pl-3 pr-4 text-sm cursor-pointer font-medium text-right whitespace-nowrap sm:pr-6">
        <a href="/admin/products/delete?productId=${product.id}" class="btn btn-danger">Delete</a>
    </td>

</tr>`
        });
        if (list.length === 0) {
            console.log("No products")
            msgDiv = `<h4 class="text-center">No Products</h4>`
            productTable.innerHTML = msgDiv;
            msgDiv = "";
        }

        table.innerHTML = row;
        handleButtons(currentPageNumber.value)
        row = "";
    }
}


function createXMLHttpRequest() {
    if (window.ActiveXObject)
        req = new ActiveXObject("Microsoft.XMLHTTP");
    else if (window.XMLHttpRequest)
        req = new XMLHttpRequest();
}