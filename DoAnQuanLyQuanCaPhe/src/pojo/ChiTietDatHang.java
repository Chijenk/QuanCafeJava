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
public class ChiTietDatHang {

    public ChiTietDatHang(int ChiTietDatHangID, int DatHangID, int MonID, int SoLuong) {
        this.ChiTietDatHangID = ChiTietDatHangID;
        this.DatHangID = DatHangID;
        this.MonID = MonID;
        this.SoLuong = SoLuong;
    }

    public int getChiTietDatHangID() {
        return ChiTietDatHangID;
    }

    public void setChiTietDatHangID(int ChiTietDatHangID) {
        this.ChiTietDatHangID = ChiTietDatHangID;
    }

    public int getDatHangID() {
        return DatHangID;
    }

    public void setDatHangID(int DatHangID) {
        this.DatHangID = DatHangID;
    }

    public int getMonID() {
        return MonID;
    }

    public void setMonID(int MonID) {
        this.MonID = MonID;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public ChiTietDatHang() {
    }
    int ChiTietDatHangID,DatHangID,MonID,SoLuong;
    
    
    
}
