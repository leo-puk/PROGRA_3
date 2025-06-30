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
    
    @Override
    public Integer salvar(CarritoItemsDTO itemsDTO){
        Integer Itemid = itemsDTO.getIdCarritoItems();
        CarritoItemsDTO carritoBD = this.obtenerPorIdProdIdCarrito(itemsDTO.getProducto().getIdProducto(),itemsDTO.getCarrito().getIdCarrito());
        if(carritoBD ==null){
            //no existe este item por lo que, crearemos uno nuevo
            Integer res= this.insertar(itemsDTO);
            return res;
        }else{
            //si existe el item, asi que, solo se modifica
            carritoBD.setCantidad(itemsDTO.getCantidad()+carritoBD.getCantidad());
            Integer resmodif = this.modificar(carritoBD);
            return resmodif;
        }
        //return 0;
    }
    
    @Override 
    public CarritoItemsDTO obtenerPorIdProdIdCarrito(Integer productoId,Integer carritoId){
        this.carritoItem = new CarritoItemsDTO();
//        this.carrito.setUsuario(idUsuario);
//        super.obtenerPorId();
        
        try {
            this.abrirConexion();
            String sql = "select * from TCS_ITEMS_CARRITO where ID_PRODUCTO = ? and id_carrito = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, productoId);
            this.statement.setInt(2, carritoId);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return this.carritoItem;
    }
    
    @Override
    public ArrayList<CarritoItemsDTO> listarPorCarrito(Integer id_carrito){
        List lista = new ArrayList<>();
        try {
            this.abrirConexion();
            String sql = "select * from TCS_ITEMS_CARRITO where id_carrito = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, id_carrito);
            this.ejecutarConsultaEnBD();
            while (this.resultSet.next()) {
                agregarObjetoALaLista(lista);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return (ArrayList<CarritoItemsDTO>)lista;
    }
    
    @Override
    public ArrayList<CarritoItemsDTO> ListarPorId(ArrayList<Integer> idItemSeleccionados){
        ArrayList<CarritoItemsDTO> lista = new ArrayList<CarritoItemsDTO>(); 
        for(Integer seleccionados : idItemSeleccionados){
            CarritoItemsDTO items = this.obtenerPorId(seleccionados);
            if(items != null){
                lista.add(items);
            }
        }
        return lista;
    }
    
    @Override
    public Integer eliminarItems(ArrayList<Integer> idItemSeleccionados){
        for(Integer iditem : idItemSeleccionados){
            this.carritoItem = obtenerPorId(iditem);
            if(this.carritoItem == null) return -1;
            this.eliminar(carritoItem);
            
        }
        return 1;
    }
    
    @Override
    public Double calcularMontoTotalCarrito(Integer idCarrito) {
        Double montoTotal = 0.0;

        try {
            this.abrirConexion();
            String sql = "SELECT SUM(CANTIDAD * PRECIO_UNITARIO) AS TOTAL " +
                         "FROM TCS_ITEMS_CARRITO " +
                         "WHERE ID_CARRITO = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idCarrito);
            this.ejecutarConsultaEnBD();

            if (this.resultSet.next()) {
                montoTotal = this.resultSet.getDouble("TOTAL");
                // Si no hay items, resultSet.getDouble() devuelve 0.0
            }
        } catch (SQLException ex) {
            System.err.println("Error al calcular monto total del carrito - " + ex);
            montoTotal = -1.0; // Indicador de error
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }

        return montoTotal;
    }
}