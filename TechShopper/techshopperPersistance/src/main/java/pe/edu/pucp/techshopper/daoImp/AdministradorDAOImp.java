package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

public class AdministradorDAOImp extends DAOImplBase implements AdministradorDAO {

    private AdministradorDTO administrador;

    public AdministradorDAOImp() {
        super("TCS_ADMINISTRADORES");
        this.retornarLlavePrimaria = true;
        this.administrador = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear(); // Limpiar lista para evitar duplicados
        this.listaColumnas.add(new Columna("ID_ADMINISTRADOR", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("CONTRASENA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("ESTADO_CONEXION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("FECHA_REGISTRO", Tipo_Dato.FECHA_HORA, false, false));
        this.listaColumnas.add(new Columna("NOMBRE", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("EMAIL", Tipo_Dato.CADENA, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.administrador.getContraseña());
        this.statement.setString(2, this.administrador.getEstadoConexion().name()); 
        this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.administrador.getFechaRegistro()));
        this.statement.setString(4, this.administrador.getNombre());
        this.statement.setString(5, this.administrador.getEmail());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.administrador.getContraseña());
        this.statement.setString(2, this.administrador.getEstadoConexion().name());
        this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.administrador.getFechaRegistro()));
        this.statement.setString(4, this.administrador.getNombre());
        this.statement.setString(5, this.administrador.getEmail());
        this.statement.setInt(6, this.administrador.getIdPersona()); 
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.administrador.getIdPersona());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.administrador.getIdPersona());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.administrador = new AdministradorDTO();
        this.administrador.setIdPersona(this.resultSet.getInt("ID_ADMINISTRADOR"));
        this.administrador.setContraseña(this.resultSet.getString("CONTRASENA"));
        
        // Manejo seguro del ENUM
        try {
            String estadoConexionStr = this.resultSet.getString("ESTADO_CONEXION");
            EstadoConexionDTO estadoDeConexion = EstadoConexionDTO.valueOf(estadoConexionStr);
            this.administrador.setEstadoConexion(estadoDeConexion);
        } catch (IllegalArgumentException e) {
            // Manejar error de conversión de ENUM
            this.administrador.setEstadoConexion(EstadoConexionDTO.DESCONECTADO); // Valor por defecto
        }
        
        // Manejo seguro de fechas
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_REGISTRO");
        if (timestamp != null) {
            this.administrador.setFechaRegistro(timestamp.toLocalDateTime());
        }
        
        this.administrador.setNombre(this.resultSet.getString("NOMBRE"));
        this.administrador.setEmail(this.resultSet.getString("EMAIL"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.administrador = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.administrador != null) {
            lista.add(this.administrador);
        }
    }

    @Override
    public Integer insertar(AdministradorDTO administrador) {
        if (administrador == null) {
            return -1; 
        }
        this.administrador = administrador;
        return super.insertar();
    }

    @Override
    public AdministradorDTO obtenerPorId(Integer idAdministrador) {
        if (idAdministrador == null || idAdministrador <= 0) {
            return null; 
        }
        this.administrador = new AdministradorDTO();
        this.administrador.setIdPersona(idAdministrador);
        super.obtenerPorId();
        return this.administrador;
    }

    @Override
    public ArrayList<AdministradorDTO> listarTodos() {
        return (ArrayList<AdministradorDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(AdministradorDTO administrador) {
        if (administrador == null || administrador.getIdPersona() == null) {
            return -1; 
        }
        this.administrador = administrador;
        return super.modificar();
    }

    @Override
    public Integer eliminar(AdministradorDTO administrador) {
        if (administrador == null || administrador.getIdPersona() == null) {
            return -1; 
        }
        this.administrador = administrador;
        return super.eliminar();
    }
}