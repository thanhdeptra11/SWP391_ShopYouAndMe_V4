<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

        <!-- CSS -->
        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <style>.product-buttons {
            display: flex;
            gap: 10px;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;  /* Adjust padding as needed */
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            white-space: nowrap;  /* Prevent text wrapping */
        }

        .button:hover {
            background-color: #0056b3;
        }

        .button:active {
            background-color: #003d82;
        }

        .button-secondary {
            background-color: #28a745;
        }

        .button-secondary:hover {
            background-color: #218838;
        }

        .button-secondary:active {
            background-color: #1e7e34;
        }
        /* General Styles */
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }



        /* Product Name */
        h1 {
            font-size: 3em;
            margin-bottom: 20px;
            color: #2c3e50;
            font-weight: bold;
            text-align: center;
        }

        /* Product Price */
        .product_price {
            font-size: 1.5em;
            margin-bottom: 20px;
            color: #e74c3c;
            text-align: center;
            font-weight: bold;
        }
        .product_price label {
            font-weight: bold;
            color: #555;
        }
        .product_price .current_price {
            font-size: 2em;
            color: #e74c3c;
            font-weight: bold;
        }

        /* Product Description */
        .product_desc {
            margin-top: 20px;
            padding: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .product_desc label {
            display: block;
            font-size: 1.5em;
            font-weight: bold;
            color: #555;
            margin-bottom: 10px;
        }
        .product_desc p {
            margin: 0;
            font-size: 1.2em;
            color: #34495e;
            font-weight: bold;
        }

        .product_reviews {
            margin-top: 30px;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        .rating, .comment {
            margin-bottom: 15px;
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
        }
        .rating p, .comment p {
            margin: 5px 0;
        }
    </style>
    <style>
        /* Form container style */
        .form-container {
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        /* Form heading style */
        .form-container h3 {
            font-size: 1.5em;
            margin-bottom: 10px;
            color: #333;
        }

        /* Select input style for rating */
        .form-container select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        /* Textarea input style for comment */
        .form-container textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        /* Submit button style */
        .form-container button {
            padding: 10px 20px;
            font-size: 1em;
            font-weight: bold;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-container button:hover {
            background-color: #0056b3;
        }

        .form-container button:active {
            background-color: #003d82;
        }
    </style>



    <body>
        <c:if test="${not empty sessionScope.successMessageAdd}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${sessionScope.successMessageAdd}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:remove var="successMessage" scope="session"/>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${successMessage}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>


        <!-- Main Wrapper Start -->
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
                            <form id="productForm" action="cart" method="POST">
                                <input type="hidden" name="action" id="action" value="addtocart">
                                <input type="hidden" name="product_id" value="${ProductData.product_id}">
                                <div class="product_container">                               
                                    <h1>${ProductData.product_name}</h1>
                                    <div class="product_price">
                                        <label >Giá tiền: </label>
                                        <span class="current_price">${ProductData.product_price} VNĐ</span>
                                    </div>
                                    <div class="product_desc">
                                        <h4>Đánh giá trung bình: 
                                            <fmt:formatNumber value="${averageRating}" maxFractionDigits="1"/> <span class="star">&#9733;</span>
                                        </h4>
                                        <label>Mô tả sản phẩm: </label>
                                        <p>${ProductData.product_describe}</p>
                                    </div>
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
                                    <select class="niceselect_option" id="size" name="size">
                                        <c:forEach items="${SizeData}" var="s">
                                            <option value="${s.size}">${s.size}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="product_variant quantity">
                                    <label>quantity</label>
                                    <input min="1" max="${ProductData.quantity}" name="quantity" type="number" value="1">
                                    <div class="product-buttons">
                                        <button class="button" type="submit">Thêm vào giỏ hàng</button>
                                        <button class="button button-secondary" type="button" onclick="setActionAndSubmit('buynow')">Mua ngay</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- Form for adding comment -->
        <div class="form-container">
            <form action="product" method="POST">
                <input type="hidden" name="action" value="addComment">
                <input type="hidden" name="product_id" value="${ProductData.product_id}">
                <input type="hidden" name="user_id" value="${user.user_id}">                
                <input type="hidden" name="user_name" value="${user.user_name}">                
                <h3>Bình luận sản phẩm</h3>
                <select name="rating" id="star-rating" style="width: 100px">
                    <option value="1">1&#9733;</option>
                    <option value="2">2&#9733;&#9733;</option>
                    <option value="3">3&#9733;&#9733;&#9733;</option>
                    <option value="4">4&#9733;&#9733;&#9733;&#9733;</option>
                    <option value="5">5&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                </select>
                <textarea name="comment" rows="4" cols="50" placeholder="Nhập bình luận của bạn"></textarea>
                <button type="submit">Gửi bình luận</button>
            </form>
        </div>
        <c:if test="${not empty comments}">
            <div class="product_reviews">
                <h3>Đánh giá và Bình luận</h3>
                <h5>Bình luận:</h5>
                <c:forEach items="${comments}" var="c">
                    <div class="comment">
                        <p>Bởi: ${c.user_name}</p>
                        <p>${c.rating}&#9733</p>
                        <p>Ngày: <fmt:formatDate value="${c.createdAt}" pattern="dd/MM/yyyy"/></p>
                        <p>${c.comment}</p>
                    </div>

                </c:forEach>
            </div>
        </c:if>



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
                                            <span class="current_price">${pc.product_price} VNĐ</span>
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
                                                function setActionAndSubmit(action) {
                                                    document.getElementById('action').value = action;
                                                    document.getElementById('productForm').submit();
                                                }
        </script>
    </body>
</html>
