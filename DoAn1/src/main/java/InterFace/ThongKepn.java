/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package InterFace;
import Model.HoaDon;
import Model.KHACHHANG1;
import Model.MatHang;
import Process.ThongKe;
import com.toedter.calendar.JDayChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
/**
 *
 * @author chihi
 */
public class ThongKepn extends javax.swing.JPanel {

    /**
     * Creates new form ThongKepn
     */
    private final  DefaultTableModel tbModel = new DefaultTableModel();
    private final  DefaultTableModel tbModel1 = new DefaultTableModel();
    private final  DefaultTableModel tbModel2= new DefaultTableModel();
    private final SimpleDateFormat ngaythangnam = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat NgayDateFormat = new SimpleDateFormat("dd");
    private final SimpleDateFormat ThangNam = new SimpleDateFormat("MM-yyyy");
    private final SimpleDateFormat ThangDateFormat = new SimpleDateFormat("MM");
    private final SimpleDateFormat Nam = new SimpleDateFormat("yyyy");
    private final ThongKe tk = new ThongKe();
    /**
     * Creates new form frmThongkepn
     */
    public List<HoaDon> getdata() throws SQLException{
        List<HoaDon> lst = tk.getAll();
        return lst;
    }
    public List<KHACHHANG1> getdataKH() throws SQLException{
        List<KHACHHANG1> lst = tk.getAllKH();
        return lst;
    }
    public List<MatHang> getdataSP() throws SQLException{
        List<MatHang> lst = tk.getAllSP();
        return lst;
    }
    public List<HoaDon> getdataNgayThangNam(String NgayString,String ThangString,String Nam) throws SQLException{
        List<HoaDon> lst = tk.TimTheoNgayThangNam(NgayString, ThangString, Nam);
        return lst;
    }
    public List<HoaDon> getdataThangNam(String ThangString,String Nam) throws SQLException{
        List<HoaDon> lst = tk.TimTheoThangNam(ThangString, Nam);
        return lst;
    }
    public List<HoaDon> getdataNam(String Nam) throws SQLException{
        List<HoaDon> lst = tk.TimTheoNam(Nam);
        return lst;
    }
    
