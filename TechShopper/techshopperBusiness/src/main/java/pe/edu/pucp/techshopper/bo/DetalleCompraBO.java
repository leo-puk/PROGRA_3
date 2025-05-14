/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.util.List;
import pe.edu.pucp.techshopper.dao.DetalleCompraDAO;
import pe.edu.pucp.techshopper.daoImp.DetalleCompraDAOImp;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.DetalleCompraDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

/**
 *
 * @author CRISTHIAN
 */
public class DetalleCompraBO {
    private DetalleCompraDAO detalleCompraDAO;
    
    public DetalleCompraBO(){
        detalleCompraDAO = new DetalleCompraDAOImp();
    }
    
    public Integer insertar (CompraDTO compra,ProductoDTO producto,Integer cantidad,
            Double precioUnitario){
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();
        
        detalleCompraDTO.setCompra(compra);
        detalleCompraDTO.setProducto(producto);
        detalleCompraDTO.setCantidad(cantidad);
        detalleCompraDTO.setPrecioUnitario(precioUnitario);
        
        return this.detalleCompraDAO.insertar(detalleCompraDTO);
    }
    
    public boolean modificar (Integer idDetalleCompra, CompraDTO compra,ProductoDTO producto,Integer cantidad,
            Double precioUnitario){
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();
        
        detalleCompraDTO.setIdDetalleCompra(idDetalleCompra);
        detalleCompraDTO.setCompra(compra);
        detalleCompraDTO.setProducto(producto);
        detalleCompraDTO.setCantidad(cantidad);
        detalleCompraDTO.setPrecioUnitario(precioUnitario);
        
        return this.detalleCompraDAO.modificar(detalleCompraDTO);
    }
    
    public boolean eliminar(Integer idDetalleCompra){
        return this.detalleCompraDAO.eliminar(idDetalleCompra);
    }
    
    public DetalleCompraDTO buscar (Integer idDetalleCompra){
        return this.detalleCompraDAO.buscar(idDetalleCompra);
    }
    
    public List<DetalleCompraDTO> listar(){
        return this.detalleCompraDAO.listar();
    }
}
