/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

public class CarritoDTO {


    private Integer idCarrito;
    private Double precio;
    private UsuarioDTO usuario;
    
    public CarritoDTO(){
        this.idCarrito = null;
        this.precio = null;
        this.usuario = null;
    }
    
    public CarritoDTO(Integer idCarrito, Double precio, UsuarioDTO usuario){
        this.idCarrito = idCarrito;
        this.precio = precio;
        this.usuario=usuario;
    }
    
    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO cliente) {
        this.usuario = cliente;
    }
    
}
