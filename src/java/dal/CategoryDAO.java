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
import model.CateSaleData;
import model.Category;

/**
 *
 * @author LNV
 */
public class CategoryDAO extends DBContext {

    //doc tat ca bang categories ra
    public List<Category> getALl() {
        List<Category> list = new ArrayList<>();
        //lenh sql select * from category
        String sql = "SELECT [category_id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "  FROM [dbo].[Categories]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //lay category tu id
    public Category getCateById(int id) {
        //lenh sql select * from category
        String sql = "SELECT [category_id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "  FROM [dbo].[Categories]"
                + " WHERE category_id = ?";
        Category c = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                c = new Category(
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return c;
    }

    public List<CateSaleData> getCategorySalesData() {
        List<CateSaleData> categorySalesDataList = new ArrayList<>();
        String sql = "SELECT c.name AS CategoryName, \n"
                + "       SUM(od.quatity * od.price) AS CategoryRevenue,\n"
                + "       CAST(SUM(od.quatity * od.price) AS DECIMAL(10,2)) * 100 / \n"
                + "       (SELECT SUM(od2.quatity * od2.price) FROM Order_Details od2)  AS Percentage\n"
                + "FROM Categories c\n"
                + "JOIN Products p ON c.category_id = p.category_id\n"
                + "JOIN Product_Size ps ON p.product_id = ps.product_id\n"
                + "JOIN Order_Details od ON ps.product_size_id = od.product_size_id\n"
                + "GROUP BY c.name;";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            int totalRevenue = 0;
            while (rs.next()) {
                String categoryName = rs.getString("CategoryName");
                int revernue = rs.getInt("CategoryRevenue");
                totalRevenue += revernue;
                CateSaleData categorySalesData = new CateSaleData(categoryName, revernue, 0);
                categorySalesDataList.add(categorySalesData);
            }
            for (CateSaleData data : categorySalesDataList) {
                double percentage = (double) data.getRevernue()/ totalRevenue * 100;
                data.setPercentage(percentage);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return categorySalesDataList;
    }

}
