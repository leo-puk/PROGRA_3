 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.util.List;

public interface ICrud<T> {
    int insertar(T modelo);
    boolean modificar(T modelo);
    boolean eliminar(int id);
    T buscar(int id);
    List<T> listar();
}
