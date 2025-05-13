/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;


public class CarritoItemsDAOImp extends BaseDAOImp <CarritoItemsDTO> implements CarritoItemsDAO{
    
    @Override
    protected String getInsertarQuery() {
        return  "insert into CarritoItems (idCarritoItems,idCarrito,idProducto,cantidad,precioUnitario,fechaRegistro) values (?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update CarritoItems SET idCarrito = ?,idProducto = ?,cantidad = ?,precioUnitario = ?,fechaRegistro = ? where idCarritoItems = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from CarritoItems WHERE idCarritoItems = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from CarritoItems";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from CarritoItems WHERE idCarritoItems = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, CarritoItemsDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdCarritoItems());
        ps.setInt(2,modelo.getCarrito().getIdCarrito());
        ps.setInt(3,modelo.getProducto().getIdProducto());
        ps.setInt(4,modelo.getCantidad());
        ps.setDouble(5,modelo.getPrecioUnitario());
        ps.setTimestamp(6, Timestamp.valueOf(modelo.getFechaRegistro()));
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, CarritoItemsDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdCarritoItems());
            ps.setInt(2,modelo.getCarrito().getIdCarrito());
            ps.setInt(3,modelo.getProducto().getIdProducto());
            ps.setInt(4,modelo.getCantidad());
            ps.setDouble(5,modelo.getPrecioUnitario());
            ps.setTimestamp(6, Timestamp.valueOf(modelo.getFechaRegistro()));
    }

    @Override
    protected CarritoItemsDTO createFromResultado(ResultSet rs) throws SQLException {
        CarritoItemsDTO p = new CarritoItemsDTO();
        p.setIdCarritoItems(rs.getInt("idCarritoItems"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setPrecioUnitario(rs.getDouble("precioUnitario"));

        Timestamp ts = rs.getTimestamp("fechaRegistro");
        if(ts != null) {
            p.setFechaRegistro(ts.toLocalDateTime());
        }

        CarritoDTO car = new CarritoDTO();
        car.setIdCarrito(rs.getInt("idCarrito"));
        p.setCarrito(car);

        ProductoDTO prod = new ProductoDTO();
        prod.setIdProducto(rs.getInt("idProducto"));
        p.setProducto(prod);                    

        return p;
    }
        
}
