/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadodirectorio;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Pablo Lopez
 */
public class ListadoDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String ruta = "/home/alumno1920";
        if (args.length >= 1) 
        {
            ruta = args[0];
        }
        File fich = new File(ruta);

        if (!fich.exists()) 
        {
            System.out.println("No existe el fichero o directorio (" + ruta + ").");
        } 
        else {

            if (fich.isFile())
            {
                System.out.println(ruta + " es un fichero.");
            } else 
            {
                System.out.println( "Contenidos de la ruta: \""+ruta+"\"");
                File[] ficheros = fich.listFiles(); // Ojo, ficheros o directorios
                
                String tipo = String.format("%-40s", "Tipo");
                String nombre = String.format("%-40s", "Nombre");
                String tamanio = String.format("%-40s", "Tamaño");
                String modfificacion = String.format("%-80s", "última modificación");
                  
                System.out.println("[" + tipo +nombre+tamanio+modfificacion+ "]");
                for (File f : ficheros) 
                {
                    
                    String textoDescr = f.isDirectory() ? "Directorio:"
                            : f.isFile() ? "Fichero:" : "?";
                  
                    Date d = new Date(f.lastModified());
                    
                    System.out.println("" +  String.format("%-40s", textoDescr)
                            + String.format("%-40s", f.getName())
                            +String.format("%-40s", f.getTotalSpace())
                            +String.format("%-40s",d.toString()));
                   
                }
            }

        }

    }
}
/*for (File f : ficheros) 
                {
                    
                    String textoDescr = f.isDirectory() ? "Directorio llamado:"
                            : f.isFile() ? "Es un fichero:" : "?";
                    System.out.println("" +  String.format("%-10s", textoDescr)
                            + String.format("%-40s", f.getName())+" Espacio total de:"
                            +String.format("%-40s", f.getTotalSpace())
                            +String.format("%-40s",f.length()));
                }*/