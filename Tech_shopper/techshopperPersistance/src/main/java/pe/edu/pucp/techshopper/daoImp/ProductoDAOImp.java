
package pe.edu.pucp.techshopper.daoImp;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.CategoriaDTO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.model.AdministradorDTO;

public class ProductoDAOImp extends BaseDAOImp <ProductoDTO> implements ProductoDAO{
    
    public ProductoDAOImp(){ //Constructor Producto implementacion

    }
    
    @Override
    protected String getInsertarQuery() {
        return  "insert into producto (idProducto,precio,stock,nombre,marca,categoria,descripcion, idAdministrador_producto) values (?,?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update producto SET precio = ?, stock = ?, nombre = ?, marca = ?, categoria = ?, descripcion = ?, idAdministrador_producto = ? where idProducto = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from producto WHERE idProducto = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from producto";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from producto WHERE idProducto = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, ProductoDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdProducto());
        ps.setDouble(2,modelo.getPrecio());
        ps.setInt(3,modelo.getStock());
        ps.setString(4,modelo.getNombre());
        ps.setString(5,modelo.getMarca());
        ps.setString(6, modelo.getCategoria().name());
        ps.setString(7,modelo.getDescripcion());
        ps.setInt(8,modelo.getAdministrador().getIdPersona());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, ProductoDTO modelo) throws SQLException {
        ps.setDouble(1,modelo.getPrecio());
        ps.setInt(2,modelo.getStock());
        ps.setString(3,modelo.getNombre());
        ps.setString(4,modelo.getMarca());
        ps.setString(5, modelo.getCategoria().name());
        ps.setString(6,modelo.getDescripcion());
        ps.setInt(7,modelo.getAdministrador().getIdPersona());
        ps.setInt(8,modelo.getIdProducto());
    }

    @Override
    protected ProductoDTO createFromResultado(ResultSet rs) throws SQLException {
        ProductoDTO p = new ProductoDTO();
        p.setIdProducto(rs.getInt("idProducto"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setCategoria(CategoriaDTO.valueOf(rs.getString("categoria")));
        p.setMarca(rs.getString("marca"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));

        AdministradorDTO admin = new AdministradorDTO();
        admin.setIdPersona(rs.getInt("idAdministrador_producto"));
        p.setAdministrador(admin);
        return p;
    }

}
