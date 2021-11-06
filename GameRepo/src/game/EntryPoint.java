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
		GameApplication test = new GREGame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {test.initialize();}

		});	
		
		
		
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	};

	}
