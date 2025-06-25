/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.dao.EnvioDAO;
import pe.edu.pucp.techshopper.daoImp.AdministradorDAOImp;
import pe.edu.pucp.techshopper.daoImp.EnvioDAOImp;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;
import pe.edu.pucp.techshopper.model.NivelAccesoDTO;


public class EnvioBO {
    private final EnvioDAO envioDAO;
    private final AdministradorDAO administradorDAO;
    
    public EnvioBO() {
        this.envioDAO = new EnvioDAOImp();
        this.administradorDAO = new AdministradorDAOImp(); 
    }
    
    public EnvioDTO actualizarEmpresaCourier(Integer idEnvio, String nuevaEmpresaCourier, Integer idAdminEditor) {
        
        if (idAdminEditor == null || idAdminEditor <= 0) {
            throw new SecurityException("ID de administrador no proporcionado o inválido. Acceso denegado.");
        }

        AdministradorDTO adminEditor = administradorDAO.obtenerPorId(idAdminEditor);

        if (adminEditor == null) {
            throw new SecurityException("Administrador con ID " + idAdminEditor + " no encontrado. Acceso denegado.");
        }

        // Se define el nivel de acceso requerido para esta operación.
        if (adminEditor.getNivelAcceso() == null ||
            (adminEditor.getNivelAcceso() != NivelAccesoDTO.SUPER &&
             adminEditor.getNivelAcceso() != NivelAccesoDTO.MEDIO)) { 
            throw new SecurityException("El usuario con ID " + idAdminEditor + " no tiene el nivel de acceso requerido para actualizar la empresa courier.");
        }
        
        EnvioDTO envioExistente = envioDAO.obtenerPorId(idEnvio);
        if (envioExistente == null) {
            throw new IllegalArgumentException("Envío con ID " + idEnvio + " no encontrado. No se puede actualizar.");
        }

        
        envioExistente.setEmpresaCourier(nuevaEmpresaCourier);

       
        Integer resultado = envioDAO.modificar(envioExistente);

        if (resultado > 0) {
            return envioExistente; 
        } else {
            throw new RuntimeException("Fallo al actualizar la empresa courier para el envío " + idEnvio + ". La operación de base de datos no fue exitosa.");
        }
    }
     
    
    public EnvioDTO crearEnvio(LocalDateTime fechaEntrega, String empresaCourier, Double precio, Integer idLocalizacion) {
        // Validaciones de negocio y de entrada
        if (fechaEntrega == null) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser nula.");
        }
        if (fechaEntrega.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser anterior a la fecha actual.");
        }
        if (empresaCourier == null || empresaCourier.trim().isEmpty()) {
            throw new IllegalArgumentException("La empresa de courier no puede ser nula o vacía.");
        }
        if (precio == null || precio < 0) {
            throw new IllegalArgumentException("El precio del envío no puede ser nulo o negativo.");
        }
        if (idLocalizacion == null || idLocalizacion <= 0) {
            throw new IllegalArgumentException("ID de localización de destino inválido.");
        }

        // Aquí podrías validar que la localización exista, si tienes un DAO/BO de Localizacion
        // if (localizacionDAO.obtenerPorId(idLocalizacion) == null) {
        //     throw new IllegalArgumentException("La localización con ID " + idLocalizacion + " no existe.");
        // }

        EnvioDTO nuevoEnvio = new EnvioDTO();
        nuevoEnvio.setFechaEntrega(fechaEntrega);
        nuevoEnvio.setEmpresaCourier(empresaCourier);
        nuevoEnvio.setPrecio(precio);

        LocalizacionDTO destino = new LocalizacionDTO();
        destino.setIdLocalizacion(idLocalizacion);
        nuevoEnvio.setDestino(destino);

        Integer idGenerado = envioDAO.insertar(nuevoEnvio);

