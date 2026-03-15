package com.example.bai14.dao;

import com.example.bai14.entity.LopHoc;
import com.example.bai14.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LopHocDao {

    public List<LopHoc> getAllClasses(){
        Connection connection = ConnectionUtils.getConnection();
       try{
           PreparedStatement preparedStatement = connection.prepareStatement("select * from lop_hoc");
           ResultSet resultSet = preparedStatement.executeQuery();
           List<LopHoc> lopHocs = new ArrayList<>();
           while (resultSet.next()){
               LopHoc lopHoc = new LopHoc();
               lopHoc.setMaLop(resultSet.getInt("ma_lop"));
               lopHoc.setTenLop(resultSet.getString("ten_lop"));
               lopHocs.add(lopHoc);
           }
           return lopHocs;
       } catch (SQLException e){
           throw new RuntimeException(e);
       } finally {
           ConnectionUtils.closeConnection(connection);
       }
    }
}
