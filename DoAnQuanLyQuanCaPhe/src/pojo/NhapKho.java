package pojo;

import java.util.Date;

public class NhapKho {
    private int NhapKhoID;
    private int NhanVienID;
    private String TenNhanVien;
    private int NguyenLieuID;
    private String TenNguyenLieu;
    private int NhaCungCapID;
    private String TenNhaCungCap;
    private int SoLuongNhap;
    private double GiaNhap;
    private Date NgayNhap;

    // Constructors
    public NhapKho() {
    }

    public NhapKho(int NhanVienID, String TenNhanVien, int NguyenLieuID, String TenNguyenLieu,
                   int NhaCungCapID, String TenNhaCungCap, int SoLuongNhap, double GiaNhap, Date NgayNhap) {
        this.NhanVienID = NhanVienID;
        this.TenNhanVien = TenNhanVien;
        this.NguyenLieuID = NguyenLieuID;
        this.TenNguyenLieu = TenNguyenLieu;
        this.NhaCungCapID = NhaCungCapID;
        this.TenNhaCungCap = TenNhaCungCap;
        this.SoLuongNhap = SoLuongNhap;
        this.GiaNhap = GiaNhap;
        this.NgayNhap = NgayNhap;
    }

    public NhapKho(int nhapKhoID, int nhanVienID, String tenNhanVien, int nguyenLieuID, String tenNguyenLieu, int nhaCungCapID, String tenNhaCungCap, int soLuongNhap, double giaNhap, Date ngayNhap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters and Setters
    public int getNhapKhoID() {
        return NhapKhoID;
    }

    public void setNhapKhoID(int NhapKhoID) {
        this.NhapKhoID = NhapKhoID;
    }

    public int getNhanVienID() {
        return NhanVienID;
    }

    public void setNhanVienID(int NhanVienID) {
        this.NhanVienID = NhanVienID;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public int getNguyenLieuID() {
        return NguyenLieuID;
    }

    public void setNguyenLieuID(int NguyenLieuID) {
        this.NguyenLieuID = NguyenLieuID;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String TenNguyenLieu) {
        this.TenNguyenLieu = TenNguyenLieu;
    }

    public int getNhaCungCapID() {
        return NhaCungCapID;
    }

    public void setNhaCungCapID(int NhaCungCapID) {
        this.NhaCungCapID = NhaCungCapID;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public int getSoLuongNhap() {
        return SoLuongNhap;
    }

    public void setSoLuongNhap(int SoLuongNhap) {
        this.SoLuongNhap = SoLuongNhap;
    }

    public double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(double GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    // toString method
    @Override
    public String toString() {
        return "NhapKho{" +
                "NhapKhoID=" + NhapKhoID +
                ", NhanVienID=" + NhanVienID +
                ", TenNhanVien='" + TenNhanVien + '\'' +
                ", NguyenLieuID=" + NguyenLieuID +
                ", TenNguyenLieu='" + TenNguyenLieu + '\'' +
                ", NhaCungCapID=" + NhaCungCapID +
                ", TenNhaCungCap='" + TenNhaCungCap + '\'' +
                ", SoLuongNhap=" + SoLuongNhap +
                ", GiaNhap=" + GiaNhap +
                ", NgayNhap=" + NgayNhap +
                '}';
    }
}
