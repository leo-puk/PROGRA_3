/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;
import java.util.List;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public interface ProductoDAO extends ICrud<ProductoDTO>{
   public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca);
   public Integer verificarCambioStock(Integer idProducto, Integer nuevoStock);
}
