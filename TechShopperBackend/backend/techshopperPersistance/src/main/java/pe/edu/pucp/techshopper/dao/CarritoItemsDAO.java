/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.util.ArrayList;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;

public interface CarritoItemsDAO extends ICrud<CarritoItemsDTO>{
    public Integer salvar(CarritoItemsDTO itemsDTO);
     public CarritoItemsDTO obtenerPorIdProdIdCarrito(Integer productoId,Integer carritoId);
     public ArrayList<CarritoItemsDTO> listarPorCarrito(Integer id_usuario);
     public ArrayList<CarritoItemsDTO> ListarPorId(ArrayList<Integer> idItemSeleccionados);
     public Integer eliminarItems(ArrayList<Integer> idItemSeleccionados);
     public Double calcularMontoTotalCarrito(Integer idCarrito);
}
