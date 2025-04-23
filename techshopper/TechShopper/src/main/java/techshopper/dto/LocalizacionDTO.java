
package techshopper.dto;


public class LocalizacionDTO {
    
    private int idLocalizacion; 
    private int latitud;
    private int longitud;
    private String direccion;
    
    
    public LocalizacionDTO(){
        
    }
    
    public Integer getLatitud() {
        return latitud;
    }


    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }
    

}
