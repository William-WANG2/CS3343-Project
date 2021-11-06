package util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
public class Music {
    private static Clip clip;
	public static void playMusic(String musicLocation)
	{
		try
		{
			File musicPath = new File(musicLocation);
			
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-30);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else
			{
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close() {
		clip.stop();
		
	}
	
	public static void open() {
		clip.start();
		
	}

}