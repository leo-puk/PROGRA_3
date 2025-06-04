package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

public class ClienteDAOImp extends DAOImplBase implements ClienteDAO {

    private ClienteDTO cliente;

    public ClienteDAOImp() {
        super("TCS_CLIENTES");
        this.retornarLlavePrimaria = true;
        this.cliente = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_CLIENTE", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("CONTRASENA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("ESTADO_CONEXION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("FECHA_REGISTRO", Tipo_Dato.FECHA_HORA, false, false));
        this.listaColumnas.add(new Columna("NOMBRE", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("DIRECCION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("TELEFONO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("EMAIL", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("INFO_TARJETA_CREDITO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("BALANCE_CUENTA", Tipo_Dato.REAL, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.cliente.getContraseña());
        this.statement.setString(2, this.cliente.getEstadoConexion().name());
        this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.cliente.getFechaRegistro()));
        this.statement.setString(4, this.cliente.getNombre());
        this.statement.setString(5, this.cliente.getDireccion());
        this.statement.setString(6, this.cliente.getTelefono());
        this.statement.setString(7, this.cliente.getEmail());
        this.statement.setString(8, this.cliente.getInfoTarjetaCredito());
        this.statement.setDouble(9, this.cliente.getBalanceCuenta());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.cliente.getContraseña());
        this.statement.setString(2, this.cliente.getEstadoConexion().name());
        this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.cliente.getFechaRegistro()));
        this.statement.setString(4, this.cliente.getNombre());
        this.statement.setString(5, this.cliente.getDireccion());
        this.statement.setString(6, this.cliente.getTelefono());
        this.statement.setString(7, this.cliente.getEmail());
        this.statement.setString(8, this.cliente.getInfoTarjetaCredito());
        this.statement.setDouble(9, this.cliente.getBalanceCuenta());
        this.statement.setInt(10, this.cliente.getIdPersona());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.cliente.getIdPersona());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.cliente.getIdPersona());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.cliente = new ClienteDTO();
        this.cliente.setIdPersona(this.resultSet.getInt("ID_CLIENTE"));
        this.cliente.setContraseña(this.resultSet.getString("CONTRASENA"));
        
        try {
            String estadoConexionStr = this.resultSet.getString("ESTADO_CONEXION");
            EstadoConexionDTO estadoDeConexion = EstadoConexionDTO.valueOf(estadoConexionStr);
            this.cliente.setEstadoConexion(estadoDeConexion);
        } catch (IllegalArgumentException e) {
            this.cliente.setEstadoConexion(EstadoConexionDTO.DESCONECTADO);
        }
        
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_REGISTRO");
        if (timestamp != null) {
            this.cliente.setFechaRegistro(timestamp.toLocalDateTime());
        }
        
        this.cliente.setNombre(this.resultSet.getString("NOMBRE"));
        this.cliente.setDireccion(this.resultSet.getString("DIRECCION"));
        this.cliente.setTelefono(this.resultSet.getString("TELEFONO"));
        this.cliente.setEmail(this.resultSet.getString("EMAIL"));
        this.cliente.setInfoTarjetaCredito(this.resultSet.getString("INFO_TARJETA_CREDITO"));
        this.cliente.setBalanceCuenta(this.resultSet.getDouble("BALANCE_CUENTA"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.cliente = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.cliente != null) {
            lista.add(this.cliente);
        }
    }

    @Override
    public Integer insertar(ClienteDTO cliente) {
        if (cliente == null) {
            return -1;
        }
        this.cliente = cliente;
        return super.insertar();
    }

    @Override
    public ClienteDTO obtenerPorId(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            return null;
        }
        this.cliente = new ClienteDTO();
        this.cliente.setIdPersona(idCliente);
        super.obtenerPorId();
        return this.cliente;
    }

    @Override
    public ArrayList<ClienteDTO> listarTodos() {
        return (ArrayList<ClienteDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(ClienteDTO cliente) {
        if (cliente == null || cliente.getIdPersona() == null) {
            return -1;
        }
        this.cliente = cliente;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ClienteDTO cliente) {
        if (cliente == null || cliente.getIdPersona() == null) {
            return -1;
        }
        this.cliente = cliente;
        return super.eliminar();
    }
    
    /***Otros métodos***/
    
    
}