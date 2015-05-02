
package GameEngine;

import GameScreen.spriteHandler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

    Clip clip;
    String name, sound_file;
    static boolean canPlay = true;
    
    URL url;
    File file;
    
    

    // Add sound files to GameEngine pakage then call in contructor eg "test.wav"
    // Constructor loads sound file
    public AudioPlayer(String sound_name, String new_name) {
        sound_file = sound_name;
        name = new_name;
        loadTrack();
    }

    // Trys to load the specified Audio file and returns message on success / faliure
    private void loadTrack() {
        try {
            InputStream audio = getClass().getResourceAsStream(sound_file);
            InputStream bufferdAudio = new BufferedInputStream(audio);
            AudioInputStream sound_one = AudioSystem.getAudioInputStream(bufferdAudio);
            //AudioInputStream sound_one = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(sound_file));
            clip = AudioSystem.getClip();
            clip.open(sound_one);
            while (!clip.isOpen()) {
                // Does nothing while clip is being loaded into memory
            }
            System.out.println(name + " has loaded succesfully.\n");
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
             Logger.getLogger(spriteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Plays Audio file from start
    public void playSound() {
      try{
        if(canPlay){
        clip.setFramePosition(0);  
        clip.start();
        }
    }catch(Exception ex){
        
    }
            }

    // Stops playing the Audio file
    public void stopSound() {
        try{
        clip.stop();
        }catch(Exception ex){
            
        }
    }

    // Loops audio file "i" times
    public void Loop(int i) {
        if(canPlay){
        clip.loop(i);
    }
    }

    //Closes Audio file
    public void close() {
        clip.close();
    }

}

