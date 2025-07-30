package com.mycompany.project.kel.desktop.sarpas.util;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Base64; // Import baru untuk Base64

public class ImageUtil {

    /**
     * Membuat ImageIcon dari string Base64 dan mengubah ukurannya.
     * @param base64String String Base64 dari gambar.
     * @param width Lebar yang diinginkan.
     * @param height Tinggi yang diinginkan.
     * @return ImageIcon yang ukurannya sudah diubah.
     */
    public static ImageIcon getIconFromBase64(String base64String, int width, int height) {
        try {
            // Hapus bagian header "data:image/png;base64," jika ada
            if (base64String.startsWith("data:image")) {
                base64String = base64String.substring(base64String.indexOf(",") + 1);
            }

            // Ubah string Base64 menjadi byte array
            byte[] imageBytes = Base64.getDecoder().decode(base64String);
            
            // Buat ImageIcon dari byte array
            ImageIcon originalIcon = new ImageIcon(imageBytes);
            Image originalImage = originalIcon.getImage();
            
            // Ubah ukuran gambar
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.err.println("Gagal membuat ikon dari Base64: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
