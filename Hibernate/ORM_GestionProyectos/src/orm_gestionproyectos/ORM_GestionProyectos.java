/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_gestionproyectos;

import ORM.Departamento;
import ORM.Empleado;
import ORM.Proyecto;
import ORM.Sede;
import  ORM.ProyectoSede;
import ORM.JefeProyecto;
import ORM.JefeProyectoId;
import java.util.Date;
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
            t=s.beginTransaction();
            //Creamos la sede
            Sede s1 = new Sede();
            s1.setIdSede(1);
            s1.setNomSede("GRANADA");
            s.save(s1);
            //Creamos el departamento
            Departamento d1 = new Departamento();
            d1.setIdDepto(1);
            d1.setNomDepto("INFORMATICA");         
            d1.setSede(s1);
            s.save(d1);
            //Creamos el proyecto
            Proyecto p1 = new Proyecto();
            p1.setIdProy(1);
            p1.setNomProy("ODISEA");
            p1.setFInicio(new Date());
            s.save(p1);
            //Creamos el empleado
            Empleado e1= new Empleado();
            e1.setDni("12345678L");
            e1.setNomEmp("PABLO");
            e1.setDepartamento(d1);
            s.save(e1);
            //Asignamos un empleado a un proyecto como jefe, e indicamos desde cuando es jefe de ese proyecto
            JefeProyectoId jefeProyectoId = new JefeProyectoId(); //Esta clase es para la clave compuesta de la tabla JefeProyecto (dni,idProyecto)
            jefeProyectoId.setDni(e1.getDni());
            jefeProyectoId.setIdProy(p1.getIdProy());
            JefeProyecto j = new JefeProyecto();//Esta otra clase es para el resto de campos de la tabla JefeProyecto
            j.setId(jefeProyectoId);
           
           j.setEmpleado(e1);
           j.setProyecto(p1);
           j.setFInicio(new Date());
           s.save(j);
            
            
            
            t.commit();
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
