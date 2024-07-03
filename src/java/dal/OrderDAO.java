/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.OrderDetail;
import model.Product_Size;

/**
 *
 * @author LNV
 */
public class OrderDAO extends DBContext {

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT [order_id]"
                + "      ,[user_id]"
                + "      ,[order_date]"
                + "      ,[status]"
                + "      ,[total]"
                + "      ,[created_at]"
                + "      ,[updated_at]"
                + "  FROM [dbo].[Orders]";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotal(rs.getDouble("total"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdateAt(rs.getTimestamp("updated_at"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error at getAllOrders of OrderDAO" + ex.getMessage());
        }
        return orders;
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT [order_id]"
                + "      ,[user_id]"
                + "      ,[order_date]"
                + "      ,[status]"
                + "      ,[total]"
                + "      ,[created_at]"
                + "      ,[updated_at]"
                + "  FROM [dbo].[Orders]"
                + " WHERE [order_id] = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setStatus(rs.getString("status"));
                    order.setTotal(rs.getDouble("total"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setUpdateAt(rs.getTimestamp("updated_at"));

                    return order;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error at getOrderById of OrderDAO" + ex.getMessage());
        }
        return null;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "SELECT [order_id]"
                + "      ,[quatity]"
                + "      ,[price]"
                + "      ,[order_detail_id]"
                + "      ,[product_size_id]"
                + "  FROM [dbo].[Order_Details]"
                + " WHERE [order_id] = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    OrderDetail detail = new OrderDetail();
                    detail.setOrderId(rs.getInt("order_id"));
                    detail.setQuantity(rs.getInt("quatity"));
                    detail.setPrice(rs.getBigDecimal("price"));
                    detail.setId(rs.getInt("order_detail_id"));
                    detail.setProductSizeId(rs.getInt("product_size_id"));

                    details.add(detail);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error at getOrderDetailsByOrderId of OrderDAO" + ex.getMessage());
        }
        return details;
    }

    public boolean insertOrder(Order order, List<OrderDetail> orderDetails) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "INSERT INTO [dbo].[Orders]"
                + "           ([user_id]"
                + "           ,[order_date]"
                + "           ,[status]"
                + "           ,[total]"
                + "           ,[created_at]"
                + "           ,[updated_at])"
                + "     OUTPUT INSERTED.order_id"
                + "     VALUES"
                + "           (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            statement.setString(3, order.getStatus());
            statement.setDouble(4, order.getTotal());
            statement.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
            statement.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));

            // Thực thi và lấy order_id được tạo tự động
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int generatedOrderId = rs.getInt(1); // Lấy order_id
                    order.setOrderId(generatedOrderId);  // Gán lại cho đối tượng Order
                }
            }
            if (!insertOrderDetails(orderDetails)) {
                connection.rollback(); // Rollback nếu thêm chi tiết đơn hàng thất bại
                return false;
            }
            
            // Cập nhật số lượng sản phẩm trong kho
            for (OrderDetail detail : orderDetails) {
                Product_Size ps = getProduct_SizeById(detail.getProductSizeId());
                // Lấy số lượng hiện tại
                int currentStock = ps.getStock(); 

                // Tính số lượng mới
                int newStock = currentStock - detail.getQuantity(); 
                
                // Cập nhật kho
                if (!updateProductSize(newStock, detail.getProductSizeId())) {
                    connection.rollback();
                    return false; 
                }
            }
            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback(); // Rollback nếu có lỗi xảy ra
            } catch (SQLException rollbackEx) {
                System.out.println("Rollback failed: " + rollbackEx.getMessage());
            }
            System.out.println("Error at insertOrder of OrderDAO: " + ex.getMessage());
            return false;
        } finally {
            // Đặt lại auto commit
            connection.setAutoCommit(true);
        }
    }

    public boolean insertOrderDetails(List<OrderDetail> orderDetails) {
        String sql = "INSERT INTO [dbo].[Order_Details]"
                + "           ([order_id]"
                + "           ,[quatity]"
                + "           ,[price]"
                + "           ,[product_size_id])"
                + "     VALUES"
                + "           (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (OrderDetail detail : orderDetails) {
                statement.setInt(1, detail.getOrderId());
                statement.setInt(2, detail.getQuantity());
                statement.setBigDecimal(3, detail.getPrice());
                statement.setInt(4, detail.getProductSizeId());

                statement.addBatch(); // Thêm vào batch
            }
            int[] affectedRows = statement.executeBatch(); // Thực thi batch
            // Kiểm tra chi tiết từng kết quả INSERT
            for (int i = 0; i < affectedRows.length; i++) {
                if (affectedRows[i] != 1) { // Nếu một câu lệnh INSERT nào không thành công
                    System.out.println("Error inserting OrderDetail at index: " + i);
                    // Xử lý lỗi, ví dụ: rollback transaction, ghi log, ...
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error at insertOrderDetails of OrderDAO: " + ex.getMessage());
            return false;
        }
    }

    //update product size
    public boolean updateProductSize(int stock, int product_size_id) {
        String updateSizeSQL = "UPDATE [dbo].[Product_Size]"
                + "   SET [stock] = ?"
                + " WHERE product_size_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(updateSizeSQL);
            st.setInt(1, stock);
            st.setInt(2, product_size_id);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return false;
    }

     //get a product size by id
    public Product_Size getProduct_SizeById(int psid) {
        Product_Size ps;
        String getProductSizesSql = "SELECT * FROM [dbo].[Product_Size] "
                + "WHERE product_size_id = ?";
        try {
            PreparedStatement getSizesStmt = connection.prepareStatement(getProductSizesSql);
            getSizesStmt.setInt(1, psid);
            ResultSet sizesRs = getSizesStmt.executeQuery();
            if (sizesRs.next()) {
                ps = new Product_Size(sizesRs.getInt("product_id"), sizesRs.getInt("size"),
                        sizesRs.getInt("stock"));
                return ps;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
}
