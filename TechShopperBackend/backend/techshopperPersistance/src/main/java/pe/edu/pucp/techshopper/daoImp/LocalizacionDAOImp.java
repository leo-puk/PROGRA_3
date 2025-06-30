package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.LocalizacionDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class LocalizacionDAOImp extends DAOImplBase implements LocalizacionDAO {

    private LocalizacionDTO localizacion;

    public LocalizacionDAOImp() {
        super("TCS_LOCALIZACIONES");
        this.retornarLlavePrimaria = true;
        this.localizacion = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_LOCALIZACION", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("LATITUD", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("LONGITUD", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("DIRECCION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("ID_USUARIO", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1, this.localizacion.getLatitud());
        this.statement.setDouble(2, this.localizacion.getLongitud());
        this.statement.setString(3, this.localizacion.getDireccion());
        this.statement.setInt(4, this.localizacion.getUsuario().getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1, this.localizacion.getLatitud());
        this.statement.setDouble(2, this.localizacion.getLongitud());
        this.statement.setString(3, this.localizacion.getDireccion());
        this.statement.setInt(4, this.localizacion.getUsuario().getIdUsuario());
        this.statement.setInt(5, this.localizacion.getIdLocalizacion());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.localizacion.getIdLocalizacion());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.localizacion.getIdLocalizacion());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.localizacion = new LocalizacionDTO();
        this.localizacion.setIdLocalizacion(this.resultSet.getInt("ID_LOCALIZACION"));
        this.localizacion.setLatitud(this.resultSet.getDouble("LATITUD"));
        this.localizacion.setLongitud(this.resultSet.getDouble("LONGITUD"));
        this.localizacion.setDireccion(this.resultSet.getString("DIRECCION"));
        
        // Creamos un usuario básico solo con el ID
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(this.resultSet.getInt("ID_USUARIO"));
        this.localizacion.setUsuario(usuario);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.localizacion = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.localizacion != null) {
            lista.add(this.localizacion);
        }
    }

    @Override
    public Integer insertar(LocalizacionDTO localizacion) {
        if (localizacion == null || localizacion.getUsuario() == null) {
            return -1;
        }
        this.localizacion = localizacion;
        return super.insertar();
    }

    @Override
    public LocalizacionDTO obtenerPorId(Integer idLocalizacion) {
        if (idLocalizacion == null || idLocalizacion <= 0) {
            return null;
        }
        this.localizacion = new LocalizacionDTO();
        this.localizacion.setIdLocalizacion(idLocalizacion);
        super.obtenerPorId();
        return this.localizacion;
    }

    @Override
    public ArrayList<LocalizacionDTO> listarTodos() {
        return (ArrayList<LocalizacionDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(LocalizacionDTO localizacion) {
        if (localizacion == null || localizacion.getIdLocalizacion() == null) {
            return -1;
        }
        this.localizacion = localizacion;
        return super.modificar();
    }

    @Override
    public Integer eliminar(LocalizacionDTO localizacion) {
        if (localizacion == null || localizacion.getIdLocalizacion() == null) {
            return -1;
        }
        this.localizacion = localizacion;
        return super.eliminar();
    }
    
    @Override
    public LocalizacionDTO obtenerPorIdUsuario(Integer idUsuario){
        if(idUsuario == null ||  idUsuario <=0){
            return null;
        }
        this.localizacion = new LocalizacionDTO();
        
        try {
            this.abrirConexion();
            String sql = "SELECT * FROM TCS_LOCALIZACIONES WHERE ID_USUARIO = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idUsuario);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorIdUsuario - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return this.localizacion;
    }
    
}