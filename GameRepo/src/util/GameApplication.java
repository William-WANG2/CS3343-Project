package util;

import scenes.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import gameObject.MapNode;

public abstract class GameApplication extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Window context
	public Canvas canvas;
	Color backgroundColor = Color.WHITE;
	int clientWidth = GlobalConstants.APP_WIDTH;
	int clientHeight = GlobalConstants.APP_HEIGHT;
	
	String winTitle = GlobalConstants.APP_TITLE;
	BufferStrategy bs;
	Graphics g;
	
	//Game context
	private volatile boolean running;
	protected Thread gameThread;
	public static Scene currScene;
	
	//Time context
	private int frameCount = 0;
	private float timeElapsed = 0;
	GameTimer timer = GameTimer.getInstance();
	
	//Event context
	public Mouse mouse = new Mouse();
	public Key key = new Key();
	
	public GameApplication() {
	}
	
	public void initialize() {
		
		//initialize window context
		canvas = new Canvas();
		canvas.setBackground(backgroundColor);
		canvas.setIgnoreRepaint(true); 
		getContentPane().add(canvas);
		setLocationByPlatform(true);
		setSize(clientWidth, clientHeight);
		setTitle(winTitle);
		setVisible(true);
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		canvas.requestFocus();
		setupInput();
	}
	
	private void gameloop(GameTimer timer) {
		timer.Tick();
		update();
		render();
		exit();
	}
	
	public void terminate(){
	}
	
	private void update(){
		currScene.update();
	}
	
	private void renderFrame(Graphics g){
		g.clearRect(0, 0, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);
		currScene.render((Graphics2D)g);
	}
	
	private void render(){	
		do {
			do {
				Graphics g = null;
				try {
					g = bs.getDrawGraphics();
					renderFrame(g);
					//calculateFrameRate(g);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (g != null) {
						g.dispose();
					}
				}
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}
	private void exit() {
		currScene.exit();
	}
	
//	private void calculateFrameRate(Graphics g) {
//		
//		frameCount++;
//		timeElapsed += timer.DeltaTime();
//		
//		if((timeElapsed) >= 1000)
//		{			
//			String frameRate = String.format("FPS %s", frameCount);
//			g.clearRect( 0, 0, clientWidth, clientHeight);
//			g.setColor(Color.RED);
//			g.setFont(new Font("TimesRoman", Font.BOLD, 10));
//			g.drawString(frameRate, 30, 30);
//			
//			frameCount = 0;
//			timeElapsed -= 1000;
//		}	
//	}
	
	public void run() {
		running = true;
		timer.Reset();
		currScene.enter();
		while (running) {
			gameloop(timer);
		}
		terminate();
	}
	
	public abstract void loadScene(Scene next);
	
	private void setupInput() {
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouse.isClicked = true;
				mouse.mousePos.x = e.getX();
				mouse.mousePos.y = e.getY();
			}
		});
		canvas.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				key.queuingEvent.add(e.getExtendedKeyCode());
			}
		});
	}
}