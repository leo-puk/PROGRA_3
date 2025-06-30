package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.ClienteBO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;
import pe.edu.pucp.techshopper.servicesUtils.CarritoItemsDTOSoap;

@WebService(serviceName = "Clientes")
public class Clientes {

    private ClienteBO clienteBO;

    public Clientes() {
        this.clienteBO = new ClienteBO();
    }

    @WebMethod(operationName = "iniciarSesion")
public UsuarioDTO iniciarSesion(
        @WebParam(name = "correoONombreUsuario") String correoONombreUsuario,
        @WebParam(name = "contrasenia") String contrasenia) {
    try {
        if (correoONombreUsuario == null || contrasenia == null ||
            correoONombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            return null; // Datos inválidos
        }

        return clienteBO.iniciarSesion(correoONombreUsuario, contrasenia);
    } catch (Exception e) {
        return null;
    }
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
//            EmailUtil.enviarEmailVerificacion(email, nombre);
                
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
    
    @WebMethod(operationName = "insertarCarrito")
    public Integer insertarCarrito(
            @WebParam(name = "idUsuario") Integer idUsuario,
            @WebParam(name = "idProducto") Integer idProducto,
            @WebParam(name = "cantidad") Integer cantidad) {
        return clienteBO.insertarCarrito(idUsuario, idProducto, cantidad);
    }

    @WebMethod(operationName = "mostrarCarritoDeCliente")
public ArrayList<CarritoItemsDTOSoap> mostrarCarritoDeCliente(
        @WebParam(name = "idUsuario") Integer idUsuario) {

    ArrayList<CarritoItemsDTO> listaOriginal = clienteBO.MostrarCarritoDeCliente(idUsuario);
    ArrayList<CarritoItemsDTOSoap> listaConvertida = new ArrayList<>();

    for (CarritoItemsDTO item : listaOriginal) {
        listaConvertida.add(new CarritoItemsDTOSoap(item));
    }

    return listaConvertida;
}

    @WebMethod(operationName = "realizarCompra")
    public Integer realizarCompra(@WebParam(name = "idUsuario")Integer idUsuario,@WebParam(name = "idItemSeleccionados") ArrayList<Integer> idItemSeleccionados){
        return this.clienteBO.realizarCompra(idUsuario, idItemSeleccionados);
    }
    
    @WebMethod(operationName = "obtenerClientePorCorreo")
    public ClienteDTO obtenerUsuarioPorCorreo(@WebParam(name = "email") String email) {
        return clienteBO.obtenerClientePorCorreo(email);
    }
    
}