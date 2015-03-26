
package GameScreen;

// Basic enemy class, can be extended for different enemy types


import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class BasicEnemy {
    
    //  Enemy config
    int health = 1;
    int size = 30;
    // Image must be in package folder, include extension in name
    String enemyIcon = "Basic_Enemy_Sprite.gif";
    
    // Enemy profile
    int x;
    int y;
    ImageIcon image;
    
    // Enemy constructor, initialises profile
    public BasicEnemy(int u,int v){
        x = u;
        y = v;
        image = new ImageIcon(getClass().getResource(enemyIcon));
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
        win.drawImage(image.getImage(), x, y,size,size, null);
    }
    
    // Damages enemy and returns 1 if no signs of life
    public boolean hit(int damage) {
        health -= damage;
        if(health == 0){
            return(true);
        }
        return(false);
    }
    }
     