    public ThongKepn() throws SQLException {
        initComponents();
         
        String[] colName = {"Ma Hd","Ngay Tao Hoa Don","Tong So Tien","Ma NV","MaKH"};
        tbModel.setColumnIdentifiers(colName);
        tableThongke.setModel(tbModel);
        showData();
        
        
    }
    public boolean KtNut(){
        if(radXemnam.isSelected()||radXemngay.isSelected()||radXemthang.isSelected()){
            return true;
        }else{
            return false;
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
        long TongDoanhThu = (long) 0.0;
        for(int i=0; i <data.size();i++){
           int a = Integer.parseInt(""+data.get(i).getTongTienLong());
           
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+formatter.format(a),
                ""+data.get(i).getMaNV(),
                ""+data.get(i).getMaKH()
            };
        TongDoanhThu =TongDoanhThu+data.get(i).getTongTienLong();
        tbModel.addRow(row11);
        }
        lblSoHoaDon.setText(""+data.size());
//        lblTongDoanhThu.setText(""+TongDoanhThu+"VND");

        lblTongDoanhThu.setText(""+formatter.format(TongDoanhThu)+"VND");
    }public void showData2() throws SQLException{
        clearData();
        List<MatHang> data = getdataSP();
//        long TongDoanhThu = (long) 0.0;
        for(int i=0; i <data.size();i++){
           
            String[] row11 = {
                data.get(i).getMaHH(),
                ""+data.get(i).getTenSP(),
                ""+data.get(i).getKieu(),
                ""+data.get(i).getSoLuongConLaiString(),
                ""+data.get(i).getSoLuongDaBan(),
                
            };
//        TongDoanhThu =TongDoanhThu+data.get(i).getTongTienLong();
        tbModel.addRow(row11);
        }
//        lblSoHoaDon2.setText(""+data.size());
//        lblTongDoanhThu2.setText(""+TongDoanhThu+"VND");
    }
    public void showData1() throws SQLException{
        clearData();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<KHACHHANG1> data = getdataKH();
        long TongDoanhThu = (long) 0.0;
        for(int i=0; i <data.size();i++){
           int a = Integer.parseInt(""+data.get(i).getTongSoTienString());
            String[] row11 = {
                data.get(i).getMaKH(),
                ""+data.get(i).getHoTen(),
                ""+data.get(i).getSDT(),
                ""+formatter.format(a),
                
            };
//        TongDoanhThu =TongDoanhThu+data.get(i).getTongTienLong();
        tbModel.addRow(row11);
        }
        lblSoHoaDon.setText(""+data.size());
        lblTongDoanhThu.setText(""+TongDoanhThu+"VND");
    }
    public void showDataNTN(String NgayString,String ThangString,String Nam) throws SQLException{
        clearData();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<HoaDon> data = getdataNgayThangNam(NgayString, ThangString, Nam);
        long TongDoanhThu = (long) 0.0;
        for(int i=0; i <data.size();i++){
           int a = Integer.parseInt(""+data.get(i).getTongTienLong());
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+formatter.format(a),
                ""+data.get(i).getMaNV(),
                ""+data.get(i).getMaKH()
            };
         TongDoanhThu =TongDoanhThu+data.get(i).getTongTienLong();
        tbModel.addRow(row11);
        lblSoHoaDon.setText(""+data.size());
        
        lblTongDoanhThu.setText(""+formatter.format(TongDoanhThu)+"VND");
        }
    }
    public void showDataTN(String ThangString,String Nam) throws SQLException{
        clearData();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<HoaDon> data =  getdataThangNam(ThangString, Nam);
        long TongDoanhThu = (long) 0.0;
        for(int i=0; i <data.size();i++){
           int a = Integer.parseInt(""+data.get(i).getTongTienLong());
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+formatter.format(a),
                ""+data.get(i).getMaNV(),
                ""+data.get(i).getMaKH()
            };
         TongDoanhThu =TongDoanhThu+data.get(i).getTongTienLong();
        tbModel.addRow(row11);
        lblSoHoaDon.setText(""+data.size());
        
        lblTongDoanhThu.setText(""+formatter.format(TongDoanhThu)+"VND");
        }
    }
    public void showDataN(String Nam) throws SQLException{
        clearData();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<HoaDon> data = getdataNam(Nam);
        long TongDoanhThu = (long) 0.0;
        for(int i=0; i <data.size();i++){
           int a = Integer.parseInt(""+data.get(i).getTongTienLong());
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+formatter.format(a),
                ""+data.get(i).getMaNV(),
                ""+data.get(i).getMaKH()
            };
         TongDoanhThu =TongDoanhThu+data.get(i).getTongTienLong();
        tbModel.addRow(row11);
        lblSoHoaDon.setText(""+data.size());
        
        lblTongDoanhThu.setText(""+formatter.format(TongDoanhThu)+"VND");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongke = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        radXemngay = new javax.swing.JRadioButton();
        radXemthang = new javax.swing.JRadioButton();
        radXemnam = new javax.swing.JRadioButton();
        btnFind = new javax.swing.JButton();
        NgayThang = new com.toedter.calendar.JDateChooser();
        lbTrangthai = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblSoHoaDon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        btnCancel3 = new javax.swing.JButton();
        btnCancel4 = new javax.swing.JButton();
        KhachHang = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableThongke1 = new javax.swing.JTable();
        btnCancel2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableThongke2 = new javax.swing.JTable();
        btnCancel1 = new javax.swing.JButton();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        tableThongke.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableThongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MaHD", "Ngày bán", "Tổng tiền hóa đơn", "NV Ban", "MaKH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableThongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableThongkeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableThongke);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        buttonGroup1.add(radXemngay);
        radXemngay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radXemngay.setText("Xem theo ngày");
        radXemngay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radXemngayItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radXemthang);
        radXemthang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radXemthang.setText("Xem theo tháng");
        radXemthang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radXemthangItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radXemnam);
        radXemnam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radXemnam.setText("Xem theo năm");
        radXemnam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radXemnamItemStateChanged(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(radXemngay)
                                .addGap(29, 29, 29)
                                .addComponent(radXemthang)
                                .addGap(27, 27, 27)
                                .addComponent(radXemnam))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(NgayThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radXemngay)
                    .addComponent(radXemthang)
                    .addComponent(radXemnam))
                .addGap(18, 18, 18)
                .addComponent(NgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnFind)
                .addContainerGap())
        );

        lbTrangthai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng thái");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Tổng Số Hóa Đơn Bán Ra:");

        lblSoHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSoHoaDon.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tổng Tiền Thu Về:");

        lblTongDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTongDoanhThu.setText("0 VND");

        btnCancel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Refresh-icon.png"))); // NOI18N
        btnCancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel3ActionPerformed(evt);
            }
        });

        btnCancel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print Sale.png"))); // NOI18N
        btnCancel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancel3, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(btnCancel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSoHoaDon)
                    .addComponent(jLabel4)
                    .addComponent(lblTongDoanhThu))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel1);

        tableThongke1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableThongke1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MaKH", "Họ Tên", "SDT", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableThongke1);

        btnCancel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print Sale.png"))); // NOI18N
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(536, 536, 536)
                .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel2)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout KhachHangLayout = new javax.swing.GroupLayout(KhachHang);
        KhachHang.setLayout(KhachHangLayout);
        KhachHangLayout.setHorizontalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        KhachHangLayout.setVerticalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khách Hàng", KhachHang);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableThongke2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableThongke2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MaHH", "Tên SP", "Kiểu", "Số Lượng Còn Lại", "Số Lượng Đã Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableThongke2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1250, -1));

        btnCancel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print Sale.png"))); // NOI18N
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnCancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 480, 120, 40));

        jTabbedPane1.addTab("Sản Phẩm", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        int index = jTabbedPane1.getSelectedIndex();
        if(index == 0){
            String[] colName = {"Ma Hd","Ngay Tao Hoa Don","Tong So Tien","Ma NV","MaKH"};

            tbModel.setColumnIdentifiers(colName);
            tableThongke.setModel(tbModel);
            try {
                showData();
            } catch (SQLException ex) {
                Logger.getLogger(ThongKepn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(index==1){
            String[] colName = {"MaKH","Ho Ten","SDT","Tong Tien"};

            tbModel.setColumnIdentifiers(colName);
            tableThongke1.setModel(tbModel);
            try {
                showData1();
            } catch (SQLException ex) {
                Logger.getLogger(ThongKepn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(index == 2){
            System.err.println("22222");
            String[] colName = {"Ma HH","Tên Sản Phẩm","Kiểu","Số Lượng Còn Lại","Số Lượng Đã Bán"};

            tbModel.setColumnIdentifiers(colName);
            tableThongke2.setModel(tbModel);
            try {
                showData2();
            } catch (SQLException ex) {
                Logger.getLogger(ThongKepn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if(NgayThang.equals(evt)){
            System.out.println("null");
        }
        String day = NgayDateFormat.format(NgayThang.getDate());
        String month = ThangDateFormat.format(NgayThang.getDate());
        String year = Nam.format(NgayThang.getDate());

        if (KtNut() == true) {
            if (radXemngay.isSelected() == true) {
                try {
                    showDataNTN(day, month, year);
                } catch (SQLException ex) {
                    Logger.getLogger(frmThongkepn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (radXemthang.isSelected() == true) {
                try {
                    showDataTN(month, year);
                } catch (SQLException ex) {
                    Logger.getLogger(frmThongkepn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (radXemnam.isSelected() == true) {
                try {
                    showDataN(year);
                } catch (SQLException ex) {
                    Logger.getLogger(frmThongkepn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "vui long chon kieu", "thong bao", 1);
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void radXemnamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radXemnamItemStateChanged

    }//GEN-LAST:event_radXemnamItemStateChanged

    private void radXemthangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radXemthangItemStateChanged

    }//GEN-LAST:event_radXemthangItemStateChanged

    private void radXemngayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radXemngayItemStateChanged

    }//GEN-LAST:event_radXemngayItemStateChanged
public void exportExcel(JTable table) {
 JFileChooser chooser = new JFileChooser();
 int i = chooser.showSaveDialog(chooser);
 if (i == JFileChooser.APPROVE_OPTION) {
  File file = chooser.getSelectedFile();
  try {
   FileWriter out = new FileWriter(file + ".xls");
   BufferedWriter bwrite = new BufferedWriter(out);
   DefaultTableModel model = (DefaultTableModel) table.getModel();
   // ten Cot
   for (int j = 0; j < table.getColumnCount(); j++) {
    bwrite.write(model.getColumnName(j) + "\t");
   }
   bwrite.write("\n");
   // Lay du lieu dong
   for (int j = 0; j < table.getRowCount(); j++) {
    for (int k = 0; k < table.getColumnCount(); k++) {
     bwrite.write(model.getValueAt(j, k) + "\t");
    }
    bwrite.write("\n");
   }
   bwrite.close();
   JOptionPane.showMessageDialog(null, "Lưu file thành công!");
  } catch (Exception e2) {
   JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
  }
 }
}

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        try {
            exportExcel(tableThongke2);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
        // TODO add your handling code here:
        try {
            exportExcel(tableThongke1);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancel2ActionPerformed

    private void btnCancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel3ActionPerformed
        // TODO add your handling code here:
        try {
            showData();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancel3ActionPerformed

    private void btnCancel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel4ActionPerformed
        // TODO add your handling code here:
        try {
            exportExcel(tableThongke);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancel4ActionPerformed

    private void tableThongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableThongkeMouseClicked
        // TODO add your handling code here:
        
        //        int Click = tbHoaDon.getSelectedRow();
        //        TableModel model = tbHoaDon.getModel();
        //        tfMaHD.setText(model.getValueAt(Click, 0).toString());
        //        cbxMaKH.addItem(model.getValueAt(Click, 7).toString());
        //        tfSL.setText(model.getValueAt(Click, 6).toString());
        int row =this.tableThongke.getSelectedRow();
        String mahdString=(this.tableThongke.getModel().getValueAt(row,0)).toString();
        frmBanHang form2 = null;
        try {
            form2 = new frmBanHang(mahdString);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        form2.setVisible(true);
    }//GEN-LAST:event_tableThongkeMouseClicked





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel KhachHang;
    private com.toedter.calendar.JDateChooser NgayThang;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnCancel3;
    private javax.swing.JButton btnCancel4;
    private javax.swing.JButton btnFind;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JLabel lblSoHoaDon;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JRadioButton radXemnam;
    private javax.swing.JRadioButton radXemngay;
    private javax.swing.JRadioButton radXemthang;
    private javax.swing.JTable tableThongke;
    private javax.swing.JTable tableThongke1;
    private javax.swing.JTable tableThongke2;
    // End of variables declaration//GEN-END:variables
}
