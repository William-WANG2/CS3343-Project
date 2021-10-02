package scenes;

import util.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import javagames.completegame.admin.GameConstants;
import javagames.completegame.state.GameLoading;
import javagames.util.Matrix3x3f;
import javagames.util.ResourceLoader;
import javagames.util.Sprite;
import javagames.util.Vector2f;

public class LoadingScene extends Scene{
	
	private ExecutorService threadPool;
	private List<Callable<Boolean>> loadTasks;
	private List<Future<Boolean>> loadResults;
	
	public void enter() {
		
		threadPool = Executors.newCachedThreadPool();
		loadTasks = new ArrayList<Callable<Boolean>>();
		
		loadTasks.add( new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				InputStream stream = ResourceLoader.load( LoadingScene.class,
						"res/textures/bricks.png",
						"/images/space_background_600x600.png" );
				BufferedImage image = ImageIO.read( stream );
				Vector2f worldTopLeft = new Vector2f(
					-GameConstants.WORLD_WIDTH / 2.0f,
					GameConstants.WORLD_HEIGHT / 2.0f 
				);
				Vector2f worldBottomRight = new Vector2f(
					GameConstants.WORLD_WIDTH / 2.0f,
					-GameConstants.WORLD_HEIGHT / 2.0f 
				);
				
				Sprite sprite =
						new Sprite( image, worldTopLeft, worldBottomRight );
				Matrix3x3f viewport =
						(Matrix3x3f)controller.getAttribute( "viewport" );
				sprite.scaleImage( viewport );
				controller.setAttribute( "background", sprite );
				return Boolean.TRUE;
			}
		});
	}
	
}
