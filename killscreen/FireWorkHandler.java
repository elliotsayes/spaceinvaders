/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package killscreen;

import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Random;

/**
 *
 * @author Owner
 * 
 * 
 */


public class FireWorkHandler {
    
    //Particle particles = new Particle(new IntVector2D(0,0), new IntVector2D(0,0));
    ArrayList<FireWork> fireworks = new ArrayList<>();
    boolean Shoot = true;
    int Delay = 5;
    Random crazyJoe = new Random();
 
    
    
    FireWorkHandler(){
        fireworks = new ArrayList<>();
        
        for(int i= 100; i <= 600; i = i + 50){
            fireworks.add(new FireWork(new IntVector2D(i,500), new IntVector2D((crazyJoe.nextInt(3)-1),-3), new IntVector2D (0,0)));
            
        }
    }
    
    void Paint(Graphics2D firework){
        for (int i = 0; i < fireworks.size(); i++){
            fireworks.get(i).fireworkPaint(firework);
        }
        
    }
    
    public void move() {
        int temp = fireworks.size();
        int count;
        for (count = 0; count < temp; count++) {
            fireworks.get(count).move();
           
            if(fireworks.get(count).getCoordinates().getY() < 200) {
                
                fireworks.remove(count);
             //
                
                temp--;
            }
        }
    }
     public void remove(int i){
         fireworks.remove(i);
     }

    // public void particlesSize(int i){
      //  fireworks.get(i).particleCoordinates.size();
     //}
          
}

