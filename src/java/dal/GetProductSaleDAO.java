/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author LNV
 */
public class GetProductSaleDAO extends DBContext {

    //get min price
    public int getMinPrice() {
        int minPrice = 0;

        try {
            String sql = "SELECT top 1 [price]"
                    + " FROM [dbo].[Products]"
                    + " order by [price] ASC ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                minPrice = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return minPrice;
    }

    //get max price
    public int getMaxPrice() {
        int maxPrice = 0;

        try {
            String sql = "SELECT top 1 [price]"
                    + " FROM [dbo].[Products]"
                    + " order by [price] DESC ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maxPrice = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return maxPrice;
    }

    //get limit product with newest created_at
    public List<Product> getLatestProducts(int limit) {
        List<Product> list = new ArrayList<>();
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer.");
        }
        String sql = "SELECT TOP " + limit + " p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id\n"
                + " FROM Products p\n"
                + " ORDER BY p.created_at DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }
        return list;
    }

    public void fetchImagesForProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products provided.");
            return;
        }
        String sql = "SELECT pi.product_id, pi.image_url"
                + " FROM ProductImages pi"
                + " WHERE pi.product_id IN (";
        for (int i = 0; i < products.size(); i++) {
            if (i > 0) {
                sql += ", ";
            }
            sql += "?";
        }
        sql += ")";
        boolean imagesFetched = false;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (int i = 0; i < products.size(); i++) {
                st.setInt(i + 1, products.get(i).getId());
            }
            // In ra câu lệnh SQL cuối cùng và các tham số
            System.out.println("Executing SQL: " + st.toString());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String imageUrl = rs.getString("image_url");
                for (Product product : products) {
                    if (product.getId() == productId) {
                        product.getImages().add(imageUrl);
                        imagesFetched = true;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        if (imagesFetched) {
            System.out.println("Images fetched successfully.");
        } else {
            System.out.println("No images found for the provided products.");
        }
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

    //count product by category
    public int getTotalProductsByCateNoPrice(int id, int sale) {
        int totalProducts = 0;

        try {
            String sql = "SELECT COUNT(*) FROM products"
                    + " where category_id = ? AND sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, sale);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalProducts;
    }

    //count product by category
    public int getTotalProductsByCate(int id, BigDecimal minPrice, BigDecimal maxPrice, int saleId) {
        int totalProducts = 0;

        try {
            String sql = "SELECT COUNT(*) FROM products"
                    + " where category_id = ? AND price >= ? AND price <= ? AND sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, saleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalProducts;
    }

    //count product
    public int getTotalProducts(BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        int totalProducts = 0;

        try {
            String sql = "SELECT COUNT(*) FROM products WHERE price >= ? AND price <= ? AND sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, minPrice);
            statement.setBigDecimal(2, maxPrice);
            statement.setInt(3, sale);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalProducts;
    }

    public int getTotalProductsSearch(BigDecimal minPrice, BigDecimal maxPrice, String keyword, int saleId) {
        int totalProducts = 0;

        try {
            String sql = "SELECT COUNT(DISTINCT p.product_id) "
                    + " FROM Products p JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, saleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalProducts;
    }

    //get list product per page
    public List<Product> getProductsPerPage(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id FROM Products p"
                    + " WHERE p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.product_id"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, minPrice);
            statement.setBigDecimal(2, maxPrice);
            statement.setInt(3, sale);
            statement.setInt(3, start);
            statement.setInt(4, productsPerPage);
//            statement.setInt(2, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page
    public List<Product> getProductsPerPageSearch(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;
        System.out.println(start + " " + productsPerPage + " " + minPrice + " " + maxPrice + " " + keyword + " " + sale);
        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id "
                    + " FROM Products p JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ? "
                    + " ORDER BY p.product_id "
                    + "  OFFSET ? ROWS "
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page
    public List<Product> getProductsPerPageSearchAndSold(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id, SUM(od.quatity) AS sold_quantity \n"
                    + " FROM Products p \n"
                    + " JOIN Categories c ON p.category_id = c.category_id \n"
                    + " LEFT JOIN Product_Size ps ON p.product_id = ps.product_id \n"
                    + " LEFT JOIN Order_Details od ON ps.product_size_id = od.product_size_id \n"
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ?  AND p.sale_id = ?\n"
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id \n"
                    + " ORDER BY sold_quantity DESC \n"
                    + " OFFSET ? ROWS \n"
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                product.setSold(rs.getInt("sold_quantity"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page newest
    public List<Product> getProductsPerPageNewestSearch(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ?  AND p.sale_id = ?"
                    + " ORDER BY p.created_at DESC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page oldest
    public List<Product> getProductsPerPageOldestSearch(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.created_at ASC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price high to low
    public List<Product> getProductsPerPagePriceDescSearch(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.price DESC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price low to high
    public List<Product> getProductsPerPagePriceAscSearch(int page, int productsPerPage, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.price ASC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page
    public List<Product> getProductsPerPageByCate(int page, int productsPerPage, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " Where p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.product_id"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            statement.setInt(5, start);
            statement.setInt(6, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page
    public List<Product> getProductsPerPageByCateAndSold(int page, int productsPerPage, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id, SUM(od.quatity) AS total_quantity_sold "
                    + " FROM Products p "
                    + " LEFT JOIN Product_Size ps ON p.product_id = ps.product_id "
                    + " LEFT JOIN Order_Details od ON ps.product_size_id = od.product_size_id "
                    + " WHERE (p.category_id = ? OR ? = 0) AND (p.price >= ? AND p.price <= ?) AND p.sale_id = ?"
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id "
                    + " ORDER BY total_quantity_sold DESC "
                    + " OFFSET ? ROWS "
                    + " FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, cid);
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            statement.setInt(6, start);
            statement.setInt(7, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                product.setSold(rs.getInt("total_quantity_sold"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page newest
    public List<Product> getProductsPerPageByCateNewest(int page, int productsPerPage, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " Where p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.created_at DESC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            statement.setInt(5, start);
            statement.setInt(6, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page oldest
    public List<Product> getProductsPerPageByCateOldest(int page, int productsPerPage, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " Where p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.created_at ASC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            statement.setInt(5, start);
            statement.setInt(6, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price high to low
    public List<Product> getProductsPerPageByCatePriceDesc(int page, int productsPerPage, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " Where p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?"
                    + " ORDER BY p.price DESC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            statement.setInt(5, start);
            statement.setInt(6, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price low to high
    public List<Product> getProductsPerPageByCatePriceAsc(int page, int productsPerPage, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " Where p.category_id = ? AND p.sale_id = ?"
                    + " ORDER BY p.price ASC"
                    + " OFFSET ? ROWS"
                    + " FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, sale);
            statement.setInt(3, start);
            statement.setInt(4, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
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
                            rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page in size
    public List<Product> getProductsPerPageInSize(int page, int productsPerPage, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id, SUM(od.quatity) AS total_quantity_sold "
                    + " FROM Products p"
                    + " LEFT JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " LEFT JOIN Order_Details od ON ps.product_size_id = od.product_size_id "
                    + " JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY total_quantity_sold DESC "
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            int paramIndex = 6;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                product.setSold(rs.getInt("total_quantity_sold"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //count product in sizes
    public int getTotalProductsInSizeSearch(List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        int totalProducts = 0;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT COUNT(DISTINCT p.product_id) FROM products p "
                    + " JOIN Product_Size ps "
                    + " ON p.product_id = ps.product_id "
                    + " JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            int paramIndex = 6;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalProducts;
    }

    //count product in sizes by cate
    public int getTotalProductsInSizeByCate(List<Integer> sizes, int cid, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        int totalProducts = 0;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT COUNT(DISTINCT p.product_id) FROM products p "
                    + " JOIN Product_Size ps "
                    + " ON p.product_id = ps.product_id "
                    + " WHERE category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ? " + sizeFilter.toString();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            int paramIndex = 5;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalProducts;
    }

    //get list product per page newest in size
    public List<Product> getProductsPerPageNewestInSize(int page, int productsPerPage, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ? " + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.created_at DESC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            int paramIndex = 6;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page oldest in size
    public List<Product> getProductsPerPageOldestInSize(int page, int productsPerPage, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ? " + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.created_at ASC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            int paramIndex = 6;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price high-low in size
    public List<Product> getProductsPerPagePriceDescInSize(int page, int productsPerPage, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.price DESC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            int paramIndex = 6;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price low-high in size
    public List<Product> getProductsPerPagePriceAscInSize(int page, int productsPerPage, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, String keyword, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " JOIN Categories c ON p.category_id = c.category_id "
                    + " WHERE (p.name LIKE ? OR c.name LIKE ?) AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.price ASC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setInt(5, sale);
            int paramIndex = 6;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    ////get list product per page in size by cate
    public List<Product> getProductsPerPageByCateInSize(int page, int productsPerPage, int cid, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id, SUM(od.quatity) AS total_quantity_sold "
                    + " FROM Products p"
                    + " LEFT JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " LEFT JOIN Order_Details od ON ps.product_size_id = od.product_size_id "
                    + " WHERE p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ? " + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY total_quantity_sold DESC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            int paramIndex = 5;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                product.setSold(rs.getInt("total_quantity_sold"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page by cate newest in size
    public List<Product> getProductsPerPageByCateNewestInSize(int page, int productsPerPage, int cid, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " WHERE p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.created_at DESC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            int paramIndex = 5;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page by cate oldest in size
    public List<Product> getProductsPerPageByCateOldestInSize(int page, int productsPerPage, int cid, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " WHERE p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.created_at ASC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            int paramIndex = 5;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page by cate price desc in size
    public List<Product> getProductsPerPageByCatePriceDescInSize(int page, int productsPerPage, int cid, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " WHERE p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.price DESC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            int paramIndex = 5;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page by cate price asc in size
    public List<Product> getProductsPerPageByCatePriceAscInSize(int page, int productsPerPage, int cid, List<Integer> sizes, BigDecimal minPrice, BigDecimal maxPrice, int sale) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        StringBuilder sizeFilter = new StringBuilder();
        if (sizes != null && !sizes.isEmpty()) {
            sizeFilter.append(" AND ps.size IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sizeFilter.append("?");
                if (i < sizes.size() - 1) {
                    sizeFilter.append(",");
                }
            }
            sizeFilter.append(")");
        }

        try {
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " WHERE p.category_id = ? AND p.price >= ? AND p.price <= ? AND p.sale_id = ?" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at, p.sale_id"
                    + " ORDER BY p.price ASC"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setBigDecimal(2, minPrice);
            statement.setBigDecimal(3, maxPrice);
            statement.setInt(4, sale);
            int paramIndex = 5;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer saleId = null;
                if (rs.getObject("sale_id") != null) {
                    saleId = rs.getInt("sale_id");
                }
                Product product;
                if (saleId != null) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>(),
                            saleId
                    );
                } else {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            "",
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            new ArrayList<>()
                    );
                }
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

}
