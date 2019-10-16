/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheroaccesoaleatorio;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import javafx.util.Pair;


/**
 *
 * @author alumno1920
 */
public class FicheroAccesoAleatorio {
private final File f;
    private final List<Pair<String, Integer>> campos;
    private long longReg;
    private long numReg = 0;

    FicheroAccesoAleatorio(String nomFich, List<Pair<String, Integer>> campos) throws IOException {
        this.campos = campos;
        this.f = new File(nomFich);
        longReg = 0;
        
      
        for (Pair<String, Integer> campo : campos) {
            this.longReg += campo.getValue();
        }
        if (f.exists()) {
            this.numReg = f.length() / this.longReg;
        }
    }

    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg++);
    }

    public void insertar(Map<String, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);
            for (Pair<String, Integer> campo : this.campos) {
                String nomCampo = campo.getKey();
                Integer longCampo = campo.getValue();
                String valorCampo = reg.get(nomCampo);
                if (valorCampo == null) {
                    valorCampo = "";
                }
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }
    }

    public static void main(String[] args) {

        List campos = new ArrayList();
        campos.add(new Pair("NUMERO", 6));
        campos.add(new Pair("DNI", 9));
        campos.add(new Pair("NOMBRE", 32));
        

        try {
            FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("fic_acceso_aleat.dat", campos);
            Map reg = new HashMap();
            //Añadir al final (creo)
            reg.put("NUMERO", "29730");
            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "SAMPER");        
            faa.insertar(reg);
            reg.clear();
            reg.put("NUMERO", "29731");
            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            faa.insertar(reg);
            reg.clear();
            reg.put("NUMERO", "29732");
            reg.put("DNI", "23456789D");
            reg.put("NOMBRE", "DORCE");
            //reg.put("CP", "13700");
            faa.insertar(reg);
            reg.clear();
            reg.put("NUMERO", "29733");
            reg.put("DNI", "78901234X");
            reg.put("NOMBRE", "NADALES");
            //reg.put("CP", "44126");
            //faa.insertar(reg, 1);
            faa.insertar(reg);
//      faa.insertar(reg,25);  // Probarlo, interesante
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
