package com.example.demo.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("config");

    public static Connection getConnection() throws Exception {
        Connection connection = comboPooledDataSource.getConnection();
        return connection;
    }

    public static void closeResource(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (resultSet != null)
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement);
        }
        return 0;
    }

    public static int update(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(null, preparedStatement);
        }
        return 0;
    }

    public static <T> T querySingle(Class<T> cl, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                T t = cl.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = cl.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, resultSet.getObject(i + 1));
                }
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement);
        }
        return null;
    }

    public static <T> List<T> queryList(Class<T> cl, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<T> arrayList = new ArrayList<T>();
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                T t = cl.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = cl.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, resultSet.getObject(i + 1));
                }
                arrayList.add(t);
            }
            return arrayList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement);
        }
        return null;
    }
}