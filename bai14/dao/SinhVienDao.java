package com.example.bai14.dao;

import com.example.bai14.entity.LopHoc;
import com.example.bai14.entity.SinhVien;
import com.example.bai14.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {
   public void themSinhVien(SinhVien sinhVien){
       Connection connection = ConnectionUtils.getConnection();
       if(connection == null){
           System.out.println("Ket noi that bai!");
           return;
       }
       try {
           String sql ="insert into sinh_vien(ho_ten,ngay_sinh,email,hoc_phi,ma_lop) values (?,?,?,?,?)";
           connection.setAutoCommit(false);
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1,sinhVien.getHoTen());
           preparedStatement.setDate(2,sinhVien.getNgaySinh());
           preparedStatement.setString(3,sinhVien.getEmail());
           preparedStatement.setFloat(4,sinhVien.getHocPhi());
           preparedStatement.setInt(5,sinhVien.getMaLop());
           int rowsAdded = preparedStatement.executeUpdate();
           if(rowsAdded > 0){
               System.out.println("Them sinh vien thanh cong!");
           } else {
               System.out.println("Them sinh vien that bai!");
           }
           connection.commit();
       } catch (SQLException e){
           throw new RuntimeException(e);
       } finally {
           ConnectionUtils.closeConnection(connection);
       }
   }

   public List<SinhVien> getAllSinhVienWithLopHoc() {
       Connection connection = ConnectionUtils.getConnection();
       try {
           String sql = """
                   SELECT sv.ma_sv,sv.ho_ten,sv.ngay_sinh,sv.email,sv.hoc_phi,lh.ten_lop
                   FROM sinh_vien sv
                   LEFT JOIN lop_hoc lh ON sv.ma_lop = lh.ma_lop
                   """;
           connection.setAutoCommit(false);
           PreparedStatement ps = connection.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();

           List<SinhVien> sinhViens = new ArrayList<>();
           while (rs.next()) {
               SinhVien sinhVien = new SinhVien();
               sinhVien.setMaSv(rs.getInt("ma_sv"));
               sinhVien.setHoTen(rs.getString("ho_ten"));
               sinhVien.setNgaySinh(rs.getDate("ngay_sinh"));
               sinhVien.setEmail(rs.getString("email"));
               sinhVien.setHocPhi(rs.getFloat("hoc_phi"));

               LopHoc lopHoc = new LopHoc();
               lopHoc.setTenLop(rs.getString("ten_lop"));
               sinhVien.setLopHoc(lopHoc);

               sinhViens.add(sinhVien);
               connection.commit();
           }
           return sinhViens;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } finally {
           ConnectionUtils.closeConnection(connection);
       }
   }

   public void updateSinhVien(SinhVien svUpdate){
       Connection connection = ConnectionUtils.getConnection();

       try{
           String sql = """
                   UPDATE sinh_vien
                   SET ho_ten=?,ngay_sinh=?,email=?,hoc_phi=?,ma_lop=?
                   WHERE MA_SV=?
                   """;

           connection.setAutoCommit(false);

           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1,svUpdate.getHoTen());
           ps.setDate(2,svUpdate.getNgaySinh());
           ps.setString(3,svUpdate.getEmail());
           ps.setFloat(4,svUpdate.getHocPhi());
           ps.setInt(5, svUpdate.getMaLop());
           ps.setInt(6,svUpdate.getMaSv());

           ps.executeUpdate();
           connection.commit();

           System.out.println("Cap nhat thong tin sinh vien thanh cong!");
       } catch (Exception e){
           throw new RuntimeException(e);
       } finally {
           ConnectionUtils.closeConnection(connection);
       }
   }

   public List<SinhVien> searchSinhVien(String keyword){
       Connection connection = ConnectionUtils.getConnection();

       try {
           String sql = """
                   SELECT sv.ma_sv,sv.ho_ten,sv.ngay_sinh,sv.email,sv.hoc_phi,lh.ten_lop
                   FROM sinh_vien sv
                   LEFT JOIN lop_hoc lh ON sv.ma_lop = lh.ma_lop
                   WHERE sv.ma_sv LIKE ? OR sv.ho_ten LIKE ?
                   """;
           connection.setAutoCommit(false);

           PreparedStatement ps = connection.prepareStatement(sql);

           int maSv = -1;

           try{
               maSv = Integer.parseInt(keyword);
           } catch (Exception ignored){}

           ps.setInt(1,maSv);
           ps.setString(2,"%"+keyword+"%");

           ResultSet rs = ps.executeQuery();

           List<SinhVien> sinhViens = new ArrayList<>();
           while(rs.next()){

               SinhVien sinhVien = new SinhVien();
               sinhVien.setMaSv(rs.getInt("ma_sv"));
               sinhVien.setHoTen(rs.getString("ho_ten"));
               sinhVien.setNgaySinh(rs.getDate("ngay_sinh"));
               sinhVien.setEmail(rs.getString("email"));
               sinhVien.setHocPhi(rs.getFloat("hoc_phi"));

               LopHoc lopHoc = new LopHoc();
               lopHoc.setTenLop(rs.getString("ten_lop"));
               sinhVien.setLopHoc(lopHoc);
               sinhViens.add(sinhVien);
               connection.commit();

           }
         return sinhViens;
       } catch (SQLException e){
           throw new RuntimeException(e);
       } finally {
           ConnectionUtils.closeConnection(connection);
       }
   }

   public void deleteSinhVien(int maSv){
      Connection connection = ConnectionUtils.getConnection();

      try{

          String sql="DELETE FROM sinh_vien WHERE ma_sv=?";
          connection.setAutoCommit(false);

          PreparedStatement ps = connection.prepareStatement(sql);
          ps.setInt(1,maSv);
          ps.executeUpdate();
          connection.commit();
      } catch (Exception e){
          e.printStackTrace();
      } finally {
          ConnectionUtils.closeConnection(connection);
      }
   }
}

