package com.mycompany.project.kel.desktop.sarpas;

import com.mycompany.project.kel.desktop.sarpas.view.MainFrame;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection; // Import DatabaseConnection
import javax.swing.SwingUtilities;
import java.sql.Connection; // Import Connection
import java.sql.SQLException; // Import SQLException

public class ProjectKel6DesktopSarpas {

    public static void main(String[] args) {
        // --- KODE TEST KONEKSI DATABASE SEMENTARA ---
        System.out.println("Memulai tes koneksi database...");
        Connection testConn = null;
        try {
            testConn = DatabaseConnection.getConnection();
            // Jika tidak ada exception, koneksi berhasil.
            // Anda bisa tambahkan System.out.println di dalam getConnection() juga
        } catch (SQLException e) {
            System.err.println("Tes koneksi database GAGAL! Aplikasi mungkin tidak berfungsi dengan baik tanpa koneksi database.");
            // Jika koneksi gagal, mungkin tidak perlu melanjutkan ke GUI
            // return; // Anda bisa un-comment ini jika ingin aplikasi berhenti saat koneksi gagal
        } finally {
            DatabaseConnection.closeConnection(testConn); // Pastikan koneksi ditutup
        }
        System.out.println("Tes koneksi database selesai.\n");
        // --- AKHIR KODE TEST KONEKSI DATABASE ---


        // --- KODE UI APLIKASI UTAMA (jangan dihapus) ---
        System.out.println("Memulai UI Aplikasi...");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}