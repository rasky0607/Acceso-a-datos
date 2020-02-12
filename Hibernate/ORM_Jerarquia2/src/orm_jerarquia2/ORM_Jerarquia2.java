/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_jerarquia2;
import  ORM.Empleado;
import  ORM.Pantilla;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pablolopez
 */
public class ORM_Jerarquia2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Transaction t = null;
        try(Session s=HibernateUtil.getSessionFactory().openSession()){
            t=s.beginTransaction();
            
            Empleado miEmpleado= new Empleado();
            miEmpleado.setDni("12345678P");
            miEmpleado.setNomEmp("JUAN");
            s.save(miEmpleado);
            System.out.println("FIN");
            
             Empleado miEmpleado2= new Empleado();
            miEmpleado2.setDni("32343674Z");
            miEmpleado2.setNomEmp("MARCOS");
            s.save(miEmpleado2);
            
            Pantilla empleadoPlantilla= new Pantilla();
            empleadoPlantilla.setEmpleado(miEmpleado2);
            empleadoPlantilla.setNumEmpleado(2);
            s.save(empleadoPlantilla);
            System.out.println("FIN");
            
            
            t.commit();
            
        }catch(Exception e){
             System.out.println("ERROR");
            if(t!=null)
                t=null;
        }
        
    }
    
}
