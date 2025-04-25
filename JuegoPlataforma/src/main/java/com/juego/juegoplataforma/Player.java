package com.juego.juegoplataforma;

import java.util.List;

public class Player {
    private int x, y;
    private int width = 15, height = 30;
    private double velocityX, velocityY;
    private boolean onGround;
    private double gravity = 0.8;  // Aumentamos la gravedad (antes 0.5)
    private double jumpForce = -12; // Reducimos fuerza inicial (antes -15)
    private double maxFallSpeed = 10; // Velocidad máxima de caída
    private boolean facingRight = true;
    
    
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public java.awt.Rectangle getBounds() {
        return new java.awt.Rectangle(x, y, width, height);
    }
    
    public void reset() {
    this.x = 100;      // Posición inicial X
    this.y = 50;     // Posición inicial Y
    this.velocityY = 0; // Reinicia la caída
    // Si tienes más estados como salto, vidas, animaciones, los reseteas aquí
}

    
    // Actualizado para manejar plataformas
    public void update(List<Platform> platforms) {
        // Aplicar gravedad (más fuerte)
        velocityY += gravity;
        
        // Limitar velocidad de caída
        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }
        // Aplicar gravedad si no está en el suelo
        if (!onGround) {
            velocityY += 0.5;
        }
        
        // Actualizar posición X y verificar colisiones
        x += velocityX;
        for (Platform p : platforms) {
            if (collidesWith(p)) {
                if (velocityX > 0) x = p.getX() - width;
                else if (velocityX < 0) x = p.getX() + p.getWidth();
                velocityX = 0;
            }
        }
        
        // Actualizar posición Y y verificar colisiones
        y += velocityY;
        onGround = false;
        for (Platform p : platforms) {
            if (collidesWith(p)) {
                if (velocityY > 0) {
                    y = p.getY() - height;
                    onGround = true;
                } else if (velocityY < 0) {
                    y = p.getY() + p.getHeight();
                }
                velocityY = 0;
            }
        }
        
        // Limitar al área de juego (ajusta estos valores según tu GamePanel)
        if (x < 0) x = 0;
        if (x > 500 - width) x = 800 - width;
        if (y > 300) {
            // Respawneo simple si cae
            y = 50;
            x = 50;
        }
    }
    
    public void jump() {
        if (onGround) {
            velocityY = jumpForce;
            onGround = false;
        }
    }
    
    private boolean collidesWith(Platform p) {
        return x < p.getX() + p.getWidth() &&
               x + width > p.getX() &&
               y < p.getY() + p.getHeight() &&
               y + height > p.getY();
    }
    
    public void setVelocityX(double velX) {
        this.velocityX = velX;
        if (velocityX > 0) {
            facingRight = true;
        } else if (velX < 0) {
            facingRight = false;
        }
    }
    
    public double getVelocityX(){
        return this.velocityX;
    }

    public boolean isFacingRight() {
        return facingRight;
    }
    
    // Getters y setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isOnGround() { return onGround; }
    public void setVelocityY(double vy) { velocityY = vy; }
}