        if (idGenerado != null && idGenerado > 0) {
            nuevoEnvio.setIdEnvio(idGenerado);
            return nuevoEnvio;
        } else {
            throw new RuntimeException("Fallo al insertar el envío en la base de datos.");
        }
    }
    
    
    public EnvioDTO modificarEnvio(EnvioDTO envio) {
        if (envio == null || envio.getIdEnvio() == null || envio.getIdEnvio() <= 0) {
            throw new IllegalArgumentException("Datos de envío inválidos para la modificación: ID nulo o inválido.");
        }
        // Validaciones adicionales para los campos del DTO según TCS_ENVIOS
        if (envio.getPrecio() == null || envio.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del envío no puede ser nulo o negativo.");
        }
        if (envio.getEmpresaCourier() == null || envio.getEmpresaCourier().trim().isEmpty()) {
            throw new IllegalArgumentException("La empresa de courier no puede ser nula o vacía.");
        }
        if (envio.getDestino() == null || envio.getDestino().getIdLocalizacion() == null || envio.getDestino().getIdLocalizacion() <= 0) {
            throw new IllegalArgumentException("La localización de destino del envío es inválida.");
        }
        if (envio.getFechaEntrega() == null) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser nula.");
        }
       

        
        EnvioDTO envioExistente = envioDAO.obtenerPorId(envio.getIdEnvio());
        if (envioExistente == null) {
            throw new IllegalArgumentException("Envío con ID " + envio.getIdEnvio() + " no encontrado para modificación.");
        }

        
        envioExistente.setFechaEntrega(envio.getFechaEntrega());
        envioExistente.setEmpresaCourier(envio.getEmpresaCourier());
        envioExistente.setPrecio(envio.getPrecio());
        // Actualizar la localización de destino (solo el ID, asumiendo que es una FK)
        if (envio.getDestino() != null && envio.getDestino().getIdLocalizacion() != null) {
             LocalizacionDTO nuevaLocalizacion = new LocalizacionDTO();
             nuevaLocalizacion.setIdLocalizacion(envio.getDestino().getIdLocalizacion());
             envioExistente.setDestino(nuevaLocalizacion);
        } else {
             // Decide cómo manejar si la localización viene nula o con ID nulo
             throw new IllegalArgumentException("La localización de destino no puede ser nula.");
        }

        

        Integer resultado = envioDAO.modificar(envioExistente);

        if (resultado != null && resultado > 0) {
            return envioExistente; // Retorna el objeto actualizado
        } else {
            throw new RuntimeException("Fallo al modificar el envío con ID " + envio.getIdEnvio() + ". La operación de base de datos no fue exitosa.");
        }
    }
    
    
    public Integer eliminarEnvio(Integer idEnvio) {
        if (idEnvio == null || idEnvio <= 0) {
            throw new IllegalArgumentException("ID de envío inválido para la eliminación.");
        }

        // Verificar existencia antes de intentar eliminar
        EnvioDTO envioExistente = envioDAO.obtenerPorId(idEnvio);
        if (envioExistente == null) {
            throw new IllegalArgumentException("Envío con ID " + idEnvio + " no encontrado para eliminación.");
        }

        
        EnvioDTO envioToDelete = new EnvioDTO();
        envioToDelete.setIdEnvio(idEnvio);

        Integer resultado = envioDAO.eliminar(envioToDelete);

        if (resultado != null && resultado > 0) {
            return idEnvio;
        } else {
            throw new RuntimeException("Fallo al eliminar el envío con ID " + idEnvio + ". La operación de base de datos no fue exitosa.");
        }
    }
    
    public EnvioDTO obtenerEnvioPorId(Integer idEnvio) {
        if (idEnvio == null || idEnvio <= 0) {
            throw new IllegalArgumentException("ID de envío inválido.");
        }

        EnvioDTO envio = envioDAO.obtenerPorId(idEnvio);
        if (envio == null) {
            throw new RuntimeException("Envío con ID " + idEnvio + " no encontrado.");
        }
        return envio;
    }
    
    public ArrayList<EnvioDTO> listarTodosLosEnvios() {
        try {
            return envioDAO.listarTodos();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar todos los envíos: " + e.getMessage(), e);
        }
    }
     
}
