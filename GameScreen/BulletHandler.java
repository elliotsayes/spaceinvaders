package GameScreen;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class BulletHandler {

    ArrayList<Bullet> bullets;
    boolean can_shoot = true;
    Timer velocity_timer;
    int velocity = 300;

    // Constructor initialises array of bullets
    public BulletHandler( int fire_rate) {
        bullets = new ArrayList<>();
        this.velocity_timer = new Timer(1000/velocity, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                kill();
            }
        }));
       
    }

    // Adds bullet and (x,y) 
    public void spawnMissile(int x, int y, int direction) {
            bullets.add(new Bullet(x, y, direction));
    }

    // Paints bullets
    public void paint(Graphics2D g) {
        int temp = bullets.size();
        if (temp == 0) {
            return;
        }
        int count;
        for (count = 0; count != temp; count++) {
            bullets.get(count).paint(g);
        }

    }

    // Calls move function of bullets
    public void move() {
        int temp = bullets.size();
        int count;
        for (count = 0; count != temp; count++) {
            bullets.get(count).move();
        }
    }

    // Returns array of bullets
    public ArrayList<Bullet> getbullets() {
        return bullets;
    }

    // Removes bullets if they hit top of screen, *******could be moved to collision detection*******
    public void kill() {
        int boundry;
        // These values shouldnt be hard coded, temporary
        for (int temp = 0; temp < bullets.size(); temp++) {
            //Checks bullet direction
            if (bullets.get(temp).velocity.getY() > 0){ boundry = 600;}else{ boundry = 0;}
            if (bullets.get(temp).getY() == boundry) {
                bullets.remove(temp);
            }
            if (bullets.isEmpty()) {
                return;
            }
        }
    }

    // Removes bullet from screen and index i
    void remove(int i) {
        bullets.remove(i);
    }
    
    void stop(){
        velocity_timer.stop();
    }
    
    void start(){
        velocity_timer.start();
    }

}
