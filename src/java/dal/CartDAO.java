/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cart;

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
    
    //update product
    public String updateCart(int id, int num) {
        String message = null;
        String updateCart = "UPDATE [dbo].[[Carts]]"
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
    
}
