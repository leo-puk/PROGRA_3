/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import java.util.ArrayList;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public interface CarritoDAO extends ICrud<CarritoDTO>{
    
    Double montoAPagar (Integer idCarrito);
    
    public Integer insertaPorUsuario(Integer idUsuario, ProductoDTO producto,Integer cantidad);
    
    public CarritoDTO ObtenerCarritoPorIdUsuaio(Integer idUsuario);
    
    public ArrayList<CarritoItemsDTO> devolverItemsDeCarrito(Integer id_usuario);
}
