
package GameEngine;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class AudioPlayer {
    
    Clip clip;
    String name, sound_file;
    
    // Add sound files to GameEngine pakage then call in contructor eg "test.wav"
    public AudioPlayer(String sound_name, String new_name){
        sound_file = sound_name;
        name = new_name;
        loadTrack();
    }
    
    public void loadTrack(){
        try{
            AudioInputStream sound_one = AudioSystem.getAudioInputStream(new File(getClass().getResource(sound_file).toURI()));
            clip = AudioSystem.getClip();
            clip.open(sound_one);
            while(!clip.isOpen()){
                
            }
            System.out.println(name + " has loaded succesfully.\n");
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
    
    public void playSound(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
     }
    
    public void stopSound(){
        clip.stop();
     }
    
    public void Loop(){
        clip.loop(300);
     }
    
    public void close(){
        clip.close();
     }
    
}

