/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.project.kel.desktop.sarpas.view;

import com.mycompany.project.kel.desktop.sarpas.dao.LaporanKerusakanDAO;
import com.mycompany.project.kel.desktop.sarpas.dao.PeminjamanDAO;
import com.mycompany.project.kel.desktop.sarpas.model.LaporanKerusakan;
import com.mycompany.project.kel.desktop.sarpas.model.Peminjaman;
import com.mycompany.project.kel.desktop.sarpas.model.User;
import com.mycompany.project.kel.desktop.sarpas.util.GlobalAppState;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author AXIOO
 */
public class SiswaDashboardPanel extends javax.swing.JPanel {
 private User currentUser;
    private PeminjamanDAO peminjamanDAO;
    private LaporanKerusakanDAO laporanDAO;
    /**
     * Creates new form SiswaDashboardPanel
     */
     public SiswaDashboardPanel() {
        initComponents();
        
        this.currentUser = GlobalAppState.getInstance().getCurrentUser();
        this.peminjamanDAO = new PeminjamanDAO();
        this.laporanDAO = new LaporanKerusakanDAO();
        
        setWelcomeMessage();
        loadStatusPeminjaman();
        loadStatusLaporan();
        setupActionListeners();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSalamSiswa = new javax.swing.JLabel();
        panelAksi = new javax.swing.JPanel();
        btnAjukanPeminjaman = new com.mycompany.project.kel.desktop.sarpas.view.RoundedButton();
        btnBuatLaporan = new com.mycompany.project.kel.desktop.sarpas.view.RoundedButton();
        panelStatus = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStatusPeminjaman = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStatusLaporan = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lblSalamSiswa.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        lblSalamSiswa.setText("jLabel1");

        panelAksi.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnAjukanPeminjaman.setBackground(new java.awt.Color(0, 153, 204));
        btnAjukanPeminjaman.setForeground(new java.awt.Color(255, 255, 255));
        btnAjukanPeminjaman.setText("Ajukan Peminjaman");
        btnAjukanPeminjaman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelAksi.add(btnAjukanPeminjaman);

        btnBuatLaporan.setBackground(new java.awt.Color(0, 153, 204));
        btnBuatLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnBuatLaporan.setText("Ajukan Laporan Kerusakan");
        btnBuatLaporan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnBuatLaporan.setPreferredSize(new java.awt.Dimension(120, 25));
        panelAksi.add(btnBuatLaporan);

        panelStatus.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        tblStatusPeminjaman.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tblStatusPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fasilitas", "Tanggal Pinjam", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblStatusPeminjaman);

        panelStatus.add(jScrollPane1);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 200));

        tblStatusLaporan.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tblStatusLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Barang", "Tanggal Lapor", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblStatusLaporan);

        panelStatus.add(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelAksi, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(lblSalamSiswa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblSalamSiswa)
                .addGap(29, 29, 29)
                .addComponent(panelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void setWelcomeMessage() {
        if (currentUser != null) {
            lblSalamSiswa.setText("Selamat Datang, " + currentUser.getNamaLengkap() + "!"+ "Ini adalah status Peminjaman dan status Laporan Kerusakan");
        }
    }
  
  
     private void loadStatusPeminjaman() {
        if (currentUser == null) return;
        DefaultTableModel model = (DefaultTableModel) tblStatusPeminjaman.getModel();
        model.setRowCount(0);
        List<Peminjaman> daftarPeminjaman = peminjamanDAO.getPeminjamanByUserId(currentUser.getIdUsers());
        for (Peminjaman p : daftarPeminjaman) {
            model.addRow(new Object[]{
                p.getNamaFasilitasManual(),
                p.getTanggalPeminjaman(),
                p.getStatusPeminjaman()
            });
        }
    }
     
         private void loadStatusLaporan() {
        if (currentUser == null) return;
        DefaultTableModel model = (DefaultTableModel) tblStatusLaporan.getModel();
        model.setRowCount(0);
       // Kode Baru
// Kode Lama
List<LaporanKerusakan> daftarLaporan = laporanDAO.getLaporanByNamaPelapor(currentUser.getNamaLengkap());
        for (LaporanKerusakan l : daftarLaporan) {
            model.addRow(new Object[]{
                l.getNamaBarang(),
                l.getTanggalPelaporan(),
                l.getStatusLaporan()
            });
        }
    }
         
             private void setupActionListeners() {
        btnAjukanPeminjaman.addActionListener(e -> {
          
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            if (mainFrame != null) {
                mainFrame.showPanel(new PeminjamanPanel());
            }
        });
        
        btnBuatLaporan.addActionListener(e -> {
            
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            if (mainFrame != null) {
                mainFrame.showPanel(new LaporanKerusakan1());
            }
        });
    }
         

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjukanPeminjaman;
    private javax.swing.JButton btnBuatLaporan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSalamSiswa;
    private javax.swing.JPanel panelAksi;
    private javax.swing.JPanel panelStatus;
    private javax.swing.JTable tblStatusLaporan;
    private javax.swing.JTable tblStatusPeminjaman;
    // End of variables declaration//GEN-END:variables
}
