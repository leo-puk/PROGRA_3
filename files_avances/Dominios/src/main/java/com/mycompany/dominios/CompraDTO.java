/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominios;

import java.time.LocalDate;

/**
 *
 * @author CRISTHIAN
 */
public class CompraDTO {

    /**
     * @return the idCompra
     */
    public Integer getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the carrito
     */
    public CarritoDTO getCarrito() {
        return carrito;
    }

    /**
     * @param carrito the carrito to set
     */
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    /**
     * @return the precioTotal
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * @param precioTotal the precioTotal to set
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * @return the fechaCompra
     */
    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * @return the estado
     */
    public EstadoCompraDTO getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoCompraDTO estado) {
        this.estado = estado;
    }

    /**
     * @return the entrega
     */
    public EnvioDTO getEntrega() {
        return entrega;
    }

    /**
     * @param entrega the entrega to set
     */
    public void setEntrega(EnvioDTO entrega) {
        this.entrega = entrega;
    }
    
    private Integer idCompra;
    private CarritoDTO carrito;
    private Double precioTotal;
    private LocalDate fechaCompra;
    private EstadoCompraDTO estado;
    private EnvioDTO entrega;
    
    
}
