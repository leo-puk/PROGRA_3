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
import java.sql.Timestamp;
import techshopper.config.DBManager;
import techshopper.dto.CompraDTO;
import techshopper.dto.CarritoDTO;
import techshopper.dto.EnvioDTO;
import techshopper.dto.EstadoCompraDTO;


public class CompraDAOImp implements CompraDAO{
    public CompraDAOImp(){ 

    }
    
    @Override
    //Categoria enum
    public void Insertar(CompraDTO p){
        String query = "insert into Compra (idCompra, carrito, precioTotal, fechaCompra, estadoCompra, entrega) values (?,?,?,?,?,?)";

        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,p.getIdCompra());
            ps.setInt(2,p.getCarrito().getIdCarrito());
            ps.setDouble(3,p.getPrecioTotal());
            ps.setTimestamp(4, Timestamp.valueOf(p.getFechaCompra()));
            ps.setString(5, p.getEstadoCompra().name());
            ps.setInt(6,p.getEntrega().getIdEnvio());
            ps.executeUpdate();
            System.out.println("Compra insertada correctamente");
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Modificar(CompraDTO p){
        String query = "update Compra SET carrito = ?, precioTotal = ?, fechaCompra = ?, estadoCompra = ?, entrega = ? where idCompra = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
             
            ps.setInt(6,p.getIdCompra());
            ps.setInt(1,p.getCarrito().getIdCarrito());
            ps.setDouble(2,p.getPrecioTotal());
            ps.setTimestamp(3, Timestamp.valueOf(p.getFechaCompra()));
            ps.setString(4, p.getEstadoCompra().name());
            ps.setInt(5,p.getEntrega().getIdEnvio());

            
            int filas = ps.executeUpdate();
            
            if(filas>0){
                 System.out.println("Compra modificado correctamente");
            }else{
                System.out.println("No se encontró una compra con ese ID");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Eliminar(int id){
        String query = "delete from Compra WHERE idCompra = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if(filas>0){
                 System.out.println("Compra eliminado correctamente");
            }else{
                System.out.println("No se encontró una compra con ese ID");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public CompraDTO ObtenerPorId(int id){
        String query = "select * from Compra WHERE idCompra = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                
                //Ahora creamos el objeto obtenido
                if(rs.next()){
                    CompraDTO p = new CompraDTO();
                    p.setIdCompra(rs.getInt("idCompra"));
                    p.setPrecioTotal(rs.getDouble("precioTotal"));
                    
                    Timestamp timestamp = rs.getTimestamp("fechaCompra");
                    if (timestamp != null) {
                       p.setFechaCompra(timestamp.toLocalDateTime());
                    }
                    p.setEstadoCompra(EstadoCompraDTO.valueOf(rs.getString("estadoCompra")));
                    
                    CarritoDTO carrito = new CarritoDTO();
                    carrito.setIdCarrito(rs.getInt("carrito"));
                    p.setCarrito(carrito);

                    EnvioDTO envio = new EnvioDTO();
                    envio.setIdEnvio(rs.getInt("entrega"));
                    p.setEntrega(envio);
                    return p;
                    
                }else{
                    System.out.println("No se encontró una compra con ese ID");
                    return null;
                }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<CompraDTO> ListarTodos(){
        ArrayList<CompraDTO> lista = new ArrayList<>();
        String query = "select * from Compra";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CompraDTO p = new CompraDTO();
                p.setIdCompra(rs.getInt("idCompra"));
                p.setPrecioTotal(rs.getDouble("precioTotal"));
                    
                Timestamp timestamp = rs.getTimestamp("fechaCompra");
                
                if (timestamp != null) {
                  p.setFechaCompra(timestamp.toLocalDateTime());
                }
                p.setEstadoCompra(EstadoCompraDTO.valueOf(rs.getString("estadoCompra")));
                    
                CarritoDTO carrito = new CarritoDTO();
                carrito.setIdCarrito(rs.getInt("carrito"));
                p.setCarrito(carrito);

                EnvioDTO envio = new EnvioDTO();
                envio.setIdEnvio(rs.getInt("entrega"));
                p.setEntrega(envio);
                lista.add(p);
            }
            return lista;
        }catch(SQLException ex){
            ex.printStackTrace();
            return lista;
        }
        
    }
}
