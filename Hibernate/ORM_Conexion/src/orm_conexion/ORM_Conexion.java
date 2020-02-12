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
            Transaction t2 = null;

            try {
                //t = s.beginTransaction();

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
                t = s.beginTransaction();
                Sede nuevaSede = new ORM.Sede();
                nuevaSede.setNomSede("GRANADA2");
                nuevaSede.setIdSede(1);
                InsertarSede(s, nuevaSede);
                t.commit();

                //Nuevos departamentos
                t = s.beginTransaction();
                Departamento nuevoDepartamento = new Departamento();
                nuevoDepartamento.setNomDepto("RECURSOS HUMANOS");
                nuevoDepartamento.setSede(nuevaSede);
                nuevoDepartamento.setIdDepto(1);
                InsertarDepartamento(s, nuevoDepartamento);
                t.commit();

                //Nuevos empleados
                t = s.beginTransaction();
                Empleado nuevoEmpleado = new Empleado();
                nuevoEmpleado.setDni("12890152P");
                nuevoEmpleado.setNomEmp("MARCOLINO");
                nuevoEmpleado.setDepartamento(nuevoDepartamento);
                InsertarEmpleado(nuevoEmpleado.getDni());
                s.saveOrUpdate(nuevoEmpleado);
                t.commit();

                t = s.beginTransaction();

                Empleado nuevoEmpleado2 = new Empleado();
                nuevoEmpleado2.setDni("12890127L");
                nuevoEmpleado2.setNomEmp("JUAN");
                nuevoEmpleado2.setDepartamento(nuevoDepartamento);
                InsertarEmpleado(nuevoEmpleado2.getDni());
                s.saveOrUpdate(nuevoEmpleado2);               
                t.commit();

                t = s.beginTransaction();

                Empleado nuevoEmpleado3 = new Empleado();
                nuevoEmpleado3.setDni("32490152W");
                nuevoEmpleado3.setNomEmp("KIKE");
                nuevoEmpleado3.setDepartamento(nuevoDepartamento);
                InsertarEmpleado(nuevoEmpleado3.getDni());
                s.saveOrUpdate(nuevoEmpleado3);              
                t.commit();

                t = s.beginTransaction();
                Empleado nuevoEmpleado4 = new Empleado();
                nuevoEmpleado4.setDni("52460148O");
                nuevoEmpleado4.setNomEmp("LOLO");
                nuevoEmpleado4.setDepartamento(nuevoDepartamento);
                InsertarEmpleado(nuevoEmpleado4.getDni());
                s.saveOrUpdate(nuevoEmpleado4);
                t.commit();
                
                t= s.beginTransaction();
                Empleado nuevoEmpleado5 = new Empleado();
                nuevoEmpleado5.setDni("12030143Z");
                nuevoEmpleado5.setNomEmp("NOMBRE");
                nuevoEmpleado5.setDepartamento(nuevoDepartamento);
                InsertarEmpleado(nuevoEmpleado5.getDni());
                s.saveOrUpdate(nuevoEmpleado5);

                t.commit();

                SelectsHQL(s);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                if (t != null) {
                    t.rollback();
                }
            }

        }
    }

    public static void SelectsHQL(Session s) {
        //Consultas Select de HQL
        Query query = s.createQuery("FROM Sede").setReadOnly(true);
        Query query2 = s.createQuery("FROM Departamento").setReadOnly(true);
        Query query3 = s.createQuery("FROM Empleado").setReadOnly(true);

        List<Sede> lSede = query.getResultList();
        List<Departamento> lDepartamentos = query2.getResultList();
        List<Empleado> lEmpleado = query3.getResultList();
        System.out.println("--TABLA SEDE--");
        for (Sede item : lSede) {
            System.out.println("Sede " + item.getNomSede());
        }
        System.out.println("--TABLA DEPARTAMENTO--");
        for (Departamento item : lDepartamentos) {
            System.out.println("Departamento " + item.getNomDepto());
        }
        System.out.println("--TABLA EMPLEADO--");
        for (Empleado item : lEmpleado) {
            System.out.println("DNI empleado " + item.getDni());
            System.out.println("Nombre empleado " + item.getNomEmp());
        }

    }

    public static void InsertarSede(Session s, Sede nuevaSede) {
        //Comprobamos si ya existe una sede con ese nombre
        Query queryComprobacion1 = s.createQuery("FROM Sede WHERE idSede='" + nuevaSede.getIdSede() + "'").setReadOnly(true);
        List<Sede> listComprobSede = queryComprobacion1.getResultList();
        if (listComprobSede.isEmpty()) {
            System.out.println("No existe una Sede con el id " + nuevaSede.getIdSede() + " y nombre " + nuevaSede.getNomSede());
            s.save(nuevaSede);
        } else {
            System.out.println("Ya existe una Sede con el nombre " + nuevaSede.getNomSede());
        }
    }

    public static void InsertarDepartamento(Session s, Departamento nuevoDepartamento) {
        //Comprobamos si existe un departamento con el mismo nombre que el que pretendemos isertar
        Query queryComprobacion = s.createQuery("FROM Departamento WHERE idDepto='" + nuevoDepartamento.getIdDepto() + "'").setReadOnly(true);
        List<Departamento> listComprobDepartamento = queryComprobacion.getResultList();
        if (listComprobDepartamento.isEmpty()) {
            s.save(nuevoDepartamento);
            System.out.println("NO existe un Departamento con id de " + nuevoDepartamento.getIdDepto() + " y nombre " + nuevoDepartamento.getNomDepto());
        } else {
            System.out.println("Ya existe un Departamento con ID  " + nuevoDepartamento.getIdDepto() + " y con nombre " + nuevoDepartamento.getNomDepto());
        }
    }

    public static void InsertarEmpleado(String dni) {
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            //Comprobamos si existe un Empleado con el mismo DNI que el que pretendemos isertar
            Query queryComprobacion = s.createQuery("FROM Empleado WHERE dni='" + dni + "'").setReadOnly(true);
            List<Empleado> listComprobEmple = queryComprobacion.getResultList();
            System.out.println("lista " + listComprobEmple.size());
            if (listComprobEmple.size() < 1) {
                System.out.println("NO existe empleado con DNI " + dni + ", insertando ...");
            } else {
                System.out.println("Actualizando empleado con DNI " + dni);
            }
        }

    }

}
