package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.NhapKho;


public class NhapKhoDAO {
    private SQLServerDataProvider dataProvider;

    public NhapKhoDAO() {
        dataProvider = new SQLServerDataProvider();
    }

    public List<NhapKho> getAllNhapKho() {
        List<NhapKho> danhSachNhapKho = new ArrayList<>();
        String sql = "SELECT * FROM NhapKho";

        try {
            dataProvider.open();
            ResultSet resultSet = dataProvider.executeQuery(sql);
            while (resultSet.next()) {
                NhapKho nhapKho = new NhapKho();
                nhapKho.setNhapKhoID(resultSet.getInt("NhapKhoID"));
                nhapKho.setNhanVienID(resultSet.getInt("NhanVienID"));
                nhapKho.setTenNhanVien(resultSet.getString("TenNhanVien"));
                nhapKho.setNguyenLieuID(resultSet.getInt("NguyenLieuID"));
                nhapKho.setTenNguyenLieu(resultSet.getString("TenNguyenLieu"));
                nhapKho.setNhaCungCapID(resultSet.getInt("NhaCungCapID"));
                nhapKho.setTenNhaCungCap(resultSet.getString("TenNhaCungCap"));
                nhapKho.setSoLuongNhap(resultSet.getInt("SoLuongNhap"));
                nhapKho.setGiaNhap(resultSet.getDouble("GiaNhap"));
                nhapKho.setNgayNhap(resultSet.getDate("NgayNhap"));
                danhSachNhapKho.add(nhapKho);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return danhSachNhapKho;
    }

    public int insertNhapKho(NhapKho nhapKho) {
        int rowsAffected = -1;
        String sql = "INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, " +
                "NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            dataProvider.open();
            PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, nhapKho.getNhanVienID());
            preparedStatement.setString(2, nhapKho.getTenNhanVien());
            preparedStatement.setInt(3, nhapKho.getNguyenLieuID());
            preparedStatement.setString(4, nhapKho.getTenNguyenLieu());
            preparedStatement.setInt(5, nhapKho.getNhaCungCapID());
            preparedStatement.setString(6, nhapKho.getTenNhaCungCap());
            preparedStatement.setInt(7, nhapKho.getSoLuongNhap());
            preparedStatement.setDouble(8, nhapKho.getGiaNhap());
            preparedStatement.setDate(9, new java.sql.Date(nhapKho.getNgayNhap().getTime()));

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhapKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return rowsAffected;
    }
    public int updateNhapKho(NhapKho nhapKho) {
    int rowsAffected = -1;
    String sql = "UPDATE NhapKho SET NhanVienID=?, TenNhanVien=?, NguyenLieuID=?, TenNguyenLieu=?, " +
            "NhaCungCapID=?, TenNhaCungCap=?, SoLuongNhap=?, GiaNhap=?, NgayNhap=? " +
            "WHERE NhapKhoID=?";

    try {
        dataProvider.open();
        PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, nhapKho.getNhanVienID());
        preparedStatement.setString(2, nhapKho.getTenNhanVien());
        preparedStatement.setInt(3, nhapKho.getNguyenLieuID());
        preparedStatement.setString(4, nhapKho.getTenNguyenLieu());
        preparedStatement.setInt(5, nhapKho.getNhaCungCapID());
        preparedStatement.setString(6, nhapKho.getTenNhaCungCap());
        preparedStatement.setInt(7, nhapKho.getSoLuongNhap());
        preparedStatement.setDouble(8, nhapKho.getGiaNhap());
        preparedStatement.setDate(9, new java.sql.Date(nhapKho.getNgayNhap().getTime()));
        preparedStatement.setInt(10, nhapKho.getNhapKhoID());

        rowsAffected = preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(NhapKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        dataProvider.close();
    }

    return rowsAffected;
}
public int deleteNhapKho(int nhapKhoID) {
    int rowsAffected = -1;
    String sql = "DELETE FROM NhapKho WHERE NhapKhoID=?";

    try {
        dataProvider.open();
        PreparedStatement preparedStatement = dataProvider.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, nhapKhoID);
        rowsAffected = preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(NhapKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        dataProvider.close();
    }

    return rowsAffected;
}
 public List<NhapKho> findNhapKho(int nhapKhoID, int nhanVienID, int nguyenLieuID, int nhaCungCapID) {
        List<NhapKho> danhSachKetQua = new ArrayList<>();
        String sql = "SELECT * FROM NhapKho WHERE 1=1";

        if (nhapKhoID != 0) {
            sql += " AND NhapKhoID = " + nhapKhoID;
        }
        if (nhanVienID != 0) {
            sql += " AND NhanVienID = " + nhanVienID;
        }
        if (nguyenLieuID != 0) {
            sql += " AND NguyenLieuID = " + nguyenLieuID;
        }
        if (nhaCungCapID != 0) {
            sql += " AND NhaCungCapID = " + nhaCungCapID;
        }

        try {
            dataProvider.open();
            ResultSet resultSet = dataProvider.executeQuery(sql);
            while (resultSet.next()) {
                NhapKho nhapKho = new NhapKho();
                nhapKho.setNhapKhoID(resultSet.getInt("NhapKhoID"));
                nhapKho.setNhanVienID(resultSet.getInt("NhanVienID"));
                nhapKho.setTenNhanVien(resultSet.getString("TenNhanVien"));
                nhapKho.setNguyenLieuID(resultSet.getInt("NguyenLieuID"));
                nhapKho.setTenNguyenLieu(resultSet.getString("TenNguyenLieu"));
                nhapKho.setNhaCungCapID(resultSet.getInt("NhaCungCapID"));
                nhapKho.setTenNhaCungCap(resultSet.getString("TenNhaCungCap"));
                nhapKho.setSoLuongNhap(resultSet.getInt("SoLuongNhap"));
                nhapKho.setGiaNhap(resultSet.getDouble("GiaNhap"));
                nhapKho.setNgayNhap(resultSet.getDate("NgayNhap"));
                danhSachKetQua.add(nhapKho);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return danhSachKetQua;
    }

}
