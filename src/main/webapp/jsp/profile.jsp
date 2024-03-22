	<%@ page contentType="text/html;charset=UTF-8" language="java"   %>
	<%@ page import="com.electro.persistence.entities.Customer" %>
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
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slick-theme.css" />

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/nouislider.min.css" />

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">

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
						<h3 class="breadcrumb-header">Update Profile</h3>
						<ul class="breadcrumb-tree">
							<li><a href="/home">Home</a></li>
							<li class="active">Edit Profile</li>
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
				<form action="/update" method="post" class="row" id="sc-edprofile">
					<h1>Edit Profile</h1>
					<div class="sc-container">
						<label for="name">Name:</label><br>
						<input type="text" placeholder="Name should contain letters and digits only"
							name="name"  pattern="[A-Za-z][A-Za-z0-9]*" id="name"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.customerName : ''}"/>

						<label for="job">Job:</label><br>
						<input type="text" placeholder="Job" name="job"  required pattern="[A-Za-z\s]+" id="job"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.job : ''}"/>

						<label for="country">Country:</label><br>
						<input type="text" placeholder="Country" name="country"
							pattern="[A-Za-z]+" id="country"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.country : ''}"/>

						<label for="city">City:</label><br>
						<input type="text" placeholder="City" name="city"
							pattern="[A-Za-z]+" id="city"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.city : ''}"/>

						<label for="streetNo">Street No:</label><br>
						<input type="text" placeholder="Street No." name="streetNo"
							   required id="streetNo"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.streetNo : ''}"/>

						<label for="streetName">Street Name:</label><br>
						<input type="text" placeholder="Street Name" name="streetName"
							required id="streetName"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.streetName : ''}"/>

						<label for="creditLimit">Credit Limit:</label><br>
						<input type="number" placeholder="Credit limit" name="creditLimit" id="creditLimit" min="0"
							   value="${not empty sessionScope.LOGGED_IN_CUSTOMER ? sessionScope.LOGGED_IN_CUSTOMER.creditLimit : ''}"/>

						<input type="submit" value="Update Profile"/>
					</div>

				</form>

				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->

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