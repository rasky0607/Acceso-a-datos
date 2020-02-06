package ORM;
// Generated 15-sep-2018 20:58:27 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProyectoSede generated by hbm2java
 */
@Entity
@Table(name="proyecto_sede"
    ,catalog="proyecto_orm"
)
public class ProyectoSede  implements java.io.Serializable {


     private ProyectoSedeId id;
     private Proyecto proyecto;
     private Sede sede;
     private Date FInicio;
     private Date FFin;

    public ProyectoSede() {
    }

	
    public ProyectoSede(ProyectoSedeId id, Proyecto proyecto, Sede sede, Date FInicio) {
        this.id = id;
        this.proyecto = proyecto;
        this.sede = sede;
        this.FInicio = FInicio;
    }
    public ProyectoSede(ProyectoSedeId id, Proyecto proyecto, Sede sede, Date FInicio, Date FFin) {
       this.id = id;
       this.proyecto = proyecto;
       this.sede = sede;
       this.FInicio = FInicio;
       this.FFin = FFin;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idProy", column=@Column(name="id_proy", nullable=false) ), 
        @AttributeOverride(name="idSede", column=@Column(name="id_sede", nullable=false) ) } )
    public ProyectoSedeId getId() {
        return this.id;
    }
    
    public void setId(ProyectoSedeId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_proy", nullable=false, insertable=false, updatable=false)
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_sede", nullable=false, insertable=false, updatable=false)
    public Sede getSede() {
        return this.sede;
    }
    
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="f_inicio", nullable=false, length=10)
    public Date getFInicio() {
        return this.FInicio;
    }
    
    public void setFInicio(Date FInicio) {
        this.FInicio = FInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="f_fin", length=10)
    public Date getFFin() {
        return this.FFin;
    }
    
    public void setFFin(Date FFin) {
        this.FFin = FFin;
    }




}

