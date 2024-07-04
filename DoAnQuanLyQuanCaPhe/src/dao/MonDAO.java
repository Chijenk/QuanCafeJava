/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Ban;
import pojo.Mon;

/**
 *
 * @author Dang Hoang Minh
 */
public class MonDAO {

     public static ArrayList<Mon> layDanhSachMon()
    {ArrayList<Mon> dsMon=new ArrayList<Mon>();
    try {
    String sql="Select * from Mon";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
        ResultSet rs=provider.executeQuery(sql);
        
            while(rs.next())
            {
                Mon mon=new Mon();
                mon.setMonID(rs.getInt(1));
                mon.setTenMon(rs.getString(2));
                mon.setMoTa(rs.getString(3));
                mon.setGia(rs.getDouble(4));
                mon.setDanhMucID(rs.getInt(5));
                mon.setHinhAnh(rs.getString(6));
                dsMon.add(mon);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsMon;
    }
     public static double layGiaMon(int id)
     {
         double gia=0;
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT Gia FROM Mon WHERE MonID = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, id);
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                gia=rs.getInt(1);
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return gia;
     }
      public static String layTenMon(int id)
     {
        String tenMon="";
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT TenMon FROM Mon WHERE MonID = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, id);
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                tenMon=rs.getString(1);
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return tenMon;
     }
     
     public static ArrayList<Mon> layDanhSachMonTheoDanhMuc(int danhMucID)
    {  ArrayList<Mon> dsMon=new ArrayList<Mon>();
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT * FROM Mon WHERE DanhMucID=  ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, danhMucID);
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                Mon mon=new Mon();
                mon.setMonID(rs.getInt(1));
                mon.setTenMon(rs.getString(2));
                mon.setMoTa(rs.getString(3));
                mon.setGia(rs.getDouble(4));
                mon.setDanhMucID(rs.getInt(5));
                dsMon.add(mon);
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return dsMon;
    }
     
     
      public static ArrayList<Mon> timKiemMon(String tenMon)
      {
          ArrayList<Mon> dsMon=new ArrayList<Mon>();
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT * FROM Mon WHERE TenMon LIKE ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setString(1, "%" + tenMon + "%");
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                Mon mon=new Mon();
                mon.setMonID(rs.getInt(1));
                mon.setTenMon(rs.getString(2));
                mon.setMoTa(rs.getString(3));
                mon.setGia(rs.getDouble(4));
                mon.setDanhMucID(rs.getInt(5));
                dsMon.add(mon);
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return dsMon;
    }
     public static boolean themMon(Mon mon) {
         boolean kq =false;
         String sql = String.format("INSERT INTO Mon(TenMon, MoTa, Gia,DanhMucID,HinhAnh) VALUES (N'%s', N'%s', %f, %d,'%s')",
         mon.getTenMon(), mon.getMoTa(), mon.getGia(),mon.getDanhMucID(),mon.getHinhAnh());
         SQLServerDataProvider provider = new SQLServerDataProvider();
         provider.open();
         int n=provider.executeUpdate(sql);
         if(n==1)
         {
             kq=true;
         }
         provider.close();
         return kq;
     
     }
     public static boolean suaMon(Mon mon) {
        boolean kq =false;
        String sql = String.format(
    "UPDATE Mon SET TenMon = N'%s', MoTa = N'%s', Gia = %f, DanhMucId = %d , HinhAnh=%s WHERE MonID = %d",mon.getTenMon(), mon.getMoTa(), mon.getGia(), mon.getDanhMucID(),mon.getHinhAnh(), mon.getMonID());
        SQLServerDataProvider provider = new SQLServerDataProvider();
         provider.open();
         int n=provider.executeUpdate(sql);
         if(n==1)
         {
             kq=true;
         }
         provider.close();
         return kq;
     }
     
     public static boolean xoaMon(Mon mon) {
        boolean kq =false;
        String sql = String.format("DELETE from Mon where MonID= %d",mon.getMonID());
    SQLServerDataProvider provider = new SQLServerDataProvider();
         provider.open();
         int n=provider.executeUpdate(sql);
         if(n==1)
         {
             kq=true;
         }
         provider.close();
         return kq;
     }
     
     public static int layMaMonLonNhat() {
        String sql = "SELECT MAX(MonID)  FROM Mon";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int maLonNhat = 0;

        
           
         try {
              SQLServerDataProvider provider =new SQLServerDataProvider();
              provider.open();
             pstmt = provider.getConnection().prepareStatement(sql);
              rs = pstmt.executeQuery();

            if (rs.next()) {
                maLonNhat = rs.getInt(1);
            }
         } catch (SQLException ex) {
             return 0;
             
         }
         return maLonNhat;
     }
      public static String layAnh(int id) {
        String sql = "SELECT HinhAnh FROM Mon where MonID = ?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String anh = "";
    
    try {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        pstmt = provider.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id); // Thiết lập giá trị id cho placeholder '?'
        rs = pstmt.executeQuery();

        if (rs.next()) {
            anh = rs.getString("HinhAnh"); // Đây là cách lấy giá trị theo tên cột, nó thường rõ ràng hơn so với chỉ số cột
        }
     } catch (SQLException ex) {
         // Xử lý hoặc log exception tại đây
         return "";
         
     }
     
     return anh;
}
     
}
