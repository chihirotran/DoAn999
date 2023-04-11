/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author PC Market
 */
public class KHACHHANG1 {
    private String MaKH;
    private String HoTen;
    private String SDT;
    private String TongSoTienString;

    public KHACHHANG1(String MaKH, String HoTen, String SDT, String TongSoTienString) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.TongSoTienString = TongSoTienString;
    }
    private String DiaChi;
    private String Email;

    public KHACHHANG1(String MaKH, String HoTen, String SDT, String DiaChi, String Email) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.Email = Email;
    }

    public KHACHHANG1(String MaKH, String HoTen) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTongSoTienString() {
        return TongSoTienString;
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
