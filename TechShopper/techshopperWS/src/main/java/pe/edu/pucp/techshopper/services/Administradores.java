package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.AdministradorBO;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

@WebService(serviceName = "Administradores")
public class Administradores {

    private AdministradorBO administradorBO;

    public Administradores() {
        this.administradorBO = new AdministradorBO();
    }

    @WebMethod(operationName = "registrarAdministrador")
    public Integer registrarAdministrador(
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "estadoConexion") String estadoConexionStr,
            @WebParam(name = "fechaRegistro") LocalDateTime fechaRegistro,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email) {
        
        try {
            EstadoConexionDTO estadoConexion = EstadoConexionDTO.valueOf(estadoConexionStr);
            return administradorBO.insertar(contraseña, estadoConexion, fechaRegistro, nombre, email);
        } catch (IllegalArgumentException e) {
            return -1; // Estado de conexión no válido
        }
    }

    @WebMethod(operationName = "actualizarAdministrador")
    public Integer actualizarAdministrador(
            @WebParam(name = "idAdministrador") Integer idAdministrador,
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "estadoConexion") String estadoConexionStr,
            @WebParam(name = "fechaRegistro") LocalDateTime fechaRegistro,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email) {
        
        try {
            EstadoConexionDTO estadoConexion = EstadoConexionDTO.valueOf(estadoConexionStr);
            return administradorBO.modificar(idAdministrador, contraseña, estadoConexion, 
                                          fechaRegistro, nombre, email);
        } catch (IllegalArgumentException e) {
            return -1; // Estado de conexión no válido
        }
    }

    @WebMethod(operationName = "eliminarAdministrador")
    public Integer eliminarAdministrador(
            @WebParam(name = "idAdministrador") Integer idAdministrador) {
        return administradorBO.eliminar(idAdministrador);
    }

    @WebMethod(operationName = "obtenerAdministradorPorId")
    public AdministradorDTO obtenerAdministradorPorId(
            @WebParam(name = "idAdministrador") Integer idAdministrador) {
        return administradorBO.obtenerPorId(idAdministrador);
    }

    @WebMethod(operationName = "listarTodosAdministradores")
    public ArrayList<AdministradorDTO> listarTodosAdministradores() {
        return administradorBO.listarTodos();
    }

    @WebMethod(operationName = "autenticarAdministrador")
    public AdministradorDTO autenticarAdministrador(
            @WebParam(name = "email") String email,
            @WebParam(name = "contraseña") String contraseña) {
        
        ArrayList<AdministradorDTO> administradores = administradorBO.listarTodos();
        for (AdministradorDTO admin : administradores) {
            if (admin.getEmail().equals(email) && admin.getContraseña().equals(contraseña)) {
                return admin;
            }
        }
        return null;
    }

    @WebMethod(operationName = "actualizarEstadoConexion")
    public Integer actualizarEstadoConexion(
            @WebParam(name = "idAdministrador") Integer idAdministrador,
            @WebParam(name = "nuevoEstado") String nuevoEstadoStr) {
        
        try {
            EstadoConexionDTO nuevoEstado = EstadoConexionDTO.valueOf(nuevoEstadoStr);
            AdministradorDTO admin = administradorBO.obtenerPorId(idAdministrador);
            if (admin != null) {
                admin.setEstadoConexion(nuevoEstado);
                return administradorBO.modificar(
                    admin.getIdPersona(), 
                    admin.getContraseña(), 
                    admin.getEstadoConexion(), 
                    admin.getFechaRegistro(), 
                    admin.getNombre(), 
                    admin.getEmail()
                );
            }
            return -1;
        } catch (IllegalArgumentException e) {
            return -1; // Estado de conexión no válido
        }
    }
}