
package GameEngine;

import java.util.ArrayList;
import java.util.Random;


public class AudioHandler {
    ArrayList<AudioPlayer> sounds = new ArrayList();
    Random ran = new Random();
    
    public AudioHandler(){ 
    }
    
    public AudioHandler(ArrayList<String> files){
        for (int i = 0;i < files.size();i++){
            add(files.get(i),files.get(i));
        } 
    }
    
    public void add(String file, String name){
        sounds.add(new AudioPlayer(file,name));
    }
    
    public void play(String find_name){
        for (int i = 0;i < sounds.size();i++){
            if (sounds.get(i).name == find_name){
                sounds.get(i).playSound();
            }
        }
    }
    
    public void stop(String find_name){
        for (int i = 0;i < sounds.size();i++){
            if (sounds.get(i).name == find_name){
                sounds.get(i).stopSound();
            }
        }
    }
    
    public void playRandom(){
        int num = ran.nextInt(sounds.size());
        sounds.get(num).playSound();
        System.out.print(num);
        System.out.print("------DEBUG - 1  \n");
    }
}
