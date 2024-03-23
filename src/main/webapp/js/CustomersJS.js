let req = null;
let url = null;
let pagesCount = document.getElementById("pageNumber");
let currentPageNumber = document.getElementById("currentPageNumberCust");
let nextButton = document.getElementById("nextButtonCust");
let previousButton = document.getElementById("previousButtonCust");
let customerTable = document.getElementById("customerTable");
let msgDiv = "";
let table = document.getElementById("tbody");
let row = "";

document.onload = loadPageOfCustomers(1);

previousButton.addEventListener("click", (e) => {
    currentPageNumber.value = parseInt(currentPageNumber.value) - 1;
    loadPageOfCustomers(currentPageNumber.value);
})

nextButton.addEventListener("click", (e) => {
    currentPageNumber.value = parseInt(currentPageNumber.value) + 1
    loadPageOfCustomers(currentPageNumber.value);
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

function loadPageOfCustomers(pageNumber) {
    createXMLHttpRequest();
    url = "/admin/customers-page?pg=" + pageNumber + "&timeStamp=" + new Date().getTime();
    req.open("GET", url, true);
    req.onreadystatechange = handleStateChange;
    req.send();
}

function handleStateChange() {
    if (req.readyState === 4 && req.status === 200) {
        let list = JSON.parse(req.responseText);
        list.forEach(customer => {
            console.log("Customer: " + customer.customerName);
            row += ` <tr>
    <td class="py-2 pl-4 pr-3 text-sm text-gray-500 whitespace-nowrap sm:pl-6">${customer.id}</td>
    <td class="px-2 py-2 text-sm font-medium text-gray-900">${customer.customerName}</td>
    <td class="px-2 py-2 text-sm text-gray-900 ">${customer.birthday}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.job}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.email}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.creditLimit}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.city}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.country}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.streetNo}</td>
    <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.streetName}</td>
    <td  class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">
    <button class="view-customer" onclick="window.location.href='/customerDetails?customerID=${customer.id}'">
    View
    </button></td>
    </tr>

</tr>`
        });
        if (list.length === 0) {
            console.log("No Customers")
            msgDiv = `<h4 class="text-center">No Customers</h4>`
            customerTable.innerHTML = msgDiv;
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