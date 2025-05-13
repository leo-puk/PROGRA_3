/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;


public class ClienteDTO extends PersonaDTO {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String infoTarjetaCredito;
    private String infoCompra;
    private Double balanceCuenta;

    public ClienteDTO() {
        super();
        this.nombre = null;
        this.direccion = null;
        this.telefono = null;
        email=null;
        this.infoTarjetaCredito = null;
        this.infoCompra = null;
        this.balanceCuenta = null;
    }

    public ClienteDTO(Integer idPersona, String contraseña, EstadoConexionDTO estadoConexion, 
                  LocalDateTime fechaRegistro, String nombre, String direccion, String telefono, String email,
                  String infoTarjetaCredito, String infoCompra, Double balanceCuenta) {
        super(idPersona, contraseña, estadoConexion, fechaRegistro);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email=email;
        this.infoTarjetaCredito = infoTarjetaCredito;
        this.infoCompra = infoCompra;
        this.balanceCuenta = balanceCuenta;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfoTarjetaCredito() {
        return infoTarjetaCredito;
    }

    public void setInfoTarjetaCredito(String infoTarjetaCredito) {
        this.infoTarjetaCredito = infoTarjetaCredito;
    }

    public String getInfoCompra() {
        return infoCompra;
    }

    public void setInfoCompra(String infoCompra) {
        this.infoCompra = infoCompra;
    }

    public Double getBalanceCuenta() {
        return balanceCuenta;
    }

    public void setBalanceCuenta(Double balanceCuenta) {
        this.balanceCuenta = balanceCuenta;
    }
}