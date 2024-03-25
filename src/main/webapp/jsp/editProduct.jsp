<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Electro | Edit Product</title>
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

<body class="d-flex flex-column h-100">

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

<section>

    <div class="container mt-5">


        <div class="row h-full">
            <div class="col h-full">
                <h2 class="text-lg font-medium" style="text-align: center">Product Information</h2>
            </div>
        </div>
        <div class="row mt-3 h-full">
            <div class="col h-full">
                <form id="productEditForm" action="${pageContext.request.contextPath}/admin/products/edit" enctype="multipart/form-data" method="POST">
                    <input type="hidden" value="${requestScope.productId}" id="idEdit" name="idEdit">
                    <div class="form-group">
                        <div style="text-align: center">
                            <img id="imageOfProductEdit" src="${requestScope.PRODUCT.productPic}" alt="productImage">
                        </div>
                        <div style="text-align: center">
                            <label id="uploadImageButtonEdit" class="btn btn-light">
                                <input class="d-none" type="file" name="productPhotoEdit" accept="image/png image/jpg image/jpeg" id="productPhotoEdit">
                                Edit Photo
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nameEdit">Name</label>
                        <input type="text" name="nameEdit" id="nameEdit" autocomplete="given-name" required value="${requestScope.PRODUCT.name}" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="descriptionEdit">Description</label>
                        <textarea id="descriptionEdit" name="descriptionEdit" rows="3" class="form-control">${requestScope.PRODUCT.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="quantityEdit">Quantity</label>
                        <input value="${requestScope.PRODUCT.quantity}" type="number" min="1" name="quantityEdit" id="quantityEdit" required class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="priceEdit">Price</label>
                        <input value="${requestScope.PRODUCT.price}" name="priceEdit" id="priceEdit" type="number" min="1" required class="form-control">
                    </div>

                    <div class="form-group">
                        <input type="hidden" value="${requestScope.PRODUCT.category}" id="valueOfCategory">
                        <label for="categoryEdit">Category</label>
                        <select id="categoryEdit" name="categoryEdit" class="form-control">
                            <option selected>Laptop</option>
                            <option>Smartphones</option>
                            <option>Camera</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="d-flex justify-content-end mt-3">
                            <button id="cancelButtonEdit" type="button" onclick="location.href='/admin/products'" class="btn btn-danger mr-2">Cancel</button>
                            <button id="submitButtonEdit" type="submit" class="btn btn-primary ms-3">Edit</button>
                            <div id="spinner" class="spinner-border text-primary d-none" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</section>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
