/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.CategoryDAO;
import dal.GetProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;

/**
 *
 * @author LNV
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int PRODUCTS_PER_PAGE = 9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // init size
        String[] sizes = request.getParameterValues("size");
        List<Integer> sizeInt = new ArrayList<>();
//        int size = 0;
//        if (request.getParameter("size")!= null) {
//            size = Integer.parseInt(request.getParameter("size"));
//        }
        //init cid
        int cid = 0;
        if (request.getParameter("cid") != null) {
            cid = Integer.parseInt(request.getParameter("cid"));
        }
//        //init sort name
        int sort = 0;
        if (request.getParameter("sort") != null) {
            sort = Integer.parseInt(request.getParameter("sort"));
        }
//        // init price from
//        int priceFrom = 0;
//        if (request.getParameter("priceFrom") != null) {
//            priceFrom = Integer.parseInt(request.getParameter("priceFrom"));
//        }
//        // init price to
//        int priceTo = 0;
//        if (request.getParameter("priceTo") != null) {
//            priceTo = Integer.parseInt(request.getParameter("priceTo"));
//        }

        //init page
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Product> products;
        GetProductDAO gpd = new GetProductDAO();
        int totalProducts;
        if (sizes == null) {
            if (cid != 0) {
                totalProducts = gpd.getTotalProductsByCate(cid);
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid);
                    case 1 ->
                        products = gpd.getProductsPerPageByCateNewest(page, PRODUCTS_PER_PAGE, cid);
                    case 2 ->
                        products = gpd.getProductsPerPageByCateOldest(page, PRODUCTS_PER_PAGE, cid);
                    case 3 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid);
                    case 4 ->
                        products = gpd.getProductsPerPageByCatePriceDesc(page, PRODUCTS_PER_PAGE, cid);
                    case 5 ->
                        products = gpd.getProductsPerPageByCatePriceAsc(page, PRODUCTS_PER_PAGE, cid);
                    default ->
                        throw new AssertionError();
                }
            } else {
                totalProducts = gpd.getTotalProducts();
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPage(page, PRODUCTS_PER_PAGE);
                    case 1 ->
                        products = gpd.getProductsPerPageNewest(page, PRODUCTS_PER_PAGE);
                    case 2 ->
                        products = gpd.getProductsPerPageOldest(page, PRODUCTS_PER_PAGE);
                    case 3 ->
                        products = gpd.getProductsPerPage(page, PRODUCTS_PER_PAGE);
                    case 4 ->
                        products = gpd.getProductsPerPagePriceDesc(page, PRODUCTS_PER_PAGE);
                    case 5 ->
                        products = gpd.getProductsPerPagePriceAsc(page, PRODUCTS_PER_PAGE);
                    default ->
                        throw new AssertionError();
                }
            }
        } else {
            for (String rid : sizes) {
                try {
                    int id = Integer.parseInt(rid);
                    sizeInt.add(id);
                } catch (NumberFormatException e) {
                }
            }
            if (cid != 0) {
                totalProducts = gpd.getTotalProductsByCate(cid);
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid);
                    case 1 ->
                        products = gpd.getProductsPerPageByCateNewest(page, PRODUCTS_PER_PAGE, cid);
                    case 2 ->
                        products = gpd.getProductsPerPageByCateOldest(page, PRODUCTS_PER_PAGE, cid);
                    case 3 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid);
                    case 4 ->
                        products = gpd.getProductsPerPageByCatePriceDesc(page, PRODUCTS_PER_PAGE, cid);
                    case 5 ->
                        products = gpd.getProductsPerPageByCatePriceAsc(page, PRODUCTS_PER_PAGE, cid);
                    default ->
                        throw new AssertionError();
                }
            } else {
                totalProducts = gpd.getTotalProductsInSize(sizeInt);
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageInSize(page, PRODUCTS_PER_PAGE, sizeInt);
                    case 1 ->
                        products = gpd.getProductsPerPageNewest(page, PRODUCTS_PER_PAGE);
                    case 2 ->
                        products = gpd.getProductsPerPageOldest(page, PRODUCTS_PER_PAGE);
                    case 3 ->
                        products = gpd.getProductsPerPage(page, PRODUCTS_PER_PAGE);
                    case 4 ->
                        products = gpd.getProductsPerPagePriceDesc(page, PRODUCTS_PER_PAGE);
                    case 5 ->
                        products = gpd.getProductsPerPagePriceAsc(page, PRODUCTS_PER_PAGE);
                    default ->
                        throw new AssertionError();
                }
            }
        }
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Category ID: " + product.getCategoryId());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Created At: " + product.getCreatedAt());
                System.out.println("Updated At: " + product.getUpdatedAt());
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("Không có sản phẩm nào được tìm thấy.");
        }
        gpd.fetchImagesForProducts(products);

        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCat = cd.getALl();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }

        request.setAttribute("categoryMap", categoryMap);
        request.setAttribute("dataPro", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("size", sizeInt);
        request.setAttribute("cid", cid);
        request.setAttribute("sort", sort);
//        request.setAttribute("priceFrom", priceFrom);
//        request.setAttribute("priceTo", priceTo);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("shop.jsp").forward(request, response);

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
