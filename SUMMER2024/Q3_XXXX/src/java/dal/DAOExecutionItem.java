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
import model.Execution;
import model.ExecutionItem;

/**
 *
 * @author HP
 */
public class DAOExecutionItem extends DBContext{
    
    public List<ExecutionItem> getExecutionItemByExId(int exeId) {
        String sql = "Select * from ExecutionItems where [ExecutionID] = ?";
        List<ExecutionItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, exeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                ExecutionItem exItem = new ExecutionItem(
                        rs.getInt("ExecutionItemID"), 
                        rs.getInt("ArrayItem"),
                        rs.getInt("ExecutionID"));
                list.add(exItem);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
