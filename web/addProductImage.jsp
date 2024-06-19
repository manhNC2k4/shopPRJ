<%-- 
    Document   : addProductImage
    Created on : Jun 19, 2024, 12:41:36 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Product Image</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
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
            #image-preview img{
                max-width: 100px;
                max-height:  100px;
                margin: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5 mb-5">
            <h2 style="color: coral">${requestScope.message}</h2>
            <form action="addImage" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="id">Product ID:</label>
                    <input type="text" class="form-control" id="id" name="id" value="${requestScope.id}" readonly>
                </div>
                <div class="form-group">
                    <label for="productImages">Product Image:</label>
                    <input type="file" onchange="previewImages()" id="productImages" name="productImages" accept="image/*" multiple required>
                </div>
                <div id="image-preview"></div>
                <button type="submit" class="btn btn-primary">Upload Product Image</button>
            </form>
            <div class="col text-right">
                <a href="updateProduct?id=${requestScope.id}" class="btn btn-primary">Back</a>                    
            </div>
        </div>
        <script src="js/bootstrap.min.js"></script>
        <script>
                        function previewImages() {
                            var preview = document.getElementById('image-preview');
                            preview.innerHTML = '';
                            var files = document.getElementById('productImages').files;

                            for (var i = 0; i < files.length; i++) {
                                var file = files[i];
                                var reader = new FileReader();

                                reader.onload = function (e) {
                                    var img = new Image();
                                    img.src = e.target.result;
                                    preview.appendChild(img);
                                }

                                reader.readAsDataURL(file);
                            }
                        }
        </script>

    </body>
</html>
