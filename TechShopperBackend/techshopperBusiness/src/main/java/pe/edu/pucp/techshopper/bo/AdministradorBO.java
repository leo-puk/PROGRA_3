package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.dao.UsuarioDAO;
import pe.edu.pucp.techshopper.daoImp.AdministradorDAOImp;
import pe.edu.pucp.techshopper.daoImp.UsuarioDAOImp;
import pe.edu.pucp.techshopper.db.util.Cifrado;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.NivelAccesoDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class AdministradorBO {
    private final AdministradorDAO administradorDAO;
    private final UsuarioDAO usuarioDAO;
    
    public AdministradorBO() {
        this.administradorDAO = new AdministradorDAOImp();
        this.usuarioDAO = new UsuarioDAOImp();
    }
    
    public Integer registrarAdministradorCompleto(String contraseña,String nombre, String email) {
        // 1. Registrar el usuario primero
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setContraseña(contraseña);
        usuario.setEstadoConexion(EstadoConexionDTO.CONECTADO);
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setRol(RolUsuarioDTO.ADMINISTRADOR);
        
        int idUsuario = usuarioDAO.insertar(usuario);
        if (idUsuario <= 0) {
            return idUsuario; // Error al registrar usuario
        }
        
        // 2. Registrar los datos específicos del administrador
        
        NivelAccesoDTO nivelAcceso= NivelAccesoDTO.BASICO;
        
        LocalDateTime fechaUltimoAcceso = LocalDateTime.now();
                
        return registrarDatosAdministrador(idUsuario, nivelAcceso, fechaUltimoAcceso);
    }
    
    public Integer registrarDatosAdministrador(Integer idUsuario, NivelAccesoDTO nivelAcceso, LocalDateTime fechaUltimoAcceso) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || nivelAcceso == null) {
            return -1;
        }
        
        AdministradorDTO administrador = new AdministradorDTO();
        administrador.setIdUsuario(idUsuario);
        administrador.setNivelAcceso(nivelAcceso);
        administrador.setFechaUltimoAcceso(fechaUltimoAcceso);
        
        return administradorDAO.insertar(administrador);
    }
    
    public Integer actualizarDatosAdministradorCompleto(Integer idUsuario, String contraseña, 
                                                  EstadoConexionDTO estadoConexion,
                                                  String nombre, String email,
                                                  NivelAccesoDTO nivelAcceso, LocalDateTime fechaUltimoAcceso) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || nivelAcceso == null || 
            nombre == null || nombre.isEmpty() || email == null || email.isEmpty()) {
            return -1;
        }

        // 1. Actualizar datos de usuario primero
        UsuarioBO usuarioBO = new UsuarioBO();
        int resultadoUsuario = usuarioBO.actualizarUsuario(idUsuario, contraseña, estadoConexion, nombre, email);

        if (resultadoUsuario <= 0) {
            return resultadoUsuario; // Retorna el código de error del usuario
        }

        // 2. Actualizar datos específicos de administrador
        AdministradorDTO administrador = administradorDAO.obtenerPorId(idUsuario);
        if (administrador == null) {
            return -3; // Código de error específico para administrador no encontrado
        }

        administrador.setNivelAcceso(nivelAcceso);
        return actualizarDatosAdministrador(idUsuario,nivelAcceso,fechaUltimoAcceso);
    }
    
    
    public Integer actualizarDatosAdministrador(Integer idUsuario, NivelAccesoDTO nivelAcceso, LocalDateTime fechaUltimoAcceso) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || nivelAcceso == null) {
            return -1;
        }
        
        AdministradorDTO administrador = administradorDAO.obtenerPorId(idUsuario);
        if (administrador == null) {
            return -2;
        }
        
        administrador.setNivelAcceso(nivelAcceso);
        administrador.setFechaUltimoAcceso(fechaUltimoAcceso);
        return administradorDAO.modificar(administrador);
    }
    
    public Integer eliminarAdministradorCompleto(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return -1;
        }

        
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(idUsuario);
        return usuarioDAO.eliminar(usuario);
    }
    
    public AdministradorDTO obtenerAdministradorCompleto(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return null;
        }

        // Obtener datos específicos de administrador
        AdministradorDTO administrador = administradorDAO.obtenerPorId(idUsuario);
        if (administrador == null) {
            return null;
        }

        // Obtener y asignar datos de usuario
        UsuarioDTO usuario = usuarioDAO.obtenerPorId(idUsuario);
        if (usuario != null) {
            // Asignar todos los campos relevantes
            administrador.setContraseña(usuario.getContraseña());
            administrador.setEstadoConexion(usuario.getEstadoConexion());
            administrador.setFechaRegistro(usuario.getFechaRegistro());
            administrador.setNombre(usuario.getNombre());
            administrador.setEmail(usuario.getEmail());
            administrador.setRol(usuario.getRol());
        }

        return administrador;
    }
    
    
    public Integer actualizarNivelAcceso(Integer idUsuario, NivelAccesoDTO nuevoNivel) {
        if (idUsuario == null || idUsuario <= 0 || nuevoNivel == null) {
            return -1;
        }
        
        AdministradorDTO administrador = administradorDAO.obtenerPorId(idUsuario);
        if (administrador == null) {
            return -2;
        }
        
        administrador.setNivelAcceso(nuevoNivel);
        return administradorDAO.modificar(administrador);
    }
    
    public UsuarioDTO iniciarSesion(String correoONombreUsuario, String contrasenia) {
        if (correoONombreUsuario == null || contrasenia == null ||
            correoONombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            return null; // Datos inválidos
        }

        // Cifrar contraseña (suponiendo que usas MD5 u otro mecanismo en BD)
//        String contraseniaCifrada = Cifrado.cifrarMD5(contrasenia); // Asegúrate que exista esta clase utilitaria

        UsuarioDTO filtro = new UsuarioDTO();
//        filtro.setContraseña(contraseniaCifrada);
        filtro.setContraseña(contrasenia);

        if (correoONombreUsuario.contains("@")) {
            filtro.setEmail(correoONombreUsuario);
        } else {
            filtro.setNombre(correoONombreUsuario);
        }

        UsuarioDTO usuario = usuarioDAO.obtenerPorAtributosUnicos(filtro);

        if (usuario == null) {
            return null; // No encontrado
        }
        
        if(usuario.getRol() != RolUsuarioDTO.ADMINISTRADOR){
            return null;
        }

        // Validación opcional de estado del usuario (ej: inactivo)
        if (usuario.getEstadoConexion() != null &&
            usuario.getEstadoConexion().equals(EstadoConexionDTO.DESCONECTADO)) {
            // A criterio: puedes retornar null o permitir ingreso
            return null;
        }

        // Devolver usuario con contraseña descifrada opcionalmente
//        usuario.setContraseña(Cifrado.descifrarMD5(usuario.getContraseña()));

        return usuario;
    }

    
}