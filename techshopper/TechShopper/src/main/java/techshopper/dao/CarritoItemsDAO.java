/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;

import java.util.ArrayList;
import techshopper.dto.CarritoItemsDTO;

public interface CarritoItemsDAO {
    void Insertar(CarritoItemsDTO p);
    void Modificar(CarritoItemsDTO p);
    void Eliminar(int id);
    CarritoItemsDTO ObtenerPorId(int id);
    ArrayList<CarritoItemsDTO> ListarTodos();
}
