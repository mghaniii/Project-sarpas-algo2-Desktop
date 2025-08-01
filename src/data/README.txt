
# Nama dan Tugas Masing-masing Anggota

1. Muhammad Ghani (Fitur Inventaris Barang dan Form Login dan menintegrasikan keseluruhan tugas )
Backend role siswa diantaranya pada bagian menampilkan home/profile dll

2. Dafa Irsyad Nashrullah (Mendesain laporan kerusakan Peminjaman fasilitas, mendesain tampilan UI/UX  dan admin dan Admin?teknisi update status peminjaman )
  - https://balsamiq.cloud/sa37fei/pbym5u5/r03F2 (Desain)

3. Dimas Arya Purnama Alam (Pelaporan kerusakan, fitur pemeliharaan berkala teknisi /perawatan  (bagian Front End))

4. Sandika Pryatna (bagian Front End, pemeliharaan berkala, sidebar kiri dan bagian login  )

5. Resti Istihany Putri (Dashboard status asset dan peminjaman form)


#Arsitektur dan Desain Sistem

Proyek ini dibangun menggunakan arsitektur 3-Tier dengan pola desain Model-View-Controller (MVC) yang dimodifikasi untuk aplikasi desktop, yaitu dengan memisahkan logika menjadi tiga lapisan utama:

- View (Tampilan): Ini adalah lapisan antarmuka pengguna (GUI) yang dibuat menggunakan Java Swing. Setiap fitur utama (seperti Inventaris, Laporan, Peminjaman) memiliki panelnya sendiri (JPanel). Lapisan ini bertanggung jawab untuk menampilkan data kepada pengguna dan menerima input dari pengguna.

Contoh: LoginPanel.java, DashboardPanel.java, InventarisAdminPanel.java

- Model (Data): Lapisan ini merepresentasikan struktur data dari aplikasi. Setiap tabel di database memiliki kelas modelnya sendiri. Kelas-kelas ini berfungsi sebagai "cetakan" untuk objek data yang digunakan di seluruh aplikasi.

Contoh: User.java, Barang.java, Peminjaman.java

- DAO (Data Access Object): Ini adalah lapisan jembatan antara aplikasi dan database MySQL. Setiap kelas model memiliki DAO-nya sendiri yang bertanggung jawab untuk semua operasi database (CRUD: Create, Read, Update, Delete) yang berhubungan dengan model tersebut. Ini memisahkan logika bisnis dari logika database.

Contoh: UserDAO.java, BarangDAO.java, PeminjamanDAO.java dll

Arsitektur ini membuat kode menjadi lebih terstruktur, mudah dikelola, dan mudah dikembangkan di masa depan.

#Fitur Utama dan Alur Kerja Berdasarkan Peran

Aplikasi ini menerapkan Role-Based Access Control (RBAC), di mana fungsionalitas dan tampilan disesuaikan berdasarkan peran pengguna yang login.

a. Peran Admin
-Admin memiliki hak akses tertinggi dan berfungsi sebagai pengelola utama sistem.

-Dashboard: Melihat ringkasan statistik seluruh sistem (total aset, jumlah barang rusak, jadwal perawatan, dll.).

-Manajemen Inventaris: Memiliki akses penuh (CRUD) untuk menambah, mengubah, dan menghapus data master barang, kategori, dan lokasi.

-Manajemen Peminjaman: Melihat semua permintaan peminjaman dan memiliki wewenang untuk menyetujui (approve), menolak (reject), atau menandai peminjaman sebagai "Sudah Dikembalikan".

-Manajemen Laporan Kerusakan: Melihat semua laporan yang masuk dan dapat mengubah statusnya (misalnya dari "Baru" menjadi "Diproses" untuk diteruskan ke teknisi).

-Manajemen Jadwal Perawatan: Dapat membuat jadwal perawatan baru untuk aset tertentu dan menugaskannya kepada petugas (teknisi/admin).

b. Peran Teknisi
-Teknisi adalah pelaksana di lapangan yang fokus pada perbaikan dan perawatan.

-Dashboard: Melihat ringkasan tugas yang relevan, seperti jumlah laporan yang perlu ditindaklanjuti.

-Melihat Laporan Kerusakan: Melihat daftar laporan yang sudah divalidasi oleh Admin sebagai "daftar tugas".

-Update Status Laporan/Perawatan: Dapat mengubah status laporan atau jadwal perawatan (misalnya dari "Terjadwal" menjadi "Sedang Dikerjakan", lalu "Selesai").

-Update Kondisi Barang: Setelah perbaikan selesai, teknisi dapat mengakses panel inventaris untuk mengubah kondisi barang (misalnya dari "Rusak" menjadi "Baik"). Tombol untuk menambah atau menghapus barang disembunyikan.

c. Peran Siswa/ guru opsi
Pengguna umum yang berinteraksi dengan fasilitas sekolah.

-Dashboard/Profil: Melihat ringkasan aktivitas pribadi, seperti status peminjaman dan laporan yang pernah dibuat.

-Melihat Inventaris: Dapat melihat daftar barang dan fasilitas yang tersedia di sekolah.

-Mengajukan Peminjaman: Mengisi form untuk membuat permintaan peminjaman fasilitas. Status awal adalah "Menunggu Persetujuan".

-Membuat Laporan Kerusakan: Mengisi form untuk melaporkan kerusakan barang, lengkap dengan deskripsi dan (opsional) foto bukti.

4. Teknologi yang Digunakan

Bahasa Pemrograman: Java

Versi JDK : 24

Framework GUI: Java Swing

Database: MySQL

Sistem Build & Manajemen Library: Apache Maven

Library Tambahan:

mysql-connector-j: Untuk menghubungkan aplikasi Java dengan database MySQL.

jcalendar: Untuk komponen pemilih tanggal (JDateChooser)

