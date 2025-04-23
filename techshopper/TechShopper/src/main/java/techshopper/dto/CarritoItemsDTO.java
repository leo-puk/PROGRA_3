/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dto;

import java.time.LocalDateTime;

public class CarritoItemsDTO {

   
    private int idCarritoItems;
    private CarritoDTO carrito;
    private ProductoDTO producto;
    private int cantidad;
    private double precioUnitario;
    private LocalDateTime fechaRegistro;
    
    
    public int getIdCarritoItems() {
        return idCarritoItems;
    }

    public void setIdCarritoItems(int idCarritoItems) {
        this.idCarritoItems = idCarritoItems;
    }

    public CarritoDTO getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    

}
