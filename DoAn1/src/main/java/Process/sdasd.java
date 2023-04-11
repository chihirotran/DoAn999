/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.KhachHang;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC Market
 */
public class sdasd {
    public Connect cn= new Connect();
    public boolean Them(KhachHang obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into KHACHHANG(MAKH, HOTEN, SDT, DIACHI, EMAIL) VALUES(?, ?, ?, ?, ?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getMaKH());
            st.setString(2, obj.getHoTen());
            st.setString(3, obj.getSDT());
            st.setString(4,obj.getDiaChi());
            st.setString(5,obj.getEmail());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean Sua(KhachHang obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update KHACHHANG set HOTEN=?,SDT=?,DIACHI=?,EMAIL=? where MAKH=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getHoTen());
            st.setString(2, obj.getSDT());
            st.setString(3,obj.getDiaChi());
            st.setString(4,obj.getEmail());
            st.setString(5, obj.getMaKH());
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
            String q = "Delete from KHACHHANG where MAKH=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, ml);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    public List<KhachHang> getAll() throws SQLException{
    String q = "SELECT MAKH,HOTEN,SDT,DIACHI,EMAIL FROM KHACHHANG";
        List<KhachHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                KhachHang obj = new KhachHang(rs.getString("MAKH"),rs.getString("HOTEN"),rs.getString("SDT"),rs.getString("DIACHI"),rs.getString("EMAIL"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<KhachHang> TimTheoTen(String ml) throws SQLException{
    String q = "SELECT MAKH,HOTEN,SDT,DIACHI,EMAIL FROM KHACHHANG WHERE HOTEN like '%"+ml+"%' or SDT like '%"+ml+"%' or EMAIL like '%"+ml+"%' ";
        List<KhachHang> ds;
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                KhachHang obj = new KhachHang(rs.getString("MAKH"),rs.getString("HOTEN"),rs.getString("SDT"),rs.getString("DIACHI"),rs.getString("EMAIL"));
                ds.add(obj);
            }
        }
        return ds;
    }
}
