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
    BasicEnemy Bossleg2;
    boolean reached ;
    int HorizontalSpeed = 1;
    
    Random CrazyMike = new Random();
    int Horizontal = 800, Vertical = 200;
    IntVector2D velocity;
    IntVector2D velocity1 = new IntVector2D(0,3) ;
    IntVector2D velocity2 = new IntVector2D(1,0) ;
    IntVector2D velocity3 = new IntVector2D(-1,0) ;
    
    int size = 300, bossStartY = -295;
    IntVector2D BOSSstartCoordinates = new IntVector2D(250,-295);
//    IntVector2D LEG1startCoordinates = new IntVector2D(250,-295);
    static String CthuluDarkLordLeg1 = "Tenticle 1 move.gif";
    static String CthuluDarkLord = "BossMan2.png";
    static String CthuluDarkLordLeg2 = "Tentacle2_move.gif";

    public bossMan() {
       
       super(); 
       enemyArray = new ArrayList<>();
       this.Boss = new BasicEnemy(BOSSstartCoordinates.getX(),BOSSstartCoordinates.getY(),5,size,CthuluDarkLord);
       this.Bossleg1 = new BasicEnemy(BOSSstartCoordinates.getX()+50,BOSSstartCoordinates.getY()+155,5,80,CthuluDarkLordLeg1);
       this.Bossleg2 = new BasicEnemy(BOSSstartCoordinates.getX()+85,BOSSstartCoordinates.getY()+160,5,90,CthuluDarkLordLeg2);
       enemyArray.add(Boss);
       enemyArray.add(Bossleg1);
       enemyArray.add(Bossleg2);
//        this.coordinates = new IntVector2D (200,-100);
       this.Boss_timer = new Timer(5000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               music.playSound();
               Boss_timer.stop();
                } 
            }));
       Boss_timer.start();
    }
    @Override
    public void moveArmy(JPanel win, BulletHandler bullets){
    if (enemyArray.size() != 0 ){
        if (enemyArray.get(0).getY() < 25){velocity = velocity1;}else{
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
        if((temp == 0) & enemyArray.size() != 1 ){return;}    
        if(enemyArray.get(temp).hit(1)){
           enemyArray.remove(temp); 
        }       
    
}
}
