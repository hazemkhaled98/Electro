<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>


        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
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
            <title>Electro | Admin</title>
        </head>

        <body>

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

                <!--   Cards Section  -->
                <section id="Cards" class="flex justify-center f-full">
                    <div class="container py-16 px-4">
                        <div class="row f-full">
                            <div class="col-lg-4 mb-4">
                                <div class="card f-full">
                                    <img src="${pageContext.request.contextPath}/img/customer.png" class="card-img-top"
                                         alt="go to customers"/>
                                    <div class="card-body">
                                        <a href="admin/customers" class="btn btn-primary">Go to customers</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 mb-4">
                                <div class="card f-full">
                                    <img src="${pageContext.request.contextPath}/img/product.png" class="card-img-top"
                                         alt="go to products"/>
                                    <div class="card-body">
                                        <a href="admin/products" class="btn btn-primary">Go to products</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 mb-4">
                                <div class="card f-full">
                                    <img src="${pageContext.request.contextPath}/img/addproduct.png" class="card-img-top"
                                         alt="add product"/>
                                    <div class="card-body">
                                        <a href="admin/add-product" class="btn btn-primary">Add Product</a>
                                    </div>
                                </div>
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