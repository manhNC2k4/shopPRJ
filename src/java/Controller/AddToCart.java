/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.CartDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Cart_Item;

/**
 *
 * @author LNV
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet AddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
        // Kiểm tra nếu giỏ hàng tồn tại
        if (c != null) {
            try {
                // Kiểm tra và lấy giá trị tham số
                String productIdParam = request.getParameter("productId");
                String quantityParam = request.getParameter("quantity");
                String sizeParam = request.getParameter("size");

                System.out.println("Received parameters: productId=" + productIdParam + ", quantity=" + quantityParam + ", size=" + sizeParam);

                // Chuyển đổi các tham số sang số nguyên
                int pid = Integer.parseInt(productIdParam);
                int quantity = Integer.parseInt(quantityParam);
                int size = Integer.parseInt(sizeParam);

                System.out.println("Parsed parameters: productId=" + pid + ", quantity=" + quantity + ", size=" + size);

                CartDAO cd = new CartDAO();
                String updateCart = cd.updateCart(c.getId(), c.getNums_items() + quantity);
                System.out.println("Update cart status: " + updateCart);

                if ("Update successfull!".equals(updateCart)) {
//                    c = cd.getCartByCartId(c.getId());
//                    if (c != null) {
//                        Cookie cookie1 = new Cookie("cart", c.toString());
//                        cookie1.setMaxAge(60 * 60 * 24 * 60);
//                        response.addCookie(cookie1);
//                    } else {
//                        // Xử lý nếu không lấy được giỏ hàng mới từ CSDL
//                        System.out.println("Failed to retrieve updated cart from database.");
//                    }

                    ProductDAO pd = new ProductDAO();
                    int psid = pd.getProduct_Size_Id(pid, size);
                    Cart_Item ci = new Cart_Item(-1, c.getId(), psid, quantity);

                    int ciid = cd.addCartItem(ci);
                    System.out.println("Cart item added with ID: " + ciid);

                    request.getSession().setAttribute("message", updateCart);
                    request.setAttribute("id", pid);
                    request.getRequestDispatcher("singleProduct").forward(request, response);
                } else {
                    request.getSession().setAttribute("message", updateCart);
                    request.setAttribute("id", pid);
                    request.getRequestDispatcher("singleProduct").forward(request, response);
                }
            } catch (NumberFormatException ex) {
                Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, "Number format exception: " + ex.getMessage(), ex);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            } catch (SQLException ex) {
                Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, "SQL exception: " + ex.getMessage(), ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
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
