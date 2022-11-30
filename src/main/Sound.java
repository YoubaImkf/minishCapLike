package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundUrl = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/sounds/01-Title-Screen.wav");
        soundUrl[1] = getClass().getResource("/sounds/11-Hyrule-Field.wav");
        soundUrl[2] = getClass().getResource("/sounds/02-File-Select.wav");
        soundUrl[3] = getClass().getResource("/sounds/effects/MC_Menu_Select.wav");

    }

    public void setFile(int i){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
