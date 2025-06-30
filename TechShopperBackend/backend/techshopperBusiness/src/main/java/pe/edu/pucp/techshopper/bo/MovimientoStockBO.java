package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.MovimientoStockDAO;
import pe.edu.pucp.techshopper.daoImp.MovimientoStockDAOImp;
import pe.edu.pucp.techshopper.model.MovimientoStockDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.TipoMovimientoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class MovimientoStockBO {
    
    private final MovimientoStockDAO movimientoStockDAO;
    
    public MovimientoStockBO() {
        this.movimientoStockDAO = new MovimientoStockDAOImp();
    }
    
    public Integer registrarMovimiento(Integer idProducto, TipoMovimientoDTO tipo, 
                                     Integer cantidad, LocalDateTime fecha, 
                                     Integer idUsuario) {
        // Validaciones básicas
        if (idProducto == null || idProducto <= 0 || tipo == null || 
            cantidad == null || cantidad <= 0 || fecha == null) {
            return -1;
        }
        
        MovimientoStockDTO movimiento = new MovimientoStockDTO();
        
        // Configurar producto
        ProductoDTO producto = new ProductoDTO();
        ProductoBO prodBO = new ProductoBO();
        producto=prodBO.obtenerProductoPorId(idProducto);
        
        movimiento.setProducto(producto);
        
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setFechaMovimiento(fecha);
        
        // Configurar usuario si existe
        if (idUsuario != null && idUsuario > 0) {
            UsuarioDTO usuario = new UsuarioDTO();
            UsuarioBO usuBO = new UsuarioBO();
            usuario=usuBO.obtenerUsuarioPorId(idUsuario);
            movimiento.setUsuario(usuario);
        }
        
        return movimientoStockDAO.insertar(movimiento);
    }
    
    public Integer registrarEntradaStock(Integer idProducto, Integer cantidad, 
                                       Integer idUsuario) {
        return registrarMovimiento(
            idProducto, 
            TipoMovimientoDTO.ENTRADA, 
            cantidad, 
            LocalDateTime.now(), 
            idUsuario
        );
    }
    
    public Integer registrarSalidaStock(Integer idProducto, Integer cantidad, 
                                      Integer idUsuario) {
        return registrarMovimiento(
            idProducto, 
            TipoMovimientoDTO.SALIDA, 
            cantidad, 
            LocalDateTime.now(), 
            idUsuario
        );
    }
    
    public Integer modificarMovimiento(Integer idMovimiento, Integer idProducto, 
                                     TipoMovimientoDTO tipo, Integer cantidad, 
                                     LocalDateTime fecha, Integer idUsuario) {
        // Validaciones básicas
        if (idMovimiento == null || idMovimiento <= 0 || idProducto == null || 
            idProducto <= 0 || tipo == null || cantidad == null || 
            cantidad <= 0 || fecha == null) {
            return -1;
        }
        
        MovimientoStockDTO movimiento = new MovimientoStockDTO();
        movimiento.setIdMovimiento(idMovimiento);
        
        // Configurar producto
        ProductoDTO producto = new ProductoDTO();
        ProductoBO prodBO = new ProductoBO();
        producto=prodBO.obtenerProductoPorId(idProducto);
        
        movimiento.setProducto(producto);
        
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setFechaMovimiento(fecha);
        
        // Configurar usuario si existe
        if (idUsuario != null && idUsuario > 0) {
            UsuarioDTO usuario = new UsuarioDTO();
            UsuarioBO usuBO = new UsuarioBO();
            usuario=usuBO.obtenerUsuarioPorId(idUsuario);
            movimiento.setUsuario(usuario);
        }
        
        return movimientoStockDAO.modificar(movimiento);
    }
    
    public Integer eliminarMovimiento(Integer idMovimiento) {
        if (idMovimiento == null || idMovimiento <= 0) {
            return -1;
        }
        
        MovimientoStockDTO movimiento = new MovimientoStockDTO();
        movimiento.setIdMovimiento(idMovimiento);
        
        return movimientoStockDAO.eliminar(movimiento);
    }
    
    public MovimientoStockDTO obtenerMovimientoPorId(Integer idMovimiento) {
        if (idMovimiento == null || idMovimiento <= 0) {
            return null;
        }
        return movimientoStockDAO.obtenerPorId(idMovimiento);
    }
    
    public ArrayList<MovimientoStockDTO> listarTodosLosMovimientos() {
        ArrayList<MovimientoStockDTO> lista = movimientoStockDAO.listarTodos();
        ProductoBO prodBO = new ProductoBO();
        UsuarioBO usuBO = new UsuarioBO();
        for (MovimientoStockDTO mov : lista) {
            int prodId=mov.getProducto().getIdProducto();
            int UsuId=mov.getUsuario().getIdUsuario();
            ProductoDTO prod = prodBO.obtenerProductoPorId(prodId);
            UsuarioDTO usu = usuBO.obtenerUsuarioPorId(UsuId);
            mov.setProducto(prod);
            mov.setUsuario(usu);
            
        }
        return lista;
    }
    
    public ArrayList<MovimientoStockDTO> listarMovimientosPorProducto(Integer idProducto) {
        if (idProducto == null || idProducto <= 0) {
            return new ArrayList<>();
        }
        
        ArrayList<MovimientoStockDTO> todos = movimientoStockDAO.listarTodos();
        ArrayList<MovimientoStockDTO> filtrados = new ArrayList<>();
        
        ProductoBO prodBO = new ProductoBO();
        UsuarioBO usuBO = new UsuarioBO();
        
        for (MovimientoStockDTO movimiento : todos) {
            
            if (movimiento.getProducto() != null && 
                movimiento.getProducto().getIdProducto().equals(idProducto)) {
                
                int prodId=movimiento.getProducto().getIdProducto();
                int UsuId=movimiento.getUsuario().getIdUsuario();
                ProductoDTO prod = prodBO.obtenerProductoPorId(prodId);
                UsuarioDTO usu = usuBO.obtenerUsuarioPorId(UsuId);
                movimiento.setProducto(prod);
                movimiento.setUsuario(usu);
                filtrados.add(movimiento);
            }
        }
        
        return filtrados;
    }
}