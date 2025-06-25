package pe.edu.pucp.techshopper.daoImp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.dao.AdministradorDAO;
import pe.edu.pucp.techshopper.daoImp.util.Columna;
import pe.edu.pucp.techshopper.daoImp.util.Tipo_Dato;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.NivelAccesoDTO;

public class AdministradorDAOImp extends DAOImplBase implements AdministradorDAO {

    private AdministradorDTO administrador;

    public AdministradorDAOImp() {
        super("TCS_ADMINISTRADORES");
        this.retornarLlavePrimaria = true;
        this.administrador = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.clear();
        this.listaColumnas.add(new Columna("ID_ADMINISTRADOR", Tipo_Dato.ENTERO, true, false));
        this.listaColumnas.add(new Columna("NIVEL_ACCESO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("FECHA_ULTIMO_ACCESO", Tipo_Dato.FECHA_HORA, false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.administrador.getIdUsuario());
        this.statement.setString(2, this.administrador.getNivelAcceso().name());
        
        // Manejo de fecha último acceso (puede ser null)
        if (this.administrador.getFechaUltimoAcceso() != null) {
            this.statement.setTimestamp(3, java.sql.Timestamp.valueOf(this.administrador.getFechaUltimoAcceso()));
        } else {
            this.statement.setNull(3, java.sql.Types.TIMESTAMP);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        
         
        this.statement.setString(1, this.administrador.getNivelAcceso().name());
        
        if (this.administrador.getFechaUltimoAcceso() != null) {
            this.statement.setTimestamp(2, java.sql.Timestamp.valueOf(this.administrador.getFechaUltimoAcceso()));
        } else {
            this.statement.setNull(2, java.sql.Types.TIMESTAMP);
        }
        
        this.statement.setInt(3, this.administrador.getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.administrador.getIdUsuario());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.administrador.getIdUsuario());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.administrador = new AdministradorDTO();
        this.administrador.setIdUsuario(this.resultSet.getInt("ID_ADMINISTRADOR"));
        
        // Manejo del enum NivelAcceso
        try {
            String nivelAccesoStr = this.resultSet.getString("NIVEL_ACCESO");
            NivelAccesoDTO nivelAcceso = NivelAccesoDTO.valueOf(nivelAccesoStr);
            this.administrador.setNivelAcceso(nivelAcceso);
        } catch (IllegalArgumentException e) {
            // Valor por defecto si hay error en la conversión
            this.administrador.setNivelAcceso(NivelAccesoDTO.BASICO);
        }
        
        // Manejo de fecha último acceso
        java.sql.Timestamp timestamp = this.resultSet.getTimestamp("FECHA_ULTIMO_ACCESO");
        if (timestamp != null) {
            this.administrador.setFechaUltimoAcceso(timestamp.toLocalDateTime());
        }
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.administrador = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if (this.administrador != null) {
            lista.add(this.administrador);
        }
    }

    @Override
    public Integer insertar(AdministradorDTO administrador) {
        if (administrador == null || administrador.getIdUsuario() == null) {
            return -1; 
        }
        this.administrador = administrador;
        return super.insertar();
    }

    @Override
    public AdministradorDTO obtenerPorId(Integer idAdministrador) {
        if (idAdministrador == null || idAdministrador <= 0) {
            return null; 
        }
        this.administrador = new AdministradorDTO();
        this.administrador.setIdUsuario(idAdministrador);
        super.obtenerPorId();
        return this.administrador;
    }

    @Override
    public ArrayList<AdministradorDTO> listarTodos() {
        return (ArrayList<AdministradorDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(AdministradorDTO administrador) {
        if (administrador == null || administrador.getIdUsuario() == null) {
            return -1; 
        }
        this.administrador = administrador;
        return super.modificar();
    }

    @Override
    public Integer eliminar(AdministradorDTO administrador) {
        if (administrador == null || administrador.getIdUsuario() == null) {
            return -1; 
        }
        this.administrador = administrador;
        return super.eliminar();
    }

    // Método adicional para actualizar solo la fecha de último acceso
//    public Integer actualizarUltimoAcceso(Integer idAdministrador) {
//        if (idAdministrador == null || idAdministrador <= 0) {
//            return -1;
//        }
//        
//        String sql = "UPDATE " + this.nombreTabla + " SET FECHA_ULTIMO_ACCESO = ? WHERE ID_ADMINISTRADOR = ?";
//        
//        try {
//            this.statement = this.connection.prepareStatement(sql);
//            this.statement.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
//            this.statement.setInt(2, idAdministrador);
//            
//            return this.statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        } finally {
//            try {
//                if (this.statement != null) this.statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}