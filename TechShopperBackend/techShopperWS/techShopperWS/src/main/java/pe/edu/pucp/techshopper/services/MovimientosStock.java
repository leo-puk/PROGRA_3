package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.MovimientoStockBO;
import pe.edu.pucp.techshopper.model.MovimientoStockDTO;
import pe.edu.pucp.techshopper.model.TipoMovimientoDTO;

@WebService(serviceName = "MovimientosStock")
public class MovimientosStock{

    private MovimientoStockBO movimientoBO;

    public MovimientosStock() {
        this.movimientoBO = new MovimientoStockBO();
    }

    @WebMethod(operationName = "registrarMovimiento")
    public Integer registrarMovimiento(
            @WebParam(name = "idProducto") Integer idProducto,
            @WebParam(name = "tipo") String tipoStr,
            @WebParam(name = "cantidad") Integer cantidad,
            @WebParam(name = "fecha") LocalDateTime fecha,
            @WebParam(name = "idUsuario") Integer idUsuario) {
        try {
            TipoMovimientoDTO tipo = TipoMovimientoDTO.valueOf(tipoStr.toUpperCase());
            return movimientoBO.registrarMovimiento(idProducto, tipo, cantidad, fecha, idUsuario);
        } catch (Exception e) {
            return -1;
        }
    }

    @WebMethod(operationName = "registrarEntradaStock")
    public Integer registrarEntradaStock(
            @WebParam(name = "idProducto") Integer idProducto,
            @WebParam(name = "cantidad") Integer cantidad,
            @WebParam(name = "idUsuario") Integer idUsuario) {
        return movimientoBO.registrarEntradaStock(idProducto, cantidad, idUsuario);
    }

    @WebMethod(operationName = "registrarSalidaStock")
    public Integer registrarSalidaStock(
            @WebParam(name = "idProducto") Integer idProducto,
            @WebParam(name = "cantidad") Integer cantidad,
            @WebParam(name = "idUsuario") Integer idUsuario) {
        return movimientoBO.registrarSalidaStock(idProducto, cantidad, idUsuario);
    }

    @WebMethod(operationName = "modificarMovimiento")
    public Integer modificarMovimiento(
            @WebParam(name = "idMovimiento") Integer idMovimiento,
            @WebParam(name = "idProducto") Integer idProducto,
            @WebParam(name = "tipo") String tipoStr,
            @WebParam(name = "cantidad") Integer cantidad,
            @WebParam(name = "fecha") LocalDateTime fecha,
            @WebParam(name = "idUsuario") Integer idUsuario) {
        try {
            TipoMovimientoDTO tipo = TipoMovimientoDTO.valueOf(tipoStr.toUpperCase());
            return movimientoBO.modificarMovimiento(idMovimiento, idProducto, tipo, cantidad, fecha, idUsuario);
        } catch (Exception e) {
            return -1;
        }
    }

    @WebMethod(operationName = "eliminarMovimiento")
    public Integer eliminarMovimiento(@WebParam(name = "idMovimiento") Integer idMovimiento) {
        return movimientoBO.eliminarMovimiento(idMovimiento);
    }

    @WebMethod(operationName = "obtenerMovimientoPorId")
    public MovimientoStockDTO obtenerMovimientoPorId(@WebParam(name = "idMovimiento") Integer idMovimiento) {
        return movimientoBO.obtenerMovimientoPorId(idMovimiento);
    }

    @WebMethod(operationName = "listarTodosLosMovimientos")
    public ArrayList<MovimientoStockDTO> listarTodosLosMovimientos() {
        return movimientoBO.listarTodosLosMovimientos();
    }

    @WebMethod(operationName = "listarMovimientosPorProducto")
    public ArrayList<MovimientoStockDTO> listarMovimientosPorProducto(
            @WebParam(name = "idProducto") Integer idProducto) {
        return movimientoBO.listarMovimientosPorProducto(idProducto);
    }
}
