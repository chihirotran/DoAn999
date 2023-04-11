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
public class HoaDon{
    private String MaHD;
    private Date NgayTaoDate;
    private Long TongTienLong;
    private String MaNV;
    private String MaKH;
    private String MaHH;
    private int SoLuong;
    private String HOTEN;
    private String DIACHI;
    private String SDT;
    private String EMAIL;
    

    public HoaDon() {
    }

    public HoaDon(String MaHD, Date NgayTaoDate, Long TongTienLong, String MaNV, String MaKH) {
        this.MaHD = MaHD;
        this.NgayTaoDate = NgayTaoDate;
        this.TongTienLong = TongTienLong;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
    }

    public HoaDon(String MaHH) {
        this.MaHH = MaHH;
    }

    public HoaDon(String MaHD, Date NgayTaoDate, Long TongTienLong, String MaNV, String MaKH, String MaHH, int SoLuong, String HOTEN, String DIACHI, String SDT, String EMAIL) {
        this.MaHD = MaHD;
        this.NgayTaoDate = NgayTaoDate;
        this.TongTienLong = TongTienLong;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.MaHH = MaHH;
        this.SoLuong = SoLuong;
        this.HOTEN = HOTEN;
        this.DIACHI = DIACHI;
        this.SDT = SDT;
        this.EMAIL = EMAIL;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public String getSDT() {
        return SDT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    
    

    public String getMaHD() {
        return MaHD;
    }

    public Date getNgayTaoDate() {
        return NgayTaoDate;
    }

    public Long getTongTienLong() {
        return TongTienLong;
    }

    public String getMaNV() {
        return MaNV;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getMaHH() {
        return MaHH;
    }

    public int getSoLuong() {
        return SoLuong;
    }
    
}
