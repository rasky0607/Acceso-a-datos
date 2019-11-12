/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentenciamodbd3_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pablo Lopez Santana
 */
public class SentenciaModBD3_3 {

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

        try ( Connection c = DriverManager.getConnection(urlConnection, user, pwd);
                //Creamos la consulta en base ala conexion que tenemos creada
            Statement s = c.createStatement()) {
            System.out.println("Conexión realizada.");
            
           //Ejecutamos la consulta
      ResultSet rs = s.executeQuery("SELECT DNI,APELLIDOS,CP FROM CLIENTES");
      
           int i=1;    
           
           //Ponemos el puntero del resulset en la ultima fila con last()
           rs.last();
           System.out.print("["+i+"]");
           System.out.println("DNI "+rs.getString("DNI"));
           System.out.println("APELLIDOS "+rs.getString("APELLIDOS"));
           System.out.println("CP "+rs.getInt("CP"));
           
           /*Leemos hacia atras despues de haber colocado el puntero en la ultima fila
           **IMPORTANTE** al hacer el previous() avanza una posicion.. por lo tanto se salta la ultima posicion en la que se coloco la funcion last()*/
            while (rs.previous()) {
                 i++;
                 System.out.print("["+i+"]");
                 //La columna del string puede  indicarse tanto por un string como  por el numero de una columna seria lo mismo getString("DNI"); que getString(0);
                 System.out.println("DNI "+rs.getString("DNI"));
                 System.out.println("APELLIDOS "+rs.getString("APELLIDOS"));
                 System.out.println("CP "+rs.getInt("CP"));               
                
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
