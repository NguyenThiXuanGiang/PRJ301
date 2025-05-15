/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.OrderItem;
import entity.Product;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderItemDAO extends DBContext {
    
     public Vector<OrderItem> getAllOrderItem() {
        String sql = "select * from [order_items]";
        Vector<OrderItem> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItem order = new OrderItem(
                        rs.getInt("order_id"),
                        rs.getInt("item_id"),
                        rs.getString("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("list_price")
                );
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
     
     public Vector<OrderItem> getAllOrderItemByOid(int oid) {
         ProductDAO pdao = new ProductDAO();
        String sql = "select * from [order_items] where order_id = ?";
        Vector<OrderItem> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItem order = new OrderItem(
                        rs.getInt("order_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("list_price"),
                        pdao.getProductById(rs.getString("product_id"))
                );
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
     
    public String getTopOrder() {
        String sql = "SELECT top 1 product_id, COUNT(*) AS order_count\n"
                + "FROM order_items \n"
                + "GROUP BY product_id\n"
                + "ORDER BY order_count DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
             return rs.getString("product_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "";
    }
    public static void main(String[] args) {
        OrderItemDAO oi = new OrderItemDAO();
        System.out.println(oi.getTopOrder());
    }
}
