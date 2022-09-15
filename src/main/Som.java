package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {

		Clip clip;
		public URL soundURL[] = new URL[30];
		
		public Som() {
			
			soundURL[0] = getClass().getResource("/musica/temcabareEssaNight.wav");
			soundURL[1] = getClass().getResource("/musica/entaoeleEh.wav");
			soundURL[2] = getClass().getResource("/musica/queissomeuFilho.wav");
		}
		
		public void setFile(int i){
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
				clip = AudioSystem.getClip();
				clip.open(ais);
				
			}catch(Exception e){
				
			}
		}
		public void play() {
			clip.start();
			
		}
		public void loop() {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		public void stop() {
			clip.stop();
		}
}
