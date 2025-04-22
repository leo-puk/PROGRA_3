package com.pucp.app;

import com.pucp.da.AlumnoCRUD;
import com.pucp.modelo.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * @author heide
 */
public class Main {
    public static void main(String[] args){
        try{
            Alumno alumno = new Alumno();
            alumno.setNombre("Mabel Vera");
            alumno.setFechaNacimiento(Date.valueOf("2001-12-25"));
            alumno.setCraest(68);
            alumno.setActivo(true);
            alumno.setTipoAlumno(TIPO_ALUMNO.PREGRADO);
            
            AlumnoCRUD alumnoCRUD = new AlumnoCRUD();
            alumnoCRUD.insertar(alumno);
            
            Alumno alumno2 = alumnoCRUD.obtenerPorId(alumno.getId());
            System.out.println("Alumno insertado recientemente:");
            System.out.println(alumno2);
            System.out.println();
            
            //actualizar fecha de nacimiento
            alumno2.setFechaNacimiento(Date.valueOf("2010-11-15"));
            alumnoCRUD.actualizar(alumno2); 
            
            alumnoCRUD.eliminar(5);

            ArrayList<Alumno> alumnos = alumnoCRUD.obtenerTodos();
            for(Alumno al : alumnos){
                System.out.println(al);
            }  
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
