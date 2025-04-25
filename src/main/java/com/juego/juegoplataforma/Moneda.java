/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juego.juegoplataforma;

/**
 *
 * @author admin
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.awt.geom.Rectangle2D;

import java.awt.Graphics2D;

import java.awt.Graphics2D;

public class Moneda {
    private double x, y;
    private final int size = 10;  // Tamaño de la moneda
    private boolean isCollected = false;  // Si la moneda ha sido recogida

    // Constructor
    public Moneda(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Método para dibujar la moneda
    public void draw(Graphics2D g2d) {
        if (!isCollected) {
            g2d.setColor(new Color(255, 215, 0)); // Dorado
            g2d.fillOval((int) x - size / 2, (int) y - size / 2, size, size);
            g2d.setColor(new Color(184, 134, 11)); // Borde bronce
            g2d.drawOval((int) x - size / 2, (int) y - size / 2, size, size);
        }

    }

    // Método para detectar si el jugador recoge la moneda
    public boolean checkCollision(Player player) {
        Rectangle2D playerBounds = player.getBounds();
        Rectangle2D coinBounds = new Rectangle2D.Double(x - size / 2, y - size / 2, size, size);

        if (playerBounds.intersects(coinBounds)) {
            isCollected = true;  // Marca la moneda como recogida
            return true;  // El jugador recogió la moneda
        }
        return false;
    }

    // Obtener la posición de la moneda
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Verificar si la moneda ha sido recogida
    public boolean isCollected() {
        return isCollected;
    }
    
    public void setCollected(boolean collected) {
        this.isCollected = collected;
    }
}
