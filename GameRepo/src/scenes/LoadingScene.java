package scenes;

import util.*;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

public class LoadingScene extends Scene{
		
	private ExecutorService threadPool;
	private List<Callable<Boolean>> loadTasks;
	private List<Future<Boolean>> loadResults;
	private int numberOfTasks;
	
	
	@Override
	public void enter() {
		
//		threadPool = Executors.newCachedThreadPool();
//		loadTasks = new ArrayList<Callable<Boolean>>();
//		
//		loadTasks.add( new Callable<Boolean>() {
//			@Override
//			public Boolean call() throws Exception {
//				InputStream stream = ResourceLoader.load( LoadingScene.class,
//						"res/textures/bricks.jpg",
//						"/textures/bricks.jpg" );
//				
//				BufferedImage image = ImageIO.read( stream );
//				
//				Vector2f position = new Vector2f(
//					 GlobalConstants.WORLD_WIDTH / 2.0f,
//					 GlobalConstants.WORLD_HEIGHT / 2.0f 
//				);
//				
//				Transform transform = new Transform(position, new Vector2f(1.0f, 1.0f));
//				Texture texture = new Texture(image, transform);
//				testRenderScene = texture;
//				
//				return Boolean.TRUE;
//			}
//		});
//		
//		loadResults = new ArrayList<Future<Boolean>>();
//		for( Callable<Boolean> task : loadTasks ) {
//			loadResults.add( threadPool.submit( task ) );
//		}
//		numberOfTasks = loadResults.size();
//		if( numberOfTasks == 0 ) {
//			numberOfTasks = 1;
//		}
//		
//		
//		//------------------------------------------
//		Iterator<Future<Boolean>> it = loadResults.iterator();
//		while (it.hasNext()) {
//			Future<Boolean> next = it.next();
//			if (next.isDone()) {
//				try {
//					if (next.get()) {
//						it.remove();
//					}
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
