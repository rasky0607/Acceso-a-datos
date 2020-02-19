package c_fichaccesoaleatorio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * (1) Crea un programa que inserte en un fichero varios nombres dados en un
 * array de String, como el siguiente: String[] nombres = { "JUAN", "MARTA",
 * "MANUEL", "LUIS", "ANA" } Cada nombre debe almacenarse ocupando 16 bytes. De
 * esta manera, si después se quiere recuperar, por ejemplo, el tercer nombre,
 * se podrá hacer en la posición 16*(3-1) = 32.
 *
 * (2) Crea una clase FicheroAleatorio que permita obtener y modificar la
 * información de un fichero con la estructura anterior. Se puede utilizar sobre
 * el fichero antes generado. Debe tener los siguientes métodos: - insertar(int
 * pos, String nombre): Inserta el nombre en la posición dada (posición de
 * registro, es decir, si se indica pos=4, se insertará a partir del byte
 * 16*(4-1)=48). Utilizar seek, read. - String leer(int pos): obtiene el nombre
 * en la posición dada. Utilizar seek, write.
 *
 * (3) ¿Qué pasa si se utiliza el método insertar con una posición más allá de
 * la del último registro? Por ejemplo, si hay tres registros y se hace
 * insertar(5, "javier")
 * 
 * RESPUESTA: Se rellena el espacio intermedio con ceros. Se puede ver resultado
 * con hexdump -C
 *       $ hexdump -C mi_fichero.dat 
00000000  4a 55 41 4e 20 20 20 20  20 20 20 20 20 20 20 20  |JUAN            |
00000010  4d 41 52 c3 8d 41 20 20  20 20 20 20 20 20 20 20  |MAR..A          |
00000020  4d 41 4e 55 45 4c 20 20  20 20 20 20 20 20 20 20  |MANUEL          |
00000030  4a 41 56 49 45 52 20 20  20 20 20 20 20 20 20 20  |JAVIER          |
00000040  41 4e 41 20 20 20 20 20  20 20 20 20 20 20 20 20  |ANA             |
00000050  00 00 00 00 00 00 00 00  00 00 00 00 00 00 00 00  |................|
*
00000070  43 45 4c 49 41 20 20 20  20 20 20 20 20 20 20 20  |CELIA           |
00000080
 * 
 * (4) Funciona perfectamente, para escribir texto se codifica en UTF-8, y para
 * leer texto se indica siempre que está en UTF-8.
 */
class FicheroAleatorio {

  String nomFich;
  int longReg;

  FicheroAleatorio(String nomFich, int longReg) {
    this.nomFich = nomFich;
    this.longReg = longReg;
  }

  void insertar(int pos, String nombre) throws IOException {

    try (RandomAccessFile raf = new RandomAccessFile(this.nomFich, "rws")) {
      raf.seek((pos - 1) * this.longReg);
      String nombre1 = nombre;
      for (int i = nombre.length(); i < this.longReg; i++) {
        nombre1 += " ";
      }
      byte[] buff = nombre1.getBytes();
      raf.write(buff, 0, this.longReg);
    }

  }

  String leer(int pos) throws IOException {

    String result;

    try (RandomAccessFile raf = new RandomAccessFile(this.nomFich, "rws")) {

      byte[] buff = new byte[this.longReg];

      raf.seek((pos - 1) * this.longReg);
      raf.read(buff);

      result = new String(buff, 0, this.longReg, "UTF-8");

    }

    return result;

  }

}

public class FichAccesoAleatorio1 {

  static final int LONG_REG = 16;

  public static void main(String[] args) {

    // (1)
    String[] nombres = {"JUAN", "MARTA", "MANUEL", "LUIS", "ANA"};
    RandomAccessFile raf;
    String nomFich = "mi_fichero.dat";
    try {

      raf = new RandomAccessFile(nomFich, "rws");

      for (String nombre : nombres) {

        // Completo nombre hasta LONG_REG caracteres, rellenando con ' ' al final.
        String nombre1 = nombre;
        for (int i = nombre.length(); i < LONG_REG; i++) {
          nombre1 += ' ';
        }

        byte[] buff = nombre1.getBytes("UTF-8");

        raf.write(buff, 0, LONG_REG);

      }

      // (2)
      FicheroAleatorio faa = new FicheroAleatorio(nomFich, LONG_REG);
      faa.insertar(2, "MARÍA");
      faa.insertar(4, "JAVIER");

      int pos = 2;
      String nomRec = faa.leer(pos);
      System.out.printf("Nombre en pos %d: %s.\n", pos, nomRec);
      
      // (3)
      faa.insertar(8, "CELIA");

    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      System.err.println("ERROR de E/S.");
      System.err.println("------------.");
      ex.printStackTrace();
    }

  }

}
