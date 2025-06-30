/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.servicesUtils;

import pe.edu.pucp.techshopper.model.CarritoItemsDTO;

/**
 *
 * @author usuario
 */
public class CarritoItemsDTOSoap {
    public Integer idCarritoItems;
    public Integer idCarrito;
    public Integer idProducto;
    public Integer cantidad;
    public Double precioUnitario;
    public String fechaRegistroIso;
    
    public CarritoItemsDTOSoap(CarritoItemsDTO dto) {
        this.idCarritoItems = dto.getIdCarritoItems();
        this.idCarrito = (dto.getCarrito() != null) ? dto.getCarrito().getIdCarrito() : null;
        this.idProducto = (dto.getProducto() != null) ? dto.getProducto().getIdProducto() : null;
        this.cantidad = dto.getCantidad();
        this.precioUnitario = dto.getPrecioUnitario();
        this.fechaRegistroIso = (dto.getFechaRegistro() != null)
                ? dto.getFechaRegistro().toString()  // ISO-8601 por defecto
                : null;
    }
}
