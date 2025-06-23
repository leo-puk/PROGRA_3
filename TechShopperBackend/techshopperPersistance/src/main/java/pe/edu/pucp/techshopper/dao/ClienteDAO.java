/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public interface ClienteDAO extends ICrud<ClienteDTO>{
//    public Integer insertarCarrito(Integer id_usuario,ProductoDTO producto,Integer cantidad);
    public Integer insertarCarrito(Integer id_usuario,Integer id_producto,Integer cantidad);
    public ArrayList<CarritoItemsDTO> MostrarCarritoDeCliente(Integer id_usuario);
//    public List<ProductoDTO> listarPor3criterios(String nombre, String categoria, String marca);
}
