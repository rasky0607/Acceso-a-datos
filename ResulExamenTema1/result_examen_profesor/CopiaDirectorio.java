package copiadirectorio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaDirectorio {

  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println("ERROR: indicar directorio origen y directorio destino.");
      return;
    }

    String dirOrigen = args[0], dirDestino = args[1];
    File fDirOrigen = new File(dirOrigen), fDirDestino = new File(dirDestino);

    if (!fDirOrigen.isDirectory()) {
      System.out.printf("ERROR: No existe directorio origen %s.\n", dirOrigen);
      return;
    }
    if (fDirDestino.exists()) {
      System.out.printf("ERROR: Existe directorio o fichero %s.\n", dirDestino);
      return;
    }

    if (!fDirDestino.mkdir()) {
      System.out.printf("ERROR: No se pudo crear directorio %s.\n", dirDestino);
      return;
    }

    File[] contDirOrig = fDirOrigen.listFiles();
    for (File f : contDirOrig) {
      if (!f.isFile()) {
        continue;  // No es un fichero, ignorar
      }
      try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));
              BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(dirDestino+File.separator+f.getName()))) {
        int b;
        while ((b = is.read()) != -1) {
          os.write(b);
        }
      } catch (IOException ex) {
        System.err.println("Error de E/S");
        ex.printStackTrace();
      }
    }

  }

}
