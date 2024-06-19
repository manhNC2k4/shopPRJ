<%-- 
    Document   : deleteProductImage
    Created on : Jun 19, 2024, 3:30:12 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Image</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>

    </head>
    <body>
        <div class="container mt-5 mb-5">
            <h2 style="color: coral">${requestScope.message}</h2>
            <form action="deleteImage" method="post">
                <div class="form-group">
                    <label for="id">Product ID:</label>
                    <input type="text" class="form-control" id="id" name="id" value="${requestScope.id}" readonly>
                </div>
                <div class="form-group">
                    <label for="productImages">Product Image:</label>
                    <div class="row">
                        <c:forEach var="image" items="${images}" varStatus="status">
                            <div class="col-md-3 mb-3">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="image-${status.index}" name="imageIds" value="${image.image_id}">
                                    <label class="form-check-label" for="image-${status.index}">
                                        <img src="${image.image_url}" alt="Product Image" class="img-thumbnail" style="cursor: pointer;">
                                    </label>
                                </div>
                            </div>
                            <c:if test="${(status.index + 1) % 4 == 0}">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Delete Image</button>
            </form>
            <div class="col text-right">
                <a href="updateProduct?id=${requestScope.id}" class="btn btn-primary">Back</a>                    
            </div>
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
