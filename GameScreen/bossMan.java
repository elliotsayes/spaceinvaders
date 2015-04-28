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
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    Timer Boss_timer;
    BasicEnemy Boss;
    BasicEnemy Bossleg1;
    boolean reached ;
    int HorizontalSpeed = -3;
    
    Random CrazyMike = new Random();
    int Horizontal = 800, Vertical = 200; 
   IntVector2D velocity1 = new IntVector2D(0,3) ;
   IntVector2D velocity2 = new IntVector2D(3,0);
   IntVector2D velocity3 = new IntVector2D(-3,0);
    ImageIcon image = new ImageIcon("BossMan.png")  ;
    
    int size = 300, bossStartY = -295;
    IntVector2D BOSSstartCoordinates = new IntVector2D(250,-295);
//    IntVector2D LEG1startCoordinates = new IntVector2D(250,-295);
    static String CthuluDarkLordLeg1 = "Tenticle 1 move.gif";
    static String CthuluDarkLord = "BossMan_1.png";

    public bossMan() {
       
       super(); 
       enemyArray = new ArrayList<>();
       this.Boss = new BasicEnemy(BOSSstartCoordinates.getX(),BOSSstartCoordinates.getY(),1,size,CthuluDarkLord);
       this.Bossleg1 = new BasicEnemy(BOSSstartCoordinates.getX()+50,BOSSstartCoordinates.getY()+155,1,80,CthuluDarkLordLeg1);
       enemyArray.add(Boss);
       enemyArray.add(Bossleg1);
//        this.coordinates = new IntVector2D (200,-100);
    }
    @Override
    public void moveArmy(JPanel win, BulletHandler bullets){  
     if(enemyArray.get(0).getX()<=0){
         HorizontalSpeed = 3;
    }
    if (enemyArray.get(0).getX() >= 500){
        HorizontalSpeed = -3;
    }
    if (enemyArray.get(0).getY() < 25){
        for (int k = 0; k < enemyArray.size();k++){
     enemyArray.get(k).coordinates.addVector(velocity1);
//     enemyArray.get(0).coordinates.addVector(velocity1);
        }
     
 
    }
     else{
         this.Boss_timer = new Timer(5000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               for (int i = 0; i < enemyArray.size(); i++){
               enemyArray.get(i).coordinates.addVector(new IntVector2D(HorizontalSpeed,0));
               // enemyArray.get(i).coordinates.addVector(new IntVector2D(HorizontalSpeed,0));
               music.playSound();
                }
                
            }
        }));
     Boss_timer.start();
    }
  
    
   
    }
//    @Override
//       public void paint(Graphics2D win) {
//           for (int i = 0; i < enemyArray.
//           Bossleg1.paint(win);
//           Boss.paint(win);
//       
//    }
       
    
}
