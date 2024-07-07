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
        if (request.getParameter("clearKeyword") != null) {
            session.removeAttribute("keyword"); // Xóa keyword khỏi session
        }
        String keyword = request.getParameter("query"); // Ưu tiên lấy từ request
        if (keyword != null && !keyword.equals("")) {
            session.setAttribute("keyword", keyword); // Cập nhật session
        } else {
            // Nếu không có từ khóa mới từ request, mới lấy từ session
            keyword = (String) session.getAttribute("keyword");
            if (keyword == null) {
                keyword = ""; // Hoặc giá trị mặc định khác
            }
        }
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

//        // init price to
        int priceTo = gpd.getMaxPrice();

        System.out.println(priceFrom + " " + priceTo);

        //init page
        int page = 1;
        if (request.getParameter("page") != null) {
            sizes = (String[]) session.getAttribute("sizes");
            priceFrom = (Integer) session.getAttribute("priceFrom");
            priceTo = (Integer) session.getAttribute("priceTo");
            page = Integer.parseInt(request.getParameter("page"));
        } else if (request.getParameterValues("nosize") != null) {
            if (request.getParameterValues("size") != null) {
                sizes = request.getParameterValues("size");
                priceFrom = (Integer) session.getAttribute("priceFrom");
                priceTo = (Integer) session.getAttribute("priceTo");
            } else {
                sizes = null;
                priceFrom = (Integer) session.getAttribute("priceFrom");
                priceTo = (Integer) session.getAttribute("priceTo");
            }
        } else if (request.getParameter("priceFrom") != null) {
            sizes = (String[]) session.getAttribute("sizes");
            String fstr = request.getParameter("priceFrom");
            String kq = fstr.substring(1);
            priceFrom = Integer.parseInt(kq);
            String fstr1 = request.getParameter("priceTo");
            String kq1 = fstr1.substring(1);
            priceTo = Integer.parseInt(kq1);
        } else {
            if (session.getAttribute("sizes") == null) {
                sizes = null;
            } else {
                sizes = (String[]) session.getAttribute("sizes");
            }
            if (session.getAttribute("priceFrom") != null) {
                priceFrom = (Integer) session.getAttribute("priceFrom");
            }
            if (session.getAttribute("priceTo") != null) {
                priceTo = (Integer) session.getAttribute("priceTo");
            }
        }
        List<Product> products;

        int totalProducts;
        if (sizes == null) {
            if (cid != 0) {
                totalProducts = gpd.getTotalProductsByCate(cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 1 ->
                        products = gpd.getProductsPerPageByCateNewest(page, PRODUCTS_PER_PAGE, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 2 ->
                        products = gpd.getProductsPerPageByCateOldest(page, PRODUCTS_PER_PAGE, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 3 ->
                        products = gpd.getProductsPerPageByCate(page, PRODUCTS_PER_PAGE, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 4 ->
                        products = gpd.getProductsPerPageByCatePriceDesc(page, PRODUCTS_PER_PAGE, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 5 ->
                        products = gpd.getProductsPerPageByCatePriceAsc(page, PRODUCTS_PER_PAGE, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    default ->
                        throw new AssertionError();
                }
            } else {
                totalProducts = gpd.getTotalProductsSearch(new BigDecimal(priceFrom), new BigDecimal(priceTo), keyword);
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageSearch(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 1 ->
                        products = gpd.getProductsPerPageNewestSearch(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 2 ->
                        products = gpd.getProductsPerPageOldestSearch(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 3 ->
                        products = gpd.getProductsPerPageSearch(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 4 ->
                        products = gpd.getProductsPerPagePriceDescSearch(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 5 ->
                        products = gpd.getProductsPerPagePriceAscSearch(page, PRODUCTS_PER_PAGE, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
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
                totalProducts = gpd.getTotalProductsInSizeByCate(sizeInt, cid, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageByCateInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 1 ->
                        products = gpd.getProductsPerPageByCateNewestInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 2 ->
                        products = gpd.getProductsPerPageByCateOldestInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 3 ->
                        products = gpd.getProductsPerPageByCateInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 4 ->
                        products = gpd.getProductsPerPageByCatePriceDescInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    case 5 ->
                        products = gpd.getProductsPerPageByCatePriceAscInSize(page, PRODUCTS_PER_PAGE, cid, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo));
                    default ->
                        throw new AssertionError();
                }
            } else {
                totalProducts = gpd.getTotalProductsInSizeSearch(sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                switch (sort) {
                    case 0 ->
                        products = gpd.getProductsPerPageInSize(page, PRODUCTS_PER_PAGE, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 1 ->
                        products = gpd.getProductsPerPageNewestInSize(page, PRODUCTS_PER_PAGE, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 2 ->
                        products = gpd.getProductsPerPageOldestInSize(page, PRODUCTS_PER_PAGE, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo), keyword);
                    case 3 ->
                        products = gpd.getProductsPerPageInSize(page, PRODUCTS_PER_PAGE, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 4 ->
                        products = gpd.getProductsPerPagePriceDescInSize(page, PRODUCTS_PER_PAGE, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo),keyword);
                    case 5 ->
                        products = gpd.getProductsPerPagePriceAscInSize(page, PRODUCTS_PER_PAGE, sizeInt, new BigDecimal(priceFrom), new BigDecimal(priceTo), keyword);
                    default ->
                        throw new AssertionError();
                }
            }
        }
        System.out.println(totalProducts);
        gpd.fetchImagesForProducts(products);
        if (session.getAttribute("sizes") != null) {
            session.removeAttribute("sizes");
        }
        if (session.getAttribute("priceFrom") != null) {
            session.removeAttribute("priceFrom");
        }
        if (session.getAttribute("priceTo") != null) {
            session.removeAttribute("priceTo");
        }
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCat = cd.getALl();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : listCat) {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
        session.setAttribute("sizes", sizes);
        session.setAttribute("priceFrom", priceFrom);
        session.setAttribute("priceTo", priceTo);
        System.out.println(priceFrom + " " + priceTo);
        request.setAttribute("categoryMap", categoryMap);
        request.setAttribute("dataPro", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("size", sizeInt);
        request.setAttribute("cid", cid);
        request.setAttribute("sort", sort);
        request.setAttribute("minPrice", gpd.getMinPrice());
        request.setAttribute("maxPrice", gpd.getMaxPrice());

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
