/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentenciamodibd3_5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Pablo Lopez Santana
 */
public class SentenciaModiBD3_5 {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String basedatos = "libro_ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "root";
        String pwd = "123";
        String dni = " ";
        System.out.println("Dime el DNI de algun cliente:");
        Scanner teclado = new Scanner(System.in);
        dni = teclado.nextLine();
        try ( Connection c = DriverManager.getConnection(urlConnection, user, pwd); //Creamos la consulta en base ala conexion que tenemos creada
                  Statement s = c.createStatement()) {
            System.out.println("Conexión realizada.");
            String query = "SELECT DNI,APELLIDOS,CP FROM CLIENTES WHERE DNI='" + dni + "'";
            //Ejecutamos la consulta
            ResultSet rs = s.executeQuery(query);

            //56789012B
            System.out.println("\n----------------------------------------");
            System.out.println("Resultado de busqueda de dni \'" + dni + "\'");
            System.out.println("----------------------------------------");
           
                if (rs.next()) {
                    System.out.println("DNI " + rs.getString("DNI"));
                    System.out.println("APELLIDOS " + rs.getString("APELLIDOS"));
                    System.out.println("CP " + rs.getInt("CP"));
               }else{
                System.out.println("No se encuentran clientes con el dni \'" + dni + "\'");
            }
            

        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
