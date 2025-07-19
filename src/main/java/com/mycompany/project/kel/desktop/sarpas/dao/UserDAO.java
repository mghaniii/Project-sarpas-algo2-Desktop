package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.User;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Untuk java.sql.Date
import java.sql.Timestamp; // Untuk java.sql.Timestamp
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /**
     * Memverifikasi kredensial pengguna dari database untuk login.
     * @param username Username yang dimasukkan pengguna.
     * @param password Password yang dimasukkan pengguna (plaintext).
     * @return Objek User jika kredensial benar, null jika salah atau error.
     */
    public User authenticate(String username, String password) {
        // Query disesuaikan dengan nama kolom di tabel Anda
        String sql = "SELECT id_users, username, password, nama_lengkap, role, " +
                     "nomor_induk, nisn, tempat_lahir, tanggal_lahir, agama, alamat, create_time " +
                     "FROM users WHERE nomor_induk = ? AND password = ?";
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Mapping data dari ResultSet ke objek User
                user = new User(
                    rs.getInt("id_users"),
                    rs.getString("username"),
                    rs.getString("password"), // Ini adalah password dari DB
                    rs.getString("nama_lengkap"),
                    rs.getString("role"),
                    rs.getString("nomor_induk"),
                    rs.getString("nisn"),
                    rs.getString("tempat_lahir"),
                    rs.getDate("tanggal_lahir"), // Gunakan getDate untuk java.sql.Date
                    rs.getString("agama"),
                    rs.getString("alamat"),
                    rs.getTimestamp("create_time") // Gunakan getTimestamp
                );
            }
        } catch (SQLException e) {
            System.err.println("Error saat autentikasi user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Menambah user baru ke database.
     * @param user Objek User yang akan ditambahkan.
     * @return true jika berhasil, false jika gagal atau terjadi error.
     */
    public boolean createUser(User user) {
        // Pastikan urutan kolom sesuai dengan VALUES (?)
        String sql = "INSERT INTO users (username, password, nama_lengkap, role, " +
                     "nomor_induk, nisn, tempat_lahir, tanggal_lahir, agama, alamat) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNamaLengkap());
            pstmt.setString(4, user.getRole());

            // Untuk kolom yang bisa NULL, pastikan Anda bisa mengirim NULL
            pstmt.setString(5, user.getNomorInduk());
            pstmt.setString(6, user.getNisn());
            pstmt.setString(7, user.getTempatLahir());
            pstmt.setDate(8, user.getTanggalLahir()); // Gunakan setDate untuk java.sql.Date
            pstmt.setString(9, user.getAgama());
            pstmt.setString(10, user.getAlamat());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menambah user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- Contoh Metode Lain (jika Anda ingin menambahkannya) ---

    /**
     * Mendapatkan user berdasarkan username.
     * @param username Username user yang dicari.
     * @return Objek User jika ditemukan, null jika tidak.
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT id_users, username, password, nama_lengkap, role, " +
                     "nomor_induk, nisn, tempat_lahir, tanggal_lahir, agama, alamat, create_time " +
                     "FROM users WHERE username = ?";
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("id_users"),
                    rs.getString("username"),
                    rs.getString("password"),
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
            }
        } catch (SQLException e) {
            System.err.println("Error mendapatkan user berdasarkan username: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Mendapatkan daftar semua user.
     * @return List objek User.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id_users, username, password, nama_lengkap, role, " +
                     "nomor_induk, nisn, tempat_lahir, tanggal_lahir, agama, alamat, create_time " +
                     "FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("id_users"),
                    rs.getString("username"),
                    rs.getString("password"),
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
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error mendapatkan semua user: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    // Metode update dan delete juga perlu disesuaikan dengan semua kolom
    // public boolean updateUser(User user) { ... }
    // public boolean deleteUser(int userId) { ... }
}
