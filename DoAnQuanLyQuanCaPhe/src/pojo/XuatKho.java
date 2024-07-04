/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;
import java.math.BigDecimal;
import java.util.Date;
/**
 *
 * @author phamh
 */
import java.util.Date;

public class XuatKho {
    private int xuatKhoID;
    private int nhanVienID;
    private String tenNhanVien;
    private int nguyenLieuID;
    private String tenNguyenLieu;
    private int soLuongXuat;
    private Date ngayXuat;

    public int getXuatKhoID() {
        return xuatKhoID;
    }

    public void setXuatKhoID(int xuatKhoID) {
        this.xuatKhoID = xuatKhoID;
    }

    public int getNhanVienID() {
        return nhanVienID;
    }

    public void setNhanVienID(int nhanVienID) {
        this.nhanVienID = nhanVienID;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getNguyenLieuID() {
        return nguyenLieuID;
    }

    public void setNguyenLieuID(int nguyenLieuID) {
        this.nguyenLieuID = nguyenLieuID;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
}
