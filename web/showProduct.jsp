<%-- 
    Document   : showProduct
    Created on : Jun 16, 2024, 5:03:46 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="container-fluid mt-5">
            <h1>List of products!</h1>
            <div class="mb-4">
                <form action="showProduct" method="get" class="form-inline">
                    <div class="form-group mr-2">
                        <label for="cid" class="mr-2">Category:</label>
                        <select name="cid" id="cid" class="form-control">
                            <c:forEach items="${requestScope.dataCat}" var="c">
                                <option value="${c.category_id}" <c:if test="${c.category_id == param.cid}">selected</c:if>>
                                    ${c.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Filter</button>
                </form>
            </div>
            </br>  
            <div class="mb-4">
                <a href="uploadProduct" class="btn btn-success">Add Product</a>
            </div>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category ID</th>
                        <th>Price</th>
                        <th>Created At</th>
                        <th>Image</th>
                        <th>Updated At</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${requestScope.dataPro}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.categoryId}</td>
                            <td>${p.price}</td>
                            <td>${p.createdAt}</td>
                            <td><img src="${p.image}" alt="${p.name}" class="img-thumbnail" style="max-width: 100px;"></td>
                            <td>${p.updatedAt}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="updateProduct?id=${p.id}" class="btn btn-primary btn-sm" onclick="return checkAdmin()">Update</a>
                                    <a href="deleteProduct?id=${p.id}" class="btn btn-danger btn-sm" onclick="return checkAdmin() && confirmDelete()">Delete</a>
                                </div>
                            </td>
                        </tr>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
            function checkAdmin() {
                var isAdmin = <%= session.getAttribute("admin") != null %>;
        //                if (!isAdmin) {
        //                    alert('Bạn không có quyền thực hiện hành động này.');
        //                    return false;
        //                }
                return true;
            }
            function confirmDelete() {
                return confirm('Are you sure you want to delete this user?');
            }
        </script>
        <script src="js/bootstrap.min.js"></script> 
    </body>
</html>
