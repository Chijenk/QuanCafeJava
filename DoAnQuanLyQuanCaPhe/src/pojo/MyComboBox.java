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
public class MyComboBox {

    public MyComboBox() {
    }

    @Override
    public String toString() {
        return tenLoai;
    }
    
    public MyComboBox(int MaLoai, String tenLoai) {
        this.MaLoai = MaLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    int MaLoai;
    String tenLoai;
    
}
