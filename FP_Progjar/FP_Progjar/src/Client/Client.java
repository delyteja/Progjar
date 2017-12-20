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
    final int waktuSoal = 2;
    JawabanData _jawabans = null;
      
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
            
            for(int i=waktuSoal; i>=0; i--)
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
            
            if(jawaban.getSelection() == null) {
                _jawabans.addJawaban("z");
            } else {
                _jawabans.addJawaban(jawaban.getSelection().getActionCommand());
            }
        }
    }
    
    private void kirimJawaban() {
        sendObjectTo(_jawabans, INET_ADDR,PORT);
        System.out.println(_jawabans.getIdUser());
        for(String jawabanUser : _jawabans.getJawabans() ) {
            System.out.println(jawabanUser);
        }
        System.out.println("Jawaban sent.");
    }
    
    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
    }
    
    public Client(String user) {
        this();
        this._jawabans = new JawabanData(user, new ArrayList<String>());
        System.out.println("ini");
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jawaban");
        jLabel1.setOpaque(true);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pertanyaan");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("KUIS ONLINE");

        waktu.setBackground(new java.awt.Color(255, 255, 255));
        waktu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waktu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        waktu.setOpaque(true);
        waktu.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                waktuComponentAdded(evt);
            }
        });

        mulai.setText("Mulai Test");
        mulai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulaiActionPerformed(evt);
            }
        });

        jawaban.add(j1);
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });

        jawaban.add(j2);

        jawaban.add(j3);

        soal.setBackground(new java.awt.Color(255, 255, 255));
        soal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        soal.setAutoscrolls(true);
        soal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        soal.setOpaque(true);

        jawaban.add(j4);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(177, 177, 177))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(j1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(j2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(j3))
                            .addComponent(j4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(backgroundLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mulai))
                                .addComponent(soal, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(soal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mulai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(j1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(j2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(j3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(j4)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
               Nilai n = new Nilai(_jawabans.getNilai());
               n.setVisible(true);
               System.out.println("nilai kamu "+_jawabans.getNilai());
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
