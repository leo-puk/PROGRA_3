/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.daoImp.AdministradorDAOImp;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

public class AdministradorBO {
    
    private AdministradorDAO administradorDAO;
    
    public AdministradorBO(){
        this.administradorDAO = new AdministradorDAOImp();
    }
    
    public Integer insertar(String contraseña, EstadoConexionDTO estadoConexion, 
                        LocalDateTime fechaRegistro, String nombre, String email){
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setNombre(nombre);
        administradorDTO.setEmail(email);
        administradorDTO.setContraseña(contraseña);
        administradorDTO.setEstadoConexion(estadoConexion);
        administradorDTO.setFechaRegistro(fechaRegistro);
        
        return this.administradorDAO.insertar(administradorDTO);
    }
    
    public Boolean modificar(Integer idPersona, String contraseña, EstadoConexionDTO estadoConexion, 
                        LocalDateTime fechaRegistro, String nombre, String email){
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setIdPersona(idPersona);
        administradorDTO.setNombre(nombre);
        administradorDTO.setEmail(email);
        administradorDTO.setContraseña(contraseña);
        administradorDTO.setEstadoConexion(estadoConexion);
        administradorDTO.setFechaRegistro(fechaRegistro);
        
        return this.administradorDAO.modificar(administradorDTO);
    }
    
    public Boolean eliminar(int id){
//        AdministradorDTO administradorDTO = new AdministradorDTO();
        return this.administradorDAO.eliminar(id);
    }
    
    public AdministradorDTO buscar(int id){
        return this.administradorDAO.buscar(id);
    }
    
    public List<AdministradorDTO> listar(){
        return this.administradorDAO.listar();
    }
    
}
