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

/**
 *
 * @author LNV
 */
public class LoginDAO extends DBContext {

    public String login(String email, String password) {
        String hashedPassword = hashPassword(password);
        System.out.println(hashedPassword);
        String sql = "SELECT * FROM [dbo].[Users] WHERE [email] = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    if (hashedPassword.equals(storedHashedPassword)) {
                        return "success"; // Đăng nhập thành công
                    } else { // Mật khẩu không khớp
                        return "Email or password is wrong";
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "Email or password is wrong";
    }

    // Hash password using SHA-256 algorithm
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

    }
}
