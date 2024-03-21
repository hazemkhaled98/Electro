<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Electro | Checkout</title>

 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap -->
 		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>

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
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">Checkout</h3>
						<ul class="breadcrumb-tree">
							<li><a href="/home">Home</a></li>
							<li class="active">Checkout</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row center">
				<c:choose>
					<c:when test="${empty requestScope.CART_ITEMS}">
						<div style="text-align: center; margin-top: 100px; height: 15vh;">
							<h1 style="color: #ac1027">Your cart is Empty</h1>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-md-6 order-details">
							<form onsubmit="checkout(event)">
								<div class="section-title text-center">
									<h3 class="title">Your Order</h3>
								</div>
								<div class="order-summary">
									<div class="order-col">
										<div><strong>PRODUCT</strong></div>
										<div><strong>TOTAL</strong></div>
									</div>
									<div class="order-products">
										<c:forEach items="${requestScope.CART_ITEMS}" var="item">
											<div class="order-col">
												<div>${item.quantity}x ${item.name}</div>
												<div>$${item.price}</div>
											</div>
										</c:forEach>
									</div>
									<div class="order-col">
										<div><strong>TOTAL</strong></div>
										<div><strong class="order-total">$<span id="total">${requestScope.TOTAL}</span></strong></div>
									</div>
								</div>
								<input value="${requestScope.TOTAL}" name="total"  hidden/>
								<input type="submit" value="Place Order" class="primary-btn order-submit"/>
							</form>
							<div  id="errorMessage" class="alert alert-danger" style="display: none; font-size: 16px; margin: 20px;">
								<p id="errorMessageTxt" class="center"></p>
							</div>

							<div id="successMessage" class="alert alert-success" style="display:none; font-size: 16px; margin: 20px">
								<p id="successMessageTxt" class="center"></p>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			<!-- /row -->
		</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->


		<!-- jQuery Plugins -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
		<script src="${pageContext.request.contextPath}/js/checkout.js"></script>


		<jsp:include page="footer.jsp"/>
	</body>
</html>
