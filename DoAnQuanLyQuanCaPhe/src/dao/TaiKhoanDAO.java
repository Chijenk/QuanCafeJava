/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Mon;
import pojo.TaiKhoan;

/**
 *
 * @author Dang Hoang Minh
 */
public class TaiKhoanDAO {
    
    
      public static boolean timKiemTK(String tenTaiKhoan,String matKhau)
      {
          boolean kq=false;
          TaiKhoan tk= new TaiKhoan();
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT * FROM TaiKhoan WHERE TenTaiKhoan= ? and MatKhau = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setString(1, tenTaiKhoan);
               preStatement.setString(2,matKhau);
              rs=preStatement.executeQuery();
                if (rs.next()) {
            kq = true;
        }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return kq;
    }
      public static int layIDNhanVien(String tenTK)
      {
          int id=0;
          TaiKhoan tk= new TaiKhoan();
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT NhanVienID from TaiKhoan WHERE TenTaiKhoan = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setString(1, tenTK);
               
              rs=preStatement.executeQuery();
                if (rs.next()) {
                    id=rs.getInt(1);
        }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return id;
    }
    
}
