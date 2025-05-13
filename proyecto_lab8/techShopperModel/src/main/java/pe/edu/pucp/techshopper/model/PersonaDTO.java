/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class PersonaDTO {
    private Integer idPersona;
    private String contraseña;
    private EstadoConexionDTO estadoConexion;
    private LocalDateTime fechaRegistro;

    public PersonaDTO() {
        this.idPersona = null;
        this.contraseña = null;
        this.estadoConexion = null;
        this.fechaRegistro = null;
    }

    public PersonaDTO(Integer idPersona, String contraseña, EstadoConexionDTO estadoConexion, LocalDateTime fechaRegistro) {
        this.idPersona = idPersona;
        this.contraseña = contraseña;
        this.estadoConexion = estadoConexion;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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
}