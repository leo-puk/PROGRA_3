 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techshopper.config.DBManager;
import techshopper.dto.CarritoDTO;


public class CarritoDAOImp implements CarritoDAO{
    public CarritoDAOImp(){ 

    }
    
    @Override
    //Categoria enum
    public void Insertar(CarritoDTO p){
        String query = "insert into Carrito (idCarrito, precio) values (?,?)";
        //String query = "insert into Producto (id,nombre) values (?, ?)";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,p.getIdCarrito());
            ps.setDouble(2,p.getPrecio());

            ps.executeUpdate();
            System.out.println("Carrito insertados correctamente");
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Modificar(CarritoDTO p){
        String query = "update Producto SET precio = ? where idCarrito = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
             
            ps.setDouble(1,p.getPrecio());
            ps.setInt(2,p.getIdCarrito());

            
            int filas = ps.executeUpdate();
            
            if(filas>0){
                 System.out.println("Carrito modificado correctamente");
            }else{
                System.out.println("No se encontró un carrito con ese ID");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Eliminar(int id){
        String query = "delete from Carrito WHERE idCarrito = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if(filas>0){
                 System.out.println("Carrito eliminado correctamente");
            }else{
                System.out.println("No se encontró un carrito con ese ID");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public CarritoDTO ObtenerPorId(int id){
        String query = "select * from Carrito WHERE idCarrito = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                
                //Ahora creamos el objeto obtenido
                if(rs.next()){
                    CarritoDTO p = new CarritoDTO();
                    p.setIdCarrito(rs.getInt("idCarrito"));
                    p.setPrecio(rs.getDouble("precio"));
                    
                    return p;
                }else{
                    System.out.println("No se encontró un carrito con ese ID");
                    return null;
                }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<CarritoDTO> ListarTodos(){
        ArrayList<CarritoDTO> lista = new ArrayList<>();
        String query = "select * from Producto";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CarritoDTO p = new CarritoDTO();
                p.setIdCarrito(rs.getInt("idCarrito"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);
            }
            return lista;
        }catch(SQLException ex){
            ex.printStackTrace();
            return lista;
        }
        
    }
}
