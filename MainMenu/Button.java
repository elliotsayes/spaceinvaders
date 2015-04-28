/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author myn
 */
public class Button {
    public String buttonText;
    public IntVector2D boxCoordinates, textOffset, buttonSize;
    public int selection;
    public boolean useImages;
    public ImageIcon unselectedImage, selectedImage, hoveredImage;
    
    public int clickAction; 
    // 0 - change gamestate
    // 1 - sound variable
    // 2 - shooter type
    // 3+- ???
    
    public Button(String buttonText, IntVector2D boxCoordinates, IntVector2D textOffset, IntVector2D buttonSize, int selection, ImageIcon unselectedImage, ImageIcon selectedImage, ImageIcon hoveredImage, int clickAction) {
        this(buttonText, boxCoordinates, textOffset, buttonSize, selection);
        this.useImages          = true;
        this.unselectedImage    = unselectedImage;
        this.selectedImage      = selectedImage;
        this.hoveredImage       = hoveredImage;
        this.clickAction        = clickAction;
    }
    
    public Button(String buttonText, IntVector2D boxCoordinates, IntVector2D textOffset, IntVector2D buttonSize, int selection) {
        this.buttonText     = buttonText;
        this.boxCoordinates = boxCoordinates;
        this.textOffset     = textOffset;
        this.buttonSize     = buttonSize;
        this.selection      = selection;
        this.useImages      = false;
    }
    
    public void paint(Graphics2D window, int state){
        if(useImages){
            ImageIcon whichImage;
            switch (state) {
                case 1  : whichImage = selectedImage; break;
                case 2  : whichImage = hoveredImage; break;
                default : whichImage = unselectedImage;
            }
            window.drawImage(whichImage.getImage(), boxCoordinates.getX(), boxCoordinates.getY(), buttonSize.getX(), buttonSize.getY(), null);
        } else {            
            switch (state) {
                case 1  : window.setColor(Color.blue); break;
                case 2  : window.setColor(Color.orange); break;
                default : window.setColor(Color.gray);
            }
            
            //fill in with colour
            window.fillRect(boxCoordinates.getX(), 
                            boxCoordinates.getY(), 
                            buttonSize.getX(), 
                            buttonSize.getY());
        }
        //paint text
        window.setColor(Color.black);
        window.drawString(buttonText, 
            boxCoordinates.getX()+textOffset.getX(), // x coordinate of text
            boxCoordinates.getY()+textOffset.getY());// y coordinate of text
    }
}
