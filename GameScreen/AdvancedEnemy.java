/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author ocean
 */
public class AdvancedEnemy extends BasicEnemy{

    Timer animator;
    int dir = 1;
    
    public AdvancedEnemy(int x, int y){
        super(x, y);
        this.health = 10;
        this.size = 60;
        type = 2;
        animator = new Timer(800, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        }));
        this.image = new ImageIcon(getClass().getResource("SpawnOfCthulhu.gif"));
        
    }
    
    @Override
    public void paint(Graphics2D win) {
        win.setColor(Color.red);
        win.drawImage(image.getImage(), coordinates.getX(), coordinates.getY(), size, size, null);
        win.fillRect(this.getX()+10, this.getY(), health*2, 3);
    }
    
    @Override
     public void move(IntVector2D velocity, BulletHandler bullets) {
       
       int num = ran.nextInt(50000);
       
       //if(coordinates.getX()+velocity.getX()*dir < 0){
       // dir = 1;
       //}else if(coordinates.getX()+velocity.getX()*dir > 710){
       //dir = -1; 
       //}
       if(velocity.getX() != 0){ dir = velocity.getX();}
        coordinates.addVector(new IntVector2D(dir,velocity.getY()));   
       
       
                    if (num<= 700 & num>= 10 ){
                        bullets.spawnMissile(getX(), getY(), 1);
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
                    if (num<= 1010 & num >= 1000){
                        dir*=-1;
                    }
    }
    
     @Override
      public boolean hit(int damage) {
        health -= damage; 
        animator.start();
        if (health == 0) {
            return (true);
        }
        return (false);
    }
}