package com.mycompany.project.kel.desktop.sarpas.dao;

import com.mycompany.project.kel.desktop.sarpas.model.Ruangan;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RuanganDAO {
    public List<Ruangan> getAllRuangan() {
        List<Ruangan> daftarRuangan = new ArrayList<>();
        String sql = "SELECT id_ruangan, nama_ruangan, kapasitas, lokasi_detail, deskripsi FROM ruangan";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ruangan ruangan = new Ruangan(
                    rs.getInt("id_ruangan"),
                    rs.getString("nama_ruangan"),
                    rs.getInt("kapasitas"),
                    rs.getString("lokasi_detail"),
                    rs.getString("deskripsi")
                );
                daftarRuangan.add(ruangan);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil semua ruangan: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarRuangan;
    }

    /**
     * Mendapatkan objek Ruangan berdasarkan nama ruangan.
     * @param namaRuangan Nama ruangan yang dicari.
     * @return Objek Ruangan jika ditemukan, null jika tidak.
     */
    public Ruangan getRuanganByNama(String namaRuangan) { // <<< PASTIKAN IMPLEMENTASI INI ADA
        String sql = "SELECT id_ruangan, nama_ruangan, kapasitas, lokasi_detail, deskripsi FROM ruangan WHERE nama_ruangan = ?";
        Ruangan ruangan = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, namaRuangan);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ruangan = new Ruangan(
                    rs.getInt("id_ruangan"),
                    rs.getString("nama_ruangan"),
                    rs.getInt("kapasitas"),
                    rs.getString("lokasi_detail"),
                    rs.getString("deskripsi")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error saat mendapatkan ruangan berdasarkan nama: " + e.getMessage());
            e.printStackTrace();
        }
        return ruangan;
    }
    // Anda bisa tambahkan metode getRuanganById, addRuangan, updateRuangan, deleteRuangan
}