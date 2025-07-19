/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.project.kel.desktop.sarpas.view;
import com.mycompany.project.kel.desktop.sarpas.view.LaporanKerusakan1;
import com.mycompany.project.kel.desktop.sarpas.model.User; // Untuk mengakses objek User
import com.mycompany.project.kel.desktop.sarpas.util.GlobalAppState; // Untuk status user login
import com.mycompany.project.kel.desktop.sarpas.view.SiswaProfilePanel; // Tambahkan ini
   
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton; // Untuk tombol di sidebar (digunakan di addSidebarButton)
import javax.swing.JLabel; // Untuk label di dummy panel/dashboard
import javax.swing.JOptionPane; // Untuk pesan pop-up

import java.awt.BorderLayout; // Layout utama JFrame
import java.awt.Color;       // Untuk warna
import java.awt.Dimension;   // Untuk ukuran panel/komponen
import java.awt.Font;        // Untuk font
import java.awt.FlowLayout;    // Untuk layout tombol di sidebar

import javax.swing.SwingUtilities; // Untuk EDT dan getWindowAncestor


public class MainFrame extends JFrame {

    private JPanel currentPanel;       // Panel yang sedang ditampilkan di contentPanel
    private SidebarPanel sidebarPanel; // Panel untuk sidebar (kiri)
    private JPanel contentPanel;       // Panel untuk area konten utama (tengah/kanan)
    private LoginPanel loginPanel;     // Deklarasi LoginPanel

    public MainFrame() {
        System.out.println("MainFrame constructor dimulai.");

        // --- PENGATURAN DASAR JFrame ---
        setTitle("Aplikasi Manajemen Sarana dan Prasarana Sekolah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Ukuran jendela lebih besar untuk sidebar dan konten
        setLocationRelativeTo(null); // Posisikan jendela di tengah layar

        setLayout(new BorderLayout()); // PENTING: Set layout JFrame di awal

        // --- 1. Inisialisasi dan Siapkan Sidebar (SidebarPanel) ---
        sidebarPanel = new SidebarPanel(); // Buat instance SidebarPanel
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight())); // Atur lebar sidebar
        // Warna background dan border diatur di dalam SidebarPanel.java

