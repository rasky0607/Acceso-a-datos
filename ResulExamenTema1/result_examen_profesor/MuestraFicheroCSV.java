package muestraficherocsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MuestraFicheroCSV {

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("ERROR: Debe especificarse fichero.");
      return;
    }

    String nomFich = args[0];

    try (BufferedReader fr = new BufferedReader(new FileReader(nomFich))) {
      String linea;
      for (int nLin = 1; (linea = fr.readLine()) != null; nLin++) {
        System.out.printf("Línea %d=>", nLin);
        int posDesde = 0;
        boolean fin = false;
        int nCampo = 1;
        while (!fin) {
          int pos = linea.indexOf(',', posDesde);
          if (nCampo > 1) {
            System.out.print(", ");
          }
          if (pos == -1) {  // último campo
            System.out.printf("Campo %d: %s", nCampo++, linea.substring(posDesde));
            fin = true;
          } else {
            System.out.printf("Campo %d: %s", nCampo++, linea.substring(posDesde, pos));
            posDesde = pos + 1;
          }
        }
        System.out.println("");
      }
    } catch (IOException ex) {
      System.err.println("ERROR de E/S");
      ex.printStackTrace();
    }

  }

}
