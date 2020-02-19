package buscatextoenfichero;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BuscaTextoEnFichero {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Indicar por favor nombre de fichero y texto a buscar");
      return;
    }
    String nomFich = args[0], texto = args[1];

    try (BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) {
      System.out.println("Buscando "+texto+ " en "+nomFich);
      int numLin = 1, numLin0 = 0;
      String linea = fbr.readLine();
      while (linea != null) {  // Busca ocurrencias en la línea
        int numCol = 0;
        while(numCol >= 0) {
          numCol = linea.indexOf(texto, numCol);
          if(numCol >= 0) {
            if(numLin != numLin0) {  // Se ha encontrado en nueva línea, se muestra
              System.out.println();
              System.out.println("Línea " + numLin + " :" + linea);
              System.out.print("Columna: ");
              numLin0 = numLin;
            }
            System.out.print((numCol+1) +  " ");
            numCol += texto.length();
          }
        }
        linea = fbr.readLine();
        numLin++;
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