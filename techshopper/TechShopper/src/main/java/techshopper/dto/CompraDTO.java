/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dto;


import java.time.LocalDateTime;

public class CompraDTO {

    private int idCompra;
    private CarritoDTO carrito;
    private double precioTotal;
    private LocalDateTime  fechaCompra;
    private EstadoCompraDTO estadoCompra;
    private EnvioDTO entrega;
   
    public EstadoCompraDTO getEstadoCompra() {
        return estadoCompra;
    }

   
    public void setEstadoCompra(EstadoCompraDTO estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    
    public int getIdCompra() {
        return idCompra;
    }

    
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    
    public CarritoDTO getCarrito() {
        return carrito;
    }

    
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    
    public double getPrecioTotal() {
        return precioTotal;
    }

    
    public void setPrecioTotal(double precioTotal) {
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
