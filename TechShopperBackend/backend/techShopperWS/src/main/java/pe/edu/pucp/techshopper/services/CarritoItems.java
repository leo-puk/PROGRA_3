package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.CarritoItemsBO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;

@WebService(serviceName = "CarritoItems")
public class CarritoItems {

    private CarritoItemsBO carritoItemsBO;

    public CarritoItems() {
        this.carritoItemsBO = new CarritoItemsBO();
    }

    @WebMethod(operationName = "insertarCarritoItem")
    public Integer insertarCarritoItem(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "cantidad") Integer cantidad,
        @WebParam(name = "precioUnitario") Double precioUnitario,
        @WebParam(name = "fechaRegistro") LocalDateTime fechaRegistro
    ) {
        return carritoItemsBO.insertar(idCarrito, idProducto, cantidad, precioUnitario, fechaRegistro);
    }

    @WebMethod(operationName = "modificarCarritoItem")
    public Integer modificarCarritoItem(
        @WebParam(name = "idCarritoItem") Integer idCarritoItem,
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "cantidad") Integer cantidad,
        @WebParam(name = "precioUnitario") Double precioUnitario,
        @WebParam(name = "fechaRegistro") LocalDateTime fechaRegistro
    ) {
        return carritoItemsBO.modificar(idCarritoItem, idCarrito, idProducto, cantidad, precioUnitario, fechaRegistro);
    }

    @WebMethod(operationName = "eliminarCarritoItem")
    public Integer eliminarCarritoItem(
        @WebParam(name = "idCarritoItem") Integer idCarritoItem
    ) {
        return carritoItemsBO.eliminar(idCarritoItem);
    }

    @WebMethod(operationName = "obtenerCarritoItemPorId")
    public CarritoItemsDTO obtenerCarritoItemPorId(
        @WebParam(name = "idCarritoItem") Integer idCarritoItem
    ) {
        return carritoItemsBO.obtenerPorId(idCarritoItem);
    }

    @WebMethod(operationName = "listarTodosLosCarritoItems")
    public ArrayList<CarritoItemsDTO> listarTodosLosCarritoItems() {
        return carritoItemsBO.listarTodos();
    }

    @WebMethod(operationName = "listarItemsPorCarrito")
    public ArrayList<CarritoItemsDTO> listarItemsPorCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito
    ) {
        return carritoItemsBO.listarPorCarrito(idCarrito);
    }

    @WebMethod(operationName = "calcularTotalCarrito")
    public Double calcularTotalCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito
    ) {
        return carritoItemsBO.calcularTotalCarrito(idCarrito);
    }

    @WebMethod(operationName = "agregarProductoAlCarrito")
    public Integer agregarProductoAlCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "cantidad") Integer cantidad,
        @WebParam(name = "precioUnitario") Double precioUnitario
    ) {
        return carritoItemsBO.agregarProductoAlCarrito(idCarrito, idProducto, cantidad, precioUnitario);
    }

    @WebMethod(operationName = "eliminarProductoDelCarrito")
    public Integer eliminarProductoDelCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto
    ) {
        return carritoItemsBO.eliminarProductoDelCarrito(idCarrito, idProducto);
    }

    @WebMethod(operationName = "actualizarCantidadProducto")
    public Integer actualizarCantidadProducto(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "nuevaCantidad") Integer nuevaCantidad
    ) {
        return carritoItemsBO.actualizarCantidadProducto(idCarrito, idProducto, nuevaCantidad);
    }

    @WebMethod(operationName = "vaciarCarrito")
    public Integer vaciarCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito
    ) {
        return carritoItemsBO.vaciarCarrito(idCarrito);
    }
}