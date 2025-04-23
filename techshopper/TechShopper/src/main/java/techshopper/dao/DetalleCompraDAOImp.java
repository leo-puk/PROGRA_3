/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import techshopper.config.DBManager;
import techshopper.dto.CompraDTO;
import techshopper.dto.DetalleCompraDTO;
import techshopper.dto.ProductoDTO;


public class DetalleCompraDAOImp implements DetalleCompraDAO{
     
    private Connection conexion;
    private CallableStatement statement;
    private ResultSet resultSet;
    
    @Override
    public Integer Insertar(DetalleCompraDTO p){
        Integer resultado = 0;
        this.conexion = DBManager.getInstance().getConnection();
        String query = "insert into DetalleCompra (idDetalleCompra,compra,producto,cantidad,precioUnitario) values (?,?,?,?,?)";

        try{
            this.statement = this.conexion.prepareCall(query);
            this.conexion.setAutoCommit(false);
            this.statement.setInt(1, p.getIdDetalleCompra());
            this.statement.setInt(2, p.getCompra().getIdCompra());
            this.statement.setInt(3, p.getProducto().getIdProducto());
            this.statement.setInt(4, p.getCantidad());
            this.statement.setDouble(5, p.getPrecioUnitario());
            this.statement.executeUpdate();
            
            System.out.println("DetalleCompra insertada correctamente");
            this.conexion.commit();
            resultado = 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            if(this.conexion != null){
                try {
                    this.conexion.close();
                } catch (SQLException ex){
                    Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return resultado;
    }
    
    @Override
    public void Modificar(DetalleCompraDTO p){
        String query = "update DetalleCompra SET compra = ?,producto = ?,cantidad = ?,precioUnitario = ? where idDetalleCompra = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
             
            ps.setInt(5,p.getIdDetalleCompra());
            ps.setInt(1,p.getCompra().getIdCompra());
            ps.setInt(2,p.getProducto().getIdProducto());
            ps.setInt(3,p.getCantidad());
            ps.setDouble(4,p.getPrecioUnitario());
            
            int filas = ps.executeUpdate();
            
            if(filas>0){
                 System.out.println("DetalleCompra modificada correctamente");
            }else{
                System.out.println("No se encontró un DetalleCompra con ese ID");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Eliminar(int id){
        String query = "delete from DetalleCompra WHERE idDetalleCompra = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if(filas>0){
                 System.out.println("DetalleCompra eliminado correctamente");
            }else{
                System.out.println("No se encontró un DetalleCompra con ese ID");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public DetalleCompraDTO ObtenerPorId(int id){
        String query = "select * from DetalleCompra WHERE idDetalleCompra = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                
                //Ahora creamos el objeto obtenido
                if(rs.next()){
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
                }else{
                    System.out.println("No se encontró un DetalleCompra con ese ID");
                    return null;
                }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<DetalleCompraDTO> ListarTodos(){
        ArrayList<DetalleCompraDTO> lista = new ArrayList<>();
        String query = "select * from DetalleCompra";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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
                lista.add(p);
            }
            return lista;
        }catch(SQLException ex){
            ex.printStackTrace();
            return lista;
        }
        
    }
}
