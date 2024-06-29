/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Favorite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author LNV
 */
public class FavoriteDAO extends DBContext {

    public void addFavorite(Favorite favorite) {
        if (!isFavoriteExists(favorite.getUser_id(), favorite.getProduct_id())) {
            try {
                String sql = "INSERT INTO Favorite (user_id, product_id) VALUES (?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, favorite.getUser_id());
                stmt.setInt(2, favorite.getProduct_id());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sản phẩm đã có trong danh sách yêu thích");
        }
    }

    // Hàm kiểm tra trùng lặp
    public boolean isFavoriteExists(int userId, int productId) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM Favorite WHERE user_id = ? AND product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public List<Product> getUserFavorites(int userId) throws SQLException {
        List<Product> favorites = new ArrayList<>();
        String sql = "SELECT p.product_id, p.name, p.description, p.category_id, p.price, p.created_at, p.updated_at, pi.image_url "
                + "FROM Products p "
                + "LEFT JOIN ProductImages pi ON p.product_id = pi.product_id "
                + "JOIN Favorite f ON p.product_id = f.product_id "
                + "WHERE f.user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    Product product = findProductById(favorites, productId);
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
                        favorites.add(product);
                    }
                    String imageUrl = rs.getString("image_url");
                    if (imageUrl != null) {
                        product.getImages().add(imageUrl);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
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

    public List<Favorite> getFavoritesByUserId(int userId) {
        List<Favorite> favorites = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Favorite WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Favorite favorite = new Favorite(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"));
                favorites.add(favorite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }

    public void removeFavorite(int userId, int productId) {
        try {
            String sql = "DELETE FROM Favorite WHERE user_id = ? AND product_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countFavoritesByUserId(int userId) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM Favorite WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
