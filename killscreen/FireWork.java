/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package killscreen;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import GameScreen.BulletHandler;
import java.util.ArrayList;
import java.awt.Transparency;

/**
 *
 * @author Owner
 */
public class FireWork {
    int temp = 0; 

    Color colour = Color.magenta; 
    ArrayList<IntVector2D> particleCoordinates = new ArrayList<>(); 
    IntVector2D coordinates, velocity, acceleration, size, sample;

    
    FireWork(IntVector2D coordinates, IntVector2D velocity){
        this(coordinates, velocity, new IntVector2D(0,0));
    }
    FireWork(IntVector2D coordinates, IntVector2D velocity, IntVector2D acceleration){
        this(coordinates, velocity, acceleration, Color.RED, 8 , 8);
    }
    
    FireWork(IntVector2D coordinates, IntVector2D velocity, IntVector2D acceleration, Color colour, int sizeX, int sizeY){
        
       this.coordinates = coordinates;
       this.velocity = velocity;
       this.acceleration = acceleration;
       this.size = new IntVector2D(sizeX, sizeY);
       this.colour = colour;
    }
    
    public void fireworkPaint(Graphics2D firework){
        firework.setColor(colour);
        firework.fill3DRect(coordinates.getX(), coordinates.getY(), size.getX() ,size.getY(), true);
           
        
        for(int i = 0; i < particleCoordinates.size(); i++){
            if( i == 4){
                //firework.setColor(Color.TRANSLUCENT);
                
            firework.setColor(Color.WHITE);
            }
            if(i == 3){
                firework.setColor(Color.YELLOW);
            }
            if(i == 2){
                firework.setColor(Color.ORANGE);
            }
            if(i == 1){
                firework.setColor(Color.RED);
            }
            
            firework.fillOval(particleCoordinates.get(i).getX()+ (size.getX()/3)
                    , particleCoordinates.get(i).getY() + (size.getY()/3), 5 ,5);
        }
        
       
    }

    public Color getColour() {
        return colour;
    }

    public IntVector2D getSize() {
        return size;
    }

   // public ArrayList<IntVector2D> getParticleCoordinates() {
     //   return particleCoordinates;
   // }

    public IntVector2D getCoordinates() {
        return coordinates;
    }

    public IntVector2D getVelocity() {
        return velocity;
    }

    public IntVector2D getAcceleration() {
        return acceleration;
    }
    
    void move(){
        coordinates.addVector(velocity);
        velocity.addVector(acceleration);
        temp++;
        if (temp == 2){
           makeParticle( new IntVector2D (coordinates.getX(),coordinates.getY()));
           removeOldestParticle();
            temp = 0;
       }
       if(coordinates.getY() >= 400){
           
       }
    }
    
    void makeParticle(IntVector2D coordinates){
        particleCoordinates.add(0, coordinates);
    }
    
    void removeOldestParticle(){
        if (particleCoordinates.size() > 5){
            particleCoordinates.remove(5);
        }
    }
}