/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author ocean
 */
public class AIplayer extends BasicPlayer{
    
    int safe = 400 - size/2;
    int danger = 1;
    int direction = 1;
    Random ran = new Random(); 
    @Override
    public void move(JPanel win, BulletHandler bullets) {
      //for (int j = getY() - size; j > 0; j = j-size){
        int index = -1;
        int Ypos = 0;
        for (int i = 0; i < bullets.getbullets().size();i++){
            Bullet temp = bullets.getbullets().get(i);
            if(temp.velocity.getY() > 0){
            if(temp.getX() <= getX() + size && temp.getX() >= getX()){
                if(temp.getY() > Ypos){
                    index = i;
                    Ypos = temp.getY();
                }
            }
        }
        }
        if (index != -1 && Ypos > 350 && Ypos < getY() && danger == 0 ){ 
            
            Bullet temp = bullets.getbullets().get(index);
            
                if(temp.getX() > getX() + size/2 ){
                    //coordinates.setX(coordinates.getX() - 1);
                    //this.player = playerSprites.getImage(3);
                    safe = (temp.getX() - (size+ 20)) ;
                    if (safe < 0 ){
                        safe = temp.getX() + temp.size;
                    }
                }else{
                    //coordinates.setX(coordinates.getX() + 1);
                    //this.player = playerSprites.getImage(2);
                     safe = temp.getX() + temp.size;
                     if (safe > 800 - size){
                        safe = (temp.getX() - (size + 20));
                    }
                    
                }
                danger = 1;
                
        }if (getX() != safe && danger == 1){
                if(safe < getX()  ){
                    coordinates.setX(coordinates.getX() - 1);
                    this.player = playerSprites.getImage(3);
                     System.out.print("SAFE: ");
             System.out.print(safe);
             System.out.print("  X: ");
             System.out.print(getX());
             System.out.print("  Y: ");
             System.out.print(getY());
              System.out.print(" \n");
                }else if(safe > getX() ){
                    coordinates.setX(coordinates.getX() + 1);
                    this.player = playerSprites.getImage(2);
                     System.out.print("SAFE: ");
             System.out.print(safe);
             System.out.print("  X: ");
             System.out.print(getX());
             System.out.print("  Y: ");
             System.out.print(getY());
              System.out.print(" \n");
                }
        }
        if (getX() == safe){
            danger = 0;
             System.out.print(" you are now ****SAFE: ");
             System.out.print(safe);
             System.out.print("  X: ");
             System.out.print(getX());
             System.out.print("  Y: ");
             System.out.print(getY());
              System.out.print(" \n");
            if(ran.nextInt(2000) < 50){
                if (can_shoot){
                bullets.spawnMissile(coordinates.getX() + size/2, coordinates.getY(), -1);
                can_shoot = false;
                fire_timer.start();
                this.player = playerSprites.getImage(1);
                }
            }
            this.player = playerSprites.getImage(0);
            //if(ran.nextInt(10000) < 20){
            //   safe = ran.nextInt(600) + 100; 
            //   danger = 1;
            //}
            
        }
            
        
     // }
            
        //if (can_shoot){
        //    bullets.spawnMissile(coordinates.getX() + size/2, coordinates.getY(), -1);
        //    can_shoot = false;
        //    fire_timer.start();
        //    this.player = playerSprites.getImage(1);
        //}
        
    }
    
    public boolean hitBox(int x, int y, int size, int u, int v, int size2) {    
            if (u < x + size & u > x) {
                if ((v < (y + size)) & (v > y)) {
                    System.out.print("DEBUG - hit 1 \n");
                    
                    return true;
                }else if (((v + size2) < (y + size)) & (v + size2 > y)) {
                    System.out.print("DEBUG - hit 2 \n");
                    
                    return true;
                }
            } else if (u + size2 < x + size & u + size2 > x){
                if ((v < (y + size)) & (v > y)) {
                    System.out.print("DEBUG - hit 3 \n");
                    
                    return true;
                }else if (((v + size2) < (y + size2)) & (v + size2 > y)) {
                    System.out.print("DEBUG - hit 4 \n");
                    
                    return true;
                }
            }
            return false;
        }
}
