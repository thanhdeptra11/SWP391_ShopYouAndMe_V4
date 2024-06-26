/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import dal.billDAO;
import dal.categoryDAO;
import dal.productDAO;
import model.Bill;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import model.Category;


public class Dashboard extends HttpServlet {

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
    response.setContentType("text/html;charset=UTF-8");

    try {
        HttpSession session = request.getSession();
        model.User user = (User) session.getAttribute("user");
        if (user.getIsAdmin().equalsIgnoreCase("true")) {
            productDAO dao = new productDAO();
            billDAO bdao = new billDAO();
            categoryDAO cdao = new categoryDAO();
            
            //pie chart
            List<Category> categoryList = cdao.getCategoryCounts();
            request.setAttribute("categoryList", categoryList);
            

            List<Bill> billbyday = bdao.getBillByDay();
            List<Double> paidList = new ArrayList<>();
            List<Double> unpaidList = new ArrayList<>();
            List<Date> dateList = new ArrayList<>();
            iiiii
            
            for (Bill bill : billbyday) {
                double totalPaid = bdao.getTotalPaidByDate(bill.getDate());
                double totalUnpaid = bdao.getTotalUnpaidByDate(bill.getDate());
                paidList.add(totalPaid);
                unpaidList.add(totalUnpaid);
                dateList.add(bill.getDate());
            }
            // Set các attribute để truyền vào biểu đồ
            request.setAttribute("dateList", dateList);
            request.setAttribute("paidList", paidList);
            request.setAttribute("unpaidList", unpaidList);
            // Retrieve start and end dates from request parameters
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            // If start or end date is not provided, set them to default values or retrieve them from previous request attributes
            if (startDate == null || startDate.isEmpty()) {
                startDate = "default_start_date"; // Replace with default start date or get from previous request attributes
            }
            if (endDate == null || endDate.isEmpty()) {
                endDate = "default_end_date"; // Replace with default end date or get from previous request attributes
            }

            // Get bills between the specified dates
            List<Bill> bills = bdao.getBillBetweenDates(startDate, endDate);           
            // Set other attributes as needed
            int count = dao.CountProduct();
            int countuser = dao.CountUser();
            int countbill = dao.CountBill();
            int countproductlow = dao.CountProductLow();
            
            request.setAttribute("product", count);
            request.setAttribute("user", countuser);
            request.setAttribute("bill", countbill);
            request.setAttribute("low", countproductlow);
            request.setAttribute("billbyday", billbyday);
            request.setAttribute("bills", bills); // Set bills attribute for displaying in JSP
            request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    } catch (Exception e) {
        response.sendRedirect("404.jsp");
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
