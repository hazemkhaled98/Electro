<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>

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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">

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
					<h3 class="breadcrumb-header">SignUp</h3>
					<ul class="breadcrumb-tree">
						<li><a href="#">Home</a></li>
						<li class="active">SignUp</li>
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
		<div id="passwordMatch" class="alert alert-danger" style="display: none; font-size: 16px;">
			<p class="center">Passwords do not match.</p>
		</div>
		<div id="errorMessage" class="alert alert-danger" style="display: ${not empty requestScope.errorMessage ? 'block' : 'none'}; font-size: 16px;">
			<p class="center">${requestScope.ERROR}</p>
		</div>
			<!-- row -->
		<form action="" method="post" class="row" id="sc-edprofile">
			<h1>SignUp</h1>
			<div class="sc-container">
				<!-- Populate all input fields with SignUpDTO data -->
				<label for="name">Name:</label><br>
				<input type="text" placeholder="Name should contain letters and digits only"
					   name="name" id="name" required pattern="[A-Za-z][A-Za-z0-9]*"
					   value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.name : ''}"/><br>
				<label for="email">E-mail:</label><br>
				<input type="email" placeholder="Email Address" name="email" id="email" required
					   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"
					   value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.email : ''}" /><br>
				<label for="password">Password:</label><br>
				<input type="password" id="password" placeholder="min length is 8 and only alphanumeric characters" name="password" required pattern="[a-zA-Z0-9]{8,}" minlength="8"><br>
				<label for="confirmPassword">Confirm Password:</label><br>
				<input type="password" placeholder="Confirm Password" name="confirmPassword" id="confirmPassword" required /><br>
				<!-- Similarly populate other input fields -->
				<label for="birthdate">Birthdate:</label><br>
				<input type="date" placeholder="Birthdate" name="birthdate" id="birthdate" required
					value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.birthdate : ''}"/> <br>
				<label for="job">Job:</label><br>
				<input type="text" placeholder="Job" name="job" id="job" required pattern="[A-Za-z\s]+"
					   value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.job : ''}"/> <br>

				<label for="country">Country:</label><br>
				<input type="text" placeholder="Country" name="country" id="country" required
					value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.country : ''}"/><br>
				<label for="city">City:</label><br>
				<input type="text" placeholder="City" name="city" id="city" required
					value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.city : ''}"/><br>
				<label for="street_no">Street No:</label><br>
				<input type="text" placeholder="Street No." name="street_no"  id="street_no" required
					value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.streetNo : ''}"/><br>
				<label for="street_name">Street Name:</label><br>
				<input type="text" placeholder="Street Name" name="street_name" id="street_name" required
					value="${not empty requestScope.ENTERED_USER ? requestScope.ENTERED_USER.streetName : ''}"/><br>
				<input type="submit" value="SignUp" />

			</div>
		</form>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->

	<jsp:include page="footer.jsp"/>

	<!-- jQuery Plugins -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>

	<script src="${pageContext.request.contextPath}/js/signup.js"></script>


</body>

</html>