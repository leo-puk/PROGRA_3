package pe.edu.pucp.techshopper.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.ProductoDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.CategoriaDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

public class ProductoDAOImp extends DAOImplBase implements ProductoDAO {

    private ProductoDTO producto;

    public ProductoDAOImp() {
        super("TCS_PRODUCTOS");
        this.retornarLlavePrimaria = true;
        this.producto = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_PRODUCTO", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("PRECIO", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("STOCK_DISPONIBLE", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("STOCK_RESERVADO", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("STOCK_MINIMO", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("NOMBRE", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("MARCA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("CATEGORIA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("DESCRIPCION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("URL_IMAGEN", Tipo_Dato.CADENA, false, false)); // Nueva columna
        this.listaColumnas.add(new Columna("ID_USUARIO", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1, this.producto.getPrecio());
        this.statement.setInt(2, this.producto.getStockDisponible());
        this.statement.setInt(3, this.producto.getStockReservado());
        this.statement.setInt(4, this.producto.getStockMinimo());
        this.statement.setString(5, this.producto.getNombre());
        this.statement.setString(6, this.producto.getMarca());
        this.statement.setString(7, this.producto.getCategoria().name());
        this.statement.setString(8, this.producto.getDescripcion());
        this.statement.setString(9, this.producto.getImagenURL()); // Nuevo parámetro
        this.statement.setInt(10, this.producto.getUsuario().getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1, this.producto.getPrecio());
        this.statement.setInt(2, this.producto.getStockDisponible());
        this.statement.setInt(3, this.producto.getStockReservado());
        this.statement.setInt(4, this.producto.getStockMinimo());
        this.statement.setString(5, this.producto.getNombre());
        this.statement.setString(6, this.producto.getMarca());
        this.statement.setString(7, this.producto.getCategoria().name());
        this.statement.setString(8, this.producto.getDescripcion());
        this.statement.setString(9, this.producto.getImagenURL()); // Nuevo parámetro
        this.statement.setInt(10, this.producto.getUsuario().getIdUsuario());
        this.statement.setInt(11, this.producto.getIdProducto());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.producto.getIdProducto());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.producto.getIdProducto());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.producto = new ProductoDTO();
        this.producto.setIdProducto(this.resultSet.getInt("ID_PRODUCTO"));
        this.producto.setPrecio(this.resultSet.getDouble("PRECIO"));
        this.producto.setStockDisponible(this.resultSet.getInt("STOCK_DISPONIBLE"));
        this.producto.setStockReservado(this.resultSet.getInt("STOCK_RESERVADO"));
        this.producto.setStockMinimo(this.resultSet.getInt("STOCK_MINIMO"));
        this.producto.setNombre(this.resultSet.getString("NOMBRE"));
        this.producto.setMarca(this.resultSet.getString("MARCA"));
        
        String categoriaStr = this.resultSet.getString("CATEGORIA");
        CategoriaDTO categoria = CategoriaDTO.valueOf(categoriaStr);
        this.producto.setCategoria(categoria);
        
        this.producto.setDescripcion(this.resultSet.getString("DESCRIPCION"));
        this.producto.setImagenURL(this.resultSet.getString("URL_IMAGEN")); // Nuevo campo
        
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(this.resultSet.getInt("ID_USUARIO"));
        this.producto.setUsuario(usuario);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.producto = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.producto != null) {
            lista.add(this.producto);
        }
    }

    @Override
    public Integer insertar(ProductoDTO producto) {
        if (producto == null) {
            return -1;
        }
        this.producto = producto;
        return super.insertar();
    }

    @Override
    public ProductoDTO obtenerPorId(Integer idProducto) {
        if (idProducto == null || idProducto <= 0) {
            return null;
        }
        this.producto = new ProductoDTO();
        this.producto.setIdProducto(idProducto);
        super.obtenerPorId();
        return this.producto;
    }

    @Override
    public ArrayList<ProductoDTO> listarTodos() {
        return (ArrayList<ProductoDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(ProductoDTO producto) {
        if (producto == null || producto.getIdProducto() == null) {
            return -1;
        }
        this.producto = producto;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ProductoDTO producto) {
        if (producto == null || producto.getIdProducto() == null) {
            return -1;
        }
        this.producto = producto;
        return super.eliminar();
    }
    
    /***Otros métodos***/
    
    @Override
    public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca){
        String sql = getSelectedAlQueryByFilters();
        List<Object> parametros = new ArrayList<>();

        if (nombre != null && !nombre.trim().isEmpty()) {
            sql += " AND nombre LIKE ?";
            parametros.add("%" + nombre.trim() + "%");
        }

        if (marca != null && !marca.trim().isEmpty()) {
            sql += " AND marca LIKE ?";
            parametros.add("%" + marca.trim() + "%");
        }

        if (categoria != null && !categoria.trim().isEmpty()) {
            sql += " AND categoria = ?";
            parametros.add(categoria.trim());
        }

        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {

            // Asignar parámetros en orden
            for (int i = 0; i < parametros.size(); i++) {
                cmd.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = cmd.executeQuery()) {
                List<ProductoDTO> resultados = new ArrayList<>();
                while (rs.next()) {
                    resultados.add(createFromResultado(rs));
                }
                return resultados;
            }

        } catch (SQLException e) {
            System.err.println("Error SQL durante el filtrado: " + e.getMessage());
            throw new RuntimeException("No se pudo filtrar los productos.", e);
        }
    }
    
    protected String getSelectedAlQueryByFilters(){
        return "select * from TCS_PRODUCTOS where 1=1";
    }
    
    protected ProductoDTO createFromResultado(ResultSet rs) throws SQLException {
        ProductoDTO p = new ProductoDTO();
        p.setIdProducto(rs.getInt("ID_PRODUCTO"));
        p.setDescripcion(rs.getString("DESCRIPCION"));
        p.setCategoria(CategoriaDTO.valueOf(rs.getString("CATEGORIA")));
        p.setMarca(rs.getString("MARCA"));
        p.setNombre(rs.getString("NOMBRE"));
        p.setPrecio(rs.getDouble("PRECIO"));
        p.setStockDisponible(rs.getInt("STOCK_DISPONIBLE"));
        p.setImagenURL(rs.getString("URL_IMAGEN")); // Nuevo campo
        
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
        p.setUsuario(usuario);
        return p;
    }
    


@Override
    public Integer verificarCambioStock(Integer idProducto, Integer nuevoStock) {
        if (idProducto == null || idProducto <= 0 || nuevoStock == null) {
            return null;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getInstance().getConnection();
            String sql = "SELECT STOCK_DISPONIBLE FROM TCS_PRODUCTOS WHERE ID_PRODUCTO = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProducto);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                return null; // Producto no encontrado
            }

            int stockActual = rs.getInt("STOCK_DISPONIBLE");
            return nuevoStock - stockActual; // Diferencia

        } catch (SQLException e) {
            System.err.println("Error al verificar stock: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}