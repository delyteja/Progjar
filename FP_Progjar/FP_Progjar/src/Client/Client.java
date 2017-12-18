/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Data.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.sql.*;

/**
 *
 * @author DELY
 */
public class Client extends javax.swing.JFrame {
    final String INET_ADDR = "224.0.0.0";
    final int PORT = 8889;
    SoalData _soal = null;
    JawabanData _jawabans;
      
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
          ObjectInputStream is = new
               ObjectInputStream(new BufferedInputStream(byteStream));
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
    
    private void terimaSoal() {
         while(true) {
            System.out.println("Receiving soal...");
            _soal = (SoalData) recvObjFrom(INET_ADDR,PORT);
            System.out.println("Soal received...\n"+_soal.getSoal());

            if(_soal.getNomerSoal() == -1) break;

            soal.setText(_soal.getSoal());

            ArrayList<String> jawabans = _soal.getJawabans();
            j1.setText(jawabans.get(0));
            j2.setText(jawabans.get(1));
            j3.setText(jawabans.get(2));
            j4.setText(jawabans.get(3));

            for(int i= _soal.getWaktu();i>=0;i--)
            {
                waktu.setText(String.valueOf(i));
                try {
                    Thread.sleep(1000);
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            waktu.setText("");
            soal.setText("");
        }
    }
    
    private void kirimJawaban() {
        sendObjectTo(jawaban, INET_ADDR,PORT);
        System.out.println("Jawaban sent.");
    }
    
    /**
     * Creates new form Client
     */
    public Client() {
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

        jawaban = new javax.swing.ButtonGroup();
        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        waktu = new javax.swing.JLabel();
        mulai = new javax.swing.JButton();
        j1 = new javax.swing.JRadioButton();
        j2 = new javax.swing.JRadioButton();
        j3 = new javax.swing.JRadioButton();
        soal = new javax.swing.JLabel();
        j4 = new javax.swing.JRadioButton();

        jawaban.add(j1);
        jawaban.add(j2);
        jawaban.add(j3);
        jawaban.add(j4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(1, 93, 168));

        jLabel1.setBackground(new java.awt.Color(153, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jawaban");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(153, 255, 255));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pertanyaan");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("KUIS ONLINE");

        waktu.setBackground(new java.awt.Color(153, 255, 255));
        waktu.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waktu.setOpaque(true);
        waktu.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                waktuComponentAdded(evt);
            }
        });

        mulai.setBackground(new java.awt.Color(153, 255, 255));
        mulai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        mulai.setText("Mulai Test");
        mulai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulaiActionPerformed(evt);
            }
        });

        j1.setBackground(new java.awt.Color(255, 255, 255));
        jawaban.add(j1);
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });

        j2.setBackground(new java.awt.Color(255, 255, 255));
        jawaban.add(j2);

        j3.setBackground(new java.awt.Color(255, 255, 255));
        jawaban.add(j3);

        soal.setBackground(new java.awt.Color(153, 255, 255));
        soal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        soal.setAutoscrolls(true);
        soal.setOpaque(true);

        jawaban.add(j4);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mulai)
                        .addGap(93, 93, 93)))
                .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(soal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(j1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(j2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(j3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(j4, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGap(82, 82, 82))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(116, 116, 116))))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(mulai)
                        .addGap(104, 104, 104)))
                .addGap(39, 39, 39)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(j1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(j2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(j3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(j4))
                    .addComponent(soal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void waktuComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_waktuComponentAdded

    // TODO add your handling code here:
    }//GEN-LAST:event_waktuComponentAdded
        
    private void mulaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mulaiActionPerformed
      j1.setActionCommand("A");
      j2.setActionCommand("B");
      j3.setActionCommand("C");
      j4.setActionCommand("D");
      
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
               terimaSoal();
               kirimJawaban();
            }
        });
        t.start();
        
                // TODO add your handling code here:
        
    }//GEN-LAST:event_mulaiActionPerformed

    private void j1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j1ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
                
            }
        });
        
        
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JRadioButton j1;
    private javax.swing.JRadioButton j2;
    private javax.swing.JRadioButton j3;
    private javax.swing.JRadioButton j4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.ButtonGroup jawaban;
    private javax.swing.JButton mulai;
    private javax.swing.JLabel soal;
    private javax.swing.JLabel waktu;
    // End of variables declaration//GEN-END:variables
    

    public class Function
    {
        Connection con = null;
        ResultSet rs =  null;
        String nomor,jawabannya;
        PreparedStatement ps = null;
        public Function(int tkn)
        {
            this.nomor=String.valueOf(tkn);
        }
        public ResultSet find(String s)
        {
            try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fp_progjar", "root", "");
            ps = con.prepareStatement("select * from soal where nomor = " + nomor );
            rs = ps.executeQuery();
            }
            catch (Exception e)
            {
                System.out.println("\n Error :" +e);
            }
            return rs;
        }
        public void InserttoDB(String jawaban )
        {   this.jawabannya=jawaban;
            try
            {   System.out.println(jawaban);
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fp_progjar", "root", "");
                ps = con.prepareStatement("insert into jawaban(NOMOR,jawaban) values('"+nomor+"','"+jawabannya+"')");
                        //+ "("+nomor+','+jawabannya+")");
                ps.executeUpdate();
            }
            catch(Exception e)
            {
                System.out.println("\n Error :" +e);
               
            }
            
        }
        
    } 
    
}
