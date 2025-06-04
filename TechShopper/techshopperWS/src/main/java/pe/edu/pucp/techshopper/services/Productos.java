/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
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
    
    
//    @WebMethod
//    public String saludar(String nombre) {
//        return "Hola, " + nombre;
//    }

    
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "registrarProductosssss")
    public Integer registrarProducto(@WebParam(name = "precio")Double precio, @WebParam(name = "stock")Integer stock, @WebParam(name = "nombre")String nombre, 
                                  @WebParam(name = "marca")String marca, @WebParam(name = "categoria")CategoriaDTO categoria, 
                                  @WebParam(name = "descripcion")String descripcion) {
        
        Integer idAdministrador=1;
        return productoBo.registrarProducto(precio, stock, nombre, marca, categoria, descripcion, idAdministrador);
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public Integer actualizarProducto(@WebParam(name = "idProducto")Integer idProducto, @WebParam(name = "precio")Double precio, @WebParam(name = "stock")Integer stock, 
                                    @WebParam(name = "nombre")String nombre, @WebParam(name = "marca")String marca, @WebParam(name = "categoria")CategoriaDTO categoria, 
                                    @WebParam(name = "descripcion")String descripcion) {
        
        return this.productoBo.actualizarProducto(idProducto, precio, stock, nombre, marca, categoria, descripcion);
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
    
    
}