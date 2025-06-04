package pe.edu.pucp.techshopper.bo;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.daoImp.ProductoDAOImp;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.CategoriaDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public class ProductoBO {

    private final ProductoDAO productoDAO;
    
    public ProductoBO() {
        this.productoDAO = new ProductoDAOImp();
    }
    
    public Integer registrarProducto(Double precio, Integer stock, String nombre, 
                                  String marca, CategoriaDTO categoria, 
                                  String descripcion, Integer idAdministrador) {
        // Validaciones básicas
        if (precio == null || precio <= 0 || stock == null || stock < 0 ||
            nombre == null || nombre.trim().isEmpty() || marca == null || 
            marca.trim().isEmpty() || categoria == null || descripcion == null || 
            idAdministrador == null || idAdministrador <= 0) {
            return -1;
        }
        
        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setNombre(nombre.trim());
        producto.setMarca(marca.trim());
        producto.setCategoria(categoria);
        producto.setDescripcion(descripcion);
        
        // Asignar administrador (solo se necesita el ID)
        AdministradorDTO admin = new AdministradorDTO();
        admin.setIdPersona(idAdministrador);
        producto.setAdministrador(admin);
        
        return productoDAO.insertar(producto);
    }
    
    public Integer actualizarProducto(Integer idProducto, Double precio, Integer stock, 
                                    String nombre, String marca, CategoriaDTO categoria, 
                                    String descripcion) {
        // Validaciones básicas
        if (idProducto == null || idProducto <= 0 || precio == null || precio <= 0 || 
            stock == null || stock < 0 || nombre == null || nombre.trim().isEmpty() || 
            marca == null || marca.trim().isEmpty() || categoria == null) {
            return -1;
        }
        
        ProductoDTO producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return -1;
        }
        
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setNombre(nombre.trim());
        producto.setMarca(marca.trim());
        producto.setCategoria(categoria);
        
        if (descripcion != null) {
            producto.setDescripcion(descripcion);
        }
        
        return productoDAO.modificar(producto);
    }
    
    public Integer eliminarProducto(Integer idProducto) {
        if (idProducto == null || idProducto <= 0) {
            return -1;
        }
        
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(idProducto);
        
        return productoDAO.eliminar(producto);
    }
    
    public ProductoDTO obtenerProductoPorId(Integer idProducto) {
        if (idProducto == null || idProducto <= 0) {
            return null;
        }
        return productoDAO.obtenerPorId(idProducto);
    }
    
    public ArrayList<ProductoDTO> listarTodosProductos() {
        return productoDAO.listarTodos();
    }
    
    public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca){
        return this.productoDAO.listarPor3criterios(nombre,categoria,marca);
    }
    
}