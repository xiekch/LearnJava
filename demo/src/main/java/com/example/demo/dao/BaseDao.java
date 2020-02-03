package com.example.demo.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * base data access object
 */
public abstract class BaseDao {

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

    public static <T> T querySingle(Connection connection, Class<T> cl, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
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
            closeResource(null, preparedStatement);
        }
        return null;
    }

    public static <T> List<T> queryList(Connection connection, Class<T> cl, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
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
            closeResource(null, preparedStatement);
        }
        return null;
    }

    public static <T> T getValue(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (T) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(null, preparedStatement);
        }
        return null;
    }
}