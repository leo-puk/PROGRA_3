/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;
import java.util.ArrayList;
import techshopper.dto.CarritoDTO;

public interface CarritoDAO {
    void Insertar(CarritoDTO p);
    void Modificar(CarritoDTO p);
    void Eliminar(int id);
    CarritoDTO ObtenerPorId(int id);
    ArrayList<CarritoDTO> ListarTodos();
}
