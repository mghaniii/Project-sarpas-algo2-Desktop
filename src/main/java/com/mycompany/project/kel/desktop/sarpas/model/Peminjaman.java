package com.mycompany.project.kel.desktop.sarpas.model;

import java.sql.Date;   
import java.sql.Time;   // Untuk waktu TIME
import java.sql.Timestamp; // Untuk created_at, updated_at

public class Peminjaman {
    private int idPeminjaman;

    // Data Peminjam
    private Integer idUserPeminjam; // Integer karena bisa NULL
    private String namaPeminjam;
    private String jenisPeminjam;
    private String noTeleponPeminjam;

    // Detail Fasilitas
    private Integer idBarangFk; // Integer karena bisa NULL
    private Integer idRuanganFk; // Integer karena bisa NULL
    private Integer jumlahDipinjam; // Integer karena bisa NULL (untuk barang)
    private String namaFasilitasManual; // Jika fasilitas tidak ada di daftar

    // Waktu
    private Date tanggalPeminjaman;
    private Time waktuMulai;
    private Time waktuSelesai;

    private String keperluan;
    private String statusPeminjaman;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor untuk membuat objek Peminjaman baru (dari form)
    public Peminjaman(Integer idUserPeminjam, String namaPeminjam, String jenisPeminjam, String noTeleponPeminjam,
                       Integer idBarangFk, Integer idRuanganFk, Integer jumlahDipinjam, String namaFasilitasManual,
                       Date tanggalPeminjaman, Time waktuMulai, Time waktuSelesai, String keperluan) {
        this.idUserPeminjam = idUserPeminjam;
        this.namaPeminjam = namaPeminjam;
        this.jenisPeminjam = jenisPeminjam;
        this.noTeleponPeminjam = noTeleponPeminjam;
        this.idBarangFk = idBarangFk;
        this.idRuanganFk = idRuanganFk;
        this.jumlahDipinjam = jumlahDipinjam;
        this.namaFasilitasManual = namaFasilitasManual;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.keperluan = keperluan;
        this.statusPeminjaman = "Menunggu Persetujuan"; // Default status
    }

    // Constructor lengkap (dari database, termasuk ID dan timestamps)
    public Peminjaman(int idPeminjaman, Integer idUserPeminjam, String namaPeminjam, String jenisPeminjam, String noTeleponPeminjam,
                       Integer idBarangFk, Integer idRuanganFk, Integer jumlahDipinjam, String namaFasilitasManual,
                       Date tanggalPeminjaman, Time waktuMulai, Time waktuSelesai, String keperluan,
                       String statusPeminjaman, Timestamp createdAt, Timestamp updatedAt) {
        this(idUserPeminjam, namaPeminjam, jenisPeminjam, noTeleponPeminjam,
             idBarangFk, idRuanganFk, jumlahDipinjam, namaFasilitasManual,
             tanggalPeminjaman, waktuMulai, waktuSelesai, keperluan);
        this.idPeminjaman = idPeminjaman;
        this.statusPeminjaman = statusPeminjaman;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getter dan Setter (buat untuk semua atribut di atas) ---
    // Contoh:
    public int getIdPeminjaman() { return idPeminjaman; }
    public void setIdPeminjaman(int idPeminjaman) { this.idPeminjaman = idPeminjaman; }

    public Integer getIdUserPeminjam() { return idUserPeminjam; }
    public void setIdUserPeminjam(Integer idUserPeminjam) { this.idUserPeminjam = idUserPeminjam; }

    public String getNamaPeminjam() { return namaPeminjam; }
    public void setNamaPeminjam(String namaPeminjam) { this.namaPeminjam = namaPeminjam; }

    public String getJenisPeminjam() { return jenisPeminjam; }
    public void setJenisPeminjam(String jenisPeminjam) { this.jenisPeminjam = jenisPeminjam; }

    public String getNoTeleponPeminjam() { return noTeleponPeminjam; }
    public void setNoTeleponPeminjam(String noTeleponPeminjam) { this.noTeleponPeminjam = noTeleponPeminjam; }

    public Integer getIdBarangFk() { return idBarangFk; }
    public void setIdBarangFk(Integer idBarangFk) { this.idBarangFk = idBarangFk; }

    public Integer getIdRuanganFk() { return idRuanganFk; }
    public void setIdRuanganFk(Integer idRuanganFk) { this.idRuanganFk = idRuanganFk; }

    public Integer getJumlahDipinjam() { return jumlahDipinjam; }
    public void setJumlahDipinjam(Integer jumlahDipinjam) { this.jumlahDipinjam = jumlahDipinjam; }

    public String getNamaFasilitasManual() { return namaFasilitasManual; }
    public void setNamaFasilitasManual(String namaFasilitasManual) { this.namaFasilitasManual = namaFasilitasManual; }

    public Date getTanggalPeminjaman() { return tanggalPeminjaman; }
    public void setTanggalPeminjaman(Date tanggalPeminjaman) { this.tanggalPeminjaman = tanggalPeminjaman; }

    public Time getWaktuMulai() { return waktuMulai; }
    public void setWaktuMulai(Time waktuMulai) { this.waktuMulai = waktuMulai; }

    public Time getWaktuSelesai() { return waktuSelesai; }
    public void setWaktuSelesai(Time waktuSelesai) { this.waktuSelesai = waktuSelesai; }

    public String getKeperluan() { return keperluan; }
    public void setKeperluan(String keperluan) { this.keperluan = keperluan; }

    public String getStatusPeminjaman() { return statusPeminjaman; }
    public void setStatusPeminjaman(String statusPeminjaman) { this.statusPeminjaman = statusPeminjaman; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }


    @Override
    public String toString() {
        return "Peminjaman{" + "idPeminjaman=" + idPeminjaman + ", namaPeminjam=" + namaPeminjam + ", fasilitasDipinjam=" + namaFasilitasManual + '}';
    }
}