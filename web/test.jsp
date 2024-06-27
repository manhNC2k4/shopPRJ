<%-- 
    Document   : test
    Created on : Jun 26, 2024, 3:25:12 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ashion Template">
        <meta name="keywords" content="Ashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Ashion | Template</title>

        <!-- Google Font -->
<!--        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">-->

        <!-- Css Styles -->
        <link rel="stylesheet" href="ashion/css/bootstrap.min.css" type="text/css">
        <!--<link rel="stylesheet" href="ashion/css/font-awesome.min.css" type="text/css">-->
        <!--    <link rel="stylesheet" href="ashion/css/elegant-icons.css" type="text/css">-->
        <link rel="stylesheet" href="ashion/css/jquery-ui.min.css" type="text/css">
        <!--<link rel="stylesheet" href="ashion/css/magnific-popup.css" type="text/css">-->
        <!--<link rel="stylesheet" href="ashion/css/owl.carousel.min.css" type="text/css">-->
        <!--<link rel="stylesheet" href="ashion/css/slicknav.min.css" type="text/css">-->

        <style>
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
                left: 81px;
                top: 12px;
                height: 1px;
                width: 5px;
                background: #0d0d0d;
                content: "";
            }

            .filter-range-wrap .range-slider .price-input input {
                font-size: 16px;
                color: #0d0d0d;
                max-width: 16%;
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
                background: #ca1515;
                border: none;
                outline: none;
                cursor: pointer;
            }

            .filter-range-wrap .price-range .ui-slider-range {
                background: #ca1515;
                border-radius: 0;
            }
        </style>

    </head>

    <body>
        <!-- Shop Section Begin -->
        <section class="shop spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <div class="shop__sidebar">
                            <div class="sidebar__filter">
                                <div class="section-title">
                                    <h4>Shop by price</h4>
                                </div>

                                <a href="#">Filter</a>
                            </div>                       
                        </div>
                    </div>
                </div>
            </div>
            <div class="filter-range-wrap">
                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                     data-min="33" data-max="99"></div>
                <div class="range-slider">
                    <div class="price-input">
                        <p>Price:</p>
                        <input type="text" id="minamount">
                        <input type="text" id="maxamount">
                    </div>
                </div>
            </div>
        </section>
        <!-- Shop Section End -->
<script src="ashion/js/jquery-ui.min.js"></script>
        <script src="ashion/js/jquery.nicescroll.min.js"></script>
        <script src="ashion/js/jquery.slicknav.js"></script>
        <script src="ashion/js/jquery.countdown.min.js"></script>
        <script src="ashion/js/mixitup.min.js"></script>
        <script src="ashion/js/jquery-3.3.1.min.js"></script>
        <!-- Js Plugins -->
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
    </body>

</html>