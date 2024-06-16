/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.User;

public class registerDAO extends DBContext {

    //insert user
    public String insertUser(User user) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[address])\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String hashedPassword = hashPassword(user.getPassword());

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFirst_name());
            pstmt.setString(5, user.getLast_name());
            pstmt.setString(6, user.getPhone());
            pstmt.setString(7, user.getAddress());

            pstmt.executeUpdate();
            return "success";
        } catch (SQLException e) {
            System.out.println(e);
            return "Error: " + e.getMessage();
        }
    }
    
    // Check if username, email, or phone already exist in database
    public boolean isExistingUsername(String username) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE [username] = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Return true if user exists, false otherwise
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean isExistingEMail(String email) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE [email] = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Return true if user exists, false otherwise
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean isExistingPhone( String phone) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE [phone] = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, phone);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Return true if user exists, false otherwise
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    // Hash password using SHA-256 algorithm
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        registerDAO d = new registerDAO();
    }
}
