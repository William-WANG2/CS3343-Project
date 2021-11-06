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
	private boolean isAlive;
	private boolean isTrapped;
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
		this.isAlive = alive;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	private void setSurround(boolean surround) {
		this.isTrapped = surround;
	}

	private boolean isTrapped() {
		return isTrapped;
	}
	
	public boolean isEscape() {
		return node.isBorder();
	}
	//return next "random" Node when Dio is angry, null if Dio is dead
	private MapNode moveSurround() {
		ArrayList<MapNode> adjacency=node.getAdjacency();
		int n=0;
		while(true) {
			if(!adjacency.get(n).getNodeInformation().blocked) {
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
		isAlive=true;
		isTrapped=false;
		normalDio = new Texture[8];
		angryDio = new Texture[8];
		String path;
		for(int i = 0; i < 8; i++) {
			path = String.format("res/sprite/kunkun%d.png", i+1);
			normalDio[i] = Texture.loadImage(path, 0, 0, (int)(2.5*node.getNodeInformation().radius), (int)(4*node.getNodeInformation().radius));
		}
		for(int i = 0; i < 8; i++) {
			path = String.format("res/sprite/angryKunkun%d.png", i+1);
			angryDio[i] = Texture.loadImage(path, 0, 0, (int)(2.5*node.getNodeInformation().radius), (int)(4*node.getNodeInformation().radius));
		}
	}

	public void render(Graphics2D g) {
		
		if(isTrapped) {
			//AffineTransform transformNode = new AffineTransform(node.);
			AffineTransform transform = new AffineTransform(angryDio[sequenceIndex].getScaleX(), 0.0, 0.0, angryDio[sequenceIndex].getScaleY(), node.getNodeInformation().displayPos.y, node.getNodeInformation().displayPos.x - normalDio[sequenceIndex].getHeight() * 0.4);
			g.drawImage(angryDio[sequenceIndex].getImage(), transform, null);
		}
		else {
			AffineTransform transform = new AffineTransform(normalDio[sequenceIndex].getScaleX(), 0.0, 0.0, normalDio[sequenceIndex].getScaleY(), node.getNodeInformation().displayPos.y, node.getNodeInformation().displayPos.x - normalDio[sequenceIndex].getHeight() * 0.4);
			g.drawImage(normalDio[sequenceIndex].getImage(), transform, null);	
		}
	}

	public void upadateAnimationSequencePerFrame() {
		
		timeElapsed += timer.DeltaTime();
		if(timeElapsed >= 150) {
			timeElapsed = 0;
			sequenceIndex++;
			sequenceIndex %= 8;
		}
	}
	
	public void recomputeShortestPath(boolean shouldMove) {

		if (isAlive()) {
			MapNode dir = null; 
			//whether change to surround from normal / death from surround
			if(!isTrapped) {
				s = new ShortestPath();
				MapNodeInfo info = node.getNodeInformation();	
				dir = s.computeDecision(info.abstractPos);
				if(dir==null) {
					setSurround(true);
				}
			}
			if(isTrapped()) {
				dir = moveSurround();
			}
			if(dir!=null && shouldMove) {
				setNode(dir);
			}
		}
	}
	
}
