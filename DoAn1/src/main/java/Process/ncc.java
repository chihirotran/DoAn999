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
 * @author PC Market
 */
public class ncc {
    public Connect cn= new Connect();
    public boolean Them(NCC obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Insert into NCC(MaNCC, TenNCC) VALUES(?, ?)";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getIdNCC());
            st.setString(2, obj.getTenNCC());
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
public boolean Sua(NCC obj){
        try {
            Connection db = cn.getConnectDB();
            String q = "Update NCC set TenNCC=? where MaNCC=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, obj.getTenNCC());
            st.setString(2, obj.getIdNCC());
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
            String q = "Delete from NCC where MaNCC=?";  
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, ml);
            int check = st.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }
        return false;
    }
    public List<NCC> getAll() throws SQLException{
    String q = "SELECT MaNCC,TenNCC FROM NCC";
        List<NCC> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NCC obj = new NCC(rs.getString("MaNCC"),rs.getString("TenNCC"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<NCC> TimTheoTen(String ml) throws SQLException{
    String q = "SELECT MaNCC,TenNCC FROM NCC WHERE TenNCC like '%"+ml+"%'";
        List<NCC> ds;
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NCC obj = new NCC(rs.getString("MaNCC"),rs.getString("TenNCC"));
                ds.add(obj);
            }
        }
        return ds;
    }
}
