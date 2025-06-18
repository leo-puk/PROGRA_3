/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import pe.edu.pucp.techshopper.model.CarritoDTO;

public interface CarritoDAO extends ICrud<CarritoDTO>{
    
    Double montoAPagar (Integer idCarrito);

}
