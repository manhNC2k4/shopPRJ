<%-- 
    Document   : addSale
    Created on : Jul 2, 2024, 3:47:21 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>RuangAdmin - Form Advanceds</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <!-- Select2 -->
        <link href="vendor/select2/dist/css/select2.min.css" rel="stylesheet" type="text/css">
        <!-- Bootstrap DatePicker -->  
        <link href="vendor/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet" >
        <!-- Bootstrap Touchspin -->
        <link href="vendor/bootstrap-touchspin/css/jquery.bootstrap-touchspin.css" rel="stylesheet" >
        <!-- ClockPicker -->
        <link href="vendor/clock-picker/clockpicker.css" rel="stylesheet">
        <!-- RuangAdmin CSS -->
        <link href="css/ruang-admin.min.css" rel="stylesheet">
    </head>

    <body id="page-top">
        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard.jsp">
                    <div class="sidebar-brand-icon">
                        <img src="img/logo/logo2.png">
                    </div>
                    <div class="sidebar-brand-text mx-3">RuangAdmin</div>
                </a>
                <hr class="sidebar-divider my-0">
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>
                <hr class="sidebar-divider">
                <div class="sidebar-heading">
                    Features
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
                    <a class="nav-link" href="charts.html">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Charts</span>
                    </a>
                </li>
                <hr class="sidebar-divider">
                <div class="version" id="version-ruangadmin"></div>
            </ul>
            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!-- TopBar -->
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
                                            <input type="text" class="form-control bg-light border-1 small"
                                                   placeholder="What do you want to look for?" aria-label="Search" aria-describedby="basic-addon2"
                                                   style="border-color: #3f51b5;">
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
                                        Alerts Center
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
                                        Message Center
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
                                    <a class="dropdown-item" href="javascript:void(0);" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <!-- Topbar -->

                    <!-- Container Fluid-->
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Sale</h1>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="./">Home</a></li>
                                <li class="breadcrumb-item">Forms</li>
                                <li class="breadcrumb-item active" aria-current="page">Sale</li>
                            </ol>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card mb-4">
                                    <c:set value="${requestScope.sale}" var="s"/>
                                    <form action="updateSale" method="post">
                                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                            <h6 class="m-0 font-weight-bold text-primary">Sale</h6>
                                        </div>
                                        <div class="card-body">        
                                            <input class="form-control  mb-3" type="text" name="id" value="${s.id}" readonly>
                                            <input class="form-control  mb-3" type="text" name="name" value="${s.name}" placeholder="name">
                                            <div class="form-group">
                                                <label for="select2SinglePlaceholder">Single Select Box with Placeholder</label>
                                                <select class="select2-single-placeholder form-control" name="discountType" id="select2SinglePlaceholder">
                                                    <option value="percentage" ${s.discountType == 'percentage' ? 'selected' : ''}>percentage</option>
                                                    <option value="fixed_amount" ${s.discountType == 'fixed_amount' ? 'selected' : ''}>fixed_amount</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="touchSpin1">Simpel TouchSpin</label>
                                                <input id="touchSpin1" name="discountValue" value="${s.discountValue}" type="text" class="form-control">
                                            </div>
                                            <div class="form-group" id="simple-date4">
                                                <label for="dateRangePicker">Range Select</label>
                                                <div class="input-daterange input-group">
                                                    <input type="text" class="input-sm form-control" value="<fmt:formatDate value="${s.startDate}" pattern="dd/MM/yyyy"/>" name="startDate" />
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">to</span>
                                                    </div>
                                                    <input type="text" value="<fmt:formatDate value="${s.endDate}" pattern="dd/MM/yyyy"/>" class="input-sm form-control" name="endDate" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>Status</label>
                                                <div class="custom-control custom-switch">
                                                    <input type="checkbox" class="custom-control-input" id="customSwitch1" value="active" name="status" ${s.status == 'active' ? 'checked' : ''}>
                                                    <label class="custom-control-label" for="customSwitch1">Active</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <button type="submit" class="btn btn-primary mb-1">Update</button>
                                        </div>
                                    </form>
                                </div>

                            </div>

                        </div>


                    </div>
                    <!---Container Fluid-->
                </div>
                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>copyright &copy; <script>
                                document.write(new Date().getFullYear());
                                </script> - developed by
                                <b><a href="https://indrijunanda.gitlab.io/" target="_blank">indrijunanda</a></b>
                            </span>
                        </div>
                    </div>
                </footer>
                <!-- Footer -->
            </div>
        </div>

        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <!-- Select2 -->
        <script src="vendor/select2/dist/js/select2.min.js"></script>
        <!-- Bootstrap Datepicker -->
        <script src="vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
        <!-- Bootstrap Touchspin -->
        <script src="vendor/bootstrap-touchspin/js/jquery.bootstrap-touchspin.js"></script>
        <!-- ClockPicker -->
        <script src="vendor/clock-picker/clockpicker.js"></script>
        <!-- RuangAdmin Javascript -->
        <script src="js/ruang-admin.min.js"></script>
        <!-- Javascript for this page -->
        <script>
                                $('#simple-date4 .input-daterange').datepicker({
                                    format: 'dd/mm/yyyy', // Định dạng ngày tháng
                                    autoclose: true, // Tự động đóng khi chọn ngày
                                    todayHighlight: true // Highlight ngày hiện tại
                                });
                                $(document).ready(function () {


                                    $('.select2-single').select2();

                                    // Select2 Single  with Placeholder
                                    $('.select2-single-placeholder').select2({
                                        placeholder: "Select a Province",
                                        allowClear: true
                                    });

                                    // Select2 Multiple
                                    $('.select2-multiple').select2();

                                    // Bootstrap Date Picker
                                    $('#simple-date1 .input-group.date').datepicker({
                                        format: 'dd/mm/yyyy',
                                        todayBtn: 'linked',
                                        todayHighlight: true,
                                        autoclose: true,
                                    });

                                    $('#simple-date2 .input-group.date').datepicker({
                                        startView: 1,
                                        format: 'dd/mm/yyyy',
                                        autoclose: true,
                                        todayHighlight: true,
                                        todayBtn: 'linked',
                                    });

                                    $('#simple-date3 .input-group.date').datepicker({
                                        startView: 2,
                                        format: 'dd/mm/yyyy',
                                        autoclose: true,
                                        todayHighlight: true,
                                        todayBtn: 'linked',
                                    });

                                    $('#simple-date4 .input-daterange').datepicker({
                                        format: 'dd/mm/yyyy',
                                        autoclose: true,
                                        todayHighlight: true,
                                        todayBtn: 'linked',
                                    });

                                    // TouchSpin

                                    $('#touchSpin1').TouchSpin({
                                        min: 0,
                                        max: 100,
                                        boostat: 5,
                                        maxboostedstep: 10,
                                        initval: 0
                                    });

                                    $('#touchSpin2').TouchSpin({
                                        min: 0,
                                        max: 100,
                                        decimals: 2,
                                        step: 0.1,
                                        postfix: '%',
                                        initval: 0,
                                        boostat: 5,
                                        maxboostedstep: 10
                                    });

                                    $('#touchSpin3').TouchSpin({
                                        min: 0,
                                        max: 100,
                                        initval: 0,
                                        boostat: 5,
                                        maxboostedstep: 10,
                                        verticalbuttons: true,
                                    });

                                    $('#clockPicker1').clockpicker({
                                        donetext: 'Done'
                                    });

                                    $('#clockPicker2').clockpicker({
                                        autoclose: true
                                    });

                                    let input = $('#clockPicker3').clockpicker({
                                        autoclose: true,
                                        'default': 'now',
                                        placement: 'top',
                                        align: 'left',
                                    });

                                    $('#check-minutes').click(function (e) {
                                        e.stopPropagation();
                                        input.clockpicker('show').clockpicker('toggleView', 'minutes');
                                    });

                                });
        </script>

    </body>

</html>
