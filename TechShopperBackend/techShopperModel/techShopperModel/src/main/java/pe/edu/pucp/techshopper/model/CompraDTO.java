/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;


import java.time.LocalDateTime;

public class CompraDTO {

    private Integer idCompra;
    private CarritoDTO carrito;
    private Double precioTotal;
    private LocalDateTime  fechaCompra;
    private EstadoCompraDTO estadoCompra;
    private EnvioDTO entrega;
    
    public CompraDTO(){
        this.idCompra = null;
        this.carrito = null;
        this.precioTotal = null;
        this.fechaCompra = null;
        this.estadoCompra = null;
        this.entrega = null;
    }
    
    public CompraDTO(Integer idCompra,CarritoDTO carrito,Double precioTotal,LocalDateTime  fechaCompra,
            EstadoCompraDTO estadoCompra,EnvioDTO entrega){
        this.idCompra = idCompra;
        this.carrito = carrito;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
        this.estadoCompra = estadoCompra;
        this.entrega = entrega;
    }
    
    public EstadoCompraDTO getEstadoCompra() {
        return estadoCompra;
    }

   
    public void setEstadoCompra(EstadoCompraDTO estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    
    public Integer getIdCompra() {
        return idCompra;
    }

    
    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    
    public CarritoDTO getCarrito() {
        return carrito;
    }

    
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    
    public Double getPrecioTotal() {
        return precioTotal;
    }

    
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

  
    public EnvioDTO getEntrega() {
        return entrega;
    }

    public void setEntrega(EnvioDTO entrega) {
        this.entrega = entrega;
    }
    

    
    
}
