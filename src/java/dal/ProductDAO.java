/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CateSaleData;
import model.Image;
import model.ProductSaleData;
import model.Product_Size;

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
            // Create a map to store size and stock
            Map<Integer, Integer> sizeStockMap = new HashMap<>();
            // Iterate through sizes and stocks arrays
            for (int i = 0; i < sizes.length; i++) {
                // Get size and stock
                int size = Integer.parseInt(sizes[i]);
                int stock = Integer.parseInt(stocks[i]);

                // If size already exists, add stock
                if (sizeStockMap.containsKey(size)) {
                    sizeStockMap.put(size, sizeStockMap.get(size) + stock);
                } else {
                    // Add size and stock to the map
                    sizeStockMap.put(size, stock);
                }
            }

            String sql = "INSERT INTO [dbo].[Product_Size]\n"
                    + "           ([product_id]\n"
                    + "           ,[size]\n"
                    + "           ,[stock]) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            // Insert each size and stock into the database
            for (Map.Entry<Integer, Integer> entry : sizeStockMap.entrySet()) {
                statement.setInt(1, productId);
                statement.setInt(2, entry.getKey());
                statement.setInt(3, entry.getValue());
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
        String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id, pi.image_url "
                + "FROM Products p "
                + "LEFT JOIN ProductImages pi ON p.product_id = pi.product_id "
                + "WHERE p.category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                int productId = rs.getInt("product_id");
                Product product = findProductById(list, productId);
                if (product == null) {
                    if (saleId != null) {
                        product = new Product(
                                productId,
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("category_id"),
                                rs.getBigDecimal("price"),
                                rs.getTimestamp("created_at"),
                                rs.getTimestamp("updated_at"),
                                new ArrayList<>(),
                                saleId
                        );
                    } else {
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
                    }
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

    //get all product
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        //lenh sql select * from category
        String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at , p.sale_id, pi.image_url"
                + "   FROM Products p"
                + "   LEFT JOIN ProductImages pi ON p.product_id = pi.product_id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                int productId = rs.getInt("product_id");
                Product product = findProductById(list, productId);
                if (product == null) {
                    if (saleId != null) {
                        product = new Product(
                                productId,
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("category_id"),
                                rs.getBigDecimal("price"),
                                rs.getTimestamp("created_at"),
                                rs.getTimestamp("updated_at"),
                                new ArrayList<>(),
                                saleId
                        );
                    } else {
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
                    }

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
        String deleteProductImagesSql = "DELETE FROM [dbo].[ProductImages] WHERE product_id = ?";
        String getProductImagesSql = "SELECT image_url FROM [dbo].[ProductImages] WHERE product_id = ?";
        List<String> imageUrls = new ArrayList<>();

        try {
            connection.setAutoCommit(false);

            //lấy link file ảnh
            PreparedStatement getImagesStmt = connection.prepareStatement(getProductImagesSql);
            getImagesStmt.setInt(1, id);
            ResultSet rs = getImagesStmt.executeQuery();
            while (rs.next()) {
                imageUrls.add(rs.getString("image_url"));
            }

            //delete size
            PreparedStatement st1 = connection.prepareStatement(deleteProductSizeSql);
            st1.setInt(1, id);
            st1.executeUpdate();
            //delete image
            PreparedStatement st2 = connection.prepareStatement(deleteProductImagesSql);
            st2.setInt(1, id);
            st2.executeUpdate();

            //delete product
            PreparedStatement st3 = connection.prepareStatement(deleteProductSql);
            st3.setInt(1, id);
            st3.executeUpdate();
            //commit giao dịch
            connection.commit();

            //xóa file ảnh
            for (String imageUrl : imageUrls) {
                File file = new File("E:/Netbeans17/Shop/web/" + imageUrl);
                if (file.exists()) {
                    file.delete();
                }
            }
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

    //get product by id
    public Product getProductById(int productId) {
        Product product = null;
        String getProductSql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id "
                + "FROM Products p "
                + "WHERE p.product_id = ?";
        String getProductImagesSql = "SELECT image_url FROM [dbo].[ProductImages] WHERE product_id = ?";
        String getProductSizesSql = "SELECT size, stock FROM [dbo].[Product_Size] WHERE product_id = ?";

        try {
            // Lấy thông tin sản phẩm
            PreparedStatement getProductStmt = connection.prepareStatement(getProductSql);
            getProductStmt.setInt(1, productId);
            ResultSet productRs = getProductStmt.executeQuery();
            if (productRs.next()) {

                Integer saleId = null;
                if (productRs.getObject("sale_id") != null) {
                    saleId = productRs.getInt("sale_id");
                }
                if (saleId != null) {
                    product = new Product(
                            productId,
                            productRs.getString("name"),
                            productRs.getString("description"),
                            productRs.getInt("category_id"),
                            productRs.getBigDecimal("price"),
                            productRs.getTimestamp("created_at"),
                            productRs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            productId,
                            productRs.getString("name"),
                            productRs.getString("description"),
                            productRs.getInt("category_id"),
                            productRs.getBigDecimal("price"),
                            productRs.getTimestamp("created_at"),
                            productRs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );
                }
            }
            if (product != null) {
                // Lấy danh sách các hình ảnh sản phẩm
                PreparedStatement getImagesStmt = connection.prepareStatement(getProductImagesSql);
                getImagesStmt.setInt(1, productId);
                ResultSet imagesRs = getImagesStmt.executeQuery();
                while (imagesRs.next()) {
                    String imageUrl = imagesRs.getString("image_url");
                    if (imageUrl != null) {
                        product.getImages().add(imageUrl);
                    }
                }
                // Lấy danh sách các kích thước sản phẩm
                PreparedStatement getSizesStmt = connection.prepareStatement(getProductSizesSql);
                getSizesStmt.setInt(1, productId);
                ResultSet sizesRs = getSizesStmt.executeQuery();
                while (sizesRs.next()) {
                    int size = sizesRs.getInt("size");
                    int stock = sizesRs.getInt("stock");
                    product.getSizes().add(new Product_Size(size, stock));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }

    //update product
    public String updateProduct(int id, String name, String descrip, int cid, BigDecimal price) {
        String message = null;
        String updateProductSql = "UPDATE [dbo].[Products]\n"
                + "   SET [name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[category_id] = ?\n"
                + "      ,[price] = ?\n"
                + ",[updated_at] = ?\n"
                + " WHERE product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(updateProductSql);
            st.setString(1, name);
            st.setString(2, descrip);
            st.setInt(3, cid);
            st.setBigDecimal(4, price);
            st.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
            st.setInt(6, id);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                message = "Update successfull!";
            } else {
                message = "Error execute";
            }
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }

        return message;
    }

    //update product size
    public String updateProductSize(int stock, int product_id, int size) {
        String message = null;
        String updateSizeSQL = "UPDATE [dbo].[Product_Size]\n"
                + "   SET [stock] = ?\n"
                + " WHERE product_id = ? AND size = ?";
        try {
            PreparedStatement st = connection.prepareStatement(updateSizeSQL);
            st.setInt(1, stock);
            st.setInt(2, product_id);
            st.setInt(3, size);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                message = "Update successfull!";
            } else {
                message = "Error execute";
            }
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    //delete product size
    public String deleteSize(int product_id, int size) {
        String message = null;
        String deleteSQL = "DELETE FROM [dbo].[Product_Size]\n"
                + "     WHERE product_id = ? AND size = ?";
        try {
            PreparedStatement st = connection.prepareStatement(deleteSQL);
            st.setInt(1, product_id);
            st.setInt(2, size);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                message = "Delete successfull!";
            } else {
                message = "Error execute!";
            }
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    //add product size
    public String addSize(int product_id, int size, int stock) {
        String message = "";
        String sql = "INSERT INTO [dbo].[Product_Size]\n"
                + "           ([product_id]\n"
                + "           ,[size]\n"
                + "           ,[stock]) VALUES (?, ?, ?)";
        try {
            // Check if the size already exists
            String checkSql = "SELECT * FROM [dbo].[Product_Size] WHERE [product_id] = ? AND [size] = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setInt(1, product_id);
            checkStatement.setInt(2, size);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                // Size already exists
                message = "Size already exists!";
            } else {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, product_id);
                st.setInt(2, size);
                st.setInt(3, stock);
                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    message = "Insert successfull!";
                } else {
                    message = "Error execute!";
                }
            }
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    //update stock 
    public String updateStock(int productId, int size, int stock) {
        String message = "";
        String sql = "UPDATE [dbo].[Product_Size] SET [stock] = ? WHERE [product_id] = ? AND [size] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, stock);
            st.setInt(2, productId);
            st.setInt(3, size);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                message = "Stock updated successfully!";
            } else {
                message = "No size found to update!";
            }
        } catch (SQLException e) {
            message = "Error updating stock: " + e.getMessage();
        }
        return message;
    }

    //get all size of a product
    public List<Product_Size> getAllSize(int product_id) {
        List<Product_Size> sizes = new ArrayList<>();
        String getProductSizesSql = "SELECT size, stock FROM [dbo].[Product_Size] WHERE product_id = ?";

        try {
            PreparedStatement getSizesStmt = connection.prepareStatement(getProductSizesSql);
            getSizesStmt.setInt(1, product_id);
            ResultSet sizesRs = getSizesStmt.executeQuery();
            while (sizesRs.next()) {
                Product_Size ps = new Product_Size(product_id, sizesRs.getInt("size"),
                        sizesRs.getInt("stock"));
                sizes.add(ps);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return sizes;
    }

    //get all image of a product
    public List<Image> getAllImage(int product_id) {
        List<Image> images = new ArrayList<>();
        String sql = "SELECT [image_id]\n"
                + "      ,[product_id]\n"
                + "      ,[image_url]\n"
                + "  FROM [dbo].[ProductImages]"
                + "     WHERE product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet imgs = st.executeQuery();
            while (imgs.next()) {
                Image ps = new Image(imgs.getInt("image_id"), product_id,
                        imgs.getString("image_url"));
                images.add(ps);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return images;
    }

    //delete images
    public String deleteImage(int image_id) {
        String message = null;
        String deleteSQL = "DELETE FROM [dbo].[ProductImages]\n"
                + "      WHERE [image_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(deleteSQL);
            st.setInt(1, image_id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                message = "Delete successfull!";
            } else {
                message = "Error execute!";
            }
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    //get all size of a product
    public int getProduct_Size_Id(int product_id, int size) {
        int id = 0;
        String getProductSizesSql = "SELECT [product_size_id] FROM [dbo].[Product_Size] "
                + "WHERE product_id = ? AND size = ?";
        try {
            PreparedStatement getSizesStmt = connection.prepareStatement(getProductSizesSql);
            getSizesStmt.setInt(1, product_id);
            getSizesStmt.setInt(2, size);
            ResultSet sizesRs = getSizesStmt.executeQuery();
            if (sizesRs.next()) {
                id = sizesRs.getInt("product_size_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    //get a product size by id
    public Product_Size getProduct_SizeById(int psid) {
        Product_Size ps;
        String getProductSizesSql = "SELECT * FROM [dbo].[Product_Size] "
                + "WHERE product_size_id = ?";
        try {
            PreparedStatement getSizesStmt = connection.prepareStatement(getProductSizesSql);
            getSizesStmt.setInt(1, psid);
            ResultSet sizesRs = getSizesStmt.executeQuery();
            if (sizesRs.next()) {
                ps = new Product_Size(sizesRs.getInt("product_id"), sizesRs.getInt("size"),
                        sizesRs.getInt("stock"));
                return ps;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateSaleForProduct(int pid, Integer sid) {
        String sql = "UPDATE Products SET sale_id = ? WHERE product_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (sid != null) {
                st.setInt(1, sid);
            } else {
                st.setNull(1, java.sql.Types.INTEGER); // Set sale_id là NULL
            }
            st.setInt(2, pid);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating sale_id failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<ProductSaleData> getProductSaleDataTop5() {
        List<ProductSaleData> listProduct = new ArrayList<>();
        String sql = "SELECT ProductName, TotalSold, TotalStockRemaining\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        p.name AS ProductName, \n"
                + "        SUM(od.quatity) AS TotalSold,\n"
                + "        SUM(ps.stock) AS TotalStockRemaining,\n"
                + "        ROW_NUMBER() OVER (ORDER BY SUM(od.quatity) DESC) AS rn\n"
                + "    FROM Products p\n"
                + "    JOIN Product_Size ps ON p.product_id = ps.product_id\n"
                + "    LEFT JOIN Order_Details od ON ps.product_size_id = od.product_size_id\n"
                + "    GROUP BY p.name\n"
                + "    HAVING SUM(od.quatity) > 0 \n"
                + ") AS RankedProducts\n"
                + "WHERE rn <= 5;";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String Name = rs.getString("ProductName");
                int totalSold = rs.getInt("TotalSold");
                int totalStock = rs.getInt("TotalStockRemaining");

                ProductSaleData categorySalesData = new ProductSaleData(Name, totalSold, totalStock);
                listProduct.add(categorySalesData);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listProduct;

    }

}
