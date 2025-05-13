 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.model;


public class ProductoDTO {
    
    private Integer idProducto;
    private Double precio;
    private Integer stock;
    private String nombre;
    private String marca;
    private CategoriaDTO categoria; //enum
    private String descripcion;
    private AdministradorDTO administrador;
    
    public ProductoDTO(){
        this.idProducto = null;
        this.precio = null;
        this.stock = null;
        this.nombre = null;
        this.marca = null;
        this.categoria = null;
        this.descripcion = null;
        this.descripcion = null;
        this.administrador =null;
    }
    public ProductoDTO(Integer idProducto,Double precio,Integer stock,String nombre,String marca,
            CategoriaDTO categoria,String descripcion, AdministradorDTO administrador){
        this.idProducto = idProducto;
        this.precio = precio;
        this.stock = stock;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.administrador = administrador;
    }

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorDTO administrador) {
        this.administrador = administrador;
    }
}
