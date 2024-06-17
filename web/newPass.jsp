<%-- 
    Document   : login
    Created on : Jun 1, 2024, 4:21:45 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update password</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <style>
            body{
                background-color:  #dbcc8f ;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            button{
                background-color: black !important;
                border-color: black !important;
            }
            .signuphere{
                color: #c2a942;
            }

        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header text-center">
                            <h4>Change Password</h4>
                        </div>
                        <div class="card-body">
                            <h4 style="color: red">${requestScope.error}</h4>
                            <form id="changePasswordForm" action="newPass" method="POST">
                                <div class="form-group">
                                    <label for="userId">User Id</label>
                                    <input type="text" class="form-control" id="userId" name="userId" value="${requestScope.userId}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="newPassword">New Password</label>
                                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                                </div>
                                <div class="form-group">
                                    <label for="confirmNewPassword">Confirm New Password</label>
                                    <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required>
                                    <div id="error-message" class="text-danger mt-2"></div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Update Password</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.getElementById('changePasswordForm').addEventListener('submit', function (event) {
                var newPassword = document.getElementById('newPassword').value;
                var confirmNewPassword = document.getElementById('confirmNewPassword').value;
                var errorMessage = document.getElementById('error-message');

                if (newPassword !== confirmNewPassword) {
                    event.preventDefault(); // Ngăn chặn form gửi đi
                    errorMessage.textContent = 'Passwords do not match. Please try again.';
                } else {
                    errorMessage.textContent = ''; // Xóa thông báo lỗi nếu mật khẩu khớp
                }
            });
        </script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
