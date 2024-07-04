/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Date;



/**
 *
 * @author Dang Hoang Minh
 */
public class DatHang {
     public DatHang() {
    }

    public DatHang(int DatHangID, int NhanVienID, int BanID, Date NgayDat, float TongGia) {
        this.DatHangID = DatHangID;
        this.NhanVienID = NhanVienID;
        this.BanID = BanID;
        this.NgayDat = NgayDat;
        this.TongGia = TongGia;
    }

    int DatHangID;
    int NhanVienID;
    int BanID;
    Date NgayDat;
    float TongGia;

    public int getDatHangID() {
        return DatHangID;
    }

    public void setDatHangID(int DatHangID) {
        this.DatHangID = DatHangID;
    }

    public int getNhanVienID() {
        return NhanVienID;
    }

    public void setNhanVienID(int NhanVienID) {
        this.NhanVienID = NhanVienID;
    }

    public int getBanID() {
        return BanID;
    }

    public void setBanID(int BanID) {
        this.BanID = BanID;
    }

    public Date getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(Date NgayDat) {
        this.NgayDat = NgayDat;
    }

    public float getTongGia() {
        return TongGia;
    }

    public void setTongGia(float TongGia) {
        this.TongGia = TongGia;
    }
    
}
