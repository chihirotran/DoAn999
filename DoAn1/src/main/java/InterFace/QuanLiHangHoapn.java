/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package InterFace;
import Database.*;
import Model.Kieu;
import Model.MatHang;
import Model.NCC;
import Process.QuanLyKhoHang;
import Process.QuanLyNCC;
import Process.kieu1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class QuanLiHangHoapn extends javax.swing.JPanel {

    /**
     * Creates new form QuanLiHangHoapn
     */
    private final DefaultTableModel tbModel = new DefaultTableModel();
    private final QuanLyNCC lsp = new QuanLyNCC();
    private final kieu1 lsp1 = new kieu1();
    private final QuanLyKhoHang qlkh = new QuanLyKhoHang();

    public List<NCC> getdatamathang() throws SQLException {
        List<NCC> lst = lsp.getAll();
        return lst;
    }
    public List<Kieu> getdatamaKieu() throws SQLException {
        List<Kieu> lst = lsp1.getAll();
        return lst;
    }


    public List<MatHang> getdata() throws SQLException {
        List<MatHang> lst = qlkh.getAll();
        return lst;
    }
    public JFrame jframe;
    
    public boolean CoMaHHChua(String maNV){
    TableModel model=tbHangHoa.getModel();
    
    for(int i = 0;i<model.getRowCount();i++){
        String makh01=model.getValueAt(i, 0).toString();
        makh01=makh01.trim();
        
        if (maNV.equals(makh01)) {
            return true;
        }
    }
    return false;
}
      public final void TimKiemTheoTen(String ml) throws SQLException {
        clearData();
        List<MatHang> data = qlkh.TimTheoTen(ml);

        for (int i = 0; i < data.size(); i++) {

            String[] row11 = {
                data.get(i).getMaHH(),
                data.get(i).getTenSP(),
                data.get(i).getNCC(),
                "" + data.get(i).getGiaNhap(),
                "" + data.get(i).getGiaBan(),
                data.get(i).getKieu(),
                "" + data.get(i).getSL()
            };
            tbModel.addRow(row11);
        }
    }

    public final void ShowDataCombo() throws SQLException {
        List<NCC> data = getdatamathang();
        for (int i = 0; i < data.size(); i++) {
            //Them du lieu vao Combobox
            cbxNCC.addItem(data.get(i).getTenNCC());
        }
    }
    public final void ShowDataCombo1() throws SQLException {
        List<NCC> data = getdatamathang();
        for (int i = 0; i < data.size(); i++) {
            //Them du lieu vao Combobox
            cbxNCC1.addItem(data.get(i).getTenNCC());
        }
    }
    public final void ShowDataCombo2() throws SQLException {
        List<Kieu> data = getdatamaKieu();
        for (int i = 0; i < data.size(); i++) {
            //Them du lieu vao Combobox
            cbxNCC2.addItem(data.get(i).getIdKieu());
        }
    }
    public final void ShowDataCombo3() throws SQLException {
        List<Kieu> data = getdatamaKieu();
        for (int i = 0; i < data.size(); i++) {
            //Them du lieu vao Combobox
            cbLoai.addItem(data.get(i).getIdKieu());
        }
    }
    

    public void clearData() {
        while (tbModel.getRowCount() > 0) {
            tbModel.removeRow(0);
        }
    }
    public void showData() throws SQLException {
        clearData();
        List<MatHang> data = getdata();
        for (int i = 0; i < data.size(); i++) {
            
            String[] row11 = {
                data.get(i).getMaHH(),
                data.get(i).getTenSP(),
                data.get(i).getNCC(),
                "" + data.get(i).getGiaNhap(),
                "" + data.get(i).getGiaBan(),
                data.get(i).getKieu(),
                "" + data.get(i).getSL()
            };

            tbModel.addRow(row11);
        }
    }


    private void Enabled() {
        tfMaHH.setEnabled(true);
        tfTenHH.setEnabled(true);
        cbxNCC.setEnabled(true);
        tfGB.setEnabled(true);
        tfGN.setEnabled(true);
        cbLoai.setEnabled(true);
        tfSL.setEnabled(true);
    }

    private void Disabled() {
        tfMaHH.setEnabled(false);
        tfTenHH.setEnabled(false);
        cbxNCC.setEnabled(false);
        tfGB.setEnabled(false);
        tfGN.setEnabled(false);
        cbLoai.setEnabled(false);
        tfSL.setEnabled(false);
    }

    private void reset() {

        tfGB.setText("");
        tfGN.setText("");
        tfMaHH.setText("");
        tfSL.setText("");
        tfTenHH.setText("");
        tfTiemKiem.setText("");
        cbLoai.setSelectedItem("");
        btnAdd.setEnabled(true);
        btnCancel.setEnabled(false);
        btnDel.setEnabled(false);
        btnEdit.setEnabled(false);
        btnFind.setEnabled(false);
        btnSave.setEnabled(false);
        lbTrangthai.setText("Trạng Thái");

    }

    private boolean checkNull() {
        if (tfMaHH.getText().equals("")) {
            lbTrangthai.setText("Bạn chưa nhập mã khách hàng!");
            return false;
        } else if (tfTenHH.getText().equals("")) {
            lbTrangthai.setText("Bạn chưa nhập họ tên khách hàng!");
            return false;
        } else if (tfGN.getText().equals("")) {
            lbTrangthai.setText("Bạn chưa nhập số điện thoại!");
            return false;
        } else if (tfSL.getText().equals("")) {
            lbTrangthai.setText("Bạn chưa nhập địa chỉ!");
            return false;
        }
        return true;
    }

    private void addNV() {
        {
            Disabled();
           
        }
    }

    private void changeNV() {
        if (checkNull()) {
            int click = tbHangHoa.getSelectedRow();
            TableModel model = tbHangHoa.getModel();

            Disabled();
            lbTrangthai.setText("Sửa thông tin nhân viên thành công!");
        }
    }

    private String cutChar(String arry) {
        return arry.replaceAll("\\D+", "");
    }
public QuanLiHangHoapn() throws SQLException {
        
        initComponents();
        
        String[] colName = {"Ma HH", "Ten SP", "Nha Cung Cap", "Gia Nhap", "Gia Ban", "Kieu", "So Luong"};
        tbModel.setColumnIdentifiers(colName);
        tbHangHoa.setModel(tbModel);
        showData();
        ShowDataCombo();
        ShowDataCombo1();
        ShowDataCombo2();
        ShowDataCombo3();
        Disabled();

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbQLNV = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHangHoa = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbMaNV = new javax.swing.JLabel();
        lbHoten = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbDiachi = new javax.swing.JLabel();
        tfMaHH = new javax.swing.JTextField();
        tfTenHH = new javax.swing.JTextField();
        tfGN = new javax.swing.JTextField();
        tfSL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbDiachi1 = new javax.swing.JLabel();
        cbLoai = new javax.swing.JComboBox<>();
        lbSDT1 = new javax.swing.JLabel();
        tfGB = new javax.swing.JTextField();
        lbHoten1 = new javax.swing.JLabel();
        cbxNCC = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lbTrangthai = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tfTiemKiem = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnFind1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbxNCC1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnFind2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbxNCC2 = new javax.swing.JComboBox<>();

        lbQLNV.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        lbQLNV.setText("Quản lý Hàng Hóa");

        tbHangHoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbHangHoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaHH", "TenSp", "Nha Cung Cap", "Gia Nhap", "Kieu", "So Luong"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHangHoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHangHoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHangHoa);

        lbMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV.setText("Mã HH");

        lbHoten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHoten.setText("Tên HH");

        lbSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSDT.setText("Giá Nhập");

        lbDiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi.setText("Số Lượng");

        tfMaHH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfTenHH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tfGN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfGNKeyReleased(evt);
            }
        });

        tfSL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Thông tin");

        lbDiachi1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi1.setText("Loại");

        lbSDT1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSDT1.setText("Giá Bán");

        tfGB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfGBKeyReleased(evt);
            }
        });

        lbHoten1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHoten1.setText("Ten NCC");

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

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk (1).png"))); // NOI18N
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print Sale.png"))); // NOI18N
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
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

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/x-button.png"))); // NOI18N
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel3)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHoten)
                            .addComponent(lbMaNV)
                            .addComponent(lbDiachi1)
                            .addComponent(lbHoten1))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbLoai, 0, 158, Short.MAX_VALUE)
                            .addComponent(tfTenHH)
                            .addComponent(cbxNCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfMaHH))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbSDT)
                                        .addGap(26, 26, 26)
                                        .addComponent(tfGN, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbDiachi)
                                            .addComponent(lbSDT1))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfGB)
                                            .addComponent(tfSL))))
                                .addGap(261, 261, 261)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(340, 340, 340))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMaNV)
                            .addComponent(tfMaHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfGB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbDiachi)
                                    .addComponent(tfSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 27, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbHoten)
                                    .addComponent(tfTenHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHoten1)
                                    .addComponent(cbxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lbDiachi1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancel1)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEdit)
                            .addComponent(btnDel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave)
                            .addComponent(btnCancel))
                        .addContainerGap())))
        );

        lbTrangthai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng Thái");

        tfTiemKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfTiemKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTiemKiemActionPerformed(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (1).png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        jLabel1.setText("Tìm Theo Tên Sản Phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfTiemKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(119, 119, 119))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTiemKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnFind1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (1).png"))); // NOI18N
        btnFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFind1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tìm Theo Tên Nhà Cung Cấp");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cbxNCC1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFind1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(119, 119, 119))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFind1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNCC1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnFind2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (1).png"))); // NOI18N
        btnFind2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFind2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tìm Theo Kiểu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cbxNCC2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFind2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(119, 119, 119))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFind2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNCC2))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbQLNV)
                .addGap(485, 485, 485))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(470, 470, 470)
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQLNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHangHoaMouseClicked
        int click = tbHangHoa.getSelectedRow();
        TableModel model = tbHangHoa.getModel();
        String tfncc;
        tfMaHH.setText(model.getValueAt(click, 0).toString());
        tfTenHH.setText(model.getValueAt(click, 1).toString());
        tfncc = model.getValueAt(click, 2).toString();
        System.out.println("" + tfncc);
        String tenccString = null;
        try {
            tenccString = qlkh.TimTenNCC(tfncc);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbxNCC.setSelectedItem(tenccString);
        tfGN.setText(model.getValueAt(click, 3).toString());
        tfGB.setText(model.getValueAt(click, 4).toString());
        tfSL.setText(model.getValueAt(click, 6).toString());
        String loai = model.getValueAt(click, 5).toString();
        cbLoai.setSelectedItem(loai);

        Enabled();
        btnEdit.setEnabled(true);
        btnDel.setEnabled(true);
    }//GEN-LAST:event_tbHangHoaMouseClicked

    private void tfGNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfGNKeyReleased

    }//GEN-LAST:event_tfGNKeyReleased

    private void tfGBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfGBKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfGBKeyReleased

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed

        String tkString = tfTiemKiem.getText();
        try {
            TimKiemTheoTen(tkString);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void tfTiemKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTiemKiemActionPerformed
        // TODO add your handling code here:
        tfTiemKiem.requestFocus();
        btnFindActionPerformed(evt);
    }//GEN-LAST:event_tfTiemKiemActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        Enabled();
        btnAdd.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        try {
            ShowDataCombo3();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        Disabled();

        String mahh = tfMaHH.getText();
        String tenhh = tfTenHH.getText();
        String TenNCC = cbxNCC.getSelectedItem().toString();
        String MaNCC = null;
        try {
            MaNCC = qlkh.TimMaNCC(TenNCC);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long GN = Long.parseLong(tfGN.getText());
        Long GB = Long.parseLong(tfGB.getText());
        String SL = tfSL.getText();
        String Kieu = cbLoai.getSelectedItem().toString();
        if (mahh.equals("") || tenhh.equals("")) {
            JOptionPane.showMessageDialog(null,"Khong Duoc De Trong");
        }
        else{
            MatHang obj = new MatHang(mahh, tenhh, MaNCC, SL, Kieu, GN, GB);
            //Luu cho tthem moi
            qlkh.Sua(obj);
            try {
                showData();
                reset();
                //Do lai du lieu vao Table Model
            } catch (SQLException ex) {
                Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed

        Disabled();

        String mahh = tfMaHH.getText();
        String tenhh = tfTenHH.getText();
        String MaNCC = cbxNCC.getSelectedItem().toString();
        Long GN = Long.parseLong(tfGN.getText());
        Long GB = Long.parseLong(tfGB.getText());
        String SL = tfSL.getText();
        String Kieu = cbLoai.getSelectedItem().toString();
        if (mahh.equals("") || tenhh.equals("")) {
            JOptionPane.showMessageDialog(null,"Khong Duoc De Trong");
        }
        else{
            try {
                MatHang obj = new MatHang(mahh, tenhh, MaNCC, SL, Kieu, GN, GB);
                //Luu cho tthem moi
                qlkh.Xoa(mahh);
                clearData(); //goi ham xoa du lieu tron tableModel
                showData(); //Do lai du lieu vao Table Model
                reset();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Capnhat that bai", "Thong bao", 1);
            }
        }

    }//GEN-LAST:event_btnDelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        String mahh = tfMaHH.getText().trim();
        String tenhh = tfTenHH.getText();
        String TenNCC = cbxNCC.getSelectedItem().toString();
        if (mahh.equals("")) {
            JOptionPane.showMessageDialog(null, "Ma Khong Duoc De Trong", "Thong Bao", 1);
        } else {
            String MaNCC = null;
            try {
                MaNCC = qlkh.TimMaNCC(TenNCC);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
            }
            Long GN = Long.parseLong(tfGN.getText());
            Long GB = Long.parseLong(tfGB.getText());
            String SL = tfSL.getText();
            String Kieu = cbLoai.getSelectedItem().toString();
            if (mahh.equals("") || tenhh.equals("")) {
                JOptionPane.showMessageDialog(null, "Khong Duoc De Trong");
            } else {
                try {
                    if (CoMaHHChua(mahh) == true) {
                        JOptionPane.showMessageDialog(null, "Da Co Ma Hang Hoa Nay", "Thong Bao", 1);
                    } else {
                        MatHang obj = new MatHang(mahh, tenhh, MaNCC, SL, Kieu, GN, GB);
                        //Luu cho tthem moi
                        qlkh.Them(obj);
                        clearData(); //goi ham xoa du lieu tron tableModel
                        showData(); //Do lai du lieu vao Table Model
                        reset();
                        Disabled();

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Disabled();
        reset();

    }//GEN-LAST:event_btnCancelActionPerformed
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
            exportExcel(tbHangHoa);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFind1ActionPerformed

            // TODO add your handling code here:
            String TenNCC = cbxNCC1.getSelectedItem().toString();
            try {
                
            String MaNCC = qlkh.TimMaNCC(TenNCC);
            TimKiemTheoTen(MaNCC);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFind1ActionPerformed

    private void btnFind2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFind2ActionPerformed
        // TODO add your handling code here:
        String TenKieu = cbxNCC2.getSelectedItem().toString();
            try {
             
            TimKiemTheoTen(TenKieu);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFind2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        QuanLiNCC form2 = null;
        try {
            form2 = new QuanLiNCC();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLiHangHoapn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        form2.setVisible(true);
        Disabled();
        reset();
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFind1;
    private javax.swing.JButton btnFind2;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbLoai;
    private javax.swing.JComboBox<String> cbxNCC;
    private javax.swing.JComboBox<String> cbxNCC1;
    private javax.swing.JComboBox<String> cbxNCC2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDiachi;
    private javax.swing.JLabel lbDiachi1;
    private javax.swing.JLabel lbHoten;
    private javax.swing.JLabel lbHoten1;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbQLNV;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbSDT1;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JTable tbHangHoa;
    private javax.swing.JTextField tfGB;
    private javax.swing.JTextField tfGN;
    private javax.swing.JTextField tfMaHH;
    private javax.swing.JTextField tfSL;
    private javax.swing.JTextField tfTenHH;
    private javax.swing.JTextField tfTiemKiem;
    // End of variables declaration//GEN-END:variables
}
