/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Home;

import dal.productDAO;
import model.Category;
import model.Color;
import model.Size;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Product extends HttpServlet {

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
        String action = request.getParameter("action");
        if (action == null) {
            productDAO c = new productDAO();
            List<model.Product> productList = c.getProduct();
            List<Category> category = c.getCategory();
            int page, numperpage = 9;
            int size = productList.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<model.Product> product = c.getListByPage(productList, start, end);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
        }

        if (action.equalsIgnoreCase("listByCategory")) {
            String category_id = request.getParameter("category_id");
            int category_id1 = Integer.parseInt(category_id);
            productDAO c = new productDAO();
            List<model.Product> productList = c.getProductByCategory(category_id1);
            List<Category> category = c.getCategory();
            int page, numperpage = 9;
            int size = productList.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<model.Product> product = c.getListByPage(productList, start, end);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
        }

        if (action.equalsIgnoreCase("productdetail")) {
            String product_id = request.getParameter("product_id");
            productDAO c = new productDAO();
            List<model.Size> sizeList = c.getSizeByID(product_id);
            List<model.Color> colorList = c.getColorByID(product_id);
            model.Product product = c.getProductByID(product_id);
            int category_id = product.getCate().getCategory_id();
            List<model.Product> productByCategory = c.getProductByCategory(category_id);
            request.setAttribute("ProductData", product);
            request.setAttribute("SizeData", sizeList);
            request.setAttribute("ColorData", colorList);
            request.setAttribute("ProductByCategory", productByCategory);
            request.getRequestDispatcher("product-details.jsp").forward(request, response);
        }

        if (action.equals("sort")) {
            String type = request.getParameter("type");
            if (type.equals("low")) {
                productDAO c = new productDAO();
                List<model.Product> productList = c.getProductLow();
                List<Category> category = c.getCategory();
                int page, numperpage = 9;
                int size = productList.size();
                int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<model.Product> product = c.getListByPage(productList, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
            if (type.equals("high")) {
                productDAO c = new productDAO();
                List<model.Product> productList = c.getProductHigh();
                List<Category> category = c.getCategory();
                int page, numperpage = 9;
                int size = productList.size();
                int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<model.Product> product = c.getListByPage(productList, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
            if (type.equals("a-z")) {
                productDAO c = new productDAO();
                List<model.Product> productList = c.getProductAZ();
                List<Category> category = c.getCategory();
                int page, numperpage = 9;
                int size = productList.size();
                int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<model.Product> product = c.getListByPage(productList, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
        }

        if (action.equals("search")) {
            String text = request.getParameter("text");
            productDAO c = new productDAO();
            List<model.Product> productList = c.SearchAll(text);
            List<Category> category = c.getCategory();
            int page, numperpage = 9;
            int size = productList.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<model.Product> product = c.getListByPage(productList, start, end);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
        }

        //SearchByPrice
        if (action.equalsIgnoreCase("searchByPrice")) {
            productDAO dao = new productDAO();
            List<Category> category = dao.getCategory();
            String[] choose = request.getParameterValues("price");
            List<model.Product> list1 = dao.getProductByPrice(0, 50000);
            List<model.Product> list2 = dao.getProductByPrice(50000, 200000);
            List<model.Product> list3 = dao.getProductByPrice(200000, 500000);
            List<model.Product> list4 = dao.getProductByPrice(500000, 1000000);
            List<model.Product> list5 = dao.getProductByPrice(1000000);
            List<model.Product> list0 = dao.getProduct();
            List<model.Product> listc = new ArrayList<>();
            if (choose == null || choose.equals("") || choose.length == 0 || choose.length == 5) {
                request.setAttribute("ProductData", list0);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
            if (choose.length == 2) {
                if (choose[0].equals("0") && choose[1].equals("1")) {
                    listc.addAll(list1);
                    listc.addAll(list2);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("2")) {
                    listc.addAll(list1);
                    listc.addAll(list3);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("3")) {
                    listc.addAll(list1);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("4")) {
                    listc.addAll(list1);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("1") && choose[1].equals("2")) {
                    listc.addAll(list2);
                    listc.addAll(list3);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("1") && choose[1].equals("3")) {
                    listc.addAll(list2);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("1") && choose[1].equals("4")) {
                    listc.addAll(list2);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("2") && choose[1].equals("4")) {
                    listc.addAll(list3);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("3") && choose[1].equals("4")) {
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("2") && choose[1].equals("3")) {
                    listc.addAll(list3);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }

            }
            if (choose.length == 4) {
                if (!choose[0].equals("0") && !choose[1].equals("0") && !choose[2].equals("0") && !choose[3].equals("0")) {
                    listc.addAll(list2);
                    listc.addAll(list3);
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (!choose[0].equals("1") && !choose[1].equals("1") && !choose[2].equals("1") && !choose[3].equals("1")) {
                    listc.addAll(list1);
                    listc.addAll(list3);
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (!choose[0].equals("2") && !choose[1].equals("2") && !choose[2].equals("2") && !choose[3].equals("2")) {
                                       listc.addAll(list1);
                    listc.addAll(list2);
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (!choose[0].equals("3") && !choose[1].equals("3") && !choose[2].equals("3") && !choose[3].equals("3")) {
                     listc.addAll(list1);
                    listc.addAll(list2);
                    listc.addAll(list3);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (!choose[0].equals("4") && !choose[1].equals("4") && !choose[2].equals("4") && !choose[3].equals("4")) {
                    listc.addAll(list1);
                    listc.addAll(list2);
                    listc.addAll(list3);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
            }
            if (choose.length == 3) {
                if (choose[0].equals("0") && choose[1].equals("1") && choose[2].equals("2")) {
                    listc.addAll(list1);
                    listc.addAll(list2);
                    listc.addAll(list3);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("1") && choose[2].equals("3")) {
                    listc.addAll(list1);
                    listc.addAll(list2);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("1") && choose[2].equals("4")) {
                    listc.addAll(list1);
                    listc.addAll(list2);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("2") && choose[2].equals("3")) {
                    listc.addAll(list1);
                    listc.addAll(list3);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("2") && choose[2].equals("4")) {
                    listc.addAll(list1);
                    listc.addAll(list3);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("0") && choose[1].equals("3") && choose[2].equals("4")) {
                    listc.addAll(list1);
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("1") && choose[1].equals("2") && choose[2].equals("3")) {
                    listc.addAll(list2);
                    listc.addAll(list3);
                    listc.addAll(list4);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("1") && choose[1].equals("2") && choose[2].equals("4")) {
                    listc.addAll(list2);
                    listc.addAll(list3);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("1") && choose[1].equals("3") && choose[2].equals("4")) {
                    listc.addAll(list2);
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose[0].equals("2") && choose[1].equals("3") && choose[2].equals("4")) {
                    listc.addAll(list3);
                    listc.addAll(list4);
                    listc.addAll(list5);
                    request.setAttribute("ProductData", listc);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
            }
                if (choose.length == 1) {
                    if (choose[0].equals("0")) {
                        request.setAttribute("ProductData", list1);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("1")) {
                        request.setAttribute("ProductData", list2);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("2")) {
                        request.setAttribute("ProductData", list3);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("3")) {
                        request.setAttribute("ProductData", list4);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("4")) {
                        request.setAttribute("ProductData", list5);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("ProductData", list0);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
            
        }
            //Search By Color
            if (action.equalsIgnoreCase("SearchByColor")) {
                productDAO dao = new productDAO();
                String[] choose = request.getParameterValues("colors");
                List<model.Product> list1 = dao.getProductColor("Red");
                List<model.Product> list2 = dao.getProductColor("Blue");
                List<model.Product> list3 = dao.getProductColor("White");
                List<model.Product> list4 = dao.getProductColor("Black");
                List<model.Product> list0 = dao.getProduct();
                List<model.Product> listp = new ArrayList<>();
                if (choose == null || choose.equals("") || choose.length == 0 || choose.length == 4) {
                    request.setAttribute("ProductData", list0);
                    request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                }
                if (choose.length == 3) {
                    if (!choose[0].equals("0") && !choose[1].equals("0") && !choose[2].equals("0")) {
                        listp.addAll(list2);
                        listp.addAll(list3);
                        listp.addAll(list4);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (!choose[0].equals("1") && !choose[1].equals("1") && !choose[2].equals("1")) {
                        listp.addAll(list1);
                        listp.addAll(list3);
                        listp.addAll(list4);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (!choose[0].equals("2") && !choose[1].equals("2") && !choose[2].equals("2")) {
                        listp.addAll(list1);
                        listp.addAll(list2);
                        listp.addAll(list4);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (!choose[0].equals("3") && !choose[1].equals("3") && !choose[2].equals("3")) {
                        listp.addAll(list1);
                        listp.addAll(list3);
                        listp.addAll(list2);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                }
                if (choose.length == 2) {
                    if (choose[0].equals("0") && choose[1].equals("1")) {
                        listp.addAll(list1);
                        listp.addAll(list2);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("0") && choose[1].equals("2")) {
                        listp.addAll(list1);
                        listp.addAll(list3);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("0") && choose[1].equals("3")) {
                        listp.addAll(list1);
                        listp.addAll(list4);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("1") && choose[1].equals("2")) {
                        listp.addAll(list2);
                        listp.addAll(list3);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("1") && choose[1].equals("3")) {
                        listp.addAll(list2);
                        listp.addAll(list4);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("2") && choose[1].equals("3")) {
                        listp.addAll(list3);
                        listp.addAll(list4);
                        request.setAttribute("ProductData", listp);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                }
                if (choose.length == 1) {
                    if (choose[0].equals("0")) {
                        request.setAttribute("ProductData", list1);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("1")) {
                        request.setAttribute("ProductData", list2);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("2")) {
                        request.setAttribute("ProductData", list3);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
                    }
                    if (choose[0].equals("3")) {
                        request.setAttribute("ProductData", list4);
                        request.getRequestDispatcher("shop_category.jsp").forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>
    }
