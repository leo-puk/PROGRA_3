/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp;
import pe.edu.pucp.techshopper.db.DBManager;
import pe.edu.pucp.techshopper.model.LocalizacionDTO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.dao.BaseDAOImp;
import pe.edu.pucp.techshopper.dao.LocalizacionDAO;

public class LocalizacionDAOImp extends BaseDAOImp<LocalizacionDTO> implements LocalizacionDAO{
    public LocalizacionDAOImp(){
        
    }
    
    @Override
    protected String getInsertarQuery() {
        return  "insert into Localizacion (idLocalizacion,latitud,longitud,direccion, idCliente) values (?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
       return  "update Localizacion SET latitud = ?, longitud = ?, direccion = ?, idCliente_localizacion=? where idLocalizacion = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "select * from Localizacion WHERE idLocalizacion = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select * from Localizacion";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from Localizacion WHERE idLocalizacion = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, LocalizacionDTO modelo) throws SQLException {
        ps.setInt(1,modelo.getIdLocalizacion());
        ps.setDouble(2,modelo.getLatitud());
        ps.setDouble(3,modelo.getLongitud());
        ps.setString(4,modelo.getDireccion());
        ps.setInt(5,modelo.getCliente().getIdPersona());
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, LocalizacionDTO modelo) throws SQLException {
        ps.setDouble(1,modelo.getLatitud());
        ps.setDouble(2,modelo.getLongitud());
        ps.setString(3,modelo.getDireccion());
        ps.setInt(4,modelo.getCliente().getIdPersona());
        ps.setInt(5,modelo.getIdLocalizacion());
    }

    @Override
    protected LocalizacionDTO createFromResultado(ResultSet rs) throws SQLException {
        LocalizacionDTO p = new LocalizacionDTO();
        p.setIdLocalizacion(rs.getInt("idLocalizacion"));
        p.setLatitud(rs.getDouble("latitud"));
        p.setLongitud(rs.getDouble("longitud"));
        p.setDireccion(rs.getString("direccion"));

        ClienteDTO cli = new ClienteDTO();
        cli.setIdPersona(rs.getInt("idCliente_localizacion"));
        p.setCliente(cli);
        return p;
    }
    
}
