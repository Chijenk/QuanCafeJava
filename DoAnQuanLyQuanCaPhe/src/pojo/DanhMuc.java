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
public class DanhMuc {

    public DanhMuc() {
    }

    public DanhMuc(int DanhMucID, String TenDanhMuc) {
        this.DanhMucID = DanhMucID;
        this.TenDanhMuc = TenDanhMuc;
    }

    public int getDanhMucID() {
        return DanhMucID;
    }

    public void setDanhMucID(int DanhMucID) {
        this.DanhMucID = DanhMucID;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }
    int DanhMucID;
    String TenDanhMuc;
}
