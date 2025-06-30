package pe.edu.pucp.techshopper.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.bo.ClienteBO;
import pe.edu.pucp.techshopper.bo.LocalizacionBO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;
import pe.edu.pucp.techshopper.servicesUtils.CarritoItemsDTOSoap;


@WebService(serviceName = "Localizaciones")
public class Localizaciones {

    private LocalizacionBO localizacionBO;

    public Localizaciones() {
        this.localizacionBO = new LocalizacionBO();
    }

    @WebMethod(operationName = "insertarLocalizacion")
    public Integer Insertar(@WebParam(name = "direccion") String direccion, 
            @WebParam(name = "latitud") Double latitud,
            @WebParam(name = "longitud") Double longitud,
            @WebParam(name = "idUsuario") Integer idUsuario) {
        return localizacionBO.insertar(direccion, latitud, longitud, idUsuario);
    }

    @WebMethod(operationName = "actualizarLocalizacion")
    public Integer actualizarLocalizacion(
            @WebParam(name = "idLocalizacion") Integer idLocalizacion,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "idUsuario") Integer idUsuario) {
        return localizacionBO.actualizar(idLocalizacion, direccion, idUsuario);
    }

    @WebMethod(operationName = "eliminarLocalizacion")
    public Integer eliminarLocalizacion(
            @WebParam(name = "idLocalizacion") Integer idLocalizacion) {
        return localizacionBO.eliminarLocalizacion(idLocalizacion);
    }

    @WebMethod(operationName = "obtenerLocalizacionPorId")
    public LocalizacionDTO obtenerLocalizacionPorId(
            @WebParam(name = "idLocalizacion") Integer idLocalizacion) {
        return localizacionBO.obtenerLocalizacionPorId(idLocalizacion);
    }

    @WebMethod(operationName = "listarTodasLasLocalizaciones")
    public ArrayList<LocalizacionDTO> listarTodasLasLocalizaciones() {
        return localizacionBO.listarTodos();
    }
}
