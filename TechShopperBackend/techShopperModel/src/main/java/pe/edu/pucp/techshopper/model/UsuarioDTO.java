package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private Integer idUsuario;
    private String contraseña;
    private EstadoConexionDTO estadoConexion;
    private LocalDateTime fechaRegistro;
    private String nombre;
    private String email;
    private RolUsuarioDTO rol;

    public UsuarioDTO() {
        this.idUsuario = null;
        this.contraseña = null;
        this.estadoConexion = null;
        this.fechaRegistro = null;
        this.nombre = null;
        this.email = null;
        this.rol = null;
    }

    public UsuarioDTO(Integer idUsuario, String contraseña, EstadoConexionDTO estadoConexion, 
                     LocalDateTime fechaRegistro, String nombre, String email, RolUsuarioDTO rol) {
        this.idUsuario = idUsuario;
        this.contraseña = contraseña;
        this.estadoConexion = estadoConexion;
        this.fechaRegistro = fechaRegistro;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public EstadoConexionDTO getEstadoConexion() {
        return estadoConexion;
    }

    public void setEstadoConexion(EstadoConexionDTO estadoConexion) {
        this.estadoConexion = estadoConexion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolUsuarioDTO getRol() {
        return rol;
    }

    public void setRol(RolUsuarioDTO rol) {
        this.rol = rol;
    }
}