package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.EnvioBO;
import pe.edu.pucp.techshopper.model.EnvioDTO;


@WebService(serviceName = "Envios")
public class Envios {

    private final EnvioBO envioBO;
    
    
    public Envios() {
        this.envioBO = new EnvioBO();
    }
    
    @WebMethod(operationName = "actualizarEmpresaCourier")
    public EnvioDTO actualizarEmpresaCourier(
        @WebParam(name = "idEnvio") Integer idEnvio,
        @WebParam(name = "nuevaEmpresaCourier") String nuevaEmpresaCourier,
        @WebParam(name = "idAdminEditor") Integer idAdminEditor)
        throws SecurityException, IllegalArgumentException, RuntimeException 
    {
        try {
            return envioBO.actualizarEmpresaCourier(idEnvio, nuevaEmpresaCourier, idAdminEditor);
        } catch (RuntimeException e) {
            System.err.println("Error en WS actualizarEmpresaCourier: " + e.getMessage());
            throw e; 
        }
    }
    
    @WebMethod(operationName = "crearEnvio")
    public EnvioDTO crearEnvio(
            @WebParam(name = "fechaEntrega") String fechaEntregaStr, // Se recibe como String
            @WebParam(name = "empresaCourier") String empresaCourier,
            @WebParam(name = "precio") Double precio,
            @WebParam(name = "idLocalizacion") Integer idLocalizacion)
            throws IllegalArgumentException, RuntimeException 
    {
        try {
            
            LocalDateTime fechaEntrega = LocalDateTime.parse(fechaEntregaStr); 

            return envioBO.crearEnvio(fechaEntrega, empresaCourier, precio, idLocalizacion);
        } catch (java.time.format.DateTimeParseException e) {
            // Captura específicamente errores de formato de fecha/hora
            System.err.println("Error de formato de fecha en WS crearEnvio: " + e.getMessage());
            throw new IllegalArgumentException("Formato de fecha de entrega inválido. Por favor, use el formato ISO-8601 (ej. 'YYYY-MM-DDTHH:MM:SS').", e);
        } catch (RuntimeException e) {
            
            System.err.println("Error en WS crearEnvio: " + e.getMessage());
            throw e;
        }
    }
    
    @WebMethod(operationName = "modificarEnvio")
    public EnvioDTO modificarEnvio(
            @WebParam(name = "envio") EnvioDTO envio) 
            throws IllegalArgumentException, RuntimeException
    {
        try {
            
            return envioBO.modificarEnvio(envio);
        } catch (RuntimeException e) {
            System.err.println("Error en WS modificarEnvio: " + e.getMessage());
            throw e; 
        }
    }
    
    @WebMethod(operationName = "eliminarEnvio")
    public Integer eliminarEnvio(
            @WebParam(name = "idEnvio") Integer idEnvio)
            throws IllegalArgumentException, IllegalStateException, RuntimeException
    {
        try {
            return envioBO.eliminarEnvio(idEnvio);
        } catch (RuntimeException e) {
            System.err.println("Error en WS eliminarEnvio: " + e.getMessage());
            throw e;
        }
    }
    
    @WebMethod(operationName = "obtenerEnvioPorId")
    public EnvioDTO obtenerEnvioPorId(
            @WebParam(name = "idEnvio") Integer idEnvio)
            throws IllegalArgumentException, RuntimeException
    {
        try {
            return envioBO.obtenerEnvioPorId(idEnvio);
        } catch (RuntimeException e) {
            System.err.println("Error en WS obtenerEnvioPorId: " + e.getMessage());
            throw e;
        }
    }

    
    @WebMethod(operationName = "listarTodosLosEnvios")
    public ArrayList<EnvioDTO> listarTodosLosEnvios()
            throws RuntimeException
    {
        try {
            return envioBO.listarTodosLosEnvios();
        } catch (RuntimeException e) {
            System.err.println("Error en WS listarTodosLosEnvios: " + e.getMessage());
            throw e;
        }
    }
    
    
}
