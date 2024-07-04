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
public class Mon {
    public Mon() {
    }

    public Mon(String TenMon, String MoTa, double Gia, int DanhMucID, int MonID, String HinhAnh) {
        this.TenMon = TenMon;
        this.MoTa = MoTa;
        this.Gia = Gia;
        this.DanhMucID = DanhMucID;
        this.MonID = MonID;
        this.HinhAnh = HinhAnh;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public int getDanhMucID() {
        return DanhMucID;
    }

    public void setDanhMucID(int DanhMucID) {
        this.DanhMucID = DanhMucID;
    }

    public int getMonID() {
        return MonID;
    }

    public void setMonID(int MonID) {
        this.MonID = MonID;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

   
    
    String TenMon,MoTa;
    double Gia;
    int DanhMucID;
    int MonID;
    String HinhAnh;
    
}
