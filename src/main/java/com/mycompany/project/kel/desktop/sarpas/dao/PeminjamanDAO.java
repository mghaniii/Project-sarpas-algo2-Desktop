package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.Peminjaman;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PeminjamanDAO {

    public boolean addPeminjaman(Peminjaman peminjaman) {
        String sql = "INSERT INTO peminjaman_fasilitas (" +
                     "id_user_peminjam, nama_peminjam, jenis_peminjam, no_telepon_peminjam, " +
                     "id_barang_fk, id_ruangan_fk, jumlah_dipinjam, nama_fasilitas_manual, " +
                     "tanggal_peminjaman, waktu_mulai, waktu_selesai, keperluan, status_peminjaman) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Handle NULLable Integer (id_user_peminjam, id_barang_fk, id_ruangan_fk, jumlah_dipinjam)
            if (peminjaman.getIdUserPeminjam() != null) pstmt.setInt(1, peminjaman.getIdUserPeminjam()); else pstmt.setNull(1, java.sql.Types.INTEGER);
            pstmt.setString(2, peminjaman.getNamaPeminjam());
            pstmt.setString(3, peminjaman.getJenisPeminjam());
            pstmt.setString(4, peminjaman.getNoTeleponPeminjam());

            if (peminjaman.getIdBarangFk() != null) pstmt.setInt(5, peminjaman.getIdBarangFk()); else pstmt.setNull(5, java.sql.Types.INTEGER);
            if (peminjaman.getIdRuanganFk() != null) pstmt.setInt(6, peminjaman.getIdRuanganFk()); else pstmt.setNull(6, java.sql.Types.INTEGER);
            if (peminjaman.getJumlahDipinjam() != null) pstmt.setInt(7, peminjaman.getJumlahDipinjam()); else pstmt.setNull(7, java.sql.Types.INTEGER);
            pstmt.setString(8, peminjaman.getNamaFasilitasManual());

            pstmt.setDate(9, peminjaman.getTanggalPeminjaman());
            pstmt.setTime(10, peminjaman.getWaktuMulai());
            pstmt.setTime(11, peminjaman.getWaktuSelesai());
            pstmt.setString(12, peminjaman.getKeperluan());
            pstmt.setString(13, peminjaman.getStatusPeminjaman());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        peminjaman.setIdPeminjaman(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error saat menambah peminjaman: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Peminjaman> getAllPeminjaman() {
        List<Peminjaman> daftarPeminjaman = new ArrayList<>();
        String sql = "SELECT id_peminjaman, id_user_peminjam, nama_peminjam, jenis_peminjam, no_telepon_peminjam, " +
                     "id_barang_fk, id_ruangan_fk, jumlah_dipinjam, nama_fasilitas_manual, " +
                     "tanggal_peminjaman, waktu_mulai, waktu_selesai, keperluan, status_peminjaman, " +
                     "created_at, updated_at FROM peminjaman_fasilitas";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman(
                    rs.getInt("id_peminjaman"),
                    rs.getObject("id_user_peminjam") != null ? rs.getInt("id_user_peminjam") : null,
                    rs.getString("nama_peminjam"),
                    rs.getString("jenis_peminjam"),
                    rs.getString("no_telepon_peminjam"),
                    rs.getObject("id_barang_fk") != null ? rs.getInt("id_barang_fk") : null,
                    rs.getObject("id_ruangan_fk") != null ? rs.getInt("id_ruangan_fk") : null,
                    rs.getObject("jumlah_dipinjam") != null ? rs.getInt("jumlah_dipinjam") : null,
                    rs.getString("nama_fasilitas_manual"),
                    rs.getDate("tanggal_peminjaman"),
                    rs.getTime("waktu_mulai"),
                    rs.getTime("waktu_selesai"),
                    rs.getString("keperluan"),
                    rs.getString("status_peminjaman"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                daftarPeminjaman.add(peminjaman);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil semua peminjaman: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarPeminjaman;
    }

    // Anda bisa menambahkan metode lain: getPeminjamanById, updatePeminjaman, deletePeminjaman
}