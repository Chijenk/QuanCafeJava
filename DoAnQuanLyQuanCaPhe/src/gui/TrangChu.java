/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import dao.BanDAO;
import dao.ChiTietDonHangDAO;
import dao.DanhMucDAO;
import dao.DatHangDAO;
import dao.KhoNguyenLieuDAO;
import dao.KhoNguyenLieuTableModel;
import dao.MonDAO;
import dao.Mon_KhoNguyenLieuDAO;
import dao.NhanVienDAO;
import dao.NhapKhoDAO;
import dao.NhapKhoTableModel;
import dao.SQLServerDataProvider;
import dao.TaiKhoanDAO;
import dao.NhaCungCapDAO;
import dao.NhaCungCapTableModel;
import dao.XuatKhoDAO;
import dao.XuatKhoTableModel;


import java.awt.Color;
import java.awt.Component;


import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
import pojo.Ban;
import pojo.ChiTietDatHang;
import pojo.DanhMuc;
import pojo.DatHang;
import pojo.KhoNguyenLieu;
import pojo.Mon;
import pojo.MyComboBox;
import pojo.NhanVien;
import pojo.NhapKho;
import pojo.XuatKho;
import pojo.NhaCungCap;

/**
 *
 * @author Dang Hoang Minh
 */
public class TrangChu extends javax.swing.JFrame {
    int x = 210;    //chieu rong
    int y = 2000; 
    private NhapKhoDAO nhapKhoDAO = new NhapKhoDAO(); // Thêm biến nhapKhoDAO
    private SQLServerDataProvider dataProvider;
    public static int iddh;
    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private KhoNguyenLieuDAO khoNguyenLieuDAO;
    private XuatKhoDAO xuatKhoDAO;
    
    /**
     * Creates new form TrangChu
     */
    DefaultTableModel dtm,dtm2,dtm1 ;
    
    String duongdananh="D:\\NetBeanProject\\DoAnQuanLyQuanCaPhe\\src\\images\\thucuong1.png";
    public TrangChu() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(1200, 700);
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //slideMenu.setSize(210,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lbTitle.setText("Quản lý món ");
        
        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
        lbMaNV.setText(String.valueOf(TaiKhoanDAO.layIDNhanVien(DangNhap.tentk)));
        lbTenNhanVien.setText(NhanVienDAO.layTenNV(TaiKhoanDAO.layIDNhanVien(DangNhap.tentk)));
        if(DangNhap.vitri.equals("Nhân viên"))
        {
            btnNhapKho.setEnabled(false);
            btnncc.setEnabled(false);
        }
       
        NhapKhoDAO nhapKhoDAO = new NhapKhoDAO();
        List<NhapKho> danhSachNhapKho = nhapKhoDAO.getAllNhapKho();
        NhapKhoTableModel tableModel2 = new NhapKhoTableModel(danhSachNhapKho);
        bangNhapKho.setModel(tableModel2);
        //
        XuatKhoDAO xuatKhoDAO = new XuatKhoDAO();
        XuatKhoTableModel tableModel5 = new XuatKhoTableModel(xuatKhoDAO.getAllXuatKho());
        bangXuatKho.setModel(tableModel5);
       
//      //
        List<NhaCungCap> nhaCungCapList = nhaCungCapDAO.getAllNhaCungCap();
        NhaCungCapTableModel tableModel = new NhaCungCapTableModel();
        tableModel.setData(nhaCungCapList);
        tbNCC.setModel(tableModel);
        //
        khoNguyenLieuDAO = new KhoNguyenLieuDAO();
        List<KhoNguyenLieu> data = khoNguyenLieuDAO.getAll();
        KhoNguyenLieuTableModel model1 = new KhoNguyenLieuTableModel(data);
        tbKhoNguyenLieu.setModel(model1);
       Ban();
//        
//        
        DatHang();
//        // hiển thị csdl lên bảng món 
        khoiTaoBangMon();
//
       // Gọi phương thức để đổ dữ liệu vào table
       hienThiCSDLTableMon();
//        
//        // hiển thị csdl lên combobox danh mục 
       hienThiCSDLComboBoxDanhMuc();
//        
        hienThiCSDLComboBoxMon();
//        
//        hienThiCSDLComboBoxBan();
//        
        khoiTaoBangTam();
//        
//        //hienThiCSDLComboBoxBan();
//        
//        hienThiCSDLComboBoxNhanVien();
        Date currentDate;
        currentDate = new Date();
        
        // Tạo một đối tượng SimpleDateFormat và định dạng theo 'dd-MM-yyyy'
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        // Định dạng ngày hiện tại theo định dạng đã khai báo
        String dateString = dateFormat.format(currentDate);
       lbNgayDat.setText(dateString);
       
        hienThiAnhSP();
       
        
    }
