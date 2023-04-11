/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chihi
 */
public class NCC {
    private String idNCC;
    private String TenNCC;

    public NCC() {
    }

    public NCC(String idNCC, String TenNCC) {
        this.idNCC = idNCC;
        this.TenNCC = TenNCC;
    }

    public NCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    
    public String getIdNCC() {
        return idNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }
    
}
