/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chihi
 */
public class PhanChucVu {
    private String TK;
    private String ChucVuString;

    public PhanChucVu() {
    }

    public PhanChucVu(String TK, String ChucVuString) {
        this.TK = TK;
        this.ChucVuString = ChucVuString;
    }

    public String getTK() {
        return TK;
    }

    public String getChucVuString() {
        return ChucVuString;
    }
    
}
