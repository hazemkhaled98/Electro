
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.electro.presentation.dto.CartItemDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    </head>
	<body>
		<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li><i class="fa fa-phone"></i>+201-012-456-789</li>
						<li><i class="fa fa-envelope-o"></i> electro@email.com</li>
						<li><i class="fa fa-map-marker"></i> Cairo, Egypt</li>
					</ul>
					<ul class="header-links pull-right">
						<li><i class="fa fa-dollar"></i>USD</li>
						<li><a href="/login"><i class="fa fa-user-o"></i> My Account</a></li>
						<%
							HttpSession session = request.getSession(false);
							if (session != null && session.getAttribute("LOGGED_IN_CUSTOMER") != null) {
						%>
						<li><a href="/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a></li>
						<%
							}
						%>

					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

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

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form>
									<select class="input-select">
										<option value="0">All Categories</option>
										<option value="1">Laptops</option>
										<option value="2">Smartphones</option>
										<option value="3">Cameras</option>
									</select>
									<input class="input" placeholder="Search here">
									<button class="search-btn">Search</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						<%
							HttpSession session2 = request.getSession(false);
							int cartSize = 0; // Default value
							if (session2 != null && session2.getAttribute("CART_ITEMS") != null) {
								List<CartItemDTO> cartItems = (List<CartItemDTO>) session2.getAttribute("CART_ITEMS");
								cartSize = cartItems.size(); // Set cartSize to the size of the cart items
							}
						%>

						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">

								<!-- Cart -->
								<div class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
										<i class="fa fa-shopping-cart"></i>
										<span>Your Cart</span>
										<div class="qty" id="cartQty"> <%= cartSize %> </div>
									</a>
									<div class="cart-dropdown">
										<div class="cart-list">
											<div class="product-widget">
												<div class="product-img">
													<img src="../img/product01.png" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="#">product name goes here</a></h3>
													<h4 class="product-price"><span class="qty">1x</span>$980.00</h4>
												</div>
												<button class="delete"><i class="fa fa-close"></i></button>
											</div>

											<div class="product-widget">
												<div class="product-img">
													<img src="../img/product02.png" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="#">product name goes here</a></h3>
													<h4 class="product-price"><span class="qty">3x</span>$980.00</h4>
												</div>
												<button class="delete"><i class="fa fa-close"></i></button>
											</div>
										</div>
										<div class="cart-summary">
											<small><span>3</span> Item(s) in the cart</small>
											<h5>Total: $2940.00</h5>
										</div>
										<div class="cart-btns">
<%--											<a href="#">View Cart</a>--%>
											<a href="#">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
										</div>
									</div>
								</div>
								<!-- /Cart -->

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
						<li><a href="/home">Home</a></li>
						<li><a href="/login">Account</a></li>
						<li><a href="/checkout">Checkout</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>

	</body>
</html>
