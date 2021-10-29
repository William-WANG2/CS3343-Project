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
	int clientWidth = 800;
	int clientHeight = 600;
	int clientRatio = 800/600;
	String winTitle = "JiJiCat";
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
	
	public void gameloop(GameTimer timer) {
		timer.Tick();
		update();
		render();
	}
	
	public void terminate(){
	}
	
	public void update(){
		currScene.update();
	}
	
	public void renderFrame(Graphics g){
		currScene.render((Graphics2D)g);
	}
	
	public void render(){
		
		do {
			do {
				Graphics g = null;
				try {
					g = bs.getDrawGraphics();
					renderFrame(g);
					calculateFrameRate(g);
				
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
	
	public void calculateFrameRate(Graphics g) {
		
		frameCount++;
		timeElapsed += timer.DeltaTime();
		
		if((timeElapsed) >= 1000)
		{			
			String frameRate = String.format("FPS %s", frameCount);
			g.clearRect( 0, 0, clientWidth, clientHeight);
			g.setColor(Color.RED);
			g.drawString(frameRate, 30, 30);
			
			frameCount = 0;
			timeElapsed -= 1000;
		}	
	}
	
	public void run() {
		running = true;
		timer.Reset();
		currScene.enter();
		while (running) {
			gameloop(timer);
		}
		terminate();
	}
	
	public final void loadScene(Scene next) {
		
		if(null != currScene) {
			currScene.exit();
		}
		currScene = next;
		currScene.mApp = this;
		currScene.enter();
	}
	
	private void setupInput() {
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouse.mouseClicked = true;
				mouse.mousePos.x = e.getX();
				mouse.mousePos.y = e.getY();
			}
		});
		canvas.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(MapNode.getUpdateNode()!=null) { 
					//Only if the game enter the input answer mode, we process the keyboard signal
					if(e.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE) {
						key.deletePressed = true;
					}
					//Process only the valid keyboard input
					else if(e.getExtendedKeyCode() >= KeyEvent.VK_A && e.getExtendedKeyCode() <= KeyEvent.VK_Z){
						key.queuingChars.add((char) (e.getExtendedKeyCode()-KeyEvent.VK_A+'a'));
					}	
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
	}
}