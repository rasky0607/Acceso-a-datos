/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatextos;

/**
 *
 * @author Pablo Lopez Santana
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class Creatextos {

    Connection c;

    public static void main(String[] args) throws SQLException {

        Creatextos dao = new Creatextos();
        Texto tx = new Texto();

        int id_texto;
        int num_palabras;
        String cod_idioma;

        //Menu
        int eleccion = -1;
      
     
        do {
            switch (eleccion) {
                case 1:
                    tx.selectAllTexto(dao.conectar());
                    dao.desconectar();
                    eleccion = -1;
                    break;
                case 2:
                    Scanner lector = new Scanner(System.in);
                    System.out.print("\tIntroduzca el id: ");
                    id_texto = Integer.parseInt(lector.nextLine());
                    System.out.println("");
                    System.out.print("\tIntroduzca el número de palabras: ");
                    num_palabras = Integer.parseInt(lector.nextLine());
                    System.out.println("");
                    System.out.print("\tIntroduzca el código del idioma: ");
                    cod_idioma = lector.nextLine();
                    
                   
                    tx.insertTexto(dao.conectar(),id_texto,num_palabras,cod_idioma);
                    dao.desconectar();
                    eleccion = -1;
                    break;

                default:
                    eleccion = menu();
                    break;

            }

        } while (eleccion != 0);

    }

    public Connection conectar() {
        String basedatos = "traducciones";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "root";
        String pwd = "123";

        c = null;
        try {
            c = DriverManager.getConnection(urlConnection, user, pwd);

        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        }
        return c;
    }

    public boolean desconectar() throws SQLException {
        c.close();
        c = null;
        return true;
    }

    public static int menu() {
        int opcion = -1;
        System.out.println("--Opciones:--\t\t");
        System.out.println("\t---------------");
        System.out.println("\t1-Listar Textos.");
        System.out.println("\t2-Insertar Textos.");
        System.out.println("\t0-Salir.");
        System.out.print("\tOpción escogida: ");
        //leo la opcion
        Scanner lector = new Scanner(System.in);
        opcion = Integer.parseInt(lector.nextLine());

        return opcion;

    }

}