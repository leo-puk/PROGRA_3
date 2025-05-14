/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.util.List;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.daoImp.ProductoDAOImp;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.CategoriaDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

/**
 *
 * @author CRISTHIAN
 */
public class ProductoBO {
    private ProductoDAO productoDAO;
    
    public ProductoBO (){
        this.productoDAO = new ProductoDAOImp();
    }
    
    public Integer insertar(Double precio, Integer stock, String nombre, String marca, 
            CategoriaDTO categoria, String descripcion, AdministradorDTO administrador){
        ProductoDTO productosDTO = new ProductoDTO();
//        productosDTO.setIdProducto(id);
        productosDTO.setPrecio(precio);
        productosDTO.setStock(stock);
        productosDTO.setNombre(nombre);
        productosDTO.setMarca(marca);
        productosDTO.setDescripcion(descripcion);
        productosDTO.setCategoria(categoria);
        productosDTO.setAdministrador(administrador);
        
        return this.productoDAO.insertar(productosDTO);
        
    }
    
    public boolean modificar(Integer productoId, Double precio, Integer stock, String nombre, String marca, 
            CategoriaDTO categoria, String descripcion, AdministradorDTO administrador){
        ProductoDTO productosDTO = new ProductoDTO();
        productosDTO.setIdProducto(productoId);
        productosDTO.setPrecio(precio);
        productosDTO.setStock(stock);
        productosDTO.setNombre(nombre);
        productosDTO.setMarca(marca);
        productosDTO.setDescripcion(descripcion);
        productosDTO.setCategoria(categoria);
        productosDTO.setAdministrador(administrador);
        
        return this.productoDAO.modificar(productosDTO);
    }
    
    public boolean eliminar (Integer productoId){
//        ProductoDTO productosDTO = new ProductoDTO();
//        productosDTO.setIdProducto(productoId);
        
        return this.productoDAO.eliminar(productoId);
    } 
    
    public ProductoDTO buscar (Integer productoId){
        return this.productoDAO.buscar(productoId);
    }
    
    public List<ProductoDTO> listar(){
        return this.productoDAO.listar();
    }
}
