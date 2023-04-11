/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chihi
 */
public class Kieu {
    private String idKieu;
    private String TenKieu;

    public Kieu(String idKieu, String TenKieu) {
        this.idKieu = idKieu;
        this.TenKieu = TenKieu;
    }

    public String getIdKieu() {
        return idKieu;
    }

    public String getTenKieu() {
        return TenKieu;
    }

    public Kieu(String TenKieu) {
        this.TenKieu = TenKieu;
    }
    
     
}
