<%-- 
    Document   : UpdateProduct
    Created on : Jun 18, 2024, 12:48:43 AM
    Author     : LNV
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Details</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <style>
            .product-images img {
                max-width: 45%;
                height: auto;
                margin-bottom: 10px;
            }
            .product-details {
                margin-top: 20px;
            }
            .product-sizes {
                margin-top: 20px;
            }
            .product-sizes table {
                width: 100%;
            }
            .product-sizes th, .product-sizes td {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5 mb-5">
            <h1 class="text-center">Product Details</h1>
            <h2 style="color: coral">${requestScope.message}</h2>
            <a href="showProduct" class="btn btn-primary btn-outline-info">Back to list</a>
            <div class="row product-details">
                <div class="col-md-6">
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <p><strong>Category:</strong> ${cate.name}</p>
                    <p><strong>Price:</strong> $${product.price}</p>
                    <a href="editProductInfo?id=${product.id}" class="btn btn-block btn-primary">Edit</a>
                </div>
                <div class="col-md-6 product-images">
                    <h3>Images</h3>
                    <c:forEach var="image" items="${product.images}">
                        <img src="${image}" alt="Product Image" class="img-thumbnail">
                    </c:forEach>
                    <div class="row container">
                        <a href="addImage?id=${product.id}" class="btn btn-block btn-primary">Add</a>
                        <a href="deleteImage?id=${product.id}" class="btn btn-block btn-primary btn-danger">Delete</a>
                    </div>

                </div>
            </div>

            <!-- Modal Add Size -->
            <div class="modal fade" id="addSizeModal" tabindex="-1" role="dialog" aria-labelledby="addSizeModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addSizeModalLabel">Add Size</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <form action="addSize" method="POST">
                            <input type="hidden" name="productId" value="${product.id}">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="size">Size:</label>
                                    <input type="number" class="form-control" id="size" name="size" required>
                                </div>
                                <div class="form-group">
                                    <label for="stock">Stock:</label>
                                    <input type="number" class="form-control" id="stock" name="stock" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal Edit Size -->
            <div class="modal fade" id="editSizeModal" tabindex="-1" role="dialog" aria-labelledby="editSizeModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editSizeModalLabel">Edit Size</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <form action="editSize" method="POST">
                            <input type="hidden" name="productId" value="${product.id}">
                            <div class="modal-body"> 
                                <div class="form-group">
                                    <label for="size">Size:</label>
                                    <input type="number" class="form-control" id="editSize" name="size" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="stock">Stock:</label>
                                    <input type="number" class="form-control" id="editStock" name="stock" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal Delete Size -->
            <div class="modal fade" id="deleteSizeModal" tabindex="-1" role="dialog" aria-labelledby="deleteSizeModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteSizeModalLabel">Delete Size</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <form action="deleteSize" method="POST">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="size" id="deleteSize" >
                            <div class="modal-body">
                                Are you sure to delete this Size?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row product-sizes">
                <div class="col-md-12">
                    <h3>Sizes</h3>
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Size</th>
                                <th>Stock</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="size" items="${product.sizes}">
                                <tr>
                                    <td>${size.size}</td>
                                    <td>${size.stock}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editSizeModal" 
                                                onclick="editSize( ${size.size}, ${size.stock})">
                                            Edit
                                        </button>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteSizeModal"
                                                onclick="deleteSize( ${size.size})">
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="#" class="btn btn-block btn-primary" data-toggle="modal" data-target="#addSizeModal">Add Size</a>
                </div>
            </div>
        </div>


    </div>
    <script>
        function editSize( size, stock) {
            document.getElementById('editSize').value = size;
            document.getElementById('editStock').value = stock;
        }
        
        function deleteSize( size) {
            document.getElementById('deleteSize').value = size;
        }
    </script>
    <script src="js/jquery-3.2.1.min.js"></script> 
    <script src="js/popper.min.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
</body>
</html>