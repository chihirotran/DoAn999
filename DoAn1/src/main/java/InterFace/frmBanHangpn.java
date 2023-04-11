/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package InterFace;

import Database.Connect;
import Model.HoaDon;
import Model.KhachHang;
import Model.MatHang;
import Model.NCC;
import Process.QuanLyDonHang;
import Process.QuanLyKhachHang;
import Process.QuanLyKhoHang;
import Process.QuanLyNCC;
import javax.swing.JFrame;


import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author chihi
 */
public class frmBanHangpn extends javax.swing.JPanel {
     private final  DefaultTableModel tbModel = new DefaultTableModel();
    private final QuanLyNCC lsp = new QuanLyNCC();
    private final QuanLyKhachHang qlkh = new QuanLyKhachHang();
     private final QuanLyKhoHang qlkho = new QuanLyKhoHang();
    private final QuanLyDonHang qldh = new QuanLyDonHang();
    public Connect cn= new Connect();

    


    private boolean AddDH = false, ChangeDH = false;
    private boolean AddLK = false, ChangeLK = false;

    private boolean KH = false, LK = false;
    public JFrame jframe;
    public String manvString;
    public Long TongTien1DoaDon;
    public frmBanHangpn(String MaNV) throws SQLException {
        
        manvString = MaNV;
        initComponents();
        lbNV.setText(MaNV);
        String[] colName = {"MaHD","NgayTaoHD","TongTien","MANV","MaHH","SoLuong","MaKH","HoTen","Dia Chi","SDT","Email"};
        tbModel.setColumnIdentifiers(colName);
        tbHoaDon.setModel(tbModel);
        showData();
        ShowDataCombo();
        ShowDataComboMaKH();
        tfSL.setEnabled(false);
//        p.image = new ImageIcon("check.png");
//        jLabel3.setIcon(p.image);
    }

