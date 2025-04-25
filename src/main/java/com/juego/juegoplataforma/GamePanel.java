package com.juego.juegoplataforma;
import gui.MenuPrincipal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
    private final Player player;
    private final List<Platform> platforms;
    private final List<Enemy> enemies;
    private ArrayList<Missile> missiles = new ArrayList<>();
    private ArrayList<Moneda> monedas = new ArrayList<>();
    

    
    
    private boolean isGameOver = false;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean value) {
        isGameOver = value;
    }
    
    public GamePanel(Player player, Dimension dimension) {
    this.player = player;
    this.platforms = new ArrayList<>();
    this.enemies = new ArrayList<>();
    initPlatforms();
    setBackground(new Color(135, 206, 235)); // Color cielo
    setPreferredSize(new Dimension(500, 300)); // Nuevo tamaño
}

private void initPlatforms() {
    // Ajusté todas las coordenadas y tamaños proporcionalmente
    
    // Plataforma base (ahora en y=250 con altura 30)
    platforms.add(new Platform(0, 280, 500, 30));
    
    //pared invisible derecha
    platforms.add(new Platform(500, 0, 10, 300));
    
    //pared invisible arriba
    platforms.add(new Platform(0, -10, 500, 0));

    
    // Plataformas flotantes (ajustadas proporcionalmente)
    platforms.add(new Platform(60, 200, 125, 15));  // Original: 100,450,200,20
    platforms.add(new Platform(230, 150, 125, 15)); // Original: 400,350,200,20
    platforms.add(new Platform(125, 80, 125, 15)); // Original: 200,250,200,20
    
    // Dentro del constructor de GamePanel
   
    crearEnemigos();
}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        // Sustituye en GamePanel
    g2d.setColor(new Color(30, 144, 255)); // Azul más vivo
    g2d.fillRoundRect(player.getX(), player.getY(), player.getWidth(), player.getHeight(), 10, 10);
    g2d.setColor(Color.BLACK);
    g2d.drawRoundRect(player.getX(), player.getY(), player.getWidth(), player.getHeight(), 10, 10);


// Actualizar y dibujar misiles
for (int i = 0; i < missiles.size(); i++) {
    Missile m = missiles.get(i);
    m.update();

    // Eliminar misiles fuera de la pantalla
    if (m.isOffScreen(getWidth())) {
        missiles.remove(i);
        i--;
    } else {
        m.draw(g2d);
    }
}
        
        // Dibujar plataformas
        for (Platform p : platforms) {
            // Parte marrón de la tierra
            g2d.setColor(new Color(139, 69, 19));
            g2d.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());

            // Hierba encima
            g2d.setColor(new Color(34, 139, 34));
            g2d.fillRect(p.getX(), p.getY(), p.getWidth(), 5); // solo arriba
        }

        
        // Actualizar enemigos antes de dibujar (puedes mover esto al bucle principal si lo tienes)
        
        for (Enemy enemy : enemies) {
            enemy.update();
        }

        // Comprobar colisión del jugador con los enemigos
        if (checkCollisionWithEnemies()) {
            showEndGameDialog("Game Over", "Info");
        }
        // Dibujar monedas
        for (Moneda moneda : monedas) {
            if (!moneda.isCollected()) {
                moneda.draw(g2d);
            }
        }
        
       checkVictoryCondition();
        
        

        // Dibujar enemigos
        g.setColor(Color.RED);
        for (Enemy e : enemies) {
            g.fillRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
}

        
        // Dibujar jugador
        g.setColor(Color.BLUE);
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }
    
    public List<Platform> getPlatforms() {
        return platforms; // Retorna la lista inmutable
    }
    private boolean checkCollisionWithEnemies() {
    // Colisión jugador - enemigo
    for (Enemy e : enemies) {
        if (player.getBounds().intersects(new java.awt.Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight()))) {
            return true;
        }
    }

    // Colisión misil - enemigo
    for (int i = 0; i < missiles.size(); i++) {
        Missile m = missiles.get(i);
        for (int j = 0; j < enemies.size(); j++) {
            Enemy e = enemies.get(j);
            if (m.getBounds().intersects(new java.awt.Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight()))) {
                missiles.remove(i);
                enemies.remove(j);
                i--; // Ajustamos índice tras eliminar
                break;
            }
        }
    }

    return false;
}
    
    public void setMissiles(ArrayList<Missile> missiles1) {
        this.missiles = missiles1;
    }
    
    public void setMonedas(ArrayList<Moneda> monedas) {
        this.monedas = monedas;
    }

    public void crearEnemigos(){
         enemies.add(new Enemy(430, 260, 20, 20, 2, platforms.get(0)));
        enemies.add(new Enemy(60, 260, 20, 20, 2, platforms.get(0)));

        // Dentro del constructor de GamePanel
        enemies.add(new Enemy(230, 130, 20, 20, 2, platforms.get(4)));

        // Dentro del constructor de GamePanel
        enemies.add(new Enemy(125, 60, 20, 20, 2, platforms.get(5)));
    }
    
    
    public void fireMissile() {
    // Puedes ajustar dirección y velocidad según si quieres que apunte a donde mira, etc.
    Missile missile = new Missile(
        player.getX() + player.getWidth() / 2,
        player.getY(),
        5, // velocidad X
        0  // sin movimiento vertical, puedes ajustar esto para diagonales
    );
    missiles.add(missile);
}
    private void showEndGameDialog(String message, String title) {
        String op = "Reintentar";
    if (isGameOver) return;
    if(message.equalsIgnoreCase("Victoria")){
        op = "Volver a jugar";
    }
    isGameOver = true;
    int option = javax.swing.JOptionPane.showOptionDialog(
        this,
        message,
        title,
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.INFORMATION_MESSAGE,
        null,
        new String[]{op, "Volver al menú"},
        "Reintentar"
    );

    if (option == javax.swing.JOptionPane.YES_OPTION) {
        restartGame();
    } else {
        javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
        new MenuPrincipal().setVisible(true);
    }
}
private void checkVictoryCondition() {
    if (isGameOver) return;

    boolean allCollected = true;
    for (Moneda m : monedas) {
        if (!m.isCollected()) {
            allCollected = false;
            break;
        }
    }

    if (allCollected) {
        showEndGameDialog("Victoria", "Info");
    }
}

    private void restartGame() {
        isGameOver = false;
        player.reset(); // Asegúrate de tener un método reset() en Player
        enemies.clear();
        initPlatforms();
        repaint();
    }



}