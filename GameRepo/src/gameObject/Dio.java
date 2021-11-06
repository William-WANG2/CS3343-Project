package gameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

import util.*;
import algorithm.*;

public class Dio{

	GameTimer timer = GameTimer.getInstance();
	long timeElapsed; 

	private MapNode node;
	private boolean alive;
	private boolean surround;
	private Texture[] normalDio;

	private int sequenceIndex;

	private Texture[] angryDio;

	private ShortestPath s;
	private static Dio dio = new Dio();
	public static Dio getInstance() {
		return dio;
	}
	private Dio() {
	}
	
	private void setNode(MapNode node) {
		this.node=node;
	}
	
	public MapNode getNode() {
		return this.node;
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

	public void enter(MapNode node) {
		this.node=node;
		alive=true;
		surround=false;
		normalDio = new Texture[8];
		angryDio = new Texture[8];
		String path;
		for(int i = 0; i < 8; i++) {
			path = String.format("res/sprite/kunkun%d.png", i+1);
			normalDio[i] = Texture.loadImage(path, 0, 0, (int)(2.5*node.getState().radius), (int)(4*node.getState().radius));
		}
		for(int i = 0; i < 8; i++) {
			path = String.format("res/sprite/angryKunkun%d.png", i+1);
			angryDio[i] = Texture.loadImage(path, 0, 0, (int)(2.5*node.getState().radius), (int)(4*node.getState().radius));
		}
	}

	public void render(Graphics2D g) {
		
		timeElapsed += timer.DeltaTime();
		
		if(surround) {
			AffineTransform transform = new AffineTransform(angryDio[sequenceIndex].getScaleX(), 0.0, 0.0, angryDio[sequenceIndex].getScaleY(), (int)node.getState().displayPos.y - (int)(0.8*node.getState().radius), (int)node.getState().displayPos.x - (int)(2*node.getState().radius));
			g.drawImage(angryDio[sequenceIndex].getImage(), transform, null);
		}
		else {

			timeElapsed += timer.DeltaTime();
			if(timeElapsed >= 150) {
				timeElapsed = 0;
				sequenceIndex++;
				sequenceIndex%=6;
			}
			
			AffineTransform transform = new AffineTransform(normalDio[sequenceIndex].getScaleX(), 0.0, 0.0, normalDio[sequenceIndex].getScaleY(), node.getState().displayPos.y, node.getState().displayPos.x);
			g.drawImage(normalDio[sequenceIndex].getImage(), transform, null);
			
		}
	}

	public void upadateAnimationSequence() {
		
		
	}
	
	public void updatePosition() {

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
			if(dir!=null) {
				setNode(dir);
			}
		}
	}
	
}
