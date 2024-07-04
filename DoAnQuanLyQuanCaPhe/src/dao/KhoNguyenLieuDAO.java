package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.KhoNguyenLieu;

public class KhoNguyenLieuDAO {
    private SQLServerDataProvider dataProvider;

    public KhoNguyenLieuDAO() {
        dataProvider = new SQLServerDataProvider();
    }

    public static boolean capNhatSLTon(int NguyenLieuID, float sl, int slTieuTon) {
        boolean kq = false;
        String sql = "UPDATE KhoNguyenLieu SET SoLuongTon = SoLuongTon - (? * ?) WHERE NguyenLieuID = ?";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();

        try (PreparedStatement stmt = provider.getConnection().prepareStatement(sql)) {
            stmt.setFloat(1, sl);
            stmt.setInt(2, slTieuTon);
            stmt.setInt(3, NguyenLieuID);

            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                kq = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            provider.close();
        }

        return kq;
    }

    public List<KhoNguyenLieu> getAll() {
        List<KhoNguyenLieu> dsNguyenLieu = new ArrayList<>();
        String sql = "SELECT * FROM KhoNguyenLieu";
        dataProvider.open();

        try (ResultSet rs = dataProvider.executeQuery(sql)) {
            while (rs.next()) {
                KhoNguyenLieu nl = new KhoNguyenLieu();
                nl.setNguyenLieuID(rs.getInt("NguyenLieuID"));
                nl.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                nl.setDonVi(rs.getString("DonVi"));
                nl.setGiaTheoDonVi(rs.getFloat("GiaTheoDonVi"));
                nl.setSoLuongTon(rs.getFloat("SoLuongTon"));
                dsNguyenLieu.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return dsNguyenLieu;
    }

    public KhoNguyenLieu getById(int id) {
        KhoNguyenLieu nl = null;
        String sql = "SELECT * FROM KhoNguyenLieu WHERE NguyenLieuID = ?";
        dataProvider.open();

        try (PreparedStatement stmt = dataProvider.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nl = new KhoNguyenLieu();
                    nl.setNguyenLieuID(rs.getInt("NguyenLieuID"));
                    nl.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                    nl.setDonVi(rs.getString("DonVi"));
                    nl.setGiaTheoDonVi(rs.getFloat("GiaTheoDonVi"));
                    nl.setSoLuongTon(rs.getFloat("SoLuongTon"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return nl;
    }

    public boolean insert(KhoNguyenLieu nl) {
        boolean result = false;
        String sql = "INSERT INTO KhoNguyenLieu (NguyenLieuID, TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (?, ?, ?, ?, ?)";
        dataProvider.open();

        try (PreparedStatement stmt = dataProvider.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, nl.getNguyenLieuID());
            stmt.setString(2, nl.getTenNguyenLieu());
            stmt.setString(3, nl.getDonVi());
            stmt.setFloat(4, nl.getGiaTheoDonVi());
            stmt.setFloat(5, nl.getSoLuongTon());

            int n = stmt.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return result;
    }

    public boolean update(KhoNguyenLieu nl) {
        boolean result = false;
        String sql = "UPDATE KhoNguyenLieu SET TenNguyenLieu = ?, DonVi = ?, GiaTheoDonVi = ?, SoLuongTon = ? WHERE NguyenLieuID = ?";
        dataProvider.open();

        try (PreparedStatement stmt = dataProvider.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nl.getTenNguyenLieu());
            stmt.setString(2, nl.getDonVi());
            stmt.setFloat(3, nl.getGiaTheoDonVi());
            stmt.setFloat(4, nl.getSoLuongTon());
            stmt.setInt(5, nl.getNguyenLieuID());

            int n = stmt.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        String sql = "DELETE FROM KhoNguyenLieu WHERE NguyenLieuID = ?";
        dataProvider.open();

        try (PreparedStatement stmt = dataProvider.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            int n = stmt.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataProvider.close();
        }

        return result;
    }
    public List<KhoNguyenLieu> findByName(String tenNguyenLieu) {
    List<KhoNguyenLieu> dsNguyenLieu = new ArrayList<>();
    String sql = "SELECT * FROM KhoNguyenLieu WHERE TenNguyenLieu LIKE ?";
    dataProvider.open();

    try (PreparedStatement stmt = dataProvider.getConnection().prepareStatement(sql)) {
        stmt.setString(1, "%" + tenNguyenLieu + "%");
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KhoNguyenLieu nl = new KhoNguyenLieu();
                nl.setNguyenLieuID(rs.getInt("NguyenLieuID"));
                nl.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                nl.setDonVi(rs.getString("DonVi"));
                nl.setGiaTheoDonVi(rs.getFloat("GiaTheoDonVi"));
                nl.setSoLuongTon(rs.getFloat("SoLuongTon"));
                dsNguyenLieu.add(nl);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(KhoNguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        dataProvider.close();
    }

    return dsNguyenLieu;
}
}
