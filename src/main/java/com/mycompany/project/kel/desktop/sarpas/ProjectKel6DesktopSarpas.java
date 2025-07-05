package com.mycompany.project.kel.desktop.sarpas;

import com.mycompany.project.kel.desktop.sarpas.view.MainFrame;
import javax.swing.SwingUtilities;

public class ProjectKel6DesktopSarpas {

    public static void main(String[] args) {
        System.out.println("1. Aplikasi dimulai. Memanggil SwingUtilities.invokeLater()"); // Tambahkan ini
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("2. Berada di dalam EDT. Membuat instance MainFrame."); // Tambahkan ini
                new MainFrame();
                System.out.println("3. Instance MainFrame dibuat. Harusnya jendela sudah terlihat."); // Tambahkan ini
            }
        });
        System.out.println("4. Metode main selesai dieksekusi."); // Tambahkan ini
    }
}