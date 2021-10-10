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
	private BufferedImage dio;
	private BufferedImage angrydio;
	private ShortestPath s ;
	
	
	
	
	
	public Dio(MapNode node,int[][] position) {
		this.node=node;
		alive=true;
		surround=false;
	
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
		InputStream stream1 = ResourceLoader.load(TestDrawingScene.class, "res/textures/dio.jpg", "/textures/dio.jpg" );
		BufferedImage dio = null;
		try {
			dio = ImageIO.read(stream1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream stream2 = ResourceLoader.load(TestDrawingScene.class, "res/textures/jojo.jpg", "/textures/jojo.jpg" );
		BufferedImage angrydio = null;
		try {
			dio = ImageIO.read(stream2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void render(Graphics2D g) {
		if(surround) {
			g.drawImage(angrydio, (int)node.getState().displayPos.y,  (int)node.getState().displayPos.x, 50, 50, null);
		}
		else {
			g.drawImage(dio, (int)node.getState().displayPos.y,  (int)node.getState().displayPos.x, 50, 50, null);
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
