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
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.dao.DetalleCompraDAO;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.DetalleCompraDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;


public class DetalleCompraDAOImp extends BaseDAOImp<DetalleCompraDTO> implements DetalleCompraDAO{
     
    @Override
    protected String getInsertarQuery() {
        return  "insert into detalleCompra (idDetalleCompra,compra,producto,cantidad,precioUnitario) values (?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update detalleCompra SET compra = ?,producto = ?,cantidad = ?,precioUnitario = ? where idDetalleCompra = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from detalleCompra WHERE idDetalleCompra = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from detalleCompra";
    }

    @Override
    protected String getDeleteQuery() {
        return  "delete from detalleCompra WHERE idDetalleCompra = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, DetalleCompraDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdDetalleCompra());
        ps.setInt(2,modelo.getCompra().getIdCompra());
        ps.setInt(3,modelo.getProducto().getIdProducto());
        ps.setInt(4,modelo.getCantidad());
        ps.setDouble(5,modelo.getPrecioUnitario());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, DetalleCompraDTO modelo) throws SQLException {
        ps.setInt(5,modelo.getIdDetalleCompra());
        ps.setInt(1,modelo.getCompra().getIdCompra());
        ps.setInt(2,modelo.getProducto().getIdProducto());
        ps.setInt(3,modelo.getCantidad());
        ps.setDouble(4,modelo.getPrecioUnitario());
}

    @Override
    protected DetalleCompraDTO createFromResultado(ResultSet rs) throws SQLException {
        DetalleCompraDTO p = new DetalleCompraDTO();
        p.setIdDetalleCompra(rs.getInt("idDetalleCompra"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setPrecioUnitario(rs.getDouble("precioUnitario"));

        CompraDTO compra = new CompraDTO();
        compra.setIdCompra(rs.getInt("compra"));
        p.setCompra(compra);

        ProductoDTO prod = new ProductoDTO();
        prod.setIdProducto(rs.getInt("producto"));
        p.setProducto(prod);                    

        return p;
    }
}
