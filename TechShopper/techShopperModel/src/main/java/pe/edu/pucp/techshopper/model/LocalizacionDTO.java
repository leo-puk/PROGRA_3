
package pe.edu.pucp.techshopper.model;


public class LocalizacionDTO {
    
    private Integer idLocalizacion; 
    private Double latitud;
    private Double longitud;
    private String direccion;
    private ClienteDTO cliente;
    
    
    public LocalizacionDTO(){
        this.idLocalizacion = null;
        this.latitud = null;
        this.longitud = null;
        this.direccion = null;
    }
    
    public LocalizacionDTO(Integer idLocalizacion,Double latitud,Double longitud,String direccion){
        this.idLocalizacion = idLocalizacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    

}
