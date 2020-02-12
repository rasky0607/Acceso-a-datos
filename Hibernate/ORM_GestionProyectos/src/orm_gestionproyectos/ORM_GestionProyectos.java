/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_gestionproyectos;

import ORM.Departamento;
import ORM.Empleado;
import ORM.Sede;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pablolopez
 */
public class ORM_GestionProyectos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            SelectsHQL(s);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

    }  
      public static void SelectsHQL(Session s) {
        //Consultas Select de HQL
        Query query = s.createQuery("FROM Sede").setReadOnly(true);       
        List<Sede> lSede = query.getResultList();    
        System.out.println("--TABLA SEDE--");
        for (Sede item : lSede) {
            System.out.println("Sede "+item.getIdSede()+" | "+ item.getNomSede());
        }
        
          //Query query2 = s.createQuery("FROM Departamento").setReadOnly(true);
        //Query query3 = s.createQuery("FROM Empleado").setReadOnly(true);
        /*List<Departamento> lDepartamentos = query2.getResultList();
        List<Empleado> lEmpleado = query3.getResultList();*/
        /*System.out.println("--TABLA DEPARTAMENTO--");
        for (Departamento item : lDepartamentos) {
            System.out.println("Departamento " + item.getNomDepto());
        }
        System.out.println("--TABLA EMPLEADO--");
        for (Empleado item : lEmpleado) {
            System.out.println("DNI empleado " + item.getDni());
            System.out.println("Nombre empleado " + item.getNomEmp());
        }*/

    }
}
