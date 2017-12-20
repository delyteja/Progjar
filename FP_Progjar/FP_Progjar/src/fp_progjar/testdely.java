/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_progjar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Raca
 */
public class testdely {    
    final String INET_ADDR = "224.0.0.0";
    final int PORT = 8889;
    final int JUMLAH_SOAL = 5;
    final int waktuSoal = 2;
    Connection con = null;
    ResultSet rs =  null;
    PreparedStatement ps = null;
    int userAktif = 1;
    ArrayList<String> kunci = new ArrayList<String>();
    
    private void ngambilkunci() {
        
        
            try
            {
                for(int j=1;j<=5;j++)
                {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fp_progjar", "root", "");
                    ps = con.prepareStatement("select kuncijawaban from kunci where nomor="+j);            
                    rs = ps.executeQuery();
                    rs.next();
                    kunci.add(rs.getString("kuncijawaban")); 
                    System.out.println("Kunci untuk nomer "+j+" "+rs.getString("kuncijawaban"));
                }
            }catch(Exception e)
            {
                System.out.println("Gagal ambil kunci:"+e);
            }
             
    }
    
    public static void main(String args[]) {
        testdely programm = new testdely();
        programm.ngambilkunci();
    }
}
