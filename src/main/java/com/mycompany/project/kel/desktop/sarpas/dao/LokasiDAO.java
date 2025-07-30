package com.mycompany.project.kel.desktop.sarpas.dao;
import com.mycompany.project.kel.desktop.sarpas.model.Lokasi;
import com.mycompany.project.kel.desktop.sarpas.util.DatabaseConnection;
import java.sql.Connection; import java.sql.PreparedStatement; import java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList; import java.util.List;
public class LokasiDAO {
    // Di dalam file LokasiDAO.java
public List<Lokasi> getAllLokasi() {
    List<Lokasi> daftarLokasi = new ArrayList<>();
    String sql = "SELECT * FROM lokasi"; // Pastikan nama tabel benar

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Lokasi lokasi = new Lokasi(
                rs.getInt("id_lokasi"),
                rs.getString("nama_lokasi"),
                rs.getString("deskripsi")
            );
            
            // --- TAMBAHKAN BARIS INI UNTUK DEBUGGING ---
            System.out.println("DAO DEBUG: Menemukan lokasi dari DB -> " + lokasi.getNamaLokasi());
            
            daftarLokasi.add(lokasi);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return daftarLokasi;
}
}