package pe.edu.pucp.techshopper.bo;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.daoImp.ProductoDAOImp;
import pe.edu.pucp.techshopper.model.CategoriaDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class ProductoBO {

    private final ProductoDAO productoDAO;
    
    public ProductoBO() {
        this.productoDAO = new ProductoDAOImp();
    }
    
    public Integer registrarProducto(Double precio, Integer stockDisponible, Integer stockMinimo,
                                  String nombre, String marca, CategoriaDTO categoria, 
                                  String descripcion, String imagenURL, Integer idUsuario) {
        // Validaciones básicas
        if (precio == null || precio <= 0 || 
            stockDisponible == null || stockDisponible < 0 ||
            stockMinimo == null || stockMinimo < 0 ||
            nombre == null || nombre.trim().isEmpty() || 
            marca == null || marca.trim().isEmpty() || 
            categoria == null || 
            idUsuario == null || idUsuario <= 0) {
            return -1;
        }
        
        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(precio);
        producto.setStockDisponible(stockDisponible);
        producto.setStockReservado(0); // Nuevo producto, stock reservado inicial en 0
        producto.setStockMinimo(stockMinimo);
        producto.setNombre(nombre.trim());
        producto.setMarca(marca.trim());
        producto.setCategoria(categoria);
        producto.setDescripcion(descripcion != null ? descripcion : "");
        producto.setImagenURL(imagenURL); // Nuevo campo
        
        // Asignar usuario (solo se necesita el ID)
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(idUsuario);
        producto.setUsuario(usuario);
        
        return productoDAO.insertar(producto);
    }
    
    public Integer actualizarProducto(Integer idProducto, Double precio,Integer stockDisponible, Integer stockMinimo,
                                    String nombre, String marca, CategoriaDTO categoria, 
                                    String descripcion, String imagenURL) {
        // Validaciones básicas
        if (idProducto == null || idProducto <= 0 || 
            precio == null || precio <= 0 || 
            stockMinimo == null || stockMinimo < 0 ||  stockDisponible < 0 ||
            nombre == null || nombre.trim().isEmpty() || 
            marca == null || marca.trim().isEmpty() || 
            categoria == null) {
            return -1;
        }
        
        ProductoDTO producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return -1;
        }
        
        producto.setPrecio(precio);
        producto.setStockMinimo(stockMinimo);
        producto.setStockDisponible(stockDisponible);
        producto.setNombre(nombre.trim());
        producto.setMarca(marca.trim());
        producto.setCategoria(categoria);
        
        if (descripcion != null) {
            producto.setDescripcion(descripcion);
        }
        
        if (imagenURL != null) {
            producto.setImagenURL(imagenURL);
        }
        
        return productoDAO.modificar(producto);
    }
    
    public Integer actualizarStock(Integer idProducto, Integer stockDisponible, Integer stockReservado) {
        if (idProducto == null || idProducto <= 0 || 
            stockDisponible == null || stockDisponible < 0 ||
            stockReservado == null || stockReservado < 0) {
            return -1;
        }
        
        ProductoDTO producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return -1;
        }
        
        producto.setStockDisponible(stockDisponible);
        producto.setStockReservado(stockReservado);
        
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
    
    public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca) {
        return productoDAO.listarPor3criterios(nombre, categoria, marca);
    }
    
    // Método adicional para verificar stock disponible
    public boolean verificarStockDisponible(Integer idProducto, Integer cantidadRequerida) {
        if (idProducto == null || idProducto <= 0 || cantidadRequerida == null || cantidadRequerida <= 0) {
            return false;
        }
        
        ProductoDTO producto = productoDAO.obtenerPorId(idProducto);
        return producto != null && producto.getStockDisponible() >= cantidadRequerida;
    }
    
    // Nuevo método para actualizar solo la imagen del producto
    public Integer actualizarImagenProducto(Integer idProducto, String imagenURL) {
        if (idProducto == null || idProducto <= 0 || imagenURL == null) {
            return -1;
        }
        
        ProductoDTO producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return -1;
        }
        
        producto.setImagenURL(imagenURL);
        return productoDAO.modificar(producto);
    }
    public Integer verificarCambioStock(Integer idProducto, Integer nuevoStock) {
        // Validaciones básicas
        if (idProducto == null || idProducto <= 0 || nuevoStock == null || nuevoStock < 0) {
            return null;
        }

        // Verificar que el producto exista
        ProductoDTO producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return null;
        }

        // Llamar al método del DAO
        return productoDAO.verificarCambioStock(idProducto, nuevoStock);
    }
    
}