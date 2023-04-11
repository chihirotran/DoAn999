/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.HoaDon;
import Model.MatHang;
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
public class QuanLyNCC {
    public Connect cn= new Connect();
    public List<NCC> getAll() throws SQLException{
    String q = "SELECT * FROM NCC";
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
    public List<MatHang> GetTenSP(String NCC) throws SQLException{
        String q ="SELECT TenSP,TenNCC FROM NCC,SanPham WHERE NCC.MaNCC=SanPham.NhaCungCap GROUP BY NCC.TenNCC,TenSP HAVING NCC.TenNCC=?";
        List<MatHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, NCC);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MatHang obj = new MatHang(rs.getString("TenNCC"),rs.getString("TenSP"));
                ds.add(obj);
            }
        }
        return ds;
    }
    
}
