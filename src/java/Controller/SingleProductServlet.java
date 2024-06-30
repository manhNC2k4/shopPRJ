/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.CartDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import model.Cart;
import model.Category;
import model.Product;

/**
 *
 * @author LNV
 */
@WebServlet(name = "SingleProductServlet", urlPatterns = {"/singleProduct"})
public class SingleProductServlet extends HttpServlet {

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
            out.println("<title>Servlet SingleProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SingleProductServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        String rid = request.getParameter("id");
        int id;
        // Lấy thông tin user từ session hoặc cookie
        Cookie[] cookies = request.getCookies();
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
            // Lấy số lượng sản phẩm yêu thích của user
            CartDAO cartd = new CartDAO();
            Cart c;
            c = cartd.getCartById(user.getUser_id());
            Cookie cookie1 = new Cookie("cart", c.toString());
            cookie1.setMaxAge(60 * 60 * 24 * 60);
            response.addCookie(cookie1);
        }
        try {
            System.out.println("Received parameter (GET): id=" + rid);

            if (rid != null) {
                id = Integer.parseInt(rid);
                System.out.println("Parsed product ID (GET): " + id);
            } else {
                Object idAttribute = request.getAttribute("id");
                if (idAttribute instanceof Integer) {
                    id = (int) idAttribute;
                } else {
                    throw new NumberFormatException("Invalid or missing product ID");
                }
                System.out.println("Parsed product ID from attribute: " + id);
            }

            Product p = pd.getProductById(id);
            Category c = cd.getCateById(p.getCategoryId());

            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                System.out.println("Set message attribute (POST): " + message);
            }

            request.getSession().setAttribute("product", p);
            request.getSession().setAttribute("cate", c);
            response.sendRedirect("singleProduct.jsp");
        } catch (NumberFormatException ex) {
            System.err.println("NumberFormatException (POST): " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        } catch (IOException ex) {
            System.err.println("ServletException | IOException (POST): " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
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
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        String rid = request.getParameter("id");
        int id;
        // Lấy thông tin user từ session hoặc cookie
        Cookie[] cookies = request.getCookies();
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
            // Lấy số lượng sản phẩm yêu thích của user
            CartDAO cartd = new CartDAO();
            Cart c;
            c = cartd.getCartById(user.getUser_id());
            Cookie cookie1 = new Cookie("cart", c.toString());
            cookie1.setMaxAge(60 * 60 * 24 * 60);
            response.addCookie(cookie1);
        }
        try {
            System.out.println("Received parameter (POST): id=" + rid);

            if (rid != null) {
                id = Integer.parseInt(rid);
                System.out.println("Parsed product ID (POST): " + id);
            } else {
                Object idAttribute = request.getAttribute("id");
                if (idAttribute instanceof Integer) {
                    id = (int) idAttribute;
                } else {
                    throw new NumberFormatException("Invalid or missing product ID");
                }
                System.out.println("Parsed product ID from attribute: " + id);
            }

            Product p = pd.getProductById(id);
            Category c = cd.getCateById(p.getCategoryId());

            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                System.out.println("Set message attribute (POST): " + message);
            }

            request.getSession().setAttribute("product", p);
            request.getSession().setAttribute("cate", c);
            response.sendRedirect("singleProduct.jsp");
        } catch (NumberFormatException ex) {
            System.err.println("NumberFormatException (POST): " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        } catch (IOException ex) {
            System.err.println("ServletException | IOException (POST): " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
        }
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
