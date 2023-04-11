/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.KhachHang;
import Model.NCC;
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
public class QuanLyKhachHang {
    public Connect cn= new Connect();
    public List<KhachHang> getAll() throws SQLException{
    String q = "SELECT * FROM KHACHHANG";
        List<KhachHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                KhachHang obj = new KhachHang(rs.getString("MAKH"),rs.getString("HOTEN"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<KhachHang> GetTenSP(String MaKH) throws SQLException{
        String q ="SELECT * FROM KHACHHANG WHERE MAKH=?";
        List<KhachHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, MaKH);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                KhachHang obj = new KhachHang(rs.getString("MAKH"),rs.getString("HOTEN"),rs.getString("SDT"),rs.getString("DIACHI"),rs.getString("EMAIL"));
                ds.add(obj);
            }
        }
        return ds;
    }
}
