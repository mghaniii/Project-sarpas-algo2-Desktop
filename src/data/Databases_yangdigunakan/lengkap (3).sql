-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2025 at 01:02 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lengkap`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `id_kategori` int(11) NOT NULL,
  `id_lokasi` int(11) NOT NULL,
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(150) NOT NULL,
  `jumlah_total` int(11) NOT NULL,
  `jumlah_tersedia` int(11) NOT NULL,
  `kondisi` enum('baik','rusak berat','rusak ringan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `id_kategori`, `id_lokasi`, `kode_barang`, `nama_barang`, `jumlah_total`, `jumlah_tersedia`, `kondisi`) VALUES
(6, 2, 2, 'PBK005', 'Kursi', 100, 95, 'baik'),
(8, 3, 1, 'jTextField1', 'MIX', 3, 2, 'baik'),
(9, 4, 3, 'LPP099', 'LAPBOLA!', 2, 2, 'baik'),
(10, 1, 2, 'dwadw', 'AAAAAA', 12, 3, 'baik'),
(11, 1, 1, 'BRG-001', 'Meja dan Kursi Siswa', 40, 39, 'baik'),
(12, 3, 1, 'BRG-002', 'Papan Tulis', 2, 2, 'baik'),
(13, 3, 1, 'BRG-003', 'Spidol dan Penghapus', 10, 10, 'baik'),
(14, 2, 1, 'BRG-004', 'Jam Dinding', 2, 2, 'baik'),
(15, 2, 1, 'BRG-005', 'AC', 2, 2, 'baik'),
(16, 2, 1, 'BRG-006', 'Proyektor', 1, 1, 'rusak berat'),
(17, 7, 6, 'BRG-010', 'Bola Futsal', 3, 3, 'baik');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL,
  `deskripsi` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`, `deskripsi`) VALUES
(1, 'Perabotan', 'Aset berupa perabotan seperti meja, kursi, lemari.'),
(2, 'Elektronik', 'Aset berupa barang elektronik.'),
(3, 'ATK', 'Alat Tulis Kantor.'),
(4, 'Peralatan Medis', 'Peralatan dan obat-obatan untuk UKS.'),
(5, 'Peralatan Kebersihan', 'Alat-alat untuk menjaga kebersihan sekolah.'),
(6, 'Kendaraan', 'Aset berupa kendaraan bermotor.'),
(7, 'Peralatan Olahraga', 'Alat untuk kegiatan olahraga dan ekstrakurikuler.'),
(8, 'Buku', 'Buku pelajaran, fiksi, dan non-fiksi.'),
(9, 'Fasilitas Dapur', 'Peralatan yang ada di kantin atau dapur.');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_kerusakan`
--

CREATE TABLE `laporan_kerusakan` (
  `id_laporan` int(11) NOT NULL,
  `id_barang` int(11) DEFAULT NULL,
  `nama_pelapor` varchar(100) NOT NULL,
  `tanggal_pelaporan` timestamp NOT NULL DEFAULT current_timestamp(),
  `jenis_kerusakan` varchar(100) DEFAULT NULL,
  `lokasi_alat` varchar(100) DEFAULT NULL,
  `deskripsi_kerusakan` text NOT NULL,
  `url_foto_kerusakan` varchar(255) DEFAULT NULL,
  `status_laporan` varchar(50) NOT NULL DEFAULT 'Belum Diproses'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan_kerusakan`
--

INSERT INTO `laporan_kerusakan` (`id_laporan`, `id_barang`, `nama_pelapor`, `tanggal_pelaporan`, `jenis_kerusakan`, `lokasi_alat`, `deskripsi_kerusakan`, `url_foto_kerusakan`, `status_laporan`) VALUES
(1, 6, 'Budi Santoso Siswa', '2025-07-19 02:45:22', 'Tidak Diketahui', 'lab', 'patah', NULL, 'Diproses'),
(2, 8, 'Budi Santoso Siswa', '2025-07-25 13:22:21', 'Tidak Diketahui', 'Gedung A, Lt. 1', 'gak nyala lampu', NULL, 'Selesai'),
(3, 6, 'Budi Santoso Siswa', '2025-07-27 15:32:40', 'Tidak Diketahui', 'lab', 'irusak arah', NULL, 'Diproses'),
(4, 6, 'Budi Santoso Siswa', '2025-07-29 03:29:04', 'Tidak Diketahui', 'kelas 1', 'kotor dan retak', NULL, 'Belum Diproses'),
(5, 11, 'Budi Santoso Siswa', '2025-07-29 11:47:22', 'Tidak Diketahui', '-', '-', NULL, 'Belum Diproses'),
(6, 11, 'Budi Santoso Siswa', '2025-07-29 12:01:04', 'Tidak Diketahui', '-', '-', NULL, 'Belum Diproses'),
(7, 12, 'Budi Santoso Siswa', '2025-07-29 12:07:42', 'Tidak Diketahui', '-', '-', 'C:\\Users\\AXIOO\\Pictures\\Screenshots\\Screenshot 2025-03-08 193001.png', 'Diproses');

