/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadodirectorio;

import java.io.File;

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

        String ruta = ".";
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
                System.out.println(ruta + " es un directorio. Contenidos: ");
                File[] ficheros = fich.listFiles(); // Ojo, ficheros o directorios
                
                String tipo = String.format("%-10s", "Tipo");
                 String nombre = String.format("%-10s", "Nombre");
                  String tamanio = String.format("%-10s", "Tamaño");
                System.out.println("[" + tipo +nombre+tamanio+ "]");
                for (File f : ficheros) 
                {
                    
                    String textoDescr = f.isDirectory() ? "Directorio llamado: "
                            : f.isFile() ? "Es un fichero: " : "?";
                    System.out.println("" + textoDescr + "-> " + f.getName()+" Un espacio total de: "+f.getTotalSpace());
                }
            }

        }

    }
}
