package util;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;


public abstract class GameApplication extends JFrame implements Runnable{
	
	private BufferStrategy bs;
	private volatile boolean running;
	private Thread gameThread;
	
	public GameApplication() {
		
	}
	
	public void run() {
		running = true;
		//initialize();
		long curTime = System.nanoTime();
		long lastTime = curTime;
		double nsPerFrame;
		while (running) {
			curTime = System.nanoTime();
			nsPerFrame = curTime - lastTime;
			//gameLoop((float) (nsPerFrame / 1.0E9));
			lastTime = curTime;
		}
		//terminate();
	}
	
	
	
	
}