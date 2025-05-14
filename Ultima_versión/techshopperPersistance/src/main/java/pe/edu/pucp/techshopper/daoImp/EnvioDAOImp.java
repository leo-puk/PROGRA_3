/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;

import pe.edu.pucp.techshopper.db.DBManager;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.dao.EnvioDAO;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;

public class EnvioDAOImp extends BaseDAOImp<EnvioDTO>implements EnvioDAO{
    public EnvioDAOImp(){
        
    }
    
    @Override
    protected String getInsertarQuery() {
        return  "insert into envio (idEnvio,destino,fechaEntrega,empresaCourier, precio) values (?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update envio SET destino = ?, fechaEntrega = ?, empresaCourier = ?, precio = ? where idEnvio = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from envio WHERE idEnvio = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from envio";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from envio WHERE idEnvio = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, EnvioDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdEnvio());
        ps.setInt(2,modelo.getDestino().getIdLocalizacion());
        ps.setTimestamp(3, Timestamp.valueOf(modelo.getFechaEntrega()));
        ps.setString (4, modelo.getEmpresaCourier());
        ps.setDouble (5, modelo.getPrecio());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, EnvioDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getDestino().getIdLocalizacion());
        ps.setTimestamp(2, Timestamp.valueOf(modelo.getFechaEntrega()));
        ps.setString (3, modelo.getEmpresaCourier());
        ps.setDouble (4, modelo.getPrecio());
        ps.setDouble (5, modelo.getIdEnvio());
    }

    @Override
    protected EnvioDTO createFromResultado(ResultSet rs) throws SQLException {
        EnvioDTO p = new EnvioDTO();
        p.setIdEnvio(rs.getInt("idEnvio"));

        LocalizacionDTO localizacion = new LocalizacionDTO();
        localizacion.setIdLocalizacion (rs.getInt("destino"));
        p.setDestino(localizacion);

        Timestamp timestamp = rs.getTimestamp("fechaEntrega");
        if (timestamp != null) {
           p.setFechaEntrega(timestamp.toLocalDateTime());
        }

        p.setEmpresaCourier(rs.getString("empresaCourier"));
        p.setPrecio(rs.getDouble("precio"));

        return p;
    }
    
  
}