//    private void xuatHoaDon(int iddh)
//    {
//        try {
//            Hashtable map = new Hashtable();
//            JasperReport rp = JasperCompileManager.compileReport("D:\\NetBeanProject\\DoAnQuanLyQuanCaPhe\\src\\gui\\HoaDon.jrxml");
//            map.put("DatHangID",iddh);
//            SQLServerDataProvider provider=new SQLServerDataProvider();
//            provider.open();
//            JasperPrint p = JasperFillManager.fillReport(rp, map,provider.getConnection());
//            JasperViewer.viewReport(p,false);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//    }
     public void hienThiDuLieuBan()
     {
          ImageIcon ic_off= new ImageIcon("D:\\NetBeanProject\\DoAnQuanLyQuanCaPhe\\src\\images\\status_off.png");
          ImageIcon ic_on=new ImageIcon("D:\\NetBeanProject\\DoAnQuanLyQuanCaPhe\\src\\images\\status_on.png");
          ArrayList<Ban> dsBan = BanDAO.layDanhSachBan();
          for(Ban ban:dsBan)
          {
              
             String soGhe=String.valueOf(ban.getSoGhe());
             String tenBan=ban.getTenBan();
             JButton btn;
             if(ban.getTrangThai().equals("Có khách")){
             
                 btn = new JButton(tenBan+" - Số chỗ ngồi: "+soGhe, ic_on);
             }
             else
             {
                btn = new JButton(tenBan+" - Số chỗ ngồi: "+soGhe, ic_off);
             }
             jpChonBan.add(btn);
             btn.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    int BanIDDuocChon=ban.getBanID();
                     lbBanIDDaChon.setText(String.valueOf(BanIDDuocChon));
                 }
             });
             
          }
     }
    public void khoiTaoBangMon()
    {
        String[] columnNames = {"ID", "Tên Món", "Mô Tả", "Giá", "Danh Mục ID"};
        dtm = new DefaultTableModel(columnNames, 0);

        tbMon.setModel(dtm);
    }
    
    public void khoiTaoBangTam()
    {
        String[] columnNamesBangTam = { "ID Món","Tên Món", "Giá","Số lượng"};
        dtm2=new DefaultTableModel(columnNamesBangTam,0);
        tbBangTam.setModel(dtm2);
        
    }
   
    public void hienThiCSDLComboBoxBan()
    {
        
        ArrayList<Ban> dsBan = BanDAO.layDanhSachBan();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

        for (Ban ban : dsBan) {
            if(ban.getTrangThai().equals("Còn trống")){
            MyComboBox cbb= new MyComboBox(ban.getBanID(), ban.getTenBan());
            
            comboBoxModel.addElement(cbb);
            }
        }

//        cboxBan.setModel(comboBoxModel);
    }
   
    public void hienThiCSDLComboBoxNhanVien()
    {
          ArrayList<NhanVien> dsnv = NhanVienDAO.layDanhSachNV();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

        for (NhanVien nv : dsnv) {
            MyComboBox cbb= new MyComboBox(nv.getID(), nv.getTenNV());
            comboBoxModel.addElement(cbb);
        }
       

//        cboxNhanVien.setModel(comboBoxModel);
    }
    public boolean kiemTraSL()
    {
       boolean kq=true;
        try {
           int  sl =Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng số !");
            kq=false;
        }
        return kq;
        
    }
    public void hienThiCSDLComboBoxDanhMuc()
    {
        
       
         ArrayList<DanhMuc> dsDM = DanhMucDAO.layDanhSachDanhMuc();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        
        for (DanhMuc dm : dsDM) {
            MyComboBox cbb= new MyComboBox(dm.getDanhMucID(), dm.getTenDanhMuc());
            comboBoxModel.addElement(cbb);
        }
       

        cboxDanhMuc.setModel(comboBoxModel);
        cboxDanhMuc2.setModel(comboBoxModel);
        
    }
     
     
      public void hienThiCSDLComboBoxMon()
    {
        MyComboBox cbbDuocChon=(MyComboBox)cboxDanhMuc2.getSelectedItem();
        
        int danhMucID=cbbDuocChon.getMaLoai();
       
        ArrayList<Mon> dsMon = MonDAO.layDanhSachMonTheoDanhMuc(danhMucID);
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

        for (Mon mon : dsMon) {
            MyComboBox cbb= new MyComboBox(mon.getMonID(), mon.getTenMon());
            comboBoxModel.addElement(cbb);
        }

        cboxMon.setModel(comboBoxModel);
        
    }
      public void Ban(){
        ArrayList<Ban> dsBan = BanDAO.layDanhSachTatCaBan();
       
        DefaultTableModel dtmBan=(DefaultTableModel)jtb_ban.getModel();
    
    
        String[] columnNames = {"Bàn ID", "Tên Bàn", "Trạng Thái", "Số Ghế"};
        dtmBan = new DefaultTableModel(columnNames, 0);

        
    
            dtmBan.setRowCount(0);
            for (Ban pb: dsBan){
                Vector<Object> vec=new Vector<Object>();
                vec.add(pb.getBanID());
                vec.add(pb.getTenBan());
                vec.add(pb.getTrangThai());
                vec.add(pb.getSoGhe());
                dtmBan.addRow(vec);
            }
            jtb_ban.setModel(dtmBan);
    }
    public void DatHang(){
        ArrayList<DatHang> dsDatHang = DatHangDAO.layDanhSachDatHang();
        DefaultTableModel dtmDatHang=(DefaultTableModel) jtb_dathang.getModel();
        dtmDatHang.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        for (DatHang dh: dsDatHang){
            String formattedDate = sdf.format(dh.getNgayDat());
            
            Vector<Object>vec=new Vector<Object>();
            vec.add(dh.getDatHangID());
            vec.add(dh.getNhanVienID());
            vec.add(dh.getTongGia());
            vec.add(dh.getBanID());
            vec.add(formattedDate);
            dtmDatHang.addRow(vec);  
        }
        jtb_dathang.setModel(dtmDatHang);
    }
    public void hienThiCSDLTableMon()
    {
        
        ArrayList<Mon> dsMon = MonDAO.layDanhSachMon();
        dtm.setRowCount(0);
        for (Mon mon : dsMon) {
            Object[] rowData = new Object[]{
                    mon.getMonID(),
                    mon.getTenMon(),
                    mon.getMoTa(),
                    mon.getGia(),
                    mon.getDanhMucID(),
                    mon.getHinhAnh()
            };
            dtm.addRow(rowData);
        }
      
        
    }
    public void openMenu() {
        slideMenu.setSize(x, y);
        if (x == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= 210; i++) {
                            slideMenu.setSize(i, y);
                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            x = 210;
        }
    }

    public void closeMenu() {
        slideMenu.setSize(x, y);
        if (x == 210) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 210; i >= 0; i--) {
                            slideMenu.setSize(i, y);
                            Thread.sleep(2);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            x = 0;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        lbMenu = new javax.swing.JLabel();
        slideMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnDangXuat = new javax.swing.JButton();
        btnncc = new javax.swing.JButton();
        btnMon = new javax.swing.JButton();
        btnBan = new javax.swing.JButton();
        btnQuanLyDatHang = new javax.swing.JButton();
        btnNhapKho = new javax.swing.JButton();
        btnXuatKho1 = new javax.swing.JButton();
        btnkho = new javax.swing.JButton();
        jpContainer = new javax.swing.JPanel();
        jpMon = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMon = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnThemMon = new javax.swing.JButton();
        btnXoaMon = new javax.swing.JButton();
        btnSuaMon = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTenMon = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        cboxDanhMuc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtMonID = new javax.swing.JTextField();
        btnTimMon = new javax.swing.JButton();
        txtTimMon = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        lbanh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        jpQuanLyBan = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtb_ban = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        lb_tenban = new javax.swing.JLabel();
        lb_soghe = new javax.swing.JLabel();
        lb_trangthai = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jtf_tenban = new javax.swing.JTextField();
        jtf_soghe = new javax.swing.JTextField();
        jlb_ban = new javax.swing.JLabel();
        btn_tim = new javax.swing.JButton();
        jtf_trangthai = new javax.swing.JTextField();
        jtf_idban = new javax.swing.JTextField();
        btnTachBan = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnGopBan = new javax.swing.JButton();
        jpQuanLyDatHang = new javax.swing.JPanel();
        panelDatHang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboxDanhMuc2 = new javax.swing.JComboBox<>();
        btnXoaMonTrongBangTam = new javax.swing.JButton();
        btnThemMonVaoBangTam = new javax.swing.JButton();
        cboxMon = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBangTam = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        btnDatHang = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnSuaMonTrongBangTam = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lbNgayDat = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaDH = new javax.swing.JTextField();
        btnDonBan = new javax.swing.JButton();
        lbTenNhanVien = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbBanIDDaChon = new javax.swing.JLabel();
        lbAnhSP = new javax.swing.JLabel();
        btnBoSungDatHang = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtb_dathang = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        lb_ngaydat = new javax.swing.JLabel();
        lb_tonggia = new javax.swing.JLabel();
        jtf_ngaydat = new javax.swing.JTextField();
        jtf_tonggia = new javax.swing.JTextField();
        btn_Sua = new javax.swing.JButton();
        lb_dathang = new javax.swing.JLabel();
        lb_ban = new javax.swing.JLabel();
        lb_nhanvien = new javax.swing.JLabel();
        jtf_dathang = new javax.swing.JTextField();
        jtf_nhanvien = new javax.swing.JTextField();
        jtf_datban = new javax.swing.JTextField();
        btn_timkiemdathang = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jtf_timkiem = new javax.swing.JTextField();
        btnReloadBan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        jpChonBan = new javax.swing.JPanel();
        jpQuanLyXuatKho = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bangXuatKho = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        btnAddXuatKHo = new javax.swing.JButton();
        btnEditXuatKho = new javax.swing.JButton();
        btnDeleteXuatKho = new javax.swing.JButton();
        btnFindXuatKho = new javax.swing.JButton();
        btnPrintXuatKho = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtXuatKhoID = new javax.swing.JTextField();
        NhanVienIDXK = new javax.swing.JTextField();
        txtNguyenLieuXK = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtSoLuongXuat = new javax.swing.JTextField();
        txtNgayXuatXK = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTimNguyenLieuTheoID = new javax.swing.JTextField();
        txtTenNLXK = new javax.swing.JTextField();
        txtTenNVXK = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jpQuanLyNhapKho = new javax.swing.JPanel();
        jpanelnhapkho = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bangNhapKho = new javax.swing.JTable();
        jpanelnhapkkho2 = new javax.swing.JPanel();
        EditNhapKho = new javax.swing.JButton();
        XoaNhapKho = new javax.swing.JButton();
        AddNhapKho = new javax.swing.JButton();
        txtNhapKhoID = new javax.swing.JTextField();
        txtNhaCungCapID = new javax.swing.JTextField();
        txtSoLuongNhap = new javax.swing.JTextField();
        txtNhanVienID = new javax.swing.JTextField();
        txtNguyenLieuID = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        FindNhapKho = new javax.swing.JButton();
        txtTenNguyenLieu = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtTenNhaCC = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jpKho = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbKhoNguyenLieu = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        Tenkho = new javax.swing.JTextField();
        donvi = new javax.swing.JTextField();
        giadonvi = new javax.swing.JTextField();
        slton = new javax.swing.JTextField();
        idkho = new javax.swing.JTextField();
        btnSuakho = new javax.swing.JButton();
        btnThemkho = new javax.swing.JButton();
        btntimkeim = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        timkiemkho = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jpNhaCC = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbNCC = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtNCCID = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        btThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        timkiemncc = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        lbTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(102, 0, 0));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("TRANG CHỦ");
        jPanel5.add(lbTitle, java.awt.BorderLayout.CENTER);

        lbMenu.setBackground(new java.awt.Color(153, 51, 0));
        lbMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        lbMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMenuMouseClicked(evt);
            }
        });
        jPanel5.add(lbMenu, java.awt.BorderLayout.LINE_START);

        slideMenu.setBackground(new java.awt.Color(153, 255, 255));
        slideMenu.setMinimumSize(new java.awt.Dimension(210, 0));
        slideMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("HUIT Coffee");
        slideMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        slideMenu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 46, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        slideMenu.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 22));
        slideMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 165, 210, 10));
        slideMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 210, 10));

        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        slideMenu.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 230, -1));

        btnncc.setText("Quản lý Nhà cung cấp");
        btnncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnccActionPerformed(evt);
            }
        });
        slideMenu.add(btnncc, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 370, 230, -1));

        btnMon.setText("Quản lý món");
        btnMon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMonMouseClicked(evt);
            }
        });
        btnMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonActionPerformed(evt);
            }
        });
        slideMenu.add(btnMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 190, 230, -1));

        btnBan.setText("Quản lý bàn");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });
        slideMenu.add(btnBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 220, 230, -1));

        btnQuanLyDatHang.setText("Quản lý đặt hàng");
        btnQuanLyDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyDatHangActionPerformed(evt);
            }
        });
        slideMenu.add(btnQuanLyDatHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 250, 230, -1));

        btnNhapKho.setText("Quản lý nhập kho");
        btnNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapKhoActionPerformed(evt);
            }
        });
        slideMenu.add(btnNhapKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 280, 230, -1));

        btnXuatKho1.setText("Quản lý xuất kho");
        btnXuatKho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatKho1ActionPerformed(evt);
            }
        });
        slideMenu.add(btnXuatKho1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 310, 230, -1));

        btnkho.setText("Quản lý kho");
        btnkho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhoActionPerformed(evt);
            }
        });
        slideMenu.add(btnkho, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 340, 230, -1));

        jpContainer.setBackground(new java.awt.Color(255, 255, 255));
        jpContainer.setLayout(new java.awt.CardLayout());

        jpMon.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        tbMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbMonMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(tbMon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));

        btnThemMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/them.png"))); // NOI18N
        btnThemMon.setText("Thêm ");
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });

        btnXoaMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xoa.png"))); // NOI18N
        btnXoaMon.setText("Xoá");
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });

        btnSuaMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sua.png"))); // NOI18N
        btnSuaMon.setText("Sửa");
        btnSuaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMonActionPerformed(evt);
            }
        });

        jLabel18.setText("Tên món");

        jLabel19.setText("Mô Tả");

        jLabel20.setText("Giá");

        jLabel21.setText("Danh mục");

        cboxDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Món ID");

        txtMonID.setEnabled(false);

        btnTimMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btnTimMon.setText("Tìm kiếm");
        btnTimMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMonActionPerformed(evt);
            }
        });

        txtTimMon.setPreferredSize(new java.awt.Dimension(30, 20));

        jLabel22.setText("Nhập tên món cần tìm :");

        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loc.png"))); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        lbanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thucuong1.png"))); // NOI18N

        btnChonAnh.setText("Chọn Ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemMon)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSuaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(btnXoaMon))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(btnTimMon)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboxDanhMuc, 0, 125, Short.MAX_VALUE)
                                        .addComponent(txtMonID)))
                                .addComponent(jLabel21))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenMon, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(103, 103, 103)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimMon, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                        .addGap(39, 39, 39)))
                .addComponent(lbanh)
                .addGap(586, 586, 586))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChonAnh)
                .addGap(648, 648, 648))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbanh, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChonAnh))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(txtTimMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(cboxDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMonID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimMon)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpMonLayout = new javax.swing.GroupLayout(jpMon);
        jpMon.setLayout(jpMonLayout);
        jpMonLayout.setHorizontalGroup(
            jpMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpMonLayout.setVerticalGroup(
            jpMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpContainer.add(jpMon, "card2");

        jtb_ban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtb_ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_banMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtb_ban);

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));

        lb_tenban.setText("Tên bàn");

        lb_soghe.setText("Số ghế");

        lb_trangthai.setText("Trạng thái");

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        jlb_ban.setText("Bàn");

        btn_tim.setText("Tim Kiem");
        btn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timActionPerformed(evt);
            }
        });

        jtf_idban.setEnabled(false);

        btnTachBan.setText("Tách bàn");
        btnTachBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTachBanActionPerformed(evt);
            }
        });

        btnReload.setText("Load");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        btnGopBan.setText("Gộp bàn");
        btnGopBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGopBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(lb_trangthai)
                                .addGap(29, 29, 29)
                                .addComponent(jtf_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                    .addComponent(lb_soghe)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtf_soghe, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlb_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb_tenban))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtf_tenban, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtf_idban, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btn_them)
                        .addGap(47, 47, 47)
                        .addComponent(btn_xoa)
                        .addGap(45, 45, 45)
                        .addComponent(btn_sua)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btn_tim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGopBan)
                        .addGap(71, 71, 71)
                        .addComponent(btnTachBan)
                        .addGap(436, 436, 436))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(337, 337, 337))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_ban)
                    .addComponent(jtf_idban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnReload)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tenban)
                    .addComponent(jtf_tenban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_soghe)
                    .addComponent(jtf_soghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_trangthai)
                    .addComponent(jtf_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_sua)
                    .addComponent(btn_tim)
                    .addComponent(btn_xoa)
                    .addComponent(btnTachBan)
                    .addComponent(btnGopBan))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jpQuanLyBanLayout = new javax.swing.GroupLayout(jpQuanLyBan);
        jpQuanLyBan.setLayout(jpQuanLyBanLayout);
        jpQuanLyBanLayout.setHorizontalGroup(
            jpQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpQuanLyBanLayout.setVerticalGroup(
            jpQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQuanLyBanLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 302, Short.MAX_VALUE))
        );

        jpContainer.add(jpQuanLyBan, "card3");

        panelDatHang.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setText("Bàn :");

        jLabel6.setText("Chọn danh mục:");

        cboxDanhMuc2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxDanhMuc2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxDanhMuc2ItemStateChanged(evt);
            }
        });

        btnXoaMonTrongBangTam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xoa.png"))); // NOI18N
        btnXoaMonTrongBangTam.setText("Xoá");
        btnXoaMonTrongBangTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonTrongBangTamActionPerformed(evt);
            }
        });

        btnThemMonVaoBangTam.setBackground(new java.awt.Color(255, 204, 204));
        btnThemMonVaoBangTam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/them.png"))); // NOI18N
        btnThemMonVaoBangTam.setText("Thêm");
        btnThemMonVaoBangTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonVaoBangTamActionPerformed(evt);
            }
        });

        cboxMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxMon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxMonItemStateChanged(evt);
            }
        });

        jLabel10.setText("Chọn đồ ăn:");

        tbBangTam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbBangTam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangTamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBangTam);

        jLabel11.setText("Tổng tiền:");

        txtTongTien.setText("0");

        btnDatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        btnDatHang.setText("Đặt hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        jLabel7.setText("Nhân viên:");

        jLabel16.setText("Số lượng:");

        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusLost(evt);
            }
        });

        btnSuaMonTrongBangTam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sua.png"))); // NOI18N
        btnSuaMonTrongBangTam.setText("Sửa");
        btnSuaMonTrongBangTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMonTrongBangTamActionPerformed(evt);
            }
        });

        jLabel12.setText("Ngày đặt hàng:");

        lbNgayDat.setText("jLabel23");

        jLabel23.setText("Mã Đặt Hàng");

        txtMaDH.setEnabled(false);

        btnDonBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        btnDonBan.setText("Dọn Bàn");
        btnDonBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonBanActionPerformed(evt);
            }
        });

        lbTenNhanVien.setText("jLabel23");

        lbMaNV.setText("jLabel9");

        jLabel9.setText("--");

        lbAnhSP.setText("jLabel14");

        btnBoSungDatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        btnBoSungDatHang.setText("Đặt bổ sung");
        btnBoSungDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoSungDatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatHangLayout = new javax.swing.GroupLayout(panelDatHang);
        panelDatHang.setLayout(panelDatHangLayout);
        panelDatHangLayout.setHorizontalGroup(
            panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatHangLayout.createSequentialGroup()
                        .addComponent(btnThemMonVaoBangTam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaMonTrongBangTam, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaMonTrongBangTam))
                    .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelDatHangLayout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(46, 46, 46)
                            .addComponent(txtSoLuong))
                        .addGroup(panelDatHangLayout.createSequentialGroup()
                            .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addGap(14, 14, 14)
                            .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelDatHangLayout.createSequentialGroup()
                                    .addGap(89, 89, 89)
                                    .addComponent(lbBanIDDaChon))
                                .addComponent(cboxDanhMuc2, 0, 218, Short.MAX_VALUE)
                                .addComponent(cboxMon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelDatHangLayout.createSequentialGroup()
                        .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel23)
                            .addComponent(jLabel7))
                        .addGap(50, 50, 50)
                        .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNgayDat)
                            .addComponent(txtTongTien)
                            .addGroup(panelDatHangLayout.createSequentialGroup()
                                .addComponent(lbTenNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMaNV))
                            .addComponent(txtMaDH, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelDatHangLayout.createSequentialGroup()
                            .addComponent(btnDonBan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDatHang)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBoSungDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAnhSP))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDatHangLayout.setVerticalGroup(
            panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lbBanIDDaChon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboxDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboxMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(lbAnhSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMonVaoBangTam, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaMonTrongBangTam, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaMonTrongBangTam, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbTenNhanVien)
                    .addComponent(lbMaNV)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbNgayDat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtMaDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatHang)
                    .addComponent(btnDonBan)
                    .addComponent(btnBoSungDatHang))
                .addGap(47, 47, 47))
        );

        jtb_dathang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Đặt Hàng", "Nhân Viên", "Tổng giá", "Bàn", "Ngày đặt"
            }
        ));
        jtb_dathang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_dathangMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtb_dathang);

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));

        lb_ngaydat.setText("Ngày đặt");

        lb_tonggia.setText("Tổng giá");

        jtf_ngaydat.setEnabled(false);
        jtf_ngaydat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_ngaydatActionPerformed(evt);
            }
        });

        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sua.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        lb_dathang.setText("Đặt hàng");

        lb_ban.setText("Bàn");

        lb_nhanvien.setText("Nhân viên");

        jtf_dathang.setEnabled(false);
        jtf_dathang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_dathangActionPerformed(evt);
            }
        });

        btn_timkiemdathang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btn_timkiemdathang.setText("Tìm Kiếm");
        btn_timkiemdathang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemdathangActionPerformed(evt);
            }
        });

        jLabel8.setText("Nhập đặt hàng id cần tìm");

        jtf_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_timkiemActionPerformed(evt);
            }
        });

        btnReloadBan.setText("Load");
        btnReloadBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadBanActionPerformed(evt);
            }
        });

        btnInHoaDon.setText("in hoa don");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnChiTiet.setText("Xem chi tiết ");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lb_nhanvien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_dathang)
                                    .addComponent(lb_ban))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtf_datban, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(jtf_dathang))))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_timkiemdathang)))
                        .addContainerGap(566, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(lb_tonggia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtf_tonggia))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(lb_ngaydat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtf_ngaydat, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChiTiet)
                        .addGap(268, 268, 268)
                        .addComponent(btnInHoaDon)
                        .addGap(39, 39, 39)
                        .addComponent(btnReloadBan))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_ngaydat)
                    .addComponent(jtf_ngaydat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_tonggia)
                            .addComponent(jtf_tonggia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReloadBan)
                            .addComponent(btnInHoaDon)
                            .addComponent(btnChiTiet))))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_dathang)
                    .addComponent(jtf_dathang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtf_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_ban)
                            .addComponent(jtf_datban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_nhanvien)
                            .addComponent(jtf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_timkiemdathang, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148))))
        );

        jpChonBan.setLayout(new java.awt.GridLayout(5, 3, 1, 1));

        javax.swing.GroupLayout jpQuanLyDatHangLayout = new javax.swing.GroupLayout(jpQuanLyDatHang);
        jpQuanLyDatHang.setLayout(jpQuanLyDatHangLayout);
        jpQuanLyDatHangLayout.setHorizontalGroup(
            jpQuanLyDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQuanLyDatHangLayout.createSequentialGroup()
                .addGroup(jpQuanLyDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpQuanLyDatHangLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpChonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpQuanLyDatHangLayout.setVerticalGroup(
            jpQuanLyDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpQuanLyDatHangLayout.createSequentialGroup()
                .addGroup(jpQuanLyDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jpChonBan, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpContainer.add(jpQuanLyDatHang, "card5");

        bangXuatKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bangXuatKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangXuatKhoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(bangXuatKho);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));

        btnAddXuatKHo.setText("Thêm");
        btnAddXuatKHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddXuatKHoActionPerformed(evt);
            }
        });

        btnEditXuatKho.setText("Sửa thông tin");
        btnEditXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditXuatKhoActionPerformed(evt);
            }
        });

        btnDeleteXuatKho.setText("Xóa");
        btnDeleteXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteXuatKhoActionPerformed(evt);
            }
        });

        btnFindXuatKho.setText("Tìm kiếm");
        btnFindXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindXuatKhoActionPerformed(evt);
            }
        });

        btnPrintXuatKho.setText("In Xuất Kho");
        btnPrintXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintXuatKhoActionPerformed(evt);
            }
        });

        jLabel13.setText("Xuất Kho ID");

        jLabel26.setText("Nhân Viên ID");

        jLabel27.setText("Nguyên Liệu ID");

        jLabel28.setText("Số Lượng Xuất");

        jLabel29.setText("Ngày Xuất");

        jLabel30.setText("Tìm kiếm theo nguyên liệu");

        txtTimNguyenLieuTheoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimNguyenLieuTheoIDActionPerformed(evt);
            }
        });

        jLabel14.setText("Tên Nguyên Liệu");

        jLabel39.setText("Tên Nhân Viên");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtXuatKhoID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddXuatKHo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnEditXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnDeleteXuatKho)
                        .addGap(40, 40, 40)
                        .addComponent(btnFindXuatKho))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(txtNguyenLieuXK, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jLabel14)
                            .addComponent(txtTenNLXK))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel26)
                                .addComponent(NhanVienIDXK, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addComponent(txtTenNVXK)))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuongXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(txtNgayXuatXK, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTimNguyenLieuTheoID, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPrintXuatKho))
                                .addGap(188, 188, 188))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(177, 177, 177))))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtXuatKhoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguyenLieuXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhanVienIDXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayXuatXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuongXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel39))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNLXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNVXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimNguyenLieuTheoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddXuatKHo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpQuanLyXuatKhoLayout = new javax.swing.GroupLayout(jpQuanLyXuatKho);
        jpQuanLyXuatKho.setLayout(jpQuanLyXuatKhoLayout);
        jpQuanLyXuatKhoLayout.setHorizontalGroup(
            jpQuanLyXuatKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpQuanLyXuatKhoLayout.setVerticalGroup(
            jpQuanLyXuatKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQuanLyXuatKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpContainer.add(jpQuanLyXuatKho, "card6");

        jpanelnhapkho.setBackground(new java.awt.Color(204, 255, 204));

        bangNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bangNhapKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangNhapKhoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(bangNhapKho);

        javax.swing.GroupLayout jpanelnhapkhoLayout = new javax.swing.GroupLayout(jpanelnhapkho);
        jpanelnhapkho.setLayout(jpanelnhapkhoLayout);
        jpanelnhapkhoLayout.setHorizontalGroup(
            jpanelnhapkhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
        );
        jpanelnhapkhoLayout.setVerticalGroup(
            jpanelnhapkhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        jpanelnhapkkho2.setBackground(new java.awt.Color(204, 255, 204));

        EditNhapKho.setText("Sửa");
        EditNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditNhapKhoActionPerformed(evt);
            }
        });

        XoaNhapKho.setText("Xóa");
        XoaNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaNhapKhoActionPerformed(evt);
            }
        });

        AddNhapKho.setText("Thêm ");
        AddNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNhapKhoActionPerformed(evt);
            }
        });

        txtNhapKhoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhapKhoIDActionPerformed(evt);
            }
        });

        txtNguyenLieuID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNguyenLieuIDActionPerformed(evt);
            }
        });

        jLabel15.setText("ID");

        jLabel24.setText("ID");

        jLabel25.setText("Nhân viên ID");

        jLabel31.setText("Nguyên Liệu ID");

        jLabel32.setText("Nhà Cung Cấp ID");

        jLabel33.setText("Số Lượng Nhập");

        jLabel34.setText("Giá Nhập");

        jLabel35.setText("Ngày Nhập");

        jLabel36.setText("Tìm kiếm theo nguyên liệu");

        FindNhapKho.setText("Tìm");
        FindNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindNhapKhoActionPerformed(evt);
            }
        });

        jLabel17.setText("Tên nhân viên");

        jLabel37.setText("Tên Nguyên Liệu");

        jLabel38.setText("Tên nhà Cung Cấp");

        javax.swing.GroupLayout jpanelnhapkkho2Layout = new javax.swing.GroupLayout(jpanelnhapkkho2);
        jpanelnhapkkho2.setLayout(jpanelnhapkkho2Layout);
        jpanelnhapkkho2Layout.setHorizontalGroup(
            jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNhapKhoID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(35, 35, 35)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtNhanVienID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNguyenLieuID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31))
                                .addGap(59, 59, 59))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelnhapkkho2Layout.createSequentialGroup()
                                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)))
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNhaCungCapID, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(txtTenNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35))
                                .addGap(73, 73, 73)
                                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(AddNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(EditNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(XoaNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(289, 289, 289)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(FindNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(784, 784, 784)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                    .addGap(236, 236, 236)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(700, Short.MAX_VALUE)))
            .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1033, Short.MAX_VALUE)))
        );
        jpanelnhapkkho2Layout.setVerticalGroup(
            jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelnhapkkho2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31)
                                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel35))
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelnhapkkho2Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNhaCungCapID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNhapKhoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNhanVienID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguyenLieuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel17))
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(AddNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(XoaNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FindNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jLabel24)
                    .addContainerGap(252, Short.MAX_VALUE)))
            .addGroup(jpanelnhapkkho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelnhapkkho2Layout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(368, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpQuanLyNhapKhoLayout = new javax.swing.GroupLayout(jpQuanLyNhapKho);
        jpQuanLyNhapKho.setLayout(jpQuanLyNhapKhoLayout);
        jpQuanLyNhapKhoLayout.setHorizontalGroup(
            jpQuanLyNhapKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanelnhapkho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpQuanLyNhapKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanelnhapkkho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jpQuanLyNhapKhoLayout.setVerticalGroup(
            jpQuanLyNhapKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQuanLyNhapKhoLayout.createSequentialGroup()
                .addComponent(jpanelnhapkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelnhapkkho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpContainer.add(jpQuanLyNhapKho, "card7");

        tbKhoNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tbKhoNguyenLieu);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        slton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sltonActionPerformed(evt);
            }
        });

        btnSuakho.setText("Sửa");
        btnSuakho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuakhoActionPerformed(evt);
            }
        });

        btnThemkho.setText("Thêm");
        btnThemkho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemkhoActionPerformed(evt);
            }
        });

        btntimkeim.setText("Tìm Kiếm");
        btntimkeim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkeimActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jButton1.setText("Load");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel40.setText("iID");

        jLabel41.setText("Tên Nguyên liệu");

        jLabel42.setText("Đơn vị");

        jLabel43.setText("Giá đơn vị");

        jLabel44.setText("Số lượng tồn");

        jLabel45.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemkho, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(idkho, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(49, 49, 49)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(Tenkho, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnSuakho, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btntimkeim, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jButton1))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(donvi, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giadonvi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(timkiemkho, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tenkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(donvi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(giadonvi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timkiemkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSuakho, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntimkeim, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemkho, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout jpKhoLayout = new javax.swing.GroupLayout(jpKho);
        jpKho.setLayout(jpKhoLayout);
        jpKhoLayout.setHorizontalGroup(
            jpKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jpKhoLayout.setVerticalGroup(
            jpKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jpContainer.add(jpKho, "card8");

        tbNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tbNCC);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btThem.setText("Thêm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tìm Kiếm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel46.setText("Tên Nhà Cung Cấp");

        jLabel47.setText("Thông tin liên lạc");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timkiemncc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNCCID, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46))
                .addGap(91, 91, 91)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNCCID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(timkiemncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout jpNhaCCLayout = new javax.swing.GroupLayout(jpNhaCC);
        jpNhaCC.setLayout(jpNhaCCLayout);
        jpNhaCCLayout.setHorizontalGroup(
            jpNhaCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNhaCCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpNhaCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhaCCLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jpNhaCCLayout.setVerticalGroup(
            jpNhaCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNhaCCLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jpContainer.add(jpNhaCC, "card7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(slideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(slideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addGap(451, 451, 451))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMenuMouseClicked
        // TODO add your handling code here:
        openMenu();
    }//GEN-LAST:event_lbMenuMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        slideMenu.setSize(0, y);
        x = 0;
    }//GEN-LAST:event_formWindowOpened

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        closeMenu();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnccActionPerformed
        // TODO add your handling code here:
        lbTitle.setText("Quản lý Nhà Cung Cấp");
        
        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
        jpNhaCC.setVisible(true);
        
    }//GEN-LAST:event_btnnccActionPerformed

    private void btnMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonActionPerformed
        // TODO add your handling code here:
        lbTitle.setText("Quản lý món");
        
        jpMon.setVisible(true);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
    }//GEN-LAST:event_btnMonActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        // TODO add your handling code here:
        lbTitle.setText("Quản lý bàn");
      
        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(true);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnQuanLyDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyDatHangActionPerformed
        // TODO add your handling code here:
        lbTitle.setText("Quản lý đặt hàng");
         // TODO add your handling code here:
        jpChonBan.removeAll();
        jpChonBan.revalidate(); // Tái xác nhận bố cục của panel
        jpChonBan.repaint();
        hienThiDuLieuBan();
        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(true);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
    }//GEN-LAST:event_btnQuanLyDatHangActionPerformed

    private void btnNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapKhoActionPerformed
        // TODO add your handling code here:
        lbTitle.setText("Danh sách nhập kho");
        
        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(true);
        jpQuanLyXuatKho.setVisible(false);
    }//GEN-LAST:event_btnNhapKhoActionPerformed

    private void btnMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMonMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnMonMouseClicked

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        //xu ly them csdl
       MyComboBox cbbMon=(MyComboBox)cboxMon.getSelectedItem();
       int maMon =cbbMon.getMaLoai();
      
       int maNV = Integer.parseInt(lbMaNV.getText());
       int banID=Integer.parseInt(lbBanIDDaChon.getText());
       float tongTien=Float.parseFloat(txtTongTien.getText());

       Date currentDate;
       currentDate = new Date();

       DatHang dh=new DatHang(DatHangDAO.layMaDHLonNhat()+1,maNV,banID,currentDate,tongTien);
        if(DatHangDAO.themDatHang(dh))
        {
                    JOptionPane.showMessageDialog(this, "Thêm thành công đơn hàng");
        }
      
   // xu ly csdl chi tiet don hang
        for (int i = 0; i < dtm2.getRowCount(); i++) {
             // Duyệt qua mỗi cột trên dòng hiện tại

                 // Lấy giá trị tại dòng i, cột j
                 int monID = Integer.parseInt(dtm2.getValueAt(i, 0).toString());
                 int soLuong=Integer.parseInt(dtm2.getValueAt(i, 3).toString());
                 int maDH=DatHangDAO.layMaDHLonNhat();
                 ChiTietDatHang ctdh= new ChiTietDatHang(ChiTietDonHangDAO.layMaCTDHLonNhat()+1,maDH,monID,soLuong);

                 ChiTietDonHangDAO.themCTDH(ctdh);
                 
                 ArrayList<Integer> dsnl;
                 dsnl=Mon_KhoNguyenLieuDAO.layDanhSachNguyenLieu(monID);
                 for(int item : dsnl)
                 {
                     float slSuDung =Mon_KhoNguyenLieuDAO.laySLSuDung(monID,item);
                     KhoNguyenLieuDAO.capNhatSLTon(item, slSuDung,soLuong);
                 }
        
        
        }
      
      



         txtMaDH.setText("");
        
         
         dtm2.setRowCount(0);
         DatHang();
        
         // cập nhật trạng thái của bàn 
         BanDAO.capNhaTrangThaiCoKhach(banID);
         // đổi màu bàn để biết đã có khách dùng
        jpChonBan.removeAll();
        jpChonBan.revalidate(); // Tái xác nhận bố cục của panel
        jpChonBan.repaint();
        hienThiDuLieuBan();
         
         
         
         
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
        // TODO add your handling code here:
        String tenMon=txtTenMon.getText();
        String moTa=txtMoTa.getText();
        String gia=txtGia.getText();
        MyComboBox cbbDuocChon= (MyComboBox)cboxDanhMuc.getSelectedItem();
        String danhMucID = String.valueOf(cbbDuocChon.getMaLoai());
        String monID=String.valueOf(MonDAO.layMaMonLonNhat());
        String anh=duongdananh;
        Mon mon=new Mon(tenMon, moTa,Double.parseDouble(gia), Integer.parseInt(danhMucID),Integer.parseInt(monID),anh);
        if(MonDAO.themMon(mon))
        {
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
        }
        hienThiCSDLTableMon();
        
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void btnSuaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMonActionPerformed
        // TODO add your handling code here:
        String tenMon=txtTenMon.getText();
        String moTa=txtMoTa.getText();
        String gia=txtGia.getText();
        MyComboBox cbbDuocChon= (MyComboBox)cboxDanhMuc.getSelectedItem();
        String danhMucID = String.valueOf(cbbDuocChon.getMaLoai());
        String monID=txtMonID.getText();
        String anh= duongdananh;
        Mon mon=new Mon(tenMon, moTa,Double.parseDouble(gia), Integer.parseInt(danhMucID),Integer.parseInt(monID),anh);
        if(MonDAO.suaMon(mon))
        {
           int confirm= JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật dữ liệu không ", "Cập nhật dữ liệu ", JOptionPane.YES_NO_OPTION);
           if(confirm==JOptionPane.YES_OPTION)
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
           else
               return;
        }
        hienThiCSDLTableMon();
        
    }//GEN-LAST:event_btnSuaMonActionPerformed

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        // TODO add your handling code here:
        String tenMon=txtTenMon.getText();
        String moTa=txtMoTa.getText();
        String gia=txtGia.getText();
        
        MyComboBox cbbDuocChon= (MyComboBox)cboxDanhMuc.getSelectedItem();
        String danhMucID = String.valueOf(cbbDuocChon.getMaLoai());
        String monID=txtMonID.getText();
        String anh= duongdananh;
        Mon mon=new Mon(tenMon, moTa,Double.parseDouble(gia), Integer.parseInt(danhMucID),Integer.parseInt(monID),anh);
        if(MonDAO.xoaMon(mon))
        {
           int confirm= JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá món không ", "Xoá món  ", JOptionPane.YES_NO_OPTION);
           if(confirm==JOptionPane.YES_OPTION)
                JOptionPane.showMessageDialog(this, "Xoá thành công !");
           else
            return;
        }
        
        hienThiCSDLTableMon();
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void btnTimMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMonActionPerformed
        // TODO add your handling code here:
        String tenMon = txtTimMon.getText();
         ArrayList<Mon> dsMon = MonDAO.timKiemMon(tenMon);
        dtm.setRowCount(0);
        for (Mon mon : dsMon) {
            Object[] rowData = new Object[]{
                    mon.getMonID(),
                    mon.getTenMon(),
                    mon.getMoTa(),
                    mon.getGia(),
                    mon.getDanhMucID()
            };
            dtm.addRow(rowData);
        }
       tbMon.setModel(dtm);
    }//GEN-LAST:event_btnTimMonActionPerformed

    private void cboxDanhMuc2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxDanhMuc2ItemStateChanged
        // TODO add your handling code here:
        hienThiCSDLComboBoxMon();
        hienThiAnhSP();
    }//GEN-LAST:event_cboxDanhMuc2ItemStateChanged

    public boolean kiemTraMonAnDaCoTrongBangTam(int MonID)
    {
        boolean kq =false;
         for (int i = 0; i < dtm2.getRowCount(); i++) {
             // Duyệt qua mỗi cột trên dòng hiện tại

                 // Lấy giá trị tại dòng i, cột j
                 int monID = Integer.parseInt(dtm2.getValueAt(i, 0).toString());
                 
                 if(monID==MonID)
                 {
                     kq=true;
                     break;
                 }
        }
        
        
        return kq;
    }
    private void btnThemMonVaoBangTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonVaoBangTamActionPerformed
        // TODO add your handling code here:
        
      
        if(kiemTraSL()){
        String soLuong=txtSoLuong.getText();
        MyComboBox cbbMon=(MyComboBox)cboxMon.getSelectedItem();
        int id=cbbMon.getMaLoai();
        String MonID = String.valueOf(id);
        String tenMon=cbbMon.getTenLoai();
        String gia= String.valueOf(MonDAO.layGiaMon(id));
        if(kiemTraMonAnDaCoTrongBangTam(id))
        {
            for (int i = 0; i < dtm2.getRowCount(); i++) {
                 // Duyệt qua mỗi cột trên dòng hiện tại

                     // Lấy giá trị tại dòng i, cột j
                     int monID = Integer.parseInt(dtm2.getValueAt(i, 0).toString());
                     int sl=Integer.parseInt(dtm2.getValueAt(i, 3).toString());
                     if(monID==Integer.parseInt(MonID))
                     {
                         dtm2.setValueAt(sl+Integer.parseInt(soLuong), i, 3);
                         break;
                     }
            }
        }
        else{
            Object[] rowData = new Object[] {MonID,tenMon,gia,soLuong};
            dtm2.addRow(rowData);
        
        
        }
        
        
        double tongGia=0;
        for (int i = 0; i < dtm2.getRowCount(); i++) {
                 // Duyệt qua mỗi cột trên dòng hiện tại

                     // Lấy giá trị tại dòng i, cột j
                     double giaTien=Double.parseDouble(dtm2.getValueAt(i, 2).toString());
                     int sl=Integer.parseInt(dtm2.getValueAt(i, 3).toString());
                     tongGia+=(giaTien*sl);
            }
        
        
        txtTongTien.setText(String.valueOf(tongGia));
        
        
        txtMaDH.setText(String.valueOf(DatHangDAO.layMaDHLonNhat()+1));
        }
    }//GEN-LAST:event_btnThemMonVaoBangTamActionPerformed

    
    
      
    private void btnXoaMonTrongBangTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonTrongBangTamActionPerformed
        // TODO add your handling code here:
          int selectedRow =tbBangTam.getSelectedRow();
            dtm2.removeRow(selectedRow);
    }//GEN-LAST:event_btnXoaMonTrongBangTamActionPerformed

    private void tbBangTamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangTamMouseClicked
        // TODO add your handling code here:
        
      
        
    }//GEN-LAST:event_tbBangTamMouseClicked

    private void btnSuaMonTrongBangTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMonTrongBangTamActionPerformed
        // TODO add your handling code here:
        if(kiemTraSL()){
          String soLuong=txtSoLuong.getText();
        MyComboBox cbbMon=(MyComboBox)cboxMon.getSelectedItem();
        int id=cbbMon.getMaLoai();
        String MonID = String.valueOf(id);
        String tenMon=cbbMon.getTenLoai();
        String gia= String.valueOf(MonDAO.layGiaMon(id));
        
        
         int selectedRow = tbBangTam.getSelectedRow();

    if (selectedRow >= 0) {
        // Cập nhật dữ liệu tại hàng đã chọn
        
        dtm2.setValueAt(MonID, selectedRow, 0); // Cột "ID Món"
        dtm2.setValueAt(tenMon, selectedRow, 1); // Cột "Tên Món"
        dtm2.setValueAt(gia, selectedRow, 2); // Cột "Giá"
        dtm2.setValueAt(soLuong, selectedRow, 3); // Cột "Số lượng"
    }
        
        }
    }//GEN-LAST:event_btnSuaMonTrongBangTamActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        
        DangNhap login = new DangNhap();
        
        login.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnDangXuatActionPerformed
    
    private void tbMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonMouseClicked
        // TODO add your handling code here:
        int rowSelected = tbMon.getSelectedRow();

        // Chắc chắn rằng đã chọn một dòng hợp lệ
        if(rowSelected != -1) {

           
            
            String monID=tbMon.getValueAt(rowSelected, 0).toString();
            txtMonID.setText(monID);
            
            String url=MonDAO.layAnh(Integer.parseInt(monID));
            ImageIcon ic=new ImageIcon(url);
            lbanh.setIcon(ic);
            
            
            String tenMon = tbMon.getValueAt(rowSelected, 1).toString();
            // Đặt giá trị cho txtTenMon
            txtTenMon.setText(tenMon);

            // Tiếp tục với các cột khác và ánh xạ vào các text field tương ứng
            String moTa = tbMon.getValueAt(rowSelected, 2).toString();
            txtMoTa.setText(moTa);

            // Nếu cột giá là kiểu số, bạn có thể cần chuyển đổi sang String
            // Ví dụ cột giá ở vị trí thứ 2
            double gia = (double) tbMon.getValueAt(rowSelected, 3);
            txtGia.setText(String.valueOf(gia));

            String danhMucID = tbMon.getValueAt(rowSelected, 4).toString();
           
           // DefaultComboBoxModel<MyComboBox> comboBoxModel = (DefaultComboBoxModel<MyComboBox>) cboxDanhMuc.getModel();
          //  for (int i = 0; i < comboBoxModel.getSize(); i++) {
          //  MyComboBox cbbItem = comboBoxModel.getElementAt(i);
            //if (cbbItem.getDanhMucID() == Integer.parseInt(danhMucID)) {
              //  cboxDanhMuc.setSelectedItem(cbbItem);
               
                //return;
            //}
        //}

            
        }

    }//GEN-LAST:event_tbMonMouseClicked

    private void bangXuatKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangXuatKhoMouseClicked
        // TODO add your handling code here:
        int row = bangXuatKho.getSelectedRow();
        if (row >= 0) {
            NhanVienIDXK.setText(bangXuatKho.getValueAt(row, 1).toString());
            txtNguyenLieuXK.setText(bangXuatKho.getValueAt(row, 2).toString());
            txtSoLuongXuat.setText(bangXuatKho.getValueAt(row, 3).toString());
            txtNgayXuatXK.setText(bangXuatKho.getValueAt(row, 4).toString());
        }
    }//GEN-LAST:event_bangXuatKhoMouseClicked

    private void btnAddXuatKHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddXuatKHoActionPerformed
        // TODO add your handling code here:
        XuatKhoDAO xuatKhoDAO = new XuatKhoDAO();
        int nhanVienID = Integer.parseInt(NhanVienIDXK.getText());
        String tenNhanVien = txtTenNVXK.getText();
        int nguyenLieuID = Integer.parseInt(NhanVienIDXK.getText());
        String tenNguyenLieu = txtTenNLXK.getText();
        int soLuongXuat = Integer.parseInt(txtSoLuongXuat.getText());
        Date ngayXuat = new Date(); // Ví dụ: bạn cần xử lý ngày thì lấy ngày từ một JDateChooser hoặc từ một JTextField khác

        XuatKho xuatKhoMoi = new XuatKho();
        xuatKhoMoi.setNhanVienID(nhanVienID);
        xuatKhoMoi.setTenNhanVien(tenNhanVien);
        xuatKhoMoi.setNguyenLieuID(nguyenLieuID);
        xuatKhoMoi.setTenNguyenLieu(tenNguyenLieu);
        xuatKhoMoi.setSoLuongXuat(soLuongXuat);
        xuatKhoMoi.setNgayXuat(ngayXuat);
        int rowsAffected = xuatKhoDAO.insertXuatKho(xuatKhoMoi);
        if (rowsAffected > 0) {
            List<XuatKho> danhSachXuatKho = xuatKhoDAO.getAllXuatKho();
            XuatKhoTableModel tableModel = new XuatKhoTableModel(danhSachXuatKho);
            bangXuatKho.setModel(tableModel); 
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại!");
        }

        
    }//GEN-LAST:event_btnAddXuatKHoActionPerformed

    private void btnEditXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditXuatKhoActionPerformed
        // TODO add your handling code here:
        int row = bangXuatKho.getSelectedRow();
        if (row != -1) {
            XuatKhoDAO xuatKhoDAO = new XuatKhoDAO();
            int nhanVienID = Integer.parseInt(NhanVienIDXK.getText());
            String tenNhanVien = txtTenNVXK.getText();
            int nguyenLieuID = Integer.parseInt(NhanVienIDXK.getText());
            String tenNguyenLieu = txtTenNLXK.getText();
            int soLuongXuat = Integer.parseInt(txtSoLuongXuat.getText());
            Date ngayXuat = new Date(); // Ví dụ: bạn cần xử lý ngày thì lấy ngày từ một JDateChooser hoặc từ một JTextField khác

            XuatKho xuatKhoMoi = new XuatKho();
            xuatKhoMoi.setNhanVienID(nhanVienID);
            xuatKhoMoi.setTenNhanVien(tenNhanVien);
            xuatKhoMoi.setNguyenLieuID(nguyenLieuID);
            xuatKhoMoi.setTenNguyenLieu(tenNguyenLieu);
            xuatKhoMoi.setSoLuongXuat(soLuongXuat);
            xuatKhoMoi.setNgayXuat(ngayXuat);
            int rowsAffected = xuatKhoDAO.updateXuatKho(xuatKhoMoi);
            if (rowsAffected > 0) {
                // Thông báo cập nhật thành công
                JOptionPane.showMessageDialog(null, "Đã cập nhật xuất kho thành công!");
            } else {
                // Thông báo cập nhật thất bại
                JOptionPane.showMessageDialog(null, "Cập nhật xuất kho không thành công!");
            }
        }
    }//GEN-LAST:event_btnEditXuatKhoActionPerformed

    private void btnFindXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindXuatKhoActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_btnFindXuatKhoActionPerformed

    private void btnPrintXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintXuatKhoActionPerformed
      
    }//GEN-LAST:event_btnPrintXuatKhoActionPerformed

    private void txtTimNguyenLieuTheoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimNguyenLieuTheoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimNguyenLieuTheoIDActionPerformed

    private void bangNhapKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangNhapKhoMouseClicked
        // TODO add your handling code here:
        int selectedRow = bangNhapKho.getSelectedRow();
        if (selectedRow != -1) {
            txtNhapKhoID.setText(bangNhapKho.getValueAt(selectedRow, 0).toString());
            txtNhanVienID.setText(bangNhapKho.getValueAt(selectedRow, 1).toString());
            txtNguyenLieuID.setText(bangNhapKho.getValueAt(selectedRow, 2).toString());
            txtNhaCungCapID.setText(bangNhapKho.getValueAt(selectedRow, 3).toString());
            txtSoLuongNhap.setText(bangNhapKho.getValueAt(selectedRow, 4).toString());
            txtGiaNhap.setText(bangNhapKho.getValueAt(selectedRow, 5).toString());
            txtNgayNhap.setText(bangNhapKho.getValueAt(selectedRow, 6).toString());
        }

    }//GEN-LAST:event_bangNhapKhoMouseClicked

    private void EditNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditNhapKhoActionPerformed
        // TODO add your handling code here:
        try {
            int nhapKhoID = Integer.parseInt(txtNhapKhoID.getText());
            int nhanVienID = Integer.parseInt(txtNhanVienID.getText());
            String tenNhanVien = txtTenNhanVien.getText();
            int nguyenLieuID = Integer.parseInt(txtNguyenLieuID.getText());
            String tenNguyenLieu = txtTenNguyenLieu.getText();
            int nhaCungCapID = Integer.parseInt(txtNhaCungCapID.getText());
            String tenNhaCungCap = txtTenNhaCC.getText();
            int soLuongNhap = Integer.parseInt(txtSoLuongNhap.getText());
            double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            Date ngayNhap = java.sql.Date.valueOf(txtNgayNhap.getText());

            NhapKho nhapKho = new NhapKho(nhapKhoID, nhanVienID, tenNhanVien, nguyenLieuID, tenNguyenLieu,
                    nhaCungCapID, tenNhaCungCap, soLuongNhap, giaNhap, ngayNhap);

            int rowsAffected = nhapKhoDAO.updateNhapKho(nhapKho);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sửa phiếu nhập kho thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa phiếu nhập kho thất bại.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho các trường số.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }

      

    }//GEN-LAST:event_EditNhapKhoActionPerformed

    private void XoaNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaNhapKhoActionPerformed
        // TODO add your handling code here:
         try {
            int nhapKhoID = Integer.parseInt(txtNhapKhoID.getText());
            int rowsAffected = nhapKhoDAO.deleteNhapKho(nhapKhoID);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Xóa phiếu nhập kho thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu nhập kho có ID " + nhapKhoID);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }
    
    }//GEN-LAST:event_XoaNhapKhoActionPerformed

    private void AddNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNhapKhoActionPerformed
        // TODO add your handling code here:
        try {
            int nhanVienID = Integer.parseInt(txtNhanVienID.getText());
            String tenNhanVien = txtTenNhanVien.getText();
            int nguyenLieuID = Integer.parseInt(txtNguyenLieuID.getText());
            String tenNguyenLieu = txtTenNguyenLieu.getText();
            int nhaCungCapID = Integer.parseInt(txtNhaCungCapID.getText());
            String tenNhaCungCap = txtTenNhaCC.getText();
            int soLuongNhap = Integer.parseInt(txtSoLuongNhap.getText());
            double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            Date ngayNhap = java.sql.Date.valueOf(txtNgayNhap.getText());

            NhapKho nhapKho = new NhapKho(nhanVienID, tenNhanVien, nguyenLieuID, tenNguyenLieu,
                                          nhaCungCapID, tenNhaCungCap, soLuongNhap, giaNhap, ngayNhap);

            int rowsAffected = nhapKhoDAO.insertNhapKho(nhapKho);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập kho thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập kho thất bại.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho các trường số.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }

        

    
       
    }//GEN-LAST:event_AddNhapKhoActionPerformed

    private void txtNhapKhoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhapKhoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhapKhoIDActionPerformed

    private void txtNguyenLieuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNguyenLieuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNguyenLieuIDActionPerformed

    private void FindNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindNhapKhoActionPerformed
        // TODO add your handling code here:
       try {
            int nhapKhoID = txtNhapKhoID.getText().isEmpty() ? 0 : Integer.parseInt(txtNhapKhoID.getText());
            int nhanVienID = txtNhanVienID.getText().isEmpty() ? 0 : Integer.parseInt(txtNhanVienID.getText());
            int nguyenLieuID = txtNguyenLieuID.getText().isEmpty() ? 0 : Integer.parseInt(txtNguyenLieuID.getText());
            int nhaCungCapID = txtNhaCungCapID.getText().isEmpty() ? 0 : Integer.parseInt(txtNhaCungCapID.getText());

            List<NhapKho> danhSachKetQua = nhapKhoDAO.findNhapKho(nhapKhoID, nhanVienID, nguyenLieuID, nhaCungCapID);
            if (!danhSachKetQua.isEmpty()) {
                StringBuilder resultText = new StringBuilder();
                for (NhapKho nhapKho : danhSachKetQua) {
                    resultText.append(nhapKho.toString()).append("\n");
                }
                txtFind.setText(resultText.toString());
            } else {
                txtFind.setText("Không tìm thấy kết quả.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }
    
    }//GEN-LAST:event_FindNhapKhoActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        
        MyComboBox cbb= (MyComboBox)cboxDanhMuc.getSelectedItem();
        int danhMucID = cbb.getMaLoai();
        
         ArrayList<Mon> dsMon = MonDAO.layDanhSachMonTheoDanhMuc(danhMucID);
        dtm.setRowCount(0);
        for (Mon mon : dsMon) {
            Object[] rowData = new Object[]{
                    mon.getMonID(),
                    mon.getTenMon(),
                    mon.getMoTa(),
                    mon.getGia(),
                    mon.getDanhMucID()
            };
            dtm.addRow(rowData);
        }
        
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnDonBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonBanActionPerformed
        // TODO add your handling code here:
        int BanID=Integer.parseInt(lbBanIDDaChon.getText());
        BanDAO.capNhaTrangThaiBanTrong(BanID);
        jpChonBan.removeAll();
        jpChonBan.revalidate(); // Tái xác nhận bố cục của panel
        jpChonBan.repaint();
        hienThiDuLieuBan();
       
        
    }//GEN-LAST:event_btnDonBanActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        int banID=BanDAO.layMaMonLonNhat();
        
        String tenBan="Bàn "+String.valueOf(banID);
        String trangThai="Còn trống";
        String soGhe=jtf_soghe.getText();
        
        Ban pb=new Ban((banID+1),tenBan,trangThai,Integer.parseInt(soGhe));
       if(BanDAO.themBan(pb))
            JOptionPane.showMessageDialog(this,"Thêm bàn thành công","Thong bao", JOptionPane.INFORMATION_MESSAGE);
        
       Ban();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        String ban=jtf_idban.getText();
        int idban=Integer.parseInt(ban);
        BanDAO.xoaBan(idban);
        JOptionPane.showMessageDialog(this,"Xóa bàn thành cong","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        Ban();

    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        String tenBan=jtf_tenban.getText();
        int banID=BanDAO.layMaMonLonNhat();
        String trangThai=jtf_trangthai.getText();
        String soGhe=jtf_soghe.getText();
        
        Ban pb=new Ban(banID,tenBan,trangThai,Integer.parseInt(soGhe));
        if(BanDAO.capNhatBan(pb))
        {
            int confirm= JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật dữ liệu không ", "Cập nhật dữ liệu ", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
            Ban();
            }
            else
                return;
            
        }
        

    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timActionPerformed
        // TODO add your handling code here:
       

    }//GEN-LAST:event_btn_timActionPerformed

    private void jtf_ngaydatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_ngaydatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_ngaydatActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
       int datHangID= Integer.parseInt(jtf_dathang.getText());
       int maNV=Integer.parseInt(jtf_nhanvien.getText());
       int banID=Integer.parseInt(jtf_datban.getText());
       float tongTien=Float.parseFloat(jtf_tonggia.getText());
       Date currentDate;
       currentDate = new Date();

        DatHang dh=new DatHang(datHangID,maNV,banID,currentDate,tongTien);
        int confirm= JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật dữ liệu không ", "Cập nhật dữ liệu ", JOptionPane.YES_NO_OPTION);
            
        if(confirm==JOptionPane.YES_OPTION){
                DatHangDAO.capNhatDathang(dh);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
                DatHang();
            }
            else
                return;


    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_timkiemdathangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemdathangActionPerformed
        // TODO add your handling code here:
       
        ArrayList<DatHang> dsDatHang = DatHangDAO.timkiemDatHang(jtf_timkiem.getText());
        DefaultTableModel dtmDatHang=(DefaultTableModel) jtb_dathang.getModel();
        dtmDatHang.setRowCount(0);
        for (DatHang dh: dsDatHang){
             Object[] rowData = new Object[]{
                    dh.getDatHangID(),
                 dh.getNhanVienID(),
                 dh.getTongGia(),
                    dh.getBanID(),
                    dh.getNgayDat()
                    
                    
            };
            dtmDatHang.addRow(rowData);  
        }
        jtb_dathang.setModel(dtmDatHang);
               

    }//GEN-LAST:event_btn_timkiemdathangActionPerformed

    private void jtf_dathangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_dathangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_dathangActionPerformed

    private void jtb_banMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_banMouseClicked
        // TODO add your handling code here:
        int rowSelected = jtb_ban.getSelectedRow();

        // Chắc chắn rằng đã chọn một dòng hợp lệ
        if(rowSelected != -1) {

            String ban=jtb_ban.getValueAt(rowSelected, 0).toString();
            jtf_idban.setText(ban);
            // Giả sử ta có các cột là Tên món, mô tả, giá, ...
            // Lấy giá trị từ cột Tên món (thí dụ cột số 0)
            String tenBan = jtb_ban.getValueAt(rowSelected, 1).toString();
            // Đặt giá trị cho txtTenMon
            jtf_tenban.setText(tenBan);

            // Tiếp tục với các cột khác và ánh xạ vào các text field tương ứng
            String trangThai = jtb_ban.getValueAt(rowSelected, 2).toString();
            jtf_trangthai.setText(trangThai);

            // Nếu cột giá là kiểu số, bạn có thể cần chuyển đổi sang String
            // Ví dụ cột giá ở vị trí thứ 2
            
            

            String soGhe = jtb_ban.getValueAt(rowSelected,3 ).toString();
            jtf_soghe.setText(soGhe);
        }
    }//GEN-LAST:event_jtb_banMouseClicked

    private void tbMonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMonMouseExited

    private void jtb_dathangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_dathangMouseClicked
        // TODO add your handling code here:
        int rowSelected = jtb_dathang.getSelectedRow();

        // Chắc chắn rằng đã chọn một dòng hợp lệ
        if(rowSelected != -1) {

            String datHang=jtb_dathang.getValueAt(rowSelected, 0).toString();
            jtf_dathang.setText(datHang);
            // Giả sử ta có các cột là Tên món, mô tả, giá, ...
            // Lấy giá trị từ cột Tên món (thí dụ cột số 0)
            String nhanVien = jtb_dathang.getValueAt(rowSelected, 1).toString();
            // Đặt giá trị cho txtTenMon
            jtf_nhanvien.setText(nhanVien);
             float tongGia = (float) jtb_dathang.getValueAt(rowSelected, 2);
            jtf_tonggia.setText(String.valueOf(tongGia));
            String ban = jtb_dathang.getValueAt(rowSelected, 3).toString();
            jtf_datban.setText(ban);
            String ngayDat = jtb_dathang.getValueAt(rowSelected, 4).toString();
            jtf_ngaydat.setText(ngayDat);
            

            // Tiếp tục với các cột khác và ánh xạ vào các text field tương ứng
            

            // Nếu cột giá là kiểu số, bạn có thể cần chuyển đổi sang String
            // Ví dụ cột giá ở vị trí thứ 2
            
            

            
        }
    }//GEN-LAST:event_jtb_dathangMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        lbTitle.setText("Quản lý món");

        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jtf_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_timkiemActionPerformed

   
    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        JFileChooser f = new JFileChooser("D:\\NetBeanProject\\DoAnQuanLyQuanCaPhe\\src\\images");
        f.setDialogTitle("mở file");
        
        f.setFileSelectionMode(f.FILES_ONLY);
        int returnValue=f.showOpenDialog(this);
        if(returnValue ==f.APPROVE_OPTION)
        {
            File file=f.getSelectedFile();
            duongdananh=file.getAbsolutePath();
            BufferedImage b;
            try {
                b=ImageIO.read(file);
                lbanh.setIcon(new ImageIcon(b));
            } catch (Exception e) {
            }
            
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
        int iddh=Integer.parseInt(jtf_dathang.getText());
       // xuatHoaDon(iddh);
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnGopBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGopBanActionPerformed
        // TODO add your handling code here:
        ChonBan chonBan=new ChonBan();
        chonBan.setVisible(true);
        
        
    }//GEN-LAST:event_btnGopBanActionPerformed

    private void btnReloadBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadBanActionPerformed
        // TODO add your handling code here:
        jpChonBan.removeAll();
        jpChonBan.revalidate(); // Tái xác nhận bố cục của panel
        jpChonBan.repaint();
        hienThiDuLieuBan();
       
    }//GEN-LAST:event_btnReloadBanActionPerformed

    private void btnTachBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTachBanActionPerformed
        // TODO add your handling code here:
        TachBan tb= new TachBan();
        tb.setVisible(true);
    }//GEN-LAST:event_btnTachBanActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        Ban();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnBoSungDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoSungDatHangActionPerformed
        // TODO add your handling code here:
       MyComboBox cbbMon=(MyComboBox)cboxMon.getSelectedItem();
       int maMon =cbbMon.getMaLoai();
      
       int maNV = Integer.parseInt(lbMaNV.getText());
      // int banID=Integer.parseInt(jtf_datban.getText());
       float tongTien=Float.parseFloat(txtTongTien.getText());

       Date currentDate;
       currentDate = new Date();
       int maDatHang =Integer.parseInt(jtf_dathang.getText());

       
      
   // xu ly csdl chi tiet don hang
        for (int i = 0; i < dtm2.getRowCount(); i++) {
             // Duyệt qua mỗi cột trên dòng hiện tại

                 // Lấy giá trị tại dòng i, cột j
                 int monID = Integer.parseInt(dtm2.getValueAt(i, 0).toString());
                 int soLuong=Integer.parseInt(dtm2.getValueAt(i, 3).toString());
                 ChiTietDatHang ctdh=new ChiTietDatHang(ChiTietDonHangDAO.layMaCTDHLonNhat()+1,maDatHang,monID,soLuong);;
                int kt=ChiTietDonHangDAO.layMaCTDHTheoIDDatHangIDMonAn(maDatHang, maMon);
                 if(kt==0){
                    if(ChiTietDonHangDAO.themCTDH(ctdh))
                    {

                        ArrayList<Integer> dsnl;
                        dsnl=Mon_KhoNguyenLieuDAO.layDanhSachNguyenLieu(monID);
                        for(int item : dsnl)
                        {
                            float slSuDung =Mon_KhoNguyenLieuDAO.laySLSuDung(monID,item);
                            KhoNguyenLieuDAO.capNhatSLTon(item, slSuDung,soLuong);
                            JOptionPane.showMessageDialog(this, "bổ sung thành công");
                        }
                        DatHangDAO.capNhatTongTien(tongTien, maDatHang);
                    }
                 }
        
                 else{
                     if(ChiTietDonHangDAO.capNhatSL(soLuong, kt))
                     {
                         ArrayList<Integer> dsnl;
                        dsnl=Mon_KhoNguyenLieuDAO.layDanhSachNguyenLieu(monID);
                        for(int item : dsnl)
                        {
                            float slSuDung =Mon_KhoNguyenLieuDAO.laySLSuDung(monID,item);
                            KhoNguyenLieuDAO.capNhatSLTon(item, slSuDung,soLuong);
                            JOptionPane.showMessageDialog(this, "bổ sung thành công");
                        }
                        DatHangDAO.capNhatTongTien(tongTien, maDatHang);
                     }
                 }
                 
        }
        
      



         txtMaDH.setText("");
        
         
         dtm2.setRowCount(0);
         DatHang();
         
         
    }//GEN-LAST:event_btnBoSungDatHangActionPerformed

    private void txtSoLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSoLuongFocusLost

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        iddh=Integer.parseInt(jtf_dathang.getText());
        CTDH chitiet =new CTDH();
        chitiet.setVisible(true);
        
      
    }//GEN-LAST:event_btnChiTietActionPerformed
