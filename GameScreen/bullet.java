
package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.Timer;


public class bullet {
    
    Color color = Color.red;
    int size = 3;
    int x;
    int y;
   
    
    // Creates bullet at (u,v)
    public bullet(int u,int v){
        x = u;
        y = v;
    }
    
    // moves bullet, controls movement pattern 
    void move(){
        if (y != 0){
            y = y - 1;
        }
    }
    
    // Returns x position
    int getx(){
        return x;
    }
    
    // Returns y position
    int gety(){
        return y;
    }
    
    // Controls what bulllets look like
    public void paint(Graphics2D win){
    
        win.setColor(color);
        win.fillOval(x,y,size,size);
	// Main menu message
	}
}
