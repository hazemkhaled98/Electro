<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ page import="com.electro.presentation.dto.CustomerDTO" %>
<%@ page import="com.electro.presentation.dto.OrderDTO" %>
<jsp:useBean id="customer" class="com.electro.presentation.dto.CustomerDTO" scope="request"/>
<jsp:useBean id="orderDTOS" scope="request" type="java.util.List"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Viewing ${customer.customerName}'s account | Admin Dashboard</title>
    <link rel="stylesheet" href="../css/NavBar.css">
    <link rel="stylesheet" href="../css/CustomerDetails.css">

</head>

<body class="d-flex flex-column h-100">

<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav" class="">
            <!-- NAV -->
            <ul class="main-nav nav navbar-nav nav-row">
                <li><a href="${pageContext.request.contextPath}/admin/customers">Customers</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/products">Products</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/add-product">Add product</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>

<section id="profileSection">
    <div
            class="grid max-w-3xl grid-cols-1 gap-6 mx-auto mt-8 sm:px-6 lg:max-w-7xl lg:grid-flow-col-dense lg:grid-cols-2">
        <div class="space-y-6 lg:col-start-1 lg:col-span-2">
            <!-- Description list-->
            <%--<section aria-labelledby="applicant-information-title" id="profile">
                <div class="bg-white shadow sm:rounded-lg">
                   &lt;%&ndash; <div class="px-4 py-5 sm:px-6">
                        <h2 id="applicant-information-title" class="text-lg font-medium leading-6 text-gray-900">
                            ${customer.customerName}</h2>
                    </div>&ndash;%&gt;
                    &lt;%&ndash;<div class="px-4 py-5 border-t border-gray-200 sm:px-6">
                        <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
                            <div class="sm:col-span-1 info">
                                <dt class="text-sm font-medium text-gray-500">ID :</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.id}</dd>
                            </div>
                            <div class="sm:col-span-1 info">
                                <dt class="text-sm font-medium text-gray-500">Email :</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.email}</dd>
                            </div>
                            <div class="sm:col-span-1 info">
                                <dt class="text-sm font-medium text-gray-500">Address :</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.city}</dd>
                            </div>
                            <div class="sm:col-span-1 info">
                                <dt class="text-sm font-medium text-gray-500">streetNo :</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.streetNo}</dd>
                            </div>
                            <div class="sm:col-span-1 info">
                                <dt class="text-sm font-medium text-gray-500">Job :</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.job}</dd>
                            </div>
                            <div class="sm:col-span-1 info">
                                <dt class="text-sm font-medium text-gray-500">Birthday :</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.birthday}</dd>
                            </div>
                            <div class="sm:col-span-2 info">
                                <dt class="text-sm font-info text-gray-500">Credit Limit :</dt>
                                <dd class="mt-1 text-sm text-gray-900">EGP ${customer.creditLimit}.00</dd>
                            </div>

                        </dl>
                    </div>&ndash;%&gt;
                    &lt;%&ndash;<div class="flex flex-col mt-8 mb-8" >
                        <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                <div
                                        class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                        <table class="table table-striped" id="customerTable">
                                            <thead>
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
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td class="py-2 pl-4 pr-3 text-sm text-gray-500 whitespace-nowrap sm:pl-6">${customer.id}</td>
                                                <td class="px-2 py-2 text-sm font-medium text-gray-900">${customer.customerName}</td>
                                                <td class="px-2 py-2 text-sm text-gray-900 ">${customer.birthday}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.job}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.email}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.creditLimit}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.city}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.country}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.streetNo}</td>
                                                <td class="px-2 py-2 text-sm text-gray-500 whitespace-nowrap">${customer.streetName}</td>

                                            </tr>


                                            <!-- More transactions... -->
                                            </tbody>
                                        </table>

                                </div>
                            </div>
                        </div>
                    </div>&ndash;%&gt;
                    <div class="bg-white shadow sm:rounded-lg sm:overflow-hidden" id="orderArea">
                        <div class="divide-y divide-gray-200">
                            <div class="px-4 py-5 sm:px-6">
                                <h2  class="text-lg font-medium text-gray-900">${customer.customerName}</h2>
                            </div>
                            <section id="customerTable" class="mt-5">
                                <div class="px-4 sm:px-6 lg:px-8">
                                    <div class="sm:flex sm:items-center">

                                    </div>
                                    <div class="flex flex-col mt-8 mb-8">
                                        <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                                            <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                                <div
                                                        class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                                        <table class="table table-striped">
                                                            <thead>
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
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td >${customer.id}</td>
                                                                    <td>${customer.customerName}</td>
                                                                    <td>${customer.birthday}</td>
                                                                    <td >${customer.job}</td>
                                                                    <td >${customer.email}</td>
                                                                    <td >${customer.creditLimit}</td>
                                                                    <td >${customer.city}</td>
                                                                    <td >${customer.country}</td>
                                                                    <td >${customer.streetNo}</td>
                                                                    <td >${customer.streetName}</td>



                                                            <!-- More transactions... -->
                                                            </tbody>
                                                        </table>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </section>
                        </div>
                        <div class="px-4 py-2 bg-gray-50 sm:px-6">

                        </div>
                    </div>
                </div>
            </section>
