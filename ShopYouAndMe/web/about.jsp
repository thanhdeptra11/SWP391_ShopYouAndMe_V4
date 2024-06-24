<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
    <head>
        <!-- Meta Tags -->
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Giới Thiệu | You&Me Shop</title>
        <meta name="description" content="Giới thiệu về You&Me Shop - Thương hiệu thời trang Việt Nam với sản phẩm chất lượng cao và thiết kế độc đáo.">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <!-- Header & Menu -->
        <jsp:include page="layout/menu.jsp"/>
        <!-- Breadcrumbs -->
        <div class="breadcrumbs_area other_bread">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">Trang chủ</a></li>
                                <li>/</li>
                                <li>Giới thiệu</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- About Section -->
        
        <div class="about_section">
            <div class="container">
                <c:forEach items="${listabout}" var="c">
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <div class="about_content">
                            <h1>${c.title}</h1>
                            <p>${c.content}</p>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="about_thumb">
                            <img src=${c.img} alt="Hình ảnh về You&Me Shop">
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>

        <!-- Our Story Section -->
        <div class="our_story_section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <div class="story_thumb">
                            <img src="https://images.pexels.com/photos/3768005/pexels-photo-3768005.jpeg?cs=srgb&dl=pexels-willoworld-3768005.jpg&fm=jpg" alt="Hình ảnh về câu chuyện của You&Me Shop">
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="story_content">
                            <h2>Câu Chuyện của Chúng Tôi</h2>
                            <p>You&Me Shop khởi đầu với một đam mê dành cho thời trang và một tầm nhìn mang đến những sản phẩm chất lượng cao, phong cách và độc đáo cho khách hàng.</p>
                            <p>Với sự đam mê và tận tâm, chúng tôi đã không ngừng nỗ lực để mang đến những thiết kế mới lạ và đột phá, kết hợp giữa phong cách truyền thống và hiện đại.</p>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        </br>
        <!-- Our Team Section -->
        <div class="our_team_section">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section_title">
                            <h2>Đội Ngũ Của Chúng Tôi</h2>
                        </div>
                    </div>
                </div>

                <div class="row">
                    
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single_team">
                            <div class="team_thumb">
                                <img src="https://png.pngtree.com/png-vector/20190223/ourlarge/pngtree-admin-rolls-glyph-black-icon-png-image_691507.jpg" alt="Hình ảnh thành viên đội ngũ">
                            </div>
                            <div class="team_content text-center">
                                <h3>Ngọc Doanh</h3>
                                <span>Sáng lập WEB</span>
                            </div>
                        </div>
                    </div>
                    <!-- Thêm các thành viên đội ngũ khác tương tự -->
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single_team">
                            <div class="team_thumb">
                                <img src="https://png.pngtree.com/png-vector/20190223/ourlarge/pngtree-admin-rolls-glyph-black-icon-png-image_691507.jpg" alt="Hình ảnh thành viên đội ngũ">
                            </div>
                            <div class="team_content text-center">
                                <h3>Khánh An</h3>
                                <span>Sáng lập WEB</span>
                            </div>
                        </div>
                    </div>
                    <!-- Thêm các thành viên đội ngũ khác tương tự -->
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single_team">
                            <div class="team_thumb">
                                <img src="https://png.pngtree.com/png-vector/20190223/ourlarge/pngtree-admin-rolls-glyph-black-icon-png-image_691507.jpg" alt="Hình ảnh thành viên đội ngũ">
                            </div>
                            <div class="team_content text-center">
                                <h3>Hiếu Thành</h3>
                                <span>Sáng lập WEB</span>
                            </div>
                        </div>
                    </div>
                    <!-- Thêm các thành viên đội ngũ khác tương tự -->
                </div>

            </div>
            <!-- Footer -->
            <jsp:include page="layout/footer.jsp"/>
            <!-- JS -->
            <script src="assets/js/plugins.js"></script>
            <script src="assets/js/main.js"></script>
    </body>
</html>