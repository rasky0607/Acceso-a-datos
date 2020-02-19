package RecodificacionFicheroTexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RecodificacionFicheroTexto {

    public static void main(String[] args) {
       
        if (args.length < 1) {
            System.out.println("Indicar por favor nombre de fichero.");
            return;
        }
        String nomFich = args[0];
        File f = new File(nomFich);
        if (!f.exists() || !f.isFile()) {
            System.out.println("No existe fichero " + nomFich);
            return;
        }
        
        String nomFich8859_1=nomFich+".8859_1.txt";
        String nomFichUTF16=nomFich+".utf16.txt";

        try (
                BufferedReader fibr = new BufferedReader(new FileReader(f));
                BufferedWriter fobsw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomFich8859_1), "ISO-8859-1"));
                BufferedWriter fobsw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomFichUTF16), "UTF-16"));
                ) {
            String linea = fibr.readLine();
            while (linea != null) {
                fobsw1.write(linea);
                fobsw1.newLine();
                fobsw2.write(linea);
                fobsw2.newLine();
                linea = fibr.readLine();
            }
            System.out.println("Leido "+nomFich+", generados "+nomFich8859_1+" y "+nomFichUTF16);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
