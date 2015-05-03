package GameScreen;


import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;


public class Bullet {

    Color color;// = Color.red;
    int size;// = 3;
    IntVector2D coordinates, velocity, acceleration;
    int damage = 1;
    //AudioPlayer fire_sound = new AudioPlayer("shoot.wav","shoot");
    
    public Bullet(IntVector2D coordinates, IntVector2D velocity) {
        this(coordinates,velocity,new IntVector2D());
    }
    
    // Creates bullet at (x,y) with direction
    public Bullet(IntVector2D coordinates, IntVector2D velocity, IntVector2D acceleration) {
        //ititialise with default colour and size
        this(coordinates, velocity, acceleration, Color.red, 3);
    }

    // general constructor;
    public Bullet(IntVector2D coordinates, IntVector2D velocity, IntVector2D acceleration, Color color, int size) {
        this.coordinates = coordinates;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.color = color;
        this.size = size;
        //fire_sound.playSound();
    }

    // moves bullet, controls movement pattern 
    void move() {
        coordinates.addVector(velocity);
        velocity.addVector(acceleration);
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
        if(!g.shooter.invincible){
        g.shooter.health -= damage;
        g.shooter.invincible = true;
        g.shooter.invulnrableTimer.start();
        }
        
       
    }
    

    
    
}
