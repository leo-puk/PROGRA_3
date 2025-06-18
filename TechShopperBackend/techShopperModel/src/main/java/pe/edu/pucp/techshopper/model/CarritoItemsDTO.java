/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class CarritoItemsDTO {

   
    private Integer idCarritoItems;
    private CarritoDTO carrito;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;
    private LocalDateTime fechaRegistro;
    
    public CarritoItemsDTO(){
        this.idCarritoItems = null;
        this.carrito = null;
        this.producto = null;
        this.cantidad = null;
        this.precioUnitario = null;
        this.fechaRegistro = null;
    }
    
    public CarritoItemsDTO(Integer idCarritoItems,CarritoDTO carrito,ProductoDTO producto,Integer cantidad,Double precioUnitario,
            LocalDateTime fechaRegistro){
        this.idCarritoItems = idCarritoItems;
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaRegistro = fechaRegistro;
    }
    
    
    public Integer getIdCarritoItems() {
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


    public Integer getCantidad() {
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
