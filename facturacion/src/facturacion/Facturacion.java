/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
    Connection c;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here 

        Facturacion dao = new Facturacion();
        //f.conectar();

        //Insercion de un cliente
        /*Cliente cl = new Cliente();
        cl.setId(4);
        cl.setDni("9498923L");
        cl.setNombre("Paco");
        cl.insertCliente(f.conectar());*/
        //Busqueda de un cliente
        Cliente cl1 = new Cliente();
        Cliente cl2 = new Cliente();
        /*cl1.setId(4);
        cl1.selectClienteForId(dao.conectar());*/

        //Menu
        int eleccion=-1;
        do {          
            switch (eleccion) {
                case 1:
                    cl1.selectAllCliente(dao.conectar());
                    dao.desconectar();
                    eleccion=-1;
                    break;
                case 2:
                    cl2.setId(25);
                    cl2.setDni("77d3R");
                    cl2.setNombre("pola");
                    cl2.insertCliente(dao.conectar());
                     eleccion=-1;
                    break;
                case 3:
                     eleccion=-1;
                    break;

                case 4:
                     eleccion=-1;
                    break;

                default:
                    //System.out.print("\tERROR: Esa opcion no esta contemplada.. ");
                    eleccion = menu();
                    break;

            }
            
        } while (eleccion != 0);

    }

    public Connection conectar() {
        String basedatos = "facturacion";
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

    public static int menu() {
        int opcion = -1;

        System.out.println("--Opciones:--\t\t");
        System.out.println("\t---------------");
        System.out.println("\t1-Lista Clientes.");
        System.out.println("\t2-Insertar Clientes.");
        System.out.println("\t3-Lista Facturas.");
        System.out.println("\t4-Listar contenido de una factura.");
        System.out.println("\t0-Salir.");
        System.out.print("\tOpción escogida: ");
        Scanner lector = new Scanner(System.in);
        opcion = Integer.parseInt(lector.nextLine());
        

        return opcion;

    }

}
