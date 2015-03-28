package GameScreen;

import GameScreen.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.Timer;

public class Bullet {

    Color color;// = Color.red;
    int size;// = 3;
    IntVector2D coordinates, velocity;
    
    // unused 
    int x;
    int y;

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
}
