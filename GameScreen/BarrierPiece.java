/*
This is a class that handles the painting of the individual 
components making up one barrier. 
 */
package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Owner
 */
public class BarrierPiece {
    
    int size = 4;//size of each barrier component 
    int health = 1;
    int x,y; 
    
    
    
//Calling a constructor with default values
    BarrierPiece(int u, int v) {
     x = u;
     y = v;
       
    }
//Calling a constructor
  /* BarrierPiece(int size, int health, int x, int y) {
       this.size = size;
       this.health = health;
        this.x = x;
        this.y = y;
   }*/
   
   
   public void piecePaint (Graphics2D barrier){
        barrier.setColor(Color.GREEN);
        barrier.fill3DRect(x, y, size, size, true);
    }
    
  
        
   public boolean getHit (int damage){
             health -= damage;
        if (health == 0) {
            return (true);
        }
        return (false);
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getY(){
        return y;
    }

    public int getX() {
        return x;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }
     
   

}

    
    
    
    
    
    

