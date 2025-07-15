package com.mycompany.project.kel.desktop.sarpas.model; // Sesuaikan dengan paket Anda

import com.mycompany.project.kel.desktop.sarpas.dao.BarangDAO; // Import BarangDAO
import java.util.List; // Import List

public class TestBarang {

    public static void main(String[] args) {
        System.out.println("--- Memulai Pengujian Kelas Barang dan BarangDAO ---");

        BarangDAO barangDAO = new BarangDAO();

        // --- Bagian 1: Menguji Fungsi CREATE (Menambah Barang Baru) ---
        System.out.println("\n--- Menguji Penambahan Barang Baru ---");
        
        // Buat objek Barang baru sesuai constructor di Barang.java
        // Constructor: (idKategori, idLokasi, kodeBarang, namaBarang, jumlahTotal, jumlahTersedia, kondisi)
        Barang barang1 = new Barang(
            1,                  // idKategori (Asumsi kategori 'Ruang Kelas' punya ID 1 di DB, Anda harus menginput kategori dulu)
            1,                  // idLokasi (Asumsi lokasi 'Kelas A' punya ID 1 di DB, Anda harus menginput lokasi dulu)
            "RKG001",           // kodeBarang
            "Meja Siswa",       // namaBarang
            50,                 // jumlahTotal
            50,                 // jumlahTersedia
            "Baik"              // kondisi
        );
        
        Barang barang2 = new Barang(
            2,                  // idKategori (Asumsi kategori 'Perpustakaan' punya ID 2)
            2,                  // idLokasi (Asumsi lokasi 'Rak Buku Fiksi' punya ID 2)
            "PBK005",           // kodeBarang
            "Buku Cerita Fiksi",// namaBarang
            100,                // jumlahTotal
            95,                 // jumlahTersedia
            "Baik"              // kondisi
        );
        
        System.out.println("Menambahkan Barang 1: " + barang1.getNamaBarang());
        if (barangDAO.addBarang(barang1)) {
            System.out.println("Barang 1 berhasil ditambahkan.");
        } else {
            System.err.println("Gagal menambahkan Barang 1. (Mungkin kode barang sudah ada atau ID kategori/lokasi tidak valid?)");
        }
        
        System.out.println("Menambahkan Barang 2: " + barang2.getNamaBarang());
        if (barangDAO.addBarang(barang2)) {
            System.out.println("Barang 2 berhasil ditambahkan.");
        } else {
            System.err.println("Gagal menambahkan Barang 2.");
        }


        // --- Bagian 2: Menguji Fungsi READ (Mengambil Semua Barang) ---
        System.out.println("\n--- Menguji Pengambilan Semua Barang ---");
        List<Barang> daftarSemuaBarang = barangDAO.getAllBarang();
        if (daftarSemuaBarang.isEmpty()) {
            System.out.println("Tidak ada barang dalam database.");
        } else {
            System.out.println("Daftar Barang yang Tersedia:");
            for (Barang b : daftarSemuaBarang) {
                System.out.println("  " + b.toString()); // Menggunakan toString() dari kelas Barang
            }
        }


        // --- Bagian 3: Menguji Fungsi UPDATE (Mengupdate Barang) ---
        System.out.println("\n--- Menguji Update Barang ---");
        // Asumsi kita ingin update barang pertama yang ditemukan atau barang dengan ID tertentu
        if (!daftarSemuaBarang.isEmpty()) {
            Barang barangUntukUpdate = daftarSemuaBarang.get(0); // Ambil barang pertama dari list
            System.out.println("Mengupdate Barang dengan ID: " + barangUntukUpdate.getIdBarang() + " (Nama Awal: " + barangUntukUpdate.getNamaBarang() + ")");
            
            barangUntukUpdate.setKondisi("Rusak Ringan"); // Ubah kondisi
            barangUntukUpdate.setJumlahTersedia(barangUntukUpdate.getJumlahTersedia() - 1); // Kurangi jumlah tersedia
            
            if (barangDAO.updateBarang(barangUntukUpdate)) {
                System.out.println("Barang berhasil diupdate: " + barangUntukUpdate.getNamaBarang());
            } else {
                System.err.println("Gagal mengupdate barang: " + barangUntukUpdate.getNamaBarang());
            }
        } else {
            System.out.println("Tidak ada barang untuk diupdate.");
        }


        // --- Bagian 4: Menguji Fungsi DELETE (Menghapus Barang) ---
        System.out.println("\n--- Menguji Penghapusan Barang ---");
        // Hapus barang dengan kode barang "RKG001" (atau ID tertentu)
        // Pertama, ambil barangnya dulu jika Anda ingin menghapus berdasarkan ID yang baru dibuat
        
        List<Barang> barangSebelumHapus = barangDAO.getAllBarang();
        if (!barangSebelumHapus.isEmpty()) {
            int idBarangUntukDihapus = barangSebelumHapus.get(0).getIdBarang(); // Hapus barang pertama yang ditemukan
            String namaBarangDihapus = barangSebelumHapus.get(0).getNamaBarang();

            System.out.println("Menghapus Barang dengan ID: " + idBarangUntukDihapus + " (Nama: " + namaBarangDihapus + ")");
            if (barangDAO.deleteBarang(idBarangUntukDihapus)) {
                System.out.println("Barang '" + namaBarangDihapus + "' berhasil dihapus.");
            } else {
                System.err.println("Gagal menghapus barang '" + namaBarangDihapus + "'.");
            }
        } else {
            System.out.println("Tidak ada barang untuk dihapus.");
        }
        
        // Cek lagi setelah penghapusan
        System.out.println("\nDaftar Barang Setelah Penghapusan:");
        List<Barang> daftarBarangAkhir = barangDAO.getAllBarang();
        if (daftarBarangAkhir.isEmpty()) {
            System.out.println("Tidak ada barang dalam database.");
        } else {
            for (Barang b : daftarBarangAkhir) {
                System.out.println("  " + b.toString());
            }
        }


        System.out.println("\n--- Pengujian Kelas Barang dan BarangDAO Selesai ---");
    }
}