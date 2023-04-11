/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.HoaDon;
import Model.KHACHHANG1;
import Model.MatHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chihi
 */
public class ThongKe {
    public Connect cn= new Connect();
    public List<HoaDon> getAll() throws SQLException{
    String q = "SELECT * FROM HoaDon";
        List<HoaDon> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoaDon obj = new HoaDon(rs.getString("MaHD"),rs.getDate("NgayTaoHD"),rs.getLong("TongTien"),rs.getString("MaNV"),rs.getString("MaKH"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<KHACHHANG1> getAllKH() throws SQLException{
    String q = "   SELECT KHACHHANG.MAKH,HOTEN,SDT,SUM(TongTien) AS TongSoTien From KHACHHANG,HoaDon Where KHACHHANG.MAKH = HoaDon.MaKH GROUP BY KHACHHANG.MAKH,HOTEN,SDT ORDER BY TongSoTien DESC";
        List<KHACHHANG1> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                KHACHHANG1 obj = new KHACHHANG1(rs.getString("MAKH"),rs.getString("HOTEN"),rs.getString("SDT"),rs.getString("TongSoTien"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<MatHang> getAllSP() throws SQLException{
    String q = "SELECT SanPham.MaHH,SanPham.TenSP,SanPham.Kieu,SanPham.SoLuong AS SoLuongConLai,SUM(CTHoaDon.SoLuong) AS SoLuongDaBan From SanPham,CTHoaDon WHERE SanPham.MaHH=CTHoaDon.MaHH GROUP By SanPham.MaHH,SanPham.TenSP,SanPham.Kieu,SanPham.SoLuong ORDER BY SoLuongDaBan DESC";
        List<MatHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MatHang obj = new MatHang(rs.getString("MaHH"),rs.getString("TenSP"),rs.getString("Kieu"),rs.getString("SoLuongConLai"),rs.getString("SoLuongDaBan"));
                ds.add(obj);
            }
        }
        return ds;
    }
 public List<HoaDon> TimTheoNgayThangNam(String ngayString,String ThangString,String Nam) throws SQLException{
    String q = "SELECT * FROM HoaDon WHERE DAY(NgayTaoHD)="+ngayString+" AND MONTH(NgayTaoHD)="+ThangString+" AND YEAR(NgayTaoHD)="+Nam+"";
        List<HoaDon> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoaDon obj = new HoaDon(rs.getString("MaHD"),rs.getDate("NgayTaoHD"),rs.getLong("TongTien"),rs.getString("MaNV"),rs.getString("MaKH"));
                ds.add(obj);
            }
        }
        return ds;
}
  public List<HoaDon> TimTheoThangNam(String ThangString,String Nam) throws SQLException{
    String q = "SELECT * FROM HoaDon WHERE MONTH(NgayTaoHD)="+ThangString+" AND YEAR(NgayTaoHD)="+Nam+"";
        List<HoaDon> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoaDon obj = new HoaDon(rs.getString("MaHD"),rs.getDate("NgayTaoHD"),rs.getLong("TongTien"),rs.getString("MaNV"),rs.getString("MaKH"));
                ds.add(obj);
            }
        }
        return ds;
}
   public List<HoaDon> TimTheoNam(String Nam) throws SQLException{
    String q = "SELECT * FROM HoaDon WHERE  YEAR(NgayTaoHD)="+Nam+"";
        List<HoaDon> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoaDon obj = new HoaDon(rs.getString("MaHD"),rs.getDate("NgayTaoHD"),rs.getLong("TongTien"),rs.getString("MaNV"),rs.getString("MaKH"));
                ds.add(obj);
            }
        }
        return ds;
}
}
