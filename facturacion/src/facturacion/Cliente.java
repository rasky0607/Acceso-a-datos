/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import static facturacion.Facturacion.menu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//añadido
import java.util.Scanner;

/**
 *
 * @author Pablo Lopez
 */
public class Cliente {

    int id;
    String dni;
    String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
//IMPORTANTE

    public boolean insertCliente(Connection c) throws SQLException {
        String Query = "INSERT INTO cliente(id_cliente,dni,nom_cliente) VALUES (?,?,?);";
        PreparedStatement psInsert = c.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        psInsert.setInt(1, getId());
        psInsert.setString(2, getDni());
        psInsert.setString(3, getNombre());
        psInsert.executeUpdate();
        return true;
    }

    //Busuqeda de cliente por el id
    public void selectClienteForId(Connection c) throws SQLException {
        String Query = "SELECT id_cliente,dni,nom_cliente from cliente where id_cliente='" + getId() + "'";
        PreparedStatement psSelect = c.prepareStatement(Query);
        ResultSet rs = psSelect.executeQuery();
        //rs.getRow() --> indica la fila actual en la que me encuentro
        int i = 1;
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id_cliente"));
            System.out.println("DNI: " + rs.getString("dni"));
            System.out.println("Nombre: " + rs.getString("nom_cliente"));
            System.out.println("--------------------------------------");
        }
    }

    //Busuqeda de cliente por el id
    public void selectAllCliente(Connection c) throws SQLException {
        //Mensaje i nformativo
        MensajeInfoListado();
        
        String Query = "SELECT id_cliente,dni,nom_cliente from cliente";
        PreparedStatement psSelect = c.prepareStatement(Query);
        ResultSet rs = psSelect.executeQuery();
        //rs.getRow() --> indica la fila actual en la que me encuentro
        //int i = 1;

        //Variables para guardar el registro que mostramos, por si deseamos eliminarlo o actulizarlo
        int idCliente = 0;

        String opcion = "s";//Inicializamos a "s" para que lea el primer registro en cuanto ejecuta el metodo
        do {
            //Vamos leiendo linea a linea los registros si  pulsa S y sale al menu si pulsa Q
            switch (opcion) {
                case "s":
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id_cliente"));
                        System.out.println("DNI: " + rs.getString("dni"));
                        System.out.println("Nombre: " + rs.getString("nom_cliente"));
                        System.out.println("--------------------------------------");
                        //Guardamos el id del cliente para futuras modificaciones 
                        idCliente = rs.getInt("id_cliente");
                        break;
                    }

                    break;

                case "d":
                    //ELiminarCliente(id);
                    if (DeleteClienteForId(c, idCliente)) {
                        System.out.println("--------Registro con id " + idCliente + " eliminado-------");
                    } else {
                        System.out.println("--------ERROR al eliminar el registro con id: " + idCliente + "-------");
                    }

                    break;
                case "u":
                    //ActualizarCliente(id);
                    if(UpdateClienteForId(c, idCliente))
                        System.out.println("--------Registro con id " + idCliente + " modificado-------");
                    else
                        System.out.println("--------ERROR al modificar el registro con id: " + idCliente + "-------");
                        
                    break;
                case "q"://Sale al menu de nuevo

                    break;

                default:
                    System.out.println("Esa opcion no es valida...");
                    break;
            }
            //Lectura
            Scanner lector = new Scanner(System.in);
            opcion = lector.nextLine();

        } while (!opcion.equals("q"));

        System.out.println("\t####### Volviendo al menu... #######");

    }

    public boolean DeleteClienteForId(Connection c, int id) throws SQLException {
        String Query = "DELETE FROM cliente WHERE id_cliente=?";
        PreparedStatement psDelete = c.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        psDelete.setInt(1, id);
        psDelete.executeUpdate();
        return true;
    }

    public boolean UpdateClienteForId(Connection c, int id) throws SQLException {
        
        //Menu de opciones segun q ue modificamos (si mete un dato vacio, no sera modificado),escribe NUll introduciremos Null al valor indicado)
          //Lectura
          String dni=" ";
          String nom_cliente=" ";
            Scanner lector = new Scanner(System.in);
            System.out.println("Nuevo DNI de Cliente: ");
            dni = lector.next();
            System.out.println("Nuevo Nombre de Cliente: ");
            nom_cliente =lector.next();
        
        String Query = "UPDATE cliente SET dni=?, nom_cliente=? WHERE id_cliente=?";
        PreparedStatement psUpdate = c.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        psUpdate.setString(1, dni);
        psUpdate.setString(2, nom_cliente);
        psUpdate.setInt(3, id);
        psUpdate.executeUpdate();
        return true;
    }
    
    
    
    private void MensajeInfoListado(){
        
     System.out.println("INFO:\npulsa s para leer la siguiente fila.\nPulsa d para eliminar el registro mostrado.\nPulsa u para actualizar el registro mostrado.\nPulsa q para volver al menu.");
            }

}
