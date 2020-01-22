/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creaidiomas;

/**
 *
 * @author Pablo Lopez Santana
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreaIdiomas {

    String basedatos = "facturacion";
    String host = "localhost";
    String port = "3306";
    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "root";
    String pwd = "123";
    Connection c;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here
        CreaIdiomas c = new CreaIdiomas();
        c.leerFichero();

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
            //System.out.println("Conectado..");

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

    public boolean insertarDatos(Connection c, String c1, String c2) throws SQLException {
        try {

            String Query = "INSERT INTO idioma(cod_iso,nom_idioma) VALUES (?,?);";
            PreparedStatement psInsert = c.prepareStatement(Query);
            psInsert.setString(1, c1);
            psInsert.setString(2, c2);
            psInsert.executeUpdate();

        } catch (Exception e) {
           System.out.println("ERROR al insertar el registro con el cod_iso: "+c1);
        }
        return true;
    }

    public void leerFichero() throws FileNotFoundException, IOException, SQLException {
        CreaIdiomas c = new CreaIdiomas();
        String rutaOrigen = "/home/alumno1920/prueba.txt";
        String[] campo = new String[2];

        //leemos
        try ( BufferedReader fbr = new BufferedReader(new FileReader(rutaOrigen))) {
            int i = 0;
            String linea = fbr.readLine();
            while (linea != null) {
                //Buscar dentro de la linea, si esta la palabra indicada

                campo = linea.split(",");
                if (campo != null) {
                    c.insertarDatos(c.conectar(), campo[0], campo[1]);
                }

                i++;
                linea = fbr.readLine();
            }
        }
    }

}
