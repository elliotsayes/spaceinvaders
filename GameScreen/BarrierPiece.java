/*
This is a class that handles the painting of the individual 
components making up one barrier. 
 */
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Owner
 */
public class BarrierPiece {
    
    int size = 2;//size of each barrier component 
    int health = 1;
    IntVector2D coordinates;
    
    
//Calling a constructor with default values
    BarrierPiece(IntVector2D coordinates) {
        this.coordinates = coordinates;
       
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
        barrier.fill3DRect(coordinates.getX(), coordinates.getY(), size, size, true);
    }
    
  
        
   public boolean getHit (int damage){
             health -= damage;
        if (health == 0) {
            return (true);
        }
        return (false);
    }

    public int getHealth() {
        return health;
    }

    public IntVector2D getCoordinates() {
        return coordinates;
    }
    
   
    public void setHealth(int health) {
        this.health = health;
    }
     
   

}

    
    
    
    
    
    

