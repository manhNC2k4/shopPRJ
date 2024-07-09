<%-- 
    Document   : dashboard
    Created on : Jul 6, 2024, 3:53:28 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="img/logo/logo.png" rel="icon">
        <title>MayJaShop - Dashboard</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/ruang-admin.min.css" rel="stylesheet">
    </head>

    <body id="page-top">
        <div id="wrapper">
            <ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashBoard">
                    <div class="sidebar-brand-icon">
                        <img src="img/logo/logo2.png">
                    </div>
                    <div class="sidebar-brand-text mx-3">MayJaShop</div>
                </a>
                <hr class="sidebar-divider my-0">
                <li class="nav-item active">
                    <a class="nav-link" href="dashBoard">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>
                <hr class="sidebar-divider">
                <div class="sidebar-heading">
                    <!--        Features-->
                </div>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProduct" aria-expanded="true"
                       aria-controls="collapseProduct">
                        <i class="fab fa-fw fa-wpforms"></i>
                        <span>Products</span>
                    </a>
                    <div id="collapseProduct" class="collapse" aria-labelledby="headingProduct" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Products</h6>
                            <a class="collapse-item" href="showProduct">List Products</a>
                            <a class="collapse-item" href="uploadProduct">Add Products</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser" aria-expanded="true"
                       aria-controls="collapseUser">
                        <i class="fab fa-fw fa-wpforms"></i>
                        <span>User</span>
                    </a>
                    <div id="collapseUser" class="collapse" aria-labelledby="headingUser" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Users</h6>
                            <a class="collapse-item" href="listUser">List Users</a>
                            <a class="collapse-item" href="addUser">Add Users</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCate" aria-expanded="true"
                       aria-controls="collapseCate">
                        <i class="fab fa-fw fa-wpforms"></i>
                        <span>Category</span>
                    </a>
                    <div id="collapseCate" class="collapse" aria-labelledby="headingCate" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Category</h6>
                            <a class="collapse-item" href="listCategory">List Category</a>
                            <a class="collapse-item" href="addCategory">Add Category</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSale" aria-expanded="true"
                       aria-controls="collapseSale">
                        <i class="fab fa-fw fa-wpforms"></i>
                        <span>Sale</span>
                    </a>
                    <div id="collapseSale" class="collapse" aria-labelledby="headingSale" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Sales</h6>
                            <a class="collapse-item" href="listSale">List Sales</a>
                            <a class="collapse-item" href="addSale">Add Sales</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrder" aria-expanded="true"
                       aria-controls="collapseOrder">
                        <i class="fab fa-fw fa-wpforms"></i>
                        <span>Orders</span>
                    </a>
                    <div id="collapseOrder" class="collapse" aria-labelledby="headingSale" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Orders</h6>
                            <a class="collapse-item" href="listOrder">List Orders</a>
                            <a class="collapse-item" href="listOrderPending">List Orders Pending</a>
                            <a class="collapse-item" href="listOrderProcessing">List Orders Processing</a>
                            <a class="collapse-item" href="listOrderShipping">List Orders Shipping</a>
                            <a class="collapse-item" href="listOrderDelivered">List Orders Delivered</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBootstrap"
                       aria-expanded="true" aria-controls="collapseBootstrap">
                        <i class="far fa-fw fa-window-maximize"></i>
                        <span>Bootstrap UI</span>
                    </a>
                    <div id="collapseBootstrap" class="collapse" aria-labelledby="headingBootstrap" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Bootstrap UI</h6>
                            <a class="collapse-item" href="alerts.html">Alerts</a>
                            <a class="collapse-item" href="buttons.html">Buttons</a>
                            <a class="collapse-item" href="dropdowns.html">Dropdowns</a>
                            <a class="collapse-item" href="modals.html">Modals</a>
                            <a class="collapse-item" href="popovers.html">Popovers</a>
                            <a class="collapse-item" href="progress-bar.html">Progress Bars</a>
                        </div>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseForm" aria-expanded="true"
                       aria-controls="collapseForm">
                        <i class="fab fa-fw fa-wpforms"></i>
                        <span>Forms</span>
                    </a>
                    <div id="collapseForm" class="collapse" aria-labelledby="headingForm" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Forms</h6>
                            <a class="collapse-item" href="form_basics.html">Form Basics</a>
                            <a class="collapse-item" href="form_advanceds.html">Form Advanceds</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTable" aria-expanded="true"
                       aria-controls="collapseTable">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Tables</span>
                    </a>
                    <div id="collapseTable" class="collapse" aria-labelledby="headingTable" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Tables</h6>
                            <a class="collapse-item" href="simple-tables.html">Simple Tables</a>
                            <a class="collapse-item" href="datatables.html">DataTables</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ui-colors.html">
                        <i class="fas fa-fw fa-palette"></i>
                        <span>UI Colors</span>
                    </a>
                </li>
                <hr class="sidebar-divider">
                <div class="sidebar-heading">
                    <!--        Examples-->
                </div>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePage" aria-expanded="true"
                       aria-controls="collapsePage">
                        <i class="fas fa-fw fa-columns"></i>
                        <span>Pages</span>
                    </a>
                    <div id="collapsePage" class="collapse" aria-labelledby="headingPage" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Example Pages</h6>
                            <a class="collapse-item" href="login.html">Login</a>
                            <a class="collapse-item" href="register.html">Register</a>
                            <a class="collapse-item" href="404.html">404 Page</a>
                            <a class="collapse-item" href="blank.html">Blank Page</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="charts.html">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Charts</span>
                    </a>
                </li>
                <hr class="sidebar-divider">
                <div class="version" id="version-ruangadmin"></div>
            </ul>
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <nav class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
                        <button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                     aria-labelledby="searchDropdown">
                                    <form class="navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-1 small" placeholder="What do you want to look for?"
                                                   aria-label="Search" aria-describedby="basic-addon2" style="border-color: #3f51b5;">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-bell fa-fw"></i>
                                    <span class="badge badge-danger badge-counter">3+</span>
                                </a>
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="alertsDropdown">
                                    <h6 class="dropdown-header">
                                        <!--                  Alerts Center-->
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-primary">
                                                <i class="fas fa-file-alt text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 12, 2019</div>
                                            <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-success">
                                                <i class="fas fa-donate text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 7, 2019</div>
                                            $290.29 has been deposited into your account!
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-warning">
                                                <i class="fas fa-exclamation-triangle text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 2, 2019</div>
                                            Spending Alert: We've noticed unusually high spending for your account.
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-envelope fa-fw"></i>
                                    <span class="badge badge-warning badge-counter">2</span>
                                </a>
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="messagesDropdown">
                                    <h6 class="dropdown-header">
                                        <!--                  Message Center-->
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="img/man.png" style="max-width: 60px" alt="">
                                            <div class="status-indicator bg-success"></div>
                                        </div>
                                        <div class="font-weight-bold">
                                            <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been
                                                having.</div>
                                            <div class="small text-gray-500">Udin Cilok · 58m</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="img/girl.png" style="max-width: 60px" alt="">
                                            <div class="status-indicator bg-default"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">Am I a good boy? The reason I ask is because someone told me that people
                                                say this to all dogs, even if they aren't good...</div>
                                            <div class="small text-gray-500">Jaenab · 2w</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-tasks fa-fw"></i>
                                    <span class="badge badge-success badge-counter">3</span>
                                </a>
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="messagesDropdown">
                                    <h6 class="dropdown-header">
                                        Task
                                    </h6>
                                    <a class="dropdown-item align-items-center" href="#">
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Design Button
                                                <div class="small float-right"><b>50%</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: 50%" aria-valuenow="50"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item align-items-center" href="#">
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Make Beautiful Transitions
                                                <div class="small float-right"><b>30%</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-warning" role="progressbar" style="width: 30%" aria-valuenow="30"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item align-items-center" href="#">
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Create Pie Chart
                                                <div class="small float-right"><b>75%</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-danger" role="progressbar" style="width: 75%" aria-valuenow="75"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">View All Taks</a>
                                </div>
                            </li>
                            <div class="topbar-divider d-none d-sm-block"></div>
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <img class="img-profile rounded-circle" src="img/boy.png" style="max-width: 60px">
                                    <span class="ml-2 d-none d-lg-inline text-white small">Maman Ketoprak</span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Settings
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Activity Log
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <!--         Topbar 
                    
                             Container Fluid-->
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="./">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                            </ol>
                        </div>

                        <div class="row mb-3">
                            <!--             Earnings (Monthly) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Earnings (Monthly)</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">$${monthlyRevenue.getOrDefault(currentMonth, 0.0)} </div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                    <c:set var="percentageChange" value="${(monthlyRevenue.getOrDefault(currentMonth, 0.0) - monthlyRevenue.getOrDefault(previousMonth, 0.0)) / monthlyRevenue.getOrDefault(previousMonth, 1.0) * 100}" />
                                                    <span class="${percentageChange >= 0 ? 'text-success' : 'text-danger'} mr-2">
                                                        <i class="fa ${percentageChange >= 0 ? 'fa-arrow-up' : 'fa-arrow-down'}"></i>  ${percentageChange} %
                                                    </span>
                                                    <span>Since last month</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-calendar fa-2x text-primary"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--             Earnings (Annual) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Sales</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${monthlySale.getOrDefault(currentMonth, 0.0)}</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                    <c:set var="percentageChange" value="${(monthlySale.getOrDefault(currentMonth, 0.0) - monthlySale.getOrDefault(previousMonth, 0.0)) / monthlySale.getOrDefault(previousMonth, 1.0) * 100}" />
                                                    <span class="${percentageChange >= 0 ? 'text-success' : 'text-danger'} mr-2">
                                                        <i class="fa ${percentageChange >= 0 ? 'fa-arrow-up' : 'fa-arrow-down'}"></i>  ${percentageChange} %
                                                    </span>
                                                    <span>Since last month</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-shopping-cart fa-2x text-success"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--             New User Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">New User</div>
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${monthlyUserNew.getOrDefault(currentMonth, 0.0)}</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                    <c:set var="percentageChange" value="${(monthlyUserNew.getOrDefault(currentMonth, 0.0) - monthlyUserNew.getOrDefault(previousMonth, 0.0)) / monthlyUserNew.getOrDefault(previousMonth, 1.0) * 100}" />
                                                    <span class="${percentageChange >= 0 ? 'text-success' : 'text-danger'} mr-2">
                                                        <i class="fa ${percentageChange >= 0 ? 'fa-arrow-up' : 'fa-arrow-down'}"></i>  ${percentageChange} %
                                                    </span>
                                                    <span>Since last month</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-users fa-2x text-info"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--             Pending Requests Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Pending Requests</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${countPending}</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-comments fa-2x text-warning"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--
                                         Area Chart -->
                            <div class="col-xl-8 col-lg-7">
                                <div class="card mb-4">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Monthly Recap Report</h6>
                                        <div class="dropdown no-arrow">
                                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false">
                                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                 aria-labelledby="dropdownMenuLink">
                                                <div class="dropdown-header">Dropdown Header:</div>
                                                <a class="dropdown-item" href="#">Action</a>
                                                <a class="dropdown-item" href="#">Another action</a>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="#">Something else here</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="chart-area">
                                            <canvas id="myAreaChart"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--             Pie Chart -->
                            <div class="col-xl-4 col-lg-5">
                                <div class="card mb-4">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Products Sold</h6>
                                        <div class="dropdown no-arrow">
                                            <a class="dropdown-toggle btn btn-primary btn-sm" href="#" role="button" id="dropdownMenuLink"
                                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                Month <i class="fas fa-chevron-down"></i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                 aria-labelledby="dropdownMenuLink">
                                                <div class="dropdown-header">Select Periode</div>
                                                <a class="dropdown-item" href="#">Today</a>
                                                <a class="dropdown-item" href="#">Week</a>
                                                <a class="dropdown-item active" href="#">Month</a>
                                                <a class="dropdown-item" href="#">This Year</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <c:forEach var="c" items="${listProduct}">
                                            <div class="mb-3">
                                                <div class="small text-gray-500">${c.name}
                                                    <div class="small float-right"><b>${c.sold} of ${c.sold + c.stock} Items</b></div>
                                                </div>
                                                <div class="progress" style="height: 12px;">
                                                    <c:choose>
                                                        <c:when test="${c.sold / (c.sold + c.stock) < 0.01}">
                                                            <div class="progress-bar bg-warning" role="progressbar" style="width: 1%" 
                                                                 aria-valuenow="${c.sold}" aria-valuemin="0" aria-valuemax="${c.sold + c.stock}"></div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="progress-bar bg-warning" role="progressbar" style="width: ${c.sold / (c.sold + c.stock) * 100}%" 
                                                                 aria-valuenow="${c.sold}" aria-valuemin="0" aria-valuemax="${c.sold + c.stock}"></div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="card-footer text-center">
                                        <a class="m-0 small text-primary card-link" href="#">View More <i
                                                class="fas fa-chevron-right"></i></a>
                                    </div>
                                </div>
                            </div>
                            <!--             Invoice Example -->
                            <div class="col-xl-8 col-lg-7 mb-4">
                                <div class="card">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Invoice</h6>
                                        <a class="m-0 float-right btn btn-danger btn-sm" href="listOrder">View More <i
                                                class="fas fa-chevron-right"></i></a>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>User Id</th>
                                                    <th>Order Time</th>
                                                    <th>Total</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="j" items="${requestScope.listOrder}" varStatus="loop">
                                                    <c:if test="${loop.index < 5}">
                                                        <tr>
                                                            <td>${j.orderId}</td>
                                                            <td>${j.userId}</td>
                                                            <td>
                                                                ${j.orderDate}
                                                            </td>
                                                            <td>${j.total}</td>
                                                            <td>
                                                                <span 
                                                                    <c:choose>
                                                                        <c:when test="${j.status == 'Delivered'}">class="badge badge-success"</c:when>
                                                                        <c:when test="${j.status == 'Pending'}">class="badge badge-danger"</c:when>
                                                                        <c:when test="${j.status == 'Shipping'}">class="badge badge-warning"</c:when>
                                                                        <c:when test="${j.status == 'Processing'}">class="badge badge-info"</c:when>
                                                                        <c:otherwise>class="badge badge-secondary"</c:otherwise> 
                                                                    </c:choose>
                                                                    >
                                                                    ${j.status}
                                                                </span>
                                                            </td>
                                                            <td><a href="detailOrder?id=${j.orderId}" class="btn btn-sm btn-info">Detail</a></td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="card-footer"></div>
                                </div>
                            </div>
                            <!--             Message From Customer-->
                            <div class="col-xl-4 col-lg-5 ">
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Category Revenue</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="chart-pie pt-4">
                                            <canvas id="myPieChart"></canvas>
                                        </div>
                                        <hr>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--          Row-->

                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <p>Do you like this template ? you can download from <a href="https://github.com/indrijunanda/RuangAdmin"
                                                                                    class="btn btn-primary btn-sm" target="_blank"><i class="fab fa-fw fa-github"></i>&nbsp;GitHub</a></p>
                        </div>
                    </div>

                    <!--           Modal Logout -->
                    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelLogout"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabelLogout">Ohh No!</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Are you sure you want to logout?</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                    <a href="logout" class="btn btn-primary">Logout</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!--        -Container Fluid-->
            </div>

        </div>
    </div>

    <!--   Scroll to top -->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="js/ruang-admin.min.js"></script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script>
        let namePercentageData = {
        <c:forEach var="entry" items="${namePercentageMap}" varStatus="loop">
        "${entry.key}": ${entry.value} ${!loop.last ? ',' : ''}
        </c:forEach>
        };
        let labels1 = Object.keys(namePercentageData);
        let percentages1 = Object.values(namePercentageData);
        Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
        Chart.defaults.global.defaultFontColor = '#858796';
