/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import pojo.NhaCungCap;
/**
 *
 * @author phamh
 */


public class NhaCungCapTableModel extends AbstractTableModel {
    private List<NhaCungCap> data;
    private String[] columnNames = {"ID", "Tên nhà cung cấp", "Thông tin liên lạc"};

    public NhaCungCapTableModel() {
        data = new ArrayList<>();
    }

    public void setData(List<NhaCungCap> data) {
        this.data = data;
        fireTableDataChanged();
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
        NhaCungCap nhaCungCap = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nhaCungCap.getNhaCungCapID();
            case 1:
                return nhaCungCap.getTenNhaCungCap();
            case 2:
                return nhaCungCap.getThongTinLienLac();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}