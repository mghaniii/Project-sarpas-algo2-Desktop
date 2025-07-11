package com.mycompany.project.kel.desktop.sarpas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Ganti 'db_inventaris_sekolah' dengan nama database yang Anda buat di phpMyAdmin
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_sarpras_sekolah_tadikamesra";
    private static final String USER = "root"; // Username default MySQL XAMPP
    private static final String PASS = ""; // Password default MySQL XAMPP (kosong)

    
    public static Connection getConnection() throws SQLException {
        System.out.println("Mencoba menghubungkan ke database MySQL di: " + DB_URL);
        Connection conn = null;
        try {
            // DriverManager akan otomatis menemukan driver jika sudah di classpath (dari Maven)
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi ke database MySQL berhasil!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke database MySQL!");
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Pesan Error: " + e.getMessage());
            e.printStackTrace();
            throw e; // Lempar kembali exception agar pemanggil tahu ada masalah
        }
    }

    /**
     * Menutup objek Connection.
     * @param conn Objek Connection yang akan ditutup.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Koneksi database MySQL ditutup.");
            } catch (SQLException e) {
                System.err.println("Error menutup koneksi database: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}