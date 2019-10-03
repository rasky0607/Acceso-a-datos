/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherotemp;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo López Santana
 */
public class FicheroTemp {

    /**
     * @param args the command line arguments
     * @@literal Cambiamos el metodo creaFicheroTempConCar a estatico,
     * al  hacer esto,ya no necesitamos crear un objeto  en el MAIN, por lo que eliminamos "new"
     */
    public static void main(String[] args) {
        
        File ft = new File("");       
        try {
            // TODO code application logic here           
             ft = FicheroTemp.creaFicheroTempConCar("AAAA_", 'A', 20);         
            System.out.println("Creado fichero: " + ft.getAbsolutePath());
           
            
        } catch (IOException ex) {
            Logger.getLogger(FicheroTemp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ft.delete();
            System.out.println("borrado fichero: " + ft.getAbsolutePath());
        }

}


  public static File creaFicheroTempConCar(String prefNomFich, char car, int numRep) throws IOException  {
    File f = File.createTempFile(prefNomFich, "");
    FileWriter fw = new FileWriter(f);
    for (int i = 0; i < numRep; i++) fw.write(car);
    fw.close();
    return f;
  }
}

   

    
