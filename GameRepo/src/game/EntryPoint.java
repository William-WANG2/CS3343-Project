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
		
		
		
		
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	};

	}
