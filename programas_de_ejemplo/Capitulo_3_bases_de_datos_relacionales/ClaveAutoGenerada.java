// Recuperación de valores para claves autogeneradas. Creación de factura.
package JDBC_claves_autogeneradas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_claves_autogeneradas {

    public static void main(String[] args) {
        (...) // Se omite declaración de variables para los datos de conexión

        try (
                 Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            try (
                     PreparedStatement sInsertFact = c.prepareStatement(
                            "INSERT INTO FACTURAS(DNI_CLIENTE) VALUES(?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );  PreparedStatement sInsertLineaFact = c.prepareStatement(
                    "INSERT INTO LINEAS_FACTURA(NUM_FACTURA, LINEA_FACTURA, CONCEPTO, CANTIDAD) VALUES(?,?,?,?)"
            )) {
                        c.setAutoCommit(false);
                        int i = 1;
                        sInsertFact.setString(i++, "78901234X");
                        sInsertFact.executeUpdate();
                        ResultSet rs = sInsertFact.getGeneratedKeys();
                        rs.next();
                        int numFact = rs.getInt(1);
                        int lineaFact = 1;
                        i = 1;
                        sInsertLineaFact.setInt(i++, numFact);
                        sInsertLineaFact.setInt(i++, lineaFact++);
                        sInsertLineaFact.setString(i++, "TUERCAS");
                        sInsertLineaFact.setInt(i++, 25);
                        sInsertLineaFact.executeUpdate();
                        i = 1;
                        sInsertLineaFact.setInt(i++, numFact);
                        sInsertLineaFact.setInt(i++, lineaFact++);
                        sInsertLineaFact.setString(i++, "TORNILLOS");
                        sInsertLineaFact.setInt(i++, 250);
                        sInsertLineaFact.executeUpdate();
                        c.commit();

                    } catch (SQLException e) {
                        muestraErrorSQL(e);
                        try {
                            c.rollback();
                            System.err.println("Se hace ROLLBACK");
                        } catch (Exception er) {
                            System.err.println("ERROR haciendo ROLLBACK");
                            er.printStackTrace(System.err);
                        }
                    }
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }
}
