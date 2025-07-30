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
    
    
    
    
    /**
 * Mengupdate status peminjaman berdasarkan ID-nya.
 * @param idPeminjaman ID dari peminjaman yang akan diupdate.
 * @param newStatus Status baru (e.g., "Disetujui", "Ditolak").
 * @return true jika berhasil, false jika gagal.
 */
 
public boolean updateStatusPeminjaman(int idPeminjaman, String newStatus) {
    // Pastikan nama tabel di sini sudah benar (peminjaman atau peminjaman_fasilitas)
    String sql = "UPDATE peminjaman_fasilitas SET status_peminjaman = ? WHERE id_peminjaman = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, idPeminjaman);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        System.err.println("Error saat update status peminjaman: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}


 public List<Peminjaman> getPeminjamanTerjadwal() {
        List<Peminjaman> daftarPeminjaman = new ArrayList<>();
        // QUERY BENAR: Mengambil peminjaman yang Disetujui DAN tanggalnya > HARI INI
        String sql = "SELECT * FROM peminjaman_fasilitas " +
                     "WHERE status_peminjaman = 'Disetujui' AND tanggal_peminjaman > CURDATE() " +
                     "ORDER BY tanggal_peminjaman ASC, waktu_mulai ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Kode parsing untuk membuat objek Peminjaman
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
            e.printStackTrace();
        }
        return daftarPeminjaman;
    }


 public List<Peminjaman> getPeminjamanHariIni() {
        List<Peminjaman> daftarPeminjaman = new ArrayList<>();
        // QUERY BENAR: Mengambil semua peminjaman yang tanggalnya = HARI INI
        String sql = "SELECT * FROM peminjaman_fasilitas WHERE tanggal_peminjaman = CURDATE() ORDER BY waktu_mulai ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Kode parsing untuk membuat objek Peminjaman
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
            e.printStackTrace();
        }
        return daftarPeminjaman;
    }
 ///////// untuk dashboard siswa
 public List<Peminjaman> getPeminjamanByUserId(int userId) {
    List<Peminjaman> daftarPeminjaman = new ArrayList<>();
    String sql = "SELECT * FROM peminjaman_fasilitas WHERE id_user_peminjam = ? ORDER BY tanggal_peminjaman DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, userId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Kode untuk membuat objek Peminjaman dari data database
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
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return daftarPeminjaman;
}


 
 
    // Anda bisa menambahkan metode lain: getPeminjamanById, updatePeminjaman, deletePeminjaman
}