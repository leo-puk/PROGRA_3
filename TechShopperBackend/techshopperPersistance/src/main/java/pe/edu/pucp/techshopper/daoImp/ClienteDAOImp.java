package pe.edu.pucp.techshopper.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

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
    
    @Override
//    public Integer insertarCarrito(Integer id_usuario,ProductoDTO producto,Integer cantidad){
    public Integer insertarCarrito(Integer id_usuario,Integer id_producto,Integer cantidad){
//        if (producto.getIdProducto() < 0 && cantidad < 0) {
        if (id_producto < 0 && cantidad < 0) {
            return -1;
        }
        CarritoDAO carrito = new CarritoDAOImp();
        ProductoDAO productodao = new ProductoDAOImp();
        ProductoDTO producto = new ProductoDTO();
        producto = productodao.obtenerPorId(id_producto);
        if(producto == null){
            System.out.println("No se encontró producto");
            return -1;
        }
//        producto = productodao.obtenerPorId(producto.getIdProducto());
        return carrito.insertaPorUsuario(id_usuario,producto,cantidad);

    }
    
    @Override
    public ArrayList<CarritoItemsDTO> MostrarCarritoDeCliente(Integer id_usuario){
        CarritoDAO carrito = new CarritoDAOImp();
        return carrito.devolverItemsDeCarrito(id_usuario);
//        return carrItem.listarPorUsuario(id_usuario); 
    }
    
    
//    @Override
//    public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca) {
//        String sql = getSelectedAlQueryByFilters();
//
//        if ((nombre == null || nombre.isBlank()) &&
//            (marca == null || marca.isBlank()) &&
//            (categoria == null || categoria.isBlank())) {
//            return new ArrayList<>(); // Nada que buscar
//        }
//
//        try (Connection conn = DBManager.getInstance().getConnection();
//             PreparedStatement cmd = conn.prepareStatement(sql)) {
//
//             //Normaliza filtros
//            String nombreFiltro = (nombre == null || nombre.isBlank()) ? null : "%" + nombre + "%";
//            String marcaFiltro = (marca == null || marca.isBlank()) ? null : "%" + marca + "%";
//            String categoriaFiltro = (categoria == null || categoria.isBlank()) ? null : categoria;
//
//             //Set de parámetros (cada valor se repite para IS NULL y la condición)
//            cmd.setString(1, (nombreFiltro == null) ? null : nombreFiltro); // IS NULL
//            cmd.setString(2, nombreFiltro);                                 // LIKE
//
//            cmd.setString(3, (marcaFiltro == null) ? null : marcaFiltro);   // IS NULL
//            cmd.setString(4, marcaFiltro);                                  // LIKE
//
//            cmd.setString(5, (categoriaFiltro == null) ? null : categoriaFiltro); // IS NULL
//            cmd.setString(6, categoriaFiltro);                                   // =
//
//            ResultSet rs = cmd.executeQuery();
//            List<ProductoDTO> objetos = new ArrayList<>();
//            while (rs.next()) {
//                objetos.add(createFromResultado(rs));
//            }
//
//            return objetos;
//
//        } catch (SQLException e) {
//            System.err.println("Error SQL durante búsqueda: " + e.getMessage());
//            throw new RuntimeException("No se pudo buscar el registro.", e);
//        } catch (Exception e) {
//            System.err.println("Error inesperado: " + e.getMessage());
//            throw new RuntimeException("Error inesperado al buscar registros.", e);
//        }
//    }
    
    
}