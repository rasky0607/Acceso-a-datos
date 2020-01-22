
package creatextos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Pablo Lopez Santana
 */
public class Texto {

    int id_texto;
    int num_palabras;
    String cod_idioma;

    public int getId_texto() {
        return id_texto;
    }

    public void setId_texto(int id_texto) {
        this.id_texto = id_texto;
    }

    public int getNum_palabras() {
        return num_palabras;
    }

    public void setNum_palabras(int num_palabras) {
        this.num_palabras = num_palabras;
    }

    public String getCod_idioma() {
        return cod_idioma;
    }

    public void setCod_idioma(String cod_idioma) {
        this.cod_idioma = cod_idioma;
    }
    
    public void Texto(int id_texto){
    
    }

    public boolean insertTexto(Connection c, int id,int numPalabra,String codIdioma) throws SQLException {
        String Query = "INSERT INTO texto(id_texto, num_palabras, cod_idioma) VALUES (?,?,?);";
        PreparedStatement psInsert = c.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        psInsert.setInt(1, id);
        psInsert.setInt(2, numPalabra);
        psInsert.setString(3, codIdioma);
        psInsert.executeUpdate();
        return true;
    }

    public void selectAllTexto(Connection c) throws SQLException {

        String Query = "SELECT id_texto, num_palabras, cod_idioma from texto";   
        PreparedStatement psSelect = c.prepareStatement(Query);
        
        ResultSet rs = psSelect.executeQuery();
        while (rs.next()) {
            System.out.println("ID_TEXTO: " + rs.getInt("id_texto"));
            System.out.println("NUM_PALABRAS: " + rs.getInt("num_palabras"));
            System.out.println("COD_IDIOMA: " + rs.getString("cod_idioma"));
            System.out.println("--------------------------------------");
        }

    }
}