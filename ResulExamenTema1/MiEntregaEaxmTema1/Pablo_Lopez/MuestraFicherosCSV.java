/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestraficheroscsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author pablo lopez Santana
 */
public class MuestraFicherosCSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String rutaOrigen = "/home/pablols/prueba.txt";
        String rutaDestino = "/home/pablols/mio.txt";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaOrigen))) {
            
            File file = new File(rutaDestino);           
            OutputStreamWriter escritor = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            String line;
            int contador = 1;
            while ((line = lector.readLine()) != null) {

                String array[] = line.split(",");
                for (int i = 0; i < array.length; i++) {
                    String palabra = array[i];
                    //Si es la ultima linea para evitar escribirla,
                    if (i == array.length - 1) {
                        escritor.write("Linea => " + contador + " campo" + contador + ":" + palabra+"\n");
                    } else {
                        escritor.write("Linea => " + contador + " campo" + contador + ":" + palabra + ",");
                    }
                }                
                contador++;

            }
            escritor.close();

        }

    }
}
