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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author LNV
 */
@WebServlet(name = "NewPassServlet", urlPatterns = {"/newPass"})
public class NewPassServlet extends HttpServlet {

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
            out.println("<title>Servlet NewPassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewPassServlet at " + request.getContextPath() + "</h1>");
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
        try {
            String rid = request.getParameter("id");
            request.setAttribute("userId", rid);
            request.getRequestDispatcher("newPass.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            String mess = "Something was wrong!";
            request.setAttribute("message", mess);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
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
        UserDAO ud = new UserDAO();
        LoginDAO ld = new LoginDAO();
        String rid = request.getParameter("userId");
        
        String newPass = request.getParameter("newPassword");
        try {
            int id = Integer.parseInt(rid);
            UserDTO userDTO = ud.getUserDTOById(id);
            String hashPass = ld.hashPassword(newPass);
            User user = new User(userDTO.getUsername(), hashPass, userDTO.getEmail(),
                    userDTO.getFirst_name(), userDTO.getLast_name(), userDTO.getPhone(), userDTO.getAddress());
            String result2 = ud.updateUser(user, userDTO.getUser_id());
            if ("Update successful".equals(result2)) {
                response.sendRedirect("listUser");
            } else {
                request.setAttribute("error", result2);
                request.getRequestDispatcher("newPass.jsp").forward(request, response);
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
