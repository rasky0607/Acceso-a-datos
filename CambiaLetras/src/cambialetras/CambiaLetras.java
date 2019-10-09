/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cambialetras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author pablo
 */
public class CambiaLetras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        if (args.length < 1) {
            System.out.println("Indicar por favor nombre de fichero:");
            return;
        }
        //Ruta
        String nomFich = args[0];
        String letraBuscada = "a";
        String letraSustituta = "e";
        
        String texto = "";

        //leemos
        try ( BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) {
            int i = 0;
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada

                for (int j = 0; j < linea.length(); j++) {
                    char letra[]= new char[1];
                    letra[0]= linea.charAt(j);//linea.charAt(j) caracter leido en el bucle
                    String tmp = new String(letra);//Me fue necesario para hacer el equals
                    if(tmp.equals(letraBuscada))
                    {
                        texto += letraSustituta;
                    } else {
                        texto += linea.charAt(j);
                    }
                }

             /*System.out.format("[%5d] %s", i++, linea);
                System.out.println();*/
                i++;
                linea = fbr.readLine();
            }
            
             //Escribimos el texto en el n uevo fichero:
                //Crear fichero donde volcaremos datos:
                File f = File.createTempFile("tmpDePrueba.txt", "");
                try ( FileWriter fw = new FileWriter(f)) {                 
                        fw.write(texto);
                    
                } catch (IOException e) {
                    System.err.println(e.toString());
                }
            
        } catch (FileNotFoundException e) {
            System.out.println("No existe fichero " + nomFich);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
