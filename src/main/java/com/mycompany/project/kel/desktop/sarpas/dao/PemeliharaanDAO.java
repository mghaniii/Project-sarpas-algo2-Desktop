package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.Pemeliharaan;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;
import com.mycompany.project.kel.desktop.sarpas.model.Barang; // Import Barang
import com.mycompany.project.kel.desktop.sarpas.model.User; // Import User
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PemeliharaanDAO {

    public List<Pemeliharaan> getJadwalMendatang() {
        List<Pemeliharaan> daftarJadwal = new ArrayList<>();
        String sql = "SELECT p.tanggal_jadwal, p.status, b.nama_barang, u.nama_lengkap AS nama_petugas " +
                     "FROM pelihara p " +
                     "JOIN barang b ON p.id_barang = b.id_barang " +
                     "JOIN users u ON p.id_users_petugas = u.id_users " +
                     "WHERE p.status != 'Selesai' AND p.tanggal_jadwal >= CURDATE() " +
                     "ORDER BY p.tanggal_jadwal ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pemeliharaan p = new Pemeliharaan();
                p.setTanggalJadwal(rs.getDate("tanggal_jadwal"));
                p.setStatus(rs.getString("status"));
                p.setNamaBarang(rs.getString("nama_barang"));
                p.setNamaPetugas(rs.getString("nama_petugas"));
                daftarJadwal.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarJadwal;
    }
    
    
    public int hitungPerawatanAktif() {
    // Query ini menghitung semua jadwal di tabel 'pelihara' yang statusnya BUKAN 'Selesai'
    String sql = "SELECT COUNT(*) FROM pelihara WHERE status != 'Selesai'";
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
    public List<Pemeliharaan> getAllPemeliharaan() {
    List<Pemeliharaan> daftarJadwal = new ArrayList<>();
    String sql = "SELECT p.*, b.nama_barang, u.nama_lengkap AS nama_petugas " +
                 "FROM pelihara p " +
                 "JOIN barang b ON p.id_barang = b.id_barang " +
                 "JOIN users u ON p.id_users_petugas = u.id_users " +
                 "ORDER BY p.tanggal_jadwal DESC"; // Urutkan dari yang terbaru

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Pemeliharaan p = new Pemeliharaan();
            p.setIdPemeliharaan(rs.getInt("id_pemeliharaan"));
            p.setTanggalJadwal(rs.getDate("tanggal_jadwal"));
            p.setStatus(rs.getString("status"));
            p.setNamaBarang(rs.getString("nama_barang"));
            p.setNamaPetugas(rs.getString("nama_petugas"));
            // Anda bisa set atribut lain jika perlu
            daftarJadwal.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return daftarJadwal;
}

    public boolean updateStatusPemeliharaan(int idPemeliharaan, String newStatus) {
    String sql = "UPDATE pelihara SET status = ? WHERE id_pemeliharaan = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, newStatus);
        ps.setInt(2, idPemeliharaan);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean addPemeliharaan(Pemeliharaan pemeliharaan) {
    String sql = "INSERT INTO pelihara (id_barang, id_users_petugas, tanggal_jadwal, jenis_perawatan, catatan_tambahan, status) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, pemeliharaan.getIdBarang());
        ps.setInt(2, pemeliharaan.getIdPetugas());
        ps.setDate(3, pemeliharaan.getTanggalJadwal());
        ps.setString(4, pemeliharaan.getJenisPerawatan());
        ps.setString(5, pemeliharaan.getCatatanTambahan());
        ps.setString(6, "Terjadwal"); // Status default saat dibuat

        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
}