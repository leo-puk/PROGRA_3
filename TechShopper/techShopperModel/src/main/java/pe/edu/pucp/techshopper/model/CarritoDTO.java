/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

public class CarritoDTO {


    private Integer idCarrito;
    private Double precio;
    private ClienteDTO cliente;
    
    public CarritoDTO(){
        this.idCarrito = null;
        this.precio = null;
    }
    
    public CarritoDTO(Integer idCarrito, Double precio,ClienteDTO cliente){
        this.idCarrito = idCarrito;
        this.precio = precio;
        this.cliente = cliente;
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
}
