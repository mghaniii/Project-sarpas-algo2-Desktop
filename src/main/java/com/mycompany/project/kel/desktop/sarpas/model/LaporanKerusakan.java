package com.mycompany.project.kel.desktop.sarpas.model;

import java.sql.Timestamp; // Untuk tanggal_pelaporan

public class LaporanKerusakan {
    private int idLaporan;
    private int idBarang; // Foreign key ke Barang
    private String namaPelapor;
    private Timestamp tanggalPelaporan; // Waktu laporan dibuat
    private String jenisKerusakan; // Contoh: "Rusak Berat", "Rusak Ringan", "Hilang"
    private String lokasiAlat; // Ruangan/Lab/Kantin
    private String deskripsiKerusakan;
    private String urlFotoKerusakan;
    private String statusLaporan; // "Belum Diproses", "Sedang Diproses", "Selesai Diperbaiki"

    // Constructor untuk laporan baru (tanpa ID dan tanggal dari DB)
    public LaporanKerusakan(int idBarang, String namaPelapor, String jenisKerusakan,
                            String lokasiAlat, String deskripsiKerusakan, String urlFotoKerusakan) {
        this.idBarang = idBarang;
        this.namaPelapor = namaPelapor;
        this.jenisKerusakan = jenisKerusakan;
        this.lokasiAlat = lokasiAlat;
        this.deskripsiKerusakan = deskripsiKerusakan;
        this.urlFotoKerusakan = urlFotoKerusakan;
        this.statusLaporan = "Belum Diproses"; // Default status
    }

    // Constructor lengkap (dari DB)
    public LaporanKerusakan(int idLaporan, int idBarang, String namaPelapor, Timestamp tanggalPelaporan,
                            String jenisKerusakan, String lokasiAlat, String deskripsiKerusakan,
                            String urlFotoKerusakan, String statusLaporan) {
        this(idBarang, namaPelapor, jenisKerusakan, lokasiAlat, deskripsiKerusakan, urlFotoKerusakan);
        this.idLaporan = idLaporan;
        this.tanggalPelaporan = tanggalPelaporan;
        this.statusLaporan = statusLaporan;
    }

    // Getter dan Setter
    public int getIdLaporan() { return idLaporan; }
    public void setIdLaporan(int idLaporan) { this.idLaporan = idLaporan; }

    public int getIdBarang() { return idBarang; }
    public void setIdBarang(int idBarang) { this.idBarang = idBarang; }

    public String getNamaPelapor() { return namaPelapor; }
    public void setNamaPelapor(String namaPelapor) { this.namaPelapor = namaPelapor; }

    public Timestamp getTanggalPelaporan() { return tanggalPelaporan; }
    public void setTanggalPelaporan(Timestamp tanggalPelaporan) { this.tanggalPelaporan = tanggalPelaporan; }

    public String getJenisKerusakan() { return jenisKerusakan; }
    public void setJenisKerusakan(String jenisKerusakan) { this.jenisKerusakan = jenisKerusakan; }

    public String getLokasiAlat() { return lokasiAlat; }
    public void setLokasiAlat(String lokasiAlat) { this.lokasiAlat = lokasiAlat; }

    public String getDeskripsiKerusakan() { return deskripsiKerusakan; }
    public void setDeskripsiKerusakan(String deskripsiKerusakan) { this.deskripsiKerusakan = deskripsiKerusakan; }

    public String getUrlFotoKerusakan() { return urlFotoKerusakan; }
    public void setUrlFotoKerusakan(String urlFotoKerusakan) { this.urlFotoKerusakan = urlFotoKerusakan; }

    public String getStatusLaporan() { return statusLaporan; }
    public void setStatusLaporan(String statusLaporan) { this.statusLaporan = statusLaporan; }

    @Override
    public String toString() {
        return "LaporanKerusakan{" + "idLaporan=" + idLaporan + ", idBarang=" + idBarang + ", namaPelapor=" + namaPelapor + ", statusLaporan=" + statusLaporan + '}';
    }
}