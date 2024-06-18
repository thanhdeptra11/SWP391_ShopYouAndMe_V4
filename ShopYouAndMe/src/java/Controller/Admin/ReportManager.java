/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import dal.categoryDAO;
import dal.productDAO;
import dal.reportDAO;
import dal.userDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Report;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "reportManager", urlPatterns = {"/reportmanager"})
public class ReportManager extends HttpServlet {

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
        String page = "";
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String action = request.getParameter("action");
            if (user.getIsAdmin().equalsIgnoreCase("true")) {
                if (action == null) {
                    userDAO cdao = new userDAO();
                    reportDAO rdao = new reportDAO();
                    List<Report> reports = rdao.getAll();
                    List<User> users = cdao.getUser();
                    session.setAttribute("Reports", reports);
                    request.setAttribute("users", users);
                    page = "admin/report.jsp";
                } else if (action.equalsIgnoreCase("insertcategory")) {
                    productDAO c = new productDAO();
                    List<Category> category = c.getCategory();
                    request.setAttribute("CategoryData", category);
                    String name = request.getParameter("name");
                    if (name != null && !name.isEmpty()) {
                        productDAO dao = new productDAO();
                        Category d = dao.getCategoryByName(name);
                        if (d != null) {
                            request.setAttribute("error", name + " already exists.");
                            page = "admin/category.jsp";
                        } else {
                            dao.insertCategory(name);
                            response.sendRedirect("categorymanager");
                            return;
                        }
                    } else {
                        request.setAttribute("error", "Category name cannot be empty.");
                        page = "admin/category.jsp";
                    }
                } else if (action.equalsIgnoreCase("updatecategory")) {
                    String category_id = request.getParameter("category_id");
                    String category_name = request.getParameter("category_name");

                    if (category_id != null && category_name != null && !category_id.isEmpty() && !category_name.isEmpty()) {
                        try {
                            int categoryId = Integer.parseInt(category_id);
                            categoryDAO dao = new categoryDAO();
                            dao.updateCategory(categoryId, category_name);
                            response.sendRedirect("categorymanager");
                            return;
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "Invalid category ID format.");
                        } catch (Exception e) {
                            request.setAttribute("error", "An error occurred while updating the category.");
                        }
                    } else {
                        // Handle validation errors or missing parameters
                        request.setAttribute("error", "Category ID and Name cannot be empty.");
                    }
                    // Forward back to the category manager page with an error message
                    page = "admin/category.jsp"; // Set the destination page

                    RequestDispatcher dd = request.getRequestDispatcher(page);
                    dd.forward(request, response);

                } else if (action.equalsIgnoreCase("delete")) {
                    String category_id = request.getParameter("category_id");
                    int id = Integer.parseInt(category_id);
                    categoryDAO dao = new categoryDAO();
                    dao.deleteCategory(id);
                    response.sendRedirect("categorymanager");
                    return;
                }else if (action.equalsIgnoreCase("insert")) {
                    reportDAO dao = new reportDAO();
                   String user_id = request.getParameter("user_id");
                   String user_email = request.getParameter("user_email");
                   String subject_report = request.getParameter("subject_report");
                   String content_report = request.getParameter("content_report");
                   dao.InsertReport(user_id, content_report, subject_report, user_email);
                   session.setAttribute("msgc", "Bạn đã phản hồi thành công");
                   response.sendRedirect("contact.jsp");
                }
            }
        } catch (Exception e) {
            page = "404.jsp";
        }
        RequestDispatcher dd = request.getRequestDispatcher(page);
        dd.forward(request, response);

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