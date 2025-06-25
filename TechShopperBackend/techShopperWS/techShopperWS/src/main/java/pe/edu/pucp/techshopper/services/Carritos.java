package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.CarritoBO;
import pe.edu.pucp.techshopper.model.CarritoDTO;

@WebService(serviceName = "Carritos")
public class Carritos {

    private CarritoBO carritoBO;

    public Carritos() {
        carritoBO = new CarritoBO();
    }

    @WebMethod(operationName = "crearCarrito")
    public Integer crearCarrito(
        @WebParam(name = "idCliente") Integer idCliente) {

        return carritoBO.crearCarrito(idCliente);
    }

    @WebMethod(operationName = "actualizarPrecioCarrito")
    public Integer actualizarPrecioCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito,
        @WebParam(name = "nuevoPrecio") Double nuevoPrecio) {

        return carritoBO.actualizarPrecioCarrito(idCarrito, nuevoPrecio);
    }

    @WebMethod(operationName = "eliminarCarrito")
    public Integer eliminarCarrito(
        @WebParam(name = "idCarrito") Integer idCarrito) {

        return carritoBO.eliminarCarrito(idCarrito);
    }

    @WebMethod(operationName = "obtenerCarritoPorId")
    public CarritoDTO obtenerCarritoPorId(
        @WebParam(name = "idCarrito") Integer idCarrito) {

        return carritoBO.obtenerCarritoPorId(idCarrito);
    }

    @WebMethod(operationName = "listarTodosLosCarritos")
    public ArrayList<CarritoDTO> listarTodosLosCarritos() {

        return carritoBO.listarTodosLosCarritos();
    }

    @WebMethod(operationName = "montoAPagar")
    public Double montoAPagar(
        @WebParam(name = "idCarrito") Integer idCarrito) {

        return carritoBO.montoAPagar(idCarrito);
    }
}