-- --------------------------------------------------------

--
-- Table structure for table `lokasi`
--

CREATE TABLE `lokasi` (
  `id_lokasi` int(11) NOT NULL,
  `nama_lokasi` varchar(100) NOT NULL,
  `deskripsi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lokasi`
--

INSERT INTO `lokasi` (`id_lokasi`, `nama_lokasi`, `deskripsi`) VALUES
(1, 'Ruang Kelas', 'Area semua ruang kelas siswa.'),
(2, 'Ruang Guru & TU', 'Area ruang kerja guru dan staff Tata Usaha.'),
(3, 'Perpustakaan', 'Area perpustakaan sekolah.'),
(4, 'Kantin', 'Area kantin sekolah.'),
(5, 'UKS', 'Unit Kesehatan Sekolah.'),
(6, 'Area Umum & Gudang', 'Area umum seperti lapangan, aula, dan gudang penyimpanan.');

-- --------------------------------------------------------

--
-- Table structure for table `pelihara`
--

CREATE TABLE `pelihara` (
  `id_pemeliharaan` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `id_users_petugas` int(11) NOT NULL,
  `tanggal_jadwal` date NOT NULL,
  `tanggal_selesai` date DEFAULT NULL,
  `jenis_perawatan` varchar(255) NOT NULL,
  `catatan_tambahan` text NOT NULL,
  `status` enum('terjadwal','sedang dikerjakan','selesai') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelihara`
--

INSERT INTO `pelihara` (`id_pemeliharaan`, `id_barang`, `id_users_petugas`, `tanggal_jadwal`, `tanggal_selesai`, `jenis_perawatan`, `catatan_tambahan`, `status`) VALUES
(1, 6, 4, '2025-07-27', '2025-07-27', 'Pengecekan Rutin', 'Cek fungsi ', 'selesai'),
(2, 6, 4, '2025-07-28', NULL, 'sada', 'asad', 'terjadwal'),
(3, 12, 5, '2025-07-30', NULL, 'Bersihkan sampai putih', '', 'terjadwal');

-- --------------------------------------------------------

--
-- Table structure for table `peminjam`
--

CREATE TABLE `peminjam` (
  `id_peminjaman` int(11) NOT NULL,
  `id_user_peminjam` int(11) DEFAULT NULL,
  `identitas_peminjam` varchar(50) DEFAULT NULL,
  `telf_peminjam` varchar(20) NOT NULL,
  `id_barang_dipinjam` int(11) DEFAULT NULL,
  `id_lokasi_dipinjam` int(11) DEFAULT NULL,
  `jumlah_barang` int(11) DEFAULT NULL,
  `waktu_mulai_pinjam` datetime NOT NULL,
  `waktu_selesai_pinjam` datetime NOT NULL,
  `durasi_peminjaman` varchar(50) NOT NULL,
  `alasan_kegiatan` text NOT NULL,
  `status_peminjaman` enum('Diajukan','Disetujui','Ditolak','Selesai') NOT NULL,
  `tanggal_pengajuan` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman_fasilitas`
--

CREATE TABLE `peminjaman_fasilitas` (
  `id_peminjaman` int(11) NOT NULL,
  `id_user_peminjam` int(11) DEFAULT NULL,
  `nama_peminjam` varchar(100) NOT NULL,
  `jenis_peminjam` varchar(50) NOT NULL,
  `no_telepon_peminjam` varchar(20) DEFAULT NULL,
  `id_barang_fk` int(11) DEFAULT NULL,
  `id_ruangan_fk` int(11) DEFAULT NULL,
  `jumlah_dipinjam` int(11) DEFAULT NULL,
  `nama_fasilitas_manual` varchar(255) DEFAULT NULL,
  `tanggal_peminjaman` date NOT NULL,
  `waktu_mulai` time NOT NULL,
  `waktu_selesai` time NOT NULL,
  `keperluan` text NOT NULL,
  `status_peminjaman` varchar(50) NOT NULL DEFAULT 'Menunggu Persetujuan',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman_fasilitas`
--

INSERT INTO `peminjaman_fasilitas` (`id_peminjaman`, `id_user_peminjam`, `nama_peminjam`, `jenis_peminjam`, `no_telepon_peminjam`, `id_barang_fk`, `id_ruangan_fk`, `jumlah_dipinjam`, `nama_fasilitas_manual`, `tanggal_peminjaman`, `waktu_mulai`, `waktu_selesai`, `keperluan`, `status_peminjaman`, `created_at`, `updated_at`) VALUES
(1, 2, 'Budi Santoso Siswa', 'Siswa', '12345', 6, NULL, 1, 'Kursi', '2025-07-19', '16:54:26', '17:54:26', 'eskulrapat', 'Sudah Dikembalikan', '2025-07-19 09:54:59', '2025-07-25 15:47:31'),
(2, 2, 'Budi Santoso Siswa', 'Siswa', '12345', NULL, 1, NULL, 'Aula Utama', '2025-07-26', '14:04:19', '15:04:19', 'main bola pel olahraga', 'Ditolak', '2025-07-26 07:05:50', '2025-07-26 13:33:12'),
(3, 2, 'Budi Santoso Siswa', 'Siswa', '12345', 8, NULL, 5, 'MIX', '2025-07-27', '20:29:35', '21:29:35', 'rapat eskul 2', 'Sudah Dikembalikan', '2025-07-26 13:32:10', '2025-07-27 04:44:43'),
(4, 2, 'Budi Santoso Siswa', 'Siswa', '12345', NULL, 2, NULL, 'Ruang Kelas B102', '2025-07-28', '13:58:11', '14:58:11', 'rapat pramuka', 'Sudah Dikembalikan', '2025-07-27 06:59:45', '2025-07-28 11:22:01'),
(5, 2, 'Budi Santoso Siswa', 'Siswa', '12345', 6, NULL, 1, 'Kursi', '2025-07-27', '14:00:00', '15:00:00', 'sdfasf', 'Sudah Dikembalikan', '2025-07-27 07:04:04', '2025-07-28 11:21:58'),
(6, 2, 'Budi Santoso Siswa', 'Siswa', '12345', 8, NULL, 1, 'MIX', '2025-07-27', '14:13:17', '15:13:17', 'dadwe', 'Disetujui', '2025-07-27 07:13:30', '2025-07-28 12:12:13'),
(7, 2, 'Budi Santoso Siswa', 'Siswa', '12345', NULL, 1, NULL, 'Aula Utama', '2025-07-27', '14:13:31', '15:13:31', 'nyanyi', 'Disetujui', '2025-07-27 07:14:34', '2025-07-28 12:20:43'),
(8, 2, 'Budi Santoso Siswa', 'Siswa', '12345', 9, NULL, 1, 'LAPBOLA!', '2025-07-28', '19:12:35', '20:12:35', 'voli', 'Ditolak', '2025-07-28 12:12:51', '2025-07-28 12:21:12'),
(9, 2, 'Budi Santoso Siswa', 'Siswa', '12345', NULL, 1, NULL, 'Aula Utama', '2025-07-29', '13:16:05', '15:16:05', 'rapat ijazah', 'Disetujui', '2025-07-28 12:16:45', '2025-07-28 12:20:55'),
(10, 2, 'Budi Santoso Siswa', 'Siswa', '12345', NULL, 2, NULL, 'Ruang Kelas B102', '2025-07-29', '18:47:42', '19:47:42', '--', 'Disetujui', '2025-07-29 11:47:58', '2025-07-29 15:32:47'),
(11, 2, 'Budi Santoso Siswa', 'Siswa', '12345', 12, NULL, 1, 'Papan Tulis', '2025-07-30', '14:12:01', '15:12:01', 'Mengajar karena di rungan sangat kotor', 'Disetujui', '2025-07-30 07:12:40', '2025-07-30 10:07:28'),
(12, 2, 'Budi Santoso Siswa', 'Siswa', '12345', NULL, 2, NULL, 'Ruang Kelas B102', '2025-07-31', '17:09:41', '18:09:41', 'rapat osis', 'Menunggu Persetujuan', '2025-07-31 10:09:58', '2025-07-31 10:09:58');

-- --------------------------------------------------------

--
-- Table structure for table `perbaikan`
--

CREATE TABLE `perbaikan` (
  `id_perbaikan` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `id_users_pelapor` int(11) NOT NULL,
  `id_user_teknisi` int(11) NOT NULL,
  `deskripsi_kerusakan` text NOT NULL,
  `foto_kerusakan` varchar(255) DEFAULT NULL,
  `tanggal_lapor` varchar(255) NOT NULL,
  `tanggal_selesai` date NOT NULL,
  `status_perbaikan` enum('belum diproses','diproses','selesai') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `id_ruangan` int(11) NOT NULL,
  `nama_ruangan` varchar(100) NOT NULL,
  `kapasitas` int(11) DEFAULT NULL,
  `lokasi_detail` varchar(255) DEFAULT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`id_ruangan`, `nama_ruangan`, `kapasitas`, `lokasi_detail`, `deskripsi`) VALUES
(1, 'Aula Utama', 300, 'Gedung A', NULL),
(2, 'Ruang Kelas B102', 30, 'Gedung B, Lt.1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_users` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `role` enum('admin','guru','teknisi','siswa') NOT NULL,
  `nomor_induk` varchar(30) DEFAULT NULL,
  `nisn` varchar(20) DEFAULT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `agama` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `username`, `password`, `nama_lengkap`, `role`, `nomor_induk`, `nisn`, `tempat_lahir`, `tanggal_lahir`, `agama`, `alamat`, `create_time`) VALUES
(2, 'budi.santoso', 'budi123', 'Budi Santoso Siswa', 'siswa', '12345', '0012345678', 'Bandung', '2005-05-10', 'Islam', 'Jl. Mawar No. 10', '2025-07-15 10:34:12'),
(3, 'guru.mtk', 'guru123', 'Bapak Andi Guru', 'guru', '98765', '', 'Jakarta', '1980-01-01', 'Kristen', 'Jl. Anggrek No. 5', '2025-07-15 10:34:12'),
(4, 'admin_sarpas', 'sarpas123', 'Toto Admin', 'admin', NULL, NULL, 'Bandung', '2002-07-01', 'islam', 'jl. mohtoha', '2025-07-29 09:46:35'),
(5, 'teknisi_sarpas1', 'teknisi123', 'Asep Kasep', 'teknisi', NULL, NULL, 'Bandung', '2000-07-05', 'islam', 'jl. soekarno hatta', '2025-07-29 09:46:54');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `laporan_kerusakan`
--
ALTER TABLE `laporan_kerusakan`
  ADD PRIMARY KEY (`id_laporan`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `lokasi`
--
ALTER TABLE `lokasi`
  ADD PRIMARY KEY (`id_lokasi`);

--
-- Indexes for table `pelihara`
--
ALTER TABLE `pelihara`
  ADD PRIMARY KEY (`id_pemeliharaan`);

--
-- Indexes for table `peminjam`
--
ALTER TABLE `peminjam`
  ADD PRIMARY KEY (`id_peminjaman`);

--
-- Indexes for table `peminjaman_fasilitas`
--
ALTER TABLE `peminjaman_fasilitas`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `fk_peminjaman_user` (`id_user_peminjam`),
  ADD KEY `fk_peminjaman_barang` (`id_barang_fk`),
  ADD KEY `fk_peminjaman_ruangan` (`id_ruangan_fk`);

--
-- Indexes for table `perbaikan`
--
ALTER TABLE `perbaikan`
  ADD PRIMARY KEY (`id_perbaikan`);

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id_ruangan`),
  ADD UNIQUE KEY `nama_ruangan` (`nama_ruangan`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`),
  ADD UNIQUE KEY `nama` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `laporan_kerusakan`
--
ALTER TABLE `laporan_kerusakan`
  MODIFY `id_laporan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `lokasi`
--
ALTER TABLE `lokasi`
  MODIFY `id_lokasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pelihara`
--
ALTER TABLE `pelihara`
  MODIFY `id_pemeliharaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `peminjam`
--
ALTER TABLE `peminjam`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `peminjaman_fasilitas`
--
ALTER TABLE `peminjaman_fasilitas`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `perbaikan`
--
ALTER TABLE `perbaikan`
  MODIFY `id_perbaikan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ruangan`
--
ALTER TABLE `ruangan`
  MODIFY `id_ruangan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `laporan_kerusakan`
--
ALTER TABLE `laporan_kerusakan`
  ADD CONSTRAINT `laporan_kerusakan_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `peminjaman_fasilitas`
--
ALTER TABLE `peminjaman_fasilitas`
  ADD CONSTRAINT `fk_peminjaman_barang` FOREIGN KEY (`id_barang_fk`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `fk_peminjaman_ruangan` FOREIGN KEY (`id_ruangan_fk`) REFERENCES `ruangan` (`id_ruangan`),
  ADD CONSTRAINT `fk_peminjaman_user` FOREIGN KEY (`id_user_peminjam`) REFERENCES `users` (`id_users`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
