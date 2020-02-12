/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_jerarquia;


import ORM.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author alumno1920
 */
public class ORM_Jerarquia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
             Empleado nuevoEmpleado = new Empleado();
         
         }
    }
    
}
