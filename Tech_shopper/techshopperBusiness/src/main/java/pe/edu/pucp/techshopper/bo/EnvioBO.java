/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.techshopper.dao.EnvioDAO;
import pe.edu.pucp.techshopper.daoImp.EnvioDAOImp;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;

/**
 *
 * @author CRISTHIAN
 */
public class EnvioBO {
    private EnvioDAO envioDAO;
    
    public EnvioBO(){
        envioDAO = new EnvioDAOImp();
    }
    
    public Integer insertar (LocalizacionDTO destino,LocalDateTime fechaEntrega,String empresaCourier,
            Double precio){
        EnvioDTO envioDTO = new EnvioDTO();
        
        envioDTO.setDestino(destino);
        envioDTO.setFechaEntrega(fechaEntrega);
        envioDTO.setEmpresaCourier(empresaCourier);
        envioDTO.setPrecio(precio);
        
        return this.envioDAO.insertar(envioDTO);
    }
    
    public boolean modificar (Integer idEnvio, LocalizacionDTO destino,LocalDateTime fechaEntrega,String empresaCourier,
            Double precio){
        EnvioDTO envioDTO = new EnvioDTO();
        
        envioDTO.setIdEnvio(idEnvio);
        envioDTO.setDestino(destino);
        envioDTO.setFechaEntrega(fechaEntrega);
        envioDTO.setEmpresaCourier(empresaCourier);
        envioDTO.setPrecio(precio);
        
        return envioDAO.modificar(envioDTO);
    }
    
    public boolean eliminar(Integer idEnvio){
        return this.envioDAO.eliminar(idEnvio);
    }
    
    public EnvioDTO buscar (Integer idEnvio){
        return this.envioDAO.buscar(idEnvio);
    }
    
    public List<EnvioDTO> listar(){
        return this.envioDAO.listar();
    }
}
