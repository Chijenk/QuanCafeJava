/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.DatHang;
import pojo.Mon;



/**
 *
 * @author Dang Hoang Minh
 */
public class DatHangDAO {
     public static boolean themDatHang(DatHang dh) {
        boolean kq =false;
       // Định dạng ngày tháng để phù hợp với cơ sở dữ liệu
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Định dạng ngày hiện tại theo định dạng đã khai báo
        String ngayDatFormatted = dateFormat.format(dh.getNgayDat());
       

// Giả sử NhanVienID, BanID, NgayDat, và TongGia là các biến đã được khai báo và khởi tạo giá trị. 

        String sql = String.format("INSERT INTO DatHang (NhanVienID, BanID, NgayDat, TongGia) VALUES (%d, %d, '%s', %.2f)",dh.getNhanVienID(), dh.getBanID(), ngayDatFormatted, dh.getTongGia());
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
     public static int layMaDHLonNhat() {
        String sql = "SELECT MAX(DatHangID)  FROM DatHang";
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
             Logger.getLogger(DatHangDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
           
       

        return maLonNhat;
    }
    public static ArrayList<DatHang> layDanhSachDatHang() {
    ArrayList<DatHang> dsDatHang = new ArrayList<>();
    try {
        String sql = "Select * from DatHang";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
       ResultSet rs = provider.executeQuery(sql);

        while (rs.next()) {
            DatHang dathang = new DatHang();
            dathang.setDatHangID(rs.getInt(1));
            dathang.setBanID(rs.getInt(2));
            dathang.setNhanVienID(rs.getInt(3));
            dathang.setNgayDat(rs.getTimestamp(4)); // Use getTimestamp for full date and time
            dathang.setTongGia(rs.getFloat(5));
            dsDatHang.add(dathang);
        }
        provider.close();
    } catch (SQLException ex) {
        Logger.getLogger(DatHangDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return dsDatHang;
}

     public static boolean xoaDatHang(int DatHangID){
        boolean kq = false;
        String sql = String.format("DELETE FROM DatHang where DatHangID=%d", DatHangID);
        SQLServerDataProvider provider=new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
        }
        provider.close();
        return kq;
    }
//    public int capNhatCTDH(int datHangIDmoi, int datHangIDCu)
//    {
//        
//        String sql=String.format("UPDATE ChiTietDonHang set DatHangID=%d where DatHangID =%d",datHangIDmoi,datHangIDCu);
//        SQLServerDataProvider provider=new SQLServerDataProvider();
//        provider.open();
//        int n = provider.executeUpdate(sql);
//       
//        provider.close();
//        return n;
//    }
    public static boolean capNhatDathang(DatHang dh){
        boolean kq = false;
        String sql=String.format("UPDATE DatHang set BanID='%s' , NhanVienID='%s'"+"Where DatHangID=%d",dh.getBanID(),dh.getNhanVienID(),dh.getDatHangID());
        SQLServerDataProvider provider=new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
          
        }
        provider.close();
        return kq;
}
     public static boolean capNhatTongTien(float gia,int iddh){
        boolean kq = false;
        String sql=String.format("UPDATE DatHang set  TongGia=TongGia+'%f'"+"Where DatHangID=%d",gia,iddh);
        SQLServerDataProvider provider=new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
          
        }
        provider.close();
        return kq;
}
    
    public static ArrayList<DatHang> timkiemDatHang(String banID)
    {
       ArrayList<DatHang> dsDatHang=new ArrayList<DatHang>();
  
    
          PreparedStatement preStatement=null;
         
          ResultSet rs;
          
          String sql="SELECT * FROM DatHang WHERE DatHangID LIKE ?";
         try {
             SQLServerDataProvider provider = new SQLServerDataProvider();
             
             provider.open();
             preStatement=provider.getConnection().prepareStatement(sql);
              preStatement.setString(1, "%" + banID + "%");
              rs=preStatement.executeQuery();
               while(rs.next())
            {
                DatHang dathang=new DatHang();
                dathang.setDatHangID(rs.getInt(1));
                dathang.setBanID(rs.getInt(2));
                dathang.setNhanVienID(rs.getInt(3));
                dathang.setNgayDat(rs.getDate(4));
                dathang.setTongGia(rs.getFloat(5));
                dsDatHang.add(dathang);
                
                
            }
         } catch (SQLException ex) {
             Logger.getLogger(MonDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
 
        return dsDatHang;
}
    
   
}
