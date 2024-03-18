function filterProducts() {
    const selectedCategories = Array.from(document.querySelectorAll('.input-checkbox input[type=checkbox]:checked')).map(checkbox => checkbox.id);

    const minPrice = parseInt(document.getElementById('price-min').value) || 0;
    const maxPrice = parseInt(document.getElementById('price-max').value) || Number.MAX_VALUE;


    const productContainer = document.getElementById('product-container');

    const products = Array.from(document.querySelectorAll('.product'));

    products.forEach(product => {
        const category = product.querySelector('.product-category').innerText.trim().toLowerCase();
        const price = parseFloat(product.querySelector('.product-price').innerText.replace('$', ''));

        const categoryFilterPassed = selectedCategories.length === 0 || selectedCategories.includes(category);
        const priceFilterPassed = price >= minPrice && price <= maxPrice;

        product.classList.add('hidden');

        if (categoryFilterPassed && priceFilterPassed) {
            productContainer.appendChild(product);
            product.classList.remove('hidden');
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {
    filterProducts();

    document.querySelectorAll('input[type=checkbox], input[type=number]').forEach(input => {
        input.addEventListener('change', filterProducts);
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
    window.scrollTo({
        top: 400,
        behavior: 'smooth'
    });
});