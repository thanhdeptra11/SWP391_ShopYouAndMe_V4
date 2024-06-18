<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>.bank_cards_content {
        text-align: center; /* Center-align the bank card images */
    }

    .bank_card_images {
        display: flex; /* Use flexbox for layout */
        justify-content: space-evenly; /* Evenly space the bank card images */
        align-items: center; /* Align bank card images vertically */
    }

    .bank-card {
        width: 150px; /* Set the width of each bank card */
        height: auto; /* Maintain aspect ratio */
        margin: 0 10px; /* Add margin between bank card images */
    }</style>
<footer class="footer_widgets">
    <div class="footer_top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="widgets_container contact_us">
                        <h3 style="font-size: 30px">Liên hệ chúng tôi</h3>
                        <div class="footer_contact">
                            <p style="font-size: 16px;">Địa chỉ: Huyện Tiền Hải - Tỉnh Thái Bình</p>
                            <p style="font-size: 16px;">Điện thoại: <a href="tel:+(+84)888195313" style="font-size: 16px;">0348956373</a> </p>
                            <p style="font-size: 16px;">Email: <a href="mailto:doanhtnhe172637@fpt.edu.vn" style="font-size: 16px;">doanhtnhe172637@fpt.edu.vn</a></p>
                            <ul>
                                <li><a href="https://twitter.com/?lang=vi" title="Twitter"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#" title="google-plus"><i class="fa fa-google"></i></a></li>
                                <li><a href="https://www.facebook.com/" title="Facebook"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="https://www.youtube.com/channel/UCL_FgBkk8fR3F5DkF4G8tfw" title="Youtube"><i class="fa fa-youtube"></i></a></li>
                            </ul>

                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="widgets_container bank_cards">
                        <h3 style="font-size: 30px">Thanh Toán</h3>
                        <div class="bank_cards_content">
                            <div class="bank_card_images">
                                <!-- Example bank card images -->
                                <img src="https://upload.wikimedia.org/wikipedia/commons/2/25/Logo_MB_new.png" alt="" class="bank-card" />
                                <img src="https://cdn.tuoitre.vn/thumb_w/1200/471584752817336320/2023/2/23/logo-vietcombank-inkythuatso-10-10-41-18-16771235759271889182462.jpg" alt="" class="bank-card" />
                                <img src="https://wikiland.vn/wp-content/uploads/Logo-TPBank-Sl.png" alt="" class="bank-card" />
                                <img src="https://image.bnews.vn/MediaUpload/Org/2022/04/26/logo-bidv-20220426071253.jpg" alt="" class="bank-card" />
                                <!-- Add more bank card images as needed -->
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <div class="map-area">                         
            <iframe id="googleMap" style="border: none;" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d119685.88087835716!2d106.46247258418202!3d20.375311160236567!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314a00977e6991b9%3A0xba8797b82f547bc6!2zVGnhu4FuIEjhuqNpLCBUaMOhaSBCw6xuaCwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1710175822544!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
</footer>
