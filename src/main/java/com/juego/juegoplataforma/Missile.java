package com.juego.juegoplataforma;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author admin
 */
public class Missile {
        private double x, y;
        private double velX, velY;
        private final int radius = 5;
        
        public Missile(double x, double y, double velX, double velY) {
            this.x = x;
            this.y = y;
            this.velX = velX;
            this.velY = velY;
        }
        
        public void update() {
            x += velX;
            y += velY;
        }
        
        public boolean isOffScreen(int screenWidth) {
            return x < 0 || x > screenWidth || y < 0 || y > 450;
        }
        
        public void draw(Graphics2D g2d) {
            g2d.setColor(new Color(255, 140, 0)); // Naranja fuerte
            g2d.fillOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);
            g2d.setColor(Color.RED);
            g2d.drawOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);

        }
        
        public Rectangle2D getBounds() {
            return new Rectangle2D.Double(x - radius, y - radius, radius * 2, radius * 2);
        }
    }
    
    