package dao;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import pojo.NhapKho;
public class NhapKhoTableModel extends AbstractTableModel {
    private List<NhapKho> danhSachNhapKho;
    private final String[] columnNames = {"ID", "Mã nhân viên", "Tên nhân viên", "Mã nguyên liệu",
            "Tên nguyên liệu", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số lượng", "Giá nhập", "Ngày nhập"};

    public NhapKhoTableModel(List<NhapKho> danhSachNhapKho) {
        this.danhSachNhapKho = danhSachNhapKho;
    }

    @Override
    public int getRowCount() {
        return danhSachNhapKho.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NhapKho nhapKho = danhSachNhapKho.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nhapKho.getNhapKhoID();
            case 1:
                return nhapKho.getNhanVienID();
            case 2:
                return nhapKho.getTenNhanVien();
            case 3:
                return nhapKho.getNguyenLieuID();
            case 4:
                return nhapKho.getTenNguyenLieu();
            case 5:
                return nhapKho.getNhaCungCapID();
            case 6:
                return nhapKho.getTenNhaCungCap();
            case 7:
                return nhapKho.getSoLuongNhap();
            case 8:
                return nhapKho.getGiaNhap();
            case 9:
                return nhapKho.getNgayNhap();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