        // Set ActionListener untuk tombol-tombol di SidebarPanel
        sidebarPanel.setButtonActionListener(e -> {
            String command = e.getActionCommand(); // Dapatkan teks tombol yang diklik
            System.out.println("MainFrame menerima klik dari Sidebar: " + command);
            // Logika switch untuk menampilkan panel yang berbeda
            switch (command.toLowerCase()) { // Gunakan toLowerCase untuk perbandingan case-insensitive
                
                
                case "dashboard":
                    showPanel(new DashboardPanel());
                    System.out.println("DEBUG Sidebar Action: Menampilkan DashboardPanel.");
                    break;
                 case "laporan kerusakan":
   
            try {
                showPanel(new LaporanKerusakan1()); // <-- Ini akan memanggil kelas yang sudah direname
                System.out.println("DEBUG Sidebar Action: Menampilkan Laporan Kerusakan.");
            } catch (Exception e_inner) {
                System.err.println("ERROR: Gagal menginisialisasi LaporanKerusakanPanel! " + e_inner.getMessage());
                e_inner.printStackTrace();
                showPanel(createDummyPanel("ERROR: Panel Laporan Kerusakan Gagal Dimuat."));
            }
            break;       
                    
                    
                case "inventaris barang":
                    // Menggunakan logika pemisahan file Admin/Siswa
                    String currentRole = GlobalAppState.getInstance().getCurrentUser() != null ? GlobalAppState.getInstance().getCurrentUser().getRole() : "NULL_ROLE";
                    try {
                        System.out.println("DEBUG Sidebar Action: Inside Inventaris Barang case.");
                        System.out.println("DEBUG Sidebar Action: currentRole for Inventaris check = " + currentRole);
                        if ("admin".equalsIgnoreCase(currentRole) || "teknisi".equalsIgnoreCase(currentRole)) {
                            // showPanel(new InventarisAdminPanel()); // Panggil jika sudah ada
                            showPanel(createDummyPanel("Inventaris Admin (Belum Dibuat)"));
                            System.out.println("DEBUG Sidebar Action: Mengarahkan ke Inventaris Admin Dummy.");
                        } else if ("siswa".equalsIgnoreCase(currentRole) || "guru".equalsIgnoreCase(currentRole)) {
                            showPanel(new InventarisSiswaPanel()); // Ini akan dipanggil untuk Siswa/Guru
                            System.out.println("DEBUG - Sidebar: InventarisSiswaPanel diklik dan diinisialisasi.");
                        } else {
                            showPanel(createDummyPanel("Akses Inventaris Ditolak"));
                            System.out.println("DEBUG Sidebar Action: Mengarahkan ke Akses Inventaris Ditolak.");
                        }
                    } catch (Exception e_inner) {
                        System.err.println("ERROR: Gagal menginisialisasi InventarisSiswaPanel dari sidebar! " + e_inner.getMessage());
                        e_inner.printStackTrace();
                        showPanel(createDummyPanel("ERROR: Panel Inventaris Gagal Dimuat."));
                    }
                    System.out.println("DEBUG Sidebar Action: Inventaris Barang case selesai.");
                    break;
                case "peminjaman fasilitas":
                    showPanel(new PeminjamanPanel());
                    System.out.println("DEBUG Sidebar Action: Menampilkan Peminjaman Fasilitas.");
                    break;
                case "pengaturan": // Asumsi tombol ini ada di SidebarPanel
                    showPanel(createDummyPanel("Pengaturan Aplikasi"));
                    System.out.println("DEBUG Sidebar Action: Menampilkan Pengaturan.");
                    break;
                    
                    case "profile": // Tambahkan case ini Profile
        User currentUser = GlobalAppState.getInstance().getCurrentUser();
        if (currentUser != null) {
            String role = currentUser.getRole();
            // Asumsi semua peran bisa melihat halaman profilnya sendiri
            // Anda bisa membuat panel profil khusus untuk Admin/Guru/Teknisi juga
            if ("siswa".equalsIgnoreCase(role) || "guru".equalsIgnoreCase(role)) {
                showPanel(new SiswaProfilePanel()); // Jika siswa/guru, tampilkan SiswaProfilePanel
            } else {
                // TODO: Buat AdminProfilePanel, GuruProfilePanel, TeknisiProfilePanel
                showPanel(createDummyPanel("Profil " + role + " (Belum Dibuat)")); // Tampilkan dummy jika tidak ada panel khusus
            }
        } else {
            showPanel(createDummyPanel("Akses Ditolak: Belum Login."));
        }
        System.out.println("DEBUG Sidebar Action: Menampilkan Profil.");
        break;
                default:
                    showPanel(createDummyPanel("ERROR: Modul Tidak Dikenal - " + command));
                    System.out.println("DEBUG Sidebar Action: Modul Tidak Dikenal.");
                    break;
            }
        });
        add(sidebarPanel, BorderLayout.WEST); // Tambahkan SidebarPanel ke JFrame

        // --- 2. Inisialisasi dan Siapkan Area Konten Utama ---
        contentPanel = new JPanel(); // Ini adalah area tempat modul-modul akan ditampilkan
        contentPanel.setLayout(new BorderLayout()); // Konten area juga pakai BorderLayout
        contentPanel.setBackground(new Color(240, 240, 240)); // Warna latar belakang area konten
        add(contentPanel, BorderLayout.CENTER);

        // --- 3. Tampilkan LoginPanel di awal ---
        showLoginPanel();

