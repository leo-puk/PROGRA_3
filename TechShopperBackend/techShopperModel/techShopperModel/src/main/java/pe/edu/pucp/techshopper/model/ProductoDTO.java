package pe.edu.pucp.techshopper.model;

public class ProductoDTO {
    private Integer idProducto;
    private Double precio;
    private Integer stockDisponible;
    private Integer stockReservado;
    private Integer stockMinimo;
    private String nombre;
    private String marca;
    private CategoriaDTO categoria;
    private String descripcion;
    private String imagenURL; 
    private UsuarioDTO usuario;

    public ProductoDTO() {
        this.idProducto = null;
        this.precio = null;
        this.stockDisponible = null;
        this.stockReservado = null;
        this.stockMinimo = null;
        this.nombre = null;
        this.marca = null;
        this.categoria = null;
        this.descripcion = null;
        this.imagenURL = null;
        this.usuario = null;
    }

    public ProductoDTO(Integer idProducto, Double precio, Integer stockDisponible, 
                     Integer stockReservado, Integer stockMinimo, String nombre, 
                     String marca, CategoriaDTO categoria, String descripcion, 
                     String imagenURL, UsuarioDTO usuario) {
        this.idProducto = idProducto;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.stockReservado = stockReservado;
        this.stockMinimo = stockMinimo;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagenURL = imagenURL; 
        this.usuario = usuario;
    }

    // Getters y Setters
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public Integer getStockReservado() {
        return stockReservado;
    }

    public void setStockReservado(Integer stockReservado) {
        this.stockReservado = stockReservado;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    // Método conveniente para obtener el stock total
    public Integer getStockTotal() {
        return (stockDisponible != null ? stockDisponible : 0) + 
               (stockReservado != null ? stockReservado : 0);
    }
}