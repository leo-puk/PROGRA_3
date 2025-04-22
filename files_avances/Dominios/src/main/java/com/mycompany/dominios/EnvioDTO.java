/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominios;

import java.time.LocalDateTime;

/**
 *
 * @author CRISTHIAN
 */
public class EnvioDTO {

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
     * @return the empresaCouries
     */
    public String getEmpresaCouries() {
        return empresaCouries;
    }

    /**
     * @param empresaCouries the empresaCouries to set
     */
    public void setEmpresaCouries(String empresaCouries) {
        this.empresaCouries = empresaCouries;
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
    
    private LocalizacionDTO destino;
    private LocalDateTime fechaEntrega;
    private String empresaCouries;
    private Double precio;
    
}
