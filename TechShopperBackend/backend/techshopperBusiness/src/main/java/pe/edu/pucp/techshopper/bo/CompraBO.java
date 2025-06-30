package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.CompraDAO;
import pe.edu.pucp.techshopper.daoImp.CompraDAOImp;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.EstadoCompraDTO;
import pe.edu.pucp.techshopper.model.EnvioDTO;

public class CompraBO {
    
    private final CompraDAO compraDAO;
    
    public CompraBO() {
        this.compraDAO = new CompraDAOImp();
    }
    
    public Integer insertar(Double precioTotal, LocalDateTime fechaCompra, 
                          EstadoCompraDTO estadoCompra, Integer idEnvio) {
        if (precioTotal == null || precioTotal <= 0 || fechaCompra == null || 
            estadoCompra == null || idEnvio == null || idEnvio <= 0) {
            return -1;
        }
        
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setPrecioTotal(precioTotal);
        compraDTO.setFechaCompra(fechaCompra);
        compraDTO.setEstadoCompra(estadoCompra);
        
        EnvioDTO envio = new EnvioDTO();
        envio.setIdEnvio(idEnvio);
        compraDTO.setEntrega(envio);
        
        return compraDAO.insertar(compraDTO);
    }
    
    public Integer modificar(Integer idCompra, Double precioTotal, LocalDateTime fechaCompra, 
                           EstadoCompraDTO estadoCompra, Integer idEnvio) {
        if (idCompra == null || idCompra <= 0 || precioTotal == null || precioTotal <= 0 || 
            fechaCompra == null || estadoCompra == null || idEnvio == null || idEnvio <= 0) {
            return -1;
        }
        
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setIdCompra(idCompra);
        compraDTO.setPrecioTotal(precioTotal);
        compraDTO.setFechaCompra(fechaCompra);
        compraDTO.setEstadoCompra(estadoCompra);
        
        EnvioDTO envio = new EnvioDTO();
        envio.setIdEnvio(idEnvio);
        compraDTO.setEntrega(envio);
        
        return compraDAO.modificar(compraDTO);
    }
    
    public Integer eliminar(Integer idCompra) {
        if (idCompra == null || idCompra <= 0) {
            return -1;
        }
        
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setIdCompra(idCompra);
        
        return compraDAO.eliminar(compraDTO);
    }
    
    public CompraDTO obtenerPorId(Integer idCompra) {
        if (idCompra == null || idCompra <= 0) {
            return null;
        }
        return compraDAO.obtenerPorId(idCompra);
    }
    
    public ArrayList<CompraDTO> listarTodos() {
        return compraDAO.listarTodos();
    }
    
    public boolean cancelarPedido(int idCompra) {
        // Validación básica del ID
        if (idCompra <= 0) {
            throw new IllegalArgumentException("ID de compra inválido");
        }
        
        // Delegar la operación al DAO
        return ((CompraDAOImp)compraDAO).cancelarPedido(idCompra);
    }
    
}