package ORM;
// Generated 12-feb-2020 19:36:11 by Hibernate Tools 4.3.1



/**
 * Pantilla generated by hbm2java
 */
public class Pantilla  implements java.io.Serializable {


     private int numEmpleado;
     private Empleado empleado;

    public Pantilla() {
    }

	
    public Pantilla(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    public Pantilla(int numEmpleado, Empleado empleado) {
       this.numEmpleado = numEmpleado;
       this.empleado = empleado;
    }
   
    public int getNumEmpleado() {
        return this.numEmpleado;
    }
    
    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }




}


