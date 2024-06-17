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
                max-width: 100%;
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
        <div class="container mt-5">
            <h1 class="text-center">Product Details</h1>
            <div class="row product-details">
                <div class="col-md-6">
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <p><strong>Category:</strong> ${product.categoryId}</p>
                    <p><strong>Price:</strong> $${product.price}</p>
                    <p><strong>Created At:</strong> ${product.createdAt}</p>
                    <p><strong>Updated At:</strong> ${product.updatedAt}</p>
                </div>
                <div class="col-md-6 product-images">
                    <h3>Images</h3>
                    <c:forEach var="image" items="${product.images}">
                        <img src="${image}" alt="Product Image" class="img-thumbnail">
                    </c:forEach>
                </div>
            </div>
            <div class="row product-sizes">
                <div class="col-md-12">
                    <h3>Sizes</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Size</th>
                                <th>Stock</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="size" items="${product.sizes}">
                                <tr>
                                    <td>${size.size}</td>
                                    <td>${size.stock}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
     <script src="js/bootstrap.min.js"></script> 
    </body>
</html>