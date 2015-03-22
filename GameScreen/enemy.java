
package GameScreen;

// Basic enemy class, can be extended to add features

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class enemy {
    
    int health = 1;
    Color color = Color.green;
    int size = 40;
    int x;
    int y;
    ImageIcon image;
    
    // Enemy constructor, initialises (x,y) position
    public enemy(int u,int v){
        x = u;
        y = v;
        image = new ImageIcon(getClass().getResource("enemy_sprite.gif"));
    }
    
    // Moves enemy by (x,y)
    public void move(int u, int v){
            y = y + v;
            x = x + u; 
    }
    
    // Returns x pos
    public int getx(){
        return x;
    }
    
    // Returns y pos
    public int gety(){
        return y;
    }
    
    // Paints enemy, controls look of enemy
    public void paint(Graphics2D win){
        
        win.setColor(color);
        win.drawImage(image.getImage(), x, y,size,size, null);
        //win.fillOval(x,y,size,size);
	// Main menu message
	}
    
    // Damages enemy and returns 1 if no signs of life
    public boolean hit() {
        health -= 1;
        size -= 5;
        if(health == 0){
            return(true);
        }
        return(false);
    }
    }
     


