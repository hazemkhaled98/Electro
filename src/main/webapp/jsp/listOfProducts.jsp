<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Electro | Manage Products</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex flex-column h-100">

<section id="productTable" class="mt-5">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1 class="text-center text-xl font-weight-bold text-gray-900">Products</h1>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="bg-gray-50">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Category</th>
                            <th scope="col">Edit</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody id="tbody">
                        <!-- products -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</section>

<section id="pagination">
    <input type="hidden" value="${requestScope.pagesCount}" id="pagesCount">
    <input type="hidden" value="1" id="currentPageNumber">

    <nav class="border-top mx-10 border-gray-200 px-4 d-flex justify-content-center">
        <div class="w-0 flex-1 flex justify-content-start">
            <a id="previousButton"
               class="btn btn-primary mr-2 px-4 py-2 rounded-pill text-sm font-weight-medium">
                Previous
            </a>
        </div>
        <div class="w-0 flex-1 flex justify-content-end">
            <a id="nextButton"
               class="btn btn-primary px-4 py-2 rounded-pill text-sm font-weight-medium">
                Next
            </a>
        </div>
    </nav>
</section>

<section id="deleteAlert">
    <!-- Delete alert modal content -->
</section>

<section class="mt-auto" id="footer">
    <footer class="bg-white">
        <div class="container py-5">
            <div class="text-center text-gray-400">&copy; Electro</div>
        </div>
    </footer>
</section>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/adminProducts.js"></script>


</body>

</html>
