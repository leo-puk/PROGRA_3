/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package techshopper.dao;
import java.util.ArrayList;
import techshopper.dto.ProductoDTO;

public interface ProductoDAO {
    void Insertar(ProductoDTO p);
    void Modificar(ProductoDTO p);
    void Eliminar(int id);
    ProductoDTO ObtenerPorId(int id);
    ArrayList<ProductoDTO> ListarTodos();
}
