package com.mycompany.project.kel.desktop.sarpas.controller;

import com.mycompany.project.kel.desktop.sarpas.dao.UserDAO;
import com.mycompany.project.kel.desktop.sarpas.model.User;

public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    public User authenticate(String userId, String password) {
        // DEBUG: Cetak userId dan password yang diterima controller
        System.out.println("DEBUG LoginController: Menerima userId = " + userId + ", password = [PASSWORD TERSEMBUNYI]");

        // Panggil metode authenticate dari UserDAO
        User user = userDAO.authenticate(userId, password);

        if (user != null) {
            System.out.println("DEBUG LoginController: Autentikasi berhasil untuk user: " + user.getUsername());
        } else {
            System.out.println("DEBUG LoginController: Autentikasi gagal untuk userId: " + userId);
        }
        return user;
    }
}