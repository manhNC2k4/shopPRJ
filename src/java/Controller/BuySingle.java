/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.ProductDAO;
import dal.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import model.Product;
import model.Product_Size;
import model.Sale;

/**
 *
 * @author LNV
 */
@WebServlet(name = "BuySingle", urlPatterns = {"/buySingle"})
public class BuySingle extends HttpServlet {

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
            out.println("<title>Servlet BuySingle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuySingle at " + request.getContextPath() + "</h1>");
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
        String productIdParam = request.getParameter("productId");
        String quantityParam = request.getParameter("quantity");
        String sizeParam = request.getParameter("size");
        System.out.println("Received parameters: productId=" + productIdParam + ", quantity=" + quantityParam + ", size=" + sizeParam);
        
        try {
            // Chuyển đổi các tham số sang số nguyên
            int pid = Integer.parseInt(productIdParam);
            int quantity = Integer.parseInt(quantityParam);
            int size = Integer.parseInt(sizeParam);
            System.out.println("Parsed parameters: productId=" + pid + ", quantity=" + quantity + ", size=" + size);
            ProductDAO pd = new ProductDAO();
            Product product = pd.getProductById(pid);
            SaleDAO sd = new SaleDAO();
            if (product.getSale_id() != 0) {
                Sale sale = sd.getSaleById(product.getSale_id());
                Date currentDate = new Date();
                if ("active".equals(sale.getStatus()) && currentDate.after(sale.getStartDate()) && currentDate.before(sale.getEndDate())
                        || currentDate.equals(sale.getStartDate()) || currentDate.equals(sale.getEndDate())) {
                    if (sale.getDiscountType().equals("percentage")) {
                        BigDecimal discountValue = BigDecimal.valueOf(sale.getDiscountValue());
                        BigDecimal discountedPrice = BigDecimal.valueOf(100)
                                .subtract(discountValue)
                                .divide(BigDecimal.valueOf(100));
                        product.setPrice(product.getPrice().multiply(discountedPrice));
                    } else {
                        BigDecimal discountValue = BigDecimal.valueOf(sale.getDiscountValue());
                        product.setPrice(product.getPrice().subtract(discountValue));
                    }
                }
            }
            BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            request.getSession().setAttribute("total", total);
            int psid = pd.getProduct_Size_Id(product.getId(), size);
            request.getSession().setAttribute("psid", psid);
            request.getSession().setAttribute("quantity", quantity);
            request.getRequestDispatcher("checkoutSingle.jsp").forward(request, response);
        } catch (NumberFormatException e) {
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
