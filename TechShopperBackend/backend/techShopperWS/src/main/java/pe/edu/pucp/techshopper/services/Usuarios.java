/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.UsuarioBO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

@WebService(serviceName = "Usuarios")
public class Usuarios {

    private UsuarioBO usuarioBO;

    public Usuarios() {
        this.usuarioBO = new UsuarioBO();
    }


    @WebMethod(operationName = "obtenerUsuarioPorCorreo")
    public UsuarioDTO obtenerUsuarioPorCorreo(@WebParam(name = "email") String email) {
        return usuarioBO.obtenerUsuarioPorCorreo(email);
    }
    
     @WebMethod(operationName = "actualizarUsuario")
    public Integer actualizarUsuario(
            @WebParam(name = "idUsuario") Integer idUsuario,
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email) {

        return usuarioBO.actualizarUsuario(idUsuario, contraseña, nombre, email);
    }
    
    @WebMethod(operationName = "obtenerUsuarioPorId")
    public UsuarioDTO obtenerUsuarioPorId(@WebParam(name = "idUsuario") Integer idUsuario) {
        return usuarioBO.obtenerUsuarioPorId(idUsuario);
    }
}
