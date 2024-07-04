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
import pojo.Ban;


/**
 *
 * @author Dang Hoang Minh
 */
public class BanDAO {
    public static ArrayList<Ban> layDanhSachTatCaBan()
    {
    ArrayList<Ban> dsBan=new ArrayList<Ban>();
    try {
    String sql="Select * from Ban   ";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
        ResultSet rs=provider.executeQuery(sql);
        
            while(rs.next())
            {
                Ban ban=new Ban();
                ban.setBanID(rs.getInt(1));
                ban.setTenBan(rs.getString(2));
                ban.setSoGhe(rs.getInt(3));
                ban.setTrangThai(rs.getString(4));
                dsBan.add(ban);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsBan;
    }
    public static ArrayList<Ban> layDanhSachBan()
    {
    ArrayList<Ban> dsBan=new ArrayList<Ban>();
    try {
    String sql="Select * from Ban where TrangThai !=N'Bàn gộp' and TrangThai!= N'Bàn tách'  ";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
        ResultSet rs=provider.executeQuery(sql);
        
            while(rs.next())
            {
                Ban ban=new Ban();
                ban.setBanID(rs.getInt(1));
                ban.setTenBan(rs.getString(2));
                ban.setSoGhe(rs.getInt(3));
                ban.setTrangThai(rs.getString(4));
                dsBan.add(ban);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsBan;
    }
    public static boolean capNhaTrangThaiBanTach(int BanID)
    {
        boolean kq=true;
        String sql = "update Ban set TrangThai=N'Bàn tách' where BanID=?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
       
        try {
            stmt = provider.getConnection().prepareStatement(sql);
             // Thiết lập các tham số cho câu lệnh SQL từ đối tượng ChiTietDatHang truyền vào
            stmt.setInt(1, BanID);
            
            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                kq = true;
            }

            stmt.close();
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    return kq;
    }
    public static boolean capNhaTrangThaiBanGop(int BanID)
    {
        boolean kq=true;
        String sql = "update Ban set TrangThai=N'Bàn gộp' where BanID=?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
       
        try {
            stmt = provider.getConnection().prepareStatement(sql);
             // Thiết lập các tham số cho câu lệnh SQL từ đối tượng ChiTietDatHang truyền vào
            stmt.setInt(1, BanID);
            
            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                kq = true;
            }

            stmt.close();
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    return kq;
    }
     public static boolean capNhaTrangThaiCoKhach(int BanID)
    {
        boolean kq=true;
        String sql = "update Ban set TrangThai=N'Có khách' where BanID=?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
       
        try {
            stmt = provider.getConnection().prepareStatement(sql);
             // Thiết lập các tham số cho câu lệnh SQL từ đối tượng ChiTietDatHang truyền vào
            stmt.setInt(1, BanID);
            
            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                kq = true;
            }

            stmt.close();
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    return kq;
    }
      public static boolean capNhaTrangThaiBanTrong(int BanID)
    {
        boolean kq=true;
        String sql = "update Ban set TrangThai=N'Còn trống' where BanID=?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        PreparedStatement stmt;
       
        try {
            stmt = provider.getConnection().prepareStatement(sql);
             // Thiết lập các tham số cho câu lệnh SQL từ đối tượng ChiTietDatHang truyền vào
            stmt.setInt(1, BanID);
            
            int rowCount = stmt.executeUpdate();
            if(rowCount > 0) {
                kq = true;
            }

            stmt.close();
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    return kq;
    }
      public static String layTrangThaiBan(int BanID) {
        String sql = "SELECT TrangThai from Ban where BanID=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String trangThai = "";

        
           
         try {
              SQLServerDataProvider provider =new SQLServerDataProvider();
              provider.open();
             pstmt = provider.getConnection().prepareStatement(sql);
             pstmt.setInt(1, BanID);
              rs = pstmt.executeQuery();
              
            if (rs.next()) {
                trangThai = rs.getString(1);
            }
         } catch (SQLException ex) {
             return "";
             
         }
           
       

        return trangThai;
    }
      public static boolean themBan(Ban pb) {
    boolean kq = false;
    String sql = "INSERT INTO Ban(TenBan, TrangThai, SoGhe) values(?, ?, ?)";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    try {
        PreparedStatement pstmt = provider.getConnection().prepareStatement(sql);
        pstmt.setString(1, pb.getTenBan());
        pstmt.setInt(3, pb.getSoGhe());
        pstmt.setString(2, pb.getTrangThai());
        
        int n = pstmt.executeUpdate();
        if (n == 1) {
            kq = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return kq;
}

      public static int layMaMonLonNhat() {
        String sql = "SELECT MAX(BanID)  FROM Ban";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int banLonNhat = 0;

        
           
         try {
              SQLServerDataProvider provider =new SQLServerDataProvider();
              provider.open();
             pstmt = provider.getConnection().prepareStatement(sql);
              rs = pstmt.executeQuery();

            if (rs.next()) {
                banLonNhat = rs.getInt(1);
            }
         } catch (SQLException ex) {
             return 0;
             
         }
         return banLonNhat;
     }
    public static boolean xoaBan(int BanID){
        boolean kq = false;
        String sql = String.format("DELETE FROM Ban where BanID=%d", BanID);
        SQLServerDataProvider provider=new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
        }
        provider.close();
        return kq;
    }
    public static boolean capNhatBan(Ban pb){
        boolean kq = false;
        
        String sql = String.format(
    "UPDATE Ban SET TenBan = N'%s', TrangThai = N'%s', SoGhe = %d WHERE BanID = %d",pb.getTenBan(), pb.getTrangThai(), pb.getSoGhe(),  pb.getBanID());
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
       public static ArrayList<Ban> timkiemBan(int BanID){
        ArrayList<Ban> dsBan=new ArrayList<>();
        try{
            String sql="SELECT * FROM Ban WHERE BanID LIKE '% "+BanID +"%'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                Ban pb=new Ban();
                pb.setBanID(rs.getInt("BanID"));
                pb.setTenBan(rs.getString("TenBan"));
                pb.setTrangThai(rs.getString("TrangThai"));
                pb.setSoGhe(rs.getInt("SoGhe"));
                
                dsBan.add(pb);
        }
            provider.close();
}
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsBan;
}
        public static int laySoChoNgoi(int BanID) {
        String sql = "SELECT SoGhe from Ban where BanID=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int sg=0;

        
           
         try {
              SQLServerDataProvider provider =new SQLServerDataProvider();
              provider.open();
             pstmt = provider.getConnection().prepareStatement(sql);
             pstmt.setInt(1, BanID);
              rs = pstmt.executeQuery();
              
            if (rs.next()) {
                sg = rs.getInt(1);
            }
         } catch (SQLException ex) {
             return 0;
             
         }
           
       

        return sg;
    }
       
}
