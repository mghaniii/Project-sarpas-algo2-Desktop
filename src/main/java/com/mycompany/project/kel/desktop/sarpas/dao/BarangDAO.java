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
    public Barang getBarangById(int idBarang) {
        String sql = "SELECT id_barang, id_kategori, id_lokasi, kode_barang, nama_barang, jumlah_total, jumlah_tersedia, kondisi FROM barang WHERE id_barang = ?";
        Barang barang = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idBarang);
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
            System.err.println("Error saat mendapatkan barang berdasarkan ID: " + e.getMessage());
            e.printStackTrace();
        }
        return barang;
    }
}