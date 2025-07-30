package com.mycompany.project.kel.desktop.sarpas.model;

// Pastikan tidak ada import java.sql.Date atau java.time.LocalDateTime yang tidak relevan jika tidak ada kolom tanggal

public class Barang {
    // Atribut sesuai dengan kolom tabel 'barang'
    private int idBarang;       // id_barang INT AUTO_INCREMENT PRIMARY KEY
    private int idKategori;     // id_kategori INT
    private int idLokasi;       // id_lokasi INT
    private String kodeBarang;  // kode_barang VARCHAR
    private String namaBarang;  // nama_barang VARCHAR
    private int jumlahTotal;    // jumlah_total INT
    private int jumlahTersedia; // jumlah_tersedia INT
    private String kondisi;     // kondisi VARCHAR (e.g., 'Baik', 'Rusak', 'Dalam Perbaikan')

    // --- CONSTRUCTOR ---
    // Constructor untuk membuat objek Barang baru (tanpa ID dari DB)
    public Barang(int idKategori, int idLokasi, String kodeBarang, String namaBarang,
                  int jumlahTotal, int jumlahTersedia, String kondisi) {
        this.idKategori = idKategori;
        this.idLokasi = idLokasi;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.jumlahTotal = jumlahTotal;
        this.jumlahTersedia = jumlahTersedia;
        this.kondisi = kondisi;
    }

    // Constructor lengkap (untuk objek Barang yang diambil dari DB, dengan ID)
    public Barang(int idBarang, int idKategori, int idLokasi, String kodeBarang, String namaBarang,
                  int jumlahTotal, int jumlahTersedia, String kondisi) {
        this(idKategori, idLokasi, kodeBarang, namaBarang, jumlahTotal, jumlahTersedia, kondisi); // Panggil constructor di atas
        this.idBarang = idBarang;
    }

    // --- GETTER dan SETTER ---
    // (Tambahkan semua getter dan setter untuk setiap atribut)

    public int getIdBarang() { return idBarang; }
    public void setIdBarang(int idBarang) { this.idBarang = idBarang; } // setIdBarang biasanya tidak di-set manual

    public int getIdKategori() { return idKategori; }
    public void setIdKategori(int idKategori) { this.idKategori = idKategori; }

    public int getIdLokasi() { return idLokasi; }
    public void setIdLokasi(int idLokasi) { this.idLokasi = idLokasi; }

    public String getKodeBarang() { return kodeBarang; }
    public void setKodeBarang(String kodeBarang) { this.kodeBarang = kodeBarang; }

    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }

    public int getJumlahTotal() { return jumlahTotal; }
    public void setJumlahTotal(int jumlahTotal) { this.jumlahTotal = jumlahTotal; }

    public int getJumlahTersedia() { return jumlahTersedia; }
    public void setJumlahTersedia(int jumlahTersedia) { this.jumlahTersedia = jumlahTersedia; }

    public String getKondisi() { return kondisi; }
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }

    // --- toString() untuk JComboBox ---
    @Override
    public String toString() {
        // Metode ini akan dipanggil oleh JComboBox.
        // Kita hanya ingin menampilkan nama barangnya saja.
        return this.namaBarang;
    }
}
