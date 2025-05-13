/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;


public class ClienteDAOImp extends BaseDAOImp<ClienteDTO> implements ClienteDAO{
    
    public ClienteDAOImp(){ 

    }
    
    @Override
    protected String getInsertarQuery() {
        return  "insert into Cliente (userId, contraseña, estadoConexion,fechaRegistro,nombreCliente,direccion,email,infoTarjetaCredito,infoCompra, balanceCuenta) values (?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update Cliente SET contraseña = ?, estadoConexion = ?,fechaRegistro = ?,nombreCliente = ?,direccion = ?,email = ?,infoTarjetaCredito = ? ,infoCompra = =?, balanceCuenta = ? where userId = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from Cliente WHERE userId = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from Cliente";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from Cliente WHERE userId = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, ClienteDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdPersona());
        ps.setString(2,modelo.getContraseña());
        ps.setString(3, modelo.getEstadoConexion().name());
        ps.setTimestamp(4, Timestamp.valueOf(modelo.getFechaRegistro()));
        ps.setString(5,modelo.getNombre());
        ps.setString(6,modelo.getDireccion());
        ps.setString(7,modelo.getEmail());
        ps.setString(8, modelo.getInfoTarjetaCredito());
        ps.setString(9, modelo.getInfoCompra());
        ps.setDouble(10,modelo.getBalanceCuenta());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, ClienteDTO modelo) throws SQLException {
        
        ps.setString(1,modelo.getContraseña());
        ps.setString(2, modelo.getEstadoConexion().name());
        ps.setTimestamp(3, Timestamp.valueOf(modelo.getFechaRegistro()));
        ps.setString(4,modelo.getNombre());
        ps.setString(5,modelo.getDireccion());
        ps.setString(6,modelo.getEmail());
        ps.setString(7, modelo.getInfoTarjetaCredito());
        ps.setString(8, modelo.getInfoCompra());
        ps.setDouble(9,modelo.getBalanceCuenta());
        ps.setInt(10,modelo.getIdPersona());
    }

    @Override
    protected ClienteDTO createFromResultado(ResultSet rs) throws SQLException {
        ClienteDTO p = new ClienteDTO();
        p.setIdPersona(rs.getInt("userId"));
        p.setContraseña(rs.getString("contraseña"));
        p.setEstadoConexion(EstadoConexionDTO.valueOf(rs.getString("estadoConexion")));
        p.setNombre(rs.getString("nombreCliente"));
        p.setDireccion(rs.getString("direccion"));
        p.setEmail(rs.getString("email"));
        p.setInfoTarjetaCredito(rs.getString("infoTarjetaCredito"));
        p.setInfoCompra(rs.getString("infoCompra"));
        p.setBalanceCuenta(rs.getDouble("balanceCuenta"));
        Timestamp timestamp = rs.getTimestamp("fechaRegistro");
        if (timestamp != null) {
           p.setFechaRegistro(timestamp.toLocalDateTime());
        }
        return p;
    }
    
}
