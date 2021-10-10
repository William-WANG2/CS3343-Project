package gameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;

import util.*;
import algorithm.*;
import testCase.TestDrawingScene;

public class Dio implements FrameUpdate {
	private MapNode node;
	private boolean alive;
	private boolean surround;
	private BufferedImage normaldio;
	private BufferedImage angrydio;
	private ShortestPath s;
	
	private static Dio dio = new Dio();
	public static Dio getInstance() {
		return dio;
	}
	private Dio() {
	}
	
	public void initialize(MapNode node) {
		this.node=node;
		alive=true;
		surround=false;
		s = new ShortestPath();
		s.initialize();
	}
	
	public void setNode(MapNode node) {
		this.node=node;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void setSurround(boolean surround) {
		this.surround = surround;
	}

	public boolean whetherSurround() {
		return surround;
	}
	
	public void moveNormal(Vector2d dir) {
		
		setNode(Map.getInstance().getMap()[dir.x][dir.y]);
	
	}
	
	public void moveSurround() {
		ArrayList<MapNode> adjacency=node.getAdjacency();
		if(!adjacency.isEmpty()) {
			setNode(adjacency.get(0));
		}
		else {
			setAlive(false);
		}
		
	}



	@Override
	public void enter() {
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/blue.png", "/circle/blue.png" );
		try {
			normaldio = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/black.png", "/circle/black.png" );
		try {
			angrydio = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void render(Graphics2D g) {
		if(surround) {
			g.drawImage(angrydio, (int)node.getState().displayPos.y,  (int)node.getState().displayPos.x, (int)(2*node.getState().radius), (int)(2*node.getState().radius), null);
		}
		else {
			g.drawImage(normaldio, (int)node.getState().displayPos.y,  (int)node.getState().displayPos.x, (int)(2*node.getState().radius), (int)(2*node.getState().radius), null);
		}
	}



	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(Mouse mouse) {
		MapNodeInfo info = node.getState();	
		Vector2d dir = s.computeDecision(info.abstractPos);
		if (isAlive()) {
			if(dir==null) {
				setSurround(true);
				moveSurround();
			}
			else {
				moveNormal(dir);
			}
			
		}
	}
	
	
	
	
	
	
	
}//xdfdzfaDFAF
