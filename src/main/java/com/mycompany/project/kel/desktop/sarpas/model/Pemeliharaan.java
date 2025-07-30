package com.mycompany.project.kel.desktop.sarpas.model;

import java.sql.Date;

public class Pemeliharaan {
    private int idPemeliharaan;
    private int idBarang;
    private int idPetugas;
    private Date tanggalJadwal;
    private Date tanggalSelesai;
    private String jenisPerawatan;
    private String catatanTambahan;
    private String status;

    // Variabel tambahan untuk tampilan (dari JOIN)
    private String namaBarang;
    private String namaPetugas;

    // Constructor kosong (default)
    public Pemeliharaan() {
    }
    
    // Constructor dengan parameter
    public Pemeliharaan(int idPemeliharaan, int idBarang, int idPetugas, Date tanggalJadwal, Date tanggalSelesai, String jenisPerawatan, String catatanTambahan, String status) {
        this.idPemeliharaan = idPemeliharaan;
        this.idBarang = idBarang;
        this.idPetugas = idPetugas;
        this.tanggalJadwal = tanggalJadwal;
        this.tanggalSelesai = tanggalSelesai;
        this.jenisPerawatan = jenisPerawatan;
        this.catatanTambahan = catatanTambahan;
        this.status = status;
    }
    
    // --- GETTER DAN SETTER ---

    public int getIdPemeliharaan() {
        return idPemeliharaan;
    }

    public void setIdPemeliharaan(int idPemeliharaan) {
        this.idPemeliharaan = idPemeliharaan;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(int idPetugas) {
        this.idPetugas = idPetugas;
    }

    public Date getTanggalJadwal() {
        return tanggalJadwal;
    }

    public void setTanggalJadwal(Date tanggalJadwal) {
        this.tanggalJadwal = tanggalJadwal;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getJenisPerawatan() {
        return jenisPerawatan;
    }

    public void setJenisPerawatan(String jenisPerawatan) {
        this.jenisPerawatan = jenisPerawatan;
    }

    public String getCatatanTambahan() {
        return catatanTambahan;
    }

    public void setCatatanTambahan(String catatanTambahan) {
        this.catatanTambahan = catatanTambahan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }
}