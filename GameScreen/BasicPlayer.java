/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class BasicPlayer {
    
        int setRateOfFire = 100;
        BulletHandler bullets = new BulletHandler(-1 , setRateOfFire);
	int x = 400;
	int xa = 0;
        int y = 460;
        Color color = Color.DARK_GRAY;
        int score;
        ImageIcon image;
        int health = 30;
        
        
        // Player constructor
	public BasicPlayer(){
            
	}

        // Moves Player aswell as bullets
	public void move(JPanel win) {
		if (x + xa > 0 && x + xa < win.getWidth()-60)
			x = x + xa;
                bullets.move();
                bullets.kill();
	}

        // Paints player and bullets, determins look of player
	public void paint(Graphics2D g) {
                g.setColor(color);
		g.fillRect(x, y, 60, 10);
                bullets.paint(g);
                g.setColor(Color.WHITE);
                g.drawString("Score:", 20, 20);
                g.drawString(String.valueOf(score), 100, 20);
                g.drawString("Health:", 200, 20);
                g.drawString(String.valueOf(health), 280, 20);
	}

        // Player mechanics
	public void keyReleased(KeyEvent e) {
		xa = 0;
	}
        
        // Player mechanics
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 1;
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
			bullets.spawnMissile(x + 30, y);
	}
        
        // Returns bullets array
        public ArrayList getAmo(){
            return bullets.getbullets();
        }
        
        // Removes bullet and increases player score
        public void hit(int i){
            bullets.remove(i);
            score = score + 100;
            
        }
        
        public int gety(){
            return y;
        }
        
        public int getx(){
            return x;
        }

    int health() {
        return health;
    }

    void playerHit() {
        health -= 1;
        reset();
    }
    
    public void reset(){
        x = 400;
	xa = 0;
        y = 460;
    }
        
}
