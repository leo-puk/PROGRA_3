 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;

import com.mysql.cj.xdevapi.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;


public class CarritoDAOImp extends BaseDAOImp <CarritoDTO> implements CarritoDAO{
    public CarritoDAOImp(){ 

    }
    
    @Override
    protected String getInsertarQuery() {
        return "insert into carrito (idCarrito, precio,idPersona_carrito) values (?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update carrito SET precio = ?,idCliente_carrito = ? where idCarrito = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from carrito WHERE idCarrito = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return  "select * from carrito"; 
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from carrito WHERE idCarrito = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, CarritoDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdCarrito());
        ps.setDouble(2,modelo.getPrecio());
        ps.setInt(3,modelo.getCliente().getIdPersona());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, CarritoDTO modelo) throws SQLException {
        ps.setDouble(1,modelo.getPrecio());
        ps.setInt(2,modelo.getCliente().getIdPersona());
        ps.setInt(3,modelo.getIdCarrito());
    }

    @Override
    protected CarritoDTO createFromResultado(ResultSet rs) throws SQLException {
        CarritoDTO p = new CarritoDTO();
        p.setIdCarrito(rs.getInt("idCarrito"));
        p.setPrecio(rs.getDouble("precio"));

        ClienteDTO cli = new ClienteDTO();
        cli.setIdPersona(rs.getInt("idCliente_carrito"));
        p.setCliente(cli);

        return p;
    }
}
