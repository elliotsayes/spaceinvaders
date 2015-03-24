
package GameScreen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class EnemyHandler {
        
        // Enemy config
        int movePos = 1;
        int enemyArraySize = 15;
        int fireDelay = 10;
        ArrayList<BasicEnemy> enemyArray;
        BulletHandler bullets = new BulletHandler(1 , fireDelay);
        Random ran = new Random();
        
        // EnemyHandler Constructor initialises array setting enemy types and layout 
        public EnemyHandler(){
            
             // Initialises enemys
            enemyArray = new ArrayList<>();
            int x = 20;
            int y = 20;
            for(int temp = 0;temp!=enemyArraySize;temp++){
                enemyArray.add(new BasicEnemy(x,y));
                x = x + 40;
            }
        }
        
        // Updates enemy position, controls movement pattern
	void moveArmy(JPanel win) {
                int y = 0;
                int sze = enemyArray.size();
                // Checks there are enemys and then updates there postion
                if (sze == 0){return;}
                // Checks if they have reached end of screen and inverses movement direction
                if ((enemyArray.get(sze - 1).getx() == win.getWidth() - 30) | (enemyArray.get(0).getx() == 0)) {
                    movePos = movePos * -1;
                    y = 10;
                } 
                for(int temp = 0;temp!=enemyArray.size();temp++){
                    enemyArray.get(temp).move(movePos, y);

                    // Spawns enemy bullets
                    int num = ran.nextInt(1000);
                    if (num<= 1){
                        bullets.spawnMissile(enemyArray.get(temp).getx(), enemyArray.get(temp).gety());
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
