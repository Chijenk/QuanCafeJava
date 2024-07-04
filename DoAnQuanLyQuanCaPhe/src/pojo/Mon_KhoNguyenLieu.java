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
public class Mon_KhoNguyenLieu {

    public Mon_KhoNguyenLieu() {
    }

    public Mon_KhoNguyenLieu(int MonID, int NguyenLieuID, float SoLuong) {
        this.MonID = MonID;
        this.NguyenLieuID = NguyenLieuID;
        this.SoLuong = SoLuong;
    }

    public int getMonID() {
        return MonID;
    }

    public void setMonID(int MonID) {
        this.MonID = MonID;
    }

    public int getNguyenLieuID() {
        return NguyenLieuID;
    }

    public void setNguyenLieuID(int NguyenLieuID) {
        this.NguyenLieuID = NguyenLieuID;
    }

    public float getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(float SoLuong) {
        this.SoLuong = SoLuong;
    }
    int MonID,NguyenLieuID;
    float SoLuong;
    
}
