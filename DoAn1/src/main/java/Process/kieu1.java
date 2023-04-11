/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.KhachHang;
import Model.Kieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC Market
 */
public class kieu1 {
    public Connect cn= new Connect();
    public boolean Them(Kieu obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into LoaiKieu(Kieu, TenKieu) VALUES(?, ?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getIdKieu());
            st.setString(2, obj.getTenKieu());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean Sua(Kieu obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update LoaiKieu set TenKieu=? where Kieu=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getTenKieu());
            st.setString(2, obj.getIdKieu());
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
            String q = "Delete from LoaiKieu where Kieu=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, ml);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    public List<Kieu> getAll() throws SQLException{
    String q = "SELECT Kieu,TenKieu FROM LoaiKieu";
        List<Kieu> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Kieu obj = new Kieu(rs.getString("Kieu"),rs.getString("TenKieu"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<Kieu> TimTheoTen(String ml) throws SQLException{
    String q = "SELECT Kieu,TenKieu FROM LoaiKieu WHERE TenKieu like '%"+ml+"%'";
        List<Kieu> ds;
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Kieu obj = new Kieu(rs.getString("Kieu"),rs.getString("TenKieu"));
                ds.add(obj);
            }
        }
        return ds;
    }
}
