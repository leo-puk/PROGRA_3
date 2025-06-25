/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.techshopper.model.AdministradorDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.NivelAccesoDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;


public class AdministradorBOTest {
    
    private AdministradorBO administradorBO;
    
    public AdministradorBOTest() {
        this.administradorBO = new AdministradorBO();
    }
    
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        
//        LocalDateTime fechaEspecifica = LocalDate.of(2023, Month.JANUARY, 27).atStartOfDay();
//        LocalDateTime fechaUltimo = LocalDate.of(2025, Month.JANUARY, 29).atStartOfDay();
//        Integer resultado = administradorBO.registrarAdministradorCompleto(
//            "admin123",
//            "Admin Principal",
//            "admin@example.com"
//        );   
//        
//    }
    
    
    
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//         LocalDateTime fechaUltimo = LocalDate.of(2025, Month.MARCH, 12).atStartOfDay();
//        int resultado = administradorBO.actualizarDatosAdministradorCompleto(
//            9, // ID del usuario
//            "nuevaContraseña123", 
//            EstadoConexionDTO.CONECTADO,
//            "Nuevo Nombre", 
//            "nuevo@email.com",
//            NivelAccesoDTO.BASICO,
//            fechaUltimo
//        );
//
//        if (resultado > 0) {
//            System.out.println("Actualización exitosa");
//        } else {
//            System.out.println("Error al actualizar: " + resultado);
//        }
//    }

    
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        Integer resultado = this.administradorBO.eliminarAdministradorCompleto(8);
//
//    }

//    @Test
//    public void testBuscar() {
//        System.out.println("buscar");
//        AdministradorDTO admin= this.administradorBO.obtenerAdministradorCompleto(8);
//        System.out.println(admin.getNombre() + "  "+ admin.getEmail()+ "  " + admin.getContraseña());
//        
//    }

//    @Test
//    public void testListar() {
//        System.out.println("listar");
//        List<AdministradorDTO> listaAdministrador = this.administradorBO.listarTodos();
//        for (int i = 0; i < listaAdministrador.size(); i++) {
//            System.out.println("ID del admin #" + (i+1) + ": " + 
//                              listaAdministrador.get(i).getIdPersona());
//        }
//    }
    
}
