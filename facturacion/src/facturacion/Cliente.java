/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        int i = 1;
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id_cliente"));
            System.out.println("DNI: " + rs.getString("dni"));
            System.out.println("Nombre: " + rs.getString("nom_cliente"));
            System.out.println("--------------------------------------");
        }
    }

}
