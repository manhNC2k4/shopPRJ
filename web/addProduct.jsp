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
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <script src="filepond/js/filepond.js"></script>
    </head>
    <body>

        <div class="container mt-5">
            <h1 class="mb-4">Product Upload</h1>
            <h2 style="color: coral">${requestScope.message}</h2>
            <form action="uploadProduct" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Product Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea class="form-control" id="productDescription" name="description" required></textarea>
                </div>

                <div class="form-group">
                    <label for="categoryId">Category ID:</label>
                    <select name="categoryId" id="categoryId" class="form-control">
                        <c:forEach items="${requestScope.listCat}" var="c">
                            <option value="${c.category_id}" <c:if test="${c.category_id == param.categoryId}">selected</c:if>>
                                ${c.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                </div>

                <div id="sizes">
                    <div class="form-group">
                        <label for="size">Size:</label>
                        <input type="number" class="form-control" id="size" name="size[]" required>
                    </div>

                    <div class="form-group">
                        <label for="stock">Stock:</label>
                        <input type="number" class="form-control" id="stock" name="stock[]" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="productImage">Product Image:</label>
                    <input type="file" class="filepond" id="productImage" name="productImage" accept="image/*" required>
                </div>

                <button type="submit" class="btn btn-primary">Upload Product</button>
            </form>
        </div>
        <script>
            ClassicEditor.create(document.querySelector('#productDescription')).catch(error => {
                console.error(error);
            });

            FilePond.create(document.querySelector('.filepond'));

            function addSize() {
                var sizesDiv = document.getElementById('sizes');
                var newSizeDiv = document.createElement('div');
                newSizeDiv.classList.add('form-group');
                newSizeDiv.innerHTML = `
                    <label for="size">Size:</label>
                    <input type="number" class="form-control" id="size" name="size[]" required>
                    <label for="stock">Stock:</label>
                    <input type="number" class="form-control" id="stock" name="stock[]" required>
                `;
                sizesDiv.appendChild(newSizeDiv);
            }
        </script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
