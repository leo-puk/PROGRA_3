/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.persistanceTest;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.techshopper.dao.ClienteDAO;
import pe.edu.pucp.techshopper.daoImp.ClienteDAOImp;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;

/**
 *
 * @author usuario
 */
public class ClientePersistanceTest {
    
    private ClienteDAO clientedao;
    
//    public ClientePersistanceTest() {
//        this.clientedao = new ClienteDAO();
//    }
    
//    @Test
//    public void testMostrarItemsCarrito() {
//        clientedao = new ClienteDAOImp();
//        int idCliente = 70;
//        System.out.println("test de mostrar carrito");
//        System.out.println("el usuario id=" + idCliente);
//        
//        ArrayList<CarritoItemsDTO>lista = clientedao.MostrarCarritoDeCliente(idCliente);
//        
//        for(CarritoItemsDTO item : lista){
//            System.out.println("\n Datos de item:");
//            System.out.println("id " + item.getIdCarritoItems());
//            System.out.println("producto " + item.getProducto().getIdProducto());
//        }
//        
//    }
    
    @Test
    public void testobtenerPorEmail() {
        clientedao = new ClienteDAOImp();
        System.out.println("obtenerClientePorCorreo");
        ClienteDTO cliente = new ClienteDTO();
        cliente= clientedao.obtenerPorEmail("melowolf01@gmail.com");
        
        System.out.println(cliente.getDireccion());
        
        
    }
    
}
