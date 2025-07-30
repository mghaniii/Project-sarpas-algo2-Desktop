package com.mycompany.project.kel.desktop.sarpas.model;

// import java.time.LocalDateTime; // Mungkin tidak diperlukan jika create_time bukan LocalDateTime di Java
import java.sql.Date; // Jika tanggal_lahir adalah DATE SQL dan ingin pakai java.sql.Date

public class User {
    private int idUsers;        // id_users INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
    private String username;    // username VARCHAR(50) NOT NULL UNIQUE
    private String password;    // password VARCHAR(255) NOT NULL (sebelumnya passwordHash)
    private String namaLengkap; // nama_lengkap VARCHAR(100) NOT NULL
    private String role;        // role VARCHAR(50) NOT NULL

    // Atribut opsional/nullable sesuai tabel baru Anda
    private String nomorInduk;  // nomor_induk
    private String nisn;        // nisn
    private String tempatLahir; // tempat_lahir
    private Date tanggalLahir;  // tanggal_lahir (gunakan java.sql.Date)
    private String agama;       // agama
    private String alamat;      // alamat
    private java.sql.Timestamp createTime; // create_time (gunakan java.sql.Timestamp)

    // --- CONSTRUCTOR ---
    // Constructor untuk User baru (tanpa ID dari DB dan create_time)
    public User(String username, String password, String namaLengkap, String role,
                String nomorInduk, String nisn, String tempatLahir, Date tanggalLahir,
                String agama, String alamat) {
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.role = role;
        this.nomorInduk = nomorInduk;
        this.nisn = nisn;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.agama = agama;
        this.alamat = alamat;
    }

    // Constructor lengkap (untuk user yang diambil dari DB, dengan ID dan create_time)
    public User(int idUsers, String username, String password, String namaLengkap, String role,
                String nomorInduk, String nisn, String tempatLahir, Date tanggalLahir,
                String agama, String alamat, java.sql.Timestamp createTime) {
        this(username, password, namaLengkap, role, nomorInduk, nisn, tempatLahir, tanggalLahir, agama, alamat); // Panggil constructor di atas
        this.idUsers = idUsers;
        this.createTime = createTime;
    }
public User() {
}
    
    // Saya hanya berikan beberapa contoh.

    public int getIdUsers() { return idUsers; }
    public void setIdUsers(int idUsers) { this.idUsers = idUsers; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getNomorInduk() { return nomorInduk; }
    public void setNomorInduk(String nomorInduk) { this.nomorInduk = nomorInduk; }

    public String getNisn() { return nisn; }
    public void setNisn(String nisn) { this.nisn = nisn; }

    public String getTempatLahir() { return tempatLahir; }
    public void setTempatLahir(String tempatLahir) { this.tempatLahir = tempatLahir; }

    public Date getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(Date tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getAgama() { return agama; }
    public void setAgama(String agama) { this.agama = agama; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public java.sql.Timestamp getCreateTime() { return createTime; }
    public void setCreateTime(java.sql.Timestamp createTime) { this.createTime = createTime; }

    @Override
public String toString() {
    return this.namaLengkap;
}
}
