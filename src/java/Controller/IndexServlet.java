/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.UserDTO;
import dal.CategoryDAO;
import dal.FavoriteDAO;
import dal.GetProductDAO;
import dal.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

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
            out.println("<title>Servlet IndexServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexServlet at " + request.getContextPath() + "</h1>");
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
        List<Category> listCat = cd.getALl();
        GetProductDAO gpd = new GetProductDAO();
        List<Product> listPro = gpd.getLatestProducts(8);
        gpd.fetchImagesForProducts(listPro);
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
        request.getSession().setAttribute("categoryMap", categoryMap);
        request.getSession().setAttribute("dataPro", listPro);
        SaleDAO sd = new SaleDAO();
        try {
            List<Sale> listSale = sd.getAllSales();
            request.getSession().setAttribute("listSale", listSale);
        } catch (SQLException ex) {
            Logger.getLogger(ListSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Lấy thông tin user từ session hoặc cookie
        Cookie[] cookies = request.getCookies();
        UserDTO user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    user = new UserDTO(cookie.getValue());
                    break;
                }
            }
        }

        if (user != null) {
            // Lấy số lượng sản phẩm yêu thích của user
            FavoriteDAO favoriteDAO = new FavoriteDAO();
            int countFavorite = favoriteDAO.countFavoritesByUserId(user.getUser_id());

            // Tạo cookie với thời hạn 2 tháng
            Cookie favoriteCookie = new Cookie("favoriteCount", String.valueOf(countFavorite));
            favoriteCookie.setMaxAge(60 * 60 * 24 * 60); // 2 tháng (tính bằng giây)
            response.addCookie(favoriteCookie);
        }

        response.sendRedirect("index.jsp");
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
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCat = cd.getALl();
        GetProductDAO gpd = new GetProductDAO();
        List<Product> listPro = gpd.getLatestProducts(8);
        gpd.fetchImagesForProducts(listPro);
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
        request.getSession().setAttribute("categoryMap", categoryMap);
        request.getSession().setAttribute("dataPro", listPro);
        SaleDAO sd = new SaleDAO();
        try {
            List<Sale> listSale = sd.getAllSales();
            request.getSession().setAttribute("listSale", listSale);
        } catch (SQLException ex) {
            Logger.getLogger(ListSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Lấy thông tin user từ session hoặc cookie
        Cookie[] cookies = request.getCookies();
        UserDTO user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    user = new UserDTO(cookie.getValue());
                    break;
                }
            }
        }

        if (user != null) {
            // Lấy số lượng sản phẩm yêu thích của user
            FavoriteDAO favoriteDAO = new FavoriteDAO();
            int countFavorite = favoriteDAO.countFavoritesByUserId(user.getUser_id());

            // Tạo cookie với thời hạn 2 tháng
            Cookie favoriteCookie = new Cookie("favoriteCount", String.valueOf(countFavorite));
            favoriteCookie.setMaxAge(60 * 60 * 24 * 60); // 2 tháng (tính bằng giây)
            response.addCookie(favoriteCookie);
        }

        response.sendRedirect("index.jsp");
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
