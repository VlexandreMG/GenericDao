package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.reflect.Field;
import db.ConnectionBD;

public class GenericDao {
    
    public static void save(Object o) throws Exception {
        // Connection conn = ConnectionBD.getConnection();
        // // String sql = "INSERT INTO Olona (nom) VALUES (?)";
        // PreparedStatement ps = conn.prepareStatement(sql);

        Class<?> classe = o.getClass();
        String classeNom = classe.getSimpleName();
        Field[] attributs = classe.getDeclaredFields();

        String query = "INSERT INTO " + classeNom + "(";
        int i = 0 ; int j = 0;
        for ( i = 0 ; i < attributs.length - 1 ; i++) {
            query += attributs[i].getName()+", ";
        }query += attributs[i].getName() + ") VALUES (";
        for ( j = 0; j < attributs.length - 1; j++) {
            query += "?, ";
        } query += "?)";

        try {
            Connection conn = ConnectionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            for (int k = 0; k < attributs.length ; k++) {
                attributs[k].setAccessible(true);
                ps.setObject(k+1, attributs[k].get(o));
            }

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }        
    }

    public static void delete(Object o) throws Exception {
        Class<?> classe = o.getClass();
        String nomCLasse = classe.getSimpleName();
        Field[] listField = classe.getDeclaredFields();

        for (int i = 0 ; i < listField.length ; i++) {
            listField[i].setAccessible(true);

            if((listField[i].get(o)) == o.getClass.get)
        }
        
        "DELECT FROM "+ nomCLasse + 



    }
}
