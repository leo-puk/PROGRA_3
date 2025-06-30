package pe.edu.pucp.techshopper.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.dao.CompraDAO;
import pe.edu.pucp.techshopper.dao.DetalleCompraDAO;
import pe.edu.pucp.techshopper.dao.EnvioDAO;
import pe.edu.pucp.techshopper.dao.LocalizacionDAO;
import pe.edu.pucp.techshopper.dao.MovimientoStockDAO;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.dao.UsuarioDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Haversine;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.CompraDTO;
import pe.edu.pucp.techshopper.model.DetalleCompraDTO;
import pe.edu.pucp.techshopper.model.EnvioDTO;
import pe.edu.pucp.techshopper.model.EstadoCompraDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;
import pe.edu.pucp.techshopper.model.MovimientoStockDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;
import pe.edu.pucp.techshopper.model.TipoMovimientoDTO;
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
    
        /*
    tipos de integer que devuelve:
    1: "compra exitosa"
    //
    -1: los datos de idusuario estan mal
    -2: al menos uno de los items seleccionado no existen
    -3: "el usuario ni si quiera tiene carrito"
    -4: "item no pertenece al carrito del usuario"
    -5: "no hay stock suficiente"
    -6: "no existe localizacion de usuario."
    */
    @Override
    public Integer realizarCompra(Integer idUsuario, ArrayList<Integer> idItemSeleccionados){
        if(idUsuario == null || idUsuario <=0 || idItemSeleccionados == null || idItemSeleccionados.isEmpty()){
            return -1;
        }
        //validaciones de carrito e item carrito
        //validar que todos los item carrito existan
        CarritoItemsDAO itemCarritoDao = new CarritoItemsDAOImp();
        ArrayList<CarritoItemsDTO> itemsSeleccionados = itemCarritoDao.ListarPorId(idItemSeleccionados);
        if(itemsSeleccionados.size() != itemsSeleccionados.size()){
            return -2;
        }
        //verificar que lso items pertenecen al carrito del usuario
        CarritoDAO carritodao = new CarritoDAOImp();
        CarritoDTO carritoUsuario = carritodao.ObtenerCarritoPorIdUsuaio(idUsuario);
        if(carritoUsuario == null){
            return -3;
        }
        for(CarritoItemsDTO item : itemsSeleccionados){
            if(item.getCarrito().getIdCarrito() != carritoUsuario.getIdCarrito()){
                return -4;
            }
        }
        //verificar stock de productos
        ProductoDAO productodao = new ProductoDAOImp();
        for(CarritoItemsDTO item : itemsSeleccionados){
            ProductoDTO producto = productodao.obtenerPorId(item.getProducto().getIdProducto());
            if(producto.getStockDisponible() < item.getCantidad()){
                return -5;
            }
        }
        
        Double precioTotal = 0.0;
        //reducir el stock y registrar movimientos de salida
        for(CarritoItemsDTO item : itemsSeleccionados){
            //para calcualr el precio total del carrito
            Double precio = item.getCantidad() * item.getPrecioUnitario();
            precioTotal += precio;
            //
            ProductoDTO producto = productodao.obtenerPorId(item.getProducto().getIdProducto());
            Integer stockActual = producto.getStockDisponible();
            stockActual -= item.getCantidad();
            producto.setStockDisponible(stockActual);
            producto.setStockReservado(producto.getStockReservado()+item.getCantidad());
            productodao.modificar(producto);
            //crear movimiento de stock
            MovimientoStockDAO movstockdao = new MovimientoStockDAOImp();
            MovimientoStockDTO movstock = new MovimientoStockDTO();
            movstock.setCantidad(item.getCantidad());
            movstock.setFechaMovimiento(LocalDateTime.now());
            movstock.setProducto(producto);
            movstock.setTipo(TipoMovimientoDTO.SALIDA);
            UsuarioDAO usuariodao = new UsuarioDAOImp();
            UsuarioDTO usuario = new UsuarioDTO();
            usuario = usuariodao.obtenerPorId(idUsuario);
            movstock.setUsuario(usuario);
            
            movstockdao.insertar(movstock);
        }
        
        //obtener localizacion del usuario
        LocalizacionDAO locadao = new LocalizacionDAOImp();
        LocalizacionDTO locadto = new LocalizacionDTO();
        locadto = locadao.obtenerPorIdUsuario(idUsuario);
        if(locadto ==null){
            return -6;
        }
        //crear registro envio
        EnvioDAO enviodao = new EnvioDAOImp();
        EnvioDTO enviodto = new EnvioDTO();
        enviodto.setDestino(locadto);
        enviodto.setEmpresaCourier("courier confiable");
        enviodto.setFechaEntrega(LocalDateTime.now());
        //calcula la distancia entre el almacen de origen a la direccion de envio
        Double distancia = Haversine.calcularDistanciaKm(locadto.getLatitud(), locadto.getLatitud());
        //5.00 es por que será el precio base de envio y el 0.50 es el precio por cada km
        Double precioEnvio = 5.00+(distancia*0.50);
        Double valorRedondeado = Math.round(precioEnvio*100.0)/100.0;
        enviodto.setPrecio(valorRedondeado);
        
        Integer idenvio = enviodao.insertar(enviodto);
        enviodto.setIdEnvio(idenvio);
        //crear registro de compra
        CompraDAO compradao = new CompraDAOImp();
        CompraDTO compradto = new CompraDTO();
        compradto.setCarrito(carritoUsuario);
        compradto.setEntrega(enviodto);
        compradto.setEstadoCompra(EstadoCompraDTO.PAGADO);
        compradto.setFechaCompra(LocalDateTime.now());
        compradto.setPrecioTotal(precioTotal+precioEnvio);
        Integer idcompra = compradao.insertar(compradto);
        compradto.setIdCompra(idcompra);
        //crear detalles de compra
        DetalleCompraDAO detalledao = new DetalleCompraDAOImp();
        for(CarritoItemsDTO item : itemsSeleccionados){
            DetalleCompraDTO detalledto = new DetalleCompraDTO();
            detalledto.setCantidad(item.getCantidad());
            detalledto.setCompra(compradto);
            //como todos los productos inluyen el igv entonces les sacaré el IGV tiene el producto
            detalledto.setIgv(item.getPrecioUnitario()*18/118);
            detalledto.setPrecioUnitario(item.getPrecioUnitario());
            detalledto.setProducto(item.getProducto());
            //inserta
            detalledao.insertar(detalledto);
        }
        //eliminar los items del carrito comprados
        itemCarritoDao.eliminarItems(idItemSeleccionados);
        //actualizar el total del carrito
        carritoUsuario.setPrecio(carritoUsuario.getPrecio()-precioTotal);
        carritodao.modificar(carritoUsuario);
        return 1;
    }
    
    @Override
    public ClienteDTO obtenerPorEmail(String email){
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        String sql = "SELECT * FROM TCS_USUARIOS WHERE EMAIL = ?";

        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {

            cmd.setString(1, email.trim());

            

        
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    UsuarioDTO usu= createUsuarioFromResultSet(rs);
                    ClienteDTO cli = obtenerPorId(usu.getIdUsuario());
                   
                    return cli;
                }
                return null; // No se encontró ningún usuario con ese email
            }

        } catch (SQLException e) {
            System.err.println("Error SQL al buscar usuario por email: " + e.getMessage());
            throw new RuntimeException("Error en la consulta de usuario por email", e);
        }
    }
    
    protected UsuarioDTO createUsuarioFromResultSet(ResultSet rs) throws SQLException {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
        usuario.setContraseña(rs.getString("CONTRASENA"));
        usuario.setEstadoConexion(EstadoConexionDTO.valueOf(rs.getString("ESTADO_CONEXION")));
        usuario.setFechaRegistro(rs.getTimestamp("FECHA_REGISTRO").toLocalDateTime());
        usuario.setNombre(rs.getString("NOMBRE"));
        usuario.setEmail(rs.getString("EMAIL"));
        usuario.setRol(RolUsuarioDTO.valueOf(rs.getString("ROL")));
        return usuario;
    }
    
}