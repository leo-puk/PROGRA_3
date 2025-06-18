package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.ClienteDTO;

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
        this.listaColumnas.add(new Columna("ID_CLIENTE", Tipo_Dato.ENTERO, true, false));
        this.listaColumnas.add(new Columna("DIRECCION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("TELEFONO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("INFO_TARJETA_CREDITO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("BALANCE_CUENTA", Tipo_Dato.REAL, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.cliente.getIdUsuario());
        this.statement.setString(2, this.cliente.getDireccion());
        this.statement.setString(3, this.cliente.getTelefono());
        this.statement.setString(4, this.cliente.getInfoTarjetaCredito());
        this.statement.setDouble(5, this.cliente.getBalanceCuenta());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.cliente.getDireccion());
        this.statement.setString(2, this.cliente.getTelefono());
        this.statement.setString(3, this.cliente.getInfoTarjetaCredito());
        this.statement.setDouble(4, this.cliente.getBalanceCuenta());
        this.statement.setInt(5, this.cliente.getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.cliente.getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.cliente.getIdUsuario());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.cliente = new ClienteDTO();
        this.cliente.setIdUsuario(this.resultSet.getInt("ID_CLIENTE"));
        this.cliente.setDireccion(this.resultSet.getString("DIRECCION"));
        this.cliente.setTelefono(this.resultSet.getString("TELEFONO"));
        this.cliente.setInfoTarjetaCredito(this.resultSet.getString("INFO_TARJETA_CREDITO"));
        
        // Manejo seguro para balance (puede ser NULL)
        double balance = this.resultSet.getDouble("BALANCE_CUENTA");
        if (!this.resultSet.wasNull()) {
            this.cliente.setBalanceCuenta(balance);
        } else {
            this.cliente.setBalanceCuenta(0.0);
        }
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
        if (cliente == null || cliente.getIdUsuario() == null) {
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
        this.cliente.setIdUsuario(idCliente);
        super.obtenerPorId();
        return this.cliente;
    }

    @Override
    public ArrayList<ClienteDTO> listarTodos() {
        return (ArrayList<ClienteDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(ClienteDTO cliente) {
        if (cliente == null || cliente.getIdUsuario() == null) {
            return -1;
        }
        this.cliente = cliente;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ClienteDTO cliente) {
        if (cliente == null || cliente.getIdUsuario() == null) {
            return -1;
        }
        this.cliente = cliente;
        return super.eliminar();
    }

}