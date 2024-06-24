<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản trị Viên</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="admin/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script src="https://www.gstatic.com/charts/loader.js"></script>

    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" href="home"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="admin/images/user.png" width="50px"
                                                alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b>${sessionScope.user.user_name}</b></p>
                    <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item" href="dashboard"><i class='app-menu__icon bx bx-tachometer'></i><span
                            class="app-menu__label">Bảng thống kê</span></a></li>
                <li><a class="app-menu__item" href="customermanager"><i class='app-menu__icon bx bx-user-voice'></i><span
                            class="app-menu__label">Quản lý khách hàng</span></a></li>
                <li><a class="app-menu__item" href="categorymanager"><i class='app-menu__icon bx bxs-category'></i><span
                            class="app-menu__label">Quản lý danh mục</span></a></li>
                <li><a class="app-menu__item" href="productmanager"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
                </li>
                <li><a class="app-menu__item" href="ordermanager"><i class='app-menu__icon bx bx-task'></i><span
                            class="app-menu__label">Quản lý đơn hàng</span></a></li>
                <li><a class="app-menu__item" href="reportmanager"><i class='app-menu__icon bx bx-receipt'></i><span
                            class="app-menu__label">Quản lý phản hồi</span></a></li>
                <li><a class="app-menu__item" href="aboutmanager"><i class='app-menu__icon bx bx-receipt'></i><span
                            class="app-menu__label">Quản lý trang giới thiệu</span></a></li>
            </ul>
        </aside>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách các bài viết giới thiệu</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">
                                    <a class="btn btn-add btn-sm" href="aboutmanager?action=insert" title="Thêm"><i class="fas fa-plus"></i>
                                        Tạo mới phần giới thiệu</a>
                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm print-file" type="button" title="In" onclick="myApp.printTable()"><i
                                            class="fas fa-print"></i> In dữ liệu</a>
                                </div>
                            </div>
                            <form action="aboutmanager" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">

                                <table class="table table-hover table-bordered" id="sampleTable">
                                    <thead>
                                        <tr>
                                            <th>Mã Bài Viết</th>
                                            <th>Tiêu Đề</th>
                                            <th>Nội dung</th>
                                            <th>Ảnh</th>
                                            <th>Chức năng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listabout}" var="p">
                                            <tr>
                                                <td>${p.getAboutId()}</td>
                                                <td>${p.title}</td>
                                                <td>${p.content}</td>
                                                <td><img src="${p.img}" alt="" width="100px;"></td>

                                                <td>
                                                    <button class="btn btn-primary btn-sm trash" type="button" title="Xóa" value="${p.aboutId}"><i
                                                            class="fas fa-trash-alt"></i>
                                                    </button>
                                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp"
                                                            data-toggle="modal" data-target="#ModalUP${p.aboutId}" ><i class="fas fa-edit"></i>
                                                    </button>
                                                </td>
                                            </tr>

                                            <!--
                                            MODAL
                                            -->

                                        <div class="modal fade" id="ModalUP${p.aboutId}" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
                                             data-keyboard="false">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-body">
                                                        <form action="aboutmanager" method="POST">
                                                            <input type="hidden" name="action" value="updateabout"/>
                                                            <div class="row">
                                                                <div class="form-group  col-md-12">
                                                                    <span class="thong-tin-thanh-toan">
                                                                        <h5>Chỉnh sửa thông tin bài viết giới thiệu</h5>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group col-md-6">
                                                                    <label class="control-label">Mã bài viết </label>
                                                                    <input class="form-control" type="text" readonly name="aboutid" value="${p.aboutId}">
                                                                </div>

                                                                <div class="form-group col-md-6">
                                                                    <label class="control-label">Tiêu đề</label>
                                                                    <input class="form-control" type="text" name="title" required value="${p.title}">
                                                                </div>
                                                                <div class="form-group col-md-6">
                                                                    <label class="control-label">Nội dung</label>
                                                                    <input class="form-control" type="text" name="content" required value="${p.content}">
                                                                </div>

                                                            </div>
                                                            <!--anh san pham-->
                                                            <div class="form-group col-md-12">
                                                                <label class="control-label">Ảnh Bài Viết</label>
                                                                <div id="myfileupload">
                                                                    <input type="file" id="uploadfile" name="about_img" value="${p.img}" onchange="readURL(this);" />
                                                                </div>
                                                                <div id="thumbbox">
                                                                    <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                                                                    <a class="removeimg" href="javascript:"></a>
                                                                </div>
                                                                <div id="boxchoice">
                                                                    <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                                                    <p style="clear:both"></p>
                                                                </div>
                                                            </div>
                                                            
                                                            <button class="btn btn-save" type="submit">Lưu lại</button>
                                                            <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                                                            <BR>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    
                                        <!--
                                      MODAL
                                        -->
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="admin/js/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/popper.min.js"></script>
        <script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/main.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/plugins/pace.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>

        <script type="text/javascript">
                                                                            google.charts.load('current', {'packages': ['corechart']});
                                                                            google.charts.setOnLoadCallback(drawChart);

                                                                            function drawChart() {
                                                                                var data = google.visualization.arrayToDataTable([
                                                                                    ['Category', 'Count'],
            <c:forEach var="category" items="${categoryList}">
                                                                                    ['${category.category_name}', ${category.count}],
            </c:forEach>
                                                                                ]);

                                                                                var options = {
                                                                                    title: 'Tỉ lệ danh mục'
                                                                                };

                                                                                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                                                                                chart.draw(data, options);
                                                                            }
        </script>

        <script type="text/javascript">
            function drawLineChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('date', 'Date');
                data.addColumn('number', 'Paid');
                data.addColumn('number', 'Unpaid');

                var rows = [];
            <c:if test="${not empty dateList}">
                <c:forEach var="i" begin="0" end="${dateList.size() - 1}">
                rows.push([new Date('${dateList[i]}'), ${paidList[i] != null ? paidList[i] : 0}, ${unpaidList[i] != null ? unpaidList[i] : 0}]);
                </c:forEach>
            </c:if>

                data.addRows(rows);

                var options = {
                    title: 'Paid vs Unpaid Amount by Date',
                    curveType: 'function',
                    legend: {position: 'bottom'}
                };

                var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
                chart.draw(data, options);

            }
        </script>
        <script type="text/javascript">
            // Function to update statistics based on checkbox selection
            function updateStatistics() {
                // Get the checkbox elements
                var customerCheckbox = document.getElementById("customerCheckbox");
                var productCheckbox = document.getElementById("productCheckbox");
                var orderCheckbox = document.getElementById("orderCheckbox");
                var outOfStockCheckbox = document.getElementById("outOfStockCheckbox");

                // Get the elements that display the statistics
                var customerInfo = document.querySelector(".widget-small.primary .info p b");
                var productInfo = document.querySelector(".widget-small.info .info p b");
                var orderInfo = document.querySelector(".widget-small.warning .info p b");
                var outOfStockInfo = document.querySelector(".widget-small.danger .info p b");

                // Update the statistics based on checkbox selection
                if (customerCheckbox.checked) {
                  customerInfo.textContent = "${requestScope.user} khách hàng";
                } else {
                    customerInfo.textContent = "";
                }

                if (productCheckbox.checked) {
                  productInfo.textContent = "${requestScope.product} sản phẩm";
                } else {
                    productInfo.textContent = "";
                }

                if (orderCheckbox.checked) {
                  orderInfo.textContent = "${requestScope.bill} đơn hàng";
                } else {
                    orderInfo.textContent = "";
                }

                if (outOfStockCheckbox.checked) {
                  outOfStockInfo.textContent = "${requestScope.low} sản phẩm";
                } else {
                    outOfStockInfo.textContent = "";
                }
            }

        </script>
        <script>
            // Function to get URL parameters
            function getParameterByName(name) {
                const urlParams = new URLSearchParams(window.location.search);
                return urlParams.get(name);
            }

            // On page load, set the values of the input fields
            window.onload = function () {
                const startDate = getParameterByName('startDate');
                const endDate = getParameterByName('endDate');

                if (startDate) {
                    document.getElementById('startDate').value = startDate;
                }
                if (endDate) {
                    document.getElementById('endDate').value = endDate;
                }


            }

        </script>
        <script>

            $(document).ready(jQuery(function () {
                jQuery(document).on('click', '.trash', function () {
                    swal({
                        title: "Cảnh báo",
                        text: "Bạn có chắc chắn là muốn xóa sản phẩm này?",
                        buttons: ["Hủy bỏ", "Đồng ý"],
                    })
                            .then((willDelete) => {
                                if (willDelete) {
                                    window.location = "aboutmanager?action=delete&aboutId=" + $(this).attr("value");
                                    swal("Đã xóa thành công.!", {
                                        icon: "success",
                                    });
                                }
                            });
                });
            }));
        </script>
        <script>
            var myApp = new function () {
                this.printTable = function () {
                    var tab = document.getElementById('sampleTable');
                    var win = window.open('', '', 'height=700,width=700');
                    win.document.write(tab.outerHTML);
                    win.document.close();
                    win.print();
                }
            }
        </script>



</html>