package pe.edu.pucp.techshopper.bo;

import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.daoImp.ClienteDAOImp;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

public class ClienteBO {

    private final ClienteDAO clienteDAO;
    
    public ClienteBO() {
        this.clienteDAO = new ClienteDAOImp();
    }
    
    public Integer registrarCliente(String contraseña, EstadoConexionDTO estadoConexion,
                                  String nombre, String direccion, String telefono,
                                  String email, String infoTarjetaCredito, Double balanceCuenta) {
        // Validaciones básicas
        if (contraseña == null || contraseña.isEmpty() || nombre == null || nombre.isEmpty() ||
            direccion == null || direccion.isEmpty() || email == null || email.isEmpty()) {
            return -1;
        }
        
        ClienteDTO cliente = new ClienteDTO();
        cliente.setContraseña(contraseña);
        cliente.setEstadoConexion(estadoConexion != null ? estadoConexion : EstadoConexionDTO.DESCONECTADO);
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setInfoTarjetaCredito(infoTarjetaCredito);
        cliente.setBalanceCuenta(balanceCuenta != null ? balanceCuenta : 0.0f);
        
        // Validar email único
        if (obtenerClientePorEmail(email) != null) {
            return -2; // Código para email duplicado
        }
        
        return clienteDAO.insertar(cliente);
    }
    
    public Integer actualizarCliente(Integer idCliente, String contraseña, EstadoConexionDTO estadoConexion,
                                   String nombre, String direccion, String telefono,
                                   String email, String infoTarjetaCredito, Double balanceCuenta) {
        // Validaciones básicas
        if (idCliente == null || idCliente <= 0 || nombre == null || nombre.isEmpty() ||
            direccion == null || direccion.isEmpty() || email == null || email.isEmpty()) {
            return -1;
        }
        
        ClienteDTO cliente = clienteDAO.obtenerPorId(idCliente);
        if (cliente == null) {
            return -1;
        }
        
        // Validar email único (si cambió)
        if (!cliente.getEmail().equals(email) && obtenerClientePorEmail(email) != null) {
            return -2; // Código para email duplicado
        }
        
        if (contraseña != null && !contraseña.isEmpty()) {
            cliente.setContraseña(contraseña);
        }
        if (estadoConexion != null) {
            cliente.setEstadoConexion(estadoConexion);
        }
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        if (infoTarjetaCredito != null) {
            cliente.setInfoTarjetaCredito(infoTarjetaCredito);
        }
        if (balanceCuenta != null) {
            cliente.setBalanceCuenta(balanceCuenta);
        }
        
        return clienteDAO.modificar(cliente);
    }
    
    public Integer eliminarCliente(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            return -1;
        }
        
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdPersona(idCliente);
        
        return clienteDAO.eliminar(cliente);
    }
    
    public ClienteDTO obtenerClientePorId(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            return null;
        }
        return clienteDAO.obtenerPorId(idCliente);
    }
    
    public ClienteDTO obtenerClientePorEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        
        ArrayList<ClienteDTO> clientes = clienteDAO.listarTodos();
        for (ClienteDTO cliente : clientes) {
            if (email.equalsIgnoreCase(cliente.getEmail())) {
                return cliente;
            }
        }
        return null;
    }
    
    public ArrayList<ClienteDTO> listarTodosClientes() {
        return clienteDAO.listarTodos();
    }
    
}