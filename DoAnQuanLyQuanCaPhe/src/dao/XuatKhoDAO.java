package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.XuatKho;

public class XuatKhoDAO {
    private SQLServerDataProvider dataProvider;

    public XuatKhoDAO() {
        dataProvider = new SQLServerDataProvider();
    }

    public List<XuatKho> getAllXuatKho() {
        List<XuatKho> danhSachXuatKho = new ArrayList<>();
        String sql = "SELECT * FROM XuatKho";

        try {
            dataProvider.open();
            ResultSet resultSet = dataProvider.executeQuery(sql);
            while (resultSet.next()) {
                XuatKho xuatKho = new XuatKho();
                xuatKho.setXuatKhoID(resultSet.getInt("XuatKhoID"));
                xuatKho.setNhanVienID(resultSet.getInt("NhanVienID"));
                xuatKho.setTenNhanVien(resultSet.getString("TenNhanVien"));
                xuatKho.setNguyenLieuID(resultSet.getInt("NguyenLieuID"));
                xuatKho.setTenNguyenLieu(resultSet.getString("TenNguyenLieu"));
                xuatKho.setSoLuongXuat(resultSet.getInt("SoLuongXuat"));
                xuatKho.setNgayXuat(resultSet.getDate("NgayXuat"));
                danhSachXuatKho.add(xuatKho);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return danhSachXuatKho;
    }

    public int insertXuatKho(XuatKho xuatKho) {
        int rowsAffected = -1;
        String sql = "INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            dataProvider.open();
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, xuatKho.getNhanVienID());
            preparedStatement.setString(2, xuatKho.getTenNhanVien());
            preparedStatement.setInt(3, xuatKho.getNguyenLieuID());
            preparedStatement.setString(4, xuatKho.getTenNguyenLieu());
            preparedStatement.setInt(5, xuatKho.getSoLuongXuat());
            preparedStatement.setDate(6, new java.sql.Date(xuatKho.getNgayXuat().getTime()));

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return rowsAffected;
    }

    public int updateXuatKho(XuatKho xuatKho) {
        int rowsAffected = -1;
        String sql = "UPDATE XuatKho SET NhanVienID=?, TenNhanVien=?, NguyenLieuID=?, TenNguyenLieu=?, " +
                "SoLuongXuat=?, NgayXuat=? WHERE XuatKhoID=?";

        try {
            dataProvider.open();
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, xuatKho.getNhanVienID());
            preparedStatement.setString(2, xuatKho.getTenNhanVien());
            preparedStatement.setInt(3, xuatKho.getNguyenLieuID());
            preparedStatement.setString(4, xuatKho.getTenNguyenLieu());
            preparedStatement.setInt(5, xuatKho.getSoLuongXuat());
            preparedStatement.setDate(6, new java.sql.Date(xuatKho.getNgayXuat().getTime()));
            preparedStatement.setInt(7, xuatKho.getXuatKhoID());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return rowsAffected;
    }

    public int deleteXuatKho(int xuatKhoID) {
        int rowsAffected = -1;
        String sql = "DELETE FROM XuatKho WHERE XuatKhoID=?";

        try {
            dataProvider.open();
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, xuatKhoID);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return rowsAffected;
    }

    public List<XuatKho> findXuatKho(int xuatKhoID, int nhanVienID, int nguyenLieuID) {
        List<XuatKho> danhSachKetQua = new ArrayList<>();
        String sql = "SELECT * FROM XuatKho WHERE 1=1";

        if (xuatKhoID != 0) {
            sql += " AND XuatKhoID = " + xuatKhoID;
        }
        if (nhanVienID != 0) {
            sql += " AND NhanVienID = " + nhanVienID;
        }
        if (nguyenLieuID != 0) {
            sql += " AND NguyenLieuID = " + nguyenLieuID;
        }

        try {
            dataProvider.open();
            ResultSet resultSet = dataProvider.executeQuery(sql);
            while (resultSet.next()) {
                XuatKho xuatKho = new XuatKho();
                xuatKho.setXuatKhoID(resultSet.getInt("XuatKhoID"));
                xuatKho.setNhanVienID(resultSet.getInt("NhanVienID"));
                xuatKho.setTenNhanVien(resultSet.getString("TenNhanVien"));
                xuatKho.setNguyenLieuID(resultSet.getInt("NguyenLieuID"));
                xuatKho.setTenNguyenLieu(resultSet.getString("TenNguyenLieu"));
                xuatKho.setSoLuongXuat(resultSet.getInt("SoLuongXuat"));
                xuatKho.setNgayXuat(resultSet.getDate("NgayXuat"));
                danhSachKetQua.add(xuatKho);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return danhSachKetQua;
    }
}
