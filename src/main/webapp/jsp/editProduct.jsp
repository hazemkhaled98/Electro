<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Electro | Edit Product</title>
</head>

<body class="d-flex flex-column h-100">


<section>

    <div class="container mt-5">


        <div class="row">
            <div class="col">
                <h2 class="text-lg font-medium" style="text-align: center">Product Information</h2>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col">
                <form id="productEditForm" action="${pageContext.request.contextPath}/admin/products/edit" enctype="multipart/form-data" method="POST">
                    <input type="hidden" value="${requestScope.productId}" id="idEdit" name="idEdit">
                    <div class="form-group">
                        <div style="text-align: center">
                            <img id="imageOfProductEdit" src="data:${requestScope.PRODUCT.mimeType};base64,${requestScope.PRODUCT.productPic}" alt="productImage">
                        </div>
                        <div style="text-align: center">
                            <label id="uploadImageButtonEdit" class="btn btn-light">
                                <input class="d-none" type="file" name="productPhotoEdit" accept="image/png image/jpg image/jpeg" id="productPhotoEdit">
                                Edit Photo
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nameEdit">Name</label>
                        <input type="text" name="nameEdit" id="nameEdit" autocomplete="given-name" required value="${requestScope.PRODUCT.name}" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="descriptionEdit">Description</label>
                        <textarea id="descriptionEdit" name="descriptionEdit" rows="3" class="form-control">${requestScope.PRODUCT.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="quantityEdit">Quantity</label>
                        <input value="${requestScope.PRODUCT.quantity}" type="number" min="1" name="quantityEdit" id="quantityEdit" required class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="priceEdit">Price</label>
                        <input value="${requestScope.PRODUCT.price}" name="priceEdit" id="priceEdit" type="number" min="1" required class="form-control">
                    </div>

                    <div class="form-group">
                        <input type="hidden" value="${requestScope.PRODUCT.category}" id="valueOfCategory">
                        <label for="categoryEdit">Category</label>
                        <select id="categoryEdit" name="categoryEdit" class="form-control">
                            <option selected>Laptop</option>
                            <option>Smartphones</option>
                            <option>Camera</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="d-flex justify-content-end mt-3">
                            <button id="cancelButtonEdit" type="button" onclick="location.href='/admin/products'" class="btn btn-danger mr-2">Cancel</button>
                            <button id="submitButtonEdit" type="submit" class="btn btn-primary ms-3">Edit</button>
                            <div id="spinner" class="spinner-border text-primary d-none" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</section>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
