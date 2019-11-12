package volcadobinario;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class VolcadoBinario {

    static int TAM_FILA = 32;
    //Numero maximo de caracteres en la tabla ASSCII sin pasar alos chinos
    static int MAX_BYTES = 2048;
    InputStream is = null;

    //Constructor
    public VolcadoBinario(InputStream is) {
        this.is = is;
    }

    
    public void volcar() throws IOException {
        byte buffer[] = new byte[TAM_FILA];
        int bytesLeidos;
        int offset = 0;
        
        do {
            bytesLeidos = is.read(buffer);
            System.out.format("[%5d]", offset);
            for (int i = 0; i < bytesLeidos; i++) {
                System.out.format(" %2x", buffer[i]);
            }
            offset += bytesLeidos;
            System.out.println();
        } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
        
        
        //Crear fichero donde volcaremos datos:
        File f = File.createTempFile("tmp.txt", "");
        try (FileWriter fw = new FileWriter(f))
        {
            for (int i = 0; i < buffer.length; i++) {
                fw.write(buffer[i]);
            }
        } catch (IOException e)
        {
            System.err.println(e.toString());
        }
    }
    

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No se ha indicado ningún fichero");
            return;
        }

        String nomFich = "/home/pablo/ProyectosNetBeans/Acceso-a-datos/Ej1/p.txt";

        try ( FileInputStream fis = new FileInputStream(nomFich)) {
            VolcadoBinario vb = new VolcadoBinario(fis);
          
            
            System.out.println("Volcado binario de " + nomFich);
            vb.volcar();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: no existe fichero " + nomFich);
        } catch (IOException e) {
            System.err.println("ERROR de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
