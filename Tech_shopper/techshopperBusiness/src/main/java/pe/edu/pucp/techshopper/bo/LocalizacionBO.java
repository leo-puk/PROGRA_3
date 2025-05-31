/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.util.List;
import pe.edu.pucp.techshopper.dao.LocalizacionDAO;
import pe.edu.pucp.techshopper.daoImp.LocalizacionDAOImp;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;

/**
 *
 * @author CRISTHIAN
 */
public class LocalizacionBO {
    private LocalizacionDAO localizacionDAO;
    
    public LocalizacionBO(){
        localizacionDAO = new LocalizacionDAOImp();
    }
    
    public Integer insertar (Double latitud, Double longitud, String direccion, ClienteDTO cliente){
        LocalizacionDTO localizacionDTO = new LocalizacionDTO();
        
        localizacionDTO.setLatitud(latitud);
        localizacionDTO.setLongitud(longitud);
        localizacionDTO.setDireccion(direccion);
        localizacionDTO.setCliente(cliente);
        
        return this.localizacionDAO.insertar(localizacionDTO);
    }
    
    public boolean modificar (Integer idLocalizacion, Double latitud, Double longitud, String direccion, ClienteDTO cliente){
        LocalizacionDTO localizacionDTO = new LocalizacionDTO();
        
        localizacionDTO.setIdLocalizacion(idLocalizacion);
        localizacionDTO.setLatitud(latitud);
        localizacionDTO.setLongitud(longitud);
        localizacionDTO.setDireccion(direccion);
        localizacionDTO.setCliente(cliente);
        
        return this.localizacionDAO.modificar(localizacionDTO);
    }
    
    public boolean eliminar(Integer idLocalizacion){
        return this.localizacionDAO.eliminar(idLocalizacion);
    }
    
    public LocalizacionDTO buscar (Integer idLocalizacion){
        return this.localizacionDAO.buscar(idLocalizacion);
    }
    
    public List<LocalizacionDTO> listar(){
        return this.localizacionDAO.listar();
    }
}
