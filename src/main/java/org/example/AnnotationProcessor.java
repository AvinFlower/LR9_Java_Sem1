package org.example;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AnnotationProcessor {

    public static void createTable(Object cl) throws Exception {
        Class<?> clClass = cl.getClass();
        if (!clClass.isAnnotationPresent(Table.class)) {
            throw new Exception("Класс не содержит аннотации @Table");
        }
        Table table = clClass.getAnnotation(Table.class);

        StringBuilder sql = new StringBuilder("CREATE TABLE " + table.title() + " (");

        StringBuilder sqlDEL = new StringBuilder("DROP TABLE IF EXISTS "+ table.title() + ";");

        Field[] fields = clClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                sql.append(field.getName()).append(" ");


                if (field.getType() == int.class) {
                    sql.append("INT");
                } else if (field.getType() == String.class) {
                    sql.append("TEXT");
                } else if (field.getType() == Crocodile.Size.class) {
                    sql.append("TEXT");
                }
                sql.append(",");

            }
        }

        //З з и з з
        sql.deleteCharAt(sql.length() - 1).append(");");

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:D:/sqlite/LR9.db");
            Statement statement = connection.createStatement();
            statement.execute(sqlDEL.toString());
            statement.execute(sql.toString());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertIntoTable(Object cl) {
        Class<?> clClass = cl.getClass();
        if (!clClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Нет аннотации Table в классе");
        }
        //т
        String tableName = clClass.getAnnotation(Table.class).title();
        //п
        Field[] fields = clClass.getDeclaredFields();
        StringBuilder query = new StringBuilder("INSERT OR IGNORE INTO " + tableName + " (");
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                query.append(field.getName()).append(",");
            }
        }

        query.deleteCharAt(query.length() - 1).append(") VALUES (");

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                try {
                    query.append("'").append(field.get(cl)).append("',");
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        query.deleteCharAt(query.length() - 1).append(")");

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:D:/sqlite/LR9.db");
            Statement statement = connection.createStatement();
            statement.execute(query.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
