
package techshopper.dao;
import techshopper.config.DBManager;
import techshopper.dto.ProductoDTO;
import techshopper.dto.CategoriaDTO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProductoDAOImp implements ProductoDAO{
    
    public ProductoDAOImp(){ //Constructor Producto implementacion

    }
    
    @Override
    //Categoria enum
    public void Insertar(ProductoDTO p){
        String query = "insert into Producto (idProducto,precio,stock,nombre,marca,categoria,descripcion) values (?,?,?,?,?,?,?)";
        //String query = "insert into Producto (id,nombre) values (?, ?)";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,p.getIdProducto());
            ps.setDouble(2,p.getPrecio());
            ps.setInt(3,p.getStock());
            ps.setString(4,p.getNombre());
            ps.setString(5,p.getMarca());
            ps.setString(6, p.getCategoria().name());
            ps.setString(7,p.getDescripcion());
            ps.executeUpdate();
            System.out.println("Productos insertados correctamente");
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Modificar(ProductoDTO p){
        String query = "update Producto SET precio = ?, stock = ?, nombre = ?, marca = ?, categoria = ?, descripcion = ? where idProducto = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
             
            ps.setDouble(1,p.getPrecio());
            ps.setInt(2,p.getStock());
            ps.setString(3,p.getNombre());
            ps.setString(4,p.getMarca());
            ps.setString(5, p.getCategoria().name());
            ps.setString(6,p.getDescripcion());
            ps.setInt(7,p.getIdProducto());
            
            int filas = ps.executeUpdate();
            
            if(filas>0){
                 System.out.println("Producto modificado correctamente");
            }else{
                System.out.println("No se encontró un producto con ese ID");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void Eliminar(int id){
        String query = "delete from Producto WHERE idProducto = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if(filas>0){
                 System.out.println("Producto eliminado correctamente");
            }else{
                System.out.println("No se encontró un producto con ese ID");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public ProductoDTO ObtenerPorId(int id){
        String query = "select * from Producto WHERE idProducto = ?";
        try(Connection con = DBManager.getInstance().getConnection()){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                
                //Ahora creamos el objeto obtenido
                if(rs.next()){
                    ProductoDTO p = new ProductoDTO();
                    p.setIdProducto(rs.getInt("idProducto"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setCategoria(CategoriaDTO.valueOf(rs.getString("categoria")));
                    p.setMarca(rs.getString("marca"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));
                    
                    return p;
                }else{
                    System.out.println("No se encontró un producto con ese ID");
                    return null;
                }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<ProductoDTO> ListarTodos(){
        ArrayList<ProductoDTO> lista = new ArrayList<>();
        String query = "select * from Producto";
        try(Connection con = DBManager.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ProductoDTO p = new ProductoDTO();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(CategoriaDTO.valueOf(rs.getString("categoria")));
                p.setMarca(rs.getString("marca"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                lista.add(p);
            }
            return lista;
        }catch(SQLException ex){
            ex.printStackTrace();
            return lista;
        }
        
    }
}