// Pie Chart Example
        var ctx = document.getElementById("myPieChart");
        var myPieChart = new Chart(ctx, {
        type: 'doughnut',
                data: {
                labels: labels1,
                        datasets: [{
                        data: percentages1,
                                backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                                hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                                hoverBorderColor: "rgba(234, 236, 244, 1)",
                        }],
                },
                options: {
                maintainAspectRatio: false,
                        tooltips: {
                        backgroundColor: "rgb(255,255,255)",
                                bodyFontColor: "#858796",
                                borderColor: '#dddfeb',
                                borderWidth: 1,
                                xPadding: 15,
                                yPadding: 15,
                                displayColors: false,
                                caretPadding: 10,
                        },
                        legend: {
                        display: false
                        },
                        cutoutPercentage: 80,
                },
        });
        function number_format(number, decimals, dec_point, thousands_sep) {
        // *     example: number_format(1234.56, 2, ',', ' ');
        // *     return: '1 234,56'
        number = (number + '').replace(',', '').replace(' ', '');
        var n = !isFinite( + number) ? 0 : + number,
                prec = !isFinite( + decimals) ? 0 : Math.abs(decimals),
                sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                s = '',
                toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.round(n * k) / k;
                };
        // Fix for IE parseFloat(0.55).toFixed(0) = 0;
        s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
        if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
        }
        if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join(dec);
        }

        // Area Chart Example               

        let monthlyRevenueData = {
        <c:forEach var="entry" items="${monthlyRevenue}" varStatus="loop">
        "${entry.key}": ${entry.value} ${!loop.last ? ',' : ''}
        </c:forEach>
        };
        let labels = [];
        let data = [];
        for (let month = 1; month <= 12; month++) {
        labels.push(month); // Hoặc bạn có thể format lại tên tháng cho đẹp hơn
        data.push(monthlyRevenueData[month] || 0); // Lấy doanh thu, nếu không có thì gán là 0
        }

        var ctx = document.getElementById("myAreaChart");
        var myLineChart = new Chart(ctx, {
        type: 'line',
                data: {
                labels: labels, // Sử dụng labels đã tạo từ dữ liệu JSP
                        datasets: [{
                        label: "Earnings",
                                lineTension: 0.3,
                                backgroundColor: "rgba(78, 115, 223, 0.5)",
                                borderColor: "rgba(78, 115, 223, 1)",
                                pointRadius: 3,
                                pointBackgroundColor: "rgba(78, 115, 223, 1)",
                                pointBorderColor: "rgba(78, 115, 223, 1)",
                                pointHoverRadius: 3,
                                pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                                pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                                pointHitRadius: 10,
                                pointBorderWidth: 2,
                                data: data, // Sử dụng data đã tạo từ dữ liệu JSP
                        }],
                },
                options: {
                maintainAspectRatio: false,
                        layout: {
                        padding: {
                        left: 10,
                                right: 25,
                                top: 25,
                                bottom: 0
                        }
                        },
                        scales: {
                        xAxes: [{
                        time: {
                        unit: 'date'
                        },
                                gridLines: {
                                display: false,
                                        drawBorder: false
                                },
                                ticks: {
                                maxTicksLimit: 7
                                }
                        }],
                                yAxes: [{
                                ticks: {
                                maxTicksLimit: 5,
                                        padding: 10,
                                        // Include a dollar sign in the ticks
                                        callback: function (value, index, values) {
                                        return '$' + number_format(value);
                                        }
                                },
                                        gridLines: {
                                        color: "rgb(234, 236, 244)",
                                                zeroLineColor: "rgb(234, 236, 244)",
                                                drawBorder: false,
                                                borderDash: [2],
                                                zeroLineBorderDash: [2]
                                        }
                                }],
                        },
                        legend: {
                        display: false
                        },
                        tooltips: {
                        backgroundColor: "rgb(255,255,255)",
                                bodyFontColor: "#858796",
                                titleMarginBottom: 10,
                                titleFontColor: '#6e707e',
                                titleFontSize: 14,
                                borderColor: '#dddfeb',
                                borderWidth: 1,
                                xPadding: 15,
                                yPadding: 15,
                                displayColors: false,
                                intersect: false,
                                mode: 'index',
                                caretPadding: 10,
                                callbacks: {
                                label: function (tooltipItem, chart) {
                                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                                }
                                }
                        }
                }
        });
    </script>
    <!--    <script>
            // Set new default font family and font color to mimic Bootstrap's default styling
            Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
            Chart.defaults.global.defaultFontColor = '#858796';
            function number_format(number, decimals, dec_point, thousands_sep) {
            // *     example: number_format(1234.56, 2, ',', ' ');
            // *     return: '1 234,56'
            number = (number + '').replace(',', '').replace(' ', '');
            var n = !isFinite( + number) ? 0 : + number,
                    prec = !isFinite( + decimals) ? 0 : Math.abs(decimals),
                    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                    s = '',
                    toFixedFix = function (n, prec) {
                    var k = Math.pow(10, prec);
                    return '' + Math.round(n * k) / k;
                    };
            // Fix for IE parseFloat(0.55).toFixed(0) = 0;
            s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
            if (s[0].length > 3) {
            s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
            }
            if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
            }
            return s.join(dec);
            }
    
            // Area Chart Example               
    
            let monthlyRevenueData = {
    <c:forEach var="entry" items="${monthlyRevenue}" varStatus="loop">
    "${entry.key}": ${entry.value} ${!loop.last ? ',' : ''}
    </c:forEach>
    };
    let labels = [];
    let data = [];
    for (let month = 1; month <= 12; month++) {
    labels.push(month); // Hoặc bạn có thể format lại tên tháng cho đẹp hơn
    data.push(monthlyRevenueData[month] || 0); // Lấy doanh thu, nếu không có thì gán là 0
    }

    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
    type: 'line',
            data: {
            labels: labels, // Sử dụng labels đã tạo từ dữ liệu JSP
                    datasets: [{
                    label: "Earnings",
                            lineTension: 0.3,
                            backgroundColor: "rgba(78, 115, 223, 0.5)",
                            borderColor: "rgba(78, 115, 223, 1)",
                            pointRadius: 3,
                            pointBackgroundColor: "rgba(78, 115, 223, 1)",
                            pointBorderColor: "rgba(78, 115, 223, 1)",
                            pointHoverRadius: 3,
                            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                            pointHitRadius: 10,
                            pointBorderWidth: 2,
                            data: data, // Sử dụng data đã tạo từ dữ liệu JSP
                    }],
            },
            options: {
            maintainAspectRatio: false,
                    layout: {
                    padding: {
                    left: 10,
                            right: 25,
                            top: 25,
                            bottom: 0
                    }
                    },
                    scales: {
                    xAxes: [{
                    time: {
                    unit: 'date'
                    },
                            gridLines: {
                            display: false,
                                    drawBorder: false
                            },
                            ticks: {
                            maxTicksLimit: 7
                            }
                    }],
                            yAxes: [{
                            ticks: {
                            maxTicksLimit: 5,
                                    padding: 10,
                                    // Include a dollar sign in the ticks
                                    callback: function (value, index, values) {
                                    return '$' + number_format(value);
                                    }
                            },
                                    gridLines: {
                                    color: "rgb(234, 236, 244)",
                                            zeroLineColor: "rgb(234, 236, 244)",
                                            drawBorder: false,
                                            borderDash: [2],
                                            zeroLineBorderDash: [2]
                                    }
                            }],
                    },
                    legend: {
                    display: false
                    },
                    tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                            bodyFontColor: "#858796",
                            titleMarginBottom: 10,
                            titleFontColor: '#6e707e',
                            titleFontSize: 14,
                            borderColor: '#dddfeb',
                            borderWidth: 1,
                            xPadding: 15,
                            yPadding: 15,
                            displayColors: false,
                            intersect: false,
                            mode: 'index',
                            caretPadding: 10,
                            callbacks: {
                            label: function (tooltipItem, chart) {
                            var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                            return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                            }
                            }
                    }
            }
    });

</script>  -->
</body>

</html>
