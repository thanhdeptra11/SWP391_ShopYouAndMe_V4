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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

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
            </ul>
        </aside>
        <main class="app-content">


            <div class="row">
                <div class="col-md-12">
                    <div class="app-title">
                        <ul class="app-breadcrumb breadcrumb">
                            <!--                            <li class="breadcrumb-item"><a href="#">Bảng Thống kê<b></b></a></li>-->
                        </ul>
                        <div id="piechart" style="width: 900px; height: 500px;"></div>
                        <div id="curve_chart" style="width: 100px; height: 200px"></div>
                        <div id="clock"></div>
                        <canvas id="lineChartDemo" width="400" height="200"></canvas>
                        <div
                            id="myChart" style="width:100%; max-width:600px; height:500px;">
                        </div>

                    </div>
                </div>
            </div>          
            <div class="row">             
            </div>
            <div class="col-md-12 col-lg-12">
                <div class="row">
                    <!-- col-6 -->
                    <div class="col-md-6">
                        <div class="widget-small primary coloured-icon"><i class='icon bx bxs-user-account fa-3x'></i>
                            <div class="info">
                                <h4>Tổng khách hàng</h4>
                                <p><b>${requestScope.user} khách hàng</b></p>
                                <p class="info-tong">Tổng số khách hàng được quản lý.</p>
                            </div>
                        </div>
                    </div>
                    <!-- col-6 -->
                    <div class="col-md-6">
                        <div class="widget-small info coloured-icon"><i class='icon bx bxs-data fa-3x'></i>
                            <div class="info">
                                <h4>Tổng sản phẩm</h4>
                                <p><b>${requestScope.product} sản phẩm</b></p>
                                <p class="info-tong">Tổng số sản phẩm được quản lý.</p>
                            </div>
                        </div>
                    </div>
                    <!-- col-6 -->
                    <div class="col-md-6">
                        <div class="widget-small warning coloured-icon"><i class='icon bx bxs-shopping-bags fa-3x'></i>
                            <div class="info">
                                <h4>Tổng đơn hàng</h4>
                                <p><b>${requestScope.bill} đơn hàng</b></p>
                                <p class="info-tong">Tổng số hóa đơn bán hàng trong tháng.</p>
                            </div>
                        </div>
                    </div>
                    <!-- col-6 -->
                    <div class="col-md-6">
                        <div class="widget-small danger coloured-icon"><i class='icon bx bxs-error-alt fa-3x'></i>
                            <div class="info">
                                <h4>Sắp hết hàng</h4>
                                <p><b>${requestScope.low} sản phẩm</b></p>
                                <p class="info-tong">Số sản phẩm cảnh báo hết cần nhập thêm.</p>
                            </div>
                        </div>
                    </div>
                    <!-- col-12 -->
                    <div class="col-md-12">
                        <div class="tile">
                            <h3 class="tile-title">Đơn hàng</h3>
                            <div>
                                <!-- Add date input fields for selecting start and end dates -->
                                <form action="dashboard" method="GET">
                                    <input type="date" id="startDate" name="startDate">
                                    <input type="date" id="endDate" name="endDate">
                                    <button type="submit">Tìm Kiếm</button>
                                </form>

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>ID đơn hàng</th>
                                            <th>Khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Địa chỉ</th>
                                            <th>Ngày mua</th>
                                            <th>Tổng tiền</th>
                                            <th>Thanh Toán</th>
                                            <th>Chức năng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${bills}" var="b">
                                            <tr>
                                                <td>${b.bill_id}</td>
                                                <td>${b.user.user_name}</td>
                                                <td>0${b.phone}</td>
                                                <td>${b.address}</td>
                                                <td>${b.date}</td>
                                                <td>${b.total} Vnđ</td>
                                                <td><span class="badge bg-success">${b.payment}</span></td>                                  
                                                <td><a style=" color: rgb(245 157 57);background-color: rgb(251 226 197); padding: 5px;border-radius: 5px;" href="ordermanager?action=showdetail&bill_id=${b.bill_id}"><i class="fa"></i>Chi tiết đơn hàng</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <!-- / div trống-->
                        </div>
                    </div>
                    <!-- / col-12 -->
                </div>
            </div>
        </div>


        <div class="text-center" style="font-size: 13px">              
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
    
 

    

    <!--        <script type="text/javascript">
                //Thời Gian
                function time() {
                    var today = new Date();
                    var weekday = new Array(7);
                    weekday[0] = "Chủ Nhật";
                    weekday[1] = "Thứ Hai";
                    weekday[2] = "Thứ Ba";
                    weekday[3] = "Thứ Tư";
                    weekday[4] = "Thứ Năm";
                    weekday[5] = "Thứ Sáu";
                    weekday[6] = "Thứ Bảy";
                    var day = weekday[today.getDay()];
                    var dd = today.getDate();
                    var mm = today.getMonth() + 1;
                    var yyyy = today.getFullYear();
                    var h = today.getHours();
                    var m = today.getMinutes();
                    m = checkTime(m);
                    nowTime = h + ":" + m + "";
                    if (dd < 10) {
                        dd = '0' + dd
                    }
                    if (mm < 10) {
                        mm = '0' + mm
                    }
                    today = day + ', ' + dd + '/' + mm + '/' + yyyy;
                    tmp = '<span class="date"> ' + today + ' - ' + nowTime +
                            '</span>';
                    document.getElementById("clock").innerHTML = tmp;
                    clocktime = setTimeout("time()", "1000", "Javascript");
    
                    function checkTime(i) {
                        if (i < 10) {
                            i = "0" + i;
                        }
                        return i;
                    }
                }
            </script>
    -->        <script type="text/javascript">
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


</body>

</html>