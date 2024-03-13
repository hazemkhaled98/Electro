<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Viewing ${customer.fullName}'s account | Admin Dashboard</title>
</head>

<body class="d-flex flex-column h-100">


<section id="profileSection">
    <div
            class="grid max-w-3xl grid-cols-1 gap-6 mx-auto mt-8 sm:px-6 lg:max-w-7xl lg:grid-flow-col-dense lg:grid-cols-2">
        <div class="space-y-6 lg:col-start-1 lg:col-span-2">
            <!-- Description list-->
            <section aria-labelledby="applicant-information-title">
                <div class="bg-white shadow sm:rounded-lg">
                    <div class="px-4 py-5 sm:px-6">
                        <h2 id="applicant-information-title" class="text-lg font-medium leading-6 text-gray-900">
                            ${customer.fullName}</h2>
                    </div>
                    <div class="px-4 py-5 border-t border-gray-200 sm:px-6">
                        <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
                            <div class="sm:col-span-1">
                                <dt class="text-sm font-medium text-gray-500">ID</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.id}</dd>
                            </div>
                            <div class="sm:col-span-1">
                                <dt class="text-sm font-medium text-gray-500">Email</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.email}</dd>
                            </div>
                            <div class="sm:col-span-1">
                                <dt class="text-sm font-medium text-gray-500">Address</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.addressFormatted}</dd>
                            </div>
                            <div class="sm:col-span-1">
                                <dt class="text-sm font-medium text-gray-500">Phone</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.phoneNumber}</dd>
                            </div>
                            <div class="sm:col-span-1">
                                <dt class="text-sm font-medium text-gray-500">Job</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.job}</dd>
                            </div>
                            <div class="sm:col-span-1">
                                <dt class="text-sm font-medium text-gray-500">Birthday</dt>
                                <dd class="mt-1 text-sm text-gray-900">${customer.birthday}</dd>
                            </div>
                            <div class="sm:col-span-2">
                                <dt class="text-sm font-medium text-gray-500">Credit Limit</dt>
                                <dd class="mt-1 text-sm text-gray-900">EGP ${customer.creditLimitFormatted}.00</dd>
                            </div>

                        </dl>
                    </div>
                </div>
            </section>

            <!-- Comments-->
            <section aria-labelledby="notes-title">
                <div class="bg-white shadow sm:rounded-lg sm:overflow-hidden">
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
                                                <c:if test="${orderList.size() !=0}">
                                                    <table class="table table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col">ID</th>
                                                            <th scope="col">Time Stamp</th>
                                                            <th scope="col">Total Cost</th>
                                                            <th scope="col">Order</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${orderList}" var="order">
                                                            <tr>
                                                                <td>${order.id}</td>
                                                                <td>${order.timestamp}</td>
                                                                <td>EGP ${order.totalFormatted}.00</td>
                                                                <td><a href="/admin/customers/customer/order?orderId=${order.id}&userId=${customer.id}" class="text-indigo-600">Order</a></td>
                                                            </tr>
                                                        </c:forEach>


                                                        <!-- More transactions... -->
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                                <c:if test="${orderList.size() ==0}">
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
