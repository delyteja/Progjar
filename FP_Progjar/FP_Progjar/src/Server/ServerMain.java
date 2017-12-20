/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import Client.Client;
import Data.*;
import Server.UserAktif;
import Server.Rank;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author DELY
 */
public class ServerMain extends javax.swing.JFrame {
    final String INET_ADDR = "224.0.0.0";
    final int PORT = 8889;
    final int JUMLAH_SOAL = 5;
    final int waktuSoal = 2;
     Connection con = null;
     ResultSet rs =  null;
     PreparedStatement ps = null;
    int userAktif = 1;
    ArrayList<JawabanData> jawabanDatas = new ArrayList<JawabanData>();
    
      
    
    //mengirim objek
    private void sendObjectTo(Object o, String hostName, int desPort) {    
        try {      
            DatagramSocket dSock = new DatagramSocket();
            InetAddress address = InetAddress.getByName(hostName);
            ByteArrayOutputStream byteStream = new
            ByteArrayOutputStream(5000);
            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
            os.flush();
            os.writeObject(o);
            os.flush();
            //retrieves byte array
            byte[] sendBuf = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, address, desPort);
            int byteCount = packet.getLength();
            dSock.send(packet);
            os.close();
        }
        catch (UnknownHostException e) {
            System.err.println("Exception:  " + e);
            e.printStackTrace();    
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Object recvObjFrom(String hostName, int recvPort){    
        try {
          MulticastSocket clientSocket = new MulticastSocket(recvPort);
          InetAddress address = InetAddress.getByName(hostName);
          clientSocket.joinGroup(address);
          byte[] recvBuf = new byte[5000];
          DatagramPacket packet = new DatagramPacket(recvBuf,recvBuf.length);
          clientSocket.receive(packet);
          int byteCount = packet.getLength();
          ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
          ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
          Object o = is.readObject();
          is.close();
          return(o);
        }
        catch (IOException e) {
          System.err.println("Exception:  " + e);
          e.printStackTrace();
        }
        catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
        }
        return(null);  
    }
    
    private void menerimaJawaban() {
        int jawabanDiterima = 0;
        
        while(jawabanDiterima<userAktif) {
            System.out.println("Receiving jawaban..");
            JawabanData jawabanTemp = (JawabanData) recvObjFrom(INET_ADDR,PORT);
            jawabanDatas.add(jawabanTemp);
            jawabanDiterima++;
            System.out.println("Jawaban received "+jawabanDiterima);
            System.out.println(jawabanTemp.getIdUser());
            for(String jawabanUser : jawabanTemp.getJawabans() ) {
                System.out.println(jawabanUser);
            }
        }
    }
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
//                System.out.println("Kunci untuk nomer "+j+" "+rs.getString("kuncijawaban"));
            }
        }catch(Exception e)
        {
            System.out.println("Gagal ambil kunci:"+e);
        }     
    }
    
    private void koreksi() {
        int skor=0;
        for(int i=0;i<userAktif;i++)
        {
            for(int j=0;j<5;j++)
            {
//                System.out.println(jawabanDatas.get(i).getJawabans().get(j));
//                System.out.println(kunci.get(j));
                if(jawabanDatas.get(i).getJawabans().get(j).equals(kunci.get(j)))
                {
                    skor++;
                }
                
            }
            jawabanDatas.get(i).setNilai(skor*20);
            System.out.println(jawabanDatas.get(i).getIdUser()+" = "+jawabanDatas.get(i).getNilai());
        }
    }
    /**
     * Creates new form ServerMain
     */
    public ServerMain() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mulaibutton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mulaibutton.setText("Mulai Tes");
        mulaibutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulaibuttonActionPerformed(evt);
            }
        });

        jButton1.setText("User Aktif");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Peringkat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Server Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton1)
                        .addGap(98, 98, 98)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(mulaibutton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(mulaibutton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        UserAktif aktif = new UserAktif();
        aktif.setVisible(true);
//        aktif.dispatchEvent(new WindowEvent(aktif, WindowEvent.WINDOW_CLOSING));
        aktif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        Rank rank = new Rank();
        rank.setVisible(true);
  //      rank.dispatchEvent(new WindowEvent(rank, WindowEvent.WINDOW_CLOSING));
        rank.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void mulaibuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mulaibuttonActionPerformed
        // TODO add your handling code here:
        ArrayList <SoalData> soals = new ArrayList<SoalData>();
        try {   
            for(int i=1;i<=JUMLAH_SOAL;i++)
            {   
                ArrayList<String> jawaban = new ArrayList<String>();
                String soalnya;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fp_progjar", "root", "");
                ps = con.prepareStatement("select * from soal2 where nomor="+String.valueOf(i));            
                ResultSet rs = ps.executeQuery();
                String temp;
                if(rs.next())
                {
                    soalnya = rs.getString("soal");
                    temp = rs.getString("jawaban1");
//                    System.out.println(temp);
                    jawaban.add(temp);
                    temp = rs.getString("jawaban2");
                    jawaban.add(temp);
                    temp = rs.getString("jawaban4");
                    jawaban.add(temp);
                    temp = rs.getString("jawaban3");
                    jawaban.add(temp);
                    
                 //   System.out.println(soalnya);
                    soals.add(new SoalData(soalnya,jawaban,i));
//                System.out.println(rs.getString("jawaban1"));    
                }
                    
           }
            
        } catch(Exception e) {
            System.out.println(e);
        }       
      
//        soals.add(new SoalData("test"));
        
        for(SoalData soal : soals) {
            //kirim soal
            System.out.println(soal.getSoal());
            sendObjectTo(soal,INET_ADDR,PORT);
            System.out.println("Sending done.");
            
            
            //nunggu 10 detik
            try {
                Thread.sleep( (waktuSoal*1000)+3000 );
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Done Sending.");
        //memerintahkan kalo soal udah habis
        SoalData soalTemp = new SoalData("",null,-1);
        sendObjectTo(soalTemp,INET_ADDR,PORT);
        
        menerimaJawaban();
        ngambilkunci();
        koreksi();
    }//GEN-LAST:event_mulaibuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new ServerMain().setVisible(true);
            }
           // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mulaibutton;
    // End of variables declaration//GEN-END:variables
}
