/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentenciamodibd3_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Pablo Lopez
 */
public class SentenciaModiBD3_2 {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    public static void main(String[] args) {

        String basedatos = "libro_ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "root";
        String pwd = "123";

        try ( Connection c = DriverManager.getConnection(urlConnection, user, pwd);
            Statement s = c.createStatement()) {
            System.out.println("Conexión realizada.");
            
           
      ResultSet rs = s.executeQuery("SELECT DNI,APELLIDOS,CP FROM CLIENTES");
      
           int i=1;
            while (rs.next()) {
                 System.out.println("["+i++ +"]");
                 System.out.println("DNI "+rs.getString("DNI"));
                 System.out.println("APELLIDOS "+rs.getString("APELLIDOS"));
                 System.out.println("CP "+rs.getInt("CP"));
                 /*Se puede utilizar getInt en lugar de getString ya que internamente hace una
                 conversion implicita a la hora  de recoger los datos*/
                
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
