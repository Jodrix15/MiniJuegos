/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.joystick.joystick.Joystick;
import com.juego.juegoplataforma.GamePanel;
import com.juego.juegoplataforma.Missile;
import com.juego.juegoplataforma.Moneda;
import com.juego.juegoplataforma.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Interfaz extends javax.swing.JFrame {
    private Player player;
    private GamePanel gamePanel;
    private Timer gameTimer;
    private static int opcion;
    private Dimension dimension;
    private ArrayList<Missile> missiles; // Lista de misiles
    private ArrayList<Moneda> monedas;  // Lista de monedas


    public Interfaz(int opcion) {
        this.dimension = new Dimension(500, 300);
        this.missiles = new ArrayList<>();
        monedas = new ArrayList<>();
        this.opcion = opcion;
        initComponents();
        initGame(opcion);
        configurarVentana();
    }
    
    private void configurarVentana() {
        // 1. Hacer la ventana no redimensionable
        setResizable(false);
        
        // 2. Tamaño fijo basado en el contenido
        pack();
        
        // 3. Centrar ventana
        setLocationRelativeTo(null);
    }
    
    private void initGame(int opcion) {
        
        
        
     switch(opcion){
         case 1:
             platformGame();
             break;
         case 2:
             System.out.println("Holaaa");
             break;
         case 3:
             break;
     }
    
}
   //A partir de aquí son métodos para el juego de plataforma
  private void platformGame() {
    // Configurar layout principal
    getContentPane().setLayout(new BorderLayout());

    // 2. Panel de juego (centro)
    player = new Player(100, 50);
    gamePanel = new GamePanel(player, dimension);
    getContentPane().add(gamePanel, BorderLayout.CENTER);
    
     // Inicializar lista de monedas
        
    resetMonedas();

    // 3. Panel inferior para controles
    JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    controlPanel.setBackground(Color.LIGHT_GRAY);

    // 4. Añadir componentes al panel de controles
    joystick1.setPreferredSize(new Dimension(200, 200));
    keyboardAction1.setPreferredSize(new Dimension(200, 70));
    controlPanel.add(keyboardAction1);
    controlPanel.add(joystick1);

    // 5. Añadir panel de controles al frame
    getContentPane().add(controlPanel, BorderLayout.SOUTH);

    // Configurar eventos para joystick
    joystick1.addJoystickEvents(new Joystick.JoystickEvents() {
        @Override public void onJoystickReleased() {
            player.setVelocityX(0);  // Detener movimiento horizontal cuando se suelta el joystick
        }
    });

    // Acción de disparo cuando presionamos la tecla ENTER
    keyboardAction1.setKeyAction(KeyEvent.VK_S, () -> {
        fireMissile();  // Disparar independientemente de si está en el aire o en el suelo
    });

    // Acción para el salto al presionar la barra espaciadora
    keyboardAction1.setKeyAction(KeyEvent.VK_SPACE, () -> {
        if (player.isOnGround()) {
            player.setVelocityY(-15);  // Realizar salto solo si está en el suelo
        }
    });

    // Iniciar juego
    gameTimer = new Timer(16, e -> updatePlatformGame());
    gameTimer.start();
}
private void resetMonedas(){
    monedas.clear();
    monedas.add(new Moneda(430, 80));  // Ejemplo de moneda en una posición
    monedas.add(new Moneda(430, 100));  // Otra moneda en otra posición
    monedas.add(new Moneda(430, 120));
    monedas.add(new Moneda(430, 140));
    monedas.add(new Moneda(430, 160));
    monedas.add(new Moneda(430, 180));
    monedas.add(new Moneda(430, 200));
    monedas.add(new Moneda(430, 220));
    monedas.add(new Moneda(430, 240));
    
    monedas.add(new Moneda(100, 160));
    monedas.add(new Moneda(120, 160));
    monedas.add(new Moneda(140, 160));
    
    monedas.add(new Moneda(140, 40));
    
        gamePanel.setMonedas(monedas);

    
}
private void updatePlatformGame() {
    Point2D.Double joyPos = joystick1.getNormalizedPosition();
    
    if (gamePanel.isGameOver()) {
        resetMonedas();
        return;
    }

    // Movimiento horizontal del jugador (izquierda/derecha)
    player.setVelocityX(joyPos.x * 5);

    // Salto solo si está en el suelo y el joystick está hacia arriba
    if (player.isOnGround() && joyPos.y < -0.7) {
        player.setVelocityY(-15);  // Salto
    }
    
    // Verificar si el jugador recoge alguna moneda
        for (Moneda moneda : monedas) {
            if (!moneda.isCollected() && moneda.checkCollision(player)) {
                // Aquí puedes agregar la lógica para cuando se recoge una moneda (por ejemplo, sumar puntos)
                System.out.println("¡Moneda recogida!");
            }
        }

    // Actualizar el estado del jugador y pintar el panel
    player.update(gamePanel.getPlatforms());
    gamePanel.repaint();
}

    
    private void fireMissile() {
        double startX = player.getX() + player.getWidth() / 2;
    double startY = player.getY() + player.getHeight() / 2;
        double velX = player.isFacingRight() ? 8 : -8;
        double velY = 0; // sin movimiento vertical

        Missile missile = new Missile(startX, startY, velX, velY);
        missiles.add(missile);
        gamePanel.setMissiles(missiles); // Asegúrate de que GamePanel tenga este método para actualizar los misiles
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        joystick1 = new com.joystick.joystick.Joystick();
        keyboardAction1 = new com.tecla.tecla.KeyboardAction();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout joystick1Layout = new javax.swing.GroupLayout(joystick1);
        joystick1.setLayout(joystick1Layout);
        joystick1Layout.setHorizontalGroup(
            joystick1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );
        joystick1Layout.setVerticalGroup(
            joystick1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout keyboardAction1Layout = new javax.swing.GroupLayout(keyboardAction1);
        keyboardAction1.setLayout(keyboardAction1Layout);
        keyboardAction1Layout.setHorizontalGroup(
            keyboardAction1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        keyboardAction1Layout.setVerticalGroup(
            keyboardAction1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(keyboardAction1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(joystick1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(433, 433, 433)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(joystick1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keyboardAction1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Método principal
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz(opcion).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.joystick.joystick.Joystick joystick1;
    private com.tecla.tecla.KeyboardAction keyboardAction1;
    // End of variables declaration//GEN-END:variables
}
