package ficheroaccesoaleatorio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

class FicheroAccesoAleatorio {

  public static final int LONG_REG = 20;
  String nomFich;

  public FicheroAccesoAleatorio(String nomFich) {
    this.nomFich = nomFich;
  }

  public void insertar(String nombre, int pos) throws FileNotFoundException, IOException {
    for (int i = nombre.length(); i < 20; i++) {  //
      nombre += ' ';
    }
    try (RandomAccessFile raf = new RandomAccessFile(this.nomFich, "rws")) {
      raf.seek((pos - 1) * LONG_REG);
      raf.write(nombre.getBytes("UTF-8"), 0, LONG_REG);
    }
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("ERROR: Debe especificarse nombre fichero CSV con datos y nombre fichero aleatorio a generar.");
      return;
    }

    String nomFichCSV = args[0];
    String nomFichDatos = args[1];
    

    try (BufferedReader fr = new BufferedReader(new FileReader(nomFichCSV))) {
      FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio(nomFichDatos);
      String linea;
      int numLin = 0;
      while ((linea = fr.readLine()) != null) {
        int posComa = linea.indexOf(',');
        if (posComa == -1) {
          System.err.printf("ERROR: en línea %d, no hay coma", numLin);
        }
        String nombre = linea.substring(0, posComa);
        int num = Integer.parseInt(linea.substring(posComa + 1));
        faa.insertar(nombre, num);
        numLin++;
      }
    } catch (IOException ex) {
      System.err.println("ERROR de E/S");
      ex.printStackTrace();
    }
  }
  
}
