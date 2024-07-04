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
public class Ban {

    public Ban() {
    }

    public Ban(int BanID, String TenBan, String TrangThai,int SoGhe ) {
        this.BanID = BanID;
        this.TenBan = TenBan;
        this.TrangThai = TrangThai;
        this.SoGhe = SoGhe;
        
    }

    public int getBanID() {
        return BanID;
    }

    public void setBanID(int BanID) {
        this.BanID = BanID;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public int getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(int SoGhe) {
        this.SoGhe = SoGhe;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    int BanID;
    String TenBan;
    int SoGhe;
    String TrangThai;
}
