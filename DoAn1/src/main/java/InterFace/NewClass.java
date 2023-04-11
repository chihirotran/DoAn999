/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterFace;

import Process.KTDangNhap;
import java.sql.SQLException;

/**
 *
 * @author chihi
 */
public class NewClass {
    public static void main(String[] args) throws SQLException {
        KTDangNhap kt = new KTDangNhap();
        String a = kt.QuenPass("THT15012gmail.com");
        System.out.println("123"+a);
    }
}
