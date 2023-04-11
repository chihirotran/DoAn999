/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.HoaDon;
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
public class QuanLyDonHang {
    public Connect cn= new Connect();
    public List<HoaDon> getAll() throws SQLException{
    String q = "SELECT HoaDon.MaHD,HoaDon.NgayTaoHD,CTHoaDon.TongTien,HoaDon.MaNV,CTHoaDon.MaHH,CTHoaDon.SoLuong,KHACHHANG.MAKH,KHACHHANG.HOTEN,KHACHHANG.DIACHI,KHACHHANG.SDT,KHACHHANG.EMAIL FROM HoaDon,CTHoaDon,KHACHHANG,SanPham WHERE HoaDon.MaHD=CTHoaDon.MaHD AND CTHoaDon.MaHH=SanPham.MaHH AND HoaDon.MaKH=KHACHHANG.MAKH OrDER BY HoaDon.NgayTaoHD DESC";
        List<HoaDon> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoaDon obj = new HoaDon(rs.getString("MaHD"),rs.getDate("NgayTaoHD"),rs.getLong("TongTien"),rs.getString("MaNV"),rs.getString("MaKH"),rs.getString("MaHH"),rs.getInt("SoLuong"),rs.getString("HOTEN"),rs.getString("DIACHI"),rs.getString("SDT"),rs.getString("EMAIL"));
                ds.add(obj);
            }
        }
        return ds;
    }
public List<HoaDon> getdatatheoHoaDon(String MaHD) throws SQLException{
    String q = "SELECT HoaDon.MaHD,HoaDon.NgayTaoHD,CTHoaDon.TongTien,HoaDon.MaNV,CTHoaDon.MaHH,CTHoaDon.SoLuong,KHACHHANG.MAKH,KHACHHANG.HOTEN,KHACHHANG.DIACHI,KHACHHANG.SDT,KHACHHANG.EMAIL FROM HoaDon,CTHoaDon,KHACHHANG,SanPham WHERE HoaDon.MaHD=CTHoaDon.MaHD AND CTHoaDon.MaHH=SanPham.MaHH AND HoaDon.MaKH=KHACHHANG.MAKH AND CTHoaDon.MAHD=?";
        List<HoaDon> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, MaHD);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoaDon obj = new HoaDon(rs.getString("MaHD"),rs.getDate("NgayTaoHD"),rs.getLong("TongTien"),rs.getString("MaNV"),rs.getString("MaKH"),rs.getString("MaHH"),rs.getInt("SoLuong"),rs.getString("HOTEN"),rs.getString("DIACHI"),rs.getString("SDT"),rs.getString("EMAIL"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<MatHang> TimTheoTen(String ml) throws SQLException{
    String q = "SELECT MaHH,TenSP,NhaCungCap,GiaNhap,GiaBan,Kieu,SoLuong FROM SanPham WHERE TenSP like '%"+ml+"%'";
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
    public List<MatHang> GiaTienTheoSL(String ml,String TenSP,int SL) throws SQLException{
    String q = "SELECT MaHH,TenSP,NhaCungCap,GiaNhap,GiaBan,Kieu,SoLuong FROM SanPham WHERE TenSP like '%"+ml+"%'";
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
    
    public String TimMaHH(String tnccString,String tensp) throws SQLException{
    String q = "SELECT MAHH FROM SanPham,NCC WHERE SanPham.NhaCungCap=NCC.MaNCC AND TenNCC=? AND TenSP=?";
    String mahhString=null;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, tnccString);
    st.setString(2, tensp);
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        mahhString=(rs.getString("MAHH"));

    }
    return mahhString ;
}
 public String TimTenHHTheoMaHH(String MaHH) throws SQLException{
    String q = "  SELECT TenSP From SanPham WHERE MAHH=? GROUP BY TenSP";
    String TongTienHD=null;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, MaHH);
    
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        TongTienHD=""+rs.getString("TenSP");
    }
    return TongTienHD ;
}
 public String TimTenNCCTheoMaHH(String MaHH) throws SQLException{
    String q = "    SELECT TenNCC FROM SanPham,NCC WHERE SanPham.NhaCungCap=NCC.MaNCC AND MaHH=? GROUP BY TenNCC";
    String TongTienHD=null;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, MaHH);
    
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        TongTienHD=""+rs.getString("TenNCC");
    }
    return TongTienHD ;
}
public String TongTienDonHang(String MaHD) throws SQLException{
    String q = "SELECT SUM(CTHoaDon.TongTien)AS TongHoaDon,MaHD  FROM CTHoaDon GROUP BY MaHD  HAVING  MAHD=? ";
    String TongTienHD=null;
    Connection db = cn.getConnectDB();
    PreparedStatement st = db.prepareStatement(q);
    st.setString(1, MaHD);
    
    ResultSet rs = st.executeQuery();
    if(rs.next()){
        TongTienHD=""+rs.getLong("TongHoaDon");

    }
    return TongTienHD ;
}
    public boolean Them(String MaHH,String MHD,int SL){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into CTHoaDon(MaHH,MAHD,SoLuong,TongTien) VALUES (?,?,?,?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, MaHH);
            st.setString(2, MHD);
            st.setInt(3,SL);
            st.setLong(4,3);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean UpdateTienDonHang(Long TongTien,String MaHD){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update HoaDon set TongTien=? where MaHD=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setLong(1,TongTien );
            st.setString(2,MaHD );
            
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    
public boolean ThemHD(String MaHD,String MaNV,String MaKH){
        try {
            Connection db = cn.getConnectDB();
            String q = "INSERT INTO HoaDon(MaHD,NgayTaoHD,TongTien,MaNV,MaKH)VALUES (?,GETDATE(),0,?,?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, MaHD);
            st.setString(2, MaNV);
            st.setString(3, MaKH);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
            
        }
        return false;
    }
public boolean UpdateTongTienDonHang(Long TongTien,String MaHD){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update HoaDon set TongTien=? where MaHD=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setLong(1,TongTien );
            st.setString(2,MaHD );
            
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public int TimID(String mahd,String mahh,String sl ) throws SQLException{
    String q = "Select id From CTHoaDon Where MAHD=? AND MAHH=? AND SoLuong=?";
        int id = 0;
        try (Connection db = cn.getConnectDB()) {
            
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, mahd);
            st.setString(2, mahh);
            st.setString(3, sl);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                id = rs.getInt("ID");
                
            }
        }
        return id;
    }
    public boolean Xoa(int id){
        try {
            Connection db = cn.getConnectDB();
            String q = "Delete from CTHoaDon where ID=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setInt(1, id);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    public boolean luulsxoadonhang(String manv){
        try {
            Connection db = cn.getConnectDB();
                String q = "Insert into LichSuXoaDonHang(Time,MaNV) VALUES (GETDATE(),?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, manv);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
   
}
