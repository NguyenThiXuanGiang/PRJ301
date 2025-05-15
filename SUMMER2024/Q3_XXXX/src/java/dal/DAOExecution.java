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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Execution;
import model.ExecutionItem;

/**
 *
 * @author HP
 */
public class DAOExecution extends DBContext{
    
    DAOExecutionItem daoItem = new DAOExecutionItem();
    
    public List<Execution> getAllExecution() {
        String sql = "Select * from Executions";
        List<Execution> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {        
                List<ExecutionItem> listItem = daoItem.getExecutionItemByExId(rs.getInt("ExecutionID"));
                Execution ex = new Execution(
                        rs.getInt("ExecutionID"),
                        rs.getInt("Result"),
                        listItem);
                list.add(ex);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Execution getExecutionById(int id) {
        String sql = "Select * from Executions where ExecutionID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {        
                List<ExecutionItem> listItem = daoItem.getExecutionItemByExId(rs.getInt("ExecutionID"));
                return new Execution(
                        rs.getInt("ExecutionID"),
                        rs.getInt("Result"),
                        listItem);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public boolean delete(int exeId) {
        String sqlDeleteChild = "Delete from ExecutionItems where ExecutionID = ?";
        String sqlDeleteParent = "Delete from Executions where ExecutionID = ?";
        //Atomicity trong ACID
        try {
            connection.setAutoCommit(false);
            
            PreparedStatement psChild = connection.prepareStatement(sqlDeleteChild);
            psChild.setInt(1, exeId);
            psChild.executeUpdate();
            
            PreparedStatement psParent = connection.prepareStatement(sqlDeleteParent);
            psParent.setInt(1, exeId);
            psParent.executeUpdate();
            
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(e);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                 System.out.println(ex);
            }
        }
    }
}
