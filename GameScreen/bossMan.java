/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

import GameEngine.AudioHandler;
import GameEngine.AudioPlayer;
import GameEngine.IntVector2D;
import MainMenu.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import static java.nio.file.Files.size;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Owner
 */
public class bossMan extends EnemyHandler {
    AudioPlayer music = new AudioPlayer("boss.mid", "BossMusic") ;
    Timer Boss_timer, Test_timer, Another_timer;
    BasicEnemy Boss;
    BasicEnemy Bossleg1;
    BasicEnemy Bossleg2;
    BasicEnemy Bossleg3;
    BasicEnemy Bossleg4;
    boolean reached ;
    int HorizontalSpeed = 1;
    
    Random CrazyMike = new Random();
    int Horizontal = 800, Vertical = 200;
    IntVector2D velocity;
    IntVector2D velocity1 = new IntVector2D(0,3) ;
    IntVector2D velocity2 = new IntVector2D(1,0) ;
    IntVector2D velocity3 = new IntVector2D(-1,0) ;
    double k = 0;
    int size =200, bossStartY = -295;
    IntVector2D BOSSstartCoordinates = new IntVector2D(250,-295);
    IntVector2D Tenticles = new IntVector2D(205,-280);
//    IntVector2D LEG1startCoordinates = new IntVector2D(250,-295);
    static String CthuluDarkLordLeg1 = "Tenticle 1 move.gif";
    static String CthuluDarkLord = "BossManFinal.png";
    static String CthuluDarkLordLeg2 = "Tentacle2_move.gif";
    static String CthuluDarkLordLeg3 = "Tenticle3_fast.gif";
    static String CthuluDarkLordLeg4 = "Tenticle4.gif";
    static String CthulhuLogo = "Cthulhu lgo.png";

    public bossMan() throws IOException, URISyntaxException {
       
       super(); 
       enemyArray = new ArrayList<>();
    
       this.Boss = new BasicEnemy(BOSSstartCoordinates.getX(),BOSSstartCoordinates.getY(),100,size,CthuluDarkLord);
       this.Bossleg1 = new BasicEnemy(Tenticles.getX()+50,Tenticles.getY()+155,10,70,CthuluDarkLordLeg1);
       this.Bossleg2 = new BasicEnemy(Tenticles.getX()+85,Tenticles.getY()+160,10,80,CthuluDarkLordLeg2);
       this.Bossleg3 = new BasicEnemy(Tenticles.getX()+ 135,Tenticles.getY()+159,10,70,CthuluDarkLordLeg3);
       this.Bossleg4 = new BasicEnemy(Tenticles.getX()+ 170,Tenticles.getY()+160,10,70,CthuluDarkLordLeg4);
       enemyArray.add(Boss);
       enemyArray.add(Bossleg1);
       enemyArray.add(Bossleg2);
       enemyArray.add(Bossleg3);
       enemyArray.add(Bossleg4);
            this.Test_timer = new Timer(10,(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(k < 5){
                             k = k + 0.01;
                        }
                    }
                    
                }));
             // Test_timer.start();
            
//        this.coordinates = new IntVector2D (200,-100);
       this.Boss_timer = new Timer(5000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               music.playSound();
               Boss_timer.stop();
              
                } 
            }));
       Boss_timer.start();
      
    
//             this.Another_timer = new Timer(5000, (new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              // music.playSound();
//               Another_timer.stop();
//              
//                } 
//            }));
            
      
    }
    @Override
    public void moveArmy(JPanel win, BulletHandler bullets){
    if (enemyArray.size() != 0 ){
        if (enemyArray.get(0).getY() < 25){velocity = velocity1;}else{ // Another_timer.start();
            if(HorizontalSpeed == 1){velocity = velocity2;}else{
                velocity = velocity3;
              
            }
        }
        
          
        for (int k = 0; k < enemyArray.size();k++){
            enemyArray.get(k).coordinates.addVector(velocity);
        }
       
            
        if(enemyArray.get(0).getX()<=0){
            HorizontalSpeed = 1;
        }
        if (enemyArray.get(0).getX() >= 500){
            HorizontalSpeed = -1;
        }
    }
    }
    
  

@Override
void hit(int temp) {           
            System.out.print("Enemy : ");
            System.out.print(temp);
            System.out.print(" Health: ");
            System.out.print(enemyArray.get(temp).health);
            System.out.print("\n");
        if(Boss.coordinates.getY()<25){return;}    
        if((temp == 0) & enemyArray.size() != 1 ){return;}    
        if(enemyArray.get(temp).hit(1)){
           enemyArray.remove(temp); 
        }       
    
}
 public void paint(Graphics2D win) {
        win.setColor(Color.green);
     
        
        win.drawString("HEALTH: ", 20, 40);
        for (int i = 0; i<enemyArray.size();i++){
           
                enemyArray.get(i).paint(win);
            if ((int) (enemyArray.get(i).health*k) < enemyArray.get(i).health*2){
                win.setColor(Color.red); 
                }
                // (double)enemyArray.get(i).health; 
             if(enemyArray.get(i).health < 5){win.setColor(Color.red);}
        win.fillRect(80, 40 + (i*20),(int) (enemyArray.get(i).health*k), 10);
                win.setColor(Color.green);
        Test_timer.start();
           
            
        
        }
    }
    
}
