package snake;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	
	private void initMusic() {
		this.lvlUp = getLvlUp();
		this.eatDaFood = getFoodClip();
		this.urlOfAllTracks = getSoundTrackURL();
		this.soundTrack = getBackroundClip();
	}
	
	private ArrayList<Clip> getBackroundClip() {
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
		
	
	private URL[] getSoundTrackURL() {
		URL[] allTracks = 
						   {this.getClass().getClassLoader().getResource("synthWaveLoop90bpm.wav"),
							this.getClass().getClassLoader().getResource("synthWaveLoop3.wav"),
							this.getClass().getClassLoader().getResource("synthWaveLoop.wav"),
							this.getClass().getClassLoader().getResource("Polypumpkins - Abandoned Zone (feat. LC Destroyer).wav"),
							this.getClass().getClassLoader().getResource("GosT-NascencyLvl10.wav")};
		return allTracks;
	}

	public void playLvlUp() {
		this.lvlUp.start();
		this.lvlUp = getLvlUp();
	}
	
	
	public void playFoodEaten() {
		this.eatDaFood.start();
		this.eatDaFood = getFoodClip();
	}
	
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
	
	public Clip getsoundTrack(int level){
		URL gameSound = null;
		if(level == 0) {
			gameSound = this.getClass().getClassLoader().getResource("synthWaveLoop90bpm.wav"); 
		}else if(level==2) {
			gameSound = this.getClass().getClassLoader().getResource("synthWaveLoop3.wav"); 
		}else if(level == 5) {
			gameSound = this.getClass().getClassLoader().getResource("synthWaveLoop.wav"); 
		}else if(level == 8) {
			gameSound = this.getClass().getClassLoader().getResource("Polypumpkins - Abandoned Zone (feat. LC Destroyer).wav");
		}else if(level == 10) {
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

	public void stopMusic() {
		for(Clip c:soundTrack) {
			c.stop();
		}
	}
}


