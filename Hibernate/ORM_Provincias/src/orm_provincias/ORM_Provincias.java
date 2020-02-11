/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_provincias;

import ORM.Comunidad;
import  ORM.Localidad;
import  ORM.Provincia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;


/**
 *
 * @author pablolopez
 */
public class ORM_Provincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String rutaComunidades = "./comunidades.csv";
        String rutaProvincias = "./provincias.csv";
        String rutaLocalidades = "./localidades.csv";
      
        Comunidad c = new Comunidad();
        Provincia p = new Provincia();
        Localidad l = new Localidad();
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            //Comunidaddes
            leerFicheroComunidades(s, rutaComunidades, c);
            selectTabla(s, c);
            //Provincias
            leerFicheroProvincias(s, rutaProvincias, p);
            selectTabla(s, p);
            //Localidades
            leerFicheroLocalidades(s, rutaLocalidades, l);
            selectTabla(s, l);
            
        }

    }
 //Las comunidades contines provincias y estas a su vez contienen localidades
    //Metodo que dependiento del tipo de objeto pasado por parametro, listara una tabla u otra
    public static void selectTabla(Session s, Object objetoDeLista) {
        
        if (objetoDeLista.getClass().equals(Comunidad.class)) {
            //Consultas de HQL
            Query query = s.createQuery("FROM Comunidad").setReadOnly(true);
            List<Comunidad> lComunidad = query.getResultList();
            System.out.println(" TABLA COMUNIDAD:");
            System.out.println("--------------------");
            System.out.println(" ID    NOMBRE ");
            System.out.println();
            for (Comunidad item : lComunidad) {
                System.out.println(" " + item.getIdCom() + "    " + item.getNomCom());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
        }

        if (objetoDeLista.getClass().equals(Localidad.class)) {
            //Consultas de HQL
            Query query = s.createQuery("FROM Localidad").setReadOnly(true);
            List<Localidad> lLocalidad = query.getResultList();
            System.out.println(" TABLA LOCALIDAD:");
            System.out.println("--------------------");
            System.out.println(" ID_LOCALIDAD    NOMBRE    ID_PROVINCIA ");
            System.out.println();
            for (Localidad item : lLocalidad) {
                System.out.println(" " + item.getIdLoc()+ "    " + item.getNomLoc()+ "    " +item.getProvincia().getIdProv());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");;
        }

        if (objetoDeLista.getClass().equals(ORM.Provincia.class)) {
            //Consultas de HQL
            Query query = s.createQuery("FROM Provincia").setReadOnly(true);
            List<Provincia> lProvincias = query.getResultList();
            System.out.println(" TABLA PROVINCIA:");
            System.out.println("--------------------");
            System.out.println(" ID_PROVINCIA    NOMBRE    ID_COMUNIDAD");
            System.out.println();
            for (Provincia item : lProvincias) {
                System.out.println(" " + item.getIdProv() + "    " + item.getNomProv()+ "    " +item.getComunidad().getIdCom());
                System.out.println("------------------");
            }
            System.out.println("--------Fin de tabla--------");
        }
        //ORM.Comunidad

    }

    //org.hibernate.exception.ConstraintViolationException
    //Comunidades
    public static void leerFicheroComunidades(Session s, String ruta, Comunidad c) {
        Transaction t = null;
        int i = 0;
        try (BufferedReader fbr = new BufferedReader(new FileReader(ruta))) {
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada
                String[] tmp = linea.split(",");//Guardamos todas las palabras de la linea del fichero en el array         
                i++;
                linea = fbr.readLine();
                //Comprobamos que no encontramos un registro en la BD con el id que hemos leido
                Query query = s.createQuery("FROM Comunidad WHERE idCom='"+tmp[0]+"'").setReadOnly(true);
                List<Comunidad> list = query.getResultList();//Si esta lista  esta vacia, quiere decir que no hay ningun registro en la BD con ese ID y puede ser insertado
                if(list.isEmpty()){
                   System.out.println("Insertando comunidad con id "+tmp[0]);
               
                
                //Iniciamos transaccion              
                t = s.beginTransaction();
                c= new Comunidad();
                c.setIdCom(Integer.parseInt(tmp[0]));
                c.setNomCom(tmp[1]);
                s.save(c);
                t.commit();
                System.out.println(i + "º Insercion en Comunidad exitosa.!");
                } else
                    System.out.println("ERROR en la "+i+"º insercion Ya hay una comunidad registrada con el id "+tmp[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe fichero " + ruta);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }catch(ConstraintViolationException e){
            System.out.println("Error en " + i + "º insercion en Comunidad Violacion de clave");
            if (t != null) {
                t.rollback();
            }
        }catch (Exception e) {
            e.getMessage();
            System.out.println("Error en " + i + "º insercion por algun otro error");
            if (t != null) {
                t.rollback();
            }
        }
    }
   
    public static void leerFicheroProvincias(Session s, String ruta, Provincia p) {
        Transaction t = null;
        int i = 0;
        try (BufferedReader fbr = new BufferedReader(new FileReader(ruta))) {
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada
                String[] tmp = linea.split(",");//Guardamos todas las palabras de la linea del fichero en el array         
                i++;
                linea = fbr.readLine();
                 //Comprobamos que no encontramos un registro en la BD con el id que hemos leido
                Query query = s.createQuery("FROM Provincia WHERE idProv='"+tmp[1]+"'").setReadOnly(true);
                List<Comunidad> list = query.getResultList();//Si esta lista  esta vacia, quiere decir que no hay ningun registro en la BD con ese ID y puede ser insertado
                if(list.isEmpty()){
                   System.out.println("Insertando Provincia con id "+tmp[1]);
                   
                //Iniciamos transaccion              
                t = s.beginTransaction();
                p= new Provincia();
                p.setIdProv(Integer.parseInt(tmp[1]));
                p.setNomProv(tmp[2]);
                Comunidad c = new Comunidad();
                c.setIdCom(Integer.parseInt(tmp[0]));//Ya que en el fichero de "Provincias" primero se lee el codigo de la comunidad y a continuacion de la provincia
                p.setComunidad(c);            
                s.save(p);
                t.commit();
                System.out.println(i + "º Insercion en Provincia exitosa.!");
                }else
                    System.out.println("ERROR en la "+i+"º insercion Ya hay una Provincia registrada con el id "+tmp[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe fichero " + ruta);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en " + i + "º insercion en Provincia por clave primaria");
            if (t != null) {
                t.rollback();
            }
        }
    }
    
    public static void leerFicheroLocalidades(Session s, String ruta, Localidad l) {
        Transaction t = null;
        int i = 0;
        try (BufferedReader fbr = new BufferedReader(new FileReader(ruta))) {
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada
                String[] tmp = linea.split(",");//Guardamos todas las palabras de la linea del fichero en el array         
                i++;
                linea = fbr.readLine();
                
                 //Comprobamos que no encontramos un registro en la BD con el id que hemos leido
                Query query = s.createQuery("FROM Localidad WHERE idLoc='"+i+"'").setReadOnly(true);
                List<Comunidad> list = query.getResultList();//Si esta lista  esta vacia, quiere decir que no hay ningun registro en la BD con ese ID y puede ser insertado
                if(list.isEmpty()){
                   System.out.println("Insertando Localidad con id "+i);
                   
                //Iniciamos transaccion              
                t = s.beginTransaction();
                l= new Localidad();
                l.setIdLoc(i);// i ya que el id es dado por nosotros no por el csv
                l.setNomLoc(tmp[1]);
                Provincia p = new Provincia();
                p.setIdProv(Integer.parseInt(tmp[0]));//Ya que en el fichero de "Localidades" primero se lee el codigo de la Provincia y a continuacion el nombre de la localidad
                l.setProvincia(p);            
                s.save(l);
                
                t.commit();
                System.out.println(i + "º Insercion en Localidad exitosa.!");
                }else
                    System.out.println("ERROR en la "+i+"º insercion Ya hay una Localidad registrada con el id "+i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe fichero " + ruta);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en " + i + "º insercion en Localidad por clave primaria");
            if (t != null) {
                t.rollback();
            }
        }
    }
    
    public static void LecturaFichero(String ruta){
         int i = 0;
    try (BufferedReader fbr = new BufferedReader(new FileReader(ruta))) {
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada
                String[] tmp = linea.split(",");//Guardamos todas las palabras de la linea del fichero en el array  
                 System.out.println("\nlinea  "+i);
                for(String item : tmp)
                {
                    System.out.print(item+" -> ");
                }
                i++;
                linea = fbr.readLine();
                //Iniciamos transaccion              
               
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe fichero " + ruta);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en " + i + "º insercion por clave primaria");
           
            }
    }

}
