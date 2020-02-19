package a_escribirenfichtempcarrep;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * 
 * Cambios necesarios:
 * 
 * 1) Añadir throws IOException a declaración de método creaFicheroTempConCar().
 *    Este cambio lo sugiere el propio IDE NetBeans.
 * 2) Añadir línea ft.delete().
 * 3) El propio IDE NetBeans sugiere este cambio.
 * 
 */

public class EscribirEnFichTempCarRep {

  public File creaFicheroTempConCar(String prefNomFich, char car, int numRep) throws IOException {
    File f = File.createTempFile(prefNomFich, "");
    try (FileWriter fw = new FileWriter(f)) {
      for (int i = 0; i < numRep; i++) {
        fw.write(car);
      }
    }
    return f;
  }

  public static void main(String[] args) {

    try {
      File ft = new EscribirEnFichTempCarRep().creaFicheroTempConCar("AAAA_", 'A', 20);
      System.out.println("Creado fichero: " + ft.getAbsolutePath());
      ft.delete();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

}
