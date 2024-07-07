/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dal.CategoryDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CateSaleData;
import model.Order;
import model.ProductSaleData;

/**
 *
 * @author LNV
 */
@WebServlet(urlPatterns = {"/dashBoard"})
public class DashBoard extends HttpServlet {

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
            out.println("<title>Servlet DashBoard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoard at " + request.getContextPath() + "</h1>");
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
        OrderDAO orderDAO = new OrderDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        Map<Integer, Double> monthlyRevenue = orderDAO.getTotalRevenueByMonthForYear();
        Map<Integer, List<Double>> revenueData = orderDAO.getTotalRevenueByWeekForYear();
        Map<Integer, Integer> monthlySale = orderDAO.getTotalProductsSoldByMonthForYear();
        Map<Integer, Integer> monthlyUserNew = userDAO.getTotalUsersCreatedPerMonthForCurrentYear();
        int countPending = orderDAO.countPendingOrders();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // Lấy tháng hiện tại (1-12)
        int previousMonth = (currentMonth - 2 + 12) % 12 + 1;
        List<ProductSaleData> listProduct = pd.getProductSaleDataTop5();
        List<Order> listOrder = orderDAO.getAllOrders();
        List<CateSaleData> listCateSaleData = cd.getCategorySalesData();
        Map<String, Double> namePercentageMap = new HashMap<>();
        for (CateSaleData data : listCateSaleData) {
            namePercentageMap.put(data.getName(), data.getPercentage());
        }
        
        request.setAttribute("namePercentageMap", namePercentageMap);
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("monthlyRevenue", monthlyRevenue);
        request.setAttribute("revenueData", revenueData);
        request.setAttribute("monthlyUserNew", monthlyUserNew);
        request.setAttribute("monthlySale", monthlySale);
        request.setAttribute("countPending", countPending);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("currentMonth", currentMonth);
        request.setAttribute("previousMonth", previousMonth);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
