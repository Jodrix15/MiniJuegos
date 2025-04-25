package com.juego.juegoplataforma;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Enemy {
    private int x, y, width, height, speed;
    private boolean movingRight = true;
    private boolean alive = true;
    private final Platform platform;

    public Enemy(int x, int y, int width, int height, int speed, Platform platform) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.platform = platform;
    }

    public void update() {
        if (!alive) return;

        if (movingRight) {
            x += speed;
            if (x + width >= platform.getX() + platform.getWidth()) {
                movingRight = false;
            }
        } else {
            x -= speed;
            if (x <= platform.getX()) {
                movingRight = true;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        if (!alive) return;

        g2d.setColor(new Color(220, 20, 60)); // Rojo intenso
        g2d.fillRoundRect(x, y, width, height, 8, 8);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, width, height, 8, 8);

    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
