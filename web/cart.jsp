<%-- 
    Document   : cart
    Created on : Jul 1, 2024, 5:28:14 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DTO.UserDTO" %>
<%@ page import="model.Cart" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%
  Cookie[] cookies = request.getCookies();
  UserDTO user = null;
  Cart c = null;
  int countFavorite = 0;
  if (cookies != null) {
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("account")) {
        user = new UserDTO(cookie.getValue()); 
      }
      if (cookie.getName().equals("cart")) {
        c = new Cart(cookie.getValue()); 
      }
      if (cookie.getName().equals("favoriteCount")) {
        countFavorite = Integer.parseInt(cookie.getValue());
      }
    }
  }
  pageContext.setAttribute("user", user);
  pageContext.setAttribute("cart", c);
  pageContext.setAttribute("countFavorite", countFavorite);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>MayJaShop - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .description {
                width: 300px; /* Adjust the width as needed */
                white-space: nowrap; /* Prevent text from wrapping to the next line */
                overflow: hidden; /* Hide the overflow text */
                text-overflow: ellipsis; /* Add the ellipsis (...) */
            }
            .search-container {
                display: none;
                align-items: center;
                position: relative;
            }
            .search-container input {
                border: none;
                border-bottom: 1px solid rgba(0, 0, 0, 1); /* 100% opacity */
                outline: none;
                padding: 5px;
                height: 35px !important;
                margin-top: 15px;
                background-color: transparent !important;
                width: 250px;
                font-size: 10px;
                padding-top: 1rem;
                padding-bottom: 1rem;
                padding-left: 20px;
                padding-right: 20px;
                font-weight: 400;
                color: #000000;
                text-transform: uppercase;
                letter-spacing: 2px;
                opacity: 1 !important;
            }
            .search-container input::placeholder {
                color: rgba(0, 0, 0, 1); /* 100% opacity */
            }
        </style>
    </head>
    <body class="goto-here">
        <div class="py-1 bg-black">
            <div class="container">
                <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                    <div class="col-lg-12 d-block">
                        <div class="row d-flex">
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                                <span class="text">+ 1235 2355 98</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                                <span class="text">youremail@email.com</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text">3-5 Business days delivery &amp; Free Returns</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="index">MayJaShop</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item search-container" id="searchContainer">
                            <form action="shop" method="GET">
                                <input type="text" name="query" class="form-control" placeholder="Search...">
                            </form>
                        </li>
                        <li class="nav-item cta cta-colored">
                            <a href="#" class="nav-link" id="searchIcon"><span class="icon-search"></span></a>
                        </li>
                        <li class="nav-item active"><a href="index" class="nav-link">Home</a></li>
                        <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>
                        <li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
                        <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
                        <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
                        <li class="nav-item cta cta-colored"><a href="favoriteShow" class="nav-link"><span class="icon-heart"></span>[${countFavorite}]</a></li>
                        <li class="nav-item cta cta-colored"><a href="cartShow" class="nav-link"><span class="icon-shopping_cart"></span>[${cart.nums_items}]</a></li>
                            <c:if test="${user == null}">
                            <li class="nav-item"><a href="login.jsp" class="nav-link">Login</a></li>
                            </c:if>
                            <c:if test="${user != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${user.username}</a>
                                <div class="dropdown-menu" aria-labelledby="dropdown04">
                                    <a class="dropdown-item" href="viewProfile">View profile</a>
                                    <a class="dropdown-item" href="viewOrder">View Order</a>
                                    <a class="dropdown-item" href="changePass">Change password</a>
                                    <a class="dropdown-item" href="logout">Log out</a>
                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_6.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="index">Home</a></span> <span>Cart</span></p>
                        <h1 class="mb-0 bread">My Wishlist</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section ftco-cart">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 ftco-animate">
                        <div class="cart-list">
                            <table class="table">
                                <thead class="thead-primary">
                                    <tr class="text-center">
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th>Product</th>
                                        <th>Size</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Select</th>
                                    </tr>
                                </thead>
                                <form id="cartForm" action="cartShow" method="post">
                                    <tbody>
                                        <c:forEach var="p" items="${sessionScope.productInfoList}" varStatus="status">
                                            <tr class="text-center">
                                                <td class="product-remove"><a href="deleteCart?cid=${p.cart_item_id}&quantity=${p.quantity}"><span class="ion-ios-close"></span></a></td>

                                                <td class="image-prod"><div class="img" style="background-image:url(${p.product.images[0]}); width: 150px;"></div></td>

                                                <td class="product-name">
                                                    <h3>
                                                        <a href="singleProduct?id=${p.product.id}" style="color: black;">
                                                            ${p.product.name}
                                                        </a>
                                                    </h3>
                                                    <p class="description">
                                                        <a href="singleProduct?id=${p.product.id}" style="color: black;">
                                                            ${p.product.description}
                                                        </a>
                                                    </p>
                                                </td>


                                                <td class="size">
                                                    <div class="form-group d-flex">
                                                        <div class="select-wrap">
                                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                                            <select name="size" id="size${status.index}" class="form-control size-select" style="padding:0 40px 0 20px">
                                                                <c:forEach var="size" items="${p.product.sizes}">
                                                                    <option value="${size.size}" data-stock="${size.stock}"
                                                                            <c:if test="${size.size == p.size.size}">
                                                                                selected
                                                                            </c:if>
                                                                            >${size.size}</option>     
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="quantity">
                                                    <div class="input-group mb-3">
                                                        <span class="input-group-btn mr-2">
                                                            <button type="button" class="quantity-left-minus btn" data-index="${status.index}" data-type="minus" data-field="">
                                                                <i class="ion-ios-remove"></i>
                                                            </button>
                                                        </span>
                                                        <input type="text" id="quantity${status.index}" name="quantity" data-price="${p.product.price}" data-index="${status.index}" class="quantity form-control input-number" value="${p.quantity}" readonly>
                                                        <span class="input-group-btn ml-2">
                                                            <button type="button" class="quantity-right-plus btn" data-index="${status.index}" data-type="plus" data-field="">
                                                                <i class="ion-ios-add"></i>
                                                            </button>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td class="total" >
                                                    <div id="total${status.index}" class="product-total" style="margin-bottom: 15px; padding: 10px; font-weight: 600;">
                                                        $${p.product.price * p.quantity}
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="item">
                                                        <input type="checkbox" class="product-checkbox" data-id="${p.cart_item_id}"  <c:forEach items="${sessionScope.listItemChecked}" var="l">
                                                                   <c:if test="${l==p.cart_item_id}">checked</c:if>
                                                               </c:forEach> > 
                                                    </div>
                                                </td>
                                            </tr><!-- END TR-->
                                        </c:forEach>
                                    </tbody>
                                    <input type="hidden" id="checkedItems" name="checkedItems">
                                </form>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate">
                        <div class="cart-total mb-3">
                            <h3>Cart Totals</h3>
                            <p class="d-flex">
                                <span>Subtotal</span>
                                <span id="subtotal">$${sessionScope.total}</span>
                            </p>
                            <!--                            <p class="d-flex">
                                                            <span>Delivery</span>
                                                            <span>$0</span>
                                                        </p>-->
                            <p class="d-flex">
                                <span>Discount</span>
                                <span id="discount">$0</span>
                            </p>
                            <hr>
                            <p class="d-flex total-price">
                                <span>Total</span>
                                <span id="total">$${sessionScope.total}</span>
                            </p>
                        </div>
                        <form action="checkout" method="POST"> <%-- Form để gửi dữ liệu --%>
                            <input type="hidden" id="orderIds" name="orderIds" value=""> 
                            <p class="text-center">
                                <button type="submit" id="checkoutButton" class="btn btn-primary py-3 px-4" disabled>
                                    Proceed to Checkout
                                </button>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </section>


        <footer class="ftco-footer ftco-section">
            <div class="container">
                <div class="row">
                    <div class="mouse">
                        <a href="#" class="mouse-icon">
                            <div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
                        </a>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">MayJaShop</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Menu</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Shop</a></li>
                                <li><a href="#" class="py-2 d-block">About</a></li>
                                <li><a href="#" class="py-2 d-block">Journal</a></li>
                                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Help</h2>
                            <div class="d-flex">
                                <ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
                                    <li><a href="#" class="py-2 d-block">Shipping Information</a></li>
                                    <li><a href="#" class="py-2 d-block">Returns &amp; Exchange</a></li>
                                    <li><a href="#" class="py-2 d-block">Terms &amp; Conditions</a></li>
                                    <li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
                                </ul>
                                <ul class="list-unstyled">
                                    <li><a href="#" class="py-2 d-block">FAQs</a></li>
                                    <li><a href="#" class="py-2 d-block">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                                    <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
                                    <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>



        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/scrollax.min.js"></script>
