/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.RolUsuarioDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;


public class UsuarioBOTest {
    
    private UsuarioBO usuarioBO;
    
    public UsuarioBOTest() {
        this.usuarioBO = new UsuarioBO();
    }
    
//    @Test
//    public void testRegistrarUsuario() {
//        System.out.println("registrarUsuario");
//        String contraseña = "awdada";
//        EstadoConexionDTO estadoConexion = EstadoConexionDTO.CONECTADO;
//        String nombre = "adad";
//        String email = "adadad";
//        RolUsuarioDTO rol = RolUsuarioDTO.ADMINISTRADOR;
//        LocalDateTime fechaRegistro = java.time.LocalDateTime.now();
//        UsuarioBO instance = new UsuarioBO();
//        Integer result = instance.registrarUsuario(contraseña, estadoConexion, fechaRegistro, nombre, email, rol);
//        assertTrue(result > 0);
//    }
//
//    /**
//     * Test of actualizarUsuario method, of class UsuarioBO.
//     */
//    @Test
//    public void testActualizarUsuario() {
//        System.out.println("actualizarUsuario");
//        Integer idUsuario = null;
//        String contraseña = "";
//        EstadoConexionDTO estadoConexion = null;
//        String nombre = "";
//        String email = "";
//        UsuarioBO instance = new UsuarioBO();
//        Integer expResult = null;
//        Integer result = instance.actualizarUsuario(idUsuario, contraseña, estadoConexion, nombre, email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminarUsuario method, of class UsuarioBO.
//     */
//    @Test
//    public void testEliminarUsuario() {
//        System.out.println("eliminarUsuario");
//        Integer idUsuario = null;
//        UsuarioBO instance = new UsuarioBO();
//        Integer expResult = null;
//        Integer result = instance.eliminarUsuario(idUsuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerUsuarioPorId method, of class UsuarioBO.
//     */
//    @Test
//    public void testObtenerUsuarioPorId() {
//        System.out.println("obtenerUsuarioPorId");
//        Integer idUsuario = null;
//        UsuarioBO instance = new UsuarioBO();
//        UsuarioDTO expResult = null;
//        UsuarioDTO result = instance.obtenerUsuarioPorId(idUsuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actualizarEstadoConexion method, of class UsuarioBO.
//     */
//    @Test
//    public void testActualizarEstadoConexion() {
//        System.out.println("actualizarEstadoConexion");
//        Integer idUsuario = null;
//        EstadoConexionDTO estado = null;
//        UsuarioBO instance = new UsuarioBO();
//        Integer expResult = null;
//        Integer result = instance.actualizarEstadoConexion(idUsuario, estado);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
