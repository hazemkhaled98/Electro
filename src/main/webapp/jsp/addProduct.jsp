<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Electro | Add Product</title>
    <link rel="stylesheet" href="../css/NavBar.css">
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">


    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/nouislider.min.css"/>

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>

</head>

<body class="d-flex flex-column min-vh-100">

<header>

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="/home?category=all" class="logo">
                            <img src="${pageContext.request.contextPath}/img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->


                <div class="col-md-6" style="display: flex; justify-content: center; align-items: center">
                    <h1 class="text-center text-2xl font-weight-bold" style="color: white">Electro Admin</h1>
                </div>

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">


                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->

<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav">
            <!-- NAV -->
            <ul class="main-nav nav navbar-nav">
                <li><a href="/admin">Admin Home</a></li>
                <li><a href="/admin/customers">Manage Customers</a></li>
                <li><a href="/admin/products">Manage Products</a></li>
                <li><a href="/admin/add-product">Add Products</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>


<section id="product h-full">

    <div class="container mt-3">

        <div id="errorMessage" class="alert alert-danger" role="alert" style="display: ${not empty requestScope.ERROR ? 'block' : 'none'}; font-size: 16px;">
            <p class="center">${requestScope.ERROR}</p>
        </div>


        <div id="successMessage" class="alert alert-success" style="display: ${not empty requestScope.SUCCESS ? 'block' : 'none'}; font-size: 16px;">
            <p class="center">${requestScope.SUCCESS}</p>
        </div>


        <div class="card h-full">
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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/productsMessages.js"></script>

</body>

</html>
