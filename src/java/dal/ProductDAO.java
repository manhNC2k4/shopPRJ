/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LNV
 */
public class ProductDAO extends DBContext {

    //insert product
    public int insertProduct(Product p) {
        String sql = "INSERT INTO Products (name, description, category_id, price, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int productId = -1;
        try {
            PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getCategoryId());
            st.setBigDecimal(4, p.getPrice());
            st.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
            st.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));

            int row = st.executeUpdate();
            if (row > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    productId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return productId;

    }

    //insert product images
    public void insertProductImages(int productId, List<String> imageUrls) throws SQLException {
        PreparedStatement statement = null;
        try {
            // Insert product images into the database
            String sql = "INSERT INTO [dbo].[ProductImages]\n"
                    + "           ([product_id]\n"
                    + "           ,[image_url])"
                    + " VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            for (String imageUrl : imageUrls) {
                statement.setInt(1, productId);
                statement.setString(2, imageUrl);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close statement: " + e.getMessage());
                }
            }
        }
    }

    //insert productsize
    public String insertProductSizes(int productId, String[] sizes, String[] stocks) throws SQLException {
        String message;
        try {
            String sql = "INSERT INTO [dbo].[Product_Size]\n"
                    + "           ([product_id]\n"
                    + "           ,[size]\n"
                    + "           ,[stock]) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < sizes.length; i++) {
                statement.setInt(1, productId);
                statement.setInt(2, Integer.parseInt(sizes[i]));
                statement.setInt(3, Integer.parseInt(stocks[i]));
                statement.executeUpdate();
            }
            message = "Product uploaded successfully!";
            return message;
        } catch (NumberFormatException | SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            return message;
        }
    }

    //save product image
    public String saveImageToFileSystem(InputStream imageInputStream, String imageName, String uploadDir) throws IOException {
        // Ensure the upload directory exists
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageName;
        // Create the file path
        String filePath = Paths.get(uploadDir, uniqueFileName).toString();

        // Save the file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = imageInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return "uploads/images/" + uniqueFileName; // Thay đổi: Trả về đường dẫn tương đối
    }

    //get product by category id
    public List<Product> getProductsOf(int cid) {
        List<Product> list = new ArrayList<>();
        //lenh sql select * from category
        String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, pi.image_url "
                + "FROM Products p "
                + "LEFT JOIN ProductImages pi ON p.product_id = pi.product_id "
                + "WHERE p.category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Product product = findProductById(list, productId);
                if (product == null) {
                    product = new Product(
                            productId,
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                    list.add(product);
                }
                String imageUrl = rs.getString("image_url");
                if (imageUrl != null) {
                    product.getImages().add(imageUrl);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get product by category id
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        //lenh sql select * from category
        String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, pi.image_url\n"
                + "   FROM Products p\n"
                + "   LEFT JOIN ProductImages pi ON p.product_id = pi.product_id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Product product = findProductById(list, productId);
                if (product == null) {
                    product = new Product(
                            productId,
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                    list.add(product);
                }
                String imageUrl = rs.getString("image_url");
                if (imageUrl != null) {
                    product.getImages().add(imageUrl);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //find product in a list by id
    private Product findProductById(List<Product> list, int productId) {
        for (Product product : list) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    //delete product
    public void deleteProduct(int id) {
        String deleteProductSizeSql = "DELETE FROM [dbo].[Product_Size] WHERE product_id = ?";
        String deleteProductSql = "DELETE FROM [dbo].[Products] WHERE product_id = ?";

        try {
            connection.setAutoCommit(false);

            PreparedStatement st1 = connection.prepareStatement(deleteProductSizeSql);
            st1.setInt(1, id);
            st1.executeUpdate();
            PreparedStatement st2 = connection.prepareStatement(deleteProductSql);
            st2.setInt(1, id);
            st2.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                // Hoàn tác giao dịch nếu có lỗi xảy ra
                connection.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println(rollbackEx);
            }
            System.out.println(e);
        } finally {
            try {
                // Đặt lại chế độ tự động commit
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

}
