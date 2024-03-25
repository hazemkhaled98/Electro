<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>${customer.fullName}'s Orders | Electro</title>
</head>

<body class="d-flex flex-column min-vh-100">


<section id="profileSection">
    <div class="container py-5">
        <div class="row g-3">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2 class="card-title">Order Info</h2>
                    </div>
                    <div class="card-body">
                        <dl class="row">
                            <dt class="col-sm-4">ID</dt>
                            <dd class="col-sm-8">${order.id}</dd>
                            <dt class="col-sm-4">Owner Name</dt>
                            <dd class="col-sm-8">${customer.fullName}</dd>
                            <dt class="col-sm-4">Total cost</dt>
                            <dd class="col-sm-8">EGP ${order.totalFormatted}.00</dd>
                            <dt class="col-sm-4">Time Stamp</dt>
                            <dd class="col-sm-8">${order.timestamp}</dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2 class="card-title">Order Item</h2>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Product Name</th>
                                <th>Unit Cost</th>
                                <th>Quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${itemList}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.product.name}</td>
                                    <td>EGP ${item.unitCostFormatted}.00</td>
                                    <td>${item.quantity}</td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty itemList}">
                                <tr>
                                    <td colspan="4">NO Orders</td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<footer id="footer" class="mt-auto bg-light">
    <div class="container py-3">
        <div class="text-center text-muted">&copy; Electro</div>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
