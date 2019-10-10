/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheroascalea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author alumno1920
 */
public class FicheroAscAlea {

    /**
     * @param args the command line arguments
     */
    public final static int TAMANIOREGISTRO = 16;
    public static int pos = 3;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String[] nombres = {"JUAN", "MARTA", "MANUEL", "LUIS", "ANA"};
        File fichero = new File("/home/alumno1920", "fichero.txt");
        RandomAccessFile raf = new RandomAccessFile(fichero, "rws");
        for (int i = 0; i < nombres.length; i++) {

            raf.write(nombres[i].getBytes("UTF-8"));
        }
        raf.close();
        FicheroAscAlea.Insertar(2, "MARCOS");
        FicheroAscAlea.Leer(2, fichero);
        System.out.println();
        FicheroAscAlea.Insertar(2, "TETE");
        System.out.println();
        FicheroAscAlea.Leer(2, fichero);
        System.out.println();

    }

    public static boolean Insertar(int pos, String nombre) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile("/home/alumno1920/prueba2.txt", "rws");
        raf.seek(TAMANIOREGISTRO * (pos - 1));
        raf.write(nombre.getBytes("UTF-8"));
        raf.close();

        return true;

    }

    public static boolean Leer(int pos, File fichero) throws FileNotFoundException, IOException {
        byte buffer[] = new byte[TAMANIOREGISTRO];
        RandomAccessFile raf = new RandomAccessFile("/home/alumno1920/prueba2.txt", "rws");
        raf.seek(TAMANIOREGISTRO * (pos - 1));
        raf.read(buffer);
        String s = null;
        s = new String(buffer, "UTF-8");
        System.out.print(s);
        return true;
    }

}
