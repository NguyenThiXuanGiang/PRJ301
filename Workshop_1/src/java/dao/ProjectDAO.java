/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author tamph
 */
public class ProjectDAO implements IDAO<ProjectDTO, Integer>{

    @Override
    public boolean create(ProjectDTO entity) {
        String sql = "INSERT INTO tblStartupProjects "
                + "(project_name, Description, Status, estimated_launch, is_active) "
                + "VALUES (?, ?, ?, ?, 1)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, entity.getProject_name());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getStatus());
            ps.setDate(4, entity.getEstimated_launch());
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<ProjectDTO> readAll() {
        String sql = "SELECT * FROM tblStartupProjects WHERE is_active > 0";
        List<ProjectDTO> list = new ArrayList<ProjectDTO>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProjectDTO p = new ProjectDTO(
                        rs.getInt("project_id"), 
                        rs.getString("project_name"), 
                        rs.getString("Description"), 
                        rs.getString("Status"), 
                        rs.getDate("estimated_launch"));
                
                list.add(p);
            }                     
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean update(ProjectDTO entity) {
        return false;       
    }

    public boolean updateStatus(ProjectDTO entity, String status) {
        String sql = "UPDATE tblStartupProjects SET Status = ? WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, entity.getProject_id());
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
        }
        return false;
    }


    public List<ProjectDTO> searchByName(String searchTerm) {
        String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ? AND is_active > 0";
        List<ProjectDTO> list = new ArrayList<ProjectDTO>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProjectDTO p = new ProjectDTO(
                        rs.getInt("project_id"), 
                        rs.getString("project_name"), 
                        rs.getString("Description"), 
                        rs.getString("Status"), 
                        rs.getDate("estimated_launch"));
                
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public ProjectDTO readByUsername(Integer username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProjectDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean setStatusToNone(int id){
        String sql = "UPDATE tblStartupProjects SET is_active = 0 WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public ProjectDTO searchById(int id){
        String sql = "SELECT * FROM tblStartupProjects WHERE project_id = ?";
        
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,  id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProjectDTO p = new ProjectDTO(
                        rs.getInt("project_id"), 
                        rs.getString("project_name"), 
                        rs.getString("Description"), 
                        rs.getString("Status"), 
                        rs.getDate("estimated_launch"));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
}
