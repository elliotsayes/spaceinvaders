/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

/**
 *
 * @author Owner
 */

import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class Loading extends JPanel{
    
    

    public Loading(IntVector2D intVector2D) {
        ImageIcon pic2 = new ImageIcon(getClass().getResource("Loading.gif"));
            JLabel label2 = new JLabel(pic2, JLabel.CENTER);
            this.add(label2);
    }

           
}
