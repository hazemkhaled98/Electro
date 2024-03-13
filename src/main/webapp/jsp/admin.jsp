<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>


        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
            <title>Electro | Admin Panel</title>
        </head>

        <body class="flex flex-col h-screen">

                <!--   Cards Section  -->
                <section id="Cards" class="flex justify-center">
                    <div class="container py-16 px-4">
                        <h2 class="text-center text-2xl font-weight-bold text-gray-900 mb-5">Electro Admin Panel</h2>
                        <div class="row">
                            <div class="col-lg-4 mb-4">
                                <div class="card">
                                    <img src="${pageContext.request.contextPath}/img/customer.png" class="card-img-top"
                                         alt="go to customers"/>
                                    <div class="card-body">
                                        <a href="admin/customers" class="btn btn-primary">Go to customers</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 mb-4">
                                <div class="card">
                                    <img src="${pageContext.request.contextPath}/img/product.png" class="card-img-top"
                                         alt="go to products"/>
                                    <div class="card-body">
                                        <a href="admin/products" class="btn btn-primary">Go to products</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 mb-4">
                                <div class="card">
                                    <img src="${pageContext.request.contextPath}/img/addproduct.png" class="card-img-top"
                                         alt="add product"/>
                                    <div class="card-body">
                                        <a href="admin/add-product" class="btn btn-primary">Add Product</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>



                <!-- Footer -->
                <section class="mt-auto" id="footer">
                    <footer class="bg-light">
                        <div class="container py-3">
                            <div class="text-center text-gray-400">&copy; Electro Dashboard</div>
                        </div>
                    </footer>
                </section>


                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        </body>

        </html>