/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.dao.CompraDAO;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.EstadoCompraDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;


public class CompraDAOImp extends BaseDAOImp<CompraDTO> implements CompraDAO{
    public CompraDAOImp(){ 

    }
    @Override
    protected String getInsertarQuery() {
        return  "insert into Compra (idCompra, precioTotal, fechaCompra, estadoCompra, entrega) values (?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update Compra SET precioTotal = ?, fechaCompra = ?, estadoCompra = ?, entrega = ? where idCompra = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from Compra WHERE idCompra = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from Compra";
    }

    @Override
    protected String getDeleteQuery() {
        return  "delete from Compra WHERE idCompra = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, CompraDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdCompra());
        ps.setDouble(2,modelo.getPrecioTotal());
        ps.setTimestamp(3, Timestamp.valueOf(modelo.getFechaCompra()));
        ps.setString(4, modelo.getEstadoCompra().name());
        ps.setInt(5,modelo.getEntrega().getIdEnvio());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, CompraDTO modelo) throws SQLException {
        ps.setInt(5,modelo.getIdCompra());
        ps.setDouble(1,modelo.getPrecioTotal());
        ps.setTimestamp(2, Timestamp.valueOf(modelo.getFechaCompra()));
        ps.setString(3, modelo.getEstadoCompra().name());
        ps.setInt(4,modelo.getEntrega().getIdEnvio());
}

    @Override
    protected CompraDTO createFromResultado(ResultSet rs) throws SQLException {
        CompraDTO p = new CompraDTO();
        p.setIdCompra(rs.getInt("idCompra"));
        p.setPrecioTotal(rs.getDouble("precioTotal"));

        Timestamp timestamp = rs.getTimestamp("fechaCompra");
        if (timestamp != null) {
           p.setFechaCompra(timestamp.toLocalDateTime());
        }
        p.setEstadoCompra(EstadoCompraDTO.valueOf(rs.getString("estadoCompra")));

        EnvioDTO envio = new EnvioDTO();
        envio.setIdEnvio(rs.getInt("entrega"));
        p.setEntrega(envio);
        return p;
    }
}
