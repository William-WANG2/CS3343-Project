package game;

import util.*;
import javax.swing.*;

public class EntryPoint{
	public static void main(String args[])
	{
		GameApplication game = new GREGame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {game.initialize();}

		});	
		
		
		String filepath = "res/textures/CXKisBeautiful.wav";
		Music musicObject = new Music();
		musicObject.playMusic(filepath);
		
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	};

	}
