package dao;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import pojo.KhoNguyenLieu;

public class KhoNguyenLieuTableModel extends AbstractTableModel {
    private List<KhoNguyenLieu> data;
    private String[] columnNames = {"ID", "Tên Nguyên Liệu", "Đơn Vị", "Giá", "Số Lượng Tồn"};

    public KhoNguyenLieuTableModel(List<KhoNguyenLieu> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KhoNguyenLieu nl = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nl.getNguyenLieuID();
            case 1:
                return nl.getTenNguyenLieu();
            case 2:
                return nl.getDonVi();
            case 3:
                return nl.getGiaTheoDonVi();
            case 4:
                return nl.getSoLuongTon();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
