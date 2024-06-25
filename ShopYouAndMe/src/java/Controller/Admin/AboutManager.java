/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import dal.AboutDao;
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
import model.About;
import model.User;

/**
 *
 * @author Phung An
 */
@WebServlet(name = "AboutManager", urlPatterns = {"/aboutmanager"})
public class AboutManager extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        String page = "";
        String page1="";
        try {
            HttpSession session = request.getSession();
            model.User user = (User) session.getAttribute("user");

            if (user != null && user.getIsAdmin().equalsIgnoreCase("true")) {
                if (action == null || action.equals("")) {
                    AboutDao dao = new AboutDao();
                    List<About> list = dao.getAbout();
                    request.setAttribute("listabout", list);
                    page = "admin/m_about.jsp";
                    page1 = "about.jsp";
                } else if (action.equals("delete")) {
                    String aboutid = request.getParameter("aboutId");
                    AboutDao dao = new AboutDao();
                    dao.DeleteAbout(aboutid);
                    response.sendRedirect("aboutmanager");
                    return;
                } else if (action.equalsIgnoreCase("insert")) {
                    AboutDao c = new AboutDao();
                    List<About> about = c.getAbout();
                    request.setAttribute("About", about);
                    page = "admin/aboutinsert.jsp";
                } else if (action.equals("aboutinsert")) {
                    AboutDao dao = new AboutDao();
                    String abouttitle = request.getParameter("about_title");
                    String aboutcontent = request.getParameter("about_content");
                    String aboutimg = request.getParameter("about_img");
                    dao.addAbout(abouttitle, aboutcontent, aboutimg);
                    response.sendRedirect("aboutmanager");
                    return;
                } else if (action.equalsIgnoreCase("updateabout")) {
                    AboutDao dao = new AboutDao();
                    String aboutid = request.getParameter("aboutid");
                    String abouttitle = request.getParameter("title");
                    String aboutcontent = request.getParameter("content");
                    String aboutimg = "images/" + request.getParameter("about_img");
                    About about = new About();
                    about.setAboutId(aboutid);
                    about.setTitle(abouttitle);
                    about.setContent(aboutcontent);
                    about.setImg(aboutimg);
                    dao.UpdateAbout(about);
                    response.sendRedirect("aboutmanager");
                    return;
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