    frmBanHangpn(frmThuNgan aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        public List<HoaDon> getdata() throws SQLException{
        List<HoaDon> lst = qldh.getAll();
        return lst;
    }
    public List<HoaDon> getdatatheomaHD(String maHD) throws SQLException{
        List<HoaDon> lst = qldh.getdatatheoHoaDon(maHD);
        return lst;
    }
    public List<NCC> getdatamathang() throws SQLException{
        List<NCC> lst = lsp.getAll();
        return lst;
    }
    public List<KhachHang> getdatamakh() throws SQLException{
        List<KhachHang> lst = qlkh.getAll();
        return lst;
    }
    public List<MatHang> getdatattheoncc(String ncc) throws SQLException{
        List<MatHang> lst = lsp.GetTenSP(ncc);
        return lst;
    }
    public List<KhachHang> getdatathongtinkhachhang(String makh) throws SQLException{
        List<KhachHang> lst = qlkh.GetTenSP(makh);
        return lst;
    }
    public final void ShowDataCombo() throws SQLException{
        List<NCC> data = getdatamathang();
        for(int i=0; i<data.size(); i++) {
        //Them du lieu vao Combobox
            cbxNCC.addItem(data.get(i).getTenNCC());
        }
    }
    public final void ShowDataComboMaKH() throws SQLException{
        List<KhachHang> data = getdatamakh();
        for(int i=0; i<data.size(); i++) {
        //Them du lieu vao Combobox
            cbxMaKH.addItem(data.get(i).getMaKH());
        }
    }
    public final void ShowTenSPTheoNCC(String ml) throws SQLException{
        List<MatHang> data = getdatattheoncc(ml);
        ClearCBX();
        for(int i=0; i<data.size(); i++) {
        //Them du lieu vao Combobox
            cbxTenHang.addItem(data.get(i).getTenSP());
        }
    }
    public final void ClearCBX(){
        int itemCount = cbxTenHang.getItemCount();
        for(int i = 0; i < itemCount; i++){
        cbxTenHang.removeItemAt(0);
}
    }
    public void clearData(){
        while(tbModel.getRowCount()>0){
            tbModel.removeRow(0);
        }
    }
    
    public void showData() throws SQLException{
        clearData();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<HoaDon> data = getdata();
        for(int i=0; i <data.size();i++){
            int a = Integer.parseInt(""+data.get(i).getTongTienLong());
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+formatter.format(a),
                data.get(i).getMaNV(),
                data.get(i).getMaHH(),
                ""+data.get(i).getSoLuong(),
                data.get(i).getMaKH(),
                data.get(i).getHOTEN(),
                data.get(i).getDIACHI(),
                data.get(i).getSDT(),
                data.get(i).getEMAIL()
            };
        tbModel.addRow(row11);
        }
    }
        public void showDatatheoMaHD(String MaHD) throws SQLException{
        clearData();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<HoaDon> data = getdatatheomaHD(MaHD);
        for(int i=0; i <data.size();i++){
            int a = Integer.parseInt(""+data.get(i).getTongTienLong());
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+formatter.format(a),
                data.get(i).getMaNV(),
                data.get(i).getMaHH(),
                ""+data.get(i).getSoLuong(),
                data.get(i).getMaKH(),
                data.get(i).getHOTEN(),
                data.get(i).getDIACHI(),
                data.get(i).getSDT(),
                data.get(i).getEMAIL()
            };
        tbModel.addRow(row11);
        }
    }
    public void showDataKH(String mkh) throws SQLException{
        
        List<KhachHang> data = getdatathongtinkhachhang(mkh);
            String ttkh = "Ten:"+data.get(0).getHoTen()+"   DiaChi:"+data.get(0).getDiaChi()+"   SDT:"+data.get(0).getSDT()+"   Email:"+data.get(0).getEmail();
            tfttkh.setText(ttkh);
        }
    
    

    private frmBanHangpn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    private void checkhoaDon() {
       
    }
    private void Enabled() {
        
        lblStatus.setText("Trạng Thái!");
        
    }

    private void EnabledAddLK() {
        cbxNCC.setEnabled(true);
        
        
  
        lblStatus.setText("Trạng Thái!");
    }

    private void Disabled() {
        cbxNCC.setEnabled(false);
        
        cbxTenHang.setEnabled(false);
        tfSL.setEnabled(false);
        
        
        
  
    }

    private void Refresh() throws SQLException {
        String[] colName = {"MaHD","NgayTaoHD","TongTien","MANV","MaHH","SoLuong","MaKH","HoTen","Dia Chi","SDT","Email"};
        tbModel.setColumnIdentifiers(colName);
        tbHoaDon.setModel(tbModel);
        
        showData();
        
        btnXoaDonHang.setEnabled(false);
        btnSave.setEnabled(false);
        btnSuaDonHang.setEnabled(false);
        refreshProduct();
        tfMaHD.setEnabled(true);
        btnThemDonHang.setEnabled(true);
        cbxMaKH.setEnabled(true);

    }

    private void refreshProduct() {
        
        tfSL.setText("");
        
  
        lblTongTien.setText("0 VNĐ");
    }
    /**
     * Creates new form frmBanHangpn
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbxMaKH = new javax.swing.JComboBox<>();
        tfttkh = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbxTenHang = new javax.swing.JComboBox<>();
        tfSL = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxNCC = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnThemDonHang = new javax.swing.JButton();
        btnSuaDonHang = new javax.swing.JButton();
        btnXoaDonHang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        btnThemDonHang1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        tfMaHD = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbNV = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cập Nhật Đơn Đặt Hàng");

        lblStatus.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Trạng Thái");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Mã Khách Hàng");

        cbxMaKH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaKHItemStateChanged(evt);
            }
        });

        tfttkh.setEditable(false);
        tfttkh.setText("jTextField2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfttkh, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfttkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbxTenHang.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxTenHangPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbxTenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTenHangActionPerformed(evt);
            }
        });

        tfSL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSLKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Số Lượng:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Tên Hàng");

        cbxNCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNCCItemStateChanged(evt);
            }
        });
        cbxNCC.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxNCCPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbxNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxNCCMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("Nhà Cung Cấp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfSL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(243, 243, 243))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbxTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        jLabel15.setText("Mã Hóa Đơn:");

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Refresh-icon.png"))); // NOI18N
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnThemDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Pay.png"))); // NOI18N
        btnThemDonHang.setText("Đơn Hàng");
        btnThemDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDonHangActionPerformed(evt);
            }
        });

        btnSuaDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add.png"))); // NOI18N
        btnSuaDonHang.setText("Thêm");
        btnSuaDonHang.setEnabled(false);
        btnSuaDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDonHangActionPerformed(evt);
            }
        });

        btnXoaDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        btnXoaDonHang.setText("Đơn Hàng");
        btnXoaDonHang.setEnabled(false);
        btnXoaDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDonHangActionPerformed(evt);
            }
        });

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbHoaDon.setDragEnabled(true);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDon);

        btnThemDonHang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Pay.png"))); // NOI18N
        btnThemDonHang1.setText("In");
        btnThemDonHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDonHang1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemDonHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaDonHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXoaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tổng Tiền Đơn Hàng:");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        lblTongTien.setText("0 VNĐ");

        tfMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfMaHDKeyReleased(evt);
            }
        });

        jLabel1.setText("MaNV:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(170, 170, 170)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNV)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel7)
                        .addComponent(lblTongTien))
                    .addComponent(tfMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbNV)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxMaKHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaKHItemStateChanged
        // TODO add your handling code here:
        String ml=cbxMaKH.getSelectedItem().toString();
        try {
            showDataKH(ml);
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxMaKHItemStateChanged

    private void cbxTenHangPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxTenHangPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cbxTenHangPopupMenuWillBecomeInvisible

    private void cbxTenHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTenHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTenHangActionPerformed
private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    private void tfSLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSLKeyReleased
        tfSL.setText(cutChar(tfSL.getText()));

    }//GEN-LAST:event_tfSLKeyReleased

    private void cbxNCCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNCCItemStateChanged
        // TODO add your handling code here:
        String ml=cbxNCC.getSelectedItem().toString();

        try {

            ShowTenSPTheoNCC(ml);
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
    }//GEN-LAST:event_cbxNCCItemStateChanged

    private void cbxNCCPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxNCCPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cbxNCCPopupMenuWillBecomeInvisible

    private void cbxNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxNCCMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxNCCMouseClicked

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        try {
            Refresh();
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        // TODO add your handling code here:
        try {
            Refresh();
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String MHD = tfMaHD.getText();
        qldh.UpdateTongTienDonHang(TongTien1DoaDon, MHD);
        try {
            Refresh();
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnThemDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDonHangActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo 1 hóa đơn bán hàng mới hay không?", "Thông Báo", 2);
        String mahdString = tfMaHD.getText();
        String makh = cbxMaKH.getSelectedItem().toString();
        if (Click == JOptionPane.YES_OPTION) {
            if (mahdString.equals("")) {
                JOptionPane.showMessageDialog(null, "Ma Hóa Đơn Không Được Để Trống", "Thông Báo", 3);
            }
            else{
                if (tbHoaDon.getRowCount()>0) {
                    JOptionPane.showMessageDialog(null, "Đã Có Mã Hóa Đơn Này", "Thông Báo", 3);
                }
                else {
                    tfSL.setEnabled(true);
                    tfMaHD.setEnabled(false);
                    btnThemDonHang.setEnabled(false);
                    qldh.ThemHD(mahdString, manvString, makh);
                    lblTongTien.setText("0 VND");
                    btnSuaDonHang.setEnabled(true);
                    btnSave.setEnabled(true);
                    cbxMaKH.setEnabled(false);

                }

            }
        }
    }//GEN-LAST:event_btnThemDonHangActionPerformed

    private void btnSuaDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDonHangActionPerformed
        String MHD = tfMaHD.getText();
        String NCC = cbxNCC.getSelectedItem().toString();
        String tenSP = cbxTenHang.getSelectedItem().toString();
        String MaHH = null;
        try {
            MaHH = qldh.TimMaHH(NCC, tenSP);
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        int SL = Integer.parseInt(tfSL.getText());
        int SLCL = 10000;
        try {
            SLCL = qlkho.CapNhatSLDonHangTrongKho(tenSP, NCC);
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int slhh = qlkho.CapNhatSLDonHangTrongKho(tenSP, NCC);
            System.out.println(""+slhh);
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (SLCL < SL) {
            JOptionPane.showMessageDialog(null, "So Luong Con Lai Khong Du");
        } else {
            qlkho.CapNhatSLDonHang(SL, MaHH);
            System.out.println("them");
            qldh.Them(MaHH, MHD, SL);
            System.out.println("sau them");
            clearData(); //goi ham xoa du lieu tron tableModel
            try {
                showDatatheoMaHD(MHD);
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                
                int a = Integer.parseInt(qldh.TongTienDonHang(MHD));
            lblTongTien.setText(""+formatter.format(a)+"VND");
//                lblTongTien.setText(qldh.TongTienDonHang(MHD));
                TongTien1DoaDon = Long.parseLong(qldh.TongTienDonHang(MHD));
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnSuaDonHangActionPerformed

    private void btnXoaDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDonHangActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String MHD = tfMaHD.getText();
            int row = this.tbHoaDon.getSelectedRow();
            String mahdString = (this.tbHoaDon.getModel().getValueAt(row, 0)).toString();
            String Sl = (this.tbHoaDon.getModel().getValueAt(row, 5)).toString();
            String Makh = (this.tbHoaDon.getModel().getValueAt(row, 6)).toString();
            String MahhString = (this.tbHoaDon.getModel().getValueAt(row, 4)).toString();
            int id = 0;
            try {
                id = qldh.TimID(mahdString, MahhString, Sl);
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            qldh.Xoa(id);
            qldh.luulsxoadonhang(manvString);
            clearData(); //goi ham xoa du lieu tron tableModel
            try {
                showDatatheoMaHD(MHD);
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lblTongTien.setText(qldh.TongTienDonHang(MHD));
                TongTien1DoaDon = Long.parseLong(qldh.TongTienDonHang(MHD));
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXoaDonHangActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked

        btnXoaDonHang.setEnabled(true);
        //        int Click = tbHoaDon.getSelectedRow();
        //        TableModel model = tbHoaDon.getModel();
        //        tfMaHD.setText(model.getValueAt(Click, 0).toString());
        //        cbxMaKH.addItem(model.getValueAt(Click, 7).toString());
        //        tfSL.setText(model.getValueAt(Click, 6).toString());
        int row =this.tbHoaDon.getSelectedRow();
        String mahdString=(this.tbHoaDon.getModel().getValueAt(row,0)).toString();
        String Sl=(this.tbHoaDon.getModel().getValueAt(row,5)).toString();
        String Makh=(this.tbHoaDon.getModel().getValueAt(row,6)).toString();
        String MahhString=(this.tbHoaDon.getModel().getValueAt(row,4)).toString();
        String tensp = null;
        try {
            tensp = qldh.TimTenHHTheoMaHH(MahhString);
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        String TenNCC=null;
        try {
            TenNCC = qldh.TimTenNCCTheoMaHH(MahhString);
        } catch (SQLException ex) {
            Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfMaHD.setText(mahdString);
        tfSL.setText(Sl);
        cbxMaKH.setSelectedItem(Makh);
        cbxTenHang.setSelectedItem(tensp);
        cbxNCC.setSelectedItem(TenNCC);

    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tfMaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMaHDKeyReleased
        // TODO add your handling code here:
        String MaHD = tfMaHD.getText();
        if(MaHD.equals("")){
            try {
                showData();
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                showDatatheoMaHD(MaHD);
            } catch (SQLException ex) {
                Logger.getLogger(frmBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_tfMaHDKeyReleased

    private void btnThemDonHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDonHang1ActionPerformed
        // TODO add your handling code here:
        if (tbHoaDon.getRowCount()<=0){
            return;
        }
        try {
            Hashtable map = new Hashtable();
            JasperReport rpt = JasperCompileManager.compileReport("src\\main\\java\\InterFace\\Blank_Letter.jrxml");
            map.put("sMaHD", tfMaHD.getText());
            
            String user = "sa";
            String pass = "123456";
            String dbUrl = "jdbc:sqlserver://DESKTOP-7G38HSS\\SQLEXPRESS:1433;databaseName=CuaHangQuanAo;encrypt=false";
            Connection conn = DriverManager.getConnection(dbUrl,user,pass);
            JasperPrint p = JasperFillManager.fillReport(rpt, map,conn);
            JasperViewer.viewReport(p,false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThemDonHang1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSuaDonHang;
    private javax.swing.JButton btnThemDonHang;
    private javax.swing.JButton btnThemDonHang1;
    private javax.swing.JButton btnXoaDonHang;
    private javax.swing.JComboBox<String> cbxMaKH;
    private javax.swing.JComboBox<String> cbxNCC;
    private javax.swing.JComboBox<String> cbxTenHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNV;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTextField tfMaHD;
    private javax.swing.JTextField tfSL;
    private javax.swing.JTextField tfttkh;
    // End of variables declaration//GEN-END:variables
}