public void hienThiAnhSP()
{
        MyComboBox cbbDuocChon=(MyComboBox)cboxMon.getSelectedItem();
        
        int monID=cbbDuocChon.getMaLoai();
        String url_goc="D:\\NetBeanProject\\DoAnQuanLyQuanCaPhe\\src\\images\\";
        String url=url_goc+MonDAO.layAnh(monID);
        ImageIcon ic=new ImageIcon(url);
        lbAnhSP.setIcon(ic);
        lbAnhSP.setText("");
        
}
    private void cboxMonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxMonItemStateChanged
        // TODO add your handling code here:
       
        hienThiAnhSP();
    }//GEN-LAST:event_cboxMonItemStateChanged

    private void btnXuatKho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatKho1ActionPerformed
        // TODO add your handling code here:
         lbTitle.setText("Quản lý xuất kho");

        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(true);
        jpKho.setVisible(false);
        jpNhaCC.setVisible(false);
    }//GEN-LAST:event_btnXuatKho1ActionPerformed

    private void btnkhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhoActionPerformed
        // TODO add your handling code here:
        lbTitle.setText("Quản lý kho");

        jpMon.setVisible(false);
        jpQuanLyBan.setVisible(false);
        jpQuanLyDatHang.setVisible(false);
        jpQuanLyNhapKho.setVisible(false);
        jpQuanLyXuatKho.setVisible(false);
        jpKho.setVisible(true);
        jpNhaCC.setVisible(false);
    }//GEN-LAST:event_btnkhoActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:
        String tenNhaCungCap = txtNCCID.getText();
        String thongTinLienLac = txtTenNCC.getText();

        // Kiểm tra xem các trường văn bản có trống không
        if (tenNhaCungCap.isEmpty() || thongTinLienLac.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        // Tạo đối tượng Nhà cung cấp mới và thêm vào cơ sở dữ liệu
        NhaCungCap newNhaCungCap = new NhaCungCap();
        newNhaCungCap.setTenNhaCungCap(tenNhaCungCap);
        newNhaCungCap.setThongTinLienLac(thongTinLienLac);

        boolean success = nhaCungCapDAO.insertNhaCungCap(newNhaCungCap);
        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
            loadTableData(); // Load lại dữ liệu sau khi thêm thành công
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại!");
        }
    
    }//GEN-LAST:event_btThemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbNCC.getSelectedRow();
                if (selectedRow != -1) {
                    int nhaCungCapID = (int) tbNCC.getValueAt(selectedRow, 0);
                    String tenNhaCungCap = txtNCCID.getText();
                    String thongTinLienLac = txtTenNCC.getText();
                    NhaCungCap nhaCungCap = new NhaCungCap(nhaCungCapID, tenNhaCungCap, thongTinLienLac);
                    boolean success = nhaCungCapDAO.updateNhaCungCap(nhaCungCap);
                    if (success) {
                        // Cập nhật lại bảng để hiển thị thông tin mới
                        loadTableData();
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                    }
                }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int selectedRow = tbNCC.getSelectedRow();
                    if (selectedRow != -1) {
                        // Cho phép người dùng xóa nhà cung cấp khi chọn một dòng trên bảng
                        int response = JOptionPane.showConfirmDialog(this,
                                "Bạn có chắc chắn muốn xóa nhà cung cấp này?",
                                "Xác nhận xóa",
                                JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            int nhaCungCapID = (int) tbNCC.getValueAt(selectedRow, 0);
                            boolean success = nhaCungCapDAO.deleteNhaCungCap(nhaCungCapID);
                            if (success) {
                                // Cập nhật lại bảng để hiển thị thông tin mới
                                loadTableData();
                                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                            } else {
                                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                            }
                        }
                    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String searchKeyword = timkiemncc.getText().trim();
                if (!searchKeyword.isEmpty()) {
                    List<NhaCungCap> nhaCungCapList = nhaCungCapDAO.timKiemNhaCungCapTheoTen(searchKeyword);
                    if (nhaCungCapList != null) {
                        // Hiển thị kết quả tìm kiếm trên bảng
                        loadTableData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
                }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void sltonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sltonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sltonActionPerformed

    private void btnThemkhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemkhoActionPerformed
        // TODO add your handling code here:
        int nguyenLieuID = Integer.parseInt(idkho.getText());
        String tenNguyenLieu = Tenkho.getText();
        String donVi = donvi.getText();
        float giaTheoDonVi = Float.parseFloat(giadonvi.getText());
        float soLuongTon = Float.parseFloat(slton.getText());

        KhoNguyenLieu nl = new KhoNguyenLieu();
        nl.setNguyenLieuID(nguyenLieuID);
        nl.setTenNguyenLieu(tenNguyenLieu);
        nl.setDonVi(donVi);
        nl.setGiaTheoDonVi(giaTheoDonVi);
        nl.setSoLuongTon(soLuongTon);

        boolean result = khoNguyenLieuDAO.insert(nl);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thành công.");
            // Cập nhật lại bảng
            KhoNguyenLieuTableModel updatedModel = new KhoNguyenLieuTableModel(khoNguyenLieuDAO.getAll());
            tbKhoNguyenLieu.setModel(updatedModel);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thất bại.");
        } 
    }//GEN-LAST:event_btnThemkhoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         // Lấy dữ liệu từ các trường JTextField
        khoNguyenLieuDAO = new KhoNguyenLieuDAO();
        List<KhoNguyenLieu> data = khoNguyenLieuDAO.getAll();
        KhoNguyenLieuTableModel model1 = new KhoNguyenLieuTableModel(data);
        tbKhoNguyenLieu.setModel(model1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSuakhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuakhoActionPerformed
        // TODO add your handling code here:
        int nguyenLieuID = Integer.parseInt(idkho.getText());
        String tenNguyenLieu = Tenkho.getText();
        String donVi = donvi.getText();
        float giaTheoDonVi = Float.parseFloat(giadonvi.getText());
        float soLuongTon = Float.parseFloat(slton.getText());

    // Tạo đối tượng KhoNguyenLieu từ dữ liệu đã lấy
    KhoNguyenLieu nl = new KhoNguyenLieu();
    nl.setNguyenLieuID(nguyenLieuID);
    nl.setTenNguyenLieu(tenNguyenLieu);
    nl.setDonVi(donVi);
    nl.setGiaTheoDonVi(giaTheoDonVi);
    nl.setSoLuongTon(soLuongTon);

    // Gọi phương thức update của KhoNguyenLieuDAO để cập nhật vào cơ sở dữ liệu
    boolean result = khoNguyenLieuDAO.update(nl);
    if (result) {
        JOptionPane.showMessageDialog(this, "Cập nhật nguyên liệu thành công.");
        // Cập nhật lại bảng
        KhoNguyenLieuTableModel updatedModel = new KhoNguyenLieuTableModel(khoNguyenLieuDAO.getAll());
        tbKhoNguyenLieu.setModel(updatedModel);
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật nguyên liệu thất bại.");
    }
                
    }//GEN-LAST:event_btnSuakhoActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
         // Lấy ID của nguyên liệu cần xóa từ JTextField
    int nguyenLieuID = Integer.parseInt(idkho.getText());

    // Gọi phương thức delete của KhoNguyenLieuDAO để xóa khỏi cơ sở dữ liệu
    boolean result = khoNguyenLieuDAO.delete(nguyenLieuID);
    if (result) {
        JOptionPane.showMessageDialog(this, "Xóa nguyên liệu thành công.");
        // Cập nhật lại bảng
        KhoNguyenLieuTableModel updatedModel = new KhoNguyenLieuTableModel(khoNguyenLieuDAO.getAll());
        tbKhoNguyenLieu.setModel(updatedModel);
    } else {
        JOptionPane.showMessageDialog(this, "Xóa nguyên liệu thất bại.");
    }
        
            
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btntimkeimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkeimActionPerformed
        // TODO add your handling code here:
         // Lấy tên nguyên liệu từ JTextField tìm kiếm
        String tenNguyenLieu = timkiemkho.getText();

        // Gọi phương thức findByName của KhoNguyenLieuDAO để tìm nguyên liệu
        List<KhoNguyenLieu> result = khoNguyenLieuDAO.findByName(tenNguyenLieu);

        // Cập nhật lại model của bảng với kết quả tìm kiếm
        KhoNguyenLieuTableModel updatedModel = new KhoNguyenLieuTableModel(result);
        tbKhoNguyenLieu.setModel(updatedModel);
    }//GEN-LAST:event_btntimkeimActionPerformed

    private void btnDeleteXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteXuatKhoActionPerformed
        // TODO add your handling code here:
        // Lấy chỉ số của dòng được chọn
        try {
            int xuatKhoID = Integer.parseInt(txtXuatKhoID.getText());
            int rowsAffected = xuatKhoDAO.deleteXuatKho(xuatKhoID);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Xóa phiếu xuất kho thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu xuất kho có ID " + xuatKhoID);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnDeleteXuatKhoActionPerformed

    private void loadTableData() {
        List<NhaCungCap> nhaCungCapList = nhaCungCapDAO.getAllNhaCungCap();
        NhaCungCapTableModel tableModel = new NhaCungCapTableModel();
        tableModel.setData(nhaCungCapList);
        tbNCC.setModel(tableModel);
    }
     
     
                                   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNhapKho;
    private javax.swing.JButton EditNhapKho;
    private javax.swing.JButton FindNhapKho;
    private javax.swing.JTextField NhanVienIDXK;
    private javax.swing.JTextField Tenkho;
    private javax.swing.JButton XoaNhapKho;
    private javax.swing.JTable bangNhapKho;
    private javax.swing.JTable bangXuatKho;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btnAddXuatKHo;
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnBoSungDatHang;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnDeleteXuatKho;
    private javax.swing.JButton btnDonBan;
    private javax.swing.JButton btnEditXuatKho;
    private javax.swing.JButton btnFindXuatKho;
    private javax.swing.JButton btnGopBan;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnMon;
    private javax.swing.JButton btnNhapKho;
    private javax.swing.JButton btnPrintXuatKho;
    private javax.swing.JButton btnQuanLyDatHang;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnReloadBan;
    private javax.swing.JButton btnSuaMon;
    private javax.swing.JButton btnSuaMonTrongBangTam;
    private javax.swing.JButton btnSuakho;
    private javax.swing.JButton btnTachBan;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnThemMonVaoBangTam;
    private javax.swing.JButton btnThemkho;
    private javax.swing.JButton btnTimMon;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JButton btnXoaMonTrongBangTam;
    private javax.swing.JButton btnXuatKho1;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_timkiemdathang;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JButton btnkho;
    private javax.swing.JButton btnncc;
    private javax.swing.JButton btntimkeim;
    private javax.swing.JComboBox<String> cboxDanhMuc;
    private javax.swing.JComboBox<String> cboxDanhMuc2;
    private javax.swing.JComboBox<String> cboxMon;
    private javax.swing.JTextField donvi;
    private javax.swing.JTextField giadonvi;
    private javax.swing.JTextField idkho;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlb_ban;
    private javax.swing.JPanel jpChonBan;
    private javax.swing.JPanel jpContainer;
    private javax.swing.JPanel jpKho;
    private javax.swing.JPanel jpMon;
    private javax.swing.JPanel jpNhaCC;
    private javax.swing.JPanel jpQuanLyBan;
    private javax.swing.JPanel jpQuanLyDatHang;
    private javax.swing.JPanel jpQuanLyNhapKho;
    private javax.swing.JPanel jpQuanLyXuatKho;
    private javax.swing.JPanel jpanelnhapkho;
    private javax.swing.JPanel jpanelnhapkkho2;
    private javax.swing.JTable jtb_ban;
    private javax.swing.JTable jtb_dathang;
    private javax.swing.JTextField jtf_datban;
    private javax.swing.JTextField jtf_dathang;
    private javax.swing.JTextField jtf_idban;
    private javax.swing.JTextField jtf_ngaydat;
    private javax.swing.JTextField jtf_nhanvien;
    private javax.swing.JTextField jtf_soghe;
    private javax.swing.JTextField jtf_tenban;
    private javax.swing.JTextField jtf_timkiem;
    private javax.swing.JTextField jtf_tonggia;
    private javax.swing.JTextField jtf_trangthai;
    private javax.swing.JLabel lbAnhSP;
    private javax.swing.JLabel lbBanIDDaChon;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMenu;
    private javax.swing.JLabel lbNgayDat;
    private javax.swing.JLabel lbTenNhanVien;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lb_ban;
    private javax.swing.JLabel lb_dathang;
    private javax.swing.JLabel lb_ngaydat;
    private javax.swing.JLabel lb_nhanvien;
    private javax.swing.JLabel lb_soghe;
    private javax.swing.JLabel lb_tenban;
    private javax.swing.JLabel lb_tonggia;
    private javax.swing.JLabel lb_trangthai;
    private javax.swing.JLabel lbanh;
    private javax.swing.JPanel panelDatHang;
    private javax.swing.JPanel slideMenu;
    private javax.swing.JTextField slton;
    private javax.swing.JTable tbBangTam;
    private javax.swing.JTable tbKhoNguyenLieu;
    private javax.swing.JTable tbMon;
    private javax.swing.JTable tbNCC;
    private javax.swing.JTextField timkiemkho;
    private javax.swing.JTextField timkiemncc;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaDH;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtMonID;
    private javax.swing.JTextField txtNCCID;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNgayXuatXK;
    private javax.swing.JTextField txtNguyenLieuID;
    private javax.swing.JTextField txtNguyenLieuXK;
    private javax.swing.JTextField txtNhaCungCapID;
    private javax.swing.JTextField txtNhanVienID;
    private javax.swing.JTextField txtNhapKhoID;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuongNhap;
    private javax.swing.JTextField txtSoLuongXuat;
    private javax.swing.JTextField txtTenMon;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTenNLXK;
    private javax.swing.JTextField txtTenNVXK;
    private javax.swing.JTextField txtTenNguyenLieu;
    private javax.swing.JTextField txtTenNhaCC;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTimMon;
    private javax.swing.JTextField txtTimNguyenLieuTheoID;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JTextField txtXuatKhoID;
    // End of variables declaration//GEN-END:variables



 
}
