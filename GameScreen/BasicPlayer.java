
package GameScreen;

import GameEngine.AudioHandler;
import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BasicPlayer {

    // co-ordinates of player
    //int x;
    //int y;
    IntVector2D coordinates;

    // player size
    int width;
    int height;

    int fireRate;
    boolean move_left, move_right, shoot;
    Color color;
    int score;
    int health;
    BulletHandler bullets;// = new BulletHandler(-1, fireRate);
    Timer fire_timer;
    int fire_rate = 5;
    boolean can_shoot = true;
    //Entite Audio Handler
    AudioHandler soundEffects = new AudioHandler();
    boolean invincible = false;
    // Unused
    //int xa;
    //ImageIcon image;
    // Player constructor
    public BasicPlayer() {
        // call secondary constructor with default width and height
        this(60, 10);
    }

    public BasicPlayer(int width, int height) {
        // call main constructor with default values
        this(new IntVector2D(370,500), width, height, 1000, Color.GREEN, 0, 3);
    }

    public BasicPlayer(IntVector2D coordinates, int width, int height, int fireRate, Color color, int score, int health) {
        soundEffects.add("BananaSlap.wav","hit");
        this.coordinates = coordinates;
        this.height = height;
        this.width = width;
        this.fireRate = fireRate;
        this.color = color;
        this.score = score;
        this.health = health;
         this.fire_timer = new Timer(1000/fire_rate, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can_shoot = true;
                fire_timer.stop();
            }
        }));
        //this.bullets = new BulletHandler(-1, fireRate);
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Moves Player as well as bullets
    public void move(JPanel win, BulletHandler bullets) {
        if (move_left && (coordinates.getX() - 1 > 0 && coordinates.getX() - 1 < win.getWidth() - width)) {
            coordinates.setX(coordinates.getX() - 1);
        }
        if (move_right && (coordinates.getX() + 1 > 0 && coordinates.getX() + 1 < win.getWidth() - width)) {
            coordinates.setX(coordinates.getX() + 1);
        }
        if (shoot & can_shoot){
            bullets.spawnMissile(coordinates.getX() + width/2, coordinates.getY(), -1);
            can_shoot = false;
            fire_timer.start();
        }
        //bullets.move();
        //bullets.kill();
    }

    // Paints player and bullets, determins look of player
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(coordinates.getX(), coordinates.getY(), width, height);
        //bullets.paint(g);
        g.setColor(Color.WHITE);
        g.drawString("Score:", 20, 20);
        g.drawString(String.valueOf(score), 100, 20);
        g.drawString("Health:", 200, 20);
        g.drawString(String.valueOf(health), 280, 20);
    }

    // Player mechanics
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move_left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move_right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot = false;
        }
    }

    // Player mechanics
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) //xa = -1;
        {
            move_left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) //xa = 1;
        {
            move_right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot = true;
        }
    }

    // Returns bullets array
    public ArrayList getAmo() {
        return bullets.getbullets();
    }

    // Removes bullet and increases player score
    public void hit() {
        score = score + 100;
    }
    
    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    int getHealth() {
        return health;
    }

    void playerHit() {
        if(!invincible){
        health -= 1;
        soundEffects.play("hit");
        locationRespawn();
        }
    }

    public void locationRespawn() {
        coordinates.setX(370);
        //coordinates.setY(500);
    }
}
