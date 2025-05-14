/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.daoImp.CarritoDAOImp;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;

/**
 *
 * @author CRISTHIAN
 */
public class CarritoBO {
    private CarritoDAO carritoDAO;
    
    public CarritoBO(){
        this.carritoDAO = new CarritoDAOImp();
    }
    
    Integer insertar(Double precio,ClienteDTO cliente){
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setCliente(cliente);
//        carritoDTO.setIdCarrito(cliente);
        carritoDTO.setPrecio(precio);
        return this.carritoDAO.insertar(carritoDTO);
    }
    
    Boolean modificar(Integer idCarrito, Double precio,ClienteDTO cliente){
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setCliente(cliente);
        carritoDTO.setIdCarrito(idCarrito);
        carritoDTO.setPrecio(precio);
        return this.carritoDAO.modificar(carritoDTO);
    }
    
    Boolean  eliminar(int id){
        return this.carritoDAO.eliminar(id);
    }
    
    CarritoDTO buscar(int id){
        return this.carritoDAO.buscar(id);
    }
    
    
    List<CarritoDTO> listar(){
        return this.carritoDAO.listar();
    }
}
