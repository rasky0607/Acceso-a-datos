/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author alumno1920
 */
public class Ej1 {

 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       
        File ficheroOrigen= new File("/home/alumno1920/NetBeansProjects/Acceso-a-datos/Ej1/p.txt");
        File ficheroDestino = new File("/home/alumno1920/NetBeansProjects/Acceso-a-datos/Ej1/p2.txt");
        Ej1.LecturaEscritura(ficheroOrigen,ficheroDestino);
        

      
    }
    
    public static void LecturaEscritura(File ficheroOrigen,File ficheroDestino) throws FileNotFoundException, IOException
    {
        //ImputStreamReader para leer byte a byte
        InputStreamReader isr = new InputStreamReader(System.in);
        //lectura
        
        BufferedReader br = new BufferedReader(new FileReader(ficheroOrigen));
          int lectura;
          
            //Escritura
        BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroDestino));
        
        //Lee-Escribe
        while ((lectura=br.read())!=-1) {    
            char c= (char)lectura;           
            System.out.println(c);
             bw.write(lectura);
             
             
        }   
      
        
        br.close();
        bw.close();
      
       
    }

   
    
 
    
}

