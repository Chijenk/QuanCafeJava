/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.NhaCungCap;
/**
 *
 * @author phamh
 */
public class NhaCungCapDAO {
    private SQLServerDataProvider dataProvider;

    public NhaCungCapDAO() {
        dataProvider = new SQLServerDataProvider();
    }

    // Phương thức thêm nhà cung cấp mới
    public boolean insertNhaCungCap(NhaCungCap nhaCungCap) {
        String sql = "INSERT INTO NhaCungCap (TenNhaCungCap, ThongTinLienLac) VALUES (?, ?)";
        dataProvider.open();
        try {
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nhaCungCap.getTenNhaCungCap());
            preparedStatement.setString(2, nhaCungCap.getThongTinLienLac());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            dataProvider.close();
        }
    }

    // Phương thức lấy danh sách tất cả nhà cung cấp
    public List<NhaCungCap> getAllNhaCungCap() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM NhaCungCap";
        dataProvider.open();
        try {
            ResultSet rs = dataProvider.executeQuery(sql);
            while (rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setNhaCungCapID(rs.getInt("NhaCungCapID"));
                nhaCungCap.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                nhaCungCap.setThongTinLienLac(rs.getString("ThongTinLienLac"));
                list.add(nhaCungCap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }
        return list;
    }

    // Phương thức cập nhật thông tin nhà cung cấp
    public boolean updateNhaCungCap(NhaCungCap nhaCungCap) {
        String sql = "UPDATE NhaCungCap SET TenNhaCungCap = ?, ThongTinLienLac = ? WHERE NhaCungCapID = ?";
        dataProvider.open();
        try {
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nhaCungCap.getTenNhaCungCap());
            preparedStatement.setString(2, nhaCungCap.getThongTinLienLac());
            preparedStatement.setInt(3, nhaCungCap.getNhaCungCapID());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            dataProvider.close();
        }
    }

    // Phương thức xóa nhà cung cấp
    public boolean deleteNhaCungCap(int nhaCungCapID) {
        String sql = "DELETE FROM NhaCungCap WHERE NhaCungCapID = ?";
        dataProvider.open();
        try {
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, nhaCungCapID);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            dataProvider.close();
        }
    }

    // Phương thức lấy thông tin nhà cung cấp theo ID
    public NhaCungCap getNhaCungCapByID(int nhaCungCapID) {
        String sql = "SELECT * FROM NhaCungCap WHERE NhaCungCapID = ?";
        dataProvider.open();
        try {
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, nhaCungCapID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setNhaCungCapID(rs.getInt("NhaCungCapID"));
                nhaCungCap.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                nhaCungCap.setThongTinLienLac(rs.getString("ThongTinLienLac"));
                return nhaCungCap;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }
        return null;
    }
     public void displayNhacungcapDataOnTable(NhaCungCapTableModel tableModel) {
        String sql = "SELECT * FROM NhaCungCap";
        dataProvider.open();
        ResultSet resultSet = dataProvider.executeQuery(sql);
        try {
            List<NhaCungCap> nhaCungCapList = new ArrayList<>();
            while (resultSet.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setNhaCungCapID(resultSet.getInt("NhaCungCapID"));
                nhaCungCap.setTenNhaCungCap(resultSet.getString("TenNhaCungCap"));
                nhaCungCap.setThongTinLienLac(resultSet.getString("ThongTinLienLac"));
                nhaCungCapList.add(nhaCungCap);
            }
            tableModel.setData(nhaCungCapList);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }
    }
      public List<NhaCungCap> timKiemNhaCungCapTheoTen(String tenNhaCungCap) {
        String sql = "SELECT * FROM NhaCungCap WHERE NhaCungCapID LIKE ?";
        dataProvider.open();
        try {
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + tenNhaCungCap + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<NhaCungCap> nhaCungCapList = new ArrayList<>();
            while (resultSet.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setNhaCungCapID(resultSet.getInt("NhaCungCapID"));
                nhaCungCap.setTenNhaCungCap(resultSet.getString("TenNhaCungCap"));
                nhaCungCap.setThongTinLienLac(resultSet.getString("ThongTinLienLac"));
                nhaCungCapList.add(nhaCungCap);
            }
            return nhaCungCapList;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            dataProvider.close();
        }
    }
}
