/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.bo.ProductoBO;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
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
    
    @WebMethod(operationName = "registrarProductosssss")
    public Integer registrarProducto(@WebParam(name = "precio")Double precio, 
                                     @WebParam(name = "stockDisponible")Integer stockDisponible,
                                     @WebParam(name = "stockMinimo")Integer stockMinimo,
                                     @WebParam(name = "nombre")String nombre, 
                                     @WebParam(name = "marca")String marca, 
                                     @WebParam(name = "categoria")String categoria, 
                                     @WebParam(name = "descripcion")String descripcion,
                                     @WebParam(name = "idAdministador")Integer idUsuario) {
        
        Integer idAdministrador=2;
        
        CategoriaDTO cat = null;
        
        if(categoria.equalsIgnoreCase(CategoriaDTO.AUDIFONOS.name())) {
            cat = CategoriaDTO.AUDIFONOS;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.AURICULARES.name())) {
            cat = CategoriaDTO.AURICULARES;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.LAPTOP.name())) {
            cat = CategoriaDTO.LAPTOP;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.MICROFONO.name())) {
            cat = CategoriaDTO.MICROFONO;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.MONITOR.name())) {
            cat = CategoriaDTO.MONITOR;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.MOUSE.name())) {
            cat = CategoriaDTO.MOUSE;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.PARLANTES.name())) {
            cat = CategoriaDTO.PARLANTES;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.PC.name())) {
            cat = CategoriaDTO.PC;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.TECLADO.name())) {
            cat = CategoriaDTO.TECLADO;
        }
        
        return productoBo.registrarProducto(precio, stockDisponible,stockMinimo, nombre, marca, cat, descripcion, idUsuario);
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public Integer actualizarProducto(@WebParam(name = "idProducto")Integer idProducto, @WebParam(name = "precio")Double precio, @WebParam(name = "stock")Integer stock, 
                                    @WebParam(name = "nombre")String nombre, @WebParam(name = "marca")String marca, @WebParam(name = "categoria")String categoria, 
                                    @WebParam(name = "descripcion")String descripcion) {
        
        CategoriaDTO cat = null;
        
        if(categoria.equalsIgnoreCase(CategoriaDTO.AUDIFONOS.name())) {
            cat = CategoriaDTO.AUDIFONOS;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.AURICULARES.name())) {
            cat = CategoriaDTO.AURICULARES;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.LAPTOP.name())) {
            cat = CategoriaDTO.LAPTOP;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.MICROFONO.name())) {
            cat = CategoriaDTO.MICROFONO;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.MONITOR.name())) {
            cat = CategoriaDTO.MONITOR;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.MOUSE.name())) {
            cat = CategoriaDTO.MOUSE;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.PARLANTES.name())) {
            cat = CategoriaDTO.PARLANTES;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.PC.name())) {
            cat = CategoriaDTO.PC;
        }
        if(categoria.equalsIgnoreCase(CategoriaDTO.TECLADO.name())) {
            cat = CategoriaDTO.TECLADO;
        }
        
        
        return this.productoBo.actualizarProducto(idProducto, precio, stock, nombre, marca, cat, descripcion);
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public Integer eliminarProducto(@WebParam(name = "idProducto")Integer idProducto) {
        return this.productoBo.eliminarProducto(idProducto);
    }
    
    @WebMethod(operationName = "obtenerProductoPorId")
    public ProductoDTO obtenerProductoPorId(@WebParam(name = "idProducto")Integer idProducto) {
        return this.productoBo.obtenerProductoPorId(idProducto);
    }
    
    @WebMethod(operationName = "listarTodosProductos")
    public ArrayList<ProductoDTO> listarTodosProductos() {
        return this.productoBo.listarTodosProductos();
    }
    
    @WebMethod(operationName = "listarPor3criterios")
    public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca){
        return this.productoBo.listarPor3criterios(nombre,categoria,marca);
    }
    
    
}