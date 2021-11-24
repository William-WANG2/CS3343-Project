package junitTest;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

import org.junit.Test;

import game.EntryPoint;
import game.GREGame;
import scenes.ResultScene;
import util.GameApplication;
import util.GameTimer;

public class Main_test {
	// Unit test begin
	// GameTimer.Reset()
	@Test
	public void test01() {
		GameTimer.getInstance().Reset();
	}
	// Unit test begin

	// Integration Testing begin
	// GameApplication.initialize()
	@Test
	public void test02() {
		GameApplication game = new GREGame();
		game.initialize();

	}

	// GameApplication.loadScene()
	@Test
	public void test03() {
		GameApplication game = new GREGame();
		game.initialize();
		ResultScene r = new ResultScene();
		game.loadScene(r);

	}

	// GameApplication.initialize()
	@Test
	public void test04() {
		GameApplication game = new GREGame();
		game.initialize();
		ResultScene r = new ResultScene();
		game.loadScene(r);
	}
	//GameApplication.run()
	@Test
	public void test05() throws AWTException, InterruptedException {
		GameApplication game = new GREGame(); 
		game.initialize();
		game.loadScene(new ResultScene());
		SwingUtilities.invokeLater(new Runnable() {
			//Start running the game instance.
			public void run(){}
		});	
		Thread.sleep(10);
		Thread.currentThread().interrupt();
	}
	@Test
	public void test06() throws AWTException {
		GameApplication game = new GREGame(); 
		game.initialize();
		game.loadScene(new ResultScene());
		SwingUtilities.invokeLater(new Runnable() {
			//Start running the game instance.
			public void run() {Thread.currentThread().interrupt();} 
		});	
	}
	//GameApplication.initialize()
	@Test
	public void test0000() throws AWTException, InterruptedException {
		GREGame game = new GREGame();
		game.initialize();
		Robot bot = new Robot();
		int mask = InputEvent.BUTTON1_DOWN_MASK;
		bot.mouseMove(100, 100);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
	}
	
	@Test
	public void test000() throws AWTException, InterruptedException {
		GREGame game = new GREGame();
		game.initialize();
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}

	// System Testing
	// main
	@Test
	public void test000000() {
		EntryPoint e = new EntryPoint();
		EntryPoint.main(null);
	}
}
