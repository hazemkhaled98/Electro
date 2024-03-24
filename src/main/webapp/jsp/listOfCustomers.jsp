<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ page import="com.electro.presentation.dto.CustomerDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Electro | Manage Customers</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/PaginationCSS.css">
<%--    <script src="${pageContext.request.contextPath}/js/PaginationJS.js"></script>--%>
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

<body class="d-flex flex-column h-100">



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


<section id="customerTable" class="mt-5 ">
    <div class="container px-4">
        <div class="text-center">
            <h1 class="text-xl font-weight-bold text-gray-900">Customers</h1>
        </div>
        <div class="flex flex-col mt-8">
            <div class="overflow-auto shadow-lg" id="tableParent">
                <table class="table table-striped">
                    <thead class="bg-gray-50">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Job</th>
                        <th scope="col">Email</th>
                        <th scope="col">Credit Limit</th>
                        <th scope="col">City</th>
                        <th scope="col">Country</th>
                        <th scope="col">Street No.</th>
                        <th scope="col">Street Name</th>
                        <th scope="col">View Customer</th>

                    </tr>
                    </thead>
                    <tbody class="bg-white" id="tbody">
                    <%--<jsp:useBean id="CustomerDTO" scope="request" type="java.util.List"/>
                    <c:forEach items="${CustomerDTO}" var="customer">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.customerName}</td>
                            <td>${customer.birthday}</td>
                            <td>${customer.job}</td>
                            <td>${customer.email}</td>
                            <td>${customer.creditLimit}</td>
                            <td>${customer.city}</td>
                            <td>${customer.country}</td>
                            <td>${customer.streetNo}</td>
                            <td>${customer.streetName}</td>
                            <td><button class="view-customer" onclick="window.location.href='/customerDetails?customerID=${customer.id}'">View</button></td>                        </tr>
                    </c:forEach>--%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</section>

<section id="pagination">
    <input type="hidden" value="${requestScope.PAGE_NUMBER}" id="pageNumber">
    <input type="hidden" value="1" id="currentPageNumberCust">

    <nav class="d-flex justify-content-center" style="margin-top: 20px">
        <div class="w-0 flex-1 flex justify-content-start">
            <a id="previousButtonCust"
               class="btn btn-primary mr-2 px-4 py-2 rounded-pill text-sm font-weight-medium">
<%--               href="?pageNumber=${PAGE_NUMBER - 1}">--%>
                Previous
            </a>
        </div>
        <div class="w-0 flex-1 flex justify-content-end">
            <a id="nextButtonCust"
               class="btn btn-primary px-4 py-2 rounded-pill text-sm font-weight-medium">
                Next
            </a>
        </div>
    </nav>
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
<script src="${pageContext.request.contextPath}/js/CustomersJS.js"></script>

</body>

</html>
