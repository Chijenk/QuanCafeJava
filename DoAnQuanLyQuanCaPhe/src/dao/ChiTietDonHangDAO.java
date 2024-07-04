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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.ChiTietDatHang;

/**
 *
 * @author Dang Hoang Minh
 */
public class ChiTietDonHangDAO {
    
    
    
    
     public static ArrayList<ChiTietDatHang> layDanhSachCTDH()
    {
        ArrayList<ChiTietDatHang> dsCTDH=new ArrayList<ChiTietDatHang>();
    try {
    String sql="Select * from ChiTietDatHang";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
        ResultSet rs=provider.executeQuery(sql);
        
            while(rs.next())
            {
                ChiTietDatHang ctdh=new ChiTietDatHang();
                ctdh.setChiTietDatHangID(rs.getInt(1));
                ctdh.setDatHangID(rs.getInt(2));
                ctdh.setMonID(rs.getInt(3));
                ctdh.setSoLuong(rs.getInt(4));
                dsCTDH.add(ctdh);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsCTDH;
    }
    public static boolean themCTDH(ChiTietDatHang ctdh) {
    boolean ketQua = false;
    
        String sql = "INSERT INTO ChiTietDatHang ( DatHangID, MonID, SoLuong) VALUES ( ?, ?, ?)";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
         try {
                stmt = provider.getConnection().prepareStatement(sql);
                stmt.setInt(1, ctdh.getDatHangID());
                stmt.setInt(2, ctdh.getMonID());
                stmt.setInt(3, ctdh.getSoLuong());

                int rowCount = stmt.executeUpdate();
                if(rowCount > 0) {
                    ketQua = true;
        }

        stmt.close();
        provider.close();
   
         } catch (SQLException ex) {
             Logger.getLogger(ChiTietDonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
         }

      
     
    return ketQua;
}
    public static boolean suaCTDH(ChiTietDatHang ctdh) {
    boolean ketQua = false;
    
        String sql = "UPDATE ChiTietDatHang SET DatHangID = ?, MonID = ?, SoLuong = ? WHERE ChiTietDatHangID = ?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
         try {
             stmt = provider.getConnection().prepareStatement(sql);
               // Thiết lập các tham số cho câu lệnh SQL từ đối tượng ChiTietDatHang truyền vào
            stmt.setInt(1, ctdh.getDatHangID());
            stmt.setInt(2, ctdh.getMonID());
            stmt.setInt(3, ctdh.getSoLuong());
            stmt.setInt(4, ctdh.getChiTietDatHangID()); // Giả định rằng ID là khóa chính và không bị thay đổi

            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                ketQua = true;
            }

            stmt.close();
            provider.close();
    
         } catch (SQLException ex) {
             Logger.getLogger(ChiTietDonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
         }

      
    return ketQua;
}
    
     public static boolean capNhatSL(int slCapNhat,int idCTHD) {
    boolean ketQua = false;
    
        String sql = "UPDATE ChiTietDatHang SET  SoLuong = SoLuong + ? WHERE ChiTietDatHangID = ?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
         try {
             stmt = provider.getConnection().prepareStatement(sql);
               // Thiết lập các tham số cho câu lệnh SQL từ đối tượng ChiTietDatHang truyền vào
            stmt.setInt(1, slCapNhat);
            stmt.setInt(2,idCTHD);
           
            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                ketQua = true;
            }

            stmt.close();
            provider.close();
    
         } catch (SQLException ex) {
             Logger.getLogger(ChiTietDonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
         }

      
    return ketQua;
}
     
     
      public static ArrayList<ChiTietDatHang> layCTDHTheoDatHangID(int dathangID)
    {  
        ArrayList<ChiTietDatHang> dsMon=new ArrayList<ChiTietDatHang>();
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT * FROM ChiTietDatHang WHERE DatHangID=  ? ";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setInt(1, dathangID);
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                ChiTietDatHang ctdh=new ChiTietDatHang();
                ctdh.setChiTietDatHangID(rs.getInt(1));
                ctdh.setDatHangID(rs.getInt(2));
                ctdh.setMonID(rs.getInt(3));
                ctdh.setSoLuong(rs.getInt(4));
                dsMon.add(ctdh);
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return dsMon;
    }
     
    public static boolean xoaCTDH(int chiTietDatHangID) {
    boolean ketQua = false;
   
        String sql = "DELETE FROM ChiTietDatHang WHERE ChiTietDatHangID = ?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
         try {
             stmt = provider.getConnection().prepareStatement(sql);
             stmt.setInt(1, chiTietDatHangID);

            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                ketQua = true; // Xóa thành công
            }

            stmt.close();
            provider.close();
         } catch (SQLException ex) {
             Logger.getLogger(ChiTietDonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
         }

        
   
    return ketQua;
}
     public static int layMaCTDHLonNhat() {
        String sql = "SELECT MAX(ChiTietDatHangID)  FROM ChiTietDonHang";
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
      public static int layMaCTDHTheoIDDatHangIDMonAn(int idDatHang,int idMon) {
        String sql = "SELECT ChiTietDatHangID  FROM ChiTietDonHang where DatHangID=? and MonID =?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int maLonNhat = 0;

        
           
         try {
              SQLServerDataProvider provider =new SQLServerDataProvider();
              provider.open();
             pstmt = provider.getConnection().prepareStatement(sql);
             pstmt.setInt(1, idDatHang);
             pstmt.setInt(2, idMon);
              rs = pstmt.executeQuery();

            if (rs.next()) {
                maLonNhat = rs.getInt(1);
            }
         } catch (SQLException ex) {
             return 0;
             
         }
           
       

        return maLonNhat;
    }

}
