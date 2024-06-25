/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import dal.billDAO;
import dal.categoryDAO;
import dal.commentRatingDAO;
import dal.productDAO;
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
import model.Bill;
import model.BillDetail;
import model.Category;
import model.Comment;
import model.Rating;
import model.User;

/**
 *
 * @author ZZ
 */
@WebServlet(name = "RatingCommentManager", urlPatterns = {"/ratingcommentmanager"})
public class RatingCommentManager extends HttpServlet {

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
            commentRatingDAO crDao = new commentRatingDAO();
            if (user.getIsAdmin().equalsIgnoreCase("true")) {
                if (action == null) {
                    List<Comment> comments = crDao.getAllComments();
                    List<Rating> ratings = crDao.getAllRatings();
                    request.setAttribute("comments", comments);
                    request.setAttribute("ratings", ratings);
                    page = "admin/rating_commnent.jsp";
                } else if (action.equals("deleteComment")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    crDao.deleteComment(id);
                    response.sendRedirect("ratingcommentmanager");
                    return;
                } else if (action.equals("deleteRating")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    crDao.deleteRating(id);
                    response.sendRedirect("ratingcommentmanager");
                    return;
                }
                // Add more actions as needed
            } else {
                response.sendRedirect("user?action=login");
                return;
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
