/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Home;

import dal.productDAO;
import model.Item;
import model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Iterator;

public class Cart extends HttpServlet {

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
       HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
         model.User user = (model.User) session.getAttribute("user");
        if (user != null && "TRUE".equalsIgnoreCase(user.getIsAdmin())) {
            session.setAttribute("errorMessage", "Quản trị viên không thể thêm hoặc mua sản phẩm.");
            response.sendRedirect("product-details.jsp"); 
            return;
        }
        if (action == null) {           
            model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            String Squantity = request.getParameter("quantity");
            String product_id = request.getParameter("product_id");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            
            try {
                int quanity = Integer.parseInt(Squantity);
                productDAO pdao = new productDAO();
                Product product = pdao.getProductByID(product_id);
                Item item = new Item(product, quanity, size, color);
                cart.addItem(item);
            } catch (Exception e) {
            }
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());
            request.getRequestDispatcher("product?action=productdetail&product_id=" + product_id).forward(request, response);
        }
        if (action.equalsIgnoreCase("showcart")) {
             model.Cart choosenCart = (model.Cart) request.getSession().getAttribute("choosenCart");
            if (choosenCart != null) {
                session.setAttribute("totalMoney", choosenCart.getTotalMoney());
            }
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        if (action.equals("deletecart")) {
            model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            model.Cart cartChoosen = (model.Cart) request.getSession().getAttribute("choosenCart");
            String product_id = request.getParameter("product_id");
            if (cartChoosen != null) {
                List<Item> list = cartChoosen.getItems();
                for (Iterator<Item> iterator = list.iterator(); iterator.hasNext();) {
                    Item value = iterator.next();
                    if (value.getProduct().getProduct_id().equals(product_id)) {
                        iterator.remove();
                    }
                }
                session.setAttribute("totalMoney", cartChoosen.getTotalMoney());
            }

            session.setAttribute("choosenCart", cartChoosen);
            cart.removeItem(product_id);
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());
            response.sendRedirect("cart.jsp");
            
             String Squantity = request.getParameter("quantity");
        //String product_id = request.getParameter("product_id");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        try {
            int quantity = Integer.parseInt(Squantity);
            productDAO pdao = new productDAO();
            Product product = pdao.getProductByID(product_id);
            Item item = new Item(product, quantity, size, color);
            cart.addItem(item);
        } catch (Exception e) {
            e.printStackTrace();
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
