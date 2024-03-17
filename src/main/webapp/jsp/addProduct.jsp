<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <script src="<c:url value="/js/components/cookie.js"/>"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Electro | Add Product</title>
    <link rel="stylesheet" href="../css/NavBar.css">

</head>

<body class="d-flex flex-column min-vh-100">

<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav" class="">
            <!-- NAV -->
            <ul class="main-nav nav navbar-nav nav-row">
                <li><a href="${pageContext.request.contextPath}/admin/customers">Customers</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/products">Products</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/add-product">Add product</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>


<h2 class="text-center text-2xl font-weight-bold text-gray-900 mb-5">Add Product</h2>
<section id="product">

    <div class="container mt-3">

        <div id="errorMessage" class="alert alert-danger" role="alert" style="display: ${not empty requestScope.ERROR ? 'block' : 'none'}; font-size: 16px;">
            <p class="center">${requestScope.ERROR}</p>
        </div>


        <div id="successMessage" class="alert alert-success" style="display: ${not empty requestScope.SUCCESS ? 'block' : 'none'}; font-size: 16px;">
            <p class="center">${requestScope.SUCCESS}</p>
        </div>


        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Product Information</h3>
                <form id="productAddForm" action="${pageContext.request.contextPath}/admin/add-product" enctype="multipart/form-data" method="POST">
                    <div class="row g-3">

                        <div class="col-12">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" name="name" id="name" class="form-control" required>
                            <span class="form-text text-danger d-none" id="nameValidation">This field is invalid</span>
                        </div>

                        <div class="col-12" style="margin-top: 10px">
                            <label for="description" class="form-label">Description</label>
                            <textarea id="description" name="description" rows="3" class="form-control"></textarea>
                            <span class="form-text text-danger d-none" id="descriptionValidation">This field is invalid</span>
                        </div>

                        <div class="col-12" style="margin-top: 10px">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input value="1" type="number" min="1" name="quantity" id="quantity" class="form-control" required>
                        </div>

                        <div class="col-12" style="margin-top: 10px">
                            <label for="price" class="form-label">Price</label>
                            <input value="1" name="price" id="price" type="number" min="0.1" step="0.1" class="form-control" required>
                        </div>

                        <div class="col-12" style="margin-top: 10px">
                            <label for="productImg" class="form-label">Product Image</label>
                            <input class="form-control" name="productImg" type="file" id="productImg" required>
                        </div>


                        <div class="col-12" style="margin-top: 10px">
                            <label for="category" class="form-label">Category</label>
                            <select id="category" name="category" class="form-select">
                                <option value="laptop" selected>Laptop</option>
                                <option value="smartphone">Smartphone</option>
                                <option value="camera">Camera</option>
                            </select>
                        </div>

                    </div>
                    <div class="d-flex justify-content-end mt-3">
                        <button id="cancelButton" type="button" onclick="" class="btn btn-secondary me-2" style="margin-right: 5px">Cancel</button>
                        <button id="submitButton" type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</section>

<section class="mt-auto" id="footer">
    <footer class="bg-white">
        <div class="container py-4">
            <div class="text-center text-muted">&copy; Electro</div>
        </div>
    </footer>
</section>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/productsMessages.js"></script>

</body>

</html>