<!--        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>-->
        <script src="js/main.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script>
            .search-container {
                display: none;
                align-items: center;
                position: relative;
            }
            .search-container input {
                border: none;
                border-bottom: 1px solid rgba(0, 0, 0, 1); /* 100% opacity */
                outline: none;
                padding: 5px;
                height: 35px !important;
                margin-top: 15px;
                background-color: transparent !important;
                width: 250px;
                font-size: 10px;
                padding-top: 1rem;
                padding-bottom: 1rem;
                padding-left: 20px;
                padding-right: 20px;
                font-weight: 400;
                color: #000000;
                text-transform: uppercase;
                letter-spacing: 2px;
                opacity: 1 !important;
            }
            .search-container input::placeholder {
                color: rgba(0, 0, 0, 1); /* 100% opacity */
            }
            // Hàm kiểm tra có item nào được chọn hay chưa
            function checkSelectedItems() {
                const checkedItems = document.querySelectorAll('.product-checkbox:checked');
                const checkoutButton = document.getElementById('checkoutButton');
                const orderIds = document.getElementById('orderIds');

                // Tạo mảng chứa các ID sản phẩm được chọn
                let checkedItemIdsarr = [];
                checkedItems.forEach(item => {
                    checkedItemIdsarr.push(item.getAttribute('data-id'));
                });

                // Cập nhật giá trị của input hidden
                console.log(checkedItemIdsarr.join(','));
                orderIds.value = checkedItemIdsarr.join(',');
                console.log(document.getElementById('orderIds').value);
                if (checkedItemIdsarr.length > 0) {
                    checkoutButton.disabled = false;
                } else {
                    checkoutButton.disabled = true;
                }
            }

            // Gọi hàm kiểm tra mỗi khi có checkbox thay đổi
            const checkboxes = document.querySelectorAll('.product-checkbox');
            checkboxes.forEach(checkbox => {
                checkbox.addEventListener('change', checkSelectedItems);
            });

            // Gọi hàm kiểm tra khi trang web được tải lên
            checkSelectedItems();

            $(document).ready(function () {
                var minQuantity = 1;
                // Khi nút giảm số lượng được nhấn
                $('.quantity-left-minus').click(function (e) {
                    e.preventDefault();
                    var button = $(this);
                    var quantityInput = button.closest('.input-group').find('.input-number');
                    var currentQuantity = parseInt(quantityInput.val());
                    if (currentQuantity > minQuantity) {
                        quantityInput.val(currentQuantity - 1).change();
                    }
                });

                // Khi nút tăng số lượng được nhấn
                $('.quantity-right-plus').click(function (e) {
                    e.preventDefault();
                    var button = $(this);
                    var quantityInput = button.closest('.input-group').find('.input-number');
                    var currentQuantity = parseInt(quantityInput.val());
                    quantityInput.val(currentQuantity + 1).change();
                });
                // Xử lý sự kiện thay đổi số lượng
                $('.input-number').on('change', function () {
                    var input = $(this);
                    var newQuantity = input.val();
                    var id = input.closest('tr').find('.product-checkbox').data('id');
                    var price = parseFloat(input.data('price'));

                    // AJAX request để cập nhật số lượng trên server
                    // Tương tự như cách bạn đã làm với size
                    $.ajax({
                        url: 'updateQuantityCart', // URL của bạn
                        type: 'POST',
                        data: {id: id, quantity: newQuantity},
                        success: function (response) {
                            // Xử lý response
                            console.log('Số lượng đã được cập nhật', response);
                            var total = price * newQuantity;
                            $('#total' + input.data('index')).text('$' + total.toFixed(2));
                            input.closest('tr').find('.product-checkbox').data('quantity', newQuantity);
                        },
                        error: function (error) {
                            console.error('Có lỗi xảy ra', error);
                        }
                    });
                });
                $('.size-select').on('change', function () {
                    var sizeSelected = $(this).val(); // Lấy giá trị size được chọn
                    var id = $(this).closest('tr').find('.product-checkbox').data('id'); // Giả sử ID sản phẩm được lưu trong data attribute của checkbox

                    // Tạo object data để gửi
                    var data = {
                        id: id,
                        size: sizeSelected
                    };
                    // Gửi request AJAX đến backend
                    $.ajax({
                        url: 'changeSizeCart', // Thay thế YOUR_BACKEND_URL bằng URL thực tế của bạn
                        type: 'POST',
                        data: data,
                        success: function (response) {
                            // Xử lý khi request thành công
                            console.log('Size đã được cập nhật', response);
                        },
                        error: function (error) {
                            // Xử lý khi có lỗi xảy ra
                            console.error('Có lỗi xảy ra', error);
                        }
                    });
                });
            });

            document.addEventListener('DOMContentLoaded', function () {
                const checkboxes = document.querySelectorAll('.product-checkbox');
                const form = document.getElementById('cartForm');

                checkboxes.forEach(checkbox => {
                    checkbox.addEventListener('change', function () {
                        submitFormWithCheckedItems();
                    });
                });

                function submitFormWithCheckedItems() {
                    // Xóa các input ẩn hiện có
                    const existingHiddenInputs = document.querySelectorAll('input[name="checkedItemIds"]');
                    existingHiddenInputs.forEach(input => input.remove());

                    // Tạo các input ẩn mới cho các checkbox được chọn
                    checkboxes.forEach(checkbox => {
                        if (checkbox.checked) {
                            const hiddenInput = document.createElement('input');
                            hiddenInput.type = 'hidden';
                            hiddenInput.name = 'checkedItemIds';
                            hiddenInput.value = checkbox.getAttribute('data-id');
                            form.appendChild(hiddenInput);
                        }
                    });

                    // Submit form
                    form.submit();
                }
            });
        </script>
    </body>
</html>