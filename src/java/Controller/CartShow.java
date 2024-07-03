/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Cart_Item;
import model.Category;
import model.Product;
import model.ProductInfo;
import model.Product_Size;

/**
 *
 * @author LNV
 */
@WebServlet(name = "CartShow", urlPatterns = {"/cartShow"})
public class CartShow extends HttpServlet {

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
            out.println("<title>Servlet CartShow</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartShow at " + request.getContextPath() + "</h1>");
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
        request.getSession().removeAttribute("productInfoList");
        Cookie[] cookies = request.getCookies();
        Cart c = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    c = new Cart(cookie.getValue());
                    System.out.println("Cart cookie found: " + c);
                }
            }
        }
        if (c != null) {
            CartDAO cd = new CartDAO();
            ProductDAO pd = new ProductDAO();
            List<Cart_Item> listItem = cd.getAllCartItemsByCartId(c.getId());
            if (listItem != null && !listItem.isEmpty()) {
                List<ProductInfo> productInfoList = new ArrayList<>();
                for (Cart_Item cat : listItem) {
                    Product_Size ps = pd.getProduct_SizeById(cat.getProduct_size_id());
                    Product product = pd.getProductById(ps.getProductId());
                    int quantity = cat.getQuantity();
                    int id = cat.getId();
                    ProductInfo productInfo = new ProductInfo(product, ps, quantity, id);
                    productInfoList.add(productInfo);
                }
                if (!productInfoList.isEmpty()) {
                    CategoryDAO cated = new CategoryDAO();
                    List<Category> listCat = cated.getALl();
                    Map<Integer, String> categoryMap = new HashMap<>();
                    for (Category category : listCat) {
                        categoryMap.put(category.getCategory_id(), category.getName());
                    }
                    request.getSession().setAttribute("categoryMap", categoryMap);
                    request.getSession().setAttribute("productInfoList", productInfoList);
                    response.sendRedirect("cart.jsp");
                } else {
                    System.out.println("No products in cart, redirecting to empty cart page");
                    request.getSession().setAttribute("productInfoList", productInfoList);
                    response.sendRedirect("cart.jsp");
                }
            } else {
                System.out.println("No items in cart, redirecting to empty cart page");
                response.sendRedirect("cart.jsp");
            }
        } else {
            System.out.println("No cart found, redirecting to login.jsp");
            response.sendRedirect("login.jsp");
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
        CartDAO cd = new CartDAO();
        ProductDAO pd = new ProductDAO();
        String[] checkedItemIds = request.getParameterValues("checkedItemIds");
        List<Integer> listItemChecked = new ArrayList<>();
        request.getSession().removeAttribute("total");
        request.getSession().removeAttribute("listItemChecked");
        int total = 0;

        if (checkedItemIds != null) {

            for (String itemId : checkedItemIds) {
                int id = Integer.parseInt(itemId);
                listItemChecked.add(id);
                Cart_Item catitem = cd.getCart_ItemById(id);
                Product_Size ps = pd.getProduct_SizeById(catitem.getProduct_size_id());
                Product product = pd.getProductById(ps.getProductId());
                total += product.getPrice().intValue() * catitem.getQuantity();
            }

        }
        request.getSession().setAttribute("total", total);
        request.getSession().setAttribute("listItemChecked", listItemChecked);

        request.getSession().removeAttribute("productInfoList");
        Cookie[] cookies = request.getCookies();
        Cart c = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    c = new Cart(cookie.getValue());
                    System.out.println("Cart cookie found: " + c);
                }
            }
        }
        if (c != null) {

            List<Cart_Item> listItem = cd.getAllCartItemsByCartId(c.getId());
            if (listItem != null && !listItem.isEmpty()) {
                List<ProductInfo> productInfoList = new ArrayList<>();
                for (Cart_Item cat : listItem) {
                    Product_Size ps = pd.getProduct_SizeById(cat.getProduct_size_id());
                    Product product = pd.getProductById(ps.getProductId());
                    int quantity = cat.getQuantity();
                    int id = cat.getId();
                    ProductInfo productInfo = new ProductInfo(product, ps, quantity, id);
                    productInfoList.add(productInfo);
                }
                if (!productInfoList.isEmpty()) {
                    CategoryDAO cated = new CategoryDAO();
                    List<Category> listCat = cated.getALl();
                    Map<Integer, String> categoryMap = new HashMap<>();
                    for (Category category : listCat) {
                        categoryMap.put(category.getCategory_id(), category.getName());
                    }
                    request.getSession().setAttribute("categoryMap", categoryMap);
                    request.getSession().setAttribute("productInfoList", productInfoList);
                    response.sendRedirect("cart.jsp");
                } else {
                    System.out.println("No products in cart, redirecting to empty cart page");
                    response.sendRedirect("cart.jsp");
                }
            } else {
                System.out.println("No items in cart, redirecting to empty cart page");
                response.sendRedirect("cart.jsp");
            }
        } else {
            System.out.println("No cart found, redirecting to login.jsp");
            response.sendRedirect("login.jsp");
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
