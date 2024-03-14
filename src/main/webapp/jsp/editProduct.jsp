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

    <div class="space-y-6">
        <div class="px-4 py-5 bg-white shadow-sm rounded-lg sm:p-6">
            <div class="row">
                <div class="col">
                    <h3 class="text-lg font-medium">Product Information</h3>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col">
                    <form id="productEditForm" action="${pageContext.request.contextPath}/admin/products/edit" enctype="multipart/form-data" method="POST">
                        <input type="hidden" value="${requestScope.product.id}" id="idEdit" name="idEdit">
                        <div class="row g-3">

                            <div class="col-12">
                                <label class="form-label">Photo</label>
                                <div class="mb-3">
                                    <img id="imageOfProductEdit" src="${pageContext.request.contextPath}/img/${requestScope.product.name}.png" alt="productImage" class="img-fluid">
                                </div>
                                <div class="col-12">
                                    <label id="uploadImageButtonEdit" class="btn btn-light">
                                        <input class="d-none" type="file" name="productPhotoEdit" accept="image/jpg, image/png, image/jpeg" id="productPhotoEdit">
                                        Add Photo
                                    </label>
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="nameEdit" class="form-label">Name</label>
                                <input type="text" name="nameEdit" id="nameEdit" autocomplete="given-name" required value="${requsetScope.product.name}" class="form-control">
                                <span class="form-text text-danger" id="nameValidationEdit">This field is invalid</span>
                            </div>

                            <div class="col-12">
                                <label for="descriptionEdit" class="form-label">Description</label>
                                <textarea id="descriptionEdit" name="descriptionEdit" rows="3" class="form-control">${requsetScope.product.description}</textarea>
                                <span class="form-text text-danger" id="descriptionValidationEdit">This field is invalid</span>
                            </div>

                            <div class="col-12">
                                <label for="quantityEdit" class="form-label">Quantity</label>
                                <input value="${requsetScope.product.quantity}" type="number" min="1" name="quantityEdit" id="quantityEdit" required class="form-control">
                            </div>

                            <div class="col-12">
                                <label for="priceEdit" class="form-label">Price</label>
                                <input value="${requsetScope.product.price}" name="priceEdit" id="priceEdit" type="number" min="1" required class="form-control">
                            </div>

                            <div class="col-12">
                                <input type="hidden" value="${requsetScope.product.category}" id="valueOfCategory">
                                <label for="categoryEdit" class="form-label">Category</label>
                                <select id="categoryEdit" name="categoryEdit" class="form-select">
                                    <option selected>Laptop</option>
                                    <option>Smartphones</option>
                                    <option>Camera</option>
                                </select>
                            </div>

                        </div>
                        <div class="d-flex justify-content-end mt-3">
                            <button id="cancelButtonEdit" type="button" onclick= location.href="/admin/products" class="btn btn-light">Cancel</button>
                            <button id="submitButtonEdit" type="submit" class="btn btn-primary ms-3">Edit</button>
                            <div id="spinner" class="spinner-border text-primary d-none" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>

</section>

<section id="feedback" class="mt-3">
    <c:if test="${requestScope.failedToEditProduct}">
        <div id="failDivEdit" class="rounded-md bg-danger text-white p-4 mt-3">
            <div class="d-flex">
                <div class="flex-shrink-0">
                    <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                        <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9 5a1 1 0 112 0v5a1 1 0 11-2 0V5zm1 10a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd"></path>
                    </svg>
                </div>
                <div class="ml-3">
                    <h3 class="text-sm font-medium">Failed to Edit Product</h3>
                    <div class="mt-2 text-sm">
                        <p>${requestScope.editProductErrorMessage}</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.successfullyEditedProduct}">
        <div id="successDivEdit" class="rounded-md bg-success text-white p-4 mt-3">
            <div class="d-flex">
                <div class="flex-shrink-0">
                    <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                        <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9 5a1 1 0 112 0v5a1 1 0 11-2 0V5zm1 10a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd" />
                    </svg>
                </div>
                <div class="ml-3">
                    <h3 class="text-sm font-medium">Successfully Edited Product</h3>
                    <div class="mt-2 text-sm">
                        <p>${requestScope.editProductSuccessMessage}</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</section>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>

</html>
