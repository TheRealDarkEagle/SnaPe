package snake;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	public Music() {
		counter = 0;
		initMusic();
	}
	
	//Level Up Sound
	private Clip lvlUp;
	//Fress-Sound
	private Clip eatDaFood;
	//GameSoundtrack
	private ArrayList<Clip> soundTrack;
	private URL[] urlOfAllTracks;
	
	private int counter; 
	
	//Holt sich alle benötigten URL´s
	private void initMusic() {
		this.lvlUp = getLvlUp();
		this.eatDaFood = getFoodClip();
		this.urlOfAllTracks = getSoundTrackURL();
		this.soundTrack = getBackroundClip();
	}
	//Setzt alle benötigten Clips bereit (für den BackroundSound)
	private ArrayList<Clip> getBackroundClip() {
		javax.sound.sampled.Control[] c = lvlUp.getControls();
		
		
		soundTrack = new ArrayList<Clip>();
		Clip clip = null;
		AudioInputStream gameSound = null;
		for(int i =0; i < urlOfAllTracks.length;i++) {
					try {
						gameSound = AudioSystem.getAudioInputStream(urlOfAllTracks[i]);
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						clip = AudioSystem.getClip();
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						clip.open(gameSound);
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						soundTrack.add(clip = AudioSystem.getClip());
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		return soundTrack;
		}
		
	//Gibt URL Array von den verschiedenen Musikstücken zurück
	private URL[] getSoundTrackURL() {
		URL[] allTracks = 
						   {this.getClass().getClassLoader().getResource("synthWaveLoop90bpm.wav"),
							this.getClass().getClassLoader().getResource("synthWaveLoop3.wav"),
							this.getClass().getClassLoader().getResource("synthWaveLoop.wav"),
							this.getClass().getClassLoader().getResource("Polypumpkins - Abandoned Zone (feat. LC Destroyer).wav"),
							this.getClass().getClassLoader().getResource("GosT-NascencyLvl10.wav")};
		return allTracks;
	}
	/*
	 * spielt den lvlUp sound
	 */
	public void playLvlUp() {
		this.lvlUp.start();
		this.lvlUp = getLvlUp();
	}
	
	/*
	 * Spielt den eatDaFood Sound 
	 */
	public void playFoodEaten() {
		this.eatDaFood.start();
		this.eatDaFood = getFoodClip();
	}
	/*
	 * Wechselt zwischen den Verschiedenen Backround Sounds 
	 */
	public void backgroundSound(int level) {
		AudioInputStream audioIn = null;
		if(level == 0 ||level == 2 || level == 5|| level == 8 || level == 10) {
			if(counter !=0) {
				soundTrack.get(counter-1).stop();
			}
			try {
				audioIn = AudioSystem.getAudioInputStream(urlOfAllTracks[counter]);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				try {
					soundTrack.get(counter).open(audioIn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			soundTrack.get(counter).start();
			soundTrack.get(counter).loop(Clip.LOOP_CONTINUOUSLY);
			counter++;
		}
	}
	
	//Hierüber wird der eatDaFood-Clip geladen
	private Clip getFoodClip() {
		Clip clip= null;
		URL lvlUpTrack = null;
		try {
			lvlUpTrack = this.getClass().getClassLoader().getResource("Quack.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(lvlUpTrack);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return clip;
	}
	
	
	//Hierüber wird der LvlUp Clip geladen
	private Clip getLvlUp() {
		URL foodTrack = null;
		foodTrack = this.getClass().getClassLoader().getResource("levelUp.wav");
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(foodTrack);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		return clip;
	}
	/*
	 * schließt alle Clips
	 */
	public void stopMusic() {
		for(Clip c:soundTrack) {
			c.stop();
		}
	}
}


