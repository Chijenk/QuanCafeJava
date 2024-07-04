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

/**
 *
 * @author Dang Hoang Minh
 */
public class Mon_KhoNguyenLieuDAO {
     public static ArrayList<Integer> layDanhSachNguyenLieu(int id)
     {
         ArrayList<Integer> dsnl=new ArrayList<>();
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT NguyenLieuID FROM Mon_NguyenLieu WHERE MonID = ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, id);
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                dsnl.add(rs.getInt(1));
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return dsnl;
     }
     public static float laySLSuDung(int idMon,int idNguyenLieu)
     {
         float gia=0;
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT SoLuongSuDungNguyenLieu FROM Mon_NguyenLieu WHERE MonID = ? and NguyenLieuID= ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, idMon);
              preStatement.setInt(2, idNguyenLieu);
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                gia=rs.getFloat(1);
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return gia;
     }
}
