/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author phamh
 */
public class NhaCungCap {
    private int nhaCungCapID;
    private String tenNhaCungCap;
    private String thongTinLienLac;

    // Constructor không đối số
    public NhaCungCap() {
    }

    // Constructor có đối số
    public NhaCungCap(int nhaCungCapID, String tenNhaCungCap, String thongTinLienLac) {
        this.nhaCungCapID = nhaCungCapID;
        this.tenNhaCungCap = tenNhaCungCap;
        this.thongTinLienLac = thongTinLienLac;
    }

    // Getter và Setter cho nhaCungCapID
    public int getNhaCungCapID() {
        return nhaCungCapID;
    }

    public void setNhaCungCapID(int nhaCungCapID) {
        this.nhaCungCapID = nhaCungCapID;
    }

    // Getter và Setter cho tenNhaCungCap
    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    // Getter và Setter cho thongTinLienLac
    public String getThongTinLienLac() {
        return thongTinLienLac;
    }

    public void setThongTinLienLac(String thongTinLienLac) {
        this.thongTinLienLac = thongTinLienLac;
    }

    // Phương thức toString()
    @Override
    public String toString() {
        return "NhaCungCap{" +
                "nhaCungCapID=" + nhaCungCapID +
                ", tenNhaCungCap='" + tenNhaCungCap + '\'' +
                ", thongTinLienLac='" + thongTinLienLac + '\'' +
                '}';
    }
}
