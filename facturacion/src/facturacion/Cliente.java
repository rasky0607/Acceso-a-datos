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
    
  public boolean save(Connection c) throws SQLException {
      String Query="INSERT INTO cliente(id_cliente,dni,nom_cliente) VALUES (?,?);";
      PreparedStatement sInsert = c.prepareStatement(Query,PreparedStatement.RETURN_GENERATED_KEYS);
      sInsert.setInt(1, 1);
      sInsert.setString(2, "44126");
      sInsert.executeUpdate();
      return  true;
   }
  
  public void GetCliente(Connection c,Cliente cl) throws SQLException{
      String Query ="SELECT id_cliente,dni,nom_cliente from cliente";
      PreparedStatement sInsert = c.prepareStatement(Query);
      sInsert.setString(1, "2");
      sInsert.setInt(3, 44126);
      sInsert.executeQuery();
  }
      
      
       
        
    
    
}
