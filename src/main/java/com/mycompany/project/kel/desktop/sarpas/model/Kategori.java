package com.mycompany.project.kel.desktop.sarpas.model;
public class Kategori {
    private int idKategori;
    private String namaKategori;
    public Kategori(int idKategori, String namaKategori) {
        this.idKategori = idKategori; this.namaKategori = namaKategori;
    }
    public int getIdKategori() { return idKategori; }
    public void setIdKategori(int idKategori) { this.idKategori = idKategori; }
    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }
   @Override
    public String toString() {
        return this.getNamaKategori(); // Asumsi Anda punya getter getNamaKategori()
    }
    
}
