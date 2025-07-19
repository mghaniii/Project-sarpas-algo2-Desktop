package com.mycompany.project.kel.desktop.sarpas.controller;

import com.mycompany.project.kel.desktop.sarpas.dao.UserDAO;
import com.mycompany.project.kel.desktop.sarpas.model.User;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO(); // Inisialisasi DAO
    }

    /**
     * Mengotentikasi pengguna berdasarkan username dan password.
     * @param username Username yang dimasukkan.
     * @param password Password yang dimasukkan.
     * @return Objek User yang terautentikasi jika sukses, null jika gagal.
     */
    public User authenticate(String username, String password) {
        // --- Validasi awal input (opsional, bisa juga di Frontend) ---
        if (username == null || username.trim().isEmpty()) {
            System.err.println("Username tidak boleh kosong.");
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            System.err.println("Password tidak boleh kosong.");
            return null;
        }
        
        // Panggil metode autentikasi dari UserDAO
        User authenticatedUser = userDAO.authenticate(username, password);
        
        if (authenticatedUser != null) {
            System.out.println("Pengguna '" + username + "' berhasil login. Role: " + authenticatedUser.getRole());
        } else {
            System.out.println("Login gagal untuk pengguna: " + username + ". Periksa kredensial.");
        }
        return authenticatedUser;
    }

    /**
     * Menambah user baru ke database (contoh fungsi tambahan untuk registrasi).
     * @param user Objek User yang akan ditambahkan.
     * @return true jika berhasil, false jika gagal.
     */
    public boolean registerUser(User user) {
        // Anda bisa tambahkan validasi bisnis di sini sebelum memanggil DAO
        if (userDAO.getUserByUsername(user.getUsername()) != null) { // Cek apakah username sudah ada
            System.err.println("Username " + user.getUsername() + " sudah ada. Registrasi gagal.");
            return false;
        }
        
        return userDAO.createUser(user); // Panggil createUser dari UserDAO
    }

    // Anda bisa menambahkan metode controller lainnya di sini (misal: getUserDetails, updateUserProfile)
}