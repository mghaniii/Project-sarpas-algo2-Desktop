package com.mycompany.project.kel.desktop.sarpas.model;
public class Lokasi {
    private int idLokasi;
    private String namaLokasi;
    private String deskripsi;
    public Lokasi(int idLokasi, String namaLokasi, String deskripsi) {
        this.idLokasi = idLokasi; this.namaLokasi = namaLokasi; this.deskripsi = deskripsi;
    }
    public int getIdLokasi() { return idLokasi; }
    public void setIdLokasi(int idLokasi) { this.idLokasi = idLokasi; }
    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
   @Override
public String toString() {
    // Mengembalikan nama lokasi sebagai teks yang akan ditampilkan.
    // Pastikan Anda memiliki getter getNamaLokasi() di kelas ini.
    return this.getNamaLokasi(); 
}
}