/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;

import java.util.ArrayList;
import techshopper.dto.CompraDTO;


public interface CompraDAO {
    void Insertar(CompraDTO p);
    void Modificar(CompraDTO p);
    void Eliminar(int id);
    CompraDTO ObtenerPorId(int id);
    ArrayList<CompraDTO> ListarTodos();
}
