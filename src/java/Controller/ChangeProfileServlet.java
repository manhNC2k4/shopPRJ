/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.UserDAO;
import dal.registerDAO;
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
@WebServlet(name = "ChangeProfileServlet", urlPatterns = {"/changeProfile"})
public class ChangeProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangeProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeProfileServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("userUpdateProfile.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        UserDAO ud = new UserDAO();
        registerDAO rd = new registerDAO();
        String error;
        try {
            if (userDTO == null) {
                response.sendRedirect("login.jsp");
            } else {
                User user = ud.getUserById(userDTO.getUser_id());
                if (user == null) {
                    error = "Server Error. Can not found user from id.";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("userUpdateProfile.jsp").forward(request, response);
                } else {
                    String message = "";
                    if (!username.equals(user.getName()) && rd.isExistingUsername(username)) {
                        message = "Username is exist!";
                    } else if (!email.equals(user.getEmail()) && rd.isExistingEMail(email)) {
                        message = "Email is exist!";
                    } else if (!phone.equals(user.getPhone()) && rd.isExistingPhone(phone)) {
                        message = "Phone is exist!";
                    }

                    if (message.equals("")) {
                        try {
                            User newUser = new User(username, user.getPassword(), email, first_name, last_name, phone, address);
                            String result = ud.updateUser(newUser, userDTO.getUser_id());
                            if (result.equals("Update successful")) {
                                UserDTO newDTO = ud.getUserDTOById(userDTO.getUser_id());
                                // Tạo lại cookie với thông tin cập nhật
                                String encodedUser = URLEncoder.encode(newDTO.toString(), "UTF-8");
                                Cookie cookie = new Cookie("account", encodedUser); // Giả sử UserDTO có toString()
                                cookie.setMaxAge(60 * 60 * 24 * 60 * 2); // Tồn tại 2 tháng (tính bằng giây)
                                response.addCookie(cookie);
                                response.sendRedirect("viewProfile.jsp");
                            } else {
                                message = result; // Lấy message lỗi từ hàm insertUser
                                request.setAttribute("error", message);
                                request.getRequestDispatcher("userUpdateProfile.jsp").forward(request, response);
                            }
                        } catch (IOException e) {
                            message = "Something was wrong!";
                            request.setAttribute("error", message);
                            request.getRequestDispatcher("userUpdateProfile.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("error", message);
                        request.getRequestDispatcher("userUpdateProfile.jsp").forward(request, response);
                    }

                }
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
