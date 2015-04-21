/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author myn
 */
public class Button {
    public String buttonText;
    public IntVector2D boxCoordinates, textOffset, buttonSize;
    public int selection;

    public Button(String buttonText, IntVector2D boxCoordinates, IntVector2D textOffset, IntVector2D buttonSize, int selection) {
        this.buttonText = buttonText;
        this.boxCoordinates = boxCoordinates;
        this.textOffset = textOffset;
        this.buttonSize = buttonSize;
        this.selection = selection;
    }
    
    public void paint(Graphics2D window, int state){
        // box color
        if (state == 1) {window.setColor(Color.blue);} // selected 
        else if (state == 2) {window.setColor(Color.orange);} //selected + hovered
        else {window.setColor(Color.gray);} // unselected
        window.fillRect(boxCoordinates.getX(), 
                        boxCoordinates.getY(), 
                        buttonSize.getX(), 
                        buttonSize.getY());
        //paint text
        window.setColor(Color.black);
        window.drawString(buttonText, 
            boxCoordinates.getX()+textOffset.getX(), // x coordinate of text
            boxCoordinates.getY()+textOffset.getY());// y coordinate of text
    }
}
