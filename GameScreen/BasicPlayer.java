
package GameScreen;

import GameEngine.AudioHandler;
import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BasicPlayer {

    // co-ordinates of player
    //int x;
    //int y;
    IntVector2D coordinates;

    // player size
    int size = 50;
    int width;
    int height;
    // health coordinates
    IntVector2D healthCoord = new IntVector2D(250,5);
    int fireRate;
    boolean move_left, move_right, shoot;
    int score;
    int health;
    Timer fire_timer, invulnrableTimer;
    boolean can_shoot = true;
    //Entite Audio Handler
    boolean invincible = false;
    static ImageIcon Lives;
    spriteHandler playerSprites;
    Image player;
    public static int skinCode =0;
    
    
    public BasicPlayer() {
        // call secondary constructor with default width and height
        this(60, 10);
        
    }

    public BasicPlayer(int width, int height) {
        // call main constructor with default values
        this(new IntVector2D(370,480), width, height, 5, Color.GREEN, 0, 3);
        
    }

    public BasicPlayer(IntVector2D coordinates, int width, int height, int fireRate, Color color, int score, int health) {
        this.coordinates = coordinates;
        this.height = height;
        this.width = width;
        this.score = score;
        this.health = health;
         this.fire_timer = new Timer(1000/fireRate, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can_shoot = true;
                fire_timer.stop();
            }
        }));
        invulnrableTimer = new Timer(1500, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invincible = false;
                invulnrableTimer.stop();
            }
        }));
        String skin = "playerSpriteSheet.png";
        switch(skinCode) {
            case 0:
                skin = "playerSpriteSheet.png";
                break;
            case 1:
                skin = "playerSpriteSheet2.png";
                break;
            case 2:
                skin = "MarloSpriteSheet.png";
                break;
            case 3:
                skin = "BraedenSpriteSheet.png";
                break;
            case 4:
                skin = "ElliotSpriteSheet.png";
                break;
        }
        Lives = new ImageIcon(getClass().getResource("HealthImage.png"));
        this.playerSprites = new spriteHandler(skin,150,130);
        this.player = playerSprites.getImage(0);
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
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
            this.player = playerSprites.getImage(3);
        }
        if (move_right && (coordinates.getX() + 1 > 0 && coordinates.getX() + 1 < win.getWidth() - width)) {
            coordinates.setX(coordinates.getX() + 1);
            this.player = playerSprites.getImage(2);
        }
        if (shoot & can_shoot){
            bullets.spawnMissile(coordinates.getX() + size/2, coordinates.getY(), -1);
            can_shoot = false;
            fire_timer.start();
            this.player = playerSprites.getImage(1);
        }
        
    }

    // Paints player and bullets, determins look of player
    public void paint(Graphics2D g) {
        //g.fillRect(coordinates.getX(), coordinates.getY(), width, height);
        //bullets.paint(g);
        
        if(invincible){g.drawImage(playerSprites.getImage(4), coordinates.getX(), coordinates.getY(), size, size, null);}else{
            g.drawImage(player, coordinates.getX(), coordinates.getY(), size, size, null);
        }
        g.setColor(Color.WHITE);
        g.drawString("Score:", 20, 20);
        g.drawString(String.valueOf(score), 100, 20);
        g.drawString("Health:", 200, 20);
       // g.drawString(String.valueOf(health), 280, 20);
        for ( int test = 0; test < health; test++ ){
            g.drawImage(Lives.getImage(), healthCoord.getX()+(test*20) ,healthCoord.getY(), 20, 20, null);
        }
    }

    // Player mechanics
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move_left = false;
            this.player = playerSprites.getImage(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move_right = false;
            this.player = playerSprites.getImage(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot = false;
            this.player = playerSprites.getImage(0);
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
        locationRespawn();
        }
    }

    public void locationRespawn() {
        coordinates.setX(370);
        
        //coordinates.setY(500);
    }
    
    public void reset() {
        move_left = false;
        move_right = false;
        shoot = false;
    }

        public int getScore() {
        return score;
    }
}
