/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Home;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.billDAO;
import model.Cart;
import model.User;
import java.io.IOException;
import VnPayCommon.Config;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.TimeZone;

public class Checkout extends HttpServlet {

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

    try {
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        User acc = (User) session.getAttribute("user");
        if (acc == null) {
            response.sendRedirect("user?action=login");
            return;
        }

        String paymentMethod = request.getParameter("payment_method");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        if (address == null || phone == null || paymentMethod == null) {
            request.getRequestDispatcher("404.jsp").forward(request, response);
            return;
        }

        String payment = null;
        switch (paymentMethod) {
            case "momo":
                payment = "MOMO";
                break;
            case "vnpay":
                payment = "VNPAY";
                break;
            case "cod":
                payment = "COD";
                break;
            default:
                request.getRequestDispatcher("404.jsp").forward(request, response);
                return;
        }

        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("404.jsp").forward(request, response);
            return;
        }

        billDAO dao = new billDAO();
        dao.addOrder(acc, cart, payment, address, phoneNumber);

        session.removeAttribute("cart");
        session.setAttribute("size", 0);

        switch (paymentMethod) {
            case "cod":
                response.sendRedirect("home");
                break;
            case "momo":
                model.Bill bill = dao.getBill();
                int total = Math.round(bill.getTotal());
                request.setAttribute("total", total);
                request.setAttribute("bill", bill);
                request.getRequestDispatcher("qrcode.jsp").forward(request, response);
                break;
            case "vnpay":
                request.getRequestDispatcher("VN_Pay/vn_pay.jsp").forward(request, response);
                break;
        }

    } catch (Exception e) {
        // Log the error for debugging
        e.printStackTrace();
        request.getRequestDispatcher("404.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        Object u = session.getAttribute("user");
        if (u != null) {
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect("user?action=login");
        }

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
