package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import pe.edu.pucp.techshopper.dao.UsuarioDAO;
import pe.edu.pucp.techshopper.daoImp.UsuarioDAOImp;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class UsuarioBO {

    private final UsuarioDAO usuarioDAO;
    
    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAOImp();
    }
    
    public Integer registrarUsuario(String contraseña, EstadoConexionDTO estadoConexion, LocalDateTime fechaRegistro,
                              String nombre, String email, RolUsuarioDTO rol) {
        // Validaciones básicas
        if (contraseña == null || contraseña.isEmpty() || fechaRegistro == null || 
            nombre == null || nombre.isEmpty() ||
            email == null || email.isEmpty() || rol == null) {
            return -1;
        }

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setContraseña(contraseña);
        usuario.setEstadoConexion(estadoConexion != null ? estadoConexion : EstadoConexionDTO.DESCONECTADO);
        usuario.setNombre(nombre);
        usuario.setFechaRegistro(fechaRegistro);
        usuario.setEmail(email);
        usuario.setRol(rol);

        return usuarioDAO.insertar(usuario);
    }
    
    public Integer actualizarUsuario(Integer idUsuario, String contraseña, 
                                   String nombre, String email) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || 
            nombre == null || nombre.isEmpty() ||
            email == null || email.isEmpty()) {
            return -1;
        }
        
        UsuarioDTO usuario = usuarioDAO.obtenerPorId(idUsuario);
        if (usuario == null) {
            return -1;
        }
        
        if (contraseña != null && !contraseña.isEmpty()) {
            usuario.setContraseña(contraseña);
        }
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        
        return usuarioDAO.modificar(usuario);
    }
    
    public Integer eliminarUsuario(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return -1;
        }
        
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(idUsuario);
        
        return usuarioDAO.eliminar(usuario);
    }
    
    public UsuarioDTO obtenerUsuarioPorId(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return null;
        }
        return usuarioDAO.obtenerPorId(idUsuario);
    }
    
    
    public Integer actualizarEstadoConexion(Integer idUsuario, EstadoConexionDTO estado) {
        if (idUsuario == null || idUsuario <= 0 || estado == null) {
            return -1;
        }
        
        UsuarioDTO usuario = usuarioDAO.obtenerPorId(idUsuario);
        if (usuario == null) {
            return -2;
        }
        
        usuario.setEstadoConexion(estado);
        return usuarioDAO.modificar(usuario);
    }
    
    public UsuarioDTO obtenerUsuarioPorCorreo(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        UsuarioDTO usuario = usuarioDAO.obtenerPorEmail(email);

        if (usuario != null) {
            return usuario;
        } else {
            return null;
        }
    }

}