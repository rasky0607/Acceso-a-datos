/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copiadirectorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import javax.naming.spi.DirectoryManager;

/**
 *
 * @author pablo lopez santana
 */
public class CopiaDirectorio {
    
    static int TAM_FILA = 32;
    //Numero maximo de caracteres en la tabla ASSCII sin pasar alos chinos
    static int MAX_BYTES = 2048;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try{
        String ruta= args[0];
        File fich = new File(ruta);
       
    if(!fich.exists())
         System.out.println("La ruta del direcotrio de origen introducida no existe!.");       
    if(args.length != 2)    
        System.out.println("Debe indicarse dos argumentos:\n1-Ruta directorio\n2-nombre de nuevo directorio");
    else   
        CopiaDirectorio.ObtenerFicheros(args[0],args[1]);
        }catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    
   

    }
    
     //Leemos ficheros del directorio dado y crea el nuevo directorio
    public static void ObtenerFicheros(String ruta,String nombreDirectorio) throws IOException{
    
              //Creamos nuevo direcotrio
         String nuevaRuta=ruta+"/"+nombreDirectorio;
         File fi2 = new File(nuevaRuta);
         fi2.mkdir();
         //Obtenemos ficheros
        File fi = new File(ruta);
        File[] ficheros = fi.listFiles();
        for (int i = 0; i < ficheros.length; i++) {
            if (ficheros[i].isFile()) {
                System.out.println(ficheros[i].getName());
                String nombreFichero = ficheros[i].getName();
                //Copiamos el fichero               
                 CopiaDirectorio.LecturaEscritura(ruta+"/"+nombreFichero, nuevaRuta+"/"+nombreFichero);
             
            }   
            
        }
    }
      
    
      public static void LecturaEscritura(String rutaOrigen,String rutaDestino) throws FileNotFoundException, IOException {

        //Flujo lectura
        try (InputStream lector = new FileInputStream(rutaOrigen)) {

            //BLoque que vamosa leer
            byte buffer[] = new byte[TAM_FILA];

            int bytesLeidos;// numero de caracteres total en el fichero
            int offset = 0;

            //LECTURA
            do {
                bytesLeidos = lector.read(buffer);
                System.out.format("[%5d]", offset);
                for (int i = 0; i < bytesLeidos; i++) {
                    System.out.format(" %2x", buffer[i]);
                }
                //Posicion del puntero
                offset += bytesLeidos;

                System.out.println();
            } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);

         
            //Crear fichero donde volcaremos datos:
            File f = new File(rutaDestino);
            
           
            //ESCRITURA
            try (  OutputStreamWriter escritor = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.US_ASCII)) {
                for (int i = 0; i < buffer.length; i++) {
                    System.out.println(buffer[i]);
                    escritor.write(buffer[i]);
                }
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            
        
     
            
            
        }

    }
    
    

}
