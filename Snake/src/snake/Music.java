package snake;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	public Clip getsoundTrack(int level){
		URL gameSound = null;
		if(level == 0) {
			gameSound = this.getClass().getClassLoader().getResource("synthWaveLoop90bpm.wav"); 
		}
		if(level==2) {
			gameSound = this.getClass().getClassLoader().getResource("synthWaveLoop3.wav"); 
		}
		if(level == 5) {
			gameSound = this.getClass().getClassLoader().getResource("synthWaveLoop.wav"); 
		}
		if(level == 8) {
			gameSound = this.getClass().getClassLoader().getResource("Polypumpkins - Abandoned Zone (feat. LC Destroyer).wav");
		}if(level == 10) {
			gameSound = this.getClass().getClassLoader().getResource("GosT-NascencyLvl10.wav"); 
		}
		AudioInputStream gameAudioSound = null;
		try {
			gameAudioSound = AudioSystem.getAudioInputStream(gameSound);
		} catch (UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Clip soundtrack = null;
		try {
			soundtrack = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			soundtrack.open(gameAudioSound);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soundtrack;
	}
}