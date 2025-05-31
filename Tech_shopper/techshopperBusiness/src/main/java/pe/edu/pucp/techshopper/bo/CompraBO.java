/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CompraDAO;
import pe.edu.pucp.techshopper.daoImp.CompraDAOImp;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.EstadoCompraDTO;

/**
 *
 * @author CRISTHIAN
 */
public class CompraBO {
    private CompraDAO compraDAO;
    
    public CompraBO(){
        compraDAO = new CompraDAOImp();
    }
    
    Integer insertar( CarritoDTO carrito,Double precioTotal,LocalDateTime  fechaCompra,
            EstadoCompraDTO estadoCompra,EnvioDTO entrega){
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setCarrito(carrito);
        compraDTO.setEntrega(entrega);
        compraDTO.setEstadoCompra(estadoCompra);
        compraDTO.setFechaCompra(fechaCompra);
        compraDTO.setPrecioTotal(precioTotal);
        return this.compraDAO.insertar(compraDTO);
    }
    
    Boolean modificar(Integer idCompra,CarritoDTO carrito,Double precioTotal,LocalDateTime  fechaCompra,
            EstadoCompraDTO estadoCompra,EnvioDTO entrega){
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setIdCompra(idCompra);
        compraDTO.setCarrito(carrito);
        compraDTO.setEntrega(entrega);
        compraDTO.setEstadoCompra(estadoCompra);
        compraDTO.setFechaCompra(fechaCompra);
        compraDTO.setPrecioTotal(precioTotal);
        return this.compraDAO.modificar(compraDTO);
    }
    
    Boolean  eliminar(int id){
        return this.compraDAO.eliminar(id);
    }
    
    CompraDTO buscar(int id){
        return this.compraDAO.buscar(id);
    }
    
    
    List<CompraDTO> listar(){
        return this.compraDAO.listar();
    }
    
}
