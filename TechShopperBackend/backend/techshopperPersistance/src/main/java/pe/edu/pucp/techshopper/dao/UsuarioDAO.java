/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.dao;

import pe.edu.pucp.techshopper.model.UsuarioDTO;


public interface UsuarioDAO extends ICrud<UsuarioDTO>{
    UsuarioDTO obtenerPorAtributosUnicos(UsuarioDTO filtro);
   UsuarioDTO obtenerPorEmail(String email);
}
