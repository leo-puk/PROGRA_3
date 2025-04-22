/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominios;

/**
 *
 * @author CRISTHIAN
 */
public class ClienteDTO {

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param infoTarjetaCredito the infoTarjetaCredito to set
     */
    public void setInfoTarjetaCredito(String infoTarjetaCredito) {
        this.infoTarjetaCredito = infoTarjetaCredito;
    }

    /**
     * @return the infoCompra
     */
    public String getInfoCompra() {
        return infoCompra;
    }

    /**
     * @param infoCompra the infoCompra to set
     */
    public void setInfoCompra(String infoCompra) {
        this.infoCompra = infoCompra;
    }

    /**
     * @return the balanceCuenta
     */
    public Double getBalanceCuenta() {
        return balanceCuenta;
    }

    /**
     * @param balanceCuenta the balanceCuenta to set
     */
    public void setBalanceCuenta(Double balanceCuenta) {
        this.balanceCuenta = balanceCuenta;
    }
    
    
    private String nombreCliente;
    private String direccion;
    private String telefono;
    private String email;
    private String infoTarjetaCredito;
    private String infoCompra;
    private Double balanceCuenta;
    
    
    
}
