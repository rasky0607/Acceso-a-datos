/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatraductores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Pablo Lopez Santana
 */
public class CreaTraductores {

    String basedatos = "traducciones";
    String host = "localhost";
    String port = "3306";
    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "root";
    String pwd = "123";
    Connection c;

    String[][] datosTraductores = {
        {"37515445G", "LÓPEZ", "es"},
        {"X5353636P", "ROSSI", "it"},
        {"73453363W", "SCHMIDT", "de"}
    };

    public static void main(String[] args) throws IOException, FileNotFoundException, SQLException {

        CreaTraductores c = new CreaTraductores();
        c.lectura();

    }

    public Connection conectar() {
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

 

    public void lectura() throws FileNotFoundException, IOException, SQLException {

        CreaTraductores c = new CreaTraductores();
        
        for (int i = 0; i < datosTraductores.length; i++) {
            for (int j = 0; j < datosTraductores[0].length; j++) {
                c.insertar(c.conectar(), datosTraductores[i][j], datosTraductores [i][++j], datosTraductores[i][++j]);
                c.desconectar();
            }
        }
    }
    
        public boolean insertar(Connection c, String c1, String c2, String c3) throws SQLException {
        try {

            String Query = "INSERT INTO traductor(dni_nie, nom_trad, id_lengua_nativa) VALUES (?,?,?);";
            PreparedStatement psInsert = c.prepareStatement(Query);
  
            
               // CASO CON TRANSACCIÓN
                // Inicializamos transacción e insertamos los campos
                c.setAutoCommit(false);
                int i = 0;
                psInsert.setString(++i, "37515445G");
                psInsert.setString(++i, "LÓPEZ");
                psInsert.setString(++i, "es");
                psInsert.executeUpdate();

                psInsert.setString(i = 1, "X5353636P");
                psInsert.setString(++i, "ROSSI");
                psInsert.setString(++i, "it");
                psInsert.executeUpdate();

                psInsert.setString(i = 1, "73453363W");
                psInsert.setString(++i, "SCHMIDT");
                psInsert.setString(++i, "de");
                psInsert.executeUpdate();

                //Realizamos commit para confirmar los insert
                c.commit();
                
            /*psInsert.setString(1, c1);
            psInsert.setString(2, c2);
            psInsert.setString(3, c3);
            psInsert.executeUpdate();*/

        } catch (Exception e) {
            System.out.println("No se ha podido insertar la fila.");
        }
        return true;
    }
}
