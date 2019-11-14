/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pablo Lopez Santana
 */
public class Facturacion {

      String basedatos = "facturacion";
      String host = "localhost";
      String port = "3306";
      String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
      String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
      String user = "root";
      String pwd = "123";   
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here    
        Facturacion f = new Facturacion();      
        Cliente cl = new Cliente();
        cl.setDni("9498923L");
        cl.setId(1);
        cl.save(f.conectar());
        
    }

    public  Connection conectar() {
        Connection c=null;
        try{
            c = DriverManager.getConnection(urlConnection, user, pwd);
            System.out.println("Conectado..");
           
        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());           
        }
         return c;
    }

    public boolean desconectar() {

        return true;
    }

}
