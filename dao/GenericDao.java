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

        String idColumn = "";
        Object idValue = null;

        for (int i = 0 ; i < listField.length ; i++) {
            listField[i].setAccessible(true);

            if((listField[i].getName().equalsIgnoreCase("id"))) {
                idColumn = listField[i].getName();
                idValue = listField[i].get(o);
                break;
            }
        }

        if (idValue != null) {
            String sql = "DELETE FROM "+ nomCLasse +"WHERE"+ idColumn +"="+idValue;
            System.out.println("Requête génére.");
            Connection conn = ConnectionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } else {
            throw new Exception("Aucun idée correspondant");
        }
        


    }
}
