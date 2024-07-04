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
import pojo.DanhMuc;
import pojo.Mon;

/**
 *
 * @author Dang Hoang Minh
 */
public class DanhMucDAO {
     public static ArrayList<DanhMuc> layDanhSachDanhMuc()
    {ArrayList<DanhMuc> dsDM=new ArrayList<DanhMuc>();
    try {
    String sql="Select * from DanhMuc";
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
        ResultSet rs=provider.executeQuery(sql);
        
            while(rs.next())
            {
                DanhMuc dm=new DanhMuc();
                dm.setDanhMucID(rs.getInt(1));
                dm.setTenDanhMuc(rs.getString(2));
                
                dsDM.add(dm);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsDM;
    }
    
     
}
