/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Image;

/**
 *
 * @author LNV
 */
@WebServlet(name = "DeleteImageProduct", urlPatterns = {"/deleteImage"})
public class DeleteImageProduct extends HttpServlet {

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
            out.println("<title>Servlet DeleteImageProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteImageProduct at " + request.getContextPath() + "</h1>");
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
        String rid = request.getParameter("id");
        ProductDAO pd = new ProductDAO();
        try {
            int id = Integer.parseInt(rid);
            List<Image> lists = pd.getAllImage(id);
            request.setAttribute("images", lists);
            request.setAttribute("id", id);
            request.getRequestDispatcher("deleteProductImage.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("404.html");
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
        String rid = request.getParameter("id");
        String[] rimgIds = request.getParameterValues("imageIds");
        ProductDAO pd = new ProductDAO();
        String message = "";
        int id;
        try {
            id = Integer.parseInt(rid);
            if (rimgIds != null) {
                for (String rimid : rimgIds) {
                    int imgid = Integer.parseInt(rimid);
                    message = pd.deleteImage(imgid);
                }
            }
            if (message.equals("Delete successfull!")) {
                request.setAttribute("id", id);
                request.getRequestDispatcher("updateProduct").forward(request, response);
            } else {
                request.setAttribute("message", message);
                List<Image> lists = pd.getAllImage(id);
                request.setAttribute("images", lists);
                request.setAttribute("id", id);
                request.getRequestDispatcher("deleteProductImage.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            System.out.println(e);
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
