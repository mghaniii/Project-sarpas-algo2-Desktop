package com.mycompany.project.kel.desktop.sarpas.util;

import com.mycompany.project.kel.desktop.sarpas.model.User;

public class GlobalAppState {
    private static GlobalAppState instance;
    private User currentUser;

    private GlobalAppState() { }

    public static GlobalAppState getInstance() {
        if (instance == null) {
            instance = new GlobalAppState();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        System.out.println("User yang sedang login: " + (currentUser != null ? currentUser.getUsername() + " (" + currentUser.getRole() + ")" : "Tidak ada"));
    }

    // --- UBAH METODE isRole() BERIKUT INI ---

    public boolean isAdmin() {
        return currentUser != null && "Admin".equalsIgnoreCase(currentUser.getRole());
    }

    public boolean isTeknisi() {
        return currentUser != null && "Teknisi".equalsIgnoreCase(currentUser.getRole());
    }

    public boolean isGuru() {
        return currentUser != null && "Guru".equalsIgnoreCase(currentUser.getRole());
    }

    public boolean isSiswa() {
        return currentUser != null && "Siswa".equalsIgnoreCase(currentUser.getRole());
    }
    
    // --- AKHIR PERUBAHAN METODE isRole() ---


    public void logout() {
        this.currentUser = null;
        System.out.println("User berhasil logout.");
    }
}