/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class AdministradorDTO extends PersonaDTO {
    private String nombre;
    private String email;

    public AdministradorDTO() {
        super();
        this.nombre = null;
        this.email = null;
    }

    public AdministradorDTO(Integer idPersona, String contraseña, EstadoConexionDTO estadoConexion, 
                        LocalDateTime fechaRegistro, String nombre, String email) {
        super(idPersona, contraseña, estadoConexion, fechaRegistro);
        this.nombre = nombre;
        this.email = email;
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
}