package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class AdministradorDTO extends UsuarioDTO {
    private NivelAccesoDTO nivelAcceso;
    private LocalDateTime fechaUltimoAcceso;

    public AdministradorDTO() {
        super();
        this.nivelAcceso = null;
        this.fechaUltimoAcceso = null;
    }

    public AdministradorDTO(Integer idUsuario, String contraseña, EstadoConexionDTO estadoConexion, 
                          LocalDateTime fechaRegistro, String nombre, String email, 
                          NivelAccesoDTO nivelAcceso, LocalDateTime fechaUltimoAcceso) {
        super(idUsuario, contraseña, estadoConexion, fechaRegistro, nombre, email, RolUsuarioDTO.ADMINISTRADOR);
        this.nivelAcceso = nivelAcceso;
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    public NivelAccesoDTO getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(NivelAccesoDTO nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public LocalDateTime getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public void setFechaUltimoAcceso(LocalDateTime fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }
}