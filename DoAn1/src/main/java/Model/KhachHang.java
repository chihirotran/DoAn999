/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chihi
 */
public class KhachHang {
    private String MaKH;
    private String HoTen;
    private String SDT;
    
    private String DiaChi;
    private String Email;

    public KhachHang(String MaKH, String HoTen, String SDT, String DiaChi, String Email) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.Email = Email;
    }

    public KhachHang(String MaKH, String HoTen) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getEmail() {
        return Email;
    }
    
    

}
