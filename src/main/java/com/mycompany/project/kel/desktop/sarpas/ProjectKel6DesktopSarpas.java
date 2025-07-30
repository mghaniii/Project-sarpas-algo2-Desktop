package com.mycompany.project.kel.desktop.sarpas;

import com.mycompany.project.kel.desktop.sarpas.view.MainFrame;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;
import com.mycompany.project.kel.desktop.sarpas.model.User; // Pastikan User.java sudah diimpor
import com.mycompany.project.kel.desktop.sarpas.dao.UserDAO; // Pastikan UserDAO.java sudah diimpor

import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.SQLException;

public class ProjectKel6DesktopSarpas {

    public static void main(String[] args) {
        // --- KODE TEST KONEKSI DATABASE (Sudah ada di tempat Anda) ---
        System.out.println("Memulai tes koneksi database...");
        Connection testConn = null;
        try {
            testConn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Tes koneksi database GAGAL! Aplikasi mungkin tidak berfungsi dengan baik tanpa koneksi database.");
        } finally {
            DatabaseConnection.closeConnection(testConn);
        }
        System.out.println("Tes koneksi database selesai.\n");
        // --- AKHIR KODE TEST KONEKSI DATABASE ---


        // --- KODE TEST OTENTIKASI BARU (Tambahkan di sini) ---
        System.out.println("--- Memulai Tes Otentikasi User ---");
        UserDAO userDAO = new UserDAO();
String testUsername = "admin_sarpas"; // <<-- GANTI INI
String testPassword = "sarpas123"; 
//        String testUsername = "budi.santoso"; // <<-- PASTIKAN INI SESUAI DENGAN DB ANDA
//        String testPassword = "budi123";      // <<-- PASTIKAN INI SESUAI DENGAN DB ANDA

        System.out.println("Menguji otentikasi untuk username: " + testUsername + ", password: " + testPassword);
        User authenticatedUser = userDAO.authenticate(testUsername, testPassword);

        if (authenticatedUser != null) {
            System.out.println("Autentikasi BERHASIL! Selamat datang, " + authenticatedUser.getNamaLengkap() + " (" + authenticatedUser.getRole() + ")");
            System.out.println("Detail User: " + authenticatedUser.toString());
        } else {
            System.err.println("Autentikasi GAGAL! Periksa username/password dan implementasi UserDAO.authenticate().");
        }
        System.out.println("--- Tes Otentikasi User Selesai ---\n");
        // --- AKHIR KODE TEST OTENTIKASI BARU ---


        // --- KODE UI APLIKASI UTAMA (jangan dihapus) ---
        System.out.println("Memulai UI Aplikasi...");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}