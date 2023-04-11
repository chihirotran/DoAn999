/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.MatHang;
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
public class QuanLyKhoHang {
    public Connect cn= new Connect();
    public List<MatHang> getAll() throws SQLException{
    String q = "SELECT MaHH,TenSP,NhaCungCap,GiaNhap,GiaBan,Kieu,SoLuong FROM SanPham";
        List<MatHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MatHang obj = new MatHang(rs.getString("MaHH"),rs.getString("TenSP"),rs.getString("NhaCungCap"),rs.getString("SoLuong"),rs.getString("Kieu"),rs.getLong("GiaBan"),rs.getLong("GiaNhap"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<MatHang> TimTheoTen(String ml) throws SQLException{
    String q = "SELECT MaHH,TenSP,NhaCungCap,GiaNhap,GiaBan,Kieu,SoLuong FROM SanPham WHERE TenSP like '%"+ml+"%' or NhaCungCap like '%"+ml+"%' or Kieu like '%"+ml+"%'";
        List<MatHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MatHang obj = new MatHang(rs.getString("MaHH"),rs.getString("TenSP"),rs.getString("NhaCungCap"),rs.getString("SoLuong"),rs.getString("Kieu"),rs.getLong("GiaBan"),rs.getLong("GiaNhap"));
                ds.add(obj);
            }
        }
        return ds;
    }
public boolean Them(MatHang obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into SanPham(MaHH,TenSP,NhaCungCap,GiaNhap,GiaBan,Kieu,SoLuong) VALUES (?,?,?,?,?,?,?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getMaHH());
            st.setString(2, obj.getTenSP());
            st.setString(3, obj.getNCC());
            st.setLong(4,obj.getGiaNhap());
            st.setLong(5,obj.getGiaBan());
            st.setString(6, obj.getKieu());
            st.setString(7,obj.getSL());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean Sua(MatHang obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update Sanpham set TenSP=?,NhaCungCap=?,GiaNhap=?,GiaBan=?,Kieu=?,SoLuong=? where MaHH=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getTenSP());
            st.setString(2, obj.getNCC());
            st.setLong(3,obj.getGiaNhap());
            st.setLong(4,obj.getGiaBan());
            st.setString(5, obj.getKieu());
            st.setString(6,obj.getSL());
            st.setString(7, obj.getMaHH());
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
            String q = "Delete from Sanpham where MaHH=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, ml);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public String TimMaNCC(String tnccString) throws SQLException{
    String q = "SELECT MaNCC FROM NCC WHERE TenNCC=?";
    String mancc=null;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, tnccString);
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        mancc=(rs.getString("MaNCC"));

    }
    return mancc ;
}
public String TimTenNCC(String MaNCC) throws SQLException{
    String q = "SELECT TenNCC FROM NCC WHERE MaNCC=?";
    String mancc=null;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, MaNCC);
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        mancc=(rs.getString("TenNCC"));

    }
    return mancc ;
}
public boolean CapNhatSLDonHang(int SL,String MaHH){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update Sanpham set SoLuong=(SoLuong - ? )where MaHH=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setInt(1, SL);
            st.setString(2,MaHH);
            
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public int CapNhatSLDonHangTrongKho(String TenSP,String IDNCC) throws SQLException{
    String q = "SELECT SoLuong FROM SanPham,NCC WHERE SanPham.NhaCungCap=NCC.MaNCC AND TenSP=? AND TenNCC=?";
    int mancc=0;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, TenSP);
    st.setString(2, IDNCC);
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        mancc=rs.getInt("SoLuong");

    }
    return mancc ;
}

}