        // Pastikan jendela terlihat
        setVisible(true);
        System.out.println("MainFrame constructor selesai. setVisible(true) dipanggil.");
    }

    // Metode addSidebarButton() dan updateSidebarButtonVisibility() dipindahkan ke SidebarPanel.java

    // --- Metode untuk Mengganti Konten di contentPanel (Area Utama) ---
    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            contentPanel.remove(currentPanel); // Hapus panel sebelumnya
        }
        currentPanel = panel;
        contentPanel.add(currentPanel, BorderLayout.CENTER); // Tambahkan panel baru
        contentPanel.revalidate(); // Validasi ulang layout
        contentPanel.repaint(); // Gambar ulang komponen
    }

    // --- Metode dummy untuk membuat panel sementara (akan diganti dengan panel asli Anda) ---
    private JPanel createDummyPanel(String panelName) {
        JPanel dummyPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Ini adalah " + panelName + ".");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        dummyPanel.add(label, BorderLayout.CENTER);
        dummyPanel.setBackground(new Color(220, 220, 220)); // Warna berbeda untuk dummy panel
        return dummyPanel;
    }


    // --- Metode untuk menampilkan LoginPanel (dipanggil di awal) ---
    public void showLoginPanel() {
        System.out.println("SLP_1. showLoginPanel() dimulai.");

        // Hapus semua komponen dari JFrame utama jika ada (terutama sidebar dan contentArea)
        getContentPane().removeAll();

        // Inisialisasi LoginPanel dan set listener-nya
        loginPanel = new LoginPanel();
        loginPanel.setLoginSuccessListener(this::onLoginSuccess); // Menggunakan method reference

        // Tambahkan LoginPanel langsung ke JFrame utama
        add(loginPanel, BorderLayout.CENTER);

        revalidate(); // Refresh tampilan frame
        repaint();
        System.out.println("SLP_4. LoginPanel ditambahkan, revalidate/repaint.");
    }

    // --- Metode yang akan dipanggil oleh LoginPanel setelah berhasil login ---
    public void onLoginSuccess() {
        System.out.println("ON_LOGIN_SUCCESS: Pengalihan setelah login.");

        // Hapus panel login yang sedang ditampilkan
        getContentPane().removeAll();

        // Tambahkan sidebar dan area konten utama kembali ke JFrame
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Perbarui visibilitas tombol sidebar berdasarkan peran user yang baru login
        System.out.println("DEBUG - onLoginSuccess: Memperbarui visibilitas tombol sidebar...");
        sidebarPanel.updateButtonVisibility();

        // Dapatkan pengguna yang sedang login dari GlobalAppState
        User currentUser = GlobalAppState.getInstance().getCurrentUser();

        if (currentUser != null) {
            String role = currentUser.getRole();
            System.out.println("User role: " + role + ". Mengarahkan ke halaman utama.");

            // Logika untuk menampilkan panel default berdasarkan peran
            switch (role.toLowerCase()) { // <-- Gunakan toLowerCase() di sini
                case "admin":
                case "guru":
                case "teknisi":
                    showPanel(new DashboardPanel());
                    System.out.println("DEBUG - onLoginSuccess: Menampilkan DashboardPanel.");
                    break;
               case "siswa":
            try {
                showPanel(new SiswaProfilePanel()); // <--- UBAH KE SISWA PROFILE PANEL
                System.out.println("DEBUG - onLoginSuccess: Menampilkan SiswaProfilePanel untuk Siswa.");
            } catch (Exception e) {
                System.err.println("ERROR: Gagal menginisialisasi SiswaProfilePanel! " + e.getMessage());
                e.printStackTrace();
                showPanel(createDummyPanel("ERROR: Panel Profil Siswa Gagal Dimuat."));
            }
            break;
                default:
                    showPanel(createDummyPanel("ERROR: Peran Pengguna Tidak Dikenal."));
                    System.out.println("DEBUG - onLoginSuccess: Menampilkan Dummy Panel (role tidak dikenal).");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mendapatkan informasi pengguna setelah login.", "Error", JOptionPane.ERROR_MESSAGE);
            showLoginPanel();
        }

        revalidate();
        repaint();
        System.out.println("ON_LOGIN_SUCCESS: Proses pengalihan selesai.");
    }


    // --- Metode main untuk menjalankan aplikasi (opsional jika dipanggil dari ProjectKel6DesktopSarpas.java) ---
    public static void main(String[] args) {
        System.out.println("MainFrame.main() dipanggil. Menjalankan di EDT.");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
        System.out.println("MainFrame.main() selesai.");
    }
}