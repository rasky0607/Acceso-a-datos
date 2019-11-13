package cifradofichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CifradoFichero {

  char[] carOrigen, carDestino;

  public CifradoFichero(char[] carOrigen, char[] carDestino) {
    this.carOrigen = carOrigen;
    this.carDestino = carDestino;
  }

  /**
   * Método base, crea nuevo fichero resultado de sustituir unos caracteres por
   * otros en el fichero existente.
   *
   * @param c1: caracteres origen
   * @param c2: caracteres destino
   * @param nomFich: fichero existente
   * @param nomNuevoFich: fichero a crear
   * @throws FileNotFoundException
   * @throws IOException
   */
  private void sustituir(char[] c1, char[] c2, String nomFich, String nomNuevoFich) throws FileNotFoundException, IOException {

    try (BufferedReader fr = new BufferedReader(new FileReader(nomFich));
            BufferedWriter fw = new BufferedWriter(new FileWriter(nomNuevoFich))) {
      int carOrigen, carDestino;
      while ((carOrigen = fr.read()) != -1) {
        carDestino = carOrigen; // por defecto
        int i = 0;
        for (char c : c1) {
          if (c == carOrigen) {
            carDestino = c2[i];
            break;
          }
          i++;
        }
        fw.write(carDestino);
      }
    }

  }

  public void cifrar(String nomFich, boolean reempFich, String nomNuevoFich) throws IOException {
    // El fichero cifrado se crea como fichero temporal si hay que reemplazar fichero
    File nuevoFich = reempFich ? File.createTempFile(nomFich, "") : new File(nomNuevoFich);
    sustituir(this.carOrigen, this.carDestino, nomFich, nuevoFich.getAbsolutePath());
    if (reempFich) {
      Files.copy(Path.of(nuevoFich.getAbsolutePath()), Path.of(nomFich), REPLACE_EXISTING);
    }
  }

  public void descifrar(String nomFich, String nomNuevoFich) throws IOException {
    sustituir(this.carDestino, this.carOrigen, nomFich, nomNuevoFich);
  }

  public static void main(String[] args) {
    char[] carOrigen =  {' ', 'T', 'c', 'e', 'o', 'r', 's', 't', 'x'};
    char[] carDestino = {'y', '+', 'e', 'c', 'q', ' ', 'p', 'm', 'w'};
    try {
      CifradoFichero cf = new CifradoFichero(carOrigen, carDestino);
      cf.cifrar("fichero.txt", false, "fichero.txt.enc");
      cf.descifrar("fichero.txt.enc", "fichero_desenc.txt");
      cf.cifrar("fichero.txt", true, null);
    } catch (FileNotFoundException ex) {
      System.err.printf("ERROR: fichero no existe");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.err.println("Error de E/S");
      ex.printStackTrace();
    }
  }

}
