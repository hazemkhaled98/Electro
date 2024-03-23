<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Electro</title>


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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="header.jsp"/>

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- shop -->
            <div class="col-md-4 col-xs-6">
                <div class="shop">
                    <div class="shop-img">
                        <img src="${pageContext.request.contextPath}/img/laptop.jpg" alt="">
                    </div>
                    <div class="shop-body">
                        <h3>Laptops<br>Collection</h3>
                        <a href="/home?category=laptop" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
            </div>
            <!-- /shop -->

            <!-- shop -->
            <div class="col-md-4 col-xs-6">
                <div class="shop">
                    <div class="shop-img">
                        <img src="${pageContext.request.contextPath}/img/smartphone.jpg" alt="">
                    </div>
                    <div class="shop-body">
                        <h3>Smartphones<br>Collection</h3>
                        <a href="/home?category=smartphone" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
            </div>
            <!-- /shop -->

            <!-- shop -->
            <div class="col-md-4 col-xs-6">
                <div class="shop">
                    <div class="shop-img">
                        <img src="${pageContext.request.contextPath}/img/camera.jpg" alt="">
                    </div>
                    <div class="shop-body">
                        <h3>Cameras<br>Collection</h3>
                        <a href="/home?category=camera" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
            </div>
            <!-- /shop -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->




<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- ASIDE -->
            <div id="aside" class="col-md-3">
                <!-- aside Widget -->
                <div class="aside">
                    <h3 class="aside-title">Categories</h3>
                    <div class="checkbox-filter">

                        <div class="input-checkbox">
                            <input type="checkbox" id="laptop">
                            <label for="laptop">
                                <span></span>
                                Laptop
                            </label>
                        </div>

                        <div class="input-checkbox">
                            <input type="checkbox" id="smartphone">
                            <label for="smartphone">
                                <span></span>
                                Smartphone
                            </label>
                        </div>

                        <div class="input-checkbox">
                            <input type="checkbox" id="camera">
                            <label for="camera">
                                <span></span>
                                Camera
                            </label>
                        </div>

                    </div>
                </div>
                <!-- /aside Widget -->

                <!-- aside Widget -->
                <div class="aside">
                    <h3 class="aside-title">Price</h3>
                    <div class="price-filter">
                        <div id="price-slider"></div>
                        <div class="input-number price-min">
                            <input id="price-min" type="number">
                            <span class="qty-up">+</span>
                            <span class="qty-down">-</span>
                        </div>
                        <span>-</span>
                        <div class="input-number price-max">
                            <input id="price-max" type="number">
                            <span class="qty-up">+</span>
                            <span class="qty-down">-</span>
                        </div>
                    </div>
                </div>
                <!-- /aside Widget -->
            </div>
            <!-- /ASIDE -->

            <!-- STORE -->
            <div id="store" class="col-md-9">

                <div id="addToCartError" class="alert alert-danger" style="display: none; font-size: 16px;">
                    <p class="center"><strong>You Can't Add This Product To The Cart</strong></p>
                </div>

                <div id="addToCartSuccess" class="alert alert-success" style="display: none; font-size: 16px;">
                    <p class="center"><strong>The Product Is Added To The Cart Successfully</strong></p>
                </div>

                <%--    Store--%>
                <section>
                    <!-- store products -->
                    <div id="product-container" class="row">

                        <!-- product -->
                        <c:choose>
                            <c:when test="${not empty requestScope.PRODUCTS}">
                                <c:forEach var="product" items="${requestScope.PRODUCTS}">
                                    <div class="col-md-4 col-xs-6">
                                        <div class="product">

                                            <div class="product-img">
                                                <img src="${product.productPic}" alt="productImage">
                                                <div class="product-label"></div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category">${product.category}</p>
                                                <h3 class="product-name">${product.name}</h3>
                                                <h4 class="product-price">$${product.price}</h4>
                                                <div class="product-btns">
                                                    <button class="quick-view"><a href="/product?name=${product.name}" class="fa fa-eye"></a><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <c:choose>
                                                    <c:when test="${product.quantity == 0}">
                                                        <button class="add-to-cart-btn" disabled><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" id="quantityNum" value="1">
                                                        <button class="add-to-cart-btn" onclick="addToCart('${product.name}')"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix visible-sm visible-xs"></div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: center">
                                    <h1 style="color: #ac1027;">No Products Matched</h1>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <!-- /product -->

                    </div>
                    <!-- /store products -->
                </section>
                <%--    /Store--%>
                <!-- store bottom filter -->
                <div class="store-filter clearfix">
                    <ul class="store-pagination">
                        <li id="previous-btn"><a><i class="fa fa-angle-left"></i></a></li>
                        <li id="next-btn"><a><i class="fa fa-angle-right"></i></a></li>
                    </ul>
                    <span hidden id="pageNumber">1</span>
                </div>
                <!-- /store bottom filter -->
            </div>
            <!-- /STORE -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->







<button class="btn btn-danger back-to-top" id="back-to-top"><i class="fas fa-arrow-up"></i></button>



<jsp:include page="footer.jsp"/>

<!-- jQuery Plugins -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/addToCart.js"></script>

</body>
</html>

