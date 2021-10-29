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
GameTimer timer = GameTimer.getInstance();
	long timeElapsed; 

	

	private MapNode node;
	private boolean alive;
	private boolean surround;
	private BufferedImage[] normaldio=new BufferedImage[5];
	private BufferedImage angrydio;
	private ShortestPath s;
	int n=0;
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
		
		if(Map.getInstance().getMap()[dir.x][dir.y]==Map.getInstance().getDummy()) {
			setAlive(false);
		}
		else {
			
			setNode(Map.getInstance().getMap()[dir.x][dir.y]);
		}
	
	}
	
	public void moveSurround() {
		ArrayList<MapNode> adjacency=node.getAdjacency();
		int n=0;
		while(adjacency.get(n)!=null) {
			if(!adjacency.get(n).block()) {
				setNode(adjacency.get(n));
				break;
			}
			else {
				n++;
			}
		}
	}



	@Override
	public void enter() {
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/walk0.png", "/circle/WALK0.png" );

		try {
			normaldio[0] = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/walk1.png", "/circle/walk1.png" );
		try {
			normaldio[1] = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/walk2.png", "/circle/walk2.png" );
		try {
			normaldio[2] = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/walk3.png", "/circle/walk3.png" );
		try {
			normaldio[3] = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/walk4.png", "/circle/walk4.png" );
		try {
			normaldio[4] = ImageIO.read(stream);
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
			timeElapsed += timer.DeltaTime();
			int n=(int) (timeElapsed/280);
			g.drawImage(normaldio[n], (int)node.getState().displayPos.y,  (int)node.getState().displayPos.x, (int)(2*node.getState().radius), (int)(2*node.getState().radius), null);
			if(n==4) {
				timeElapsed=0;
			}
		}
	}



	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(Mouse mouse, Key key) {
		s = new ShortestPath();
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
