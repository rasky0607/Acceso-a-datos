package ORM;
// Generated 12 feb. 2020 11:37:01 by Hibernate Tools 4.3.1



/**
 * Empleado generated by hbm2java
 */
public class Empleado  implements java.io.Serializable {


     private String dni;
     private String nomEmp;
     //private Integer numEmp; Este campo pasa a  ser de la clase Enplantilla
     //private String tipo; //Eliminado para implementar la jerarquia  en hibernate a traves de una clase que heredara de esta llama Emplantilla

    public Empleado() {
    }

	
    public Empleado(String dni) {
        this.dni = dni;

    }
    /*public Empleado(String dni, String nomEmp, Integer numEmp) {
       this.dni = dni;
       this.nomEmp = nomEmp;
       //this.numEmp = numEmp;
       //this.tipo = tipo;
    }*/
   
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
    /* Esta propiedad pasa a ser de la clase Enplantilla
    public Integer getNumEmp() {
        return this.numEmp;
    }
    
    public void setNumEmp(Integer numEmp) {
        this.numEmp = numEmp;
    }*/
    /*public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }*/




}

