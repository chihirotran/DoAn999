/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chihi
 */
public class MatHang extends NCC{
    private String MaHH;
    private String TenSP;
    private String NCC;
    private String SL;
    private String Kieu;
    private Long GiaBan;
    private Long GiaNhap;
    private String SoLuongConLaiString;
    private String SoLuongDaBan;

    public String getSoLuongConLaiString() {
        return SoLuongConLaiString;
    }

    public String getSoLuongDaBan() {
        return SoLuongDaBan;
    }

    
    public MatHang() {
    }

    public String getMaHH() {
        return MaHH;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getNCC() {
        return NCC;
    }

    public String getSL() {
        return SL;
    }

    public String getKieu() {
        return Kieu;
    }

    public Long getGiaBan() {
        return GiaBan;
    }

    public Long getGiaNhap() {
        return GiaNhap;
    }

    public MatHang(String MaHH, String TenSP, String Kieu, String SoLuongConLaiString, String SoLuongDaBan) {
        this.MaHH = MaHH;
        this.TenSP = TenSP;
        this.Kieu = Kieu;
        this.SoLuongConLaiString = SoLuongConLaiString;
        this.SoLuongDaBan = SoLuongDaBan;
    }

    
    public MatHang(String MaHH, String TenSP, String NCC, String SL, String Kieu, Long GiaBan, Long GiaNhap) {
        this.MaHH = MaHH;
        this.TenSP = TenSP;
        this.NCC = NCC;
        this.SL = SL;
        this.Kieu = Kieu;
        this.GiaBan = GiaBan;
        this.GiaNhap = GiaNhap;
    }

//    public MatHang(String MaHH, String TenSP, String NCC, int SL, String Kieu, Long GiaNhap) {
//        this.MaHH = MaHH;
//        this.TenSP = TenSP;
//        this.NCC = NCC;
//        this.SL = SL;
//        this.Kieu = Kieu;
//        this.GiaNhap = GiaNhap;
//    }

    public MatHang(String TenSP) {
        this.TenSP = TenSP;
    }

    public MatHang(String TenSP, String idNCC, String TenNCC) {
        super(idNCC, TenNCC);
        this.TenSP = TenSP;
    }

    public MatHang(String TenNCC,String TenSP){
        super(TenNCC);
        this.TenSP=TenSP;
        
    }
    

    

   
   
    
}
