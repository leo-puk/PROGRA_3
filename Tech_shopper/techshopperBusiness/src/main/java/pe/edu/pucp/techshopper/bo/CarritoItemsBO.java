/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.daoImp.CarritoItemsDAOImp;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

/**
 *
 * @author CRISTHIAN
 */
public class CarritoItemsBO {
     private CarritoItemsDAO carritoItemsDAO;
    
    public CarritoItemsBO(){
        this.carritoItemsDAO = new CarritoItemsDAOImp();
    }
    
    Integer insertar(CarritoDTO carrito,ProductoDTO producto,Integer cantidad,Double precioUnitario,
            LocalDateTime fechaRegistro){
        CarritoItemsDTO carritoItemsDTO = new CarritoItemsDTO();
        carritoItemsDTO.setCantidad(cantidad);
        carritoItemsDTO.setCarrito(carrito);
        carritoItemsDTO.setFechaRegistro(fechaRegistro);
        carritoItemsDTO.setPrecioUnitario(precioUnitario);
        carritoItemsDTO.setProducto(producto);
        return this.carritoItemsDAO.insertar(carritoItemsDTO);
    }
    
    Boolean modificar(Integer idCarritoItems,CarritoDTO carrito,ProductoDTO producto,Integer cantidad,Double precioUnitario,
            LocalDateTime fechaRegistro){
        CarritoItemsDTO carritoItemsDTO = new CarritoItemsDTO();
        carritoItemsDTO.setIdCarritoItems(idCarritoItems);
        carritoItemsDTO.setCantidad(cantidad);
        carritoItemsDTO.setCarrito(carrito);
        carritoItemsDTO.setFechaRegistro(fechaRegistro);
        carritoItemsDTO.setPrecioUnitario(precioUnitario);
        carritoItemsDTO.setProducto(producto);
        return this.carritoItemsDAO.modificar(carritoItemsDTO);
    }
    
    Boolean  eliminar(int id){
        return this.carritoItemsDAO.eliminar(id);
    }
    
    CarritoItemsDTO buscar(int id){
        return this.carritoItemsDAO.buscar(id);
    }
    
    
    List<CarritoItemsDTO> listar(){
        return this.carritoItemsDAO.listar();
    }
}
