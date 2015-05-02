
package GameEngine;

import java.util.ArrayList;
import java.util.Random;

public class AudioHandler {

    ArrayList<AudioPlayer> playList = new ArrayList();
    Random ran = new Random();
    

    public AudioHandler() {
    }

    // Creates playlist of songs passed to constructor
    // Files must be in GameEngine package
    public AudioHandler(ArrayList<String> files) {
        for (int i = 0; i < files.size(); i++) {
            add(files.get(i), files.get(i));
        }
    }

    // Add Audio file to playlist
    public void add(String file, String name) {
        playList.add(new AudioPlayer(file, name));
    }

    // Plays specified song
    public void play(String find_name) {
 
        int i = findTrack(find_name);
        if (i != -1) {
            playList.get(i).playSound();
        }
        
    }

    // Loops specified song set number of times
    public void loop(String find_name, int loopCount) {
       
        int i = findTrack(find_name);
        if (i != -1) {
            playList.get(i).Loop(loopCount);
        }
    }

    // Stops specified song
    public void stop(String find_name) {
        int i = findTrack(find_name);
        if (i != -1) {
            playList.get(i).stopSound();
        }
    }

    // Plays random audio file from playlist
    public void playRandom() {
        int num = ran.nextInt(playList.size());
        playList.get(num).playSound();
        System.out.print(num);
        System.out.print("------DEBUG - 1  \n");
    }

    // Finds track index in playlist
    private int findTrack(String find_name) {
        for (int i = 0; i < playList.size(); i++) {
            if (playList.get(i).name.equals(find_name)) {
                return i;
            }
        }
        return -1;
    }
    
    public void stopAll() {
        
        for (int i = 0; i < playList.size(); i++) {
            playList.get(i).stopSound();
        }
    }
}
