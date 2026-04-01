package com.java.shopweb.dao;

import com.java.shopweb.config.DatabaseConnection;
import com.java.shopweb.mapper.RowMapper;
import com.java.shopweb.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {

    protected List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             var ps = connection.prepareStatement(sql)) {
            setParams(params, ps);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected Integer count(String sql,Object...params) {
        Integer count = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             var ps = connection.prepareStatement(sql)){
              setParams(params,ps);
              var resultSet = ps.executeQuery();
              while (resultSet.next()){
                  count = StringUtils.getInteger(resultSet.getString("total"));
              }
              return count;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }


    protected int update(String sql, Object... params) {
        int affectedRows = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             var ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setParams(params, ps);
            affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (var generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    protected int insert(String sql,Object... params){
        return update(sql,params);
    }

    protected void delete(String sql,Object... params){
        update(sql,params);
    }

    private void setParams(Object[] params, PreparedStatement ps) throws SQLException{
            for(int i=0;i<params.length;i++){
                ps.setObject(i+1,params[i]);
            }
    }
}
