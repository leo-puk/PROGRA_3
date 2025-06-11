package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;

public class CarritoDAOImp extends DAOImplBase implements CarritoDAO {

    private CarritoDTO carrito;

    public CarritoDAOImp() {
        super("TCS_CARRITOS");
        this.retornarLlavePrimaria = true;
        this.carrito = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_CARRITO", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("PRECIO", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("ID_CLIENTE", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1, this.carrito.getPrecio());
        this.statement.setInt(2, this.carrito.getCliente().getIdPersona());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1, this.carrito.getPrecio());
        this.statement.setInt(2, this.carrito.getCliente().getIdPersona());
        this.statement.setInt(3, this.carrito.getIdCarrito());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.carrito.getIdCarrito());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.carrito.getIdCarrito());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.carrito = new CarritoDTO();
        this.carrito.setIdCarrito(this.resultSet.getInt("ID_CARRITO"));
        this.carrito.setPrecio(this.resultSet.getDouble("PRECIO"));
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdPersona(this.resultSet.getInt("ID_CLIENTE"));
        this.carrito.setCliente(cliente);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.carrito = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.carrito != null) {
            lista.add(this.carrito);
        }
    }

    @Override
    public Integer insertar(CarritoDTO carrito) {
        if (carrito == null) {
            return -1;
        }
        this.carrito = carrito;
        return super.insertar();
    }

    @Override
    public CarritoDTO obtenerPorId(Integer idCarrito) {
        if (idCarrito == null || idCarrito <= 0) {
            return null;
        }
        this.carrito = new CarritoDTO();
        this.carrito.setIdCarrito(idCarrito);
        super.obtenerPorId();
        return this.carrito;
    }

    @Override
    public ArrayList<CarritoDTO> listarTodos() {
        return (ArrayList<CarritoDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(CarritoDTO carrito) {
        if (carrito == null || carrito.getIdCarrito() == null) {
            return -1;
        }
        this.carrito = carrito;
        return super.modificar();
    }

    @Override
    public Integer eliminar(CarritoDTO carrito) {
        if (carrito == null || carrito.getIdCarrito() == null) {
            return -1;
        }
        this.carrito = carrito;
        return super.eliminar();
    }
    
    
    @Override
    public Double montoAPagar (Integer idCarrito) {
        List<CarritoItemsDTO> listadoItemsCarrito = new ArrayList<>();
        CarritoItemsDAO carritoItemDAO = new CarritoItemsDAOImp();
        listadoItemsCarrito = carritoItemDAO.listarTodos();
        
        Double precioTotal=0.0;
        for(CarritoItemsDTO elem : listadoItemsCarrito){
            if (elem.getCarrito().getIdCarrito()==idCarrito){ //aqui reemplaza por el id que quieras
                
                precioTotal = precioTotal + (elem.getCantidad()*elem.getPrecioUnitario());
            }
        }
        CarritoDAO carritoDAO = new CarritoDAOImp();
        CarritoDTO carrito = carritoDAO.obtenerPorId(idCarrito);
        
        return precioTotal+carrito.getPrecio();
    }

}