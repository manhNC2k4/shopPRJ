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
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
        HttpSession session = request.getSession();
        String[] sizes;

        List<Integer> sizeInt = new ArrayList<>();
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
        GetProductDAO gpd = new GetProductDAO();
        int priceFrom = gpd.getMinPrice();
        if (request.getParameter("priceFrom") != null) {
            priceFrom = Integer.parseInt(request.getParameter("priceFrom"));
        }
//        // init price to
        int priceTo = gpd.getMaxPrice();
        if (request.getParameter("priceTo") != null) {
            priceTo = Integer.parseInt(request.getParameter("priceTo"));
        }

        System.out.println(priceFrom + " " + priceTo);
        
        //init page
        int page = 1;
        if (request.getParameter("page") != null) {
            Object sizesFromSession = session.getAttribute("sizes");
            if (sizesFromSession instanceof Object[]) {
                Object[] objectArray = (Object[]) sizesFromSession;
                String[] stringArray = new String[objectArray.length];
                int i = 0;
                for (Object obj : objectArray) {
                    if (obj instanceof String) {
                        stringArray[i] = (String) obj;
                        i++;
                    }
                }
                sizes = Arrays.copyOf(stringArray, i);
            } else if (sizesFromSession instanceof String[]) {
                sizes = (String[]) sizesFromSession;
            } else {
                ArrayList<String> sizesList = (ArrayList<String>) sizesFromSession;
                sizes = sizesList.toArray(new String[sizesList.size()]);
            }
            page = Integer.parseInt(request.getParameter("page"));
        } else {
            sizes = request.getParameterValues("size");
        }
        List<Product> products;

        int totalProducts;
        if (sizes == null) {
            if (cid != 0) {
                totalProducts = gpd.getTotalProductsByCate(cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 1 ->
                        products = gpd.getProductsPerPageByCateNewest(page, PRODUCTS_PER_PAGE, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 2 ->
                        products = gpd.getProductsPerPageByCateOldest(page, PRODUCTS_PER_PAGE, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 3 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 4 ->
                        products = gpd.getProductsPerPageByCatePriceDesc(page, PRODUCTS_PER_PAGE, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 5 ->
                        products = gpd.getProductsPerPageByCatePriceAsc(page, PRODUCTS_PER_PAGE, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    default ->
                        throw new AssertionError();
                }
            } else {
                totalProducts = gpd.getTotalProducts(new BigDecimal(priceFrom),new BigDecimal(priceTo));
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPage(page, PRODUCTS_PER_PAGE,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 1 ->
                        products = gpd.getProductsPerPageNewest(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 2 ->
                        products = gpd.getProductsPerPageOldest(page, PRODUCTS_PER_PAGE,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 3 ->
                        products = gpd.getProductsPerPage(page, PRODUCTS_PER_PAGE,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 4 ->
                        products = gpd.getProductsPerPagePriceDesc(page, PRODUCTS_PER_PAGE,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 5 ->
                        products = gpd.getProductsPerPagePriceAsc(page, PRODUCTS_PER_PAGE,new BigDecimal(priceFrom),new BigDecimal(priceTo));
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
                totalProducts = gpd.getTotalProductsInSizeByCate(sizeInt, cid,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageByCateInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 1 ->
                        products = gpd.getProductsPerPageByCateNewestInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 2 ->
                        products = gpd.getProductsPerPageByCateOldestInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 3 ->
                        products = gpd.getProductsPerPageByCateInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 4 ->
                        products = gpd.getProductsPerPageByCatePriceDescInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 5 ->
                        products = gpd.getProductsPerPageByCatePriceAscInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    default ->
                        throw new AssertionError();
                }
            } else {
                totalProducts = gpd.getTotalProductsInSize(sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageInSize(page, PRODUCTS_PER_PAGE, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 1 ->
                        products = gpd.getProductsPerPageNewestInSize(page, PRODUCTS_PER_PAGE, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 2 ->
                        products = gpd.getProductsPerPageOldestInSize(page, PRODUCTS_PER_PAGE, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 3 ->
                        products = gpd.getProductsPerPageInSize(page, PRODUCTS_PER_PAGE, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 4 ->
                        products = gpd.getProductsPerPagePriceDescInSize(page, PRODUCTS_PER_PAGE, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    case 5 ->
                        products = gpd.getProductsPerPagePriceAscInSize(page, PRODUCTS_PER_PAGE, sizeInt,new BigDecimal(priceFrom),new BigDecimal(priceTo));
                    default ->
                        throw new AssertionError();
                }
            }
        }
        System.out.println(totalProducts);
//        if (products != null && !products.isEmpty()) {
//            for (Product product : products) {
//                System.out.println("Product ID: " + product.getId());
//                System.out.println("Name: " + product.getName());
//                System.out.println("Description: " + product.getDescription());
//                System.out.println("Category ID: " + product.getCategoryId());
//                System.out.println("Price: " + product.getPrice());
//                System.out.println("Created At: " + product.getCreatedAt());
//                System.out.println("Updated At: " + product.getUpdatedAt());
//                System.out.println("----------------------------------");
//            }
//        } else {
//            System.out.println("Không có sản phẩm nào được tìm thấy.");
//        }
        gpd.fetchImagesForProducts(products);
        if (session.getAttribute("sizes") != null) {
            session.removeAttribute("sizes");
        }
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCat = cd.getALl();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
        session.setAttribute("sizes", sizes);
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
