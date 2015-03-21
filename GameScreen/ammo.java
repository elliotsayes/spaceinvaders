
package GameScreen;


import java.awt.Graphics2D;
import java.util.ArrayList;

public class ammo {
    
    ArrayList<bullet> bullets;
   
    // Constructor initialises array of bullets
    public ammo(){
        bullets = new ArrayList<>();
    }
    
    // Adds bullet and (x,y) 
    public void spawnMissile(int x,int y){
        bullets.add(new bullet(x , y));
    }
    
    // Paints bullets
    public void paint(Graphics2D g) {
        int temp = bullets.size();
        if (temp == 0){return;}
        int count;
        for (count = 0;count!=temp;count++){
            bullets.get(count).paint(g);   
        }
	
    }
    
    // Calls move function of bullets
    public void move(){
        int temp = bullets.size();
        int count;
        for (count = 0;count!=temp;count++){
            bullets.get(count).move();   
        }
    }
    
    // Returns array of bullets
    public ArrayList<bullet> getbullets(){
        return bullets;
    }
    
    // Removes bullets if they hit top of screen, *******could be moved to collision detection*******
    public void kill(){
        for(int temp = 0;temp!=bullets.size();temp++){
               if (bullets.get(temp).gety()==0){
                   bullets.remove(temp);
               }
               if (bullets.isEmpty()){
                   return;
               }
    }
}
    
    // Removes bullet from screen and index i
    void remove(int i) {
        bullets.remove(i);
    }
}
