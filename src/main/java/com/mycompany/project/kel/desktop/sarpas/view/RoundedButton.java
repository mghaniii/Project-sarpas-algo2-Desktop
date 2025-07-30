package com.mycompany.project.kel.desktop.sarpas.view; // Pastikan nama package ini benar

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    private Color backgroundColor;
    private int cornerRadius = 15; // Anda bisa mengubah nilai ini untuk tingkat kelengkungan

    public RoundedButton() {
        super();
        // Atur warna default
        this.backgroundColor = new Color(21, 128, 250); // Warna biru
        setContentAreaFilled(false); // Penting agar background kustom kita terlihat
        setFocusPainted(false); // Hilangkan border saat tombol difokus
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gambar background rounded
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        
        g2.setColor(Color.BLACK);
         g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        // Gambar teks tombol di atasnya
        super.paintComponent(g);
        g2.dispose();
    }

    // (Opsional) Metode untuk mengubah warna secara dinamis
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint(); // Gambar ulang tombol dengan warna baru
    }
}
