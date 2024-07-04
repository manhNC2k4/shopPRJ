/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.CartDAO;
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
import model.Cart_Item;
import model.Product;
import model.ProductInfo;
import model.Product_Size;

/**
 *
 * @author LNV
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/checkout"})
public class CheckOut extends HttpServlet {

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
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("checkout.jsp");
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
        String checkedItems = request.getParameter("orderIds");
        System.out.println(checkedItems + "aaaaaaaa");
        int totalBill = 0;
        CartDAO cd = new CartDAO();
        ProductDAO pd = new ProductDAO();
        request.getSession().removeAttribute("totalBill");
        request.getSession().removeAttribute("listOrder");
        if (checkedItems != null) {
            // Tách chuỗi thành mảng các ID
            String[] checkedItemIds = checkedItems.split(",");

            if (checkedItemIds != null && checkedItemIds.length > 0) {
                List<ProductInfo> productInfoList = new ArrayList<>();
                for (String itemId : checkedItemIds) {
                    try {

                        int id = Integer.parseInt(itemId);

                        Cart_Item catitem = cd.getCart_ItemById(id);
                        if (catitem == null) {
                            throw new Exception("Không tìm thấy sản phẩm trong giỏ hàng (ID: " + id + ")");
                        }
                        Product_Size ps = pd.getProduct_SizeById(catitem.getProduct_size_id());
                        if (ps == null) {
                            throw new Exception("Không tìm thấy kích thước sản phẩm (ID: " + catitem.getProduct_size_id() + ")");
                        }
                        Product product = pd.getProductById(ps.getProductId());
                        if (product == null) {
                            throw new Exception("Không tìm thấy sản phẩm (ID: " + ps.getProductId() + ")");
                        }
                        totalBill += product.getPrice().intValue() * catitem.getQuantity();
                        int quantity = catitem.getQuantity();
                        ProductInfo productInfo = new ProductInfo(product, ps, quantity, id);
                        productInfoList.add(productInfo);
                    } catch (Exception e) {
                        // Xử lý lỗi chung (ví dụ: lỗi database)
                        System.err.println("Lỗi: " + e.getMessage());
                        response.sendRedirect("cartShow?error=generalError");
                        return; // Thoát khỏi hàm sau khi xử lý lỗi
                    }
                }
                request.getSession().setAttribute("totalBill", totalBill);
                request.getSession().setAttribute("listOrder", productInfoList);
                response.sendRedirect("checkout.jsp");
            } else {
                String message = "Something was wrong";
                response.sendRedirect("cartShow");
            }
        } else {
            response.sendRedirect("cartShow?error=noItemSelected");
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
