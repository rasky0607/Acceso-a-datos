/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscartexto;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Pablo Lopez Santana
 */
public class BuscarTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
        if (args.length < 2) {
            System.out.println("Indicar por favor nombre de fichero:");
            return;
        }
        //Ruta
        String nomFich = args[0];
        String palabraBuscada= args[1];
        

        //leemos
        try ( BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) {
            int i = 0;
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada
                String[] tmp = linea.split(" ");//Guardamos todas las palabras de la frase en el array
               for(int j=0;j<tmp.length;j++)
               {
                   String esEstaPalabra=tmp[j];
                   //POR AQUIIII
                  if(palabraBuscada.equals(esEstaPalabra))//mas 1 para evitar que cuente desde 0
                       System.out.println("Fila ["+ i+"] Columna ["+(j+1)+"] palabra-> '"+tmp[j]+"'");               
               }
               
                /*System.out.format("[%5d] %s", i++, linea);
                System.out.println();*/
                i++;
                linea = fbr.readLine();
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
