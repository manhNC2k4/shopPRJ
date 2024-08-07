/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.CartDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.Order_Info;
import model.ProductInfo;

/**
 *
 * @author LNV
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

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
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
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
        // Lấy dữ liệu từ form
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String country = request.getParameter("country");
        String street = request.getParameter("street");
        String street2 = request.getParameter("street2"); // Optional
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String rtotal = request.getParameter("total");
        String paymentMethod = request.getParameter("paymentMethod");
        if (street2 != null) {
            street = street + "-" + street2;
        }
        Order_Info orderInfo = new Order_Info(0, firstName, lastName, country, street, city, postcode, phone, email, paymentMethod, 0);
        try {
            int total = Integer.parseInt(rtotal);
            Cookie[] cookies = request.getCookies();
            Cart c = null;
            UserDTO user = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("account")) {
                        user = new UserDTO(cookie.getValue());
                    }
                    if (cookie.getName().equals("cart")) {
                        c = new Cart(cookie.getValue());
                        System.out.println("Cart cookie found: " + c);
                    }
                }
            }
            if (user != null && c != null) {
                int userId = user.getUser_id();
                Order order = new Order(0, userId, null, "Pending", total, null, null);
                List<OrderDetail> orderDetails = new ArrayList<>();
                List<ProductInfo> orderList = (List<ProductInfo>) request.getSession().getAttribute("listOrder");
                if (orderList == null) {
                    String error = "something was wrong";
                    request.setAttribute("message", error);
                    request.getRequestDispatcher("checkout").forward(request, response);
                }
                ProductDAO pd = new ProductDAO();
                for (ProductInfo pi : orderList) {
                    OrderDetail od = new OrderDetail(0, 0, pi.getQuantity(), pi.getProduct().getPrice(), pd.getProduct_Size_Id(pi.getProduct().getId(), pi.getSize().getSize()));
                    orderDetails.add(od);
                }

                OrderDAO orderdao = new OrderDAO();
                boolean check = orderdao.insertOrder(order, orderDetails, orderInfo);
                if (check) {
                    request.getSession().removeAttribute("listOrder");
                    CartDAO cartDAO = new CartDAO();
                    String result0 = cartDAO.updateCart(c.getId(), c.getNums_items() - orderList.size());
                    if ("Update successfull!".equals(result0)) {
                        for (ProductInfo pi : orderList) {
                            int id = pi.getCart_item_id();

                            String result = cartDAO.deleteCartItem(id);
                            if ("Delete successful".equals(result)) {
                                CartDAO cartd = new CartDAO();
                                Cart c1;
                                c1 = cartd.getCartById(user.getUser_id());
                                Cookie cookie1 = new Cookie("cart", c1.toString());
                                cookie1.setMaxAge(60 * 60 * 24 * 60);
                                response.addCookie(cookie1);
                            } else {
                                System.out.println("delete cart item is reject!");
                            }
                        }
                    } else {
                        System.out.println("update cart is reject!");
                    }
                    response.sendRedirect("cartShow");
                } else {
                    String error = "something was wrong";
                    System.out.println(error);
                    response.sendRedirect("checkout");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (NumberFormatException e) {
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
