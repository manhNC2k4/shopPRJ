<%-- 
    Document   : admin
    Created on : Jun 4, 2024, 8:18:19 AM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard - MayJa Shop</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">MayJa Shop Admin</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Users</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container mt-5">
            <h2>Welcome to MayJa Shop Admin Dashboard</h2>
            <div class="row mt-4">
                <!-- Product Management -->
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            Product Management
                        </div>
                        <div class="card-body">
                            <p class="card-text">Manage your shoe products here.</p>
                            <a href="showProduct" class="btn btn-primary">Go to Products</a>
                        </div>
                    </div>
                </div>
                <!-- User Management -->
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            User Management
                        </div>
                        <div class="card-body">
                            <p class="card-text">Manage your shop users here.</p>
                            <a href="listUser" class="btn btn-primary">Go to Users</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
