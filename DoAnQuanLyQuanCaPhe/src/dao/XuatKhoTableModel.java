/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author phamh
 */
import javax.swing.table.AbstractTableModel;
import java.util.List;
import pojo.XuatKho;

public class XuatKhoTableModel extends AbstractTableModel {
    private List<XuatKho> danhSachXuatKho;
    private final String[] columnNames = {"XuatKhoID", "NhanVienID", "TenNhanVien", "NguyenLieuID", "TenNguyenLieu", "SoLuongXuat", "NgayXuat"};

    public XuatKhoTableModel(List<XuatKho> danhSachXuatKho) {
        this.danhSachXuatKho = danhSachXuatKho;
    }

    @Override
    public int getRowCount() {
        return danhSachXuatKho.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        XuatKho xuatKho = danhSachXuatKho.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return xuatKho.getXuatKhoID();
            case 1:
                return xuatKho.getNhanVienID();
            case 2:
                return xuatKho.getTenNhanVien();
            case 3:
                return xuatKho.getNguyenLieuID();
            case 4:
                return xuatKho.getTenNguyenLieu();
            case 5:
                return xuatKho.getSoLuongXuat();
            case 6:
                return xuatKho.getNgayXuat();
            default:
                return null;
        }
    }

    // Các phương thức khác để cập nhật dữ liệu nếu cần
}
