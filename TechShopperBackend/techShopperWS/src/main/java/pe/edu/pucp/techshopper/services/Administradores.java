/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import pe.edu.pucp.techshopper.bo.AdministradorBO;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.NivelAccesoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

/**
 *
 * @author melow
 */
@WebService(serviceName = "Administradores")
public class Administradores {
    
    private AdministradorBO administradorBO;
    
    public Administradores() {
        this.administradorBO = new AdministradorBO();
    }
    
    @WebMethod(operationName = "registrarAdministrador")
    public Integer registrarAdministrador(
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email) {
        
        try {
            
            return administradorBO.registrarAdministradorCompleto(contraseña, nombre, email);
        } catch (IllegalArgumentException e) {
            return -3;
        }
    }
    
    @WebMethod(operationName = "actualizarAdministrador")
    public Integer actualizarAdministrador(
            @WebParam(name = "idAdministrador") Integer idAdministrador,
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "estadoConexion") String estadoConexionStr,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email,
            @WebParam(name = "nivelAcceso") String nivelAccesoStr,
            @WebParam(name = "fechaRegistro") LocalDateTime fechaUltimoAcceso) {
        
        try {
            EstadoConexionDTO estadoConexion = EstadoConexionDTO.valueOf(estadoConexionStr);
            NivelAccesoDTO nivelAcceso = NivelAccesoDTO.valueOf(nivelAccesoStr);
            return administradorBO.actualizarDatosAdministradorCompleto(idAdministrador, contraseña, estadoConexion, 
                                          nombre, email,nivelAcceso,fechaUltimoAcceso);
        } catch (IllegalArgumentException e) {
            return -1; 
        }
    }
    
    @WebMethod(operationName = "eliminarAdministrador")
    public Integer eliminarAdministrador(
            @WebParam(name = "idAdministrador") Integer idAdministrador) {
        return administradorBO.eliminarAdministradorCompleto(idAdministrador);
    }

    @WebMethod(operationName = "obtenerAdministradorPorId")
    public AdministradorDTO obtenerAdministradorPorId(
            @WebParam(name = "idAdministrador") Integer idAdministrador) {
        return administradorBO.obtenerAdministradorCompleto(idAdministrador);
    }

    @WebMethod(operationName = "actualizarEstadoConexion")
    public Integer actualizarEstadoConexion(
            @WebParam(name = "idAdministrador") Integer idAdministrador,
            @WebParam(name = "nuevoEstado") String nuevoEstadoStr) {
        
        try {
            EstadoConexionDTO nuevoEstado = EstadoConexionDTO.valueOf(nuevoEstadoStr);
            AdministradorDTO admin = administradorBO.obtenerAdministradorCompleto(idAdministrador);
            if (admin != null) {
                admin.setEstadoConexion(nuevoEstado);
                return administradorBO.actualizarDatosAdministradorCompleto(
                    admin.getIdUsuario(), 
                    admin.getContraseña(), 
                    nuevoEstado, 
                    admin.getNombre(), 
                    admin.getEmail(),
                    admin.getNivelAcceso(),
                    admin.getFechaUltimoAcceso()
                );
            }
            return -1;
        } catch (IllegalArgumentException e) {
            return -1; 
        }
    }
    
    @WebMethod(operationName = "iniciarSesion")
    public UsuarioDTO iniciarSesion(
                            @WebParam(name = "correoONombreUsuario") String correoONombreUsuario, 
                            @WebParam(name = "contrasenia") String contrasenia){
        
        return administradorBO.iniciarSesion(correoONombreUsuario,contrasenia);
    }
    
    
}
