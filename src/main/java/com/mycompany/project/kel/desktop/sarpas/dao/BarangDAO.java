package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.Barang;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection; // Pastikan ini benar

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {

    /**
     * Menambah barang baru ke database.
     * @param barang Objek Barang yang akan ditambahkan.
     * @return true jika berhasil, false jika gagal atau terjadi error.
     */
    public boolean addBarang(Barang barang) {
        // SQL INSERT query sesuai dengan kolom tabel 'barang' Anda
        String sql = "INSERT INTO barang (id_kategori, id_lokasi, kode_barang, nama_barang, jumlah_total, jumlah_tersedia, kondisi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); // Dapatkan koneksi
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameter sesuai dengan atribut objek Barang
            pstmt.setInt(1, barang.getIdKategori());
            pstmt.setInt(2, barang.getIdLokasi());
            pstmt.setString(3, barang.getKodeBarang());
            pstmt.setString(4, barang.getNamaBarang());
            pstmt.setInt(5, barang.getJumlahTotal());
            pstmt.setInt(6, barang.getJumlahTersedia());
            pstmt.setString(7, barang.getKondisi());

            int rowsAffected = pstmt.executeUpdate(); // Jalankan query INSERT
            return rowsAffected > 0; // Mengembalikan true jika berhasil menambah 1 baris
        } catch (SQLException e) {
            System.err.println("Error saat menambah barang: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Mengambil daftar semua barang dari database.
     * @return List objek Barang.
     */
    public List<Barang> getAllBarang() {
        List<Barang> daftarBarang = new ArrayList<>();
        // SQL SELECT query sesuai dengan kolom tabel 'barang' Anda
        String sql = "SELECT id_barang, id_kategori, id_lokasi, kode_barang, nama_barang, jumlah_total, jumlah_tersedia, kondisi FROM barang";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // Jalankan query SELECT

            while (rs.next()) { // Loop melalui setiap baris hasil
                // Mapping data dari ResultSet ke objek Barang
                Barang barang = new Barang(
                    rs.getInt("id_barang"),
                    rs.getInt("id_kategori"),
                    rs.getInt("id_lokasi"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("jumlah_total"),
                    rs.getInt("jumlah_tersedia"),
                    rs.getString("kondisi")
                );
                daftarBarang.add(barang);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil semua barang: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarBarang;
    }

    /**
     * Mengupdate data barang di database berdasarkan ID.
     * @param barang Objek Barang dengan data yang diperbarui.
     * @return true jika berhasil, false jika gagal atau terjadi error.
     */
    public boolean updateBarang(Barang barang) {
        // SQL UPDATE query sesuai dengan kolom tabel 'barang' Anda
        String sql = "UPDATE barang SET id_kategori = ?, id_lokasi = ?, kode_barang = ?, nama_barang = ?, jumlah_total = ?, jumlah_tersedia = ?, kondisi = ? WHERE id_barang = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameter sesuai dengan atribut objek Barang dan ID untuk WHERE clause
            pstmt.setInt(1, barang.getIdKategori());
            pstmt.setInt(2, barang.getIdLokasi());
            pstmt.setString(3, barang.getKodeBarang());
            pstmt.setString(4, barang.getNamaBarang());
            pstmt.setInt(5, barang.getJumlahTotal());
            pstmt.setInt(6, barang.getJumlahTersedia());
            pstmt.setString(7, barang.getKondisi());
            pstmt.setInt(8, barang.getIdBarang()); // ID untuk WHERE clause

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error saat mengupdate barang: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Menghapus barang dari database berdasarkan ID.
     * @param idBarang ID barang yang akan dihapus.
     * @return true jika berhasil, false jika gagal atau terjadi error.
     */
    public boolean deleteBarang(int idBarang) {
        String sql = "DELETE FROM barang WHERE id_barang = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idBarang); // ID barang yang akan dihapus

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus barang: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Mendapatkan barang berdasarkan ID.
     * @param idBarang ID barang yang dicari.
     * @return Objek Barang jika ditemukan, null jika tidak.
     */
    public Barang getBarangByKode(String kodeBarang) {
    String sql = "SELECT id_barang, id_kategori, id_lokasi, kode_barang, nama_barang, jumlah_total, jumlah_tersedia, kondisi FROM barang WHERE kode_barang = ?";
    Barang barang = null;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, kodeBarang);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            barang = new Barang(
                rs.getInt("id_barang"),
                rs.getInt("id_kategori"),
                rs.getInt("id_lokasi"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah_total"),
                rs.getInt("jumlah_tersedia"),
                rs.getString("kondisi")
            );
        }
    } catch (SQLException e) {
        System.err.println("Error saat mendapatkan barang berdasarkan kode: " + e.getMessage());
        e.printStackTrace();
    }
    return barang;
}
    
    public List<Barang> searchBarang(String keyword) {
        List<Barang> daftarBarang = new ArrayList<>();
        // Menggunakan LIKE untuk pencarian parsial, dan LOWER untuk case-insensitive
        String sql = "SELECT id_barang, id_kategori, id_lokasi, kode_barang, nama_barang, jumlah_total, jumlah_tersedia, kondisi " +
                     "FROM barang " +
                     "WHERE LOWER(nama_barang) LIKE ? OR LOWER(kode_barang) LIKE ?"; // <<< Perubahan di sini
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword.toLowerCase() + "%"; // Tambahkan wildcard %
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Barang barang = new Barang(
                        rs.getInt("id_barang"),
                        rs.getInt("id_kategori"),
                        rs.getInt("id_lokasi"),
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah_total"),
                        rs.getInt("jumlah_tersedia"),
                        rs.getString("kondisi")
                    );
                    daftarBarang.add(barang);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari barang: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarBarang;
    }

   // Di dalam file BarangDAO.java

public Barang getBarangById(int idBarang) {
    Barang barang = null;
    String sql = "SELECT * FROM barang WHERE id_barang = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idBarang); // Set parameter ID untuk query

        try (ResultSet rs = ps.executeQuery()) {
            // Jika data ditemukan, buat objek Barang
            if (rs.next()) {
                barang = new Barang(
                    rs.getInt("id_barang"),
                    rs.getInt("id_kategori"),
                    rs.getInt("id_lokasi"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("jumlah_total"),
                    rs.getInt("jumlah_tersedia"),
                    rs.getString("kondisi")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil barang by ID: " + e.getMessage());
        e.printStackTrace();
    }
    return barang; // Akan mengembalikan objek Barang jika ditemukan, atau null jika tidak
}
    public int hitungTotalAset() {
    String sql = "SELECT COUNT(*) FROM barang";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Mengembalikan 0 jika terjadi error
}
    
    
    public int hitungAsetByKondisi(String kondisi) {
    String sql = "SELECT COUNT(*) FROM barang WHERE kondisi = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, kondisi);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Mengembalikan 0 jika terjadi error
}
    
    
    public int hitungSemuaAsetRusak() {
    // Query ini menggunakan LIKE untuk mencari semua baris yang kolom
    // kondisinya mengandung kata "Rusak"
    String sql = "SELECT COUNT(*) FROM barang WHERE kondisi LIKE '%Rusak%'";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
    
}