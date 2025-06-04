package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.CompraBO;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.EstadoCompraDTO;

@WebService(serviceName = "ComprasWS")
public class Compras {

//    private CompraBO compraBO;
//
//    public Compras() {
//        this.compraBO = new CompraBO();
//    }
//
//    @WebMethod(operationName = "registrarCompra")
//    public Integer registrarCompra(
//            @WebParam(name = "precioTotal") Double precioTotal,
//            @WebParam(name = "fechaCompra") LocalDateTime fechaCompra,
//            @WebParam(name = "estadoCompra") String estadoCompraStr,
//            @WebParam(name = "idEnvio") Integer idEnvio) {
//        
//        try {
//            EstadoCompraDTO estadoCompra = EstadoCompraDTO.valueOf(estadoCompraStr);
//            return compraBO.insertar(precioTotal, fechaCompra, estadoCompra, idEnvio);
//        } catch (IllegalArgumentException e) {
//            return -2; // Estado de compra no válido
//        }
//    }

//    @WebMethod(operationName = "actualizarCompra")
//    public Integer actualizarCompra(
//            @WebParam(name = "idCompra") Integer idCompra,
//            @WebParam(name = "precioTotal") Double precioTotal,
//            @WebParam(name = "fechaCompra") LocalDateTime fechaCompra,
//            @WebParam(name = "estadoCompra") String estadoCompraStr,
//            @WebParam(name = "idEnvio") Integer idEnvio) {
//        
//        try {
//            EstadoCompraDTO estadoCompra = EstadoCompraDTO.valueOf(estadoCompraStr);
//            return compraBO.modificar(idCompra, precioTotal, fechaCompra, estadoCompra, idEnvio);
//        } catch (IllegalArgumentException e) {
//            return -2; // Estado de compra no válido
//        }
//    }
//
//    @WebMethod(operationName = "eliminarCompra")
//    public Integer eliminarCompra(
//            @WebParam(name = "idCompra") Integer idCompra) {
//        return compraBO.eliminar(idCompra);
//    }
//
//    @WebMethod(operationName = "obtenerCompraPorId")
//    public CompraDTO obtenerCompraPorId(
//            @WebParam(name = "idCompra") Integer idCompra) {
//        return compraBO.obtenerPorId(idCompra);
//    }
//
//    @WebMethod(operationName = "listarTodasCompras")
//    public ArrayList<CompraDTO> listarTodasCompras() {
//        return compraBO.listarTodos();
//    }
//
//    @WebMethod(operationName = "actualizarEstadoCompra")
//    public Integer actualizarEstadoCompra(
//            @WebParam(name = "idCompra") Integer idCompra,
//            @WebParam(name = "nuevoEstado") String nuevoEstadoStr) {
//        
//        try {
//            EstadoCompraDTO nuevoEstado = EstadoCompraDTO.valueOf(nuevoEstadoStr);
//            CompraDTO compra = compraBO.obtenerPorId(idCompra);
//            if (compra != null) {
//                return compraBO.modificar(
//                    compra.getIdCompra(),
//                    compra.getPrecioTotal(),
//                    compra.getFechaCompra(),
//                    nuevoEstado,
//                    compra.getEntrega().getIdEnvio()
//                );
//            }
//            return -1;
//        } catch (IllegalArgumentException e) {
//            return -2; // Estado de compra no válido
//        }
//    }
//
//    @WebMethod(operationName = "listarComprasPorEstado")
//    public ArrayList<CompraDTO> listarComprasPorEstado(
//            @WebParam(name = "estadoCompra") String estadoCompraStr) {
//        
//        try {
//            EstadoCompraDTO estadoCompra = EstadoCompraDTO.valueOf(estadoCompraStr);
//            ArrayList<CompraDTO> todasCompras = compraBO.listarTodos();
//            ArrayList<CompraDTO> comprasFiltradas = new ArrayList<>();
//            
//            for (CompraDTO compra : todasCompras) {
//                if (compra.getEstadoCompra() == estadoCompra) {
//                    comprasFiltradas.add(compra);
//                }
//            }
//            return comprasFiltradas;
//        } catch (IllegalArgumentException e) {
//            return new ArrayList<>(); // Retorna lista vacía si estado no válido
//        }
//    }
}