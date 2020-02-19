package listadodirectoriomasinfo;

import java.io.File;
import java.text.SimpleDateFormat;

public class ListadoDirectorioMasInfo {
  
    private static void muestra_info(File f) {
      if(f.isFile()) {
        System.out.print(f.length()+" bytes,");
        }
      System.out.print(
              (f.canRead() ? "r" : "-" )+
              (f.canWrite() ? "w" : "-" )+
              (f.canExecute() ? "x" : "-" )+", "
               );
      SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      System.out.print(sdf.format(f.lastModified()));
    }
  
    public static void main(String[] args) {        
        String ruta=".";
        if(args.length>=1) ruta=args[0];
        File fich=new File(ruta);        
        if(!fich.exists()) {
            System.out.println("No existe el fichero o directorio ("+ruta+").");
        }
        else {
            if(fich.isFile()) {
                System.out.println(ruta+" es un fichero.");
                muestra_info(fich);
            }
            else {
                System.out.println(ruta+" es un directorio. Contenidos: ");
                File[] ficheros=fich.listFiles(); // Ojo, ficheros o directorios
                for(File f : ficheros) {
                    String textoDescr=f.isDirectory() ? "/" :
                            f.isFile() ? "_" : "?";
                    System.out.print("("+textoDescr+") "+f.getName());
                    System.out.print(" [");
                    muestra_info(f);
                    System.out.println("]");
                }
            }
        }
    }
}