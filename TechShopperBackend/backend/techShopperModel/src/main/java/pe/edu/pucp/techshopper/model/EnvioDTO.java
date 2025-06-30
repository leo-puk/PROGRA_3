/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;


public class EnvioDTO {
    
    private Integer idEnvio;
    private LocalizacionDTO destino;
    private LocalDateTime fechaEntrega;
    private String empresaCourier;
    private Double precio;

    /**
     * @return the idEnvio
     */
    public Integer getIdEnvio() {
        return idEnvio;
    }

    /**
     * @param idEnvio the idEnvio to set
     */
    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    /**
     * @return the destino
     */
    public LocalizacionDTO getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(LocalizacionDTO destino) {
        this.destino = destino;
    }

    /**
     * @return the fechaEntrega
     */
    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the empresaCourier
     */
    public String getEmpresaCourier() {
        return empresaCourier;
    }

    /**
     * @param empresaCourier the empresaCourier to set
     */
    public void setEmpresaCourier(String empresaCourier) {
        this.empresaCourier = empresaCourier;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public EnvioDTO(){
        this.idEnvio = null;
        this.destino = null;
        this.fechaEntrega = null;
        this.empresaCourier = null;
        this.precio = null;
    }
    
    public EnvioDTO(Integer idEnvio,LocalizacionDTO destino,LocalDateTime fechaEntrega,String empresaCourier,
            Double precio){
        this.idEnvio = null;
        this.destino = null;
        this.fechaEntrega = null;
        this.empresaCourier = null;
        this.precio = null;
    }
    
    
    private String fechaEntregaStr;

    public String getFechaEntregaStr() {
        return fechaEntregaStr;
    }

    public void setFechaEntregaStr(String fechaEntregaStr) {
        this.fechaEntregaStr = fechaEntregaStr;
    }
    
    
}
