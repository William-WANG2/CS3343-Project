package util;

import scenes.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import javax.swing.*;

public abstract class GameApplication extends JFrame implements Runnable{
	
	//Window context
	Canvas canvas;
	Color backgroundColor = Color.BLACK;
	int clientWidth = 800;
	int clientHeight = 600;
	int clientRatio = 800/600;
	String winTitle = "JiJiCat";
	BufferStrategy bs;
	Graphics g;
	
	//Game context
	private volatile boolean running;
	private Thread gameThread;
	public Scene currScene;
	
	//Time context
	private int frameCount = 0;
	private float timeElapsed = 0;
	GameTimer timer = GameTimer.getInstance();
	
	public GameApplication() {
	}
	
	public void initialize() {
		//initialize window context
		canvas = new Canvas();
		canvas.setBackground(backgroundColor);
		canvas.setIgnoreRepaint(true);
		getContentPane().add(canvas);
		setLocationByPlatform(true);
		canvas.setSize(clientWidth, clientHeight);
		setTitle(winTitle);
		
		//setupInput(canvas); Input registers, later implement
		
		setVisible(true);
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		canvas.requestFocus();
	}
	
	public void gameloop(GameTimer timer) {
		update();
		render();
	}
	
	public void terminate(){
	}
	
	public void update(){
	}
	
	public void render(){
		do {
			do {
				Graphics g = null;
				try {
					g = bs.getDrawGraphics();
					calculateFrameRate(g);
					//renderFrame(g);
				} finally {
					if (g != null) {
						g.dispose();
					}
				}
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}
	
	public void calculateFrameRate(Graphics g) {
		//This is for calculating frame rate per second. 
		frameCount++;
		timeElapsed += timer.DeltaTime();
		
		if((timeElapsed) >= 1000)
		{			
			String frameRate = String.format("FPS %s", frameCount);
			
			g.setColor(Color.GREEN);
			g.drawString(frameRate, 30, 30);
			
			frameCount = 0;
			timeElapsed -= 1000;
		}	
	}
	
	public void run() {
		running = true;
		initialize();
		while (running) {
			timer.Tick();
			gameloop(timer);
		}
		terminate();
	}
	
	
	
	
}