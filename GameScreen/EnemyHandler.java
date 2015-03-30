
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class EnemyHandler {
        
        // Enemy config
        int movePos = 1;
        int enemyArraySize = 15;
        int fireDelay = 5;
        ArrayList<BasicEnemy> enemyArray;
        BulletHandler bullets = new BulletHandler(1 , fireDelay);
        Random ran = new Random();
        
        // EnemyHandler Constructor initialises array setting enemy types and layout 
        public EnemyHandler(){
            
             // Initialises enemys
            enemyArray = new ArrayList<>();
            int x = 20;
            int y = 20;
            for(int temp1 = 0;temp1!=3;temp1++){
                for(int temp = 0;temp!=enemyArraySize;temp++){
                enemyArray.add(new BasicEnemy(x,y));
                x = x + 40;
                }
            y += 60;
            x = 20;
            }
        }
        
        // Updates enemy position, controls movement pattern
	void moveArmy(JPanel win) {
                int y = 0;
                int sze = enemyArray.size() - 1;
                // Checks there are enemys and then updates there postion
                //if (sze == 0){return;}
                // Checks if they have reached end of screen and inverses movement direction
                for(int temp1 = 0;temp1 <= sze;temp1++){
                if ((enemyArray.get(temp1).getX() == win.getWidth() - 30) | (enemyArray.get(temp1).getX() == 0)) {
                    movePos = movePos * -1;
                    y = 10;
                    temp1 = sze + 1;
                } 
                }
                for(int temp = 0;temp!=enemyArray.size();temp++){
                    IntVector2D tempVector = new IntVector2D(movePos, y);
                    enemyArray.get(temp).move(tempVector);

                    // Spawns enemy bullets
                    int num = ran.nextInt(5000);
                    if (num<= 1){
                        bullets.spawnMissile(enemyArray.get(temp).getX(), enemyArray.get(temp).getY());
                    }
                }
            bullets.move();    
	}
        
        // Calls paint method for each enemy
        public void paint(Graphics2D win){
             for(int temp = 0;temp!=enemyArray.size();temp++){
                    enemyArray.get(temp).paint(win);
            }
            bullets.paint(win);
        }
        
        // Returns enemy array
        public ArrayList<BasicEnemy> getArmy(){
            return enemyArray;
        }
        
         public ArrayList<Bullet> getAmmo(){
            return bullets.getbullets();
        }
        
        // Hits the enemy then checks for life..
        void hit(int temp) {
        if(enemyArray.get(temp).hit(1)){
           enemyArray.remove(temp); 
        }
        
    }
          
}
