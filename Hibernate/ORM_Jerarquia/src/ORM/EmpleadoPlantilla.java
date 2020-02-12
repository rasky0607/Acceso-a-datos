/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ORM;

/**
 *
 * @author alumno1920
 */
public class EmpleadoPlantilla extends Empleado implements  java.io.Serializable{
    
    private Integer numEmp;
    
    //Constructor
    public EmpleadoPlantilla(){
    }
    
    public EmpleadoPlantilla(Integer numEmp,String dni){
        super(dni);//Llamada al constructor de la clase padre
        this.numEmp=numEmp;
    }
    
    
     public Integer getNumEmp() {
        return this.numEmp;
    }
    
    public void setNumEmp(Integer numEmp) {
        this.numEmp = numEmp;
    }
    
}
