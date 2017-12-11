/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_progjar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.sql.*;


/**
 *
 * @author DELY
 */
public class MulticastSocketServer{
    final static int PORT = 8889;
    final static String INET_ADDR = "224.0.0.0";
        
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        // Get the address that we are going to connect to.
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        String url="jdbc:mysql://localhost:3306/fp_progjar";
        String userid="root";
        String password="";
        Statement st = null;
        ResultSet rs =null;
        String tanya = "";
        int nomor=0;
      
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
            try
            {
                Connection con = DriverManager.getConnection(url, userid, password);
                st = con.createStatement();
                for(int i=1;i<6;i++)
                {  
                    String sql="select * from soal where nomor="+String.valueOf(i);
                    rs=st.executeQuery(sql);
                    if(rs.next())
                    {
                        nomor = rs.getInt("nomor");
                        tanya = rs.getString("soal");
                    }
                  DatagramPacket msgPacket = new DatagramPacket(tanya.getBytes(),tanya.getBytes().length, addr, PORT);
                  serverSocket.send(msgPacket); 
                  Thread.sleep(5000);
                }
                
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
