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
import model.Product_Size;

/**
 *
 * @author LNV
 */
public class ProductDAO extends DBContext {

    //insert product
    public int insertProduct(Product p) {
        String sql = "INSERT INTO Products (name, description, category_id, price, created_at, image, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int productId = -1;
        try {
            PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getCategoryId());
            st.setBigDecimal(4, p.getPrice());
            st.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
            st.setString(6, p.getImage());
            st.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));

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
        String sql = "SELECT [product_id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[category_id]\n"
                + "      ,[price]\n"
                + "      ,[created_at]\n"
                + "      ,[image]\n"
                + "      ,[updated_at]\n"
                + "  FROM [dbo].[Products] where category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getBigDecimal("price"),
                        rs.getTimestamp("created_at"),
                        rs.getString("image"),
                        rs.getTimestamp("updated_at")
                );
                list.add(c);
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
        String sql = "SELECT [product_id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[category_id]\n"
                + "      ,[price]\n"
                + "      ,[created_at]\n"
                + "      ,[image]\n"
                + "      ,[updated_at]\n"
                + "  FROM [dbo].[Products]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getBigDecimal("price"),
                        rs.getTimestamp("created_at"),
                        rs.getString("image"),
                        rs.getTimestamp("updated_at")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
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
