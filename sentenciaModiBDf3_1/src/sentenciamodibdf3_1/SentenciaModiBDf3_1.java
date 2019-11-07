/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentenciamodibdf3_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pablo Lopez
 */
public class SentenciaModiBDf3_1 {

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
            
            s.execute("CREATE TABLE CLIENTES (DNI CHAR(9) NOT NULL,"
                    + " APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5),"
                    + " PRIMARY KEY(DNI));");
            
            int nFil = s.executeUpdate("INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES "
              + "('78901234X','NADALES','44126'),"
              + "('89012345E','HOJAS', null),"
              + "('56789012B','SAMPER','29730'),"
              + "('09876543K','LAMIQUIZ', null);");

      System.out.println(nFil + " Filas insertadas.");
      //Actualizacion y eliminacion de datos
      s.executeUpdate("update CLIENTES set APELLIDOS = 'ROJAS' where DNI = '89012345E';");
      s.executeUpdate("delete from CLIENTES where DNI = '09876543K';");
      
      
            
        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
}
