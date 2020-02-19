/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VolcadoBinarioAPrintStream;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;

/**
 *
 * @author carlos
 */
public class VolcadoBinarioAPrintStream {

  static int TAM_FILA = 32;
  static int MAX_BYTES = 2048;
  InputStream is = null;

  public VolcadoBinarioAPrintStream(InputStream is) {
    this.is = is;
  }

  public void volcar(PrintStream ps) throws IOException {
    byte buffer[] = new byte[TAM_FILA];
    int bytesLeidos;
    int offset = 0;
    do {
      bytesLeidos = is.read(buffer);
      ps.format("[%5d]", offset);
      for (int i = 0; i < bytesLeidos; i++) {
        ps.format(" %2x", buffer[i]);
      }
      offset += bytesLeidos;
      ps.println();
    } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("No se ha indicado ningún fichero");
      return;
    }

    String nomFich = args[0];

    try (FileInputStream fis = new FileInputStream(nomFich)) {
      VolcadoBinarioAPrintStream vb = new VolcadoBinarioAPrintStream(fis);
          // Se genera fichero con nombre derivado del nombre del fichero original,
          // y en directorio actual. El fichero original puede estar en un directorio
          // para el que no se tengan permisos de escritura.
        String fichVolcado = (new File(nomFich).getName())+".dump.txt";
        vb.volcar(new PrintStream(fichVolcado));
        System.out.println("Volcado de "+nomFich+" a "+fichVolcado);
    } catch (FileNotFoundException e) {
      System.err.println("ERROR, no existe fichero: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("ERROR de E/S: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
