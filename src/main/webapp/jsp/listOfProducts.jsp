<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Electro | Manage Products</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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

<body class="d-flex flex-column">

<%--<nav id="navigation">--%>
<%--    <!-- container -->--%>
<%--    <div class="container">--%>
<%--        <!-- responsive-nav -->--%>
<%--        <div id="responsive-nav" class="">--%>
<%--            <!-- NAV -->--%>
<%--            <ul class="main-nav nav navbar-nav nav-row">--%>
<%--                <li><a href="${pageContext.request.contextPath}/admin/customers">Customers</a></li>--%>
<%--                <li><a href="${pageContext.request.contextPath}/admin/products">Products</a></li>--%>
<%--                <li><a href="${pageContext.request.contextPath}/admin/add-product">Add product</a></li>--%>
<%--            </ul>--%>
<%--            <!-- /NAV -->--%>
<%--        </div>--%>
<%--        <!-- /responsive-nav -->--%>
<%--    </div>--%>
<%--    <!-- /container -->--%>
<%--</nav>--%>

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


<section id="productTable" class="mt-5">

<%--    <div class="container">--%>

        <div class="row">
            <div class="col">
                <h1 class="text-center text-xl font-weight-bold text-gray-900">Products</h1>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="bg-gray-50">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Category</th>
                            <th scope="col">Edit</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody id="tbody">
                        <!-- products -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
<%--    </div>--%>

</section>

<section id="pagination">
    <input type="hidden" value="${requestScope.PAGES_COUNT}" id="pagesCount">
    <input type="hidden" value="1" id="currentPageNumber">

    <nav class="d-flex justify-content-center" style="margin-top: 10px">
        <div class="w-0 flex-1 flex justify-content-start">
            <a id="previousButton"
               class="btn btn-primary mr-2 px-4 py-2 rounded-pill text-sm font-weight-medium">
                Previous
            </a>
        </div>
        <div class="w-0 flex-1 flex justify-content-end">
            <a id="nextButton"
               class="btn btn-primary px-4 py-2 rounded-pill text-sm font-weight-medium">
                Next
            </a>
        </div>
    </nav>
</section>

<div id="errorMessage" class="alert alert-danger" role="alert" style="display: ${not empty requestScope.ERROR ? 'block' : 'none'}; font-size: 16px; margin-top: 20px">
    <p class="text-center">${requestScope.ERROR}</p>
</div>

<div id="successMessage" class="alert alert-success" style="display: ${not empty requestScope.SUCCESS ? 'block' : 'none'}; font-size: 16px; margin-top: 20px">
    <p class="text-center">${requestScope.SUCCESS}</p>
</div>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/adminProducts.js"></script>
<script src="${pageContext.request.contextPath}/js/productsMessages.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>

</html>
