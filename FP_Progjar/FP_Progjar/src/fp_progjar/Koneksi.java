/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_progjar;

import java.util.*;
import java.sql.*;
/**
 *
 * @author DELY
 */
public class Koneksi {
    public static void main(String[] args)
    {
        String url="jdbc:mysql://localhost:3306/fp_progjar";
        String userid="root";
        String password="";
        Statement st = null;
        ResultSet rs =null;
        String tanya = "";
        int nomor=0;
        try
        {
          /*  Scanner sc = new Scanner(System.in);
            System.out.println("Masukkan nama : ");
            String name = sc.next();
            String query= "insert into soal values(1,'Siapakah soal')";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userid, password);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
            System.out.println("Data masuk");
                  */
            Connection con = DriverManager.getConnection(url, userid, password);
            st = con.createStatement();
            String sql="select * from soal2 where nomor=1";
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                nomor = rs.getInt("nomor");
                tanya = rs.getString("soal");
            }
            System.out.println(String.valueOf(nomor)+ tanya+rs.getString("jawaban1")+rs.getString("jawaban2")+rs.getString("jawaban3"));
        }
        catch(Exception e)
        {
                    System.out.println("\n Error :" +e);
        }
    }
}
