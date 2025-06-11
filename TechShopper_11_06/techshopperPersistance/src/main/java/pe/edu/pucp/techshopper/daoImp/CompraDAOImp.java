package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CompraDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.EstadoCompraDTO;

public class CompraDAOImp extends DAOImplBase implements CompraDAO {

    private CompraDTO compra;

    public CompraDAOImp() {
        super("TCS_COMPRAS");
        this.retornarLlavePrimaria = true;
        this.compra = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_COMPRA", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("PRECIO_TOTAL", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("FECHA_COMPRA", Tipo_Dato.FECHA_HORA, false, false));
        this.listaColumnas.add(new Columna("ESTADO_COMPRA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("ID_ENVIO", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1, this.compra.getPrecioTotal());
        this.statement.setTimestamp(2, java.sql.Timestamp.valueOf(this.compra.getFechaCompra()));
        this.statement.setString(3, this.compra.getEstadoCompra().name());
        this.statement.setInt(4, this.compra.getEntrega().getIdEnvio());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1, this.compra.getPrecioTotal());
        this.statement.setTimestamp(2, java.sql.Timestamp.valueOf(this.compra.getFechaCompra()));
        this.statement.setString(3, this.compra.getEstadoCompra().name());
        this.statement.setInt(4, this.compra.getEntrega().getIdEnvio());
        this.statement.setInt(5, this.compra.getIdCompra());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.compra.getIdCompra());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.compra.getIdCompra());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.compra = new CompraDTO();
        this.compra.setIdCompra(this.resultSet.getInt("ID_COMPRA"));
        this.compra.setPrecioTotal(this.resultSet.getDouble("PRECIO_TOTAL"));
        
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_COMPRA");
        if (timestamp != null) {
            this.compra.setFechaCompra(timestamp.toLocalDateTime());
        }
        
        try {
            String estadoCompraStr = this.resultSet.getString("ESTADO_COMPRA");
            EstadoCompraDTO estadoCompra = EstadoCompraDTO.valueOf(estadoCompraStr);
            this.compra.setEstadoCompra(estadoCompra);
        } catch (IllegalArgumentException e) {
            this.compra.setEstadoCompra(EstadoCompraDTO.SIN_PAGAR); // Valor por defecto
        }
        
        EnvioDTO envio = new EnvioDTO();
        envio.setIdEnvio(this.resultSet.getInt("ID_ENVIO"));
        this.compra.setEntrega(envio);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.compra = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.compra != null) {
            lista.add(this.compra);
        }
    }

    @Override
    public Integer insertar(CompraDTO compra) {
        if (compra == null) {
            return -1;
        }
        this.compra = compra;
        return super.insertar();
    }

    @Override
    public CompraDTO obtenerPorId(Integer idCompra) {
        if (idCompra == null || idCompra <= 0) {
            return null;
        }
        this.compra = new CompraDTO();
        this.compra.setIdCompra(idCompra);
        super.obtenerPorId();
        return this.compra;
    }

    @Override
    public ArrayList<CompraDTO> listarTodos() {
        return (ArrayList<CompraDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(CompraDTO compra) {
        if (compra == null || compra.getIdCompra() == null) {
            return -1;
        }
        this.compra = compra;
        return super.modificar();
    }

    @Override
    public Integer eliminar(CompraDTO compra) {
        if (compra == null || compra.getIdCompra() == null) {
            return -1;
        }
        this.compra = compra;
        return super.eliminar();
    }
    
}