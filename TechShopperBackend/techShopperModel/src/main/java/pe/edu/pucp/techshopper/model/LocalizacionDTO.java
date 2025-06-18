
package pe.edu.pucp.techshopper.model;


public class LocalizacionDTO {
    
    private Integer idLocalizacion; 
    private Double latitud;
    private Double longitud;
    private String direccion;
    private UsuarioDTO usuario;
    
    
    public LocalizacionDTO(){
        this.idLocalizacion = null;
        this.latitud = null;
        this.longitud = null;
        this.direccion = null;
        this.usuario= null;
    }
    
    public LocalizacionDTO(Integer idLocalizacion,Double latitud,Double longitud,String direccion, UsuarioDTO usuario){
        this.idLocalizacion = idLocalizacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.usuario = usuario;
    }
    
    public Double getLatitud() {
        return latitud;
    }


    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO cliente) {
        this.usuario = cliente;
    }
    

}
