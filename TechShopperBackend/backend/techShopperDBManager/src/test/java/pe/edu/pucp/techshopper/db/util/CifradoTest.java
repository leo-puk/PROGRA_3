package pe.edu.pucp.techshopper.db.util;

import pe.edu.pucp.techshopper.db.util.Cifrado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andres
 */
public class CifradoTest {
    
    public CifradoTest() {
    }

    /**
     * Test of cifrarMD5 method, of class Cifrado.
     */
    @Test
    public void testCifrarMD5() {
        System.out.println("cifrarMD5");
        String texto = "4111111111111111";        
        String resultado = Cifrado.cifrarMD5(texto);
        System.out.println(resultado);
//        String resultado_esperado = "R8gBwyMzjrwsc60oL2HHtw==";                                        
//        assertEquals(resultado_esperado, resultado);        
    }

    /**
     * Test of descifrarMD5 method, of class Cifrado.
     */
//    @Test
//    public void testDescifrarMD5() {
//        System.out.println("descifrarMD5");
//        String textoEncriptado = "R8gBwyMzjrwsc60oL2HHtw==";
//        String resultado_esperado = "GitHappens";
//        String resultado = Cifrado.descifrarMD5(textoEncriptado);
//        System.out.println(resultado);
//        assertEquals(resultado_esperado, resultado);
//    }
    
}
