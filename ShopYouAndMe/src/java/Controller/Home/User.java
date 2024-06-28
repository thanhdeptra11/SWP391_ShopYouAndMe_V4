/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Home;

import Controller.Authentication.SendEmail;
import Controller.Authentication.UserC;
import Controller.Authentication.VerifyRecaptcha;
import dal.billDAO;
import dal.userDAO;
import model.BillDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (action.equals("checkLogin")) {
            String user_email = request.getParameter("user_email");
            String user_pass = request.getParameter("user_pass");
            String remember = request.getParameter("remember");
            userDAO dao = new userDAO();
            model.User user = dao.checkUser(user_email, user_pass);
            if (user == null) {
                request.setAttribute("error", "Tài khoản không tồn tại !");
                request.getRequestDispatcher("user?action=login").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                Cookie email = new Cookie("email", user_email);
                Cookie pass = new Cookie("pass", user_pass);
                Cookie rem = new Cookie("remember", remember);
                if (remember != null) {
                    email.setMaxAge(60 * 60 * 24 * 30);
                    pass.setMaxAge(60 * 60 * 24 * 3);
                    rem.setMaxAge(60 * 60 * 24 * 30);
                } else {
                    email.setMaxAge(0);
                    pass.setMaxAge(0);
                    rem.setMaxAge(0);
                }
                response.addCookie(email);
                response.addCookie(pass);
                response.addCookie(rem);
                response.sendRedirect("home");
            }
        }

        if (action.equals("logout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            response.sendRedirect("home");
        }

        if (action.equals("myaccount")) {
            try {
                HttpSession session = request.getSession();
                model.User user = (model.User) session.getAttribute("user");
                if (user != null) {
                    int user_id = user.getUser_id();
                    billDAO dao = new billDAO();
                    List<model.Bill> bill = dao.getBillByID(user_id);
                    request.setAttribute("bill", bill);
                    request.getRequestDispatcher("my-account.jsp").forward(request, response);
                } else {
                    response.sendRedirect("user?action=login");
                }
            } catch (Exception e) {
                response.sendRedirect("user?action=login");
            }
        }
        if (action.equals("showdetail")) {
            String bill_id = request.getParameter("bill_id");
            int id = Integer.parseInt(bill_id);
            billDAO dao = new billDAO();
            List<BillDetail> detail = dao.getDetail(id);
            request.setAttribute("detail", detail);
            request.getRequestDispatcher("billdetail.jsp").forward(request, response);
        }

        if (action.equals("updateinfo")) {
            try {
                HttpSession session = request.getSession();
                model.User user = (model.User) session.getAttribute("user");
                if (user != null) {
                    String user_name = request.getParameter("user_name");
                    String user_pass = request.getParameter("user_pass");
                    String dateOfBirth = request.getParameter("dateOfBirth");
                    String address = request.getParameter("address");
                    String phoneNumber = request.getParameter("phoneNumber");

                    // Kiểm tra ngày sinh có nhỏ hơn ngày hiện tại không
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date dob = sdf.parse(dateOfBirth);
                        Date currentDate = new Date();
                        if (dob.after(currentDate)) {
                            // Ngày sinh lớn hơn ngày hiện tại
                            request.setAttribute("error_dob", "Ngày sinh không được lớn hơn ngày hiện tại");
                            request.getRequestDispatcher("user?action=myaccount").forward(request, response);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        // Xử lý lỗi khi chuyển đổi chuỗi ngày sinh

                    }

                    // Kiểm tra mật khẩu có chữ cái đầu viết hoa và chứa ít nhất 1 số hay không
                    boolean isValidPassword = false;
                    if (user_pass != null && !user_pass.isEmpty()) {
                        boolean hasUpperCase = !user_pass.equals(user_pass.toLowerCase());
                        boolean hasNumber = user_pass.matches(".*\\d.*");
                        isValidPassword = hasUpperCase && hasNumber;
                    }

                    if (isValidPassword) {
                        int user_id = user.getUser_id();
                        userDAO dao = new userDAO();
                        dao.updateUser(user_id, user_name, user_pass, dateOfBirth, address, phoneNumber);
                        model.User user1 = new model.User(user.getUser_id(), user_name, user.getUser_email(), user_pass, user.getIsAdmin(), dateOfBirth, address, phoneNumber);
                        session.setAttribute("user", user1);
                        response.sendRedirect("user?action=myaccount");
                    } else {
                        request.setAttribute("error_pass", "Mật khẩu chữ cái đầu phải viết hoa");
                        request.getRequestDispatcher("user?action=myaccount").forward(request, response);
                        // Xử lý khi mật khẩu không hợp lệ
                        // Ví dụ: hiển thị thông báo lỗi, yêu cầu nhập lại...
                    }
                } else {
                    response.sendRedirect("user?action=login");
                }
            } catch (Exception e) {
                response.sendRedirect("user?action=login");
            }
        }

        if (action.equals("signup")) {
            userDAO da = new userDAO();
            String email = request.getParameter("user_email");
            String pass = request.getParameter("user_pass");
            String repass = request.getParameter("re_pass");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            System.out.println(gRecaptchaResponse);
             boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";
            if (!pass.matches(passwordRegex)) {
                request.setAttribute("error_match", "Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất một chữ cái viết hoa và một chữ số");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            if (!verify) {
                request.setAttribute("Recaptcha", "Vui lòng xác nhận mã captcha");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            if (!pass.equals(repass)) {
                request.setAttribute("error_rePass", "Vui lòng nhập lại mật khẩu cho đúng");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                model.User a = da.checkAcc(email);
                if (a == null) {
                    // Check verify
                    SendEmail sm = new SendEmail();
                    String code = sm.getRandom();
                    UserC userc = new UserC(code, email);
                    boolean test = sm.sendEmail1(userc);
                    if (test == true && userc != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userc", userc);
                        session.setAttribute("email", email);
                        session.setAttribute("pass", pass);
                        response.sendRedirect("verify.jsp");
                        return;
                    }
                } else {
                    HttpSession session = request.getSession();
                    String msg = "Email đã tồn tại";
                    session.setAttribute("msg", msg);
                    response.sendRedirect("user?action=login");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
