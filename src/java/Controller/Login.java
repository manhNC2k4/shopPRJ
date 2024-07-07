/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.CartDAO;
import dal.FavoriteDAO;
import dal.LoginDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import model.Cart;

/**
 *
 * @author LNV
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            LoginDAO ld = new LoginDAO();
            String result = ld.login(email, password);
            if (result.equals("success")) {
                UserDAO ud = new UserDAO();
                UserDTO user = ud.getUserByMail(email);
                System.out.println(user.toString());

                // Tạo cookie với thời hạn 2 tháng
                String encodedUser = URLEncoder.encode(user.toString(), "UTF-8");
                Cookie cookie = new Cookie("account", encodedUser);
                cookie.setMaxAge(60 * 60 * 24 * 60); // 2 tháng (tính bằng giây)
                System.out.println(cookie);
                response.addCookie(cookie);

                if (user.getRole() == 1) {
                    // Chuyển hướng đến admin.jsp nếu role = 1
                    request.getRequestDispatcher("admin").forward(request, response);
                } else {
                    // Chuyển hướng đến index.jsp nếu role != 1
                    CartDAO cd = new CartDAO();
                    Cart c;
                    c = cd.getCartById(user.getUser_id());
                    if (c == null) {
                        int cid = cd.insertCart(user.getUser_id());
                        c = cd.getCartByCartId(cid);
                    }
                    Cookie cookie1 = new Cookie("cart", c.toString());
                    cookie1.setMaxAge(60 * 60 * 24 * 60);
                    response.addCookie(cookie1);

                    // Lấy số lượng sản phẩm yêu thích và tạo cookie cho nó
                    FavoriteDAO fd = new FavoriteDAO();
                    int favoriteCount = fd.countFavoritesByUserId(user.getUser_id());
                    System.out.println(favoriteCount);
                    Cookie favoriteCookie = new Cookie("favoriteCount", String.valueOf(favoriteCount));
                    favoriteCookie.setMaxAge(60 * 60 * 24 * 60);
                    response.addCookie(favoriteCookie);
                    
                    request.getRequestDispatcher("index").forward(request, response);
                }
            } else {
                request.setAttribute("error", result);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (ServletException | IOException e) {

            request.setAttribute("error", "An error occurred during login");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
