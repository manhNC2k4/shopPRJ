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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MyOrder;
import model.OrderDetail;
import model.Order_Info;
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

    public List<Order> getAllOrdersPending() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT [order_id]"
                + "      ,[user_id]"
                + "      ,[order_date]"
                + "      ,[status]"
                + "      ,[total]"
                + "      ,[created_at]"
                + "      ,[updated_at]"
                + "  FROM [dbo].[Orders]"
                + " WHERE [status] = 'Pending'";

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

    public List<Order> getAllOrdersProcessing() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT [order_id]"
                + "      ,[user_id]"
                + "      ,[order_date]"
                + "      ,[status]"
                + "      ,[total]"
                + "      ,[created_at]"
                + "      ,[updated_at]"
                + "  FROM [dbo].[Orders]"
                + " WHERE [status] = 'Processing'";

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

    public List<Order> getAllOrdersShipping() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT [order_id]"
                + "      ,[user_id]"
                + "      ,[order_date]"
                + "      ,[status]"
                + "      ,[total]"
                + "      ,[created_at]"
                + "      ,[updated_at]"
                + "  FROM [dbo].[Orders]"
                + " WHERE [status] = 'Shipping'";

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

    public List<Order> getAllOrdersDelivered() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT [order_id]"
                + "      ,[user_id]"
                + "      ,[order_date]"
                + "      ,[status]"
                + "      ,[total]"
                + "      ,[created_at]"
                + "      ,[updated_at]"
                + "  FROM [dbo].[Orders]"
                + " WHERE [status] = 'Delivered'";

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

    public boolean insertOrder(Order order, List<OrderDetail> orderDetails, Order_Info orderInfo) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "INSERT INTO [dbo].[Orders]"
                + "           ([user_id]"
                + "           ,[order_date]"
                + "           ,[status]"
                + "           ,[total]"
                + "           ,[created_at]"
                + "           ,[updated_at])"
                + "     VALUES"
                + "           (?, ?, ?, ?, ?, ?)";
        int generatedOrderId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getUserId());
            statement.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            statement.setString(3, order.getStatus());
            statement.setDouble(4, order.getTotal());
            statement.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
            statement.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));

            int row = statement.executeUpdate();
            if (row > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedOrderId = generatedKeys.getInt(1);
                }
            }
            orderInfo.setOrderId(generatedOrderId);
            if (generatedOrderId == 0 || !insertOrderInfo(orderInfo)) {
                connection.rollback(); // Rollback nếu thêm chi tiết đơn hàng thất bại
                return false;
            }
            for (OrderDetail orderDetail : orderDetails) {
                orderDetail.setOrderId(generatedOrderId);
            }
            if (!insertOrderDetails(orderDetails)) {
                connection.rollback(); // Rollback nếu thêm chi tiết đơn hàng thất bại
                return false;
            }
            ProductDAO pd = new ProductDAO();
            // Cập nhật số lượng sản phẩm trong kho
            for (OrderDetail detail : orderDetails) {
                Product_Size ps = pd.getProduct_SizeById(detail.getProductSizeId());
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

    public boolean insertOrderInfo(Order_Info orderInfo) {
        String sql = "INSERT INTO Order_Infos (first_name, last_name, country, street, city, postcode, phone, email, payment, order_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderInfo.getFirstName());
            ps.setString(2, orderInfo.getLastName());
            ps.setString(3, orderInfo.getCountry());
            ps.setString(4, orderInfo.getStreet());
            ps.setString(5, orderInfo.getCity());
            ps.setString(6, orderInfo.getPostcode());
            ps.setString(7, orderInfo.getPhone());
            ps.setString(8, orderInfo.getEmail());
            ps.setString(9, orderInfo.getPayment());
            ps.setInt(10, orderInfo.getOrderId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateOrderStatus(String status, int orderId) {
        String sql = "UPDATE [dbo].[Orders] "
                + "SET status = ?, updated_at = GETDATE() "
                + "WHERE order_id = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, status);
            st.setInt(2, orderId);
            int rowAffected = st.executeUpdate();
            if (rowAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Map<Integer, Double> getTotalRevenueByMonthForYear() {
        Map<Integer, Double> monthlyRevenue = new HashMap<>();

        // Khởi tạo doanh thu cho tất cả các tháng là 0.0
        for (int month = 1; month <= 12; month++) {
            monthlyRevenue.put(month, 0.0);
        }

        String sql = "SELECT MONTH(o.order_date) AS Month, "
                + "       SUM(o.total) AS TotalRevenue "
                + "FROM Orders o "
                + "WHERE o.status = 'Delivered' "
                + "AND YEAR(o.order_date) = YEAR(GETDATE()) "
                + "GROUP BY MONTH(o.order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("Month");
                double revenue = rs.getDouble("TotalRevenue");
                monthlyRevenue.put(month, revenue);
            }

        } catch (SQLException ex) {
            System.out.println("Error at getTotalRevenueByMonthForYear of OrderDAO: " + ex.getMessage());
        }

        return monthlyRevenue;
    }

    public Map<Integer, List<Double>> getTotalRevenueByWeekForYear() {
        Map<Integer, List<Double>> revenueByWeekForYear = new HashMap<>();
        String sql = "SELECT MONTH(o.order_date) AS Month, "
                + "       DATEPART(WEEK, o.order_date) AS WeekOfMonth, "
                + "       SUM(o.total) AS TotalRevenue "
                + "FROM Orders o "
                + "WHERE o.status = 'Delivered' "
                + "AND YEAR(o.order_date) = YEAR(GETDATE()) "
                + "GROUP BY MONTH(o.order_date), DATEPART(WEEK, o.order_date) "
                + "ORDER BY MONTH(o.order_date), DATEPART(WEEK, o.order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("Month");
                double weekRevenue = rs.getDouble("TotalRevenue");

                revenueByWeekForYear.computeIfAbsent(month, k -> new ArrayList<>())
                        .add(weekRevenue);
            }

        } catch (SQLException ex) {
            System.out.println("Error at getTotalRevenueByWeekForYear of OrderDAO: " + ex.getMessage());
        }

        return revenueByWeekForYear;
    }

    public Map<Integer, Integer> getTotalProductsSoldByMonthForYear() {
        Map<Integer, Integer> monthlyProductsSold = new HashMap<>();

        // Khởi tạo số lượng sản phẩm bán ra cho tất cả các tháng là 0
        for (int month = 1; month <= 12; month++) {
            monthlyProductsSold.put(month, 0);
        }

        String sql = "SELECT MONTH(o.order_date) AS Month, "
                + "       SUM(od.quatity) AS TotalProductsSold "
                + "FROM Orders o "
                + "JOIN Order_Details od ON o.order_id = od.order_id "
                + "WHERE o.status = 'Delivered' "
                + "AND YEAR(o.order_date) = YEAR(GETDATE()) "
                + "GROUP BY MONTH(o.order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("Month");
                int productsSold = rs.getInt("TotalProductsSold");
                monthlyProductsSold.put(month, productsSold);
            }

        } catch (SQLException ex) {
            System.out.println("Error at getTotalProductsSoldByMonthForYear of OrderDAO: " + ex.getMessage());
        }

        return monthlyProductsSold;
    }

    public int countPendingOrders() {
        String sql = "SELECT COUNT(*) AS PendingOrderCount "
                + "FROM Orders "
                + "WHERE status = 'Pending'";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("PendingOrderCount");
            }
        } catch (SQLException ex) {
            System.out.println("Error at countPendingOrders of OrderDAO: " + ex.getMessage());
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không có đơn hàng nào
    }

    public List<MyOrder> getMyOrder(int userId) {
        List<MyOrder> myOrder = new ArrayList<>();
        String sql = "SELECT\n"
                + "    OD.order_detail_id,\n"
                + "    P.name AS product_name,\n"
                + "    OD.quatity,\n"
                + "    PS.size,\n"
                + "    OD.price,\n"
                + "    (SELECT TOP 1 PI.image_url FROM ProductImages PI WHERE PI.product_id = P.product_id) AS first_image,\n"
                + "    O.status\n"
                + "FROM Orders AS O\n"
                + "JOIN Users AS U\n"
                + "    ON O.user_id = U.user_id\n"
                + "JOIN Order_Details AS OD\n"
                + "    ON O.order_id = OD.order_id\n"
                + "JOIN Product_Size AS PS\n"
                + "    ON OD.product_size_id = PS.product_size_id\n"
                + "JOIN Products AS P\n"
                + "    ON PS.product_id = P.product_id\n"
                + "WHERE\n"
                + "    U.user_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MyOrder mod = new MyOrder(rs.getInt("order_detail_id"), rs.getString("product_name"), rs.getString("first_image"), rs.getInt("quatity"), rs.getInt("size"), rs.getInt("price"), rs.getString("status"));
                myOrder.add(mod);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return myOrder;
    }
}
