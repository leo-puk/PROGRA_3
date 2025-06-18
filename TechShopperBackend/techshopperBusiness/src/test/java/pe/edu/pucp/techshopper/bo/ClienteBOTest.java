
package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
    
    @Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        String correoONombreUsuario = "Juan Pérez";
        String contrasenia = "admin123";
        ClienteBO instance = new ClienteBO();
        UsuarioDTO expResult = null;
        UsuarioDTO result = instance.iniciarSesion(correoONombreUsuario, contrasenia);
//        assertEquals(expResult, result);
        if(result != null){
            System.out.println("Id: "+ result.getIdUsuario()+ "  Nombre: "+result.getNombre());
        }else{
            System.out.println("No existe");
        }
        
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
