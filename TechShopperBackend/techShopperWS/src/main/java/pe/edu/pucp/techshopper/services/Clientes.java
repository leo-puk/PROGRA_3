package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.ClienteBO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

@WebService(serviceName = "Clientes")
public class Clientes {

    private ClienteBO clienteBO;

    public Clientes() {
        this.clienteBO = new ClienteBO();
    }

    @WebMethod(operationName = "registrarCliente")
    public Integer registrarCliente(
            @WebParam(name = "contraseña") String contraseña,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "infoTarjetaCredito") String infoTarjetaCredito,
            @WebParam(name = "balanceCuenta") Double balanceCuenta) {
        
        try {
            
            return clienteBO.registrarClienteCompleto(
                contraseña,
                nombre, 
                email,
                direccion, 
                telefono,
                infoTarjetaCredito, 
                balanceCuenta
            );
        } catch (IllegalArgumentException e) {
            return -3; 
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
            
            return clienteBO.actualizarDatosclienteCompleto(
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
            return -3; 
        }
    }

    @WebMethod(operationName = "eliminarCliente")
    public Integer eliminarCliente(
            @WebParam(name = "idCliente") Integer idCliente) {
        return clienteBO.eliminarDatosCliente(idCliente);
    }

    @WebMethod(operationName = "obtenerClientePorId")
    public ClienteDTO obtenerClientePorId(
            @WebParam(name = "idCliente") Integer idCliente) {
        return clienteBO.obtenerClienteCompleto(idCliente);
    }

    @WebMethod(operationName = "actualizarEstadoConexionCliente")
    public Integer actualizarEstadoConexionCliente(
            @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "nuevoEstado") String nuevoEstadoStr) {
        
        try {
            EstadoConexionDTO nuevoEstado = EstadoConexionDTO.valueOf(nuevoEstadoStr);
            ClienteDTO cliente = clienteBO.obtenerClienteCompleto(idCliente);
            if (cliente != null) {
                return clienteBO.actualizarDatosclienteCompleto(
                    cliente.getIdUsuario(),
                    cliente.getContraseña(),
                    nuevoEstado,
                    cliente.getNombre(),
                    cliente.getEmail(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getInfoTarjetaCredito(),
                    cliente.getBalanceCuenta()
                );
            }
            return -1;
        } catch (IllegalArgumentException e) {
            return -3; 
        }
    }

    @WebMethod(operationName = "actualizarBalanceCliente")
    public Integer actualizarBalanceCliente(
            @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "nuevoBalance") Double nuevoBalance) {
        
        ClienteDTO cliente = clienteBO.obtenerClienteCompleto(idCliente);
        if (cliente != null) {
            return clienteBO.actualizarDatosclienteCompleto(
                cliente.getIdUsuario(),
                cliente.getContraseña(),
                cliente.getEstadoConexion(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getInfoTarjetaCredito(),
                nuevoBalance
            );
        }
        return -1;
    }
}