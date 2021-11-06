package scenes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import gameObject.Button;
import util.*;
import util.Texture;
public class LoginScene extends Scene {

	private Boolean toNextScene;
	private Mouse mouse;
	private Button startButton;
	private Texture[] cxk;
	private int sequenceIndex;
	private long timeElapsed;
	private GameTimer timer = GameTimer.getInstance();;
	
	private ExecutorService threadPool;
	private List<Callable<Boolean>> loadTasks;
	
	private void handleEvent(Mouse muouse) {
		startButton.handleEvent(muouse);
		if(startButton.isClicked())
		{
			toNextScene = true;
		}
	}
	
	@Override
	public void enter() {
		
		threadPool = Executors.newCachedThreadPool();
		loadTasks = new ArrayList<Callable<Boolean>>();
		
		cxk = new Texture[98];
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 0; i < 9; i++) {
					String path = String.format("res/animation/caixukun 00%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 9; i < 10; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 10; i < 20; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 20; i < 30; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 30; i < 40; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 40; i < 50; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 50; i < 60; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 60; i < 70; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 70; i < 80; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 80; i < 90; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		loadTasks.add(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				for(int i = 90; i < 98; i++) {
					String path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
					cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
				}
				return true;
			}
		});
		
		for(Callable<Boolean> task : loadTasks) {
			threadPool.submit(task);
		}
		threadPool.shutdown();
		
		toNextScene = false;
		mouse = mApp.mouse;
		startButton = new Button("res/textures/StartButton.png", "res/textures/StartButtonClicked.png", GlobalConstants.APP_WIDTH/2 - 130, GlobalConstants.APP_HEIGHT/2 - 150, 200, 200);
		
		sequenceIndex = 0;
		timeElapsed = 0;
		while(true) {
			if(threadPool.isTerminated())
				break;
		}
		
	}

	@Override
	public void update() {
		
		handleEvent(mouse);
		startButton.update();
		
		timeElapsed += timer.DeltaTime();
		if(timeElapsed >= 300) {
			sequenceIndex++;
			sequenceIndex %= 98;
			timeElapsed -= 300;
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(toNextScene) {
			mApp.loadScene(new PlayingScene());
			
		}else {
			
			AffineTransform transform = new AffineTransform(cxk[sequenceIndex].getScaleX(), 0.0, 0.0, cxk[sequenceIndex].getScaleY(), cxk[sequenceIndex].getPosX(), cxk[sequenceIndex].getPosY());
			g.drawImage(cxk[sequenceIndex].getImage(), transform, null);
			startButton.render(g);
		}
	}

	@Override
	public void exit() {
		
	}
}
