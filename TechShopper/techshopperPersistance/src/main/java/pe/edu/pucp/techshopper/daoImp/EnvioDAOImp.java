package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.EnvioDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;

public class EnvioDAOImp extends DAOImplBase implements EnvioDAO {

    private EnvioDTO envio;

    public EnvioDAOImp() {
        super("TCS_ENVIOS");
        this.retornarLlavePrimaria = true;
        this.envio = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_ENVIO", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("FECHA_ENTREGA", Tipo_Dato.FECHA_HORA, false, false));
        this.listaColumnas.add(new Columna("EMPRESA_COURIER", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("PRECIO", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("ID_LOCALIZACION", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setTimestamp(1, java.sql.Timestamp.valueOf(this.envio.getFechaEntrega()));
        this.statement.setString(2, this.envio.getEmpresaCourier());
        this.statement.setDouble(3, this.envio.getPrecio());
        this.statement.setInt(4, this.envio.getDestino().getIdLocalizacion());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setTimestamp(1, java.sql.Timestamp.valueOf(this.envio.getFechaEntrega()));
        this.statement.setString(2, this.envio.getEmpresaCourier());
        this.statement.setDouble(3, this.envio.getPrecio());
        this.statement.setInt(4, this.envio.getDestino().getIdLocalizacion());
        this.statement.setInt(5, this.envio.getIdEnvio());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.envio.getIdEnvio());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.envio.getIdEnvio());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.envio = new EnvioDTO();
        this.envio.setIdEnvio(this.resultSet.getInt("ID_ENVIO"));
        
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_ENTREGA");
        if (timestamp != null) {
            this.envio.setFechaEntrega(timestamp.toLocalDateTime());
        }
        
        this.envio.setEmpresaCourier(this.resultSet.getString("EMPRESA_COURIER"));
        this.envio.setPrecio(this.resultSet.getDouble("PRECIO"));
        
        LocalizacionDTO localizacion = new LocalizacionDTO();
        localizacion.setIdLocalizacion(this.resultSet.getInt("ID_LOCALIZACION"));
        this.envio.setDestino(localizacion);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.envio = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.envio != null) {
            lista.add(this.envio);
        }
    }

    @Override
    public Integer insertar(EnvioDTO envio) {
        if (envio == null) {
            return -1;
        }
        this.envio = envio;
        return super.insertar();
    }

    @Override
    public EnvioDTO obtenerPorId(Integer idEnvio) {
        if (idEnvio == null || idEnvio <= 0) {
            return null;
        }
        this.envio = new EnvioDTO();
        this.envio.setIdEnvio(idEnvio);
        super.obtenerPorId();
        return this.envio;
    }

    @Override
    public ArrayList<EnvioDTO> listarTodos() {
        return (ArrayList<EnvioDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(EnvioDTO envio) {
        if (envio == null || envio.getIdEnvio() == null) {
            return -1;
        }
        this.envio = envio;
        return super.modificar();
    }

    @Override
    public Integer eliminar(EnvioDTO envio) {
        if (envio == null || envio.getIdEnvio() == null) {
            return -1;
        }
        this.envio = envio;
        return super.eliminar();
    }
    
}