package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.daoImp.AdministradorDAOImp;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

public class AdministradorBO {
    
    private final AdministradorDAO administradorDAO;
    
    public AdministradorBO() {
        this.administradorDAO = new AdministradorDAOImp();
    }
    
    public Integer insertar(String contraseña, EstadoConexionDTO estadoConexion, 
                          LocalDateTime fechaRegistro, String nombre, String email) {
        if (contraseña == null || contraseña.isEmpty() || estadoConexion == null || 
            fechaRegistro == null || nombre == null || nombre.isEmpty() || 
            email == null || email.isEmpty()) {
            return -1;
        }
        
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setContraseña(contraseña);
        administradorDTO.setEstadoConexion(estadoConexion);
        administradorDTO.setFechaRegistro(fechaRegistro);
        administradorDTO.setNombre(nombre);
        administradorDTO.setEmail(email);
        
        return administradorDAO.insertar(administradorDTO);
    }
    
    public Integer modificar(Integer idAdministrador, String contraseña, 
                           EstadoConexionDTO estadoConexion, LocalDateTime fechaRegistro, 
                           String nombre, String email) {
        if (idAdministrador == null || idAdministrador <= 0 || contraseña == null || 
            contraseña.isEmpty() || estadoConexion == null || fechaRegistro == null || 
            nombre == null || nombre.isEmpty() || email == null || email.isEmpty()) {
            return -1;
        }
        
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setIdPersona(idAdministrador);
        administradorDTO.setContraseña(contraseña);
        administradorDTO.setEstadoConexion(estadoConexion);
        administradorDTO.setFechaRegistro(fechaRegistro);
        administradorDTO.setNombre(nombre);
        administradorDTO.setEmail(email);
        
        return administradorDAO.modificar(administradorDTO);
    }
    
    public Integer eliminar(Integer idAdministrador) {
        if (idAdministrador == null || idAdministrador <= 0) {
            return -1;
        }
        
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setIdPersona(idAdministrador);
        
        return administradorDAO.eliminar(administradorDTO);
    }
    
    public AdministradorDTO obtenerPorId(Integer idAdministrador) {
        if (idAdministrador == null || idAdministrador <= 0) {
            return null;
        }
        return administradorDAO.obtenerPorId(idAdministrador);
    }
    
    public ArrayList<AdministradorDTO> listarTodos() {
        return administradorDAO.listarTodos();
    }
    
}