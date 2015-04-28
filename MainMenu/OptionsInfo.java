/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

/**
 *
 * @author myn
 */
public class OptionsInfo {
    public boolean soundData; // false - no sound, true - sound
    public int shooterData; // ID code for which shooter sprite

    public OptionsInfo() {
        this(true,0); // default to sound on and shooter 0
    }
    
    public OptionsInfo(boolean soundData, int shooterData) {
        this.soundData = soundData;
        this.shooterData = shooterData;
    }
    
}
