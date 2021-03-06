package ORM;
// Generated 13-feb-2020 1:27:10 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Empleado generated by hbm2java
 */
public class Empleado  implements java.io.Serializable {


     private String dni;
     private String nomEmp;
     private Set proyectos = new HashSet(0);

    public Empleado() {
    }

	
    public Empleado(String dni, String nomEmp) {
        this.dni = dni;
        this.nomEmp = nomEmp;
    }
    public Empleado(String dni, String nomEmp, Set proyectos) {
       this.dni = dni;
       this.nomEmp = nomEmp;
       this.proyectos = proyectos;
    }
   
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNomEmp() {
        return this.nomEmp;
    }
    
    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }
    public Set getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Set proyectos) {
        this.proyectos = proyectos;
    }




}


