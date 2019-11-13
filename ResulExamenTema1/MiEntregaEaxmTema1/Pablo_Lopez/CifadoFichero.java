/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifadofichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author pablols
 */
public class CifadoFichero {
char []_sutitutas= new char[5];

    public char[] getSutitutas() {
        return _sutitutas;
    }

    public void setSutitutas(char[] _sutitutas) {
        this._sutitutas = _sutitutas;
    }

    public char[] getSustituidas() {
        return _sustituidas;
    }

    public void setSustituidas(char[] _sustituidas) {
        this._sustituidas = _sustituidas;
    }
    
    
char []_sustituidas=new char[5];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        
    }
    public CifadoFichero(char []sutitutas, char []sustituidas)
    {
        _sutitutas=sutitutas;
        _sustituidas=sustituidas;
    }
    
    public  void Cifrado(CifadoFichero f) {
         String rutaOrigen = "/home/pablols/prueba.txt";
        String rutaDestino = "/home/pablols/dos.txt";
         char caracter=' ';
        
        try (FileReader fr = new FileReader(rutaOrigen)) {
            FileWriter fw = new FileWriter(rutaDestino);
     
           
           while ((caracter=(char)fr.read()) != -1){
               for (int i = 0; i < f._sustituidas.length; i++) {
                   //si son iguales alguna de las pasadas a sustituir alcaracter leido, lo escribimos el caracter sustituto en su lugar
                   if(_sustituidas[i]==caracter)
                   {
                       fw.write(_sutitutas[i]);
                   }
                       
               }
                 
            }
           fr.close();
           fw.close();
                     
           
        } catch (IOException e) {
            System.err.println("Error I/O");
            System.err.println("-------------------");
            e.printStackTrace();
        }
    }
    
}
