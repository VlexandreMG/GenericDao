package model;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class Mapping {
    String tableName;
    String[][] tableColumn;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[][] getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String[][] tableColumn) {
        this.tableColumn = tableColumn;
    }

    public Mapping() {
    }

    public Mapping(String tableName, String[][] tableColumn) {
        this.tableName = tableName;
        this.tableColumn = tableColumn;
    }

    public Mapping recupObject(Object o) throws Exception {
        Class<?> clazz = o.getClass();
        String clazzName = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        String[][] tableColumn = new String[fields.length][2];

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            tableColumn[i][0] = fields[i].getName();
            tableColumn[i][1] = String.valueOf(fields[i].get(o));
        }
        return new Mapping(clazzName, tableColumn);
    }

    public Mapping recupClass(Class<?> clazz) throws Exception {
        String clazzName = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        String[][] tableColumn = new String[fields.length][2];

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            tableColumn[i][0] = fields[i].getName();
            tableColumn[i][1] = fields[i].getType().getSimpleName();
        }
        return new Mapping(clazzName, tableColumn);
    }

    public List<Mapping> getAllClass(String pkg) throws Exception {
        Reflections reflection = new Reflections(
            new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(pkg))
                .filterInputsBy(new FilterBuilder().includePackage(pkg))
                .setScanners(Scanners.SubTypes.filterResultsBy(s -> true))
        );

        List<Mapping> listMap = new ArrayList<>();
        Set<Class<?>> classes = reflection.getSubTypesOf(Object.class);
        for (Class<?> clazz : classes) {
            listMap.add(recupClass(clazz));           
        }
        return listMap;
    }
}