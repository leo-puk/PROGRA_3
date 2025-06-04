package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.CarritoItemsBO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;

@WebService(serviceName = "CarritoItemsWS")
public class CarritoItems {

//    private CarritoItemsBO carritoItemsBO;
//
//    public CarritoItems() {
//        this.carritoItemsBO = new CarritoItemsBO();
//    }
//
//    @WebMethod(operationName = "agregarItemAlCarrito")
//    public Integer agregarItemAlCarrito(
//            @WebParam(name = "idCarrito") Integer idCarrito,
//            @WebParam(name = "idProducto") Integer idProducto,
//            @WebParam(name = "cantidad") Integer cantidad,
//            @WebParam(name = "precioUnitario") Double precioUnitario) {
//        
//        return carritoItemsBO.agregarProductoAlCarrito(idCarrito, idProducto, cantidad, precioUnitario);
//    }
//
//    @WebMethod(operationName = "modificarItemCarrito")
//    public Integer modificarItemCarrito(
//            @WebParam(name = "idItemCarrito") Integer idItemCarrito,
//            @WebParam(name = "idCarrito") Integer idCarrito,
//            @WebParam(name = "idProducto") Integer idProducto,
//            @WebParam(name = "cantidad") Integer cantidad,
//            @WebParam(name = "precioUnitario") Double precioUnitario) {
//        
//        return carritoItemsBO.modificar(idItemCarrito, idCarrito, idProducto, cantidad, 
//                                      precioUnitario, LocalDateTime.now());
//    }
//
//    @WebMethod(operationName = "eliminarItemCarrito")
//    public Integer eliminarItemCarrito(
//            @WebParam(name = "idItemCarrito") Integer idItemCarrito) {
//        return carritoItemsBO.eliminar(idItemCarrito);
//    }
//
//    @WebMethod(operationName = "obtenerItemPorId")
//    public CarritoItemsDTO obtenerItemPorId(
//            @WebParam(name = "idItemCarrito") Integer idItemCarrito) {
//        return carritoItemsBO.obtenerPorId(idItemCarrito);
//    }
//
//    @WebMethod(operationName = "listarItemsPorCarrito")
//    public ArrayList<CarritoItemsDTO> listarItemsPorCarrito(
//            @WebParam(name = "idCarrito") Integer idCarrito) {
//        return carritoItemsBO.listarPorCarrito(idCarrito);
//    }
//
//    @WebMethod(operationName = "calcularTotalCarrito")
//    public Double calcularTotalCarrito(
//            @WebParam(name = "idCarrito") Integer idCarrito) {
//        return carritoItemsBO.calcularTotalCarrito(idCarrito);
//    }
//
//    @WebMethod(operationName = "actualizarCantidadItem")
//    public Integer actualizarCantidadItem(
//            @WebParam(name = "idCarrito") Integer idCarrito,
//            @WebParam(name = "idProducto") Integer idProducto,
//            @WebParam(name = "nuevaCantidad") Integer nuevaCantidad) {
//        return carritoItemsBO.actualizarCantidadProducto(idCarrito, idProducto, nuevaCantidad);
//    }
//
//    @WebMethod(operationName = "eliminarProductoDelCarrito")
//    public Integer eliminarProductoDelCarrito(
//            @WebParam(name = "idCarrito") Integer idCarrito,
//            @WebParam(name = "idProducto") Integer idProducto) {
//        return carritoItemsBO.eliminarProductoDelCarrito(idCarrito, idProducto);
//    }
//
//    @WebMethod(operationName = "vaciarCarrito")
//    public Integer vaciarCarrito(
//            @WebParam(name = "idCarrito") Integer idCarrito) {
//        return carritoItemsBO.vaciarCarrito(idCarrito);
//    }
//
//    @WebMethod(operationName = "buscarItemEnCarrito")
//    public CarritoItemsDTO buscarItemEnCarrito(
//            @WebParam(name = "idCarrito") Integer idCarrito,
//            @WebParam(name = "idProducto") Integer idProducto) {
//        return carritoItemsBO.buscarItemEnCarrito(idCarrito, idProducto);
//    }
}