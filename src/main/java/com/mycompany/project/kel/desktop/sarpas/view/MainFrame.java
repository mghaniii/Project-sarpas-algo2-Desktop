/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.project.kel.desktop.sarpas.view;

import javax.swing.JFrame; // Import kelas JFrame
import javax.swing.JPanel; // Import kelas JPanel
import javax.swing.SwingUtilities; // Penting untuk menjalankan Swing di EDT
// import javax.swing.JLabel; // HAPUS/KOMENTARI: JLabel tidak diperlukan lagi di MainFrame
import java.awt.BorderLayout; // Tetap diperlukan untuk layout manager
import javax.swing.JLabel;

public class MainFrame extends JFrame { 
// Jangan lupa pakai komentar (Ghani)
    private JPanel currentPanel; // Ini akan melacak panel yang sedang aktif

    public MainFrame() {
      
        System.out.println("MainFrame constructor dimulai.");

        // Set judul jendela
        setTitle("Aplikasi Manajemen sarana dan prasarana Sekolah");

        // Set operasi default saat tombol tutup diklik
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Aplikasi akan keluar saat jendela ditutup

        // Set ukuran jendela (lebar, tinggi)
        setSize(1000, 700); // Ukuran yang cukup besar untuk aplikasi inventaris

        // Posisikan jendela di tengah layar
        setLocationRelativeTo(null);

        // Atur layout manager untuk JFrame Anda (jika belum ada)
        setLayout(new BorderLayout()); // Pastikan JFrame menggunakan BorderLayout

        // --- PENTING: KEMBALIKAN PEMANGGILAN LoginPanel ---
        System.out.println("M_F_2. Memanggil showLoginPanel().");
        showLoginPanel(); // Panggil method untuk menampilkan LoginPanel

        // Pastikan jendela terlihat
        setVisible(true);

        // --- TAMBAHAN UNTUK DIAGNOSA TES ---
        System.out.println("MainFrame constructor selesai. setVisible(true) dipanggil.");
    }

    // Metode untuk menampilkan LoginPanel
    public void showLoginPanel() {
        System.out.println("SLP_1. showLoginPanel() dimulai.");
        if (currentPanel != null) {
            remove(currentPanel); // Hapus panel sebelumnya jika ada
            System.out.println("SLP_2. Panel sebelumnya dihapus.");
        }
        
        // Pastikan LoginPanel.java sudah ada di paket yang sama
        LoginPanel loginPanel = new LoginPanel(); 
        System.out.println("SLP_3. LoginPanel instance dibuat.");
        
        currentPanel = loginPanel;
        add(currentPanel, BorderLayout.CENTER); // Tambahkan ke frame
        revalidate(); // Penting untuk memperbarui layout
        repaint(); // Penting untuk menggambar ulang
        System.out.println("SLP_4. LoginPanel ditambahkan, revalidate/repaint.");
    }

    // --- Tambahkan metode showDashboardPanel() untuk navigasi setelah login ---
    public void showDashboardPanel() {
        System.out.println("SDP_1. showDashboardPanel() dimulai.");
        if (currentPanel != null) {
            remove(currentPanel); // Hapus panel login
            System.out.println("SDP_2. Panel login dihapus.");
        }
        // TODO: Buat kelas DashboardPanel.java di paket view
        // dan instansiasikan di sini setelah login berhasil
        JPanel dashboardPanel = new JPanel(); 
        dashboardPanel.add(new JLabel("Selamat datang di Dashboard Utama!")); // Contoh isi dashboard
        
        currentPanel = dashboardPanel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate(); 
        repaint();
        System.out.println("SDP_3. DashboardPanel ditambahkan.");
    }

 
    // Metode main untuk menjalankan aplikasi (opsional jika dipanggil dari ProjectKel6DesktopSarpas.java)
    // Biarkan saja jika Anda tidak ingin menghapusnya.
    public static void main(String[] args) {
        System.out.println("MainFrame.main() dipanggil. Menjalankan di EDT.");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(); // Buat instance dari MainFrame
            }
        });
        System.out.println("MainFrame.main() selesai.");
    }
}