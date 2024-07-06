/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Sale;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author LNV
 */
public class SaleDAO extends DBContext {

    // Hàm hỗ trợ để map ResultSet thành đối tượng Sale
    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getInt("id"));
        sale.setName(rs.getString("name"));
        sale.setDiscountType(rs.getString("discount_type"));
        sale.setDiscountValue(rs.getDouble("discount_value"));
        sale.setStartDate(rs.getDate("start_date"));
        sale.setEndDate(rs.getDate("end_date"));
        sale.setStatus(rs.getString("status"));
        return sale;
    }

    // Lấy tất cả các chương trình giảm giá
    public List<Sale> getAllSales(){
        List<Sale> sales = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Sales");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return sales;
    }

    // Lấy chương trình giảm giá theo ID
    public Sale getSaleById(int id){
        Sale sale = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Sales WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sale = mapResultSetToSale(rs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return sale;
    }
    
    
    // Thêm mới một chương trình giảm giá
    public void addSale(Sale sale) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Sales (name, discount_type, discount_value, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, sale.getName());
            stmt.setString(2, sale.getDiscountType());
            stmt.setDouble(3, sale.getDiscountValue());
            stmt.setDate(4, new java.sql.Date(sale.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(sale.getEndDate().getTime()));
            stmt.setString(6, sale.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // Cập nhật thông tin chương trình giảm giá
    public void updateSale(Sale sale) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE Sales SET name = ?, discount_type = ?, discount_value = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?");
            stmt.setString(1, sale.getName());
            stmt.setString(2, sale.getDiscountType());
            stmt.setDouble(3, sale.getDiscountValue());
            stmt.setDate(4, new java.sql.Date(sale.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(sale.getEndDate().getTime()));
            stmt.setString(6, sale.getStatus());
            stmt.setInt(7, sale.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
     // Xóa chương trình giảm giá
    public void deleteSale(int id) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Sales WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }  catch (SQLException e) {
            System.out.println(e);
        }
    }
    
     // Lấy danh sách chương trình giảm giá đang hoạt động
    public List<Sale> getActiveSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Sales WHERE status = 'active' AND start_date <= GETDATE() AND end_date >= GETDATE()");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return sales;
    }
    
    
    
}
