package game;

import util.*;
import testCase.*;

import javax.swing.*;
import java.awt.*;

public class EntryPoint{
	public static void main(String args[])
	{
		TestDrawingApp test = new TestDrawingApp();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {test.initialize();}
		});	
		
		
		String filepath = "res/textures/CXKisBeautiful.wav";
		Music musicObject = new Music();
		musicObject.playMusic(filepath);
		
	}
}