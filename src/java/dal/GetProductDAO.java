/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
public class GetProductDAO extends DBContext {

    //get limit product with newest created_at
    public List<Product> getLatestProducts(int limit) {
        List<Product> list = new ArrayList<>();
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer.");
        }
        String sql = "SELECT TOP " + limit + " p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at\n"
                + "FROM Products p\n"
                + "ORDER BY p.created_at DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        String sql = "SELECT pi.product_id, pi.image_url\n"
                + "FROM ProductImages pi\n"
                + "WHERE pi.product_id IN (";
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
    public int getTotalProductsByCate(int id) {
        int totalProducts = 0;

        try {
            String sql = "SELECT COUNT(*) FROM products\n"
                    + "where category_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
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
    public int getTotalProducts() {
        int totalProducts = 0;

        try {
            String sql = "SELECT COUNT(*) FROM products";
            PreparedStatement statement = connection.prepareStatement(sql);
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
    public List<Product> getProductsPerPage(int page, int productsPerPage) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "ORDER BY p.product_id\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page newest
    public List<Product> getProductsPerPageNewest(int page, int productsPerPage) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "ORDER BY p.created_at DESC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page oldest
    public List<Product> getProductsPerPageOldest(int page, int productsPerPage) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "ORDER BY p.created_at ASC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price high to low
    public List<Product> getProductsPerPagePriceDesc(int page, int productsPerPage) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "ORDER BY p.price DESC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price low to high
    public List<Product> getProductsPerPagePriceAsc(int page, int productsPerPage) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "ORDER BY p.price ASC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page
    public List<Product> getProductsPerPageByCate(int page, int productsPerPage, int cid) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "Where p.category_id = ?\n"
                    + "ORDER BY p.product_id\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, start);
            statement.setInt(3, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page newest
    public List<Product> getProductsPerPageByCateNewest(int page, int productsPerPage, int cid) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "Where p.category_id = ?\n"
                    + "ORDER BY p.created_at DESC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, start);
            statement.setInt(3, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page oldest
    public List<Product> getProductsPerPageByCateOldest(int page, int productsPerPage, int cid) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "Where p.category_id = ?\n"
                    + "ORDER BY p.created_at ASC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, start);
            statement.setInt(3, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price high to low
    public List<Product> getProductsPerPageByCatePriceDesc(int page, int productsPerPage, int cid) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "Where p.category_id = ?\n"
                    + "ORDER BY p.price DESC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, start);
            statement.setInt(3, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    //get list product per page price low to high
    public List<Product> getProductsPerPageByCatePriceAsc(int page, int productsPerPage, int cid) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * productsPerPage;

        try {
            String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p\n"
                    + "Where p.category_id = ?\n"
                    + "ORDER BY p.price ASC\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cid);
            statement.setInt(2, start);
            statement.setInt(3, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
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
        } catch (SQLException e) {
            System.err.println("Error fetching latest products: " + e.getMessage());
            // Ghi log lỗi hoặc xử lý lỗi theo logic của ứng dụng
            return Collections.emptyList(); // Trả về danh sách rỗng
        }

        return list;
    }

    ////get list product per page in size
    public List<Product> getProductsPerPageInSize(int page, int productsPerPage, List<Integer> sizes) {
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
            String sql = "SELECT p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at"
                    + " FROM Products p"
                    + " JOIN Product_Size ps ON p.product_id = ps.product_id"
                    + " WHERE 1=1" + sizeFilter.toString()
                    + " GROUP BY p.product_id, p.name, p.category_id, p.price, p.created_at, p.updated_at"
                    + " ORDER BY p.product_id"
                    + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement statement = connection.prepareStatement(sql);
            int paramIndex = 1;
            if (sizes != null && !sizes.isEmpty()) {
                for (Integer size : sizes) {
                    statement.setInt(paramIndex++, size);
                }
            }
            statement.setInt(paramIndex++, start);
            statement.setInt(paramIndex, productsPerPage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        "",
                        rs.getInt("category_id"),
                        rs.getBigDecimal("price"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        new ArrayList<>()
                );
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
    public int getTotalProductsInSize(List<Integer> sizes) {
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
            String sql = "SELECT COUNT(*) FROM products p "
                    + "JOIN Product_Size ps "
                    + "ON p.product_id = ps.product_id "
                    + "WHERE 1=1" + sizeFilter.toString();
            PreparedStatement statement = connection.prepareStatement(sql);
            int paramIndex = 1;
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
    
}
