/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_gestionproyectos3;

import ORM.Empleado;
import ORM.Proyecto;
import ORM.AsigProy;
import ORM.AsigProyId;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import java.sql.Date;
import java.util.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author pablolopez
 */
public class ORM_GestionProyectos3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Transaction t = null;

        Date fechaOrigen = new Date();//Fecha origien, en funcion a esta fecha, restaremos o sumaremos dias

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            Empleado e1 = new Empleado();
            e1.setDni("54262628Z");
            e1.setNomEmp("BECA");
            s.save(e1);

            Proyecto p1 = new Proyecto();
            p1.setNomProy("EPXILON");
            p1.setFInicio(anadirDiasAFechas(fechaOrigen, -15));
            s.save(p1);

            //Asignacion de proyecto
            AsigProyId asigProyId = new AsigProyId();
            asigProyId.setDniEmp(e1.getDni());
            asigProyId.setIdProy(p1.getIdProy());
            asigProyId.setFInicio(anadirDiasAFechas(fechaOrigen, -5));

            AsigProy asigProy = new AsigProy();
            asigProy.setEmpleado(e1);
            asigProy.setProyecto(p1);
            asigProy.setId(asigProyId);
            asigProy.setFFin(anadirDiasAFechas(fechaOrigen, -1));
            s.save(asigProy);

            t.commit();

            //Date.valueOf("2011-10-01")
            //  s.setDate(++i, this.fInicio, Calendar.getInstance());
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                t = null;
            }
        }
        
        selectTablas();
        proyectosVigentes();
        proyectosNoVigentes();

    }
//ESte metodo suma o resta dias a la fecha actual

    public static Date anadirDiasAFechas(Date fecha, int dias) {
        System.out.println("FechaOrigen " + fecha.toString());
        java.util.Date fechaOrigen = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaOrigen);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static void selectTablas() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query query = s.createQuery("FROM Proyecto").setReadOnly(true);
            List<Proyecto> lProyectos = query.getResultList();
            System.out.println(" TABLA PROYECTO:");
            System.out.println("--------------------");
            System.out.println(" ID    NOMBRE    FechaInicio ");
            System.out.println();
            for (Proyecto item : lProyectos) {
                System.out.println(" " + item.getIdProy() + "    " + item.getNomProy() + "    " + item.getFInicio());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
            
            Query query2 = s.createQuery("FROM Empleado").setReadOnly(true);
            List<Empleado> lEmpleados = query2.getResultList();
            System.out.println(" TABLA EMPLEADOS:");
            System.out.println("--------------------");
            System.out.println(" DNI    NOMBRE ");
            System.out.println();
            for (Empleado item : lEmpleados) {
                System.out.println(" " + item.getDni()+ "    " + item.getNomEmp());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
            
             Query query3 = s.createQuery("FROM AsigProy").setReadOnly(true);
            List<AsigProy> lAsigProy = query3.getResultList();
            System.out.println(" TABLA ASIG_PROY:");
            System.out.println("--------------------");
            System.out.println(" DNI    ID_PROY    FECHAIni     FECHAFin ");
            System.out.println();
            for (AsigProy item : lAsigProy) {
                System.out.println(" " + item.getId().getDniEmp()+ "    " + item.getId().getIdProy()+"    "+item.getId().getFInicio()+"    "+item.getFFin());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
            
        }
    }
    
    public static void proyectosNoVigentes(){
         try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query query3 = s.createQuery("FROM AsigProy WHERE FFin> date(now()) or FFin is null").setReadOnly(true);
            List<AsigProy> lAsigProy = query3.getResultList();
            System.out.println(" TABLA ASIG_PROY NO VIGENTES:");
            System.out.println("--------------------");
            System.out.println(" DNI    ID_PROY    FECHAIni     FECHAFin ");
            System.out.println();
            for (AsigProy item : lAsigProy) {
                System.out.println(" " + item.getId().getDniEmp()+ "    " + item.getId().getIdProy()+"    "+item.getId().getFInicio()+"    "+item.getFFin());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
         }
    }
    
        public static void proyectosVigentes(){
         try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query query3 = s.createQuery("FROM AsigProy WHERE FFin<= date(now())").setReadOnly(true);
            List<AsigProy> lAsigProy = query3.getResultList();
            System.out.println(" TABLA ASIG_PROY VIGENTES:");
            System.out.println("--------------------");
            System.out.println(" DNI    ID_PROY    FECHAIni     FECHAFin ");
            System.out.println();
            for (AsigProy item : lAsigProy) {
                System.out.println(" " + item.getId().getDniEmp()+ "    " + item.getId().getIdProy()+"    "+item.getId().getFInicio()+"    "+item.getFFin());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
         }
    }

}
/**
 * Transaction t = null;
 *
 * try(Session s=HibernateUtil.getSessionFactory().openSession()) {
 *
 * }catch(Exception e) { if(t!=null){ t.rollback(); t=null; } }
 */
