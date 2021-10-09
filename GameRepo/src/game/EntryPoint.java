package game;

import gameObject.*;
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
	}
}