
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.Timer;


public class MarloEnemy extends BasicEnemy {

    spriteHandler temp;
    Image pic;
    Timer animator;
    int dir = 1;
    
    public MarloEnemy(int x, int y) throws IOException, URISyntaxException {
        super(x, y);
        this.temp = new spriteHandler("test.png");
        pic = temp.getImage(0);
        this.health = 50;
        this.size = 90;
        animator = new Timer(800, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pic = temp.getImage(0); 
                animator.stop();
            }
        }));
        
    }
    
    @Override
    public void paint(Graphics2D win) {
        win.setColor(Color.red);
        win.drawImage(pic, coordinates.getX(), coordinates.getY(), size, size, null);
        win.drawString("HEALTH: ", 20, 40);
        win.fillRect(80, 40, health*10, 10);
    }
    
    @Override
     public void move(IntVector2D velocity, BulletHandler bullets) {
       
       int num = ran.nextInt(50000);
       
       coordinates.addVector(new IntVector2D(velocity.getX()*dir,0));
       
                    if (num<= 700 & num>= 10 ){
                        bullets.spawnMissile(getX(), getY(), 1);
                        pic = temp.getImage(1);
                        animator.start();
                    }
                    // Temp way to add different bullet types
                    if (num<= 1){
                        bullets.bullets.add(new HealthPowerUp(getX(), getY(), 1));
                    }
                    if (num<= 4 & num >= 3){
                        bullets.bullets.add(new SpeedPowerUp(getX(), getY(), 1));
                    }
                    if (num<= 5000 & num >= 50){
                        coordinates.addVector(new IntVector2D(velocity.getX()*dir,0));
                    }
                    if (num<= 1200 & num >= 1000){
                        dir*=-1;
                    }
    }
    
     @Override
      public boolean hit(int damage) {
        health -= damage;
        pic = temp.getImage(2); 
        animator.start();
        if (health == 0) {
            return (true);
        }
        return (false);
    }
}
