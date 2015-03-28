
package GameScreen;


import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class BulletHandler {
    
    ArrayList<Bullet> bullets;
    boolean can_shoot = true;
    Timer fire_timer;
    int direction;/* -1 for up 1 for down */
    // Constructor initialises array of bullets
    public BulletHandler(int dist, int fire_rate){
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
            bullets.add(new Bullet(x , y, direction));
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
    public ArrayList<Bullet> getbullets(){
        return bullets;
    }
    
    // Removes bullets if they hit top of screen, *******could be moved to collision detection*******
    public void kill(){
        for(int temp = 0;temp!=bullets.size();temp++){
               if (bullets.get(temp).getY()==0){
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
