package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.DetalleCompraDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.DetalleCompraDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public class DetalleCompraDAOImp extends DAOImplBase implements DetalleCompraDAO {

    private DetalleCompraDTO detalleCompra;

    public DetalleCompraDAOImp() {
        super("TCS_DETALLES_COMPRA");
        this.retornarLlavePrimaria = true;
        this.detalleCompra = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_DETALLE_COMPRA", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("ID_COMPRA", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("ID_PRODUCTO", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("CANTIDAD", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("PRECIO_UNITARIO", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("IGV", Tipo_Dato.REAL, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.detalleCompra.getCompra().getIdCompra());
        this.statement.setInt(2, this.detalleCompra.getProducto().getIdProducto());
        this.statement.setInt(3, this.detalleCompra.getCantidad());
        this.statement.setDouble(4, this.detalleCompra.getPrecioUnitario());
        this.statement.setDouble(5, this.detalleCompra.getIgv());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1, this.detalleCompra.getCompra().getIdCompra());
        this.statement.setInt(2, this.detalleCompra.getProducto().getIdProducto());
        this.statement.setInt(3, this.detalleCompra.getCantidad());
        this.statement.setDouble(4, this.detalleCompra.getPrecioUnitario());
        this.statement.setDouble(5, this.detalleCompra.getIgv());
        this.statement.setInt(6, this.detalleCompra.getIdDetalleCompra());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.detalleCompra.getIdDetalleCompra());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.detalleCompra.getIdDetalleCompra());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.detalleCompra = new DetalleCompraDTO();
        this.detalleCompra.setIdDetalleCompra(this.resultSet.getInt("ID_DETALLE_COMPRA"));
        
        CompraDTO compra = new CompraDTO();
        compra.setIdCompra(this.resultSet.getInt("ID_COMPRA"));
        this.detalleCompra.setCompra(compra);
        
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(this.resultSet.getInt("ID_PRODUCTO"));
        this.detalleCompra.setProducto(producto);
        
        this.detalleCompra.setCantidad(this.resultSet.getInt("CANTIDAD"));
        this.detalleCompra.setPrecioUnitario(this.resultSet.getDouble("PRECIO_UNITARIO"));
        this.detalleCompra.setIgv(this.resultSet.getDouble("IGV"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.detalleCompra = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.detalleCompra != null) {
            lista.add(this.detalleCompra);
        }
    }

    @Override
    public Integer insertar(DetalleCompraDTO detalleCompra) {
        if (detalleCompra == null) {
            return -1;
        }
        this.detalleCompra = detalleCompra;
        return super.insertar();
    }

    @Override
    public DetalleCompraDTO obtenerPorId(Integer idDetalleCompra) {
        if (idDetalleCompra == null || idDetalleCompra <= 0) {
            return null;
        }
        this.detalleCompra = new DetalleCompraDTO();
        this.detalleCompra.setIdDetalleCompra(idDetalleCompra);
        super.obtenerPorId();
        return this.detalleCompra;
    }

    @Override
    public ArrayList<DetalleCompraDTO> listarTodos() {
        return (ArrayList<DetalleCompraDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(DetalleCompraDTO detalleCompra) {
        if (detalleCompra == null || detalleCompra.getIdDetalleCompra() == null) {
            return -1;
        }
        this.detalleCompra = detalleCompra;
        return super.modificar();
    }

    @Override
    public Integer eliminar(DetalleCompraDTO detalleCompra) {
        if (detalleCompra == null || detalleCompra.getIdDetalleCompra() == null) {
            return -1;
        }
        this.detalleCompra = detalleCompra;
        return super.eliminar();
    }
    
}