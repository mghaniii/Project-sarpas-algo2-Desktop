package com.mycompany.project.kel.desktop.sarpas.model;

public class Ruangan {
    private int idRuangan;
    private String namaRuangan;
    private int kapasitas;
    private String lokasiDetail;
    private String deskripsi;

    public Ruangan(int idRuangan, String namaRuangan, int kapasitas, String lokasiDetail, String deskripsi) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.kapasitas = kapasitas;
        this.lokasiDetail = lokasiDetail;
        this.deskripsi = deskripsi;
    }

    // Getters
    public int getIdRuangan() { return idRuangan; }
    public String getNamaRuangan() { return namaRuangan; }
    public int getKapasitas() { return kapasitas; }
    public String getLokasiDetail() { return lokasiDetail; }
    public String getDeskripsi() { return deskripsi; }

    // Setters (opsional, tergantung kebutuhan)
    public void setIdRuangan(int idRuangan) { this.idRuangan = idRuangan; }
    public void setNamaRuangan(String namaRuangan) { this.namaRuangan = namaRuangan; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
    public void setLokasiDetail(String lokasiDetail) { this.lokasiDetail = lokasiDetail; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    @Override
    public String toString() { return namaRuangan; } // Penting untuk JComboBox
}