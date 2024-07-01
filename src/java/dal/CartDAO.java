/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Cart_Item;

/**
 *
 * @author LNV
 */
public class CartDAO extends DBContext {

    //create cart
    public int insertCart(int userId) {
        String sql = "INSERT INTO [dbo].[Carts]"
                + "           ([user_id]"
                + "           ,[num_items])"
                + "     VALUES (?, ?)";
        int cartId = -1;
        try {
            PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, userId);
            st.setInt(2, 0);
            int row = st.executeUpdate();
            if (row > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cartId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return cartId;
    }
    
    //update cart
    public String updateCart(int id, int num) {
        String message = null;
        String updateCart = "UPDATE [dbo].[Carts]"
                + "   SET [num_items] = ?"
                + " WHERE [cart_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(updateCart);
            st.setInt(1, num);
            st.setInt(2, id);
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
    
    //get cart by id
    public Cart getCartById(int id) {
        String sql = "select * from [dbo].[Carts] where [user_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart c = new Cart(
                        rs.getInt("cart_id"),
                         rs.getInt("user_id"),
                         rs.getInt("num_items")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
 
    //get cart by cart id
    public Cart getCartByCartId(int id) {
        String sql = "select * from [dbo].[Carts] where [cart_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart c = new Cart(
                        rs.getInt("cart_id"),
                         rs.getInt("user_id"),
                         rs.getInt("num_items")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //add cart item
    public int addCartItem(Cart_Item cartItem) throws SQLException {
        String sql = "INSERT INTO [dbo].[Cart_Items] ([cart_id]" +
            " ,[product_size_id]" +
            " ,[quantity]) VALUES (?, ?, ?)";
        int id = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cartItem.getCart_id());
            statement.setInt(2, cartItem.getProduct_size_id());
            statement.setInt(3, cartItem.getQuantity());
            int row = statement.executeUpdate();
            if (row > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return id;
    }
    
    //update cart item
    public String updateCartItem(int id, int num) {
        String message = null;
        String updateCart = "UPDATE [dbo].[Cart_Items]"
                + "   SET [quantity] = ?"
                + " WHERE [cart_item_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(updateCart);
            st.setInt(1, num);
            st.setInt(2, id);
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
    
    //delete cart
    public String deleteCart(int id) {
        String sql = "DELETE FROM [dbo].[Carts]"
                + "      WHERE cart_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                return "Delete successful";
            } else {
                return "No rows updated";
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
    
     //delete cart item
    public String deleteCartItem(int id) {
        String sql = "DELETE FROM [dbo].[Cart_Items]"
                + "      WHERE cart_item_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                return "Delete successful";
            } else {
                return "No rows updated";
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
    
    //get all cart_item by cart_id
    public List<Cart_Item> getAllCartItemsByCartId(int cartId) {
        List<Cart_Item> cartItems = new ArrayList<>();
        String sql = "SELECT [cart_item_id], [cart_id], [product_size_id], [quantity] "
                   + "FROM [dbo].[Cart_Items] "
                   + "WHERE [cart_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart_Item cartItem = new Cart_Item(
                    rs.getInt("cart_item_id"),
                    rs.getInt("cart_id"),
                    rs.getInt("product_size_id"),
                    rs.getInt("quantity")
                );
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return cartItems;
    }
    
    //get cart by cart id
    public Cart_Item getCart_ItemById(int id) {
        String sql = "select * from [dbo].[Cart_Items] where [cart_item_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart_Item cartItem = new Cart_Item(
                    rs.getInt("cart_item_id"),
                    rs.getInt("cart_id"),
                    rs.getInt("product_size_id"),
                    rs.getInt("quantity")
                );
                return cartItem;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //update cart item by size 
    public String updateCartItemBySize(int id, int size_id) {
        String message = null;
        String updateCart = "UPDATE [dbo].[Cart_Items]"
                + "   SET [product_size_id] = ?"
                + " WHERE [cart_item_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(updateCart);
            st.setInt(1, size_id);
            st.setInt(2, id);
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
    
}
