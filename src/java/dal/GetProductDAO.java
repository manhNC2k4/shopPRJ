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
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            for (int i = 0; i < products.size(); i++) {
                st.setInt(i + 1, products.get(i).getId());
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String imageUrl = rs.getString("image_url");
                for (Product product : products) {
                    if (product.getId() == productId) {
                        product.getImages().add(imageUrl);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
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
}
