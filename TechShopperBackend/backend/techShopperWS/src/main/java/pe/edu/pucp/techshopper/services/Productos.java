package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.bo.ProductoBO;
import pe.edu.pucp.techshopper.model.CategoriaDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

/**
 *
 * @author melow
 */
@WebService(serviceName = "Productos")
public class Productos {

    private ProductoBO productoBo; 
 
    public Productos(){
        this.productoBo = new ProductoBO();
    }
    
    @WebMethod(operationName = "registrarProducto")
    public Integer registrarProducto(@WebParam(name = "precio") Double precio, 
                                   @WebParam(name = "stockDisponible") Integer stockDisponible,
                                   @WebParam(name = "stockMinimo") Integer stockMinimo,
                                   @WebParam(name = "nombre") String nombre, 
                                   @WebParam(name = "marca") String marca, 
                                   @WebParam(name = "categoria") String categoria, 
                                   @WebParam(name = "descripcion") String descripcion,
                                   @WebParam(name = "imagenURL") String imagenURL,
                                   @WebParam(name = "idUsuario") Integer idUsuario) {
        
        CategoriaDTO cat = obtenerCategoriaDesdeString(categoria);
        if (cat == null) {
            return -1; // Categoría no válida
        }
        
        return productoBo.registrarProducto(precio, stockDisponible, stockMinimo, 
                                          nombre, marca, cat, descripcion, 
                                          imagenURL, idUsuario);
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public Integer actualizarProducto(@WebParam(name = "idProducto") Integer idProducto, 
                                    @WebParam(name = "precio") Double precio, 
                                    @WebParam(name = "stockDisponible") Integer stockDisponible, 
                                    @WebParam(name = "stockMinimo") Integer stockMinimo, 
                                    @WebParam(name = "nombre") String nombre, 
                                    @WebParam(name = "marca") String marca, 
                                    @WebParam(name = "categoria") String categoria, 
                                    @WebParam(name = "descripcion") String descripcion,
                                    @WebParam(name = "imagenURL") String imagenURL) {
        
        CategoriaDTO cat = obtenerCategoriaDesdeString(categoria);
        if (cat == null) {
            return -1; // Categoría no válida
        }
        
        return this.productoBo.actualizarProducto(idProducto, precio,stockDisponible, stockMinimo, 
                                                nombre, marca, cat, descripcion, 
                                                imagenURL);
    }
    
    @WebMethod(operationName = "actualizarImagenProducto")
    public Integer actualizarImagenProducto(@WebParam(name = "idProducto") Integer idProducto,
                                          @WebParam(name = "imagenURL") String imagenURL) {
        return this.productoBo.actualizarImagenProducto(idProducto, imagenURL);
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public Integer eliminarProducto(@WebParam(name = "idProducto") Integer idProducto) {
        return this.productoBo.eliminarProducto(idProducto);
    }
    
    @WebMethod(operationName = "obtenerProductoPorId")
    public ProductoDTO obtenerProductoPorId(@WebParam(name = "idProducto") Integer idProducto) {
        return this.productoBo.obtenerProductoPorId(idProducto);
    }
    
    @WebMethod(operationName = "listarTodosProductos")
    public ArrayList<ProductoDTO> listarTodosProductos() {
        return this.productoBo.listarTodosProductos();
    }
    
    @WebMethod(operationName = "listarPor3criterios")
    public List<ProductoDTO> listarPor3criterios(@WebParam(name = "nombre") String nombre, 
                                               @WebParam(name = "categoria") String categoria, 
                                               @WebParam(name = "marca") String marca) {
        return this.productoBo.listarPor3criterios(nombre, categoria, marca);
    }
    
    // Método auxiliar para convertir String a CategoriaDTO
    private CategoriaDTO obtenerCategoriaDesdeString(String categoria) {
        if (categoria == null) return null;
        
        try {
            return CategoriaDTO.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    @WebMethod(operationName = "verificarCambioStock")
    public Integer verificarCambioStock(@WebParam(name = "idProducto") Integer idProducto,
                                      @WebParam(name = "nuevoStock") Integer nuevoStock) {
        return this.productoBo.verificarCambioStock(idProducto, nuevoStock);
    }
}