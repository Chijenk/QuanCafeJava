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
public class TaiKhoan {

    public TaiKhoan() {
    }

    public TaiKhoan(int NhanVienID, String TenTaiKhoan, String MatKhau) {
        this.NhanVienID = NhanVienID;
        this.TenTaiKhoan = TenTaiKhoan;
        this.MatKhau = MatKhau;
    }

    public int getNhanVienID() {
        return NhanVienID;
    }

    public void setNhanVienID(int NhanVienID) {
        this.NhanVienID = NhanVienID;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String TenTaiKhoan) {
        this.TenTaiKhoan = TenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    int NhanVienID;
    String TenTaiKhoan,MatKhau;
    
}
