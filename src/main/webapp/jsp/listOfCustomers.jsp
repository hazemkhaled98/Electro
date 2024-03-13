<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>


<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Electro | Manage Customers</title>
</head>

<body class="d-flex flex-column h-100">




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
                        <th scope="col">Full Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Address</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Job</th>
                        <th scope="col">Credit Limit</th>
                    </tr>
                    </thead>
                    <tbody class="bg-white" id="tbody">

                    </tbody>
                </table>
            </div>
        </div>
    </div>

</section>

<section id="pagination">
    <input type="hidden" value="${pageNumber}" id="pageNumber">
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

<section class="mt-auto" id="footer">
    <footer class="bg-white">
        <div class="container py-3">
            <div class="text-center text-gray-400">&copy; Electro</div>
        </div>
    </footer>
</section>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
