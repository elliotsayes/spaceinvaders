package GameScreen;

import GameEngine.AudioPlayer;
import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Bullet {

    Color color;// = Color.red;
    int size;// = 3;
    IntVector2D coordinates, velocity;
    int damage = 1;
    //AudioPlayer fire_sound = new AudioPlayer("shoot.wav","shoot");

    // Creates bullet at (x,y) with direction
    public Bullet(int x, int y, int verticalVelocity) {
        //ititialise with default colour and size
        this(x, y, verticalVelocity, Color.red, 3);
    }

    // general constructor;
    public Bullet(int x, int y, int verticalVelocity, Color color, int size) {
        this.coordinates = new IntVector2D(x,y);
        this.velocity = new IntVector2D(0,verticalVelocity);
        this.color = color;
        this.size = size;
        //fire_sound.playSound();
    }

    // moves bullet, controls movement pattern 
    void move() {
        coordinates.addVector(velocity);
    }

    // Returns x position
    int getX() {
        return coordinates.getX();
    }

    // Returns y position
    int getY() {
        return coordinates.getY();
    }

    // Controls what bulllets look like
    public void paint(Graphics2D win) {
        win.setColor(color);
        win.fillOval(coordinates.getX(), coordinates.getY(), size, size);
        // Main menu message
    }
    
    public void upgrade(GamePanel g){
        g.shooter.color = Color.WHITE;
        g.shooter.invincible = true;
        g.shooter.health -= damage;
        
        g.powerUpTimer = new Timer(1500, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.powerUpTimer.stop();
                g.shooter.color = Color.GREEN;
                g.shooter.invincible = false;
            }
        }));
        g.powerUpTimer.start();
    }
    

    
    
}
