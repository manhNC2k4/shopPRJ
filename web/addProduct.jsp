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

                <div id="sizes" class="form-group-inline">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="size">Size:</label>
                            <input type="number" class="form-control" id="size" name="size[]" required>
                        </div>
                        <div class="form-group">
                            <label for="stock">Stock:</label>
                            <input type="number" class="form-control" id="stock" name="stock[]" required>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-secondary" onclick="addSize()">Add Another Size</button><br><br>
                <div class="form-group">
                    <label for="productImage">Product Image:</label>
                    <input type="file" class="filepond" id="productImages" name="productImages" accept="image/*" multiple required>                
                </div>    
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Upload Product</button>
                    </div>
                    <div class="col text-right">
                        <button class="btn btn-primary btn-link" onclick="window.location.href = 'showProduct'">Back</button>
                    </div>
                </div>
            </form>
        </div>
        <script>
//            // Register the plugin
//            FilePond.registerPlugin(FilePondPluginImagePreview);
            ClassicEditor.create(document.querySelector('#productDescription')).catch(error => {
                console.error(error);
            });
//            
//            FilePond.parse(inputEl);
//
//            FilePond.create(document.querySelector('.filepond'), {
//                allowMultiple: true,
//                // Cho phép chọn nhiều tệp tin
//                allowImagePreview: true,
//                required: true, // Yêu cầu chọn ít nhất một file
//                fileValidateSize: {// Cấu hình validate kích thước
//                    minFileSize: 1, // Kích thước tối thiểu (1 byte)
//                    maxFileSize: 5 * 1024 * 1024 // Kích thước tối đa (5MB)
//                }
//            });
            
            function addSize() {
                var sizesDiv = document.getElementById('sizes');
                var newSizeDiv = document.createElement('div');
                newSizeDiv.classList.add('form-group');
                newSizeDiv.innerHTML = `
                    <div class="form-group">
                        <label for="size">Size:</label>
                        <input type="number" class="form-control" id="size" name="size[]" required>
                    </div>
                <div class="form-group">
                    <label for="stock">Stock:</label>
                        <input type="number" class="form-control" id="stock" name="stock[]" required>
           </div>
                `;
                sizesDiv.appendChild(newSizeDiv);
                if (sizeInputs.length >= 20) {
                    var newRowDiv = document.createElement('div');
                    newRowDiv.classList.add('form-group-inline');
                    sizesDiv.appendChild(newRowDiv);
                    sizesDiv = newRowDiv;
                }
            }
        </script>
        <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="filepond/js/filepond.js"></script>
    </body>
</html>
