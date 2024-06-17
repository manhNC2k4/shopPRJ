<%-- 
    Document   : register
    Created on : Jun 1, 2024, 4:30:13 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <style>
            
            button{
                background-color: black !important;
                border-color: black !important;
            }
            .signinhere{
                color: #c2a942;
            }
        </style>
    </head>
    <body>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header text-center">
                            <h4>Add User</h4>
                        </div>
                        <div class="card-body">
                            <%
                                String error = (String) request.getAttribute("error");
                                if (error != null) {
                            %>
                            <div class="alert alert-danger" role="alert">
                                <%= error %>
                            </div>
                            <%
                                }
                            %>
                            <form action="addUser" method="post">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="firstName" class="form-label">First Name</label>
                                        <input type="text" class="form-control" id="firstName" name="first_name" 
                                               value="<%= request.getParameter("first_name") != null ? request.getParameter("first_name") : "" %>" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="lastName" class="form-label">Last Name</label>
                                        <input type="text" class="form-control" id="lastName" name="last_name" 
                                               value="<%= request.getParameter("last_name") != null ? request.getParameter("last_name") : "" %>" required>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email address</label>
                                    <input type="email" class="form-control" name="email" id="email"
                                           value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" required>
                                </div>
                                <div class="mb-3">
                                    <label for="username" class="form-label">Username</label>
                                    <input type="text" class="form-control" name="username" id="username" 
                                           value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" class="form-control"  name="password" id="password" required>
                                </div> 
                                <div class="mb-3">
                                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
                                    <div id="error-message" class="text-danger mt-2"></div> 
                                </div>
                                <div class="mb-3">
                                    <label for="phone" class="form-label">Phone</label>
                                    <input type="tel" class="form-control"  name="phone" id="phone"
                                           value="<%= request.getParameter("phone") != null ? request.getParameter("phone") : "" %>" required>
                                </div>                               
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" class="form-control"  name="address" id="address"
                                           value="<%= request.getParameter("address") != null ? request.getParameter("address") : "" %>" required>
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Add user</button>
                            </form>
                            <div class="mt-3 text-center">
                                <p>Back to user list <a class="signinhere" href="listUser">Click here</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.min.js"></script>
        <script>
            document.getElementById('confirmPassword').addEventListener('input', function () {
                var password = document.getElementById('password').value;
                var confirmPassword = document.getElementById('confirmPassword').value;
                var errorMessage = document.getElementById('error-message');

                if (password !== confirmPassword) {
                    errorMessage.textContent = 'Passwords do not match. Please try again.';
                } else {
                    errorMessage.textContent = ''; // Xóa thông báo lỗi nếu mật khẩu khớp
                }
            });
        </script>
    </body>
</html>
