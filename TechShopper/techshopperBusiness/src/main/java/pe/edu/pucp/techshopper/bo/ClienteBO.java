/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.daoImp.ClienteDAOImp;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;

/**
 *
 * @author CRISTHIAN
 */
public class ClienteBO {
    private ClienteDAO clienteDAO;
    
    public ClienteBO(){
        this.clienteDAO = new ClienteDAOImp();
    }
    
    public Integer insertar(String contraseña, EstadoConexionDTO estadoConexion, 
                  LocalDateTime fechaRegistro, String nombre, String direccion, String telefono, String email,
                  String infoTarjetaCredito, String infoCompra, Double balanceCuenta){
        ClienteDTO clienteDTO= new ClienteDTO();
        clienteDTO.setBalanceCuenta(balanceCuenta);
        clienteDTO.setContraseña(contraseña);
        clienteDTO.setDireccion(direccion);
        clienteDTO.setEmail(email);
        clienteDTO.setEstadoConexion(estadoConexion);
        clienteDTO.setFechaRegistro(fechaRegistro);
        clienteDTO.setInfoCompra(infoCompra);
        clienteDTO.setInfoTarjetaCredito(infoTarjetaCredito);
        clienteDTO.setNombre(nombre);
        clienteDTO.setTelefono(telefono);
        
        return this.clienteDAO.insertar(clienteDTO);
    }
    
    public Boolean modificar(Integer idPersona, String contraseña, EstadoConexionDTO estadoConexion, 
                  LocalDateTime fechaRegistro, String nombre, String direccion, String telefono, String email,
                  String infoTarjetaCredito, String infoCompra, Double balanceCuenta){
        ClienteDTO clienteDTO= new ClienteDTO();
        clienteDTO.setIdPersona(idPersona);
        clienteDTO.setBalanceCuenta(balanceCuenta);
        clienteDTO.setContraseña(contraseña);
        clienteDTO.setDireccion(direccion);
        clienteDTO.setEmail(email);
        clienteDTO.setEstadoConexion(estadoConexion);
        clienteDTO.setFechaRegistro(fechaRegistro);
        clienteDTO.setInfoCompra(infoCompra);
        clienteDTO.setInfoTarjetaCredito(infoTarjetaCredito);
        clienteDTO.setNombre(nombre);
        clienteDTO.setTelefono(telefono);
        
        return this.clienteDAO.modificar(clienteDTO);
    }
    
    public Boolean eliminar(int id){
//        AdministradorDTO administradorDTO = new AdministradorDTO();
        return this.clienteDAO.eliminar(id);
    }
    
    public ClienteDTO buscar(int id){
        return this.clienteDAO.buscar(id);
    }
    
    public List<ClienteDTO> listar(){
        return this.clienteDAO.listar();
    }
}
