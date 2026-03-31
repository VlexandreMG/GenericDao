package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import db.ConnectionBD;
import model.Mapping;

public class GenericDao implements InterfaceDao {
    Mapping mapping;

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public GenericDao() {
        this.mapping = new Mapping();
    }

    @Override
    public void save(Object o) throws Exception {
        // Connection conn = ConnectionBD.getConnection();
        // // String sql = "INSERT INTO Olona (nom) VALUES (?)";
        // PreparedStatement ps = conn.prepareStatement(sql);

        // Class<?> classe = o.getClass();
        // String classeNom = classe.getSimpleName();
        // Field[] attributs = classe.getDeclaredFields();

        Mapping mapping = this.getMapping();

        Mapping map = mapping.recupObject(o);
        String tableNom = map.getTableName();
        String[][] tableColumn = map.getTableColumn();

        String query = "INSERT INTO " + tableNom + "(";
        int i = 0;
        int j = 0;
        for (i = 0; i < tableColumn.length - 1; i++) {
            query += tableColumn[i][0] + ", ";
        }
        query += tableColumn[i][0] + ") VALUES (";
        for (j = 0; j < tableColumn.length - 1; j++) {
            query += "?, ";
        }
        query += "?)";

        try {
            Connection conn = ConnectionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            for (int k = 0; k < tableColumn.length; k++) {
                // tableColumn[k][1].setAccessible(true);
                ps.setObject(k + 1, tableColumn[k][1]);
            }

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(Object o) throws Exception {
        
        // Class<?> classe = o.getClass();
        // String nomCLasse = classe.getSimpleName();
        // Field[] listField = classe.getDeclaredFields();

        Mapping mapping = this.getMapping();

        Mapping map = mapping.recupObject(o);
        String nomCLasse = map.getTableName();
        String[][] tableColumn = map.getTableColumn();

        String idColumn = "";
        Object idValue = null;

        for (int i = 0; i < tableColumn.length; i++) {
            if ((tableColumn[i][0].equalsIgnoreCase("id"))) {
                idColumn = tableColumn[i][0];
                idValue = tableColumn[i][1];
                break;
            }
        }

        if (idValue != null) {
            String sql = "DELETE FROM " + nomCLasse + " WHERE " + idColumn + " = ?";
            System.out.println("Requête générée.");
            Connection conn = ConnectionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, idValue);
            ps.execute();
        } else {
            throw new Exception("Aucun idée correspondant");
        }
    }

    // public void update(Object o) throws Exception {
    // Class<?> classe = o.getClass();
    // String nomClasse = classe.getSimpleName();
    // Field[] listField = classe.getDeclaredFields();

    // String idColumn = "";
    // Object idValue = null;

    // String sql = "UPDATE " + nomClasse + " SET ";
    // boolean first = true;

    // for (int i = 0; i < listField.length; i++) {
    // listField[i].setAccessible(true);
    // if (listField[i].getName().equalsIgnoreCase("id")) {
    // idColumn = listField[i].getName();
    // idValue = listField[i].get(o);
    // } else {
    // if (!first) {
    // sql += ", ";
    // }
    // sql += listField[i].getName() + " = ?";
    // first = false;
    // }
    // }

    // if (idValue == null) {
    // throw new Exception("Aucun id correspondant");
    // }

    // sql += " WHERE " + idColumn + " = ?";

    // Connection conn = ConnectionBD.getConnection();
    // PreparedStatement ps = conn.prepareStatement(sql);

    // int index = 1;
    // for (int i = 0; i < listField.length; i++) {
    // listField[i].setAccessible(true);
    // if (!listField[i].getName().equalsIgnoreCase("id")) {
    // ps.setObject(index, listField[i].get(o));
    // index++;
    // }
    // }
    // ps.setObject(index, idValue);
    // ps.executeUpdate();
    // }

    // public Object findById(Class<?> classe, Object id) throws Exception {
    // String nomClasse = classe.getSimpleName();
    // Field[] listField = classe.getDeclaredFields();

    // String idColumn = "";
    // for (int i = 0; i < listField.length; i++) {
    // if (listField[i].getName().equalsIgnoreCase("id")) {
    // idColumn = listField[i].getName();
    // break;
    // }
    // }

    // if (idColumn.equals("")) {
    // throw new Exception("Aucun id correspondant");
    // }

    // String sql = "SELECT * FROM " + nomClasse + " WHERE " + idColumn + " = ?";
    // Connection conn = ConnectionBD.getConnection();
    // PreparedStatement ps = conn.prepareStatement(sql);
    // ps.setObject(1, id);

    // ResultSet rs = ps.executeQuery();
    // if (rs.next()) {
    // Object obj = classe.getDeclaredConstructor().newInstance();

    // for (int i = 0; i < listField.length; i++) {
    // listField[i].setAccessible(true);
    // Object value = rs.getObject(listField[i].getName());
    // listField[i].set(obj, value);
    // }
    // return obj;
    // }

    // return null;
    // }

    // public List<Object> getAll(Class<?> classe) throws Exception {
    // String nomClasse = classe.getSimpleName();
    // Field[] listField = classe.getDeclaredFields();

    // String sql = "SELECT * FROM " + nomClasse;
    // Connection conn = ConnectionBD.getConnection();
    // PreparedStatement ps = conn.prepareStatement(sql);
    // ResultSet rs = ps.executeQuery();

    // List<Object> list = new ArrayList<>();

    // while (rs.next()) {
    // Object obj = classe.getDeclaredConstructor().newInstance();

    // for (int i = 0; i < listField.length; i++) {
    // listField[i].setAccessible(true);
    // Object value = rs.getObject(listField[i].getName());
    // listField[i].set(obj, value);
    // }
    // list.add(obj);
    // }

    // return list;
    // }

}
