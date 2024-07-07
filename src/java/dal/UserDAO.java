
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import DTO.UserDTO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.User;

/**
 *
 * @author LNV
 */
public class UserDAO extends DBContext {

    //doc tat ca bang categories ra
    public List<UserDTO> getALl() {
        List<UserDTO> list = new ArrayList<>();
        //lenh sql select * from User
        String sql = "SELECT [user_id]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[phone]\n"
                + "      ,[address]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]"
                + "      ,[role]\n"
                + "  FROM [dbo].[Users]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserDTO c = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getInt("role")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //delete user
    public String delete(int id) {
        String sql = "DELETE FROM [dbo].[Users]\n"
                + "      WHERE user_id = ?";

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

    //get user by id
    public UserDTO getUserDTOById(int code) {
        String sql = "select * from [dbo].[Users] where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDTO c = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getInt("role")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserById(int code) {
        String sql = "select * from [dbo].[Users] where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"), rs.getString("address"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //get user by email
    public UserDTO getUserByMail(String code) {
        String sql = "select * from [dbo].[Users] where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDTO c = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getInt("role")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String updateUser(User c, int id) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[address] = ?\n"
                + " WHERE user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getPassword());
            st.setString(3, c.getEmail());
            st.setString(4, c.getFirst_name());
            st.setString(5, c.getLast_name());
            st.setString(6, c.getPhone());
            st.setString(7, c.getAddress());
            st.setInt(8, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                return "Update successful";
            } else {
                return "No rows updated";
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    public Map<Integer, Integer> getTotalUsersCreatedPerMonthForCurrentYear() {
        Map<Integer, Integer> usersCreatedPerMonth = new HashMap<>();
        String sql = "SELECT MONTH(created_at) AS month, COUNT(*) AS totalUsers "
                + "FROM Users "
                + "WHERE YEAR(created_at) = YEAR(GETDATE()) "
                + "GROUP BY MONTH(created_at)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("month");
                int totalUsers = rs.getInt("totalUsers");
                usersCreatedPerMonth.put(month, totalUsers);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return usersCreatedPerMonth;
    }

    public static void main(String[] args) {

    }
}
