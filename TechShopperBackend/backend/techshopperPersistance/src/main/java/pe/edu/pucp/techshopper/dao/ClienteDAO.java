 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.util.ArrayList;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public interface ClienteDAO extends ICrud<ClienteDTO>{
    public Integer insertarCarrito(Integer id_usuario,Integer id_producto,Integer cantidad);
    public ArrayList<CarritoItemsDTO> MostrarCarritoDeCliente(Integer id_usuario);
    public Integer realizarCompra(Integer idUsuario, ArrayList<Integer> idItemSeleccionados);
    public ClienteDTO obtenerPorEmail(String email);
}
