<%-- 
    Document   : AddProduct
    Created on : Jun 16, 2024, 12:39:10 AM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <script src="ckeditor/ckeditor.js"></script>
        <link href="filepond/css/filepond.css" rel="stylesheet">
        <link
            href="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <script src="filepond/js/filepond.js"></script>
        <style>
            .form-group-inline {
                display: flex;
                flex-wrap: wrap;
            }
            .form-group-inline .form-group {
                flex: 1 1 10%;
                min-width: 100px;
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="filepond/js/filepond.js"></script>
        <div class="container mt-5 mb-5">
            <h1 class="mb-4">Product Upload</h1>
            <h2 style="color: coral">${requestScope.message}</h2>
            <c:set var="p" value="${requestScope.product}"/>
            <form action="editProductInfo" method="post">
                <div class="form-group">
                    <label for="id">Product Id</label>
                    <input type="text" class="form-control" id="id" name="id" value="${p.id}" readonly>
                </div>
                <div class="form-group">
                    <label for="name">Product Name:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${p.name}">
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea style="height: 150px" class="form-control" id="productDescription" name="description">${p.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="categoryId">Category ID:</label>
                    <select name="categoryId" id="categoryId" class="form-control">
                        <c:forEach items="${requestScope.listCat}" var="c">
                            <option value="${c.category_id}" <c:if test="${c.category_id == p.categoryId}">selected</c:if>>
                                ${c.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" value="${p.price}">
                </div>              
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Update Product</button>
                    </div>
                    <div class="col text-right">
                        <a href="updateProduct?id=${p.id}" class="btn btn-primary">Back</a>                    
                    </div>
                </div>
            </form>
        </div>

        <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="filepond/js/filepond.js"></script>
    </body>
</html>
