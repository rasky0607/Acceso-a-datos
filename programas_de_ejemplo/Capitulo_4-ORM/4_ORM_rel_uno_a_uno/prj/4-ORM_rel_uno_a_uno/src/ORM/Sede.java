package ORM;
// Generated 02-ago-2018 18:44:56 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Sede generated by hbm2java
 */
public class Sede  implements java.io.Serializable {


     private Integer idSede;
     private String nomSede;
     private Set departamentos = new HashSet(0);
     private Set proyectoSedes = new HashSet(0);

    public Sede() {
    }

	
    public Sede(String nomSede) {
        this.nomSede = nomSede;
    }
    public Sede(String nomSede, Set departamentos, Set proyectoSedes) {
       this.nomSede = nomSede;
       this.departamentos = departamentos;
       this.proyectoSedes = proyectoSedes;
    }
   
    public Integer getIdSede() {
        return this.idSede;
    }
    
    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }
    public String getNomSede() {
        return this.nomSede;
    }
    
    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }
    public Set getDepartamentos() {
        return this.departamentos;
    }
    
    public void setDepartamentos(Set departamentos) {
        this.departamentos = departamentos;
    }
    public Set getProyectoSedes() {
        return this.proyectoSedes;
    }
    
    public void setProyectoSedes(Set proyectoSedes) {
        this.proyectoSedes = proyectoSedes;
    }




}


