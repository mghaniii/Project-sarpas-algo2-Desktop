/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project.kel.desktop.sarpas.view;

import com.mycompany.project.kel.desktop.sarpas.model.User;
import com.mycompany.project.kel.desktop.sarpas.util.GlobalAppState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author AXIOO
 */
public class MainFrame extends JFrame {

    // Deklarasi semua panel utama
    private JPanel mainContentWrapper; // Panel pembungkus untuk header dan konten
    private HeaderPanel headerPanel;
    private SidebarPanel sidebarPanel;
    private JPanel contentPanel;       // Area untuk panel yang berganti-ganti
    private JPanel currentPanel;       // Panel yang sedang ditampilkan di contentPanel
    private LoginPanel loginPanel;

    public MainFrame() {
        System.out.println("MainFrame constructor dimulai.");

        // --- PENGATURAN DASAR JFRAME ---
        setTitle("Aplikasi Manajemen Sarana dan Prasarana Sekolah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. INISIALISASI SEMUA PANEL ---
        sidebarPanel = new SidebarPanel();
        sidebarPanel.setPreferredSize(new Dimension(200, 0)); // Atur lebar sidebar di sini
        headerPanel = new HeaderPanel();
        contentPanel = new JPanel(new BorderLayout());
        mainContentWrapper = new JPanel(new BorderLayout());

        // --- 2. SUSUN STRUKTUR PANEL PEMBUNGKUS (WRAPPER) ---
        // Masukkan header ke bagian ATAS wrapper
        mainContentWrapper.add(headerPanel, BorderLayout.NORTH);
        // Masukkan area konten ke bagian TENGAH wrapper
        mainContentWrapper.add(contentPanel, BorderLayout.CENTER);

        // --- 3. ATUR LISTENER UNTUK TOMBOL-TOMBOL SIDEBAR ---
        sidebarPanel.setButtonActionListener(e -> {
            String command = e.getActionCommand();
            System.out.println("MainFrame menerima klik dari Sidebar: " + command);
            
              System.out.println("DEBUG TOMBOL: Teks asli dari tombol adalah -> '" + command + "'");

            System.out.println("MainFrame menerima klik dari Sidebar: " + command); // Baris ini sudah ada

            switch (command.toLowerCase()) {
                case "logout":
                    headerPanel.clearHeader();
                    GlobalAppState.getInstance().logout();
                    showLoginPanel();
                    break;
                case "dashboard":
                    handleDashboardClick(); 
                    break;
                case "laporan kerusakan":
                    handleLaporanKerusakanClick();
                    break;
                case "inventaris barang":
                    handleInventarisBarangClick();
                    break;
                case "peminjaman fasilitas":
                    handlePeminjamanFasilitasClick();
                    break;
              case "jadwal perawatan": // Ini adalah teks dari btnSubJadwal
    showPanel(new JadwalPerawatanPanel()); // Panel untuk mengelola jadwal
    break;

case "riwayat perawatan": // Ini adalah teks dari btnSubRiwayat
    // Buat panel baru bernama RiwayatPerawatanPanel.java
    // Untuk sementara, kita bisa gunakan dummy panel
    showPanel(new RiwayatPerawatanPanel()); 
    break;
                case "profile":
                    handleProfileClick();
                    break;
                    // Di dalam switch-case di MainFrame.java
                   
                default:
                    showPanel(createDummyPanel("ERROR: Modul Tidak Dikenal - " + command));
                    break;
            }
        });

        // --- 4. TAMPILKAN LOGIN PANEL DI AWAL ---
        showLoginPanel();

        setVisible(true);
        System.out.println("MainFrame constructor selesai.");
    }

    // --- METODE UNTUK MENGGANTI KONTEN DI contentPanel ---
    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            contentPanel.remove(currentPanel);
        }
        currentPanel = panel;
        contentPanel.add(currentPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // --- METODE UNTUK MENAMPILKAN LOGIN PANEL ---
    public void showLoginPanel() {
        System.out.println("SLP_1. showLoginPanel() dimulai.");
        getContentPane().removeAll();
        loginPanel = new LoginPanel();
        loginPanel.setLoginSuccessListener(this::onLoginSuccess);
        add(loginPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        System.out.println("SLP_4. LoginPanel ditambahkan.");
    }

    // --- METODE YANG DIPANGGIL SETELAH LOGIN SUKSES ---
    public void onLoginSuccess() {
        System.out.println("ON_LOGIN_SUCCESS: Pengalihan setelah login.");
        getContentPane().removeAll(); // Hapus panel login

        // --- TAMBAHKAN KEMBALI PANEL DENGAN STRUKTUR YANG BENAR ---
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentWrapper, BorderLayout.CENTER);

        // Update tampilan sidebar dan header
        sidebarPanel.updateButtonVisibility();
        User currentUser = GlobalAppState.getInstance().getCurrentUser();
        if (currentUser != null) {
            headerPanel.setUserName("Hai, " + currentUser.getNamaLengkap());
            headerPanel.setWelcomeMessageByRole();
            
            // Tampilkan panel default setelah login
            if ("siswa".equalsIgnoreCase(currentUser.getRole())) {
                showPanel(new SiswaProfilePanel());
            } else {
                showPanel(new DashboardPanel());
            }
        }
        revalidate();
        repaint();
        System.out.println("ON_LOGIN_SUCCESS: Proses pengalihan selesai.");
    }

    // --- Metode-metode pembantu untuk menangani klik agar lebih rapi ---
    
      private void handleDashboardClick() {
        String role = GlobalAppState.getInstance().getCurrentUser().getRole();
        if ("siswa".equalsIgnoreCase(role)) {
            // Untuk siswa, "Dashboard" atau "Home" adalah halaman profil mereka
            showPanel(new SiswaDashboardPanel());
        } else {
            // Untuk admin, teknisi, dan guru, tampilkan dashboard utama
            showPanel(new DashboardPanel());
        }
    }
    
    private void handleLaporanKerusakanClick() {
        String role = GlobalAppState.getInstance().getCurrentUser().getRole();
        if ("admin".equalsIgnoreCase(role) || "teknisi".equalsIgnoreCase(role)) {
            showPanel(new LaporanKerusakanAdminPanel());
        } else {
            showPanel(new LaporanKerusakan1());
        }
    }

    private void handleInventarisBarangClick() {
        String role = GlobalAppState.getInstance().getCurrentUser().getRole();
        if ("admin".equalsIgnoreCase(role) || "teknisi".equalsIgnoreCase(role)) {
            showPanel(new InventarisAdminPanel());
        } else {
            showPanel(new InventarisSiswaPanel());
        }
    }

    private void handlePeminjamanFasilitasClick() {
        String role = GlobalAppState.getInstance().getCurrentUser().getRole();
        if ("admin".equalsIgnoreCase(role)) {
            showPanel(new RiwayatPeminjamanPanel());
        } else {
            showPanel(new PeminjamanPanel());
        }
    }

 // KODE BARU YANG BENAR
private void handleProfileClick() {
    User currentUser = GlobalAppState.getInstance().getCurrentUser();
    if (currentUser != null) {
        String role = currentUser.getRole();
        if ("siswa".equalsIgnoreCase(role) || "guru".equalsIgnoreCase(role)) {
            showPanel(new SiswaProfilePanel());
        } else {
            // INI PERBAIKANNYA: Panggil panel profil admin yang sudah Anda buat
            showPanel(new AdminProfilePanel1());
        }
    } else {
        showPanel(createDummyPanel("Akses Ditolak: Belum Login."));
    }
}

    // --- Metode dummy untuk panel sementara ---
    private JPanel createDummyPanel(String panelName) {
        JPanel dummyPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Ini adalah " + panelName + ".");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        dummyPanel.add(label, BorderLayout.CENTER);
        dummyPanel.setBackground(new Color(220, 220, 220));
        return dummyPanel;
    }

    // --- Metode main untuk menjalankan aplikasi ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
