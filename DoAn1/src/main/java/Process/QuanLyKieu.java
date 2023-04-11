/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Model.HoaDon;
import Model.MatHang;
import Model.Kieu;
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
public class QuanLyKieu {
    public Connect cn= new Connect();
    public List<Kieu> getAll() throws SQLException{
    String q = "SELECT * FROM Kieu";
        List<Kieu> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Kieu obj = new Kieu(rs.getString("Kieu"),rs.getString("Ten Kieu"));
                ds.add(obj);
            }
        }
        return ds;
    }
    public List<MatHang> GetTenSP(String Kieu) throws SQLException{
        String q ="SELECT TenSP,[Ten Kieu] FROM Kieu,SanPham WHERE Kieu.MaKieu=SanPham.NhaCungCap GROUP BY Kieu.TenKieu,TenSP HAVING Kieu.TenKieu=?";
        List<MatHang> ds;
        
        try (Connection db = cn.getConnectDB()) {
            ds = new ArrayList<>();
            PreparedStatement st = db.prepareStatement(q);
            st.setString(1, Kieu);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MatHang obj = new MatHang(rs.getString("TenKieu"),rs.getString("TenSP"));
                ds.add(obj);
            }
        }
        return ds;
    }
    
}
