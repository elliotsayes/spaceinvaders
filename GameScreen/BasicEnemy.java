package GameScreen;

// Basic enemy class, can be extended for different enemy types
import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.ImageIcon;

public class BasicEnemy {
    static String defaultSprite = "Basic_Enemy_Sprite.gif";
    
    //  Enemy parameters
    int health;
    int size;
    int type = 0;
    int fireChance, healthChance, speedChance;
    Random ran = new Random();
    
    // Enemy location
    IntVector2D coordinates;
    
    // Enemy Sprite (MAKING THIS STATIC USE LESS MEMORY?)
    ImageIcon image;

    // Enemy constructor, initialises profile
    public BasicEnemy(int x, int y) {
        this(x, y, 1, 30, defaultSprite,10,1,3);
    }

    public BasicEnemy(int x, int y, int health, int size, String imageName,int fireC,int healthC,int speedC) {
        this.health = health;
        this.size = size;
        this.coordinates = new IntVector2D(x,y);
        this.image = new ImageIcon(getClass().getResource(imageName));
        this.fireChance = fireC;
        this.healthChance = healthC;
        this.speedChance = speedC;
    }

    // Returns x pos
    public int getX() {
        return coordinates.getX();
    }

    // Returns y pos
    public int getY() {
        return coordinates.getY();
    }

    // Returns Health
    public int getHealth() {
        return health;
    }

    // Returns Size
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
      
       if (ran.nextInt(10000) <= fireChance){
            bullets.spawnMissile(getX(), getY(), 1);
        }
       if (ran.nextInt(70000) <= healthChance){
            bullets.bullets.add(new HealthPowerUp(getX(), getY(), 1));
        }
       if (ran.nextInt(90000) <= speedChance){
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
