const productContainer = document.getElementById('product-container');
const products = Array.from(document.querySelectorAll('.product'));
let filteredProducts = [];

const productsPerPage = 6;
const pageNumber = document.getElementById('pageNumber');

const previous = document.getElementById("previous-btn");
const next = document.getElementById("next-btn");


function filterProducts() {
    const selectedCategories = Array.from(document.querySelectorAll('.input-checkbox input[type=checkbox]:checked')).map(checkbox => checkbox.id);

    const minPrice = parseInt(document.getElementById('price-min').value) || 0;
    const maxPrice = parseInt(document.getElementById('price-max').value) || Number.MAX_VALUE;

    filteredProducts = [];

    products.forEach(product => {
        const category = product.querySelector('.product-category').innerText.trim().toLowerCase();
        const price = parseFloat(product.querySelector('.product-price').innerText.replace('$', ''));

        const categoryFilterPassed = selectedCategories.length === 0 || selectedCategories.includes(category);
        const priceFilterPassed = price >= minPrice && price <= maxPrice;


        if (categoryFilterPassed && priceFilterPassed) {
            filteredProducts.push(product);
        }
    });

    loadProductsPage(1);


}

function loadProductsPage(page) {
    productContainer.innerHTML = '';


    let start = (page - 1) * productsPerPage;
    let end = Math.min(start + productsPerPage, filteredProducts.length);
    for(let i = start; i < end; i++) {
        productContainer.appendChild(filteredProducts[i]);
    }

    pageNumber.innerHTML = page;

    if (page === 1) {
        previous.style.display = 'none';
    }
    else {
        previous.style.display = 'block';
    }

    const totalPages= Math.ceil(filteredProducts.length / productsPerPage);

    if (page === totalPages) {
        next.style.display = 'none';
    }
    else {
        next.style.display = 'block';
    }
}

function scrollToTop(pos) {
    window.scrollTo({
        top: pos,
        behavior: 'smooth'
    });
}

document.addEventListener('DOMContentLoaded', function() {
    hideMessage("message");

    filterProducts();

    document.querySelectorAll('input[type=checkbox], input[type=number]').forEach(input => {
        input.addEventListener('change', filterProducts);
    });

    next.addEventListener('click', function() {
        let currentPage = parseInt(pageNumber.innerHTML);
        loadProductsPage(currentPage + 1);
        scrollToTop(450);
    });

    previous.addEventListener('click', function() {
        let currentPage = parseInt(pageNumber.innerHTML);
        loadProductsPage(currentPage - 1);
        scrollToTop(450);
    });
});

document.addEventListener('scroll', function () {
    let scrollPosition = window.scrollY;
    let backToTopButton = document.querySelector('.back-to-top');
    if (scrollPosition > 400) {
        backToTopButton.style.display = 'block';
    } else {
        backToTopButton.style.display = 'none';
    }
});

document.querySelector('.back-to-top').addEventListener('click', function () {
    scrollToTop(50);
});

function hideMessage(message) {
    setTimeout(function () {
        document.getElementById(message).style.display = 'none';
    }, 3000);
}