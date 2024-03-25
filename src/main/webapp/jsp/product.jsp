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
 		<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="../css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="../css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="../css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="../css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="../css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>

	<jsp:include page="header.jsp"/>

		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="/home?category=all">Home</a></li>
							<li>${requestScope.PRODUCT.category}s</li>
							<li class="active">${requestScope.PRODUCT.name}</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

	<div id="addToCartError" class="alert alert-danger" style="display: none; font-size: 16px;">
		<p class="center"><strong>You Can't Add This Product To The Cart</strong></p>
	</div>

	<div id="addToCartSuccess" class="alert alert-success" style="display: none; font-size: 16px;">
		<p class="center"><strong>The Product Is Added To The Cart Successfully</strong></p>
	</div>

		<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row" style="display: flex; align-items: center">
				<!-- Product main img -->
				<div class="col-md-7">
					<div id="product-main-img">
						<div class="product-preview">
							<img src="${requestScope.PRODUCT.productPic}" alt="productImage">
						</div>
					</div>
				</div>
				<!-- /Product main img -->

				<!-- Product details -->
				<div class="col-md-4">
					<div class="product-details">
						<h2 class="product-name">${requestScope.PRODUCT.name}</h2>
						<div>
							<h3 class="product-price">$${requestScope.PRODUCT.price}</h3>
							<c:choose>
								<c:when test="${requestScope.PRODUCT.quantity > 0}">
									<span class="product-available">${requestScope.PRODUCT.quantity} Items In Stock</span>
								</c:when>
								<c:otherwise>
									<span class="product-available">Out of Stock</span>
								</c:otherwise>
							</c:choose>
						</div>
						<p>${requestScope.PRODUCT.description}</p>

						<div class="add-to-cart">
							<div class="qty-label">
								Qty
								<div class="input-number">
									<input type="number" id="quantityNum" value="1" max="${requestScope.PRODUCT.quantity}" min="1">
<%--									<span class="qty-up">+</span>--%>
<%--									<span class="qty-down">-</span>--%>
								</div>
							</div>
							<c:choose>
								<c:when test="${requestScope.PRODUCT.quantity == 0}">
									<button class="add-to-cart-btn" disabled><i class="fa fa-shopping-cart"></i> add to cart</button>
								</c:when>
								<c:otherwise>
									<button class="add-to-cart-btn" onclick="addToCart('${requestScope.PRODUCT.name}')"><i class="fa fa-shopping-cart"></i> add to cart</button>
								</c:otherwise>
							</c:choose>
						</div>

						<ul class="product-links">
							<li>Category:</li>
							<li>${requestScope.PRODUCT.category}</li>
						</ul>

					</div>
				</div>
				<!-- /Product details -->

			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>

	<!-- /container -->

			<jsp:include page="footer.jsp"/>


		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>
		<script src="${pageContext.request.contextPath}/js/addToCart.js"></script>

	</body>
</html>
