/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_gestionproyextos2;

import ORM.Empleado;
import ORM.Proyecto;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import java.sql.Date;

/**
 *
 * @author pablolopez
 */
public class ORM_GestionProyextos2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here  

           insertarProyecto(insertarEmpleado("98765432H","MARIA"),"VEX", new Date());
           insertarProyecto(insertarEmpleado("12675632X","LOLO"),"MEC", new Date());
           insertarProyecto(insertarEmpleado("82665632T","TANIA"),"ODISEA", new Date());
           insertarProyecto(insertarEmpleado("42372631E","ALEJANDRO"),"DANTE", new Date());
           
           //Date.valueOf("2011-10-01")
             //  s.setDate(++i, this.fInicio, Calendar.getInstance());
    }

    public static Empleado insertarEmpleado(String dni,String nombre) {
        Transaction t = null;
           Empleado e1 = new Empleado();
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t=s.beginTransaction();
            e1.setDni(dni);
            e1.setNomEmp(nombre);
           s.save(e1);
           t.commit();
           return  e1;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                t = null;
            }
        }
        return e1;
    }

    public static void insertarProyecto(Empleado e1,String nombreProyecto, Date fecha) {
        Transaction t = null;
        Proyecto p1 = new Proyecto();
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t=s.beginTransaction();
            p1.setEmpleado(e1);
            //p1.setIdProy(1);//No hace falta indicar el id ya que el atributo del xml es identity por lo que autoincrementa hibernate
            p1.setNomProy(nombreProyecto);
            p1.setFInicio(fecha);         
            s.save(p1);
            
            t.commit();
        } catch (Exception e) {
            if (e1.getDni() == null) {
                System.out.println("ERROR: El proyecto necesita tener asigano un empleado!");               
            }
            if(fecha==null)
                System.out.println("La fecha no puede ser nula");
            if (t != null) {
                t.rollback();
                t = null;
            }
            
        }
    }

}
