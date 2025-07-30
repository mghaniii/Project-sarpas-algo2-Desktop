package com.mycompany.project.kel.desktop.sarpas.dao;
import com.mycompany.project.kel.desktop.sarpas.model.Kategori;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;
import java.sql.Connection; import java.sql.PreparedStatement; import java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList; import java.util.List;
public class KategoriDAO {
    public List<Kategori> getAllKategori() {
        List<Kategori> daftarKategori = new ArrayList<>();
        String sql = "SELECT id_kategori, nama_kategori FROM kategori";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Kategori kategori = new Kategori(rs.getInt("id_kategori"), rs.getString("nama_kategori"));
                daftarKategori.add(kategori);
            }
        } catch (SQLException e) { System.err.println("Error get all kategori: " + e.getMessage()); e.printStackTrace(); } return daftarKategori;
    }
}