package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.LaporanKerusakan;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Untuk Statement (jika perlu)
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp; // Untuk Timestamp

public class LaporanKerusakanDAO {

    /**
     * Menambah laporan kerusakan baru ke database.
     * @param laporan Objek LaporanKerusakan yang akan ditambahkan.
     * @return true jika berhasil, false jika gagal.
     */
    public boolean addLaporanKerusakan(LaporanKerusakan laporan) {
        String sql = "INSERT INTO laporan_kerusakan (id_barang, nama_pelapor, jenis_kerusakan, lokasi_alat, deskripsi_kerusakan, url_foto_kerusakan, status_laporan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, laporan.getIdBarang());
            pstmt.setString(2, laporan.getNamaPelapor());
            pstmt.setString(3, laporan.getJenisKerusakan());
            pstmt.setString(4, laporan.getLokasiAlat());
            pstmt.setString(5, laporan.getDeskripsiKerusakan());
            pstmt.setString(6, laporan.getUrlFotoKerusakan());
            pstmt.setString(7, laporan.getStatusLaporan());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        laporan.setIdLaporan(rs.getInt(1)); // Set ID yang baru dibuat
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error saat menambah laporan kerusakan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Mendapatkan daftar semua laporan kerusakan.
     * @return List objek LaporanKerusakan.
     */
// Di dalam file LaporanKerusakanDAO.java

public List<LaporanKerusakan> getAllLaporanKerusakan() {
    List<LaporanKerusakan> daftarLaporan = new ArrayList<>();
    // Query ini mengambil semua data dari laporan_kerusakan DAN nama_barang dari tabel barang
    String sql = "SELECT lk.*, b.nama_barang, b.kode_barang, l.nama_lokasi " +
                 "FROM laporan_kerusakan lk " +
                 "JOIN barang b ON lk.id_barang = b.id_barang " +
                 "JOIN lokasi l ON b.id_lokasi = l.id_lokasi"; // JOIN ke tabel lokasi
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            // Buat objek LaporanKerusakan menggunakan constructor lengkap Anda
            LaporanKerusakan laporan = new LaporanKerusakan(
                rs.getInt("id_laporan"),
                rs.getInt("id_barang"),
                rs.getString("nama_pelapor"),
                rs.getTimestamp("tanggal_pelaporan"),
                rs.getString("jenis_kerusakan"),
                rs.getString("lokasi_alat"),
                rs.getString("deskripsi_kerusakan"),
                rs.getString("url_foto_kerusakan"),
                rs.getString("status_laporan")
            );

            // Set namaBarang yang kita dapat dari JOIN
            laporan.setNamaBarang(rs.getString("nama_barang"));
            laporan.setKodeBarang(rs.getString("kode_barang")); 
                laporan.setLokasiAlat(rs.getString("nama_lokasi"));
            daftarLaporan.add(laporan);
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil semua laporan kerusakan: " + e.getMessage());
        e.printStackTrace();
    }
    return daftarLaporan;
}

    /**
     * Mengupdate status laporan kerusakan.
     * @param idLaporan ID laporan yang akan diupdate.
     * @param newStatus Status baru (e.g., "Sedang Diproses", "Selesai Diperbaiki").
     * @return true jika berhasil, false jika gagal.
     */
    public boolean updateLaporanStatus(int idLaporan, String newStatus) {
        String sql = "UPDATE laporan_kerusakan SET status_laporan = ? WHERE id_laporan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, idLaporan);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error saat update status laporan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
///////Untuk dashboard siswa
    public List<LaporanKerusakan> getLaporanByNamaPelapor(String namaPelapor) {
    List<LaporanKerusakan> daftarLaporan = new ArrayList<>();
    String sql = "SELECT lk.*, b.nama_barang " +
                 "FROM laporan_kerusakan lk " +
                 "JOIN barang b ON lk.id_barang = b.id_barang " +
                 "WHERE lk.nama_pelapor = ? ORDER BY lk.tanggal_pelaporan DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, namaPelapor);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                LaporanKerusakan laporan = new LaporanKerusakan(
                    rs.getInt("id_laporan"),
                    rs.getInt("id_barang"),
                    rs.getString("nama_pelapor"),
                    rs.getTimestamp("tanggal_pelaporan"),
                    rs.getString("jenis_kerusakan"),
                    rs.getString("lokasi_alat"),
                    rs.getString("deskripsi_kerusakan"),
                    rs.getString("url_foto_kerusakan"),
                    rs.getString("status_laporan")
                );
                laporan.setNamaBarang(rs.getString("nama_barang"));
                daftarLaporan.add(laporan);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return daftarLaporan;
}
}