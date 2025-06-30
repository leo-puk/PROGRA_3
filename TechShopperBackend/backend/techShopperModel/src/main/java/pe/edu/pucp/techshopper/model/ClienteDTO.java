package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class ClienteDTO extends UsuarioDTO {
    private String direccion;
    private String telefono;
    private String infoTarjetaCredito;
    private String infoCompra;
    private Double balanceCuenta;

    public ClienteDTO() {
        super();
        this.direccion = null;
        this.telefono = null;
        this.infoTarjetaCredito = null;
        this.infoCompra = null;
        this.balanceCuenta = null;
    }

    public ClienteDTO(Integer idUsuario, String contraseña, EstadoConexionDTO estadoConexion, 
                    LocalDateTime fechaRegistro, String nombre, String email,
                    String direccion, String telefono, String infoTarjetaCredito, 
                    String infoCompra, Double balanceCuenta) {
        super(idUsuario, contraseña, estadoConexion, fechaRegistro, nombre, email, RolUsuarioDTO.CLIENTE);
        this.direccion = direccion;
        this.telefono = telefono;
        this.infoTarjetaCredito = infoTarjetaCredito;
        this.infoCompra = infoCompra;
        this.balanceCuenta = balanceCuenta;
    }

    // Getters y Setters (igual que antes)
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