/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.project.kel.desktop.sarpas.view;

import com.mycompany.project.kel.desktop.sarpas.util.GlobalAppState;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout; 
/**
 *
 * @author AXIOO
 */
public class HeaderPanel extends javax.swing.JPanel {

    /**
     * Creates new form HeaderPanel
     */
    public HeaderPanel() {
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

        jPanel1 = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblWelcomeMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblUserName.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblUserName.setText("jLabel1");

        lblWelcomeMessage.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        lblWelcomeMessage.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcomeMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(lblUserName)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUserName)
                        .addComponent(lblWelcomeMessage))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
public void setUserName(String name) {
    lblUserName.setText(name);
}


public void setWelcomeMessageByRole() {
    // Ambil role dari pengguna yang sedang login
    String role = GlobalAppState.getInstance().getCurrentUser().getRole();

    String message = ""; // Variabel untuk menyimpan pesan

  
    switch (role.toLowerCase()) {
        case "admin":
            message = "Dashboard Anda siap. Segera cek status aset, peminjaman, dan laporan terbaru.";
            break;
        case "teknisi":
            message = "Selamat datang, Teknisi. Silakan periksa daftar laporan kerusakan.";
            break;
        case "siswa":
            message = "Sebagai siswa Manfaatkan fasilitas Tadika Mesra yang tersedia dan laporkan jika ada kerusakan.";
            break;
        case "guru":
            message = "Selamat datang, Guru. Manfaatkan fasilitas  untuk mendukung kegiatan belajar.";
            break;
        default:
            message = "Selamat datang di Aplikasi Sarana dan Prasarana Sekolah.";
            break;
    }

    // Atur teks di JLabel dengan pesan yang sudah dipilih
    
    lblWelcomeMessage.setText("<html><div style='width:300px;'>" + message + "</div></html>");
}

public void clearHeader() {
    lblUserName.setText("");
    lblWelcomeMessage.setText("");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblWelcomeMessage;
    // End of variables declaration//GEN-END:variables
}
