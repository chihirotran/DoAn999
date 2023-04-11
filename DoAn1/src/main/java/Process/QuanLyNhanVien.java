/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.MatHang;
import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chihi
 */
public class QuanLyNhanVien {
    public Connect cn= new Connect();
    public List<NhanVien> getAll() throws SQLException{
    String q = "SELECT * FROM QuanLiNhanVien";
        List<NhanVien> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NhanVien obj = new NhanVien(rs.getString("TenNV"),rs.getString("HoVaTen"),rs.getString("GioiTinh"),rs.getString("SDT"),rs.getString("NgaySinh"),rs.getString("DiaChi"),rs.getString("Email"),rs.getLong("Luong"),rs.getString("Quyen"),rs.getString("MaNV"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<NhanVien> TimTheoTen(String ml) throws SQLException{
    String q = "SELECT * FROM QuanLiNhanVien WHERE TenSP HoVaTen '%"+ml+"%'";
        List<NhanVien> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NhanVien obj = new NhanVien(rs.getString("TenNV"),rs.getString("HoVaTen"),rs.getString("GioiTinh"),rs.getString("SDT"),rs.getString("NgaySinh"),rs.getString("DiaChi"),rs.getString("Email"),rs.getLong("Luong"),rs.getString("Quyen"),rs.getString("MaNV"));
                ds.add(obj);
            }
        }
        return ds;
    }
public boolean ThemTTNV(NhanVien obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into QuanLiNhanVien VALUES (?,?,?,?,?,?,?,?,?,?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getMaNV());
            st.setString(2, obj.getQuyen());
            st.setString(3, obj.getTenNV());
            st.setString(4,obj.getHoVaTen());
            st.setString(5,obj.getGioiTinh());
            st.setString(6, obj.getSDT());
            st.setString(7, obj.getNgaySinh());
            st.setString(8, obj.getDiaChiString()   );
            st.setString(9, obj.getEmail());
            st.setLong(10,obj.getLuongLong());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean ThemTK(NhanVien obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into TaiKhoan VALUES (?,?,?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getTK());
            st.setString(2, obj.getMK());
            st.setString(3, obj.getMaNV());
            System.out.println("Manv: "+obj.getMaNV());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean KiemTraCoTk(String MaNV){
        try {
            Connection db = cn.getConnectDB();
            String q = "SELECT MaNV From TaiKhoan Where MaNV='"+MaNV+"'";  
            PreparedStatement st = db.prepareStatement(q);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            String maString= rs.getString("MaNV");
            System.out.println(""+maString);
            if(maString.equals(MaNV)){
             return true;   
            }
            else{
            return false;
            }
            }
            
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean Sua(NhanVien obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update QuanLiNhanVien set Quyen=?,TenNV=?,HoVaTen=?,GioiTinh=?,SDT=?,NgaySinh=?,DiaChi=?,Email=?,Luong=? where MaNV=?";  
            PreparedStatement st = db.prepareStatement(q);
            
            st.setString(1, obj.getQuyen());
            st.setString(2, obj.getTenNV());
            st.setString(3,obj.getHoVaTen());
            st.setString(4,obj.getGioiTinh());
            st.setString(5, obj.getSDT());
            st.setString(6,obj.getNgaySinh());
            st.setString(7, obj.getDiaChiString());
            st.setString(8, obj.getEmail());
            st.setLong(9,obj.getLuongLong());
            st.setString(10, obj.getMaNV());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    public boolean Xoa(String ml){
        try {
            Connection db = cn.getConnectDB();
            String q = "Delete from QuanLiNhanVien where MaNV=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, ml);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    public boolean XoaTK(String ml){
        try {
            Connection db = cn.getConnectDB();
            String q = "Delete from TaiKhoan where MaNV=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, ml);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }

}
