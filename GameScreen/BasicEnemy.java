package GameScreen;

// Basic enemy class, can be extended for different enemy types
import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.ImageIcon;

public class BasicEnemy {
    static String defaultSprite = "Basic_Enemy_Sprite.gif";
    
    //  Enemy parameters
    int health;// = 1;
    int size;// = 30;
    Random ran = new Random();
    
    // Enemy location
    IntVector2D coordinates;
    
    // Enemy Sprite
    ImageIcon image;

    // Enemy constructor, initialises profile
    public BasicEnemy(int x, int y) {
        this(x, y, 1, 30, defaultSprite);
    }

    public BasicEnemy(int x, int y, int health, int size, String imageName) {
        this.health = health;
        this.size = size;
        this.coordinates = new IntVector2D(x,y);
        this.image = new ImageIcon(getClass().getResource(imageName));
    }

    // Returns x pos
    public int getX() {
        return coordinates.getX();
    }

    // Returns y pos
    public int getY() {
        return coordinates.getY();
    }

    public int getHealth() {
        return health;
    }

    public int getSize() {
        return size;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setX(int x) {
        this.coordinates.setX(x);
    }

    public void setY(int y) {
        this.coordinates.setY(y);
    }

    public void setImage(String imageName) {
        this.image = new ImageIcon(getClass().getResource(imageName));
    }

    // Moves enemy by (x,y)
    public void move(IntVector2D velocity, BulletHandler bullets) {
       coordinates.addVector(velocity);
       int num = ran.nextInt(50000);
                    if (num<= 50 & num>= 10 ){
                        bullets.spawnMissile(getX(), getY(), 1);
                    }
                    // Temp way to add different bullet types
                    if (num<= 1){
                        bullets.bullets.add(new HealthPowerUp(getX(), getY(), 1));
                    }
                    if (num<= 4 & num >= 3){
                        bullets.bullets.add(new SpeedPowerUp(getX(), getY(), 1));
                    }
    }

    // Paints enemy, controls look of enemy
    public void paint(Graphics2D win) {
        win.drawImage(image.getImage(), coordinates.getX(), coordinates.getY(), size, size, null);
    }

    // Damages enemy and returns 1 if no signs of life
    public boolean hit(int damage) {
        health -= damage;
        size -= 5;
        if (health == 0) {
            return (true);
        }
        return (false);
    }
}
