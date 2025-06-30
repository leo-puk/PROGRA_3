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
    
    public Integer registrarAdministradorCompleto(String contraseña, String nombre, String email) {
        // Validaciones básicas
        if (contraseña == null || contraseña.isEmpty() || 
            nombre == null || nombre.isEmpty() ||
            email == null || email.isEmpty()) {
            return -1; // Código de error para datos inválidos
        }

        // Validar que no exista un usuario con el mismo email
        UsuarioDTO usuarioExistente = usuarioDAO.obtenerPorEmail(email);
        if (usuarioExistente != null) {
            return -2; // Código de error para email duplicado
        }

        // 1. Registrar el usuario primero
        UsuarioDTO usuario = new UsuarioDTO();
        String contraseniaCifrada = Cifrado.cifrarMD5(contraseña);
        usuario.setContraseña(contraseniaCifrada);
        //usuario.setContraseña(contraseña);
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
        NivelAccesoDTO nivelAcceso = NivelAccesoDTO.BASICO; // Valor por defecto
        LocalDateTime fechaUltimoAcceso = LocalDateTime.now();

        int resultado = registrarDatosAdministrador(idUsuario, nivelAcceso, fechaUltimoAcceso);


        return idUsuario;
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
                                                  String nombre, String email) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 ||
            nombre == null || nombre.isEmpty() || email == null || email.isEmpty()) {
            return -1;
        }

        // 1. Actualizar datos de usuario primero
        UsuarioBO usuarioBO = new UsuarioBO();
        int resultadoUsuario = usuarioBO.actualizarUsuario(idUsuario, contraseña,nombre, email);

        if (resultadoUsuario <= 0) {
            return resultadoUsuario; // Retorna el código de error del usuario
        }

        // 2. Actualizar datos específicos de administrador
        AdministradorDTO administrador = administradorDAO.obtenerPorId(idUsuario);
        if (administrador == null) {
            return -3; // Código de error específico para administrador no encontrado
        }

        return actualizarDatosAdministrador(idUsuario);
        
    }
    
    
    public Integer actualizarDatosAdministrador(Integer idUsuario) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 ) {
            return -1;
        }
        
        AdministradorDTO administrador = administradorDAO.obtenerPorId(idUsuario);
        if (administrador == null) {
            return -2;
        }
        
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

        // Cifrar contraseña 
        String contraseniaCifrada = Cifrado.cifrarMD5(contrasenia);

        UsuarioDTO filtro = new UsuarioDTO();
        filtro.setContraseña(contraseniaCifrada);
//        filtro.setContraseña(contrasenia);

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
            
            return null;
        }

        // Devolver usuario con contraseña descifrada opcionalmente
//        usuario.setContraseña(Cifrado.descifrarMD5(usuario.getContraseña()));

        return usuario;
    }
    
    

    
}