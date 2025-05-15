/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Category;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class ProductDAO extends DBContext{
    public Vector<Product> getAll() {
        Vector<Product> list = new Vector<>();
        String sql = "select * from products";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getString("product_id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getFloat("price"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductById(String pid) {
        Product list = null;
        String sql = "Select * from Products P\n"
                + "join Categories C on P.cid = C.category_id where P.product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("category_id"),
                        rs.getString("name")
                );
                Product p = new Product(
                        rs.getString("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getFloat("price"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        c
                );
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public Category getCategoryById(int id) {
        String sql = "select * from Categories where category_id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt("category_id"),
                        rs.getString("name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Vector<Product> getProductByCid(int cid) {
        Vector<Product> list = new Vector<>();
        String sql = "select * from products where 1=1 ";
        if (cid != 0) {
            sql += " and cid=" + cid;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getString("product_id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getFloat("price"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
     public Vector<Product> getProductsByName(String name) {
        Vector<Product> list = new Vector<>();
        String sql = "select * from products where name like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name +"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getString("product_id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getFloat("price"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public boolean insertProduct(Product pro) {
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[name]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[describe]\n"
                + "           ,[image]\n"
                + "           ,[cid])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pro.getProduct_id());
            ps.setString(2, pro.getName());
            ps.setInt(3, pro.getQuantity());
            ps.setDouble(4, pro.getPrice());
            ps.setString(5, pro.getDescribe());
            ps.setString(6, pro.getImage());
            ps.setInt(7, pro.getCid());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean updateProduct(Product pro) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [name] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[cid] = ?\n"
                + " WHERE [product_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pro.getName());
            ps.setInt(2, pro.getQuantity());
            ps.setDouble(3, pro.getPrice());
            ps.setString(4, pro.getDescribe());
            ps.setString(5, pro.getImage());
            ps.setInt(6, pro.getCid());
            ps.setString(7, pro.getProduct_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
 public boolean deleteProductById(String pid) {
    String sqlOrderItemsDelete = "DELETE FROM [dbo].[order_items] WHERE product_id = ?";
    String sqlProductDelete = "DELETE FROM [dbo].[products] WHERE product_id = ?";

    try {
        // Xóa bản ghi trong bảng con order_items trước
        PreparedStatement stOrderItems = connection.prepareStatement(sqlOrderItemsDelete);
        stOrderItems.setString(1, pid);
        stOrderItems.executeUpdate();

        // Xóa bản ghi trong bảng cha products
        PreparedStatement stProduct = connection.prepareStatement(sqlProductDelete);
        stProduct.setString(1, pid);
        return stProduct.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}
    
    public Vector<Product> searchProducts(String searchTxt) {
        Vector<Product> list = new Vector<>();
        String sql = "SELECT * FROM products\n"
                + "WHERE product_id+name+describe like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchTxt +"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getString("product_id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getFloat("price"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public static void main(String[] args) {
        ProductDAO pdao = new ProductDAO();
        System.out.println(pdao.getProductsByName("a"));
    }
}
