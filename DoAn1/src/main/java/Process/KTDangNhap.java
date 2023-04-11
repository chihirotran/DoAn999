/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;

import InterFace.QuanLi1;
import InterFace.ThuKho1;
import InterFace.ThuNhan11;

import InterFace.frmThuNgan;
import Model.PhanChucVu;
import Model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author chihi
 */
public class KTDangNhap {
    public Connect cn= new Connect();
    private String quyenString;
    private String MaNV;
    public boolean KiemTra(TaiKhoan obj, JFrame jframe){
        try {
            Connection db = cn.getConnectDB();
            String q = "select * from TaiKhoan T,QuanLiNhanVien Q where T.MaNV=Q.MaNV AND TaiKhoan = ? and MatKhau = ?";  
//            PreparedStatement st = db.prepareCall(q);
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getTK());
            st.setString(2, obj.getMK());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                quyenString = rs.getString("quyen");
                MaNV=rs.getString("MaNV");
                JOptionPane.showMessageDialog(null,"Dang Nhap Thanh Cong");
                System.out.println("" +MaNV+"*");
                if(quyenString.equals("quanli    ")){
                    QuanLi1 form2 = new QuanLi1(MaNV);
                    jframe.setVisible(false);
                    form2.setVisible(true);
                    
                }
                if(quyenString.equals("thukho    ")){
                    ThuKho1 form2 = new ThuKho1(MaNV);
                    jframe.setVisible(false);
                    form2.setVisible(true);
                }
                if(quyenString.equals("thungan   ")){
                    ThuNhan11 form2 = new ThuNhan11(MaNV);
                    jframe.setVisible(false);
                    form2.setVisible(true);
                }
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Loi Kiem Tra: "+ e.getMessage());
        }
        return false;
    }
    public String QuenPass(String email) throws SQLException{
    String aString="";
    String q = "  SELECT TaiKhoan, MatKhau FROM TaiKhoan,QuanLiNhanVien WHERE TaiKhoan.MaNV=QuanLiNhanVien.MaNV AND QuanLiNhanVien.Email="+email;        
        try (Connection db = cn.getConnectDB()) {
            
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                TaiKhoan obj = new TaiKhoan(rs.getString("TaiKhoan"),rs.getString("MatKhau"));
                aString = ""+obj;
            }
        }
    return aString;
    }
    public String QuenPass1(String email) throws SQLException{
    String aString="";
    try {
            Connection db = cn.getConnectDB();
            String q = "SELECT TaiKhoan, MatKhau FROM TaiKhoan,QuanLiNhanVien WHERE TaiKhoan.MaNV=QuanLiNhanVien.MaNV AND QuanLiNhanVien.Email=?";  
//            PreparedStatement st = db.prepareCall(q);
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, email);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                String aStringTK = rs.getString("TaiKhoan");
                String aStringMK=rs.getString("MatKhau");
                
                aString = "TaiKhoan: "+aStringTK+"MatKhau: "+aStringMK;
                System.out.println("" +aString+"*");
                
                return aString;
            }
            
        } catch (Exception e) {
            System.out.println("Loi Kiem Tra: "+ e.getMessage());
        }
        return aString;
    }
}
