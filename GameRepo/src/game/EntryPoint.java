package game;

import util.*;
import javax.swing.*;

/*
 *  The entry point of java program. This is where our game application construct and start running.
 */
public class EntryPoint{

	public static void main(String args[])
	{
		GameApplication game = new GREGame(); 
		SwingUtilities.invokeLater(new Runnable() {
			//Start running the game instance.
			public void run() {game.initialize();} 
		});	
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	};
}
