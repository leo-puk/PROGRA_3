/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominios;

/**
 *
 * @author CRISTHIAN
 */
public class LocalizacionDTO {

    /**
     * @return the latitud
     */
    public Integer getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Integer getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    private Integer latitud;
    private Integer longitud;
    private String direccion;
}