--%>
            <section aria-labelledby="notes-title" id="cust">
                <div class="bg-white shadow sm:rounded-lg sm:overflow-hidden" id="customerArea">
                    <div class="divide-y divide-gray-200">
                        <div class="px-4 py-5 sm:px-6">
                            <h2 id="cust-name" class="text-lg font-medium text-gray-900">${customer.customerName}</h2>
                        </div>
                        <section id="customerTable" class="mt-5">
                            <div class="px-4 sm:px-6 lg:px-8">
                                <div class="sm:flex sm:items-center">

                                </div>
                                <div class="flex flex-col mt-8 mb-8">
                                    <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                                        <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                            <div
                                                    class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                                    <table class="table table-striped">
                                                        <thead>
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
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td >${customer.id}</td>
                                                                <td>${customer.customerName}</td>
                                                                <td>${customer.birthday}</td>
                                                                <td >${customer.job}</td>
                                                                <td >${customer.email}</td>
                                                                <td >${customer.creditLimit}</td>
                                                                <td >${customer.city}</td>
                                                                <td >${customer.country}</td>
                                                                <td >${customer.streetNo}</td>
                                                                <td >${customer.streetName}</td>
                                                            </tr>


                                                        <!-- More transactions... -->
                                                        </tbody>
                                                    </table>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </section>
                    </div>
                    <div class="px-4 py-2 bg-gray-50 sm:px-6">

                    </div>
                </div>
            </section>
            <!-- Comments-->
            <section aria-labelledby="notes-title" id="order">
                <div class="bg-white shadow sm:rounded-lg sm:overflow-hidden" id="orderArea">
                    <div class="divide-y divide-gray-200">
                        <div class="px-4 py-5 sm:px-6">
                            <h2 id="notes-title" class="text-lg font-medium text-gray-900">Orders</h2>
                        </div>
                        <section id="orderTable" class="mt-5">
                            <div class="px-4 sm:px-6 lg:px-8">
                                <div class="sm:flex sm:items-center">

                                </div>
                                <div class="flex flex-col mt-8 mb-8">
                                    <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                                        <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                            <div
                                                    class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                                <c:if test="${orderDTOS.size() !=0}">
                                                    <table class="table table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col">ID</th>
                                                            <th scope="col">Customer</th>
                                                            <th scope="col">Ordered at</th>
                                                            <th scope="col">Items</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${orderDTOS}" var="order">
                                                            <tr>
                                                                <td>${order.id}</td>
                                                                <td>${order.customer.customerName}</td>
                                                                <td>${order.orderedAt}</td>
                                                                <c:set var="productNames" value="" scope="page" />
                                                                <c:forEach items="${order.orderItems}" var="orderItem">
                                                                    <c:set var="productNames" value="${productNames}${orderItem.product.productName}, " scope="page" />
                                                                </c:forEach>
                                                                <td>${fn:substring(productNames, 0, fn:length(productNames)-2)}</td>
<%--                                                                <td>${order.orderItems.product}</td>--%>

<%--                                                                <td><a href="/admin/customers/customer/order?orderId=${order.id}&userId=${customer.id}" class="text-indigo-600">Order</a></td>--%>
                                                            </tr>
                                                        </c:forEach>


                                                        <!-- More transactions... -->
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                                <c:if test="${orderDTOS.size() ==0}">
                                                    <h4 class="text-center">No Orders</h4>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </section>
                    </div>
                    <div class="px-4 py-2 bg-gray-50 sm:px-6">

                    </div>
                </div>
            </section>
        </div>
    </div>
</section>

<section class="mt-auto" id="footer">
    <footer class="bg-white">
            <div class="mt-8 md:mt-0 md:order-1">
                <p class="text-base text-center text-gray-400">&copy; Electro</p>
            </div>
        </div>
    </footer>
</section>

<!-- Bootstrap JavaScript -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
