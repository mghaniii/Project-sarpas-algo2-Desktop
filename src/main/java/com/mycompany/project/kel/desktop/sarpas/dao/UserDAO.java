package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.User;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User authenticate(String username, String password) {
        // DEBUG: Cetak username dan password yang diterima metode ini
        System.out.println("DEBUG UserDAO: Menerima username = " + username + ", password = [PASSWORD TERSEMBUNYI]");

        // Pastikan query ini mencari berdasarkan kolom 'username' di database Anda
        String sql = "SELECT id_users, username, password, nama_lengkap, role, " +
                     "nomor_induk, nisn, tempat_lahir, tanggal_lahir, agama, alamat, create_time " +
                     "FROM users WHERE username = ? AND password = ?"; // <<< PASTIKAN INI 'username'

        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password); // <<< Jika Anda menggunakan password hashing, ini harus di-hash dulu

            // DEBUG: Cetak query yang akan dieksekusi
            System.out.println("DEBUG UserDAO: Mengeksekusi SQL: " + sql + " dengan parameter: " + username + ", [PASSWORD TERSEMBUNYI]");

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // DEBUG: User ditemukan di database
                System.out.println("DEBUG UserDAO: User ditemukan! Role: " + rs.getString("role"));
                user = new User(
                    rs.getInt("id_users"),
                    rs.getString("username"),
                    rs.getString("password"), // Password dari DB (mungkin hash)
                    rs.getString("nama_lengkap"),
                    rs.getString("role"),
                    rs.getString("nomor_induk"),
                    rs.getString("nisn"),
                    rs.getString("tempat_lahir"),
                    rs.getDate("tanggal_lahir"),
                    rs.getString("agama"),
                    rs.getString("alamat"),
                    rs.getTimestamp("create_time")
                );
            } else {
                // DEBUG: User tidak ditemukan
                System.out.println("DEBUG UserDAO: User tidak ditemukan dengan kredensial tersebut.");
            }
        } catch (SQLException e) {
            System.err.println("ERROR UserDAO: Terjadi kesalahan SQL saat autentikasi user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
    
    
    public List<User> getAllUsers() {
    List<User> daftarUser = new ArrayList<>();
    // Ambil semua kolom yang Anda butuhkan, KECUALI password untuk keamanan
    String sql = "SELECT id_users, username, nama_lengkap, role FROM users";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            // Buat objek User baru untuk setiap baris
            User user = new User(); // Asumsi Anda punya constructor kosong
            user.setIdUsers(rs.getInt("id_users"));
            user.setUsername(rs.getString("username"));
            user.setNamaLengkap(rs.getString("nama_lengkap"));
            user.setRole(rs.getString("role"));
            
            daftarUser.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return daftarUser;
}
    
}