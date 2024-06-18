<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="vi">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Trang Chủ</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">
        
        <head>
    ...
    <!-- Chart.js Library -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    ...
</head>


    </head>

    <body>
        <!-- Main Wrapper Start -->
        <!--Offcanvas menu area start-->
        <div class="off_canvars_overlay"></div>

        <jsp:include page="layout/menu.jsp"/>

        <!--slider area start-->
        <div class="slider_area slider_style home_three_slider owl-carousel">
            <div class="single_slider" data-bgimg="assets/img/slider/slider8.png">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <div class="slider_content content_one">   <br/>                                                            
                                <a style="margin-top: 470px" href="product">Khám Phá Ngay</a>
                            </div>    
                        </div>
                    </div>
                </div>    
            </div>
            <div class="single_slider" data-bgimg="assets/img/slider/slider7.png">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <div class="slider_content content_two">

                                <a style="margin-top: 470px"  href="product">Khám Phá Ngay</a>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
            <div class="single_slider" data-bgimg="assets/img/slider/slider9.png">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <div class="slider_content content_three">

                                <a style="margin-top: 470px" href="product">Khám Phá Ngay</a>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--slider area end-->

        <!--banner area start-->
        <div class="banner_section banner_section_three">
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-lg-4 col-md-6">
                        <div class="banner_area">
                            <div class="banner_thumb">
                                <a href="product"><img src="assets/img/bg/banner_fashion1.png" alt="#"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="banner_area">
                            <div class="banner_thumb">
                                <a href="product"><img src="assets/img/bg/banner3.png" alt="#"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="banner_area bottom">
                            <div class="banner_thumb">
                                <a href="product"><img src="assets/img/bg/banner_fashion2.png" alt="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--banner area end-->

        <!--product section area start-->
        <section class="product_section womens_product bottom">
            <div class="container">
                <div class="row">   
                    <div class="col-12">
                        <div class="section_title">
                            <h2>Sản phẩm của chúng tôi</h2>
                            <p>Sản phẩm dành cho bạn</p>
                        </div>
                    </div> 
                </div>    
                <div class="product_area"> 
                    <div class="row">
                        <div class="product_carousel product_three_column4 owl-carousel">
                            <c:forEach items="${top10}" var="top10">
                                <div class="col-lg-3">
                                    <div class="single_product">
                                        <div class="product_thumb">
                                            <a class="primary_img" href="product?action=productdetail&product_id=${top10.product_id}"><img src="${top10.img}" width="10px;" alt=""></a>
                                            <div class="quick_button">
                                                <a href="product?action=productdetail&product_id=${top10.product_id}" title="quick_view">Xem sản phẩm</a>
                                            </div>
                                        </div>
                                        <div class="product_content">
                                            <h3><a href="product?action=productdetail&product_id=${top10.product_id}">${top10.product_name}</a></h3>
                                            <span class="current_price">${top10.product_price}đ</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--banner area start-->
        <section class="banner_section banner_section_three">
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-lg-6 col-md-6">
                        <div class="banner_area">
                            <div class="banner_thumb">
                                <a href="product"><img src="assets/img/bg/banner_fashion3.jpg" alt="#"></a>
                                <div class="banner_content">                                  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="banner_area">
                            <div class="banner_thumb">
                                <a href="product"><img src="assets/img/bg/banner_fashion4.jpg" alt="#"></a>
                                <div class="banner_content">                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--banner area end-->

        <!--product section area start-->
        <section class="product_section womens_product bottom">
            <div class="container">
                <div class="row">   
                    <div class="col-12">
                        <div class="section_title">
                            <h2>Sản phẩm thịnh hành</h2>
                            <p>Sản phẩm ấn tượng và bán chạy nhất</p>
                        </div>
                    </div> 
                </div>    
                <div class="product_area"> 
                    <div class="row">
                        <div class="product_carousel product_three_column4 owl-carousel">
                            <c:forEach items="${topTrend}" var="td">
                                <div class="col-lg-3">
                                    <div class="single_product">
                                        <div class="product_thumb">
                                            <a class="primary_img" href="product?action=productdetail&product_id=${td.product_id}"><img src="${td.img}" width="10px;" alt=""></a>
                                            <div class="quick_button">
                                                <a href="product?action=productdetail&product_id=${td.product_id}" title="quick_view">Xem sản phẩm</a>
                                            </div>
                                        </div>
                                        <div class="product_content">
                                            <h3><a href="product?action=productdetail&product_id=${td.product_id}">${td.product_name}</a></h3>
                                            <span class="current_price">${td.product_price}đ</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>


            <!--banner area end-->
            <div class="banner_section banner_section_three">
                <div class="container-fluid">
                    <div class="row ">
                        <div class="col-lg-4 col-md-6">
                            <div class="banner_area">
                                <div class="banner_thumb">
                                    <a href="product"><img src="assets/img/bg/banner_fashion1.png" alt="#"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="banner_area">
                                <div class="banner_thumb">
                                    <a href="product"><img src="assets/img/bg/banner3.png" alt="#"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="banner_area bottom">
                                <div class="banner_thumb">
                                    <a href="product"><img src="assets/img/bg/banner_fashion2.png" alt="#"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--product section area start-->
            <section class="product_section womens_product bottom">
                <div class="container">
                    <div class="row">   
                        <div class="col-12">
                            <div class="section_title">
                                <h2>Sản phẩm mới nhất</h2>
                                <p>Sản phẩm mới nhất của cửa hàng</p>
                            </div>
                        </div> 
                    </div>    
                    <div class="product_area"> 
                        <div class="row">
                            <div class="product_carousel product_three_column4 owl-carousel">
                                <c:forEach items="${newProducts}" var="product">
                                    <div class="single_product">
                                        <div class="product_thumb">
                                            <a class="primary_img" href="product?action=productdetail&product_id=${product.product_id}"><img src="${product.img}" alt=""></a>
                                            <div class="quick_button">
                                                <a href="product?action=productdetail&product_id=${product.product_id}" title="quick_view">View Product</a>
                                            </div>
                                        </div>
                                        <div class="product_content">
                                            <h3><a href="product?action=productdetail&product_id=${product.product_id}">${product.product_name}</a></h3>
                                            <span class="current_price">${product.product_price}đ</span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
                <df-messenger
                    intent="WELCOME"
                    chat-title="chatAI-Doanh"
                    agent-id="ec189088-7e7b-40b7-a464-fec9d527a03f"
                    language-code="en"
                    ></df-messenger>
            </section>
            <!--product section area end-->
            <!--footer area start-->
            <jsp:include page="layout/footer.jsp"/>
            <!--footer area end-->
            <!-- JS
            ============================================ -->

            <!-- Plugins JS -->
            <script src="assets/js/plugins.js"></script>

            <!-- Main JS -->
            <script src="assets/js/main.js"></script>
    </body>

</html>
