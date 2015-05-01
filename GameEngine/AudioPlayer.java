
package GameEngine;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

    Clip clip;
    String name, sound_file;
    static boolean canPlay = true;

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
            AudioInputStream sound_one = AudioSystem.getAudioInputStream(new File(getClass().getResource(sound_file).toURI()));
            clip = AudioSystem.getClip();
            clip.open(sound_one);
            while (!clip.isOpen()) {
                // Does nothing while clip is being loaded into memory
            }
            System.out.println(name + " has loaded succesfully.\n");
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }

    // Plays Audio file from start
    public void playSound() {
        if(canPlay){
        clip.setFramePosition(0);  
        clip.start();
    }}

    // Stops playing the Audio file
    public void stopSound() {
        clip.stop();
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

