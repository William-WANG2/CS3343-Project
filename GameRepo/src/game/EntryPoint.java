package game;

import gameObject.*;
import util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
