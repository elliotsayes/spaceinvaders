package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.Timer;

public class Bullet {

    Color color;// = Color.red;
    int size;// = 3;
    int x;
    int y;
    int direction;

    // Creates bullet at (x,y) with direction
    public Bullet(int x, int y, int direction) {
        //ititialise with default colour and size
        this(x, y, direction, Color.red, 3);
    }

    //general constructor;
    public Bullet(int x, int y, int direction, Color color, int size) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.color = color;
        this.size = size;
    }

    // moves bullet, controls movement pattern 
    void move() {
        if (y != 0) {
            y = y + direction;
        }
    }

    // Returns x position
    int getX() {
        return x;
    }

    // Returns y position
    int getY() {
        return y;
    }

    // Controls what bulllets look like
    public void paint(Graphics2D win) {

        win.setColor(color);
        win.fillOval(x, y, size, size);
        // Main menu message
    }
}
