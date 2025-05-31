/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;


public class AdministradorBOTest {
    
    private AdministradorBO administradorBO;
    
    public AdministradorBOTest() {
        this.administradorBO = new AdministradorBO();
    }
    

 
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        Integer resultado = this.administradorBO.insertar("algo", EstadoConexionDTO.CONECTADO, java.time.LocalDateTime.now(),
//                                            "didi2","didi2@gmail.com");
//        assertTrue(resultado > 0);
//        
//    }
    
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//        Boolean resultado = this.administradorBO.modificar(3,"adawdawf", EstadoConexionDTO.DESCONECTADO, java.time.LocalDateTime.now(),
//                                            "didi2ad","didi323@gmail.com");
//        assertTrue(resultado ==true);
//    }

    
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        Boolean resultado = this.administradorBO.eliminar(1);
//        assertTrue(resultado ==true);
//    }

//    @Test
//    public void testBuscar() {
//        System.out.println("buscar");
//        AdministradorDTO admin= this.administradorBO.buscar(3);
//        System.out.println(admin.getNombre());
//        
//    }

//    @Test
//    public void testListar() {
//        System.out.println("listar");
//        List<AdministradorDTO> listaAdministrador = this.administradorBO.listar();
//        for (int i = 0; i < listaAdministrador.size(); i++) {
//            System.out.println("ID del admin #" + (i+1) + ": " + 
//                              listaAdministrador.get(i).getIdPersona());
//        }
//    }
//    
}
