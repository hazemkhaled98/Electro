<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Electro - HTML Ecommerce Template</title>

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

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>

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
						<h3 class="breadcrumb-header">Regular Page</h3>
						<ul class="breadcrumb-tree">
							<li><a href="#">Home</a></li>
							<li class="active">Blank</li>
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
				<div id="errorMessage" class="alert alert-danger" style="display: ${not empty requestScope.ERROR ? 'block' : 'none'}; font-size: 16px;">
					<p class="center">${requestScope.ERROR}</p>
				</div>
				<!-- row -->
				<div class="row">
					<form action="/login" method="post" class="row" id="sc-edprofile">
						<h1>Login</h1>
						<div class="sc-container">
							<label for="email">E-mail:</label><br>
							<input type="email" id="email" placeholder="Email Address" name="email" required
								   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" />
							<label for="password">Password:</label>
							<input type="password" id="password" placeholder="min length is 8 and only alphanumeric characters" name="password" required pattern="[a-zA-Z0-9]{8,}" minlength="8">
							<input type="submit" value="Login" />
							<a href="${pageContext.request.contextPath}/signup" style="color: white">
								<div id="signup">
									SignUp
								</div>
							</a>
						</div>

					</form>

				</div>
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


	<jsp:include page="footer.jsp"/>
		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
