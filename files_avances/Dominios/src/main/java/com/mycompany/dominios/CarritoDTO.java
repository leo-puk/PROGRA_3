/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominios;

import java.util.List;

/**
 *
 * @author CRISTHIAN
 */
public class CarritoDTO {

    /**
     * @return the idCarrito
     */
    public Integer getIdCarrito() {
        return idCarrito;
    }

    /**
     * @param idCarrito the idCarrito to set
     */
    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    /**
     * @return the listaProductos
     */
    public List<DetalleProductoDTO> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(List<DetalleProductoDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    private Integer idCarrito;
    private List<DetalleProductoDTO> listaProductos;
    private Double precio;
    
    
}
