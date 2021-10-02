package game;

import util.*;
import testCase.*;

import javax.swing.*;
import java.awt.*;

public class EntryPoint{
	public static void main(String args[])
	{
		Test00 test = new Test00();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {test.initialize();}
		});	
	}
}