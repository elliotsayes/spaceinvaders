
package GameScreen;


import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class ammo {
    
    ArrayList<bullet> bullets;
    boolean can_shoot = true;
    int fire_rate = 1000 /* ms */;
    Timer fire_timer;
    int direction;/* -1 for up 1 for down */
    // Constructor initialises array of bullets
    public ammo(int dist){
        bullets = new ArrayList<>();
        this.fire_timer = new Timer(fire_rate,(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    can_shoot = true;
                    fire_timer.stop();
                }
                }));
        direction = dist;
    }
    
    // Adds bullet and (x,y) 
    public void spawnMissile(int x,int y){
        if(can_shoot){
            bullets.add(new bullet(x , y, direction));
            can_shoot = false;
            fire_timer.start();
        }
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
    
    public void setRateOfFire(int i){
        fire_rate = i;
    }
}
