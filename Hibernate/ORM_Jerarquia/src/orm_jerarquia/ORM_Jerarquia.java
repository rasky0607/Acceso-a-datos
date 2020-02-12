/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_jerarquia;

import ORM.Empleado;
import ORM.EmpleadoPlantilla;
import java.util.List;
import javax.persistence.Query;
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
        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            t = s.beginTransaction();
           EmpleadoPlantilla empleadoPlantilla= new EmpleadoPlantilla();
           empleadoPlantilla.setDni("12432433G");
           empleadoPlantilla.setNomEmp("MARCO POLO");
           empleadoPlantilla.setNumEmp(Integer.valueOf(4));
           s.saveOrUpdate(empleadoPlantilla);
            /*Empleado nuevoEmpleado = new Empleado();
                nuevoEmpleado.setDni("12890152P");
                nuevoEmpleado.setNomEmp("PACO PALOTES");
                s.save(nuevoEmpleado);*/
                
                
                
     

            t.commit();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

    }
}
