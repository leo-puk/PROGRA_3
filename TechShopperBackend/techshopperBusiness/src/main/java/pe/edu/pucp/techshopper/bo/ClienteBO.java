package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.dao.UsuarioDAO;
import pe.edu.pucp.techshopper.daoImp.ClienteDAOImp;
import pe.edu.pucp.techshopper.daoImp.UsuarioDAOImp;
import pe.edu.pucp.techshopper.db.util.Cifrado;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class ClienteBO {

    private final ClienteDAO clienteDAO;
    private final UsuarioDAO usuarioDAO;
    
    public ClienteBO() {
        this.clienteDAO = new ClienteDAOImp();
        this.usuarioDAO = new UsuarioDAOImp();
    }
    
    public Integer registrarClienteCompleto(String contraseña,
                                          String nombre, String email, String direccion, 
                                          String telefono, String infoTarjetaCredito, 
                                          Double balanceCuenta) {
        // 1. Registrar el usuario primero
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setContraseña(contraseña);
        usuario.setEstadoConexion(EstadoConexionDTO.DESCONECTADO);
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setRol(RolUsuarioDTO.CLIENTE);
        
        int idUsuario = usuarioDAO.insertar(usuario);
        if (idUsuario <= 0) {
            return idUsuario; // Error al registrar usuario
        }
        
        // 2. Registrar los datos específicos del cliente
        return registrarDatosCliente(idUsuario, direccion, telefono, 
                                    infoTarjetaCredito, balanceCuenta);
    }
    
    public Integer registrarDatosCliente(Integer idUsuario, String direccion, 
                                       String telefono, String infoTarjetaCredito, 
                                       Double balanceCuenta) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || 
            direccion == null || direccion.isEmpty()) {
            return -1;
        }
        
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdUsuario(idUsuario);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setInfoTarjetaCredito(infoTarjetaCredito);
        cliente.setBalanceCuenta(balanceCuenta != null ? balanceCuenta : 0.0);
        
        return clienteDAO.insertar(cliente);
    }
    
    public Integer actualizarDatosclienteCompleto(Integer idUsuario, String contraseña, 
                                                  EstadoConexionDTO estadoConexion,
                                                  String nombre, String email,
                                                  String direccion, 
                                                  String telefono, String infoTarjetaCredito, 
                                                  Double balanceCuenta) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || 
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
        ClienteDTO cliente = clienteDAO.obtenerPorId(idUsuario);
        if (cliente == null) {
            return -3; // Código de error específico para administrador no encontrado
        }

        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setInfoTarjetaCredito(infoTarjetaCredito);
        cliente.setBalanceCuenta(balanceCuenta);
        return actualizarDatosCliente(idUsuario,direccion, telefono,  infoTarjetaCredito, 
                                         balanceCuenta);
    }
    
    public Integer actualizarDatosCliente(Integer idUsuario, String direccion, 
                                        String telefono, String infoTarjetaCredito, 
                                        Double balanceCuenta) {
        // Validaciones básicas
        if (idUsuario == null || idUsuario <= 0 || 
            direccion == null || direccion.isEmpty()) {
            return -1;
        }
        
        ClienteDTO cliente = clienteDAO.obtenerPorId(idUsuario);
        if (cliente == null) {
            return -2; // No existe registro de cliente
        }
        
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        
        if (infoTarjetaCredito != null) {
            cliente.setInfoTarjetaCredito(infoTarjetaCredito);
        }
        
        if (balanceCuenta != null) {
            cliente.setBalanceCuenta(balanceCuenta);
        }
        
        return clienteDAO.modificar(cliente);
    }
    
    public Integer eliminarDatosCliente(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return -1;
        }

        
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(idUsuario);
        return usuarioDAO.eliminar(usuario);
    }
    
    public ClienteDTO obtenerClienteCompleto(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return null;
        }

        // Obtener datos específicos de cliente
        ClienteDTO cliente = clienteDAO.obtenerPorId(idUsuario);
        if (cliente == null) {
            return null;
        }

        // Obtener y asignar datos de usuario
        UsuarioDTO usuario = usuarioDAO.obtenerPorId(idUsuario);
        if (usuario != null) {
            // Asignar todos los campos relevantes
            cliente.setContraseña(usuario.getContraseña());
            cliente.setEstadoConexion(usuario.getEstadoConexion());
            cliente.setFechaRegistro(usuario.getFechaRegistro());
            cliente.setNombre(usuario.getNombre());
            cliente.setEmail(usuario.getEmail());
            cliente.setRol(usuario.getRol());
        }

        return cliente;
    }
    
    public ArrayList<ClienteDTO> listarTodosDatosClientes() {
        return clienteDAO.listarTodos();
    }
    
    // Método adicional para actualizar balance
    public Integer actualizarBalance(Integer idUsuario, Double nuevoBalance) {
        if (idUsuario == null || idUsuario <= 0 || nuevoBalance == null) {
            return -1;
        }
        
        ClienteDTO cliente = clienteDAO.obtenerPorId(idUsuario);
        if (cliente == null) {
            return -2;
        }
        
        cliente.setBalanceCuenta(nuevoBalance);
        return clienteDAO.modificar(cliente);
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
        
        if(usuario.getRol() != RolUsuarioDTO.CLIENTE){
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