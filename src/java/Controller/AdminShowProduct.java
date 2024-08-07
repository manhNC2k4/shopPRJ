/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import dal.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;
import model.Sale;

/**
 *
 * @author LNV
 */
@WebServlet(name="AdminShowProduct", urlPatterns={"/showProduct"})
public class AdminShowProduct extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AdminShowProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminShowProduct at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCat = cd.getALl();
        ProductDAO pd = new ProductDAO();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
        SaleDAO sd = new SaleDAO();
        try {
            List<Sale> listSale = sd.getActiveSales();
            request.setAttribute("listSale", listSale);
        } catch (SQLException ex) {
            Logger.getLogger(ListSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(request.getParameter("cid") != null) {
            String rid = request.getParameter("cid");
            try {
                int id = Integer.parseInt(rid);
                List<Product> list = pd.getProductsOf(id);
            request.setAttribute("categoryMap", categoryMap);
            request.setAttribute("dataCat", listCat);
            request.setAttribute("dataPro", list);
            request.getRequestDispatcher("listProducts.jsp").forward(request, response);
            } catch (NumberFormatException e) {
            }
        }
        request.setAttribute("categoryMap", categoryMap);
        List<Product> listPro = pd.getAllProduct();
        request.setAttribute("dataPro", listPro);
        request.setAttribute("dataCat", listCat);
        request.getRequestDispatcher("listProducts.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCat = cd.getALl();
        ProductDAO pd = new ProductDAO();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
         SaleDAO sd = new SaleDAO();
        try {
            List<Sale> listSale = sd.getActiveSales();
            request.setAttribute("listSale", listSale);
        } catch (SQLException ex) {
            Logger.getLogger(ListSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(request.getParameter("cid") != null) {
            String rid = request.getParameter("cid");
            try {
                int id = Integer.parseInt(rid);
                List<Product> list = pd.getProductsOf(id);
            request.setAttribute("categoryMap", categoryMap);
            request.setAttribute("dataCat", listCat);
            request.setAttribute("dataPro", list);
            request.getRequestDispatcher("listProducts.jsp").forward(request, response);
            } catch (NumberFormatException e) {
            }
        }
        
        List<Product> listPro = pd.getAllProduct();
        request.setAttribute("categoryMap", categoryMap);
        request.setAttribute("dataPro", listPro);
        request.setAttribute("dataCat", listCat);
        request.getRequestDispatcher("listProducts.jsp").forward(request, response);
   
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
