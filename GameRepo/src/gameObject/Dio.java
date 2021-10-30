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
	
	private void setNode(MapNode node) {
		this.node=node;
	}
	
	private void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}
	
	private void setSurround(boolean surround) {
		this.surround = surround;
	}

	private boolean isSurround() {
		return surround;
	}
	
	public boolean isEscape() {
		return node.isBorder();
	}
	//return next "random" Node when Dio is angry, null if Dio is dead
	private MapNode moveSurround() {
		ArrayList<MapNode> adjacency=node.getAdjacency();
		int n=0;
		while(true) {
			if(!adjacency.get(n).getState().blocked) {
				return adjacency.get(n);
			}
			else {
				n++;
			}
			if(n==6) {
				setAlive(false);
				break;
			}
		}
		return null;
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
			int counter=(int) (timeElapsed/280);
			AffineTransform transform = new AffineTransform(normalDio[counter].getScaleX(), 0.0, 0.0, normalDio[counter].getScaleY(), node.getState().displayPos.y, node.getState().displayPos.x);
			g.drawImage(normalDio[counter].getImage(), transform, null);
			if(counter==4) {
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
		// TODO Auto-generated method stub
		
	}
	public void update(Mouse mouse, Key key, boolean move) {
		if (isAlive()) {
			MapNode dir = null; 
			//whether change to surround from normal / death from surround
			if(!isSurround()) {
				s = new ShortestPath();
				MapNodeInfo info = node.getState();	
				dir = s.computeDecision(info.abstractPos);
				if(dir==null) {
					setSurround(true);
				}
			}
			if(isSurround()) {
				dir = moveSurround();
			}
			if(move && dir!=null) {
				setNode(dir);
			}
		}
	}
	
}
