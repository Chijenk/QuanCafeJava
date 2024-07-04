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
import pojo.NhanVien;

/**
 *
 * @author Dang Hoang Minh
 */
public class NhanVienDAO {
    public static ArrayList<NhanVien> layDanhSachNV()
    {
        ArrayList<NhanVien> dsnv=new ArrayList<NhanVien>();
    try {
    String sql="Select * from NhanVien";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
        ResultSet rs=provider.executeQuery(sql);
        
            while(rs.next())
            {
                NhanVien nv=new NhanVien();
                nv.setID(rs.getInt(1));
                nv.setTenNV(rs.getString(3));
                nv.setHoNV(rs.getString(2));
                nv.setViTri(rs.getString(4));
                nv.setLuong(rs.getDouble(5));
                dsnv.add(nv);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnv;
    }
     public static String layViTriNhanVien(int id)
      {
          String vt="";
         NhanVien nv= new NhanVien();
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT ViTri from NhanVien WHERE NhanVienID = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, id);
               
              rs=preStatement.executeQuery();
                if (rs.next()) {
                    vt=rs.getString(1);
        }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return vt;
    }
      public static String layTenNV(int id)
      {
          String vt="";
         NhanVien nv= new NhanVien();
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT TenNV from NhanVien WHERE NhanVienID = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, id);
               
              rs=preStatement.executeQuery();
                if (rs.next()) {
                    vt=rs.getString(1);
        }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return vt;
    }
}
