/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chihi
 */
public class TaiKhoan {
    private  String TK;
    private String MK;
    private String quyen;
    private String MaNV;

    public String getQuyen() {
        return quyen;
    }

    public String getMaNV() {
        return MaNV;
    }

    public TaiKhoan(String TK, String MK, String quyen, String MaNV) {
        this.TK = TK;
        this.MK = MK;
        this.quyen = quyen;
        this.MaNV = MaNV;
    }
    public TaiKhoan() {
    }

    public TaiKhoan(String TK, String MK, String MaNV) {
        this.TK = TK;
        this.MK = MK;
        this.quyen = MaNV;
    }

    public TaiKhoan(String TK, String MK) {
        this.TK = TK;
        this.MK = MK;
    }
    
    

    public String getTK() {
        return TK;
    }

    public String getMK() {
        return MK;
    }

    public String GetqNV() {
        return quyen;
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    

    
    
   
    
}
