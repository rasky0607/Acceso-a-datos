/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_conexion;

import org.hibernate.Session;
import org.hibernate.Transaction;
//Añadido
import ORM.Departamento;
import ORM.Empleado;
import ORM.EmpleadoDatosProf;
import ORM.Proyecto;
import ORM.ProyectoSede;
import ORM.ProyectoSedeId;
import ORM.Sede;
import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author Pablo Lopez
 */
public class ORM_Conexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = null;

            try {
                t = s.beginTransaction();

                /*ORM.Sede sede = new ORM.Sede();
                sede.setNomSede("MÁLAGA");
                s.save(sede);             

                ORM.Departamento depto = new ORM.Departamento();
                depto.setNomDepto("INVESTIGACIÓN Y DESARROLLO");
                depto.setSede(sede);
                s.save(depto);                             

                ORM.Empleado emp = new ORM.Empleado();
                emp.setDni("56789012B");
                emp.setNomEmp("SAMPER");
                emp.setDepartamento(depto);
                s.save(emp);*/
                
                 //Nuevas Sede
                Sede nuevaSede =new ORM.Sede();
                nuevaSede.setNomSede("GRANADA2");
                s.save(nuevaSede);
                //Nuevos departamentos
                Departamento nuevoDepartamento = new Departamento();
                nuevoDepartamento.setNomDepto("RECURSOS HUMANOS");
                nuevoDepartamento.setSede(nuevaSede);
                s.save(nuevoDepartamento);
                //Nuevos empleados
                Empleado nuevoEmpleado = new Empleado();
                nuevoEmpleado.setDni("12890152P");
                nuevoEmpleado.setNomEmp("SOLOMILLO2");
                nuevoEmpleado.setDepartamento(nuevoDepartamento);
                s.save(nuevoEmpleado);  
                 Empleado nuevoEmpleado2 = new Empleado();
                nuevoEmpleado2.setDni("12890127L");
                nuevoEmpleado2.setNomEmp("ENTRCOD");
                nuevoEmpleado2.setDepartamento(nuevoDepartamento);
                s.save(nuevoEmpleado2);    
               
                t.commit();
                
                //Consultas de HQL
                Query query=s.createQuery("FROM Sede").setReadOnly(true);
                Query query2=s.createQuery("FROM Departamento").setReadOnly(true);
                Query query3=s.createQuery("FROM Empleado").setReadOnly(true);
                
               List<Sede> lSede= query.getResultList();
               List<Departamento> lDepartamentos= query2.getResultList();
               List<Empleado> lEmpleado= query3.getResultList();
           
             for(Sede item :lSede)
             {
                 System.out.println("Sede "+item.getNomSede());
             }
             
             for(Departamento item :lDepartamentos)
             {
                 System.out.println("Departamento "+item.getNomDepto());
             }
             
             for(Empleado item :lEmpleado)
             {
                  System.out.println("Empleado "+item.getDni());
                 System.out.println("Empleado "+item.getNomEmp());
             }

            } catch (Exception e) {
                e.printStackTrace(System.err);
                if (t != null) {
                    t.rollback();
                }
            }

        }
    }
    
}
