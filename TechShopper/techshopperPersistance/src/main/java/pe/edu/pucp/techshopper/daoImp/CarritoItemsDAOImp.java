package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public class CarritoItemsDAOImp extends DAOImplBase implements CarritoItemsDAO {

    private CarritoItemsDTO carritoItem;

    public CarritoItemsDAOImp() {
        super("TCS_ITEMS_CARRITO");
        this.retornarLlavePrimaria = true;
        this.carritoItem = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_ITEM_CARRITO", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("ID_CARRITO", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("ID_PRODUCTO", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("CANTIDAD", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("PRECIO_UNITARIO", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("FECHA_REGISTRO", Tipo_Dato.FECHA_HORA, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.carritoItem.getCarrito().getIdCarrito());
        this.statement.setInt(2, this.carritoItem.getProducto().getIdProducto());
        this.statement.setInt(3, this.carritoItem.getCantidad());
        this.statement.setDouble(4, this.carritoItem.getPrecioUnitario());
        this.statement.setTimestamp(5, java.sql.Timestamp.valueOf(this.carritoItem.getFechaRegistro()));
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1, this.carritoItem.getCarrito().getIdCarrito());
        this.statement.setInt(2, this.carritoItem.getProducto().getIdProducto());
        this.statement.setInt(3, this.carritoItem.getCantidad());
        this.statement.setDouble(4, this.carritoItem.getPrecioUnitario());
        this.statement.setTimestamp(5, java.sql.Timestamp.valueOf(this.carritoItem.getFechaRegistro()));
        this.statement.setInt(6, this.carritoItem.getIdCarritoItems());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.carritoItem.getIdCarritoItems());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.carritoItem.getIdCarritoItems());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.carritoItem = new CarritoItemsDTO();
        this.carritoItem.setIdCarritoItems(this.resultSet.getInt("ID_ITEM_CARRITO"));
        
        CarritoDTO carrito = new CarritoDTO();
        carrito.setIdCarrito(this.resultSet.getInt("ID_CARRITO"));
        this.carritoItem.setCarrito(carrito);
        
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(this.resultSet.getInt("ID_PRODUCTO"));
        this.carritoItem.setProducto(producto);
        
        this.carritoItem.setCantidad(this.resultSet.getInt("CANTIDAD"));
        this.carritoItem.setPrecioUnitario(this.resultSet.getDouble("PRECIO_UNITARIO"));
        
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_REGISTRO");
        if (timestamp != null) {
            this.carritoItem.setFechaRegistro(timestamp.toLocalDateTime());
        }
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.carritoItem = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.carritoItem != null) {
            lista.add(this.carritoItem);
        }
    }

    @Override
    public Integer insertar(CarritoItemsDTO carritoItem) {
        if (carritoItem == null) {
            return -1;
        }
        this.carritoItem = carritoItem;
        return super.insertar();
    }

    @Override
    public CarritoItemsDTO obtenerPorId(Integer idItemCarrito) {
        if (idItemCarrito == null || idItemCarrito <= 0) {
            return null;
        }
        this.carritoItem = new CarritoItemsDTO();
        this.carritoItem.setIdCarritoItems(idItemCarrito);
        super.obtenerPorId();
        return this.carritoItem;
    }

    @Override
    public ArrayList<CarritoItemsDTO> listarTodos() {
        return (ArrayList<CarritoItemsDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(CarritoItemsDTO carritoItem) {
        if (carritoItem == null || carritoItem.getIdCarritoItems()== null) {
            return -1;
        }
        this.carritoItem = carritoItem;
        return super.modificar();
    }

    @Override
    public Integer eliminar(CarritoItemsDTO carritoItem) {
        if (carritoItem == null || carritoItem.getIdCarritoItems() == null) {
            return -1;
        }
        this.carritoItem = carritoItem;
        return super.eliminar();
    }
}