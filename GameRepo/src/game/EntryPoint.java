package game;

import util.*;
import javax.swing.*;

public class EntryPoint{
	public static void main(String args[])
	{
		GameApplication test = new GREGame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {test.initialize();}

		});	
		
		
		String filepath = "res/textures/CXKisBeautiful.wav";
		Music musicObject = new Music();
		musicObject.playMusic(filepath);
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	};

	}
