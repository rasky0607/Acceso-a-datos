package ORM;
// Generated 13-feb-2020 0:49:55 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * JefeProyecto generated by hbm2java
 */
public class JefeProyecto  implements java.io.Serializable {


     private JefeProyectoId id;
     private Empleado empleado;
     private Proyecto proyecto;
     private Date FInicio;
     private Date FFin;

    public JefeProyecto() {
    }

	
    public JefeProyecto(JefeProyectoId id, Empleado empleado, Proyecto proyecto, Date FInicio) {
        this.id = id;
        this.empleado = empleado;
        this.proyecto = proyecto;
        this.FInicio = FInicio;
    }
    public JefeProyecto(JefeProyectoId id, Empleado empleado, Proyecto proyecto, Date FInicio, Date FFin) {
       this.id = id;
       this.empleado = empleado;
       this.proyecto = proyecto;
       this.FInicio = FInicio;
       this.FFin = FFin;
    }
     public JefeProyecto( Empleado empleado, Proyecto proyecto, Date FInicio, Date FFin) {      
       this.empleado = empleado;
       this.proyecto = proyecto;
       this.FInicio = FInicio;
       this.FFin = FFin;
    }
   
    public JefeProyectoId getId() {
        return this.id;
    }
    
    public void setId(JefeProyectoId id) {
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
    public Date getFInicio() {
        return this.FInicio;
    }
    
    public void setFInicio(Date FInicio) {
        this.FInicio = FInicio;
    }
    public Date getFFin() {
        return this.FFin;
    }
    
    public void setFFin(Date FFin) {
        this.FFin = FFin;
    }




}


