package ORM;
// Generated 13-feb-2020 9:48:24 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AsigProy generated by hbm2java
 */
public class AsigProy  implements java.io.Serializable {


     private AsigProyId id;
     private Empleado empleado;
     private Proyecto proyecto;
     private Date FFin;

    public AsigProy() {
    }

	
    public AsigProy(AsigProyId id, Empleado empleado, Proyecto proyecto) {
        this.id = id;
        this.empleado = empleado;
        this.proyecto = proyecto;
    }
    public AsigProy(AsigProyId id, Empleado empleado, Proyecto proyecto, Date FFin) {
       this.id = id;
       this.empleado = empleado;
       this.proyecto = proyecto;
       this.FFin = FFin;
    }
   
    public AsigProyId getId() {
        return this.id;
    }
    
    public void setId(AsigProyId id) {
        this.id = id;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public Date getFFin() {
        return this.FFin;
    }
    
    public void setFFin(Date FFin) {
        this.FFin = FFin;
    }




}

