/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author ocean
 */
public class PlayerMarlo extends BasicPlayer {
    
    spriteHandler temp;

    public PlayerMarlo() throws IOException, URISyntaxException {
        super();
        this.temp = new spriteHandler("test.png");
        this.height = 80;
        this.width = 80;
        coordinates.setX(100);
        coordinates.setY(450);
    }
    
    @Override 
    public void paint(Graphics2D g) {
        
        g.setColor(color);
        //g.fillRect(coordinates.getX(), coordinates.getY(), width, height);
        //bullets.paint(g);
        int pic = 0;
        if(shoot == true){pic = 1;}
        if(move_left == true){pic = 4;}
        if(move_right == true){pic = 3;}
        
        
        g.drawImage(temp.getImage(pic),coordinates.getX(), coordinates.getY(), width, height,null);
        g.setColor(Color.WHITE);
        g.drawString("Score:", 20, 20);
        g.drawString(String.valueOf(score), 100, 20);
        g.drawString("Health:", 200, 20);
        g.drawString(String.valueOf(health), 280, 20);
    }
}
