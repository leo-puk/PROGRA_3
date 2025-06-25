package pe.edu.pucp.techshopper.daoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.techshopper.dao.UsuarioDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.UsuarioDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;

public class UsuarioDAOImp extends DAOImplBase implements UsuarioDAO {

    private UsuarioDTO usuario;

    public UsuarioDAOImp() {
        super("TCS_USUARIOS");
        this.retornarLlavePrimaria = true;
        this.usuario = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_USUARIO", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("CONTRASENA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("ESTADO_CONEXION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("FECHA_REGISTRO", Tipo_Dato.FECHA_HORA, false, false));
        this.listaColumnas.add(new Columna("NOMBRE", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("EMAIL", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("ROL", Tipo_Dato.CADENA, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.usuario.getContraseña());
        this.statement.setString(2, this.usuario.getEstadoConexion().name());
        this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.usuario.getFechaRegistro()));
        this.statement.setString(4, this.usuario.getNombre());
        this.statement.setString(5, this.usuario.getEmail());
        this.statement.setString(6, this.usuario.getRol().name());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.usuario.getContraseña());
        this.statement.setString(2, this.usuario.getEstadoConexion().name());
        this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.usuario.getFechaRegistro()));
        this.statement.setString(4, this.usuario.getNombre());
        this.statement.setString(5, this.usuario.getEmail());
        this.statement.setString(6, this.usuario.getRol().name());
        this.statement.setInt(7, this.usuario.getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.usuario.getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.usuario.getIdUsuario());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.usuario = new UsuarioDTO();
        this.usuario.setIdUsuario(this.resultSet.getInt("ID_USUARIO"));
        this.usuario.setContraseña(this.resultSet.getString("CONTRASENA"));
        
        // Manejo seguro del ENUM EstadoConexion
        try {
            String estadoConexionStr = this.resultSet.getString("ESTADO_CONEXION");
            EstadoConexionDTO estadoConexion = EstadoConexionDTO.valueOf(estadoConexionStr);
            this.usuario.setEstadoConexion(estadoConexion);
        } catch (IllegalArgumentException e) {
            this.usuario.setEstadoConexion(EstadoConexionDTO.DESCONECTADO);
        }
        
        // Manejo de fecha
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_REGISTRO");
        if (timestamp != null) {
            this.usuario.setFechaRegistro(timestamp.toLocalDateTime());
        }
        
        this.usuario.setNombre(this.resultSet.getString("NOMBRE"));
        this.usuario.setEmail(this.resultSet.getString("EMAIL"));
        
        // Manejo seguro del ENUM Rol
        try {
            String rolStr = this.resultSet.getString("ROL");
            RolUsuarioDTO rol = RolUsuarioDTO.valueOf(rolStr);
            this.usuario.setRol(rol);
        } catch (IllegalArgumentException e) {
            this.usuario.setRol(RolUsuarioDTO.CLIENTE); // Valor por defecto
        }
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.usuario = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.usuario != null) {
            lista.add(this.usuario);
        }
    }

    @Override
    public Integer insertar(UsuarioDTO usuario) {
        if (usuario == null) {
            return -1;
        }
        this.usuario = usuario;
        return super.insertar();
    }

    @Override
    public UsuarioDTO obtenerPorId(Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            return null;
        }
        this.usuario = new UsuarioDTO();
        this.usuario.setIdUsuario(idUsuario);
        super.obtenerPorId();
        return this.usuario;
    }

    @Override
    public ArrayList<UsuarioDTO> listarTodos() {
        return (ArrayList<UsuarioDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(UsuarioDTO usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            return -1;
        }
        this.usuario = usuario;
        return super.modificar();
    }

    @Override
    public Integer eliminar(UsuarioDTO usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            return -1;
        }
        this.usuario = usuario;
        return super.eliminar();
    }
    
    @Override
    public UsuarioDTO obtenerPorAtributosUnicos(UsuarioDTO filtro) {
        String sql = getBaseQueryForUniqueAttributes();
        List<Object> parametros = new ArrayList<>();

        // Parámetro obligatorio (contraseña)
        parametros.add(filtro.getContraseña());

        // Agregar criterios dinámicos
        if (filtro.getEmail() != null && !filtro.getEmail().trim().isEmpty()) {
            sql += " AND EMAIL = ?";
            parametros.add(filtro.getEmail().trim());
        } else if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
            sql += " AND NOMBRE = ?";
            parametros.add(filtro.getNombre().trim());
        } else {
            return null; // No hay criterio adicional válido
        }

        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {

            // Asignar parámetros en orden
            for (int i = 0; i < parametros.size(); i++) {
                cmd.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    return createUsuarioFromResultSet(rs);
                }
                return null; // No se encontraron resultados
            }

        } catch (SQLException e) {
            System.err.println("Error SQL al buscar usuario: " + e.getMessage());
            throw new RuntimeException("Error en la consulta de usuario", e);
        }
    }

    protected String getBaseQueryForUniqueAttributes() {
        return "SELECT * FROM TCS_USUARIOS WHERE CONTRASENA = ?";
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
    
    @Override
    public UsuarioDTO obtenerPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        String sql = "SELECT * FROM TCS_USUARIOS WHERE EMAIL = ?";

        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {

            cmd.setString(1, email.trim());

            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    return createUsuarioFromResultSet(rs);
                }
                return null; // No se encontró ningún usuario con ese email
            }

        } catch (SQLException e) {
            System.err.println("Error SQL al buscar usuario por email: " + e.getMessage());
            throw new RuntimeException("Error en la consulta de usuario por email", e);
        }
    }
    
}