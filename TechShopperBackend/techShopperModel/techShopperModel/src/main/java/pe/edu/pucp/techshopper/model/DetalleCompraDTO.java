/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;

public class DetalleCompraDTO {
    
    private Integer idDetalleCompra;
    private CompraDTO compra;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double igv;
    
    public DetalleCompraDTO(){
        this.idDetalleCompra = null;
        this.compra = null;
        this.producto = null;
        this.cantidad = null;
        this.precioUnitario = null;
        this.igv = null;
    }
    
    public DetalleCompraDTO(Integer idDetalleCompra,CompraDTO compra,ProductoDTO producto,Integer cantidad,
            Double precioUnitario, Double igv){
        
        this.idDetalleCompra = idDetalleCompra;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.igv = igv;
        
    }
    
    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public CompraDTO getCompra() {
        return compra;
    }

    public void setCompra(CompraDTO compra) {
        this.compra = compra;
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

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

}
