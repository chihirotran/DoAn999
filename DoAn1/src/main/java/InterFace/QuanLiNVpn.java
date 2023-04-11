/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package InterFace;
import Database.*;
import Model.KhachHang;
import Model.MatHang;
import Model.NhanVien;
import Model.TaiKhoan;
import Process.QuanLyKhoHang;
import Process.QuanLyNCC;
import Process.QuanLyNhanVien;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author chihi
 */
public class QuanLiNVpn extends javax.swing.JPanel {
     private final  DefaultTableModel tbModel = new DefaultTableModel();
    private final QuanLyNhanVien qlnv = new QuanLyNhanVien();
    private final SimpleDateFormat ngaythangnam = new SimpleDateFormat("MM-dd-yyyy");
    

     public List<NhanVien> getdata() throws SQLException{
        List<NhanVien> lst = qlnv.getAll();
        return lst;
    }
     public void clearData(){
        while(tbModel.getRowCount()>0){
            tbModel.removeRow(0);
        }
    }
     public void showData() throws SQLException{
        clearData();
        List<NhanVien> data = getdata();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        for(int i=0; i <data.size();i++){
            int a = Integer.parseInt(""+data.get(i).getLuongLong());
            String[] row11 = {
                data.get(i).getMaNV(),
                data.get(i).getQuyen(),
                data.get(i).getTenNV(),
                ""+data.get(i).getHoVaTen(),
                ""+data.get(i).getGioiTinh(),
                data.get(i).getSDT(),
                ""+data.get(i).getNgaySinh(),
                data.get(i).getDiaChiString(),
                data.get(i).getEmail(),
                ""+formatter.format(a),
            };
         
        tbModel.addRow(row11);
        }
    }
     public QuanLiNVpn() throws SQLException {
        
        initComponents();
        
        String[] colName = {"Ma NV","Quyen","TenNV","Ho Va Ten","Gioi Tinh","SDT","NgaySinh","DiaChi","Email","Luong"};
        tbModel.setColumnIdentifiers(colName);
        tableNV.setModel(tbModel);
        showData();
       
        
        
        
        Disabled();
        
    }
    
    
    private void Enabled(){
        tfMaNV.setEnabled(true);
        tfHoten.setEnabled(true);
        tfTenNV.setEnabled(true);
        jDateChooser1.setEnabled(true);
        tfLuong.setEnabled(true);
        
        tfEmail.setEnabled(true);
        tfSDT.setEnabled(true);
        
        tfDiachi.setEnabled(true);
        rbNu.setEnabled(true);
        rbNam.setEnabled(true);
    }
    private void EnabledNotTK(){
        tfMaNV.setEnabled(true);
        tfHoten.setEnabled(true);
        tfTenNV.setEnabled(true);
        jDateChooser1.setEnabled(true);
        tfLuong.setEnabled(true);
        
        tfEmail.setEnabled(true);
        tfSDT.setEnabled(true);
        
        
        tfDiachi.setEnabled(true);
        rbNu.setEnabled(true);
        rbNam.setEnabled(true);
    }
    
    private void Disabled(){
        tfMaNV.setEnabled(false);
        tfHoten.setEnabled(false);
        tfTenNV.setEnabled(false);
        jDateChooser1.setEnabled(false);
        tfLuong.setEnabled(false);
        
        tfEmail.setEnabled(false);
        tfSDT.setEnabled(false);
        
        tfDiachi.setEnabled(false);
        rbNu.setEnabled(false);
        rbNam.setEnabled(false);
    }
    
    private void reset(){
        
     Disabled();
    tfDiachi.setText("");
    tfEmail.setText("");
    tfFind.setText("");
    tfHoten.setText("");
    tfLuong.setText("");
    tfMaNV.setText("");
    tfSDT.setText("");
    jDateChooser1.cleanup();
    
    tfTenNV.setText("");
    lbTrangthai.setText("Trạng Thái");
    btnAdd.setEnabled(true);
    btnSave.setEnabled(false);
    btnCancel.setEnabled(false);
    btnDel.setEnabled(false);
    btnEdit.setEnabled(false);
    }

    private void checkGT(String GT){
        if(GT.equals("Nam"))
            rbNam.setSelected(true);
        else
            rbNu.setSelected(true);
    }
    
