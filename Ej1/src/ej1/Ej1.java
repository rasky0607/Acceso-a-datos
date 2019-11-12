/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
//Añadido
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import javax.print.attribute.DateTimeSyntax;

/**
 *
 * @author alumno1920
 */
public class Ej1 {

    static int TAM_FILA = 32;
    //Numero maximo de caracteres en la tabla ASSCII sin pasar alos chinos
    static int MAX_BYTES = 2048;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String rutaOrigen = "/home/pablo/ProyectosNetBeans/Acceso-a-datos/Ej1/p.txt";
        Ej1.LecturaEscritura(rutaOrigen);

    }

    public static void LecturaEscritura(String rutaOrigen) throws FileNotFoundException, IOException {

        //Flujo lectura
        try (InputStream lector = new FileInputStream(rutaOrigen)) {

            //BLoque que vamosa leer
            byte buffer[] = new byte[TAM_FILA];

            int bytesLeidos;// numero de caracteres total en el fichero
            int offset = 0;

            //LECTURA
            /*Leemos bloques de 32 bytes en 32 bytes y guardamos el total de bytes leidos en bytesLeidos*/
 /*
        //Ejemplo de lectura aclarativo:
        int j = lector.read(buffer);//leo bloques de 32
        while(j!=-1)
        {
            System.out.println("bytes leidos "+j);
            j= lector.read(buffer);//leo bloques de 32
        }*/
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

            Date fecha = Calendar.getInstance().getTime();
            //Crear fichero donde volcaremos datos:
            File f = File.createTempFile("tmp", fecha.toString());
            
            //ESCRITURA
            try ( FileWriter fw = new FileWriter(f)) {
                for (int i = 0; i < buffer.length; i++) {
                    fw.write(buffer[i]);
                }
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            
            //ESCRITURA binaria con ObjectOutputStream
            try ( FileOutputStream fo = new FileOutputStream("/home/pablo/ProyectosNetBeans/Acceso-a-datos/Ej1/oso2.txt")) {
                ObjectOutputStream oos = new ObjectOutputStream(fo);
             
                
                for (int i = 0; i < buffer.length; i++) {
                    
                    System.out.println("Caracter "+buffer[i]);
                    Texto t= new Texto(buffer);
                    oos.writeObject(buffer);
                    
                }
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            
            
        }

    }

}
