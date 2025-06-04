package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.ClienteBO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

@WebService(serviceName = "ClientesWS")
public class Clientes {

    private ClienteBO clienteBO;

    public Clientes() {
        this.clienteBO = new ClienteBO();
    }

    @WebMethod(operationName = "registrarCliente")
    public Integer registrarCliente(
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "estadoConexion") String estadoConexionStr,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "email") String email,
            @WebParam(name = "infoTarjetaCredito") String infoTarjetaCredito,
            @WebParam(name = "balanceCuenta") Double balanceCuenta) {
        
        try {
            EstadoConexionDTO estadoConexion = estadoConexionStr != null ? 
                EstadoConexionDTO.valueOf(estadoConexionStr) : EstadoConexionDTO.DESCONECTADO;
            
            return clienteBO.registrarCliente(
                contraseña, 
                estadoConexion,
                nombre, 
                direccion, 
                telefono,
                email, 
                infoTarjetaCredito, 
                balanceCuenta
            );
        } catch (IllegalArgumentException e) {
            return -3; // Código para estado de conexión inválido
        }
    }

    @WebMethod(operationName = "actualizarCliente")
    public Integer actualizarCliente(
            @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "estadoConexion") String estadoConexionStr,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "email") String email,
            @WebParam(name = "infoTarjetaCredito") String infoTarjetaCredito,
            @WebParam(name = "balanceCuenta") Double balanceCuenta) {
        
        try {
            EstadoConexionDTO estadoConexion = estadoConexionStr != null ? 
                EstadoConexionDTO.valueOf(estadoConexionStr) : null;
            
            return clienteBO.actualizarCliente(
                idCliente,
                contraseña, 
                estadoConexion,
                nombre, 
                direccion, 
                telefono,
                email, 
                infoTarjetaCredito, 
                balanceCuenta
            );
        } catch (IllegalArgumentException e) {
            return -3; // Código para estado de conexión inválido
        }
    }

    @WebMethod(operationName = "eliminarCliente")
    public Integer eliminarCliente(
            @WebParam(name = "idCliente") Integer idCliente) {
        return clienteBO.eliminarCliente(idCliente);
    }

    @WebMethod(operationName = "obtenerClientePorId")
    public ClienteDTO obtenerClientePorId(
            @WebParam(name = "idCliente") Integer idCliente) {
        return clienteBO.obtenerClientePorId(idCliente);
    }

    @WebMethod(operationName = "obtenerClientePorEmail")
    public ClienteDTO obtenerClientePorEmail(
            @WebParam(name = "email") String email) {
        return clienteBO.obtenerClientePorEmail(email);
    }

    @WebMethod(operationName = "listarTodosClientes")
    public ArrayList<ClienteDTO> listarTodosClientes() {
        return clienteBO.listarTodosClientes();
    }

    @WebMethod(operationName = "autenticarCliente")
    public ClienteDTO autenticarCliente(
            @WebParam(name = "email") String email,
            @WebParam(name = "contraseña") String contraseña) {
        
        ClienteDTO cliente = clienteBO.obtenerClientePorEmail(email);
        if (cliente != null && cliente.getContraseña().equals(contraseña)) {
            return cliente;
        }
        return null;
    }

    @WebMethod(operationName = "actualizarEstadoConexionCliente")
    public Integer actualizarEstadoConexionCliente(
            @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "nuevoEstado") String nuevoEstadoStr) {
        
        try {
            EstadoConexionDTO nuevoEstado = EstadoConexionDTO.valueOf(nuevoEstadoStr);
            ClienteDTO cliente = clienteBO.obtenerClientePorId(idCliente);
            if (cliente != null) {
                return clienteBO.actualizarCliente(
                    cliente.getIdPersona(),
                    cliente.getContraseña(),
                    nuevoEstado,
                    cliente.getNombre(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getEmail(),
                    cliente.getInfoTarjetaCredito(),
                    cliente.getBalanceCuenta()
                );
            }
            return -1;
        } catch (IllegalArgumentException e) {
            return -3; // Estado de conexión no válido
        }
    }

    @WebMethod(operationName = "actualizarBalanceCliente")
    public Integer actualizarBalanceCliente(
            @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "nuevoBalance") Double nuevoBalance) {
        
        ClienteDTO cliente = clienteBO.obtenerClientePorId(idCliente);
        if (cliente != null) {
            return clienteBO.actualizarCliente(
                cliente.getIdPersona(),
                cliente.getContraseña(),
                cliente.getEstadoConexion(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getInfoTarjetaCredito(),
                nuevoBalance
            );
        }
        return -1;
    }
}