/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;

/**
 *
 * @author LNV
 */
@WebServlet(name = "DeleteCart", urlPatterns = {"/deleteCart"})
public class DeleteCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Cookie[] cookies = request.getCookies();
        Cart c = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    c = new Cart(cookie.getValue());
                    System.out.println("Cart cookie found: " + c);
                }
            }
        }
        if (c != null) {
            String rctid = request.getParameter("cid");
            String rquantity = request.getParameter("quantity");
            try {
                int cid = Integer.parseInt(rctid);
                int quantity = Integer.parseInt(rquantity);
                CartDAO cd = new CartDAO();
                if(c.getNums_items() == 0) {
                    String result0 = cd.updateCart(c.getId(), 0);
                }
                String result0 = cd.updateCart(c.getId(), c.getNums_items() - 1);
                if ("Update successfull!".equals(result0)) {
                    String result = cd.deleteCartItem(cid);
                    if ("Delete successful".equals(result)) {
                        // Lấy thông tin user từ session hoặc cookie
                        UserDTO user = null;
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("account")) {
                                    user = new UserDTO(cookie.getValue());
                                    break;
                                }
                            }
                        }
                        if (user != null) {
                            // Lấy số lượng sản phẩm torng giỏ hàng của user
                            CartDAO cartd = new CartDAO();
                            Cart c1;
                            c1 = cartd.getCartById(user.getUser_id());
                            Cookie cookie1 = new Cookie("cart", c1.toString());
                            cookie1.setMaxAge(60 * 60 * 24 * 60);
                            response.addCookie(cookie1);
                            request.getRequestDispatcher("cartShow").forward(request, response);
                        }
                    } else {
                        System.out.println("delete cart item is reject!");
                        request.getRequestDispatcher("cartShow").forward(request, response);
                    }
                } else {
                    System.out.println("update cart is reject!");
                    request.getRequestDispatcher("cartShow").forward(request, response);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("No cart found, redirecting to login.jsp");
            response.sendRedirect("login.jsp");
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
