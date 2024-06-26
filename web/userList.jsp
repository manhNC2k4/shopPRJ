<%-- 
    Document   : userList
    Created on : Jun 11, 2024, 8:45:45 AM
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
        <link rel="stylesheet" href="css/font-awesome/css/all.css">
        <link rel="stylesheet" href="css/font-awesome/css/all.min.css">
        <style>
            
            .btn-group a {
                margin-right: 5px;
            }

            .btn-group a:last-child {
                margin-right: 0;
            }
            .btn i{
                font-size: 1rem;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <h1 style="padding: 20px">User page</h1>
            <h4 style="color: red">${requestScope.error}</h4>
        </div>
        <div class="container-fluid">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="j" items="${requestScope.data}">
                        <tr>
                            <td>${j.user_id}</td>
                            <td>${j.username}</td>
                            <td>${j.email}</td>
                            <td>${j.first_name}</td>
                            <td>${j.last_name}</td>
                            <td>${j.phone}</td>
                            <td>${j.address}</td>
                            <td>${j.created_at}</td>
                            <td>${j.updated_at}</td>
                            <td class="btn-group">
                                <a href="newPass?id=${j.user_id}" class="btn btn-primary btn-sm" title="New password">
                                    <i class="fas fa-key"></i>
                                </a>
                                <a href="deleteUser?id=${j.user_id}" class="btn btn-danger btn-sm" onclick="return confirmDelete()" title="Delete">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container-fluid">
            <button class="btn btn-primary btn-link" onclick="window.location.href = 'addUser'">Add User</button>
            <button class="btn btn-primary btn-link" onclick="window.location.href = 'admin.jsp'">Go to main dashboard</button>
        </div>
        <script src="js/bootstrap.min.js"></script>
        <script>
                function confirmDelete() {
                    return confirm('Are you sure you want to delete this user?');
                }
        </script>
    </body>
</html>
