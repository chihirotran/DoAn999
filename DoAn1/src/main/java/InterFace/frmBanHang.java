package InterFace;

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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class frmBanHang extends javax.swing.JFrame {
    private final  DefaultTableModel tbModel = new DefaultTableModel();
    private final QuanLyNCC lsp = new QuanLyNCC();
    private final QuanLyKhachHang qlkh = new QuanLyKhachHang();
     private final QuanLyKhoHang qlkho = new QuanLyKhoHang();
    private final QuanLyDonHang qldh = new QuanLyDonHang();

    


    private boolean AddDH = false, ChangeDH = false;
    private boolean AddLK = false, ChangeLK = false;

    private boolean KH = false, LK = false;
    public JFrame jframe;
    public String manvString;
    public Long TongTien1DoaDon;
    public frmBanHang(String MaNV) throws SQLException {
        setResizable(false);
        
        manvString = MaNV;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        this.setLocationRelativeTo(null);
        
        String[] colName = {"MaHD","NgayTaoHD","TongTien","MANV","MaHH","SoLuong","MaKH","HoTen","Dia Chi","SDT","Email"};
        tbModel.setColumnIdentifiers(colName);
        tbHoaDon.setModel(tbModel);
        showDatatheoMaHD(MaNV);
        
        
//        p.image = new ImageIcon("check.png");
//        jLabel3.setIcon(p.image);
    }

    frmBanHang(frmThuNgan aThis) {
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
   
    public void clearData(){
        while(tbModel.getRowCount()>0){
            tbModel.removeRow(0);
        }
    }
    
    public void showData() throws SQLException{
        clearData();
        List<HoaDon> data = getdata();
        for(int i=0; i <data.size();i++){
            
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+data.get(i).getTongTienLong(),
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
        List<HoaDon> data = getdatatheomaHD(MaHD);
        for(int i=0; i <data.size();i++){
            
            String[] row11 = {
                data.get(i).getMaHD(),
                ""+data.get(i).getNgayTaoDate(),
                ""+data.get(i).getTongTienLong(),
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
   
    
    

    private frmBanHang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    private void checkhoaDon() {
       
    }
    private void Enabled() {
        
        
        
    }

    

   
   

    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBackHome = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1289, 677));
        setMinimumSize(new java.awt.Dimension(1289, 677));
        setPreferredSize(new java.awt.Dimension(1289, 677));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnBackHome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.png"))); // NOI18N
        btnBackHome.setText("Hệ Thống");
        btnBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackHomeMouseClicked(evt);
            }
        });
        btnBackHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackHomeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cập Nhật Đơn Đặt Hàng");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBackHome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 197, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBackHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      
    }//GEN-LAST:event_formWindowClosing

    private void btnBackHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackHomeMouseClicked
      
    }//GEN-LAST:event_btnBackHomeMouseClicked

    private void btnBackHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnBackHomeActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked

        
    }//GEN-LAST:event_tbHoaDonMouseClicked

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
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new frmBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackHome;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbHoaDon;
    // End of variables declaration//GEN-END:variables
}
