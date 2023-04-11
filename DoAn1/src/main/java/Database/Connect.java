/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chihi
 */
public class Connect {
    String svName = "DESKTOP-7G38HSS\\SQLEXPRESS";
    String dbName = "CuaHangQuanAo";
    int port = 1433;
    String user = "sa";
    String pass = "123456";
    Connection conn = null;
     public Connection getConnectDB(){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://"+svName+":"+port+";databaseName="+dbName+";encrypt=false";
            conn = DriverManager.getConnection(dbUrl,user,pass);
             System.out.println("Ket Noi Thanh Cong");
            }
         catch (Exception ex) {
             System.out.println("Loi: "+ex.getMessage());
         }
         return conn;
     }
}
