<%-- 
    Document   : register
    Created on : Jun 1, 2024, 4:30:13 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <style>
            body{
                background-color:  #dbcc8f ;
            }
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
        <c:set var="c" value="${sessionScope.account}"/>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header text-center">
<!--                            <h4>Profile</h4>-->
                            <img src="images/user.png" width="100px" height="100px" alt="Profile"/>
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
                            <form action="changeProfile" method="post">
                                <div class="mb-3">
                                    <label for="username" class="form-label">Username</label>
                                    <input type="text" class="form-control" name="username" id="username" 
                                           value="${c.username}">
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="firstName" class="form-label">First Name</label>
                                        <input type="text" class="form-control" id="firstName" name="first_name" 
                                               value="${c.first_name}">
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="lastName" class="form-label">Last Name</label>
                                        <input type="text" class="form-control" id="lastName" name="last_name" 
                                               value="${c.last_name}">
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email address</label>
                                    <input type="email" class="form-control" name="email" id="email"
                                           value="${c.email}">
                                </div>


                                <div class="mb-3">
                                    <label for="phone" class="form-label">Phone</label>
                                    <input type="tel" class="form-control"  name="phone" id="phone"
                                           value="${c.phone}">
                                </div>                               
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" class="form-control"  name="address" id="address"
                                           value="${c.address}">
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <a href="viewProfile" class="btn btn-primary w-100" >Cancel</a>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                         <button type="submit" class="btn btn-primary w-100">Update</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.min.js"></script>
        <script>

        </script>
    </body>
</html>
