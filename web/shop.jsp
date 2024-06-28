


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>MayJaShop</title>
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
        <link rel="stylesheet" href="ashion/css/jquery-ui.min.css" type="text/css">

        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .clicked-category {
                color: #dbcc8f;
            }
            .hidden {
                display: none;
            }
            .size-item {
                margin: 5px;
                width: 25%;
                text-align: center;
                border-radius: 5px;
            }
            input[type="checkbox"] {
                display: none;
                margin-top: 0;
                vertical-align: top;
                width: 18px;
                height: 18px;
                /* -webkit-appearance: none; */
                -moz-appearance: none;
                appearance: none;
                position: relative;
                margin: 0 1rem 0 0;
                cursor: pointer;
                outline: none !important;
                border: none !important;
                box-sizing: border-box;
                padding: 0;
            }
            label {
                display: inline-block;
                background-color: #f1f0f1;
                margin-right: 15px;
                padding: 5px 15px;
                border-radius: 10px;
                cursor: pointer;
                color: black;
            }
            input[type="checkbox"]:checked + label {
                background-color: #dbcc8f; /* Màu nền khi checkbox được checked */
                color: white; /* Màu chữ khi checkbox được checked */
            }
            .filter-range-wrap .range-slider .price-input {
                position: relative;
            }

            .filter-range-wrap .range-slider .price-input p {
                font-size: 16px;
                color: #0d0d0d;
                font-weight: 500;
                display: inline-block;
                margin-bottom: 0;
            }

            .filter-range-wrap .range-slider .price-input:after {
                position: absolute;
                left: 97px;
                top: 15px;
                height: 1px;
                width: 5px;
                background: #000;
                content: "";
            }

            .filter-range-wrap .range-slider .price-input input {
                font-size: 16px;
                background: #f8f9fa;
                color: #0d0d0d;
                max-width: 26%;
                border: none;
            }

            .filter-range-wrap .range-slider .price-input input:nth-child(1) {
                margin-right: 28px;
            }

            .filter-range-wrap .price-range {
                border-radius: 0;
                margin-bottom: 28px;
            }

            .filter-range-wrap .price-range.ui-widget-content {
                border: none;
                background: rgba(0, 0, 0, 0.1);
                height: 5px;
            }

            .filter-range-wrap .price-range.ui-widget-content .ui-slider-handle {
                height: 14px;
                width: 14px;
                border-radius: 50%;
                background: #dbcc8f;
                border: none;
                outline: none;
                cursor: pointer;
            }

            .filter-range-wrap .price-range .ui-slider-range {
                background: #dbcc8f;
                border-radius: 0;
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
                                <span class="text">mayja@email.com</span>
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
                        <li class="nav-item"><a href="index" class="nav-link">Home</a></li>
                        <li class="nav-item dropdown active">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catalog</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown04">
                                <a class="dropdown-item" href="shop">Shop</a>
                                <a class="dropdown-item" href="product-single.html">Single Product</a>
                                <a class="dropdown-item" href="cart.html">Cart</a>
                                <a class="dropdown-item" href="checkout.html">Checkout</a>
                            </div>
                        </li>
                        <li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
                        <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
                        <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
                        <li class="nav-item cta cta-colored"><a href="cart.html" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-color:#fff; padding: 2em 0">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <!--                    <div class="col-md-9 ftco-animate text-center">
                                            <p class="breadcrumbs"><span class="mr-2"><a href="index">Home</a></span> <span>Shop</span></p>
                                            <h1 class="mb-0 bread">Shop</h1>
                                        </div>-->
                </div>
            </div>
        </div>

        <section class="ftco-section bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-10 order-md-last">
                        <div class="row">
                            <c:choose>
                                <c:when test="${not empty requestScope.dataPro}">
                                    <c:forEach var="p" items="${dataPro}">
                                        <div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex">
                                            <div class="product d-flex flex-column">
                                                <a href="#" class="img-prod"><img class="img-fluid" src="${p.images[0]}" alt="${p.name}">
                                                    <div class="overlay"></div>
                                                </a>
                                                <div class="text py-3 pb-4 px-3">
                                                    <div class="d-flex">
                                                        <div class="cat">
                                                            <span>${categoryMap[p.categoryId]}</span>
                                                        </div>
                                                        <div class="rating">
                                                            <p class="text-right mb-0">
                                                                <a href="#"><span class="ion-ios-star-outline"></span></a>
                                                                <a href="#"><span class="ion-ios-star-outline"></span></a>
                                                                <a href="#"><span class="ion-ios-star-outline"></span></a>
                                                                <a href="#"><span class="ion-ios-star-outline"></span></a>
                                                                <a href="#"><span class="ion-ios-star-outline"></span></a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <h3><a href="#">${p.name}</a></h3>
                                                    <div class="pricing">
                                                        <p class="price"><span>$${p.price}</span></p>
                                                    </div>
                                                    <p class="bottom-area d-flex px-3">
                                                        <a href="#" class="add-to-cart text-center py-2 mr-1"><span>Add to cart <i class="ion-ios-add ml-1"></i></span></a>
                                                        <a href="singleProduct?id=${p.id}" class="buy-now text-center py-2">Buy now<span><i class="ion-ios-cart ml-1"></i></span></a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <p>No products available.</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="row mt-5">
                            <div class="col text-center">
                                <div class="block-27">
                                    <ul>
                                        <li>
                                            <a href="shop?page=${currentPage > 1 ? currentPage - 1 : 1}">&lt;</a>
                                        </li>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="${i == currentPage ? 'active' : ''}">
                                                <a href="shop?page=${i}&cid=${param.cid != null ? param.cid : 0}&sort=${param.sort != null ? param.sort : 0}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li>        
                                            <a href="shop?page=${currentPage < totalPages ? currentPage + 1 : totalPages}&cid=${param.cid != null ? param.cid : 0}&sort=${param.sort != null ? param.sort : 0}">&gt;</a>
                                        </li>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-2">
                        <div class="sidebar">
                            <div class="sidebar-box-2">
                                <c:choose>
                                    <c:when test="${requestScope.cid != 0}">
                                        <h2 class="heading" >${categoryMap[cid]} (${requestScope.totalProducts})</h2>
                                    </c:when>
                                    <c:otherwise>
                                        <h2 class="heading" >All Shoes (${requestScope.totalProducts})</h2>
                                    </c:otherwise>
                                </c:choose>

                                <div class="fancy-collapse-panel">
                                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="headingOne">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="${param.cid != 0 ? 'true' : 'false'}" aria-controls="collapseOne">Categories
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                                <div class="panel-body">
                                                    <ul>
                                                        <c:if test="${requestScope.cid != 0}">
                                                            <li>
                                                                <a href="shop">All</a>
                                                            </li>
                                                        </c:if>
                                                        <c:forEach var="entry" items="${categoryMap}">
                                                            <li>
                                                                <a href="shop?cid=${entry.key}"
                                                                   class="${param.cid == entry.key ? 'clicked-category' : ''}">${entry.value}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="headingTwo">
                                                <h4 class="panel-title">
                                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Sort
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                                <div class="panel-body">
                                                    <ul>
                                                        <li><a href="shop?sort=1&cid=${param.cid != null ? param.cid : 0}">Newest</a></li>
                                                        <li><a href="shop?sort=2&cid=${param.cid != null ? param.cid : 0}">Oldest</a></li>
                                                        <li><a href="shop?sort=3&cid=${param.cid != null ? param.cid : 0}">Top Seller</a></li>
                                                        <li><a href="shop?sort=4&cid=${param.cid != null ? param.cid : 0}">Price(High - Low)</a></li>
                                                        <li><a href="shop?sort=5&cid=${param.cid != null ? param.cid : 0}">Price(Low - High)</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="headingThree">
                                                <h4 class="panel-title">
                                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">Size
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse show" role="tabpanel" aria-labelledby="headingThree">
                                                <div class="panel-body">
                                                    <form action="shop">
                                                        <ul id="sizeList" style="list-style: none; display: flex; flex-wrap: wrap; width: 100%; margin: 0; padding: 0;">
                                                            <c:forEach var="i" begin="3" end="18">
                                                                <li class="size-item default" > 
                                                                    <input type="checkbox" id="id${i}" name="size" value="${i}" onchange="this.form.submit()" 
                                                                           <c:forEach items="${requestScope.size}" var="l">
                                                                               <c:if test="${l==i}">checked</c:if>
                                                                           </c:forEach> >
                                                                    <label for="id${i}">${i}</label>
                                                                </li>
                                                            </c:forEach>    
                                                        </ul>
                                                         <input type="hidden" name="nosize" value="nosize">
                                                        <input type="hidden" name="cid" value="${param.cid != null ? param.cid : 0}">
                                                        <input type="hidden" name="sort" value="${param.sort != null ? param.sort : 0}">
                                                    </form>
                                                    <button id="showMoreBtn" onclick="toggleList()" style="margin-top: 10px; border: 1px solid #ccc; border-radius: 5px">Show More</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="sidebar-box-2">
                                <h2 class="heading">Price Range</h2>
                                <form class="colorlib-form-2" action="shop">
                                    <div class="filter-range-wrap">
                                        <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                             data-min="${requestScope.minPrice}" data-max="${requestScope.maxPrice}"
                                             data-selected-min="${sessionScope.priceFrom}"
                                             data-selected-max="${sessionScope.priceTo}"
                                             ></div>
                                        <div class="range-slider">
                                            <div class="price-input">
                                                <button class="btn btn-primary btn-outline-secondary" type="submit">Filter</button>
                                                <input type="text" name="priceFrom" id="minamount">
                                                <input type="text" name="priceTo"  id="maxamount">
                                                <input type="hidden" name="cid" value="${param.cid != null ? param.cid : 0}">
                                                <input type="hidden" name="sort" value="${param.sort != null ? param.sort : 0}">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-gallery">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 heading-section text-center mb-4 ftco-animate">
                        <h2 class="mb-4">Follow Us On Instagram</h2>
                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in</p>
                    </div>
                </div>
            </div>
            <div class="container-fluid px-0">
                <div class="row no-gutters">
                    <div class="col-md-4 col-lg-2 ftco-animate">
                        <a href="images/gallery-1.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(images/gallery-1.jpg);">
                            <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                <span class="icon-instagram"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4 col-lg-2 ftco-animate">
                        <a href="images/gallery-2.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(images/gallery-2.jpg);">
                            <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                <span class="icon-instagram"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4 col-lg-2 ftco-animate">
                        <a href="images/gallery-3.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(images/gallery-3.jpg);">
                            <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                <span class="icon-instagram"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4 col-lg-2 ftco-animate">
                        <a href="images/gallery-4.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(images/gallery-4.jpg);">
                            <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                <span class="icon-instagram"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4 col-lg-2 ftco-animate">
                        <a href="images/gallery-5.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(images/gallery-5.jpg);">
                            <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                <span class="icon-instagram"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4 col-lg-2 ftco-animate">
                        <a href="images/gallery-6.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(images/gallery-6.jpg);">
                            <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                <span class="icon-instagram"></span>
                            </div>
                        </a>
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

        <script>

            function toggleList() {
                var sizeItems = document.querySelectorAll('#sizeList .size-item');
                var showMoreBtn = document.getElementById('showMoreBtn');
                var isExpanded = showMoreBtn.getAttribute('data-expanded') === 'true';

                if (isExpanded) {
                    // Hide extra items
                    sizeItems.forEach(function (item, index) {
                        if (index >= 5) {
                            item.classList.add('hidden');
                        }
                    });
                    showMoreBtn.textContent = 'Show More';
                    showMoreBtn.setAttribute('data-expanded', 'false');
                } else {
                    // Show all items
                    sizeItems.forEach(function (item) {
                        item.classList.remove('hidden');
                    });
                    showMoreBtn.textContent = 'Show Less';
                    showMoreBtn.setAttribute('data-expanded', 'true');
                }
            }

// Initially hide items beyond the first 5
            window.onload = function () {
                var sizeItems = document.querySelectorAll('#sizeList .size-item');
                sizeItems.forEach(function (item, index) {
                    if (index >= 5) {
                        item.classList.add('hidden');
                    }
                });
                document.getElementById('showMoreBtn').setAttribute('data-expanded', 'false');
            };

        </script>
        <script src="js/jquery.min.js"></script>

        <script src="ashion/js/jquery-3.3.1.min.js"></script>
        <script src="ashion/js/bootstrap.min.js"></script>
        <script src="ashion/js/jquery.magnific-popup.min.js"></script>
        <script src="ashion/js/jquery-ui.min.js"></script>
        <script src="ashion/js/mixitup.min.js"></script>
        <script src="ashion/js/jquery.countdown.min.js"></script>
        <script src="ashion/js/jquery.slicknav.js"></script>
        <script src="ashion/js/owl.carousel.min.js"></script>
        <script src="ashion/js/jquery.nicescroll.min.js"></script>
        <script src="ashion/js/main.js"></script>

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
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>