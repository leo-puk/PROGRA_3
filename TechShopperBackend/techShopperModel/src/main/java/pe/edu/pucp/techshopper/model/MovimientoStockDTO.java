package pe.edu.pucp.techshopper.model;

import java.time.LocalDateTime;

public class MovimientoStockDTO {
    private Integer idMovimiento;
    private ProductoDTO producto;
    private TipoMovimientoDTO tipo;
    private Integer cantidad;
    private LocalDateTime fechaMovimiento;
    private UsuarioDTO usuario;
    private String motivo;

    public MovimientoStockDTO() {
        this.idMovimiento = null;
        this.producto = null;
        this.tipo = null;
        this.cantidad = null;
        this.fechaMovimiento = null;
        this.usuario = null;
        this.motivo = null;
    }

    public MovimientoStockDTO(Integer idMovimiento, ProductoDTO producto, TipoMovimientoDTO tipo, 
                            Integer cantidad, LocalDateTime fechaMovimiento, 
                            UsuarioDTO usuario, String motivo) {
        this.idMovimiento = idMovimiento;
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.usuario = usuario;
        this.motivo = motivo;
    }

    // Getters y Setters
    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public TipoMovimientoDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimientoDTO tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
}