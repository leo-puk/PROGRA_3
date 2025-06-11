 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.util.ArrayList;

public interface ICrud<T> {
    Integer insertar(T modelo);
    Integer modificar(T modelo);
    Integer eliminar(T modelo);
    T obtenerPorId(Integer id);
    ArrayList<T> listarTodos();
    
}
