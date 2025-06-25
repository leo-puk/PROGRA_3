package pe.edu.pucp.techshopper.daoImp;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.MovimientoStockDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.MovimientoStockDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.TipoMovimientoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class MovimientoStockDAOImp extends DAOImplBase implements MovimientoStockDAO {

    private MovimientoStockDTO movimiento;

    public MovimientoStockDAOImp() {
        super("TCS_MOVIMIENTOS_STOCK");
        this.retornarLlavePrimaria = true;
        this.movimiento = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_MOVIMIENTO", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("ID_PRODUCTO", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("TIPO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("CANTIDAD", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("FECHA", Tipo_Dato.FECHA_HORA, false, false));
        this.listaColumnas.add(new Columna("ID_USUARIO", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.movimiento.getProducto().getIdProducto());
        this.statement.setString(2, this.movimiento.getTipo().name());
        this.statement.setInt(3, this.movimiento.getCantidad());
        this.statement.setTimestamp(4, Timestamp.valueOf(this.movimiento.getFechaMovimiento()));
        
        if (this.movimiento.getUsuario() != null) {
            this.statement.setInt(5, this.movimiento.getUsuario().getIdUsuario());
        } else {
            this.statement.setNull(5, Types.INTEGER);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        // Normalmente los movimientos de stock no se modifican, pero implementado por consistencia
        this.statement.setInt(1, this.movimiento.getProducto().getIdProducto());
        this.statement.setString(2, this.movimiento.getTipo().name());
        this.statement.setInt(3, this.movimiento.getCantidad());
        this.statement.setTimestamp(4, Timestamp.valueOf(this.movimiento.getFechaMovimiento()));
        
        if (this.movimiento.getUsuario() != null) {
            this.statement.setInt(5, this.movimiento.getUsuario().getIdUsuario());
        } else {
            this.statement.setNull(5, Types.INTEGER);
        }
        
        this.statement.setInt(6, this.movimiento.getIdMovimiento());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.movimiento.getIdMovimiento());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.movimiento.getIdMovimiento());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.movimiento = new MovimientoStockDTO();
        this.movimiento.setIdMovimiento(this.resultSet.getInt("ID_MOVIMIENTO"));
        
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(this.resultSet.getInt("ID_PRODUCTO"));
        this.movimiento.setProducto(producto);
        
        // Manejo seguro del ENUM TipoMovimiento
        try {
            String tipoMovimientoStr = this.resultSet.getString("TIPO");
            TipoMovimientoDTO tipoMovimiento = TipoMovimientoDTO.valueOf(tipoMovimientoStr);
            this.movimiento.setTipo(tipoMovimiento);
        } catch (IllegalArgumentException e) {
            this.movimiento.setTipo(TipoMovimientoDTO.SALIDA);
        }
        
        this.movimiento.setCantidad(this.resultSet.getInt("CANTIDAD"));
        
        Timestamp timestamp = this.resultSet.getTimestamp("FECHA");
        if (timestamp != null) {
            this.movimiento.setFechaMovimiento(timestamp.toLocalDateTime());
        }
        
        int idUsuario = this.resultSet.getInt("ID_USUARIO");
        if (!this.resultSet.wasNull()) {
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setIdUsuario(idUsuario);
            this.movimiento.setUsuario(usuario);
        }
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.movimiento = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.movimiento != null) {
            lista.add(this.movimiento);
        }
    }

    @Override
    public Integer insertar(MovimientoStockDTO movimiento) {
        if (movimiento == null || movimiento.getProducto() == null) {
            return -1;
        }
        this.movimiento = movimiento;
        return super.insertar();
    }

    @Override
    public MovimientoStockDTO obtenerPorId(Integer idMovimiento) {
        if (idMovimiento == null || idMovimiento <= 0) {
            return null;
        }
        this.movimiento = new MovimientoStockDTO();
        this.movimiento.setIdMovimiento(idMovimiento);
        super.obtenerPorId();
        return this.movimiento;
    }

    @Override
    public ArrayList<MovimientoStockDTO> listarTodos() {
        return (ArrayList<MovimientoStockDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(MovimientoStockDTO movimiento) {
        if (movimiento == null || movimiento.getIdMovimiento() == null) {
            return -1;
        }
        this.movimiento = movimiento;
        return super.modificar();
    }

    @Override
    public Integer eliminar(MovimientoStockDTO movimiento) {
        if (movimiento == null || movimiento.getIdMovimiento() == null) {
            return -1;
        }
        this.movimiento = movimiento;
        return super.eliminar();
    }
}