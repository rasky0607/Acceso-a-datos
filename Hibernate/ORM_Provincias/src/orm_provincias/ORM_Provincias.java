/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm_provincias;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alumno1920
 */
public class ORM_Provincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = null;

            try {
                t = s.beginTransaction();

            } catch (Exception e) {
                e.printStackTrace(System.err);
                if (t != null) {
                    t.rollback();
                }
            }

        }
    }

}
