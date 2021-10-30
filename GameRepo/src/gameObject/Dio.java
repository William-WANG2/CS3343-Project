package gameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

import util.*;
import algorithm.*;

public class Dio implements FrameUpdate {
GameTimer timer = GameTimer.getInstance();
	long timeElapsed; 

	

	private MapNode node;
	private boolean alive;
	private boolean surround;
	private Texture[] normalDio;
	private Texture angryDio;
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
		normalDio = new Texture[5];
		String path;
		for(int i = 0; i < 5; i++) {
			path = String.format("res/circle/walk%d.png", i);
			normalDio[i] = Texture.loadImage(path, 0, 0, (int)(2*node.getState().radius), (int)(2*node.getState().radius));
		}
		path = "res/circle/black.png";
		angryDio = Texture.loadImage(path, 0, 0, (int)(2*node.getState().radius), (int)(2*node.getState().radius));
	}



	@Override
	public void render(Graphics2D g) {
		if(surround) {
			AffineTransform transform = new AffineTransform(angryDio.getScaleX(), 0.0, 0.0, angryDio.getScaleY(), node.getState().displayPos.y, node.getState().displayPos.x);
			g.drawImage(angryDio.getImage(), transform, null);
		}
		else {
			timeElapsed += timer.DeltaTime();
			int n=(int) (timeElapsed/280);
			AffineTransform transform = new AffineTransform(normalDio[n].getScaleX(), 0.0, 0.0, normalDio[n].getScaleY(), node.getState().displayPos.y, node.getState().displayPos.x);
			g.drawImage(normalDio[n].getImage(), transform, null);
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
