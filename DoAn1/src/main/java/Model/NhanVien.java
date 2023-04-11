/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author chihi
 */
public class NhanVien {
    private String TenNV;
    private String HoVaTen;
    private String GioiTinh;
    private String SDT;
    private String NgaySinh;
    private String DiaChiString;
    private String Email;
    private Long LuongLong;

    public NhanVien(String TenNV, String HoVaTen, String GioiTinh, String SDT, String NgaySinh, String DiaChiString, String Email, Long LuongLong, String MaNV) {
        this.TenNV = TenNV;
        this.HoVaTen = HoVaTen;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.NgaySinh = NgaySinh;
        this.DiaChiString = DiaChiString;
        this.Email = Email;
        this.LuongLong = LuongLong;
        this.MaNV = MaNV;
    }
    private  String TK;
    private String MK;
    private String quyen;
    private String MaNV;

    public NhanVien() {
    }

    
    public NhanVien(String TenNV, String HoVaTen, String GioiTinh, String SDT, String NgaySinh, String DiaChiString, String Email, Long LuongLong, String TK, String MK, String quyen, String MaNV) {
        this.TenNV = TenNV;
        this.HoVaTen = HoVaTen;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.NgaySinh = NgaySinh;
        this.DiaChiString = DiaChiString;
        this.Email = Email;
        this.LuongLong = LuongLong;
        this.TK = TK;
        this.MK = MK;
        this.quyen = quyen;
        this.MaNV = MaNV;
    }

    public NhanVien(String TenNV, String HoVaTen, String GioiTinh, String SDT, String NgaySinh, String DiaChiString, String Email, Long LuongLong, String quyen, String MaNV) {
        this.TenNV = TenNV;
        this.HoVaTen = HoVaTen;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.NgaySinh = NgaySinh;
        this.DiaChiString = DiaChiString;
        this.Email = Email;
        this.LuongLong = LuongLong;
        this.quyen = quyen;
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public NhanVien(String TK, String MK, String MaNV) {
        this.TK = TK;
        this.MK = MK;
        this.MaNV = MaNV;
    }

    
    

    public String getHoVaTen() {
        return HoVaTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getDiaChiString() {
        return DiaChiString;
    }

    public String getEmail() {
        return Email;
    }

    public Long getLuongLong() {
        return LuongLong;
    }

    public String getTK() {
        return TK;
    }

    public String getMK() {
        return MK;
    }

    public String getQuyen() {
        return quyen;
    }

    public String getMaNV() {
        return MaNV;
    }

    
    
    
}
