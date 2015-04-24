/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package killscreen;

import GameEngine.AudioPlayer;
import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Random;

/**
 *
 * Handles each firework individually 
 * 
 * 
 */


public class FireWorkHandler {
    ArrayList<FireWork> blowingUp = new ArrayList<>();
    ArrayList<FireWork> fireworks = new ArrayList<>();
    ArrayList<Color> colours = new ArrayList<>();
    ArrayList<IntVector2D> velocities = new ArrayList<IntVector2D>(){{ 
        add(new IntVector2D(-2,0));
        add(new IntVector2D(0,-2));
        add(new IntVector2D(2,0));
        add(new IntVector2D(0,2));
        add(new IntVector2D(-2,-2));
        add(new IntVector2D(2,2));
        add(new IntVector2D(2,-2));
        add(new IntVector2D(-2,2));
        add(new IntVector2D(1,2));
        add(new IntVector2D(1,-2));
        add(new IntVector2D(-1,2));
        add(new IntVector2D(-1,-2));
        add(new IntVector2D(2,1));
        add(new IntVector2D(2,-1));
        add(new IntVector2D(-2,1));
        add(new IntVector2D(-2,-1));
       
         add(new IntVector2D(-3,-3));
          add(new IntVector2D(3,3));
           add(new IntVector2D(3,-3));
            add(new IntVector2D(-3,3));
             add(new IntVector2D(0,-3));
              add(new IntVector2D(-3,-3));
               add(new IntVector2D(3,0));
                add(new IntVector2D(0,3));
                 add(new IntVector2D(-3,0));
        
    }};
    
    int Bcount = 0;
    boolean Shoot = true;
    boolean test = false;
    int Delay = 5;
    Timer fireWorkTimer;
    Timer BlowyUpyTimer;
    int Update = 5 ;
    int BlowyUpdate = 10;
    Random crazyJoe = new Random();
   // AudioPlayer sound = new AudioPlayer("shoot.wav", "firework");
 
    
    
    FireWorkHandler(){
       
     
        
        for(int i= 50; i <= 770; i = i + 75 ){
            fireworks.add(new FireWork(new IntVector2D(i,500), new IntVector2D(0,crazyJoe.nextInt(3)-4)));
            }
        
     
    }
    
    void Paint(Graphics2D firework){
        for (int i = 0; i < fireworks.size(); i++){
            fireworks.get(i).fireworkPaint(firework);
            
        }
        for (int k = 0; k < blowingUp.size(); k++){
            blowingUp.get(k).fireworkPaint(firework);
        }
    }
    
    public void move() {
        int temp = fireworks.size();
        int count;
        for (count = 0; count < temp; count++) {
            fireworks.get(count).move();
        }  
            this.fireWorkTimer = new Timer(10000/Update, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                
           
                for(int i = 0; i < fireworks.size(); i++){
                        makeBlowUp(new IntVector2D (fireworks.get(i).getCoordinates().getX(),
                                                   fireworks.get(i).getCoordinates().getY()));
                        BlowyUpyTimer = new Timer(10000/BlowyUpdate, (new ActionListener() {
                      @Override
                       public void actionPerformed(ActionEvent e) {  
                           for(int g = 0; g < blowingUp.size(); g++){
                           blowingUp.remove(g);
                           }
                           
                       }}));
                        BlowyUpyTimer.start();
                        fireWorkTimer.restart();
                        
                    if (i == fireworks.size()  - 1){
                        test = true;
                    }
                    fireworks.remove(i);
                    
                }
                 if (test == true){
                for(int h = 0; h < blowingUp.size(); h++){
                   
                    blowingUp.get(h).move();
                    
                }
                
            }}
            }));
            
            fireWorkTimer.start();
            
            }
        

     public void remove(int i){
         fireworks.remove(i);
     }
     public void makeBlowUp(IntVector2D coordinates){
         Color fireworkColor = new Color(crazyJoe.nextInt(255),crazyJoe.nextInt(255),crazyJoe.nextInt(255));
         for (int t =0 ; t < velocities.size(); t ++){
            
            blowingUp.add( new FireWork( new IntVector2D(coordinates.getX(),coordinates.getY()),velocities.get(t), 20, new IntVector2D(0,0),
                   Color.RED,8,8,fireworkColor ));     
         }
     }
}

