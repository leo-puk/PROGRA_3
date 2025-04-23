/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.dao;

import java.util.ArrayList;
import techshopper.dto.DetalleCompraDTO;


public interface DetalleCompraDAO {
    Integer Insertar(DetalleCompraDTO p);
    void Modificar(DetalleCompraDTO p);
    void Eliminar(int id);
    DetalleCompraDTO ObtenerPorId(int id);
    ArrayList<DetalleCompraDTO> ListarTodos();
}
