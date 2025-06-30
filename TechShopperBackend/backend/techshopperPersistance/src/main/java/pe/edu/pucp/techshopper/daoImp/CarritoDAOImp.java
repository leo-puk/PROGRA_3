package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

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
        this.listaColumnas.add(new Columna("ID_USUARIO", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1, this.carrito.getPrecio());
        this.statement.setInt(2, this.carrito.getUsuario().getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1, this.carrito.getPrecio());
        this.statement.setInt(2, this.carrito.getUsuario().getIdUsuario());
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
        
        // Creamos un usuario básico solo con el ID
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(this.resultSet.getInt("ID_USUARIO"));
        this.carrito.setUsuario(usuario);
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
        if (carrito == null || carrito.getUsuario() == null) {
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
    public Double montoAPagar(Integer idCarrito) {
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
    
    @Override
    public Integer insertaPorUsuario(Integer idUsuario, ProductoDTO producto,Integer cantidad){
        this.carrito = new CarritoDTO();
        this.carrito = this.ObtenerCarritoPorIdUsuaio(idUsuario);
        //carro nuevo
        CarritoDTO carritoNuevo = new CarritoDTO();
            carritoNuevo.setPrecio(producto.getPrecio()*cantidad);
            UsuarioDTO cliente = new UsuarioDTO();
            cliente.setIdUsuario(idUsuario);
            carritoNuevo.setUsuario(cliente);
        //para item 
        CarritoItemsDAO items = new CarritoItemsDAOImp();
        CarritoItemsDTO itemsDTO = new CarritoItemsDTO();
        itemsDTO.setCantidad(cantidad);
//        itemsDTO.setCarrito(carritoNuevo);
        itemsDTO.setFechaRegistro(LocalDateTime.now());
        itemsDTO.setPrecioUnitario(producto.getPrecio());
        itemsDTO.setProducto(producto);
                
        if(this.carrito == null){
            //entonces no existe un carrito para este usuario por lo tanto se crea un nuevo carrito
            
            int resInsertar = this.insertar(carritoNuevo);
            if(resInsertar > 0){
                //como ya tenemos una carrito nuevo entonces tambien debemos crear un carrito items nuevo
                
                carritoNuevo.setIdCarrito(resInsertar);
                itemsDTO.setCarrito(carritoNuevo);
                int resInsertaItem = items.insertar(itemsDTO);
                if(resInsertaItem <=0) return 0;
                return resInsertaItem;
            }else{
                return 0;
            }
        }else{
            //en este caso:
            //el usuario ya tiene un carrito por lo que solo se actualizará el precio total del carrito
            carritoNuevo.setIdCarrito(this.carrito.getIdCarrito());
            carritoNuevo.setPrecio(this.carrito.getPrecio() + carritoNuevo.getPrecio());
            
            int resModifCarrito = this.modificar(carritoNuevo);
            //en el caso del item
            //lo evaluaremos en la misma clase de items: osea si ya modifica un item o si se agrega uno nuevo
//            carritoNuevo.setIdCarrito(this.carrito.getIdCarrito());
                itemsDTO.setCarrito(carritoNuevo);
            int resObsItem = items.salvar(itemsDTO);
            return resObsItem;
        }
    }
    
    @Override 
    public CarritoDTO ObtenerCarritoPorIdUsuaio(Integer idUsuario){
        this.carrito = new CarritoDTO();
//        this.carrito.setUsuario(idUsuario);
//        super.obtenerPorId();
        
        try {
            this.abrirConexion();
            String sql = "select * from TCS_CARRITOS where ID_USUARIO = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idUsuario);
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
        return this.carrito;
    }
    
    @Override
    public ArrayList<CarritoItemsDTO> devolverItemsDeCarrito(Integer id_usuario){
        ArrayList<CarritoItemsDTO> lista = new ArrayList<CarritoItemsDTO>();
        CarritoDTO carrito = this.ObtenerCarritoPorIdUsuaio(id_usuario);
        if(carrito == null)
            return lista;
        
        CarritoItemsDAO itemsCarrito = new CarritoItemsDAOImp();
        return (ArrayList<CarritoItemsDTO>)itemsCarrito.listarPorCarrito(carrito.getIdCarrito());
    }
}