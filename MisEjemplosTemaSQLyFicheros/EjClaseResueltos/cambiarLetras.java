/*
 * Programa que lea un fichero de texto y genere otro igual, pero cambiando la
 * letra a por la e, y la e por la a.
 *
 * Probar fichero generado con: cat fichero.txt.salida.txt | tr 'ae' 'ea'
 * O bien comparar ambos ficheros con un comparador. Se aconseja meld.
 *
 */
package cambialetras;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CambiaLetras {

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Indique fichero de texto.");
      return;
    }

    String nomFich = args[0];

    try (FileReader fr = new FileReader(nomFich);
            FileWriter fw = new FileWriter(nomFich + ".salida.txt")) {
      int carEnt = fr.read();  // Líneas alternativas a esta marcadas con [ALT]
      while(carEnt != -1) {
      // Alternativa:      while ((carEnt = fr.read()) != -1) {, y quitar fr.read() de antes y de fin del bucle
      int carSal;
      switch (carEnt) {
        case 'a':
          carSal = 'e';
          break;
        case 'e':
          carSal = 'a';
          break;
        default:
          carSal = carEnt;
      }
      fw.write(carSal);
      carEnt = fr.read();
    }
  }
  catch (IOException ex

  
    ) {
      ex.printStackTrace();
  }

}

}