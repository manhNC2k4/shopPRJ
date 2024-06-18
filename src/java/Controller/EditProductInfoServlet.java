/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author LNV
 */
@WebServlet(name = "EditProductInfoServlet", urlPatterns = {"/editProductInfo"})
public class EditProductInfoServlet extends HttpServlet {

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
            out.println("<title>Servlet EditProductInfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductInfoServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        List<Category> lists = cd.getALl();
        String rid = request.getParameter("id");
        try {
            int id = Integer.parseInt(rid);
            Product p = pd.getProductById(id);
            request.setAttribute("listCat", lists);
            request.setAttribute("product", p);
            request.getRequestDispatcher("editProductInfo.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String r_categoryId = request.getParameter("categoryId");
        String priceStr = request.getParameter("price");
        BigDecimal price;
        String message;
        try {
            price = new BigDecimal(priceStr);
            int categoryId = Integer.parseInt(r_categoryId);
            int id = Integer.parseInt(rid);
            ProductDAO pd = new ProductDAO();
            CategoryDAO cd = new CategoryDAO();
            message = pd.updateProduct(id, name, description, categoryId, price);
            Product p = pd.getProductById(id);
            Category c = cd.getCateById(p.getCategoryId());
            request.setAttribute("message", message);
            request.setAttribute("product", p);
            request.setAttribute("cate", c);
            request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            message = "Error: " + e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("editProductInfo.jsp").forward(request, response);
            
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
