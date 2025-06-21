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

    private final CarritoItemsBO carritoItemsBO = new CarritoItemsBO();

    @WebMethod(operationName = "insertar")
    public Integer insertar(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "cantidad") Integer cantidad,
        @WebParam(name = "precioUnitario") Double precioUnitario,
        @WebParam(name = "fechaRegistro") String fechaRegistroStr) {

        try {
            LocalDateTime fechaRegistro = LocalDateTime.parse(fechaRegistroStr);
            return carritoItemsBO.insertar(idCarrito, idProducto, cantidad, precioUnitario, fechaRegistro);
        } catch (Exception e) {
            return -2;
        }
    }

    @WebMethod(operationName = "modificar")
    public Integer modificar(
        @WebParam(name = "idItemCarrito") Integer idItemCarrito,
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "cantidad") Integer cantidad,
        @WebParam(name = "precioUnitario") Double precioUnitario,
        @WebParam(name = "fechaRegistro") String fechaRegistroStr) {

        try {
            LocalDateTime fechaRegistro = LocalDateTime.parse(fechaRegistroStr);
            return carritoItemsBO.modificar(idItemCarrito, idCarrito, idProducto, cantidad, precioUnitario, fechaRegistro);
        } catch (Exception e) {
            return -2;
        }
    }

    @WebMethod(operationName = "eliminar")
    public Integer eliminar(@WebParam(name = "idItemCarrito") Integer idItemCarrito) {
        return carritoItemsBO.eliminar(idItemCarrito);
    }

    @WebMethod(operationName = "obtenerPorId")
    public CarritoItemsDTO obtenerPorId(@WebParam(name = "idItemCarrito") Integer idItemCarrito) {
        return carritoItemsBO.obtenerPorId(idItemCarrito);
    }

    @WebMethod(operationName = "listarTodos")
    public ArrayList<CarritoItemsDTO> listarTodos() {
        return carritoItemsBO.listarTodos();
    }

    @WebMethod(operationName = "listarPorCarrito")
    public ArrayList<CarritoItemsDTO> listarPorCarrito(@WebParam(name = "idCarrito") Integer idCarrito) {
        return carritoItemsBO.listarPorCarrito(idCarrito);
    }

    @WebMethod(operationName = "calcularTotalCarrito")
    public Double calcularTotalCarrito(@WebParam(name = "idCarrito") Integer idCarrito) {
        return carritoItemsBO.calcularTotalCarrito(idCarrito);
    }

    @WebMethod(operationName = "agregarProductoAlCarrito")
    public Integer agregarProductoAlCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "cantidad") Integer cantidad,
        @WebParam(name = "precioUnitario") Double precioUnitario) {

        return carritoItemsBO.agregarProductoAlCarrito(idCarrito, idProducto, cantidad, precioUnitario);
    }

    @WebMethod(operationName = "eliminarProductoDelCarrito")
    public Integer eliminarProductoDelCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto) {

        return carritoItemsBO.eliminarProductoDelCarrito(idCarrito, idProducto);
    }

    @WebMethod(operationName = "actualizarCantidadProducto")
    public Integer actualizarCantidadProducto(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "idProducto") Integer idProducto,
        @WebParam(name = "nuevaCantidad") Integer nuevaCantidad) {

        return carritoItemsBO.actualizarCantidadProducto(idCarrito, idProducto, nuevaCantidad);
    }

    @WebMethod(operationName = "vaciarCarrito")
    public Integer vaciarCarrito(@WebParam(name = "idCarrito") Integer idCarrito) {
        return carritoItemsBO.vaciarCarrito(idCarrito);
    }
}
