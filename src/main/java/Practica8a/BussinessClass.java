/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica8a;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfervic933@maraboran.es
 */
public class BussinessClass {


    public static void main(String[] args) throws SQLException {
        //Instanciamos DAO
        IMuebleDAO dao = new MuebleDAO();
        
        List<MuebleVO> lista = new ArrayList<>();

        lista.add(new MuebleVO("AA2", "Mesa", 60.20, 50, 20));
        lista.add(new MuebleVO("AB1", "Silla", 30, 40, 20.10));
        lista.add(new MuebleVO("BB2", "Escritorio", 75.10, 60, 30));
        lista.add(new MuebleVO("BB3", "Mesita de noche", 20.5, 35.30, 30.20));
        lista.add(new MuebleVO("DB9", "Armario", 50, 100.50, 30.60));
        lista.add(new MuebleVO("NA5", "Estantería", 20.70, 70, 15.20));


         System.out.println("Nº personas insertadas " + dao.insertMueble(lista));
            System.out.println("-----------------------------------------");
         System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<MuebleVO> nuevaLista = dao.getAll();
         System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
           System.out.println("Persona con primary key 1: ");
            System.out.println(dao.findByPk("AA2"));
                 System.out.println("Nº personas borradas " + 
                    dao.deleteMueble(new MuebleVO("BB3", "Mesita de noche", 20.5, 35.30, 30.20)));    
        System.out.println("-----------------------------------------");
            nuevaLista = dao.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar una persona -------------");
            nuevaLista.forEach(System.out::println);
      
    }

}
