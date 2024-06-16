/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author LNV
 */
@WebServlet(name = "UploadProductServlet", urlPatterns = {"/uploadProduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50, // 50MB
        location = "/tmp" // Thư mục tạm thời
)
public class UploadProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> list = cd.getALl();
        request.setAttribute("listCat", list);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form fields
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String r_categoryId = request.getParameter("categoryId");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String[] sizes = request.getParameterValues("size[]");
        String[] stocks = request.getParameterValues("stock[]");
        InputStream inputStream;
        String imagePath;
        String message;
        Part filePart = request.getPart("productImage");
        try {
            int categoryId = Integer.parseInt(r_categoryId);
            if (filePart != null) {
                inputStream = filePart.getInputStream();
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("/") + "uploads/images";
                ProductDAO pd = new ProductDAO();
                imagePath = pd.saveImageToFileSystem(inputStream, fileName, uploadDir);
                Product p = new Product(-1, name, description, categoryId, price, null, imagePath, null);
                int productId = pd.insertProduct(p);
                if (productId > 0) {
                    message = pd.insertProductSizes(productId, sizes, stocks);
                    if (message.equals("Product uploaded successfully!")) {
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                    }
                } else {
                    message = "Failed to upload product.";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                }
            }
        } catch (IOException | NumberFormatException | SQLException e) {
            message = "ERROR: " + e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
