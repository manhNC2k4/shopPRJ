/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
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
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import model.User;

/**
 *
 * @author LNV
 */
@WebServlet(name = "ChangePassServlet", urlPatterns = {"/changePass"})
public class ChangePassServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changePass.jsp").forward(request, response);
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
        Cookie[] cookies = request.getCookies();
        UserDTO userDTO = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    // Chuyển đổi chuỗi cookie thành UserDTO
                    String decodedUser = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    userDTO = new UserDTO(decodedUser);
                    break; // Dừng vòng lặp khi tìm thấy cookie
                }
            }
        }
        if (userDTO == null) {
            // Xử lý trường hợp người dùng chưa đăng nhập
            response.sendRedirect("login.jsp");
            return; // Kết thúc phương thức
        }
        String curPass = request.getParameter("currentPassword");
        String newPass = request.getParameter("newPassword");
        UserDAO ud = new UserDAO();
        LoginDAO ld = new LoginDAO();
        try {
            String email = userDTO.getEmail();
            String result = ld.login(email, curPass);
            if (result.equals("success")) {
                String hashPass = ld.hashPassword(newPass);
                User user = new User(userDTO.getUsername(), hashPass, userDTO.getEmail(),
                        userDTO.getFirst_name(), userDTO.getLast_name(), userDTO.getPhone(), userDTO.getAddress());
                String result2 = ud.updateUser(user, userDTO.getUser_id());
                if ("Update successful".equals(result2)) {
                    // Tạo lại cookie với thông tin cập nhật
                    String encodedUser = URLEncoder.encode(userDTO.toString(), "UTF-8");
                    Cookie cookie = new Cookie("account", encodedUser); // Giả sử UserDTO có toString()
                    cookie.setMaxAge(60 * 60 * 24 * 60 * 2); // Tồn tại 2 tháng (tính bằng giây)
                    response.addCookie(cookie);
                    response.sendRedirect("index");
                } else {
                    request.setAttribute("error", result2);
                    request.getRequestDispatcher("changePass.jsp").forward(request, response);
                }
            } else {
                String error = "Current password is wrong!";
                request.setAttribute("error", error);
                request.getRequestDispatcher("changePass.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
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
