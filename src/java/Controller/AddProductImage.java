/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author LNV
 */
@WebServlet(name = "AddProductImage", urlPatterns = {"/addImage"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddProductImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rid = request.getParameter("id");
        request.setAttribute("id", rid);
        request.getRequestDispatcher("addProductImage.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String rid = request.getParameter("id");
    Collection<Part> fileParts = request.getParts();
    List<String> imagePaths = new ArrayList<>();
    String message;

    try {
        int id = Integer.parseInt(rid);
        ProductDAO pd = new ProductDAO();
        
        boolean fileUploaded = false;

        for (Part filePart : fileParts) {
            if (filePart.getName().equals("productImages") && filePart.getSize() > 0) {
                fileUploaded = true;
                InputStream inputStream = filePart.getInputStream();
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = "E:/Netbeans17/Shop/web/uploads/images";
                String imagePath = pd.saveImageToFileSystem(inputStream, fileName, uploadDir);
                imagePaths.add(imagePath);
            }
        }

        if (!fileUploaded) {
            message = "Lỗi: Không có file ảnh được chọn.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("addProductImage.jsp").forward(request, response);
            return;
        }

        pd.insertProductImages(id, imagePaths);
        request.setAttribute("id", id);
        request.getRequestDispatcher("updateProduct").forward(request, response);
    } catch (NumberFormatException e) {
        message = "ERROR: Invalid product ID.";
        request.setAttribute("message", message);
        request.getRequestDispatcher("addProductImage.jsp").forward(request, response);
    } catch (SQLException e) {
        message = "ERROR: Database error - " + e.getMessage();
        request.setAttribute("message", message);
        request.getRequestDispatcher("addProductImage.jsp").forward(request, response);
    } catch (ServletException | IOException e) {
        message = "ERROR: " + e.getMessage();
        request.setAttribute("message", message);
        request.getRequestDispatcher("addProductImage.jsp").forward(request, response);
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
