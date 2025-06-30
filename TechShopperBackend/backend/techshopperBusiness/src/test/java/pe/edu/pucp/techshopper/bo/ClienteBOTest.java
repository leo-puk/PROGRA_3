
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.techshopper.daoImp.ClienteDAOImp;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;
import pe.edu.pucp.techshopper.model.EstadoConexionDTO;
import pe.edu.pucp.techshopper.model.UsuarioDTO;

/**
 *
 * @author melow
 */
public class ClienteBOTest {
    
    private ClienteBO clientebo;
    
    public ClienteBOTest() {
        this.clientebo = new ClienteBO();
    }
    
//    @Test
//    public void testIniciarSesion() {
//        System.out.println("iniciarSesion");
//        String correoONombreUsuario = "Juan Pérez";
//        String contrasenia = "admin123";
//        ClienteBO instance = new ClienteBO();
//        UsuarioDTO expResult = null;
//        UsuarioDTO result = instance.iniciarSesion(correoONombreUsuario, contrasenia);
////        assertEquals(expResult, result);
//        if(result != null){
//            System.out.println("Id: "+ result.getIdUsuario()+ "  Nombre: "+result.getNombre());
//        }else{
//            System.out.println("No existe");
//        }
//        
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }
    
    
    @Test
    public void testRegistraCliente() {
//        System.out.println("Registrar Cliente");
//        Integer resultado = clientebo.registrarClienteCompleto(
//            "estaNoEs",
//            "Melvin Custodio",
//            "melowf@gmail.com",
//            "ChorrillosCity",
//            "7112412",
//            null,
//            null
//        );
//        
//        Integer resultado = clientebo.eliminarDatosCliente(14);
    }
    
//    @Test
//    public void testInsertarCarrito() {
//        
//        System.out.println("test de insertar carrito");
//        clientebo.insertarCarrito(67, 1, 5);
//        
//    }
    
    
//    @Test
//    public void testMostrarItemsCarrito() {
//        clientebo = new ClienteBO();
//        int idCliente = 70;
//        System.out.println("test de mostrar carrito");
//        System.out.println("el usuario id=" + idCliente);
//        
//        ArrayList<CarritoItemsDTO>lista = clientebo.MostrarCarritoDeCliente(idCliente);
//        
//        for(CarritoItemsDTO item : lista){
//            System.out.println("\n Datos de item:");
//            System.out.println("id " + item.getIdCarritoItems());
//            System.out.println("producto " + item.getProducto().getIdProducto());
//            System.out.println("cantidad " + item.getCantidad());
//        }
//        
//    }
//    
    @Test
    public void testobtenerClientePorCorreo() {
        clientebo = new ClienteBO();
        int idCliente = 70;
        System.out.println("obtenerClientePorCorreo");
        ClienteDTO cliente= clientebo.obtenerClientePorCorreo("melowolf01@gmail.com");
        
        System.out.println(cliente.getDireccion());
        
        
    }
}
