package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/terra_ff6_theme.wav");
		soundURL[1] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[2] = getClass().getResource("/sound/coin_plus.wav");
		soundURL[3] = getClass().getResource("/sound/flash.wav");	
		soundURL[4] = getClass().getResource("/sound/flash02.wav");	
		soundURL[5] = getClass().getResource("/sound/hitWall.wav");	
		soundURL[6] = getClass().getResource("/sound/hitTrap.wav");	
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			System.out.println("chayj");
			
		}
		catch(Exception e) {
			
		}
		
	}
	
	public void play() {
		System.out.println("YOOOOOO");
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop( ) {
		clip.stop();
	}

}
