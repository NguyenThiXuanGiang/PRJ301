/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;


public class OrderDAO extends DBContext{
    
    
      public Vector<Order> getAllOrder() {
        String sql = "select * from Orders";
        Vector<Order> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("customer_id"),
                        rs.getString("order_status"),
                        rs.getString("order_date"),
                        rs.getDouble("totalMoney")
                );
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
      public Vector<Order> searchOrder(String searchTxt) {
        String sql = "select * from Orders where customer_id+order_status like ?";
        Vector<Order> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%"+searchTxt+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("customer_id"),
                        rs.getString("order_status"),
                        rs.getString("order_date"),
                        rs.getDouble("totalMoney")
                );
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
      public Order getOrderById(int id) {
        String sql = "select * from Orders WHERE order_id = ?";
        Order od = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                od = new Order(
                        rs.getInt("order_id"),
                        rs.getString("customer_id"),
                        rs.getString("order_status"),
                        rs.getString("order_date"),
                        rs.getDouble("totalMoney")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return od;
    }

    public void updateStatus(String status, int oid) {
        String sql = "UPDATE [dbo].[orders]\n"
                + "   SET [order_status] = ?"
                + " WHERE order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, oid);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
           
public void checkcount(Customer cuss, Vector<Product> listItem) {
    LocalDateTime myDateObj = LocalDateTime.now();  
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
    String formattedDate = myDateObj.format(myFormatObj); 
    try {
        double totalMoney = 0;
        // Tính tổng tiền đơn hàng
        for (Product product : listItem) {
            totalMoney += product.getAfterPrice();
        }

        // Insert the order into orders table
        String sql1 = "INSERT INTO [dbo].[orders] "
                    + "([customer_id], [order_status], [order_date], [totalMoney]) "
                    + "VALUES(?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql1);
        st.setString(1, cuss.getCustomer_id());
        st.setString(2, "wait");  // Trạng thái đơn hàng có thể là "wait", "processing", hoặc "completed"
        st.setTimestamp(3, Timestamp.valueOf(formattedDate));
        st.setDouble(4, totalMoney);
        st.executeUpdate();

        // Lấy order_id của đơn hàng vừa thêm vào
        String sql2 = "SELECT TOP 1 order_id FROM [orders] ORDER BY order_id DESC";
        PreparedStatement st2 = connection.prepareStatement(sql2);
        ResultSet rs = st2.executeQuery();
        int orderId = 0;
        if (rs.next()) {
            orderId = rs.getInt("order_id");
        }

        // Insert vào bảng order_items
        for (Product item : listItem) {
            String sql3 = "INSERT INTO [dbo].[order_items] "
                    + "([order_id], [product_id], [quantity], [list_price], [item_id]) "
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st3.setInt(1, orderId);
            st3.setString(2, item.getProduct_id());
            st3.setInt(3, item.getQuantity());
            st3.setBigDecimal(4, BigDecimal.valueOf(item.getPrice()));
            st3.setInt(5, orderId);  // item_id có thể là orderId hoặc một giá trị tự động
            st3.executeUpdate();
            
            // Cập nhật số lượng sản phẩm trong bảng products sau khi checkout
            String sql4 = "UPDATE [dbo].[products] SET [quantity] = [quantity] - ? WHERE [product_id] = ?";
            PreparedStatement st4 = connection.prepareStatement(sql4);
            st4.setInt(1, item.getQuantity());  // Trừ đi số lượng sản phẩm trong giỏ hàng
            st4.setString(2, item.getProduct_id());
            st4.executeUpdate();
        }

    } catch (SQLException e) {
        System.out.println(e);
    }
}
     public void insertOI(Product p) {
         OrderItemDAO Oi = new OrderItemDAO();
         String sql = "INSERT INTO [dbo].[order_items]\n"
                 + "           ([order_id]\n"
                 + "           ,[product_id]\n"
                 + "           ,[quantity]\n"
                 + "           ,[list_price]\n"
                 + "           ,[item_id])\n"
                 + "     VALUES(?, ?, ?, ?, ?)";
        Vector<Order> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int newOrderId = getAllOrder().get(getAllOrder().size()-1).getOrder_id();
            int newOrderItemId = Oi.getAllOrderItem().get(Oi.getAllOrderItem().size() - 1).getItem_id() + 1;
            
            st.setInt(1, newOrderId);
            st.setString(2, p.getProduct_id());
            st.setInt(3, p.getQuantity());
            st.setBigDecimal(4, BigDecimal.valueOf(p.getPrice()));
            st.setInt(5, newOrderItemId);
           st.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        }
     }
     
    public Vector<Order> getCustomerOrder(String customer_Id) {
        OrderItemDAO oiDao = new OrderItemDAO();
        String sql = "Select * from orders\n"
                + "where customer_id = ? "
                + "order by order_date desc";
        Vector<Order> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer_Id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Vector<OrderItem> oi = oiDao.getAllOrderItemByOid(rs.getInt("order_id"));
                list.add(new Order(
                        rs.getInt("order_id"),
                        rs.getString("customer_id"),
                        rs.getString("order_status"),
                        rs.getString("order_date"),
                        rs.getDouble("totalMoney"),
                        oi
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public static void main(String[] args) {
        OrderDAO oi = new OrderDAO();
        System.out.println(oi.getAllOrder());
    }
    public int insertOrder(Order order) {
        String sql = "INSERT INTO [dbo].[orders] "
                + "([customer_id], [order_status], [order_date], [totalMoney]) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, order.getCustomer_id());
            st.setString(2, order.getOrder_status());
            st.setString(3, order.getOrder_date());
            st.setDouble(4, order.getTotalMoney());
            st.executeUpdate();

            // Get generated order_id
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return the generated order_id
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

}
