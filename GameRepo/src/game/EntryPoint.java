package game;

import gameObject.*;
import util.*;
import testCase.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EntryPoint{
	
	public static void main(String args[])
	{
		//Test your program by replacing the constructor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//GameApplication game = new TestDrawingApp();
		
		GameApplication game = new GREGame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {game.initialize();}

		});	
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Don't put anything here
		//String filepath = "res/textures/CXKisBeautiful.wav";
		//Music musicObject = new Music();
		//musicObject.playMusic(filepath);
		
		
		

	};

	}
