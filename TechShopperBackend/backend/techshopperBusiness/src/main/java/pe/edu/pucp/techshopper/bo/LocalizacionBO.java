package pe.edu.pucp.techshopper.bo;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.LocalizacionDAO;
import pe.edu.pucp.techshopper.daoImp.LocalizacionDAOImp;
import pe.edu.pucp.techshopper.daoImp.util.GeoUtils;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

/**
 *
 * @author CRISTHIAN
 */
public class LocalizacionBO {

    private final LocalizacionDAO localizacionDAO;

    public LocalizacionBO() {
        this.localizacionDAO = new LocalizacionDAOImp();
    }

    public Integer insertar(String direccion, Double latitud, Double longitud, Integer idUsuario) {
        // Validaciones básicas
        
        if (direccion == null || idUsuario == null || idUsuario <= 0 || longitud <=0 || latitud <=0) {
            return -2;
        }
        

        LocalizacionDTO localizacion = new LocalizacionDTO();
        localizacion.setDireccion(direccion);
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(idUsuario);
        localizacion.setLatitud(latitud);
        localizacion.setLongitud(longitud);
        localizacion.setUsuario(usuario);

        return localizacionDAO.insertar(localizacion);
    }

    public Integer actualizar(Integer idLocalizacion,String direccion,Integer idUsuario) {
        // Validaciones básicas
        boolean esDeLima = GeoUtils.esDireccionEnLima(direccion);
        if(esDeLima == false) return -1;
        if (direccion == null || idUsuario == null || idUsuario <= 0) {
            return -1;
        }

        LocalizacionDTO localizacion = new LocalizacionDTO();
        localizacion.setDireccion(direccion);
        localizacion.setIdLocalizacion(idLocalizacion);
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(idUsuario);
        double lis[] = GeoUtils.geocodificarDireccion(direccion);
        localizacion.setLatitud(lis[0]);
        localizacion.setLongitud(lis[1]);
        localizacion.setUsuario(usuario);

        return localizacionDAO.modificar(localizacion);
    }

    public Integer eliminarLocalizacion(Integer idLocalizacion) {
        if (idLocalizacion == null || idLocalizacion <= 0) {
            return -1;
        }

        LocalizacionDTO Localizacion = new LocalizacionDTO();
        Localizacion.setIdLocalizacion(idLocalizacion);

        return localizacionDAO.eliminar(Localizacion);
    }

    public LocalizacionDTO obtenerLocalizacionPorId(Integer idLocalizacion) {
        if (idLocalizacion == null || idLocalizacion <= 0) {
            return null;
        }
        return localizacionDAO.obtenerPorId(idLocalizacion);
    }

    public ArrayList<LocalizacionDTO> listarTodos() {
        return localizacionDAO.listarTodos();
    }
}
