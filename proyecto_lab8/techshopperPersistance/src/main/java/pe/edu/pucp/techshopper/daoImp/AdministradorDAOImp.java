/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;


public class AdministradorDAOImp  extends BaseDAOImp <AdministradorDTO> implements AdministradorDAO {
    public AdministradorDAOImp(){ 

    }
    
    @Override
    protected String getInsertarQuery() {
        return  "insert into Administrador (idAdministrador, contraseña, estadoConexion,fechaRegistro,nombre,email) values (?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update Cliente SET contraseña = ?, estadoConexion = ?,fechaRegistro = ?,nombreAdmin = ?,email = ? where userId = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from Administrador WHERE userId = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from Administrador";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from Administrador WHERE userId = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, AdministradorDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdPersona());
        ps.setString(2,modelo.getContraseña());
        ps.setString(3, modelo.getEstadoConexion().name());
        ps.setTimestamp(4, Timestamp.valueOf(modelo.getFechaRegistro()));
        ps.setString(5,modelo.getNombre());
        ps.setString(6,modelo.getEmail());
        
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, AdministradorDTO modelo) throws SQLException {
        
        ps.setString(1,modelo.getContraseña());
        ps.setString(2, modelo.getEstadoConexion().name());
        ps.setTimestamp(3, Timestamp.valueOf(modelo.getFechaRegistro()));
        ps.setString(4,modelo.getNombre());
        ps.setString(5,modelo.getEmail());
        ps.setInt(6,modelo.getIdPersona());
    }

    @Override
    protected AdministradorDTO createFromResultado(ResultSet rs) throws SQLException {
        AdministradorDTO p = new AdministradorDTO();
        p.setIdPersona(rs.getInt("idAdministrador"));
        p.setContraseña(rs.getString("contraseña"));
        p.setEstadoConexion(EstadoConexionDTO.valueOf(rs.getString("estadoConexion")));
        p.setNombre(rs.getString("nombre"));
        p.setEmail(rs.getString("email"));
        Timestamp timestamp = rs.getTimestamp("fechaRegistro");
        if (timestamp != null) {
           p.setFechaRegistro(timestamp.toLocalDateTime());
        }
        return p;
    }
}
