/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import techshopper.config.DBManager;
import techshopper.dto.CarritoDTO;
import techshopper.dto.CarritoItemsDTO;
import techshopper.dto.ProductoDTO;


public class CarritoItemsDAOImp implements CarritoItemsDAO{
        @Override
    public void Insertar(CarritoItemsDTO p){
        String query = "insert into CarritoItems (idCarritoItems,carrito,producto,cantidad,precioUnitario,fechaRegistro) values (?,?,?,?,?,?)";

        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,p.getIdCarritoItems());
            ps.setInt(2,p.getCarrito().getIdCarrito());
            ps.setInt(3,p.getProducto().getIdProducto());
            ps.setInt(4,p.getCantidad());
            ps.setDouble(5,p.getPrecioUnitario());
            ps.setTimestamp(6, Timestamp.valueOf(p.getFechaRegistro()));

            ps.executeUpdate();
            System.out.println("Items del carrito insertados correctamente");
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Modificar(CarritoItemsDTO p){
        String query = "update CarritoItems SET compra = ?,producto = ?,cantidad = ?,precioUnitario = ?,fechaRegistro = ? where idCarritoItems = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
             
            ps.setInt(1,p.getIdCarritoItems());
            ps.setInt(2,p.getCarrito().getIdCarrito());
            ps.setInt(3,p.getProducto().getIdProducto());
            ps.setInt(4,p.getCantidad());
            ps.setDouble(5,p.getPrecioUnitario());
            ps.setTimestamp(6, Timestamp.valueOf(p.getFechaRegistro()));
            
            int filas = ps.executeUpdate();
            
            if(filas>0){
                 System.out.println("Items del carrito modificados correctamente");
            }else{
                System.out.println("No se encontró una entrada con ese ID");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Eliminar(int id){
        String query = "delete from CarritoItems WHERE idCarritoItems = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if(filas>0){
                 System.out.println("Items del carrito eliminados correctamente");
            }else{
                System.out.println("No se encontró una entrada con ese ID");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public CarritoItemsDTO ObtenerPorId(int id){
        String query = "select * from CarritoItems WHERE idCarritoItems = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                
                //Ahora creamos el objeto obtenido
                if(rs.next()){
                    CarritoItemsDTO p = new CarritoItemsDTO();
                    p.setIdCarritoItems(rs.getInt("idCarritoItems"));
                    p.setCantidad(rs.getInt("cantidad"));
                    p.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    
                    CarritoDTO car = new CarritoDTO();
                    car.setIdCarrito(rs.getInt("carrito"));
                    p.setCarrito(car);

                    ProductoDTO prod = new ProductoDTO();
                    prod.setIdProducto(rs.getInt("producto"));
                    p.setProducto(prod);                    
                    
                    return p;
                }else{
                    System.out.println("No se encontró una entrada con ese ID");
                    return null;
                }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<CarritoItemsDTO> ListarTodos(){
        ArrayList<CarritoItemsDTO> lista = new ArrayList<>();
        String query = "select * from CarritoItems";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                    CarritoItemsDTO p = new CarritoItemsDTO();
                    p.setIdCarritoItems(rs.getInt("idCarritoItems"));
                    p.setCantidad(rs.getInt("cantidad"));
                    p.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    
                    Timestamp ts = rs.getTimestamp("fechaRegistro");
                    if(ts != null) {
                        p.setFechaRegistro(ts.toLocalDateTime());
                    }

                    CarritoDTO car = new CarritoDTO();
                    car.setIdCarrito(rs.getInt("carrito"));
                    p.setCarrito(car);

                    ProductoDTO prod = new ProductoDTO();
                    prod.setIdProducto(rs.getInt("producto"));
                    p.setProducto(prod);  
                lista.add(p);
            }
            return lista;
        }catch(SQLException ex){
            ex.printStackTrace();
            return lista;
        }
        
    }
}