    private boolean checkNull(){
        if(tfMaNV.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập mã nhân viên!");
            return false;
        }
        else
        if(tfHoten.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập họ tên nhân viên!");
            return false;
        }
        else
        if(rbNam.isSelected()==false && rbNu.isSelected()==false){
            lbTrangthai.setText("Bạn chưa chọn giới tính!");
            return false;
        }

        else   
        if(tfSDT.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập số điện thoại!");
            return false;
        }
        else   
        if(tfDiachi.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập địa chỉ!");
            return false;
        }
        else {
            lbTrangthai.setText("Nhập lại mật khẩu không đúng!");
            return false;
        }
    }
    
    private String gioiTinh(){
        if(rbNam.isSelected())
            return rbNam.getText();
        else
            return rbNu.getText();
    }
    
    private void addNV(){
        {
            Disabled();
            lbTrangthai.setText("Thêm nhân viên thành công!");
        }
    }
    
    private void changeNV(){
        if(checkNull()){
            int click=tableNV.getSelectedRow();
            TableModel model=tableNV.getModel();
        
           
            Disabled();
            lbTrangthai.setText("Sửa thông tin nhân viên thành công!");
        }
    }
    
    
    
    
    
    
    
    private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    public boolean CoMaNVChua(String maNV){
    TableModel model=tableNV.getModel();
    
    for(int i = 0;i<model.getRowCount();i++){
        String makh01=model.getValueAt(i, 0).toString();
        makh01=makh01.trim();
        
        if (maNV.equals(makh01)) {
            return true;
        }
    }
    return false;
}
public final void TimKiemTheoTen(String ml) throws SQLException{
        clearData();
        List<NhanVien> data = qlnv.TimTheoTen(ml);
        
        for(int i=0; i <data.size();i++){
            
            String[] row11 = {
                data.get(i).getMaNV(),
                data.get(i).getQuyen(),
                data.get(i).getTenNV(),
                ""+data.get(i).getHoVaTen(),
                ""+data.get(i).getGioiTinh(),
                data.get(i).getSDT(),
                ""+data.get(i).getNgaySinh(),
                data.get(i).getDiaChiString(),
                data.get(i).getEmail(),
                ""+data.get(i).getLuongLong()
            };
        tbModel.addRow(row11);
        }
    }
    /**
     * Creates new form QuanLiNVpn
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbTrangthai = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        Export = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        tfFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbMaNV = new javax.swing.JLabel();
        lbHoten = new javax.swing.JLabel();
        lbGioitinh = new javax.swing.JLabel();
        lbNgaysinh = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbDiachi = new javax.swing.JLabel();
        tfMaNV = new javax.swing.JTextField();
        tfHoten = new javax.swing.JTextField();
        tfSDT = new javax.swing.JTextField();
        tfDiachi = new javax.swing.JTextField();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lbDiachi1 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbDiachi2 = new javax.swing.JLabel();
        tfLuong = new javax.swing.JTextField();
        lbMaNV1 = new javax.swing.JLabel();
        tfTenNV = new javax.swing.JTextField();
        cbxquyen = new javax.swing.JComboBox<>();
        lbDiachi3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        lbQLNV = new javax.swing.JLabel();

        lbTrangthai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng Thái");

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user (1).png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Change.png"))); // NOI18N
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        btnDel.setEnabled(false);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk (1).png"))); // NOI18N
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/x-button.png"))); // NOI18N
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        Export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportActionPerformed(evt);
            }
        });

        jButton1.setText("Them Tai Khoan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Export, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit)
                    .addComponent(Export, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDel)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        tfFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFindActionPerformed(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (1).png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        lbMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV.setText("Mã Nhân viên:");

        lbHoten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHoten.setText("Họ và Tên:");

        lbGioitinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGioitinh.setText("Giới tính:");

        lbNgaysinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNgaysinh.setText("Ngày sinh:");

        lbSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSDT.setText("Số điện thoại:");

        lbDiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi.setText("Địa chỉ:");

        tfMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfHoten.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tfSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSDTKeyReleased(evt);
            }
        });

        tfDiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rbNam);
        rbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbNu.setText("Nữ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Thông tin");

        lbDiachi1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi1.setText("Email");

        tfEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbDiachi2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi2.setText("Lương");

        tfLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLuongKeyReleased(evt);
            }
        });

        lbMaNV1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV1.setText("Tên NV");

        tfTenNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbxquyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "quanli    ", "thukho    ", "thungan   " }));
        cbxquyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxquyenActionPerformed(evt);
            }
        });

        lbDiachi3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi3.setText("Quyền");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(211, 211, 211))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbMaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMaNV)
                        .addGap(92, 92, 92)
                        .addComponent(lbGioitinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(rbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(lbMaNV1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfTenNV))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lbHoten)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfHoten))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbNgaysinh)
                                    .addComponent(lbSDT)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbDiachi2)
                                        .addComponent(lbDiachi)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfDiachi)
                                            .addComponent(tfSDT)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lbDiachi1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfLuong)
                                    .addComponent(tfEmail))))
                        .addGap(92, 92, 92)
                        .addComponent(lbDiachi3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxquyen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaNV)
                    .addComponent(tfMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGioitinh)
                    .addComponent(rbNu)
                    .addComponent(rbNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaNV1)
                    .addComponent(tfTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoten)
                    .addComponent(tfHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDiachi3)
                    .addComponent(cbxquyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNgaysinh)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDiachi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDiachi1)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDiachi2)
                    .addComponent(tfLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        tableNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNV);

        lbQLNV.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        lbQLNV.setText("Quản lý nhân viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(286, 286, 286))))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(436, 436, 436)
                        .addComponent(lbQLNV))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbQLNV)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        Enabled();
        btnAdd.setEnabled(false);
        btnSave.setEnabled(true);
        btnAdd.setEnabled(true);
        btnCancel.setEnabled(true);
        btnDel.setEnabled(false);
        btnEdit.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        String MaNV = tfMaNV.getText();
        String TenNV = tfTenNV.getText();
        String HoVaTen = tfHoten.getText();
        String day = ngaythangnam.format(jDateChooser1.getDate());
        String SDT = tfSDT.getText();
        String DiaChi = tfDiachi.getText();
        String Email = tfEmail.getText();
        Long Luong = Long.parseLong(tfLuong.getText());
        String GioiTinh = "";
        if (rbNu.isSelected() == true) {
            GioiTinh = "Nữ";
        }
        if (rbNam.isSelected() == true) {
            GioiTinh = "Nam";
        }
        if (GioiTinh.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui Long Chon Gioi Tinh");
        } else {
            String Quyen = cbxquyen.getSelectedItem().toString();
            
                NhanVien obj = new NhanVien(TenNV, HoVaTen, GioiTinh, SDT, day, DiaChi, Email, Luong,Quyen, MaNV);
                qlnv.Sua(obj);
                try {
                    showData();
                    //                    tfDiachi.setText("");
                    //                    tfEmail.setText("");
                    //                    tfFind.setText("");
                    //                    tfHoten.setText("");
                    //                    tfLuong.setText("");
                    //                    tfMaNV.setText("");
                    //                    tfSDT.setText("");
                    //                    tfTaikhoan.setText("");
                    //                    tfTenNV.setText("");
                    //                    Disabled();
                    //                    btnAdd.setEnabled(true);
                    //                    btnSave.setEnabled(false);
                    //                    btnCancel.setEnabled(false);
                    //                    btnDel.setEnabled(false);
                    //                    btnEdit.setEnabled(false);
                    lbTrangthai.setText("Sửa Thành Công");
                    reset();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này hay không?", "Thông báo", 2);
        if (click == JOptionPane.YES_OPTION) {
            Disabled();

            lbTrangthai.setText("Trạng Thái");
            btnAdd.setEnabled(true);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
            btnDel.setEnabled(false);
            btnEdit.setEnabled(false);

            String manv = tfMaNV.getText();
            qlnv.XoaTK(manv);
            qlnv.Xoa(manv);
            lbTrangthai.setText("Xoa Thanh Cong");
            try {
                showData();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        reset();
        tfDiachi.setText("");
        tfEmail.setText("");
        tfFind.setText("");
        tfHoten.setText("");
        tfLuong.setText("");
        tfMaNV.setText("");
        tfSDT.setText("");
        
        tfTenNV.setText("");
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        String MaNV = tfMaNV.getText().trim();
        String TenNV = tfTenNV.getText();
        String HoVaTen = tfHoten.getText();
        String day = ngaythangnam.format(jDateChooser1.getDate());
        
        System.out.println("+"+day);
        String SDT = tfSDT.getText();
        String DiaChi = tfDiachi.getText();
        String Email = tfEmail.getText();
        Long Luong = Long.parseLong(tfLuong.getText());
        String GioiTinh = "";
        if (rbNu.isSelected() == true) {
            GioiTinh = "Nữ";
        }
        if (rbNam.isSelected() == true) {
            GioiTinh = "Nam";
        }
        if (GioiTinh.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui Long Chon Gioi Tinh");
        } else {
            if(MaNV.equals("")){
                JOptionPane.showMessageDialog(null, "Ma Khong Duoc De Trong");
            }else{
                    String Quyen = cbxquyen.getSelectedItem().toString();
                        if(CoMaNVChua(MaNV)==true){
                            JOptionPane.showMessageDialog(null,"Da Co Ma Nhan Vien","Thong Bao",1);
                        }else{
                            NhanVien obj = new NhanVien(TenNV, HoVaTen, GioiTinh, SDT, day, DiaChi, Email, Luong,Quyen, MaNV);
                            qlnv.ThemTTNV(obj);
                            System.out.println("ThemTTNV");
                            
                            lbTrangthai.setText("Thêm Thành Công");
                            try {
                                showData();
                                reset();
                                Disabled();
                            } catch (SQLException ex) {
                                Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                
            }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        reset();
        Disabled();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFindActionPerformed
        // TODO add your handling code here:
        tfFind.requestFocus();
        btnFindActionPerformed(evt);
    }//GEN-LAST:event_tfFindActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String tkString=tfFind.getText();
        try {
            TimKiemTheoTen(tkString);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void tfSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSDTKeyReleased
        tfSDT.setText(cutChar(tfSDT.getText()));

        if(tfSDT.getText().length()==11 || tfSDT.getText().length()==10 ){

            btnSave.setEnabled(true);
            lbTrangthai.setText("Số điện thoại đã hợp lệ!!");
        }
        else
        if(tfSDT.getText().length()>11 || tfSDT.getText().length()<10){
            btnSave.setEnabled(false);
            lbTrangthai.setText("Số điện thoại không được nhỏ hơn 10 số hoặc vượt quá 11 số!!");
        }
    }//GEN-LAST:event_tfSDTKeyReleased

    private void tfLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLuongKeyReleased
        // TODO add your handling code here:
        tfLuong.setText(cutChar(tfLuong.getText()));
    }//GEN-LAST:event_tfLuongKeyReleased

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        int click=tableNV.getSelectedRow();
        TableModel model=tableNV.getModel();

        tfMaNV.setText(model.getValueAt(click, 0).toString());
        String quyen =model.getValueAt(click, 1).toString();
        cbxquyen.setSelectedItem(quyen);
        
        tfTenNV.setText(model.getValueAt(click, 2).toString());
        tfHoten.setText(model.getValueAt(click, 3).toString());
        String GT=model.getValueAt(click, 4).toString();
        if(GT.equals("Nam       ")){
            rbNam.setSelected(true);
        }
        else{
            rbNu.setSelected(true);
        }
        tfSDT.setText(model.getValueAt(click, 5).toString());
        String ns = model.getValueAt(click, 6).toString();
        System.out.println(""+ns);
        Date ngays = Date.valueOf(ns);
        jDateChooser1.setDate(ngays);
        tfDiachi.setText(model.getValueAt(click, 7).toString());
        tfEmail.setText(model.getValueAt(click, 8).toString());
        tfLuong.setText(model.getValueAt(click, 9).toString());

        EnabledNotTK();
        btnEdit.setEnabled(true);
        btnDel.setEnabled(true);

    }//GEN-LAST:event_tableNVMouseClicked
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
    private void ExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportActionPerformed

        // TODO add your handling code here:
        try {
                        exportExcel(tableNV);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_ExportActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         TaiKhoan2 form2 = null;
        try {
            form2 = new TaiKhoan2();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        form2.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxquyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxquyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxquyenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Export;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxquyen;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDiachi;
    private javax.swing.JLabel lbDiachi1;
    private javax.swing.JLabel lbDiachi2;
    private javax.swing.JLabel lbDiachi3;
    private javax.swing.JLabel lbGioitinh;
    private javax.swing.JLabel lbHoten;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMaNV1;
    private javax.swing.JLabel lbNgaysinh;
    private javax.swing.JLabel lbQLNV;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField tfDiachi;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfHoten;
    private javax.swing.JTextField tfLuong;
    private javax.swing.JTextField tfMaNV;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTenNV;
    // End of variables declaration//GEN-END:variables
}
