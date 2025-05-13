/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.db.DBManager;

 
public abstract class BaseDAOImp<T> implements ICrud<T>{
    
    protected abstract String getInsertarQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getSelectByIdQuery();
    protected abstract String getSelectAllQuery();
    protected abstract String getDeleteQuery();
    
    protected abstract void setInsertParameters(PreparedStatement ps, T modelo) throws SQLException;
    protected abstract void setUpdateParameters(PreparedStatement ps, T modelo) throws SQLException;
    
    protected abstract T createFromResultado(ResultSet rs) throws SQLException;
    
    
    @Override
    public int insertar (T modelo){
        String sql = getInsertarQuery();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            setInsertParameters(cmd, modelo);

            
            if (cmd.executeUpdate() == 0) {
                System.err.println("El registro no se inserto.");
                return 0;
            }
            
            try (ResultSet rs = cmd.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la insercion: " + e.getMessage());
            throw new RuntimeException("No se pudo insertar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al insertar el registro.", e);
        }
    }
    
    @Override
    public boolean modificar(T modelo){
        String sql =getUpdateQuery();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            setUpdateParameters(cmd,modelo);
            
            return cmd.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la modificacion: " + e.getMessage());
            throw new RuntimeException("No se pudo modificar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al modificar el registro.", e);
        } 
    }
    @Override
    public T buscar (int id){
        String sql = getSelectByIdQuery();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            cmd.setInt(1, id);
            try (ResultSet rs = cmd.executeQuery()) {
                if (!rs.next()) {
                    System.err.println("No se encontro la cuenta de usuario con id: " + id);
                    return null;
                }   

                return createFromResultado(rs);
            }
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la busqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
    }
    
    @Override
    public List<T> listar() {
        String sql = getSelectAllQuery();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery()) {
            
            List<T> objetos = new ArrayList<>();
            while (rs.next()) {
                objetos.add(createFromResultado(rs));
            }
            
            return objetos;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
    
        @Override
    public boolean eliminar(int id) {
        String sql = getDeleteQuery();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la eliminacion: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al eliminar el registro.", e);
        }
    }
}
