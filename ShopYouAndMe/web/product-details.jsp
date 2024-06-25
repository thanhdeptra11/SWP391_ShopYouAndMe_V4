<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Thông Tin Sản Phẩm</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

        <!-- CSS 
        ========================= -->


        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">

        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">

    </head>

    <body>

        <!-- Main Wrapper Start -->
        <!--Offcanvas menu area start-->
        <div class="off_canvars_overlay"></div>
        <jsp:include page="layout/menu.jsp"/>

        <!--breadcrumbs area start-->
        <div class="breadcrumbs_area product_bread">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">home</a></li>
                                <li>/</li>
                                <li>product details</li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>
        <!--breadcrumbs area end-->

        <!--product details start-->
        <div class="product_details">
            <div class="container">

                <div class="row">
                    <div class="col-lg-5 col-md-5">
                        <div class="product-details-tab">

                            <div id="img-1" class="zoomWrapper single-zoom">
                                <a href="#">
                                    <img id="zoom1" src="${ProductData.img}" data-zoom-image="${ProductData.img}" alt="product">
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-7 col-md-7">
                        <div class="product_d_right">
                            <form action="cart?product_id=${ProductData.product_id}" method="POST">
                                <h1>${ProductData.product_name}</h1>
                                <div class="product_price">
                                    <label>Giá tiền: </label>
                                    <span class="current_price">${ProductData.product_price} VNĐ</span>
                                </div>
                                <div class="product_desc">
                                    <label>Mô tả sản phẩm: </label>
                                    <p>${ProductData.product_describe}</p>
                                </div>

                                <div class="product_variant color">
                                    <h3>color</h3>
                                    <select class="niceselect_option" id="color" name="color">
                                        <c:forEach items="${ColorData}" var="c">
                                            <option value="${c.color}">${c.color}</option>
                                        </c:forEach>
                                    </select>   
                                </div>
                                <div class="product_variant size">
                                    <h3>size</h3>
                                    <select class="niceselect_option" id="color1" name="size">       
                                        <c:forEach items="${SizeData}" var="s">
                                            <option value="${s.size}">${s.size}</option>
                                        </c:forEach>                             
                                    </select> 
                                </div>
                                <div class="product_variant quantity">
                                    <label>quantity</label>
                                    <input min="1" max="${ProductData.quantity}" name="quantity" type="number">
                                    <button class="button" type="submit">Thêm vào giỏ hàng</button> 
                                    <button class="button" type="submit" onclick="setAction('buynow')">Mua ngay</button>                                  
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
        <!--product details end-->
        <!--product info end-->

        <div class="form-container">
            <form action="product" method="POST">
                <input type="hidden" name="action" value="addRating">
                <input type="hidden" name="product_id" value="${ProductData.product_id}">
                <input type="hidden" name="user_id" value="${user.user_id}">
                <h3>Đánh giá sản phẩm</h3>
                <select name="rating" id="star-rating">
                    <option value="1">1&#9733;</option>
                    <option value="2">2&#9733;&#9733;</option>
                    <option value="3">3&#9733;&#9733;&#9733;</option>
                    <option value="4">4&#9733;&#9733;&#9733;&#9733;</option>
                    <option value="5">5&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                </select>
                <button type="submit">Gửi đánh giá</button>
            </form>
        </div>


        <!-- Form for adding comment -->
        <div class="form-container">
            <form action="product" method="POST">
                <input type="hidden" name="action" value="addComment">
                <input type="hidden" name="product_id" value="${ProductData.product_id}">
                <input type="hidden" name="user_id" value="${user.user_id}">
                <h3>Bình luận sản phẩm</h3>
                <textarea name="comment" rows="4" cols="50" placeholder="Nhập bình luận của bạn"></textarea>
                <button type="submit">Gửi bình luận</button>
            </form>
        </div>



        <div class="product_reviews">
            <h3>Đánh giá và Bình luận</h3>
            <h4>Đánh giá trung bình: 
                <fmt:formatNumber value="${averageRating}" maxFractionDigits="1"/> <span class="star">&#9733;</span>
            </h4>
            <c:forEach items="${ratings}" var="rating">
                <div class="rating">
                    <p>${rating.rating}&#9733</p>
                    <p>Bởi: ${user.user_name}</p>
                    <p>Ngày: <fmt:formatDate value="${rating.createdAt}" pattern="dd/MM/yyyy"/></p>
                </div>
            </c:forEach>

            <h5>Bình luận:</h5>
            <c:forEach items="${comments}" var="c">
                <div class="comment">
                    <p>Bởi: ${user.user_name}</p>
                    <p>${c.comment}</p>
                </div>
            </c:forEach>
        </div>

        <!--product section area start-->
        <section class="product_section related_product">
            <div class="container">
                <div class="row">   
                    <div class="col-12">
                        <div class="section_title">
                            <h2>Sản phẩm tương tự</h2>
                        </div>
                    </div> 
                </div>    
                <div class="product_area"> 
                    <div class="row">
                        <div class="product_carousel product_three_column4 owl-carousel">
                            <c:forEach items="${ProductByCategory}" var="pc">
                                <div class="col-lg-3">
                                    <div class="single_product">
                                        <div class="product_thumb">
                                            <a class="primary_img" href="product?action=productdetail&product_id=${pc.product_id}"><img src="${pc.img}" alt=""></a>
                                        </div>
                                        <div class="product_content">
                                            <h3><a href="product?action=productdetail&product_id=${pc.product_id}">${pc.product_name}</a></h3>
                                            <span class="current_price">${pc.product_price}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--product section area end-->

        <!--footer area start-->
        <jsp:include page="layout/footer.jsp"/>
        <!--footer area end-->

        <!-- Plugins JS -->
        <script src="assets/js/plugins.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <!-- Main JS -->
        <script src="assets/js/main.js"></script>
        <script>
//            $("#submit").click(function()){
//            swal({
//            title: "Thanks for Contacting us..!",
//                    text: "We Will Contact You Soon...",
//                    icon: "success",
//            })
//            }


                                        function setAction(action) {
                                            document.getElementById('action').value = action;
                                            if (action === 'buynow') {
                                                document.getElementById('productForm').action = 'cart?action=showcart';
                                            }
                                        }
        </script>
    </body>

</html>
