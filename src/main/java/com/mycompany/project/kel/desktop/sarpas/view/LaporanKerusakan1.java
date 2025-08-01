/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.project.kel.desktop.sarpas.view;
import com.mycompany.project.kel.desktop.sarpas.dao.LaporanKerusakanDAO;
import com.mycompany.project.kel.desktop.sarpas.model.LaporanKerusakan;
import com.mycompany.project.kel.desktop.sarpas.model.User; 
import com.mycompany.project.kel.desktop.sarpas.util.GlobalAppState; 
import com.mycompany.project.kel.desktop.sarpas.dao.BarangDAO; 
import com.mycompany.project.kel.desktop.sarpas.model.Barang;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

/**
 *
 * @author AXIOO
 */
public class LaporanKerusakan1 extends javax.swing.JPanel {
  private LaporanKerusakanDAO laporanDAO;

    
    
    
    private File selectedFile;
    
    /**
     * Creates new form LaporanKerusakan
     */
       public LaporanKerusakan1() {
        initComponents(); // Dihasilkan oleh GUI Builder
        
        System.out.println("DEBUG - LaporanKerusakanPanel (Form Only): Constructor dimulai."); 
        
        this.laporanDAO = new LaporanKerusakanDAO();

       
        // tableModel = (DefaultTableModel) tblLaporan.getModel(); 
        // System.out.println("DEBUG - LaporanKerusakanPanel: tableModel berhasil dihubungkan. Jumlah kolom: " + tableModel.getColumnCount());

        setupPelaporInfo(); // Isi nama pelapor otomatis dan tanggal
        // >>> loadLaporanData() DIHAPUS KARENA TIDAK ADA TABEL <<<
        // loadLaporanData(); 
        
        // Listener Tombol Laporkan
        if (btnLaporkan != null) {
            btnLaporkan.addActionListener(e -> submitLaporan());
        }
        // Listener Tombol Bersihkan
        if (btnClearForm != null) {
            btnClearForm.addActionListener(e -> clearForm());
        }
        
      if (btnPilihFoto != null) {
        btnPilihFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                // ... (pengaturan fileChooser) ...

                int returnValue = fileChooser.showOpenDialog(LaporanKerusakan1.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();

                   
                    long fileSizeInBytes = selectedFile.length(); 
                    long maxFileSizeInBytes = 2 * 1024 * 1024; // 2 MB = 2 * 1024 KB * 1024 Bytes

                    if (fileSizeInBytes > maxFileSizeInBytes) {
                        JOptionPane.showMessageDialog(LaporanKerusakan1.this,
                            "Ukuran file terlalu besar! Maksimal 2 MB.",
                            "Ukuran File Tidak Valid",
                            JOptionPane.WARNING_MESSAGE);
                        selectedFile = null; // Reset file yang dipilih
                        if (txtFotoPath != null) {
                            txtFotoPath.setText(""); // Bersihkan path
                        }
                        System.err.println("DEBUG: Ukuran file " + fileSizeInBytes + " bytes, melebihi batas 2MB.");
                        return; // Hentikan proses jika file terlalu besar
                    }
                   

                    if (txtFotoPath != null) {
                        txtFotoPath.setText(selectedFile.getAbsolutePath()); 
                    }
                    System.out.println("DEBUG: File foto dipilih: " + selectedFile.getAbsolutePath() + ", Ukuran: " + fileSizeInBytes + " bytes.");
                }
            }
        });
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNamaPelapor = new javax.swing.JTextField();
        txtTanggalPelaporan = new javax.swing.JTextField();
        txtKodeBarangRusak = new javax.swing.JTextField();
        txtLokasiAlat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDeskripsiKerusakan = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnLaporkan = new com.mycompany.project.kel.desktop.sarpas.view.RoundedButton();
        btnPilihFoto = new javax.swing.JButton();
        txtFotoPath = new javax.swing.JTextField();
        btnClearForm = new com.mycompany.project.kel.desktop.sarpas.view.RoundedButton();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Nama  Pelapor");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("Tanggal pelaporan");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("Kode Barang");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setText("Lokasi Alat");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setText("Deskripsi Kerusakan");

        txtNamaPelapor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTanggalPelaporan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtKodeBarangRusak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtLokasiAlat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taDeskripsiKerusakan.setColumns(20);
        taDeskripsiKerusakan.setRows(5);
        taDeskripsiKerusakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(taDeskripsiKerusakan);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel6.setText("Upload Foto Kerusakan");

        btnLaporkan.setBackground(new java.awt.Color(0, 153, 204));
        btnLaporkan.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnLaporkan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporkan.setText("Laporkan");
        btnLaporkan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLaporkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporkanActionPerformed(evt);
            }
        });

        btnPilihFoto.setBackground(new java.awt.Color(0, 153, 204));
        btnPilihFoto.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnPilihFoto.setForeground(new java.awt.Color(255, 255, 255));
        btnPilihFoto.setText("Pilih foto");

        txtFotoPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFotoPathActionPerformed(evt);
            }
        });

        btnClearForm.setBackground(new java.awt.Color(0, 153, 204));
        btnClearForm.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnClearForm.setForeground(new java.awt.Color(255, 255, 255));
        btnClearForm.setText("Clear Form");
        btnClearForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("max 2mb");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(txtNamaPelapor)
                            .addComponent(txtTanggalPelaporan)
                            .addComponent(txtKodeBarangRusak)
                            .addComponent(txtLokasiAlat)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtFotoPath, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClearForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPilihFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(btnLaporkan, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtNamaPelapor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggalPelaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKodeBarangRusak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLokasiAlat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnPilihFoto)
                    .addComponent(txtFotoPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaporkan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaporkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporkanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLaporkanActionPerformed

    private void txtFotoPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFotoPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFotoPathActionPerformed

    
    private void setupPelaporInfo() {
        User currentUser = GlobalAppState.getInstance().getCurrentUser();
        if (currentUser != null && txtNamaPelapor != null) {
            txtNamaPelapor.setText(currentUser.getNamaLengkap());
        } else if (txtNamaPelapor != null) {
            txtNamaPelapor.setText("Tidak Diketahui (Login Gagal?)");
        }
        
        if (txtTanggalPelaporan != null) {
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            txtTanggalPelaporan.setText(now.format(formatter));
        }
    }

    // Metode untuk submit laporan
  private void submitLaporan() {
    try {
        // ... (Validasi Input Form) ...
        // UBAH VALIDASI INI
        if (txtKodeBarangRusak.getText().trim().isEmpty() || // <<< UBAH INI
            txtLokasiAlat.getText().trim().isEmpty() ||
            taDeskripsiKerusakan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ambil data dari form UI
        String kodeBarang = txtKodeBarangRusak.getText().trim(); 

       
        BarangDAO barangDAO = new BarangDAO(); // Inisialisasi BarangDAO
        Barang barangRusak = barangDAO.getBarangByKode(kodeBarang); 

        if (barangRusak == null) {
            JOptionPane.showMessageDialog(this, "Kode Barang tidak ditemukan. Mohon masukkan kode yang benar.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idBarang = barangRusak.getIdBarang(); // Dapatkan ID Barang dari objek Barang

        // ... (Ambil data lainnya) ...
        String namaPelapor = txtNamaPelapor.getText().trim();
        String jenisKerusakan = "Tidak Diketahui"; // Atau sesuaikan
        String lokasiAlat = txtLokasiAlat.getText().trim();
        String deskripsi = taDeskripsiKerusakan.getText().trim();

        String urlFoto = null;
       
 
            if (selectedFile != null) {
               
                urlFoto = selectedFile.getAbsolutePath();
            }
           

        // Buat objek LaporanKerusakan
        LaporanKerusakan newLaporan = new LaporanKerusakan(
            idBarang, namaPelapor, jenisKerusakan, lokasiAlat, deskripsi, urlFoto
        );

     
        if (laporanDAO.addLaporanKerusakan(newLaporan)) {
            JOptionPane.showMessageDialog(this, "Laporan berhasil dikirim!");
            // loadLaporanData(); // Jika tabel ada di panel ini, panggil ini
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengirim laporan. Cek konsol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) { // Ini tidak akan terpanggil jika ID Barang sudah tidak di-parse
        JOptionPane.showMessageDialog(this, "Error dalam konversi angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    // Metode untuk membersihkan form (akan dipanggil oleh tombol Bersihkan)
    private void clearForm() {
    txtNamaPelapor.setText("");
    txtKodeBarangRusak.setText(""); // <<< UBAH INI
    
    txtLokasiAlat.setText("");
    taDeskripsiKerusakan.setText("");
        if (txtTanggalPelaporan != null) { 
             java.time.LocalDateTime now = java.time.LocalDateTime.now();
             java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
             txtTanggalPelaporan.setText(now.format(formatter));
        }
        if (txtFotoPath != null) txtFotoPath.setText("");
        selectedFile = null; 
    }
    
  
    


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnLaporkan;
    private javax.swing.JButton btnPilihFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taDeskripsiKerusakan;
    private javax.swing.JTextField txtFotoPath;
    private javax.swing.JTextField txtKodeBarangRusak;
    private javax.swing.JTextField txtLokasiAlat;
    private javax.swing.JTextField txtNamaPelapor;
    private javax.swing.JTextField txtTanggalPelaporan;
    // End of variables declaration//GEN-END:variables
}

