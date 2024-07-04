/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Dang Hoang Minh
 */
public class KhoNguyenLieu {

    public KhoNguyenLieu() {
    }

    public KhoNguyenLieu(int NguyenLieuID, String TenNguyenLieu, String DonVi, Float GiaTheoDonVi, Float SoLuongTon) {
        this.NguyenLieuID = NguyenLieuID;
        this.TenNguyenLieu = TenNguyenLieu;
        this.DonVi = DonVi;
        this.GiaTheoDonVi = GiaTheoDonVi;
        this.SoLuongTon = SoLuongTon;
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

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public Float getGiaTheoDonVi() {
        return GiaTheoDonVi;
    }

    public void setGiaTheoDonVi(Float GiaTheoDonVi) {
        this.GiaTheoDonVi = GiaTheoDonVi;
    }

    public Float getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(Float SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }
    int NguyenLieuID;
    String TenNguyenLieu,DonVi;
    Float GiaTheoDonVi,SoLuongTon;
}
