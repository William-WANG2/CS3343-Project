package gameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

import util.*;
import algorithm.*;

public class Character{
	
	//Components for animation/character appearance
	GameTimer timer = GameTimer.getInstance(); //The api of timer is thread safe in our application.
	private long timeElapsed; 		   //Time for animation control 
	private int sequenceIndex;
	private Texture[] untrappedAnimation;
	private Texture[] trappedAnimation;
	
	private MapNode node;      //Reference to character standing node
	
	private boolean isAlive;   //The character has next step to move
	private boolean isTrapped; //The character has no path to go out
	
	private ShortestPath s;	  
	
	private static Character character = new Character();
	public static Character getInstance() {
		return character;
	}
	
	private Character() {
	}
	
	public MapNode getNode() {
		return this.node;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isEscape() {
		return node.isBorder();
	}
	
	//return next "random" Node when character is trapped, null if the character is dead
	private MapNode moveSurround() {
		ArrayList<MapNode> adjacency=node.getAdjacency();
		int n=0;
		for(int i=0; i<6; i++) {
			if(!adjacency.get(n).getNodeInformation().blocked) {
				return adjacency.get(n);
			}
			else {
				n++;
			}
			if(n==6) {
				isAlive = false;
				break;
			}
		}
		return null;
	}
	
	public void enter(MapNode node) {
		
		this.node = node;
		
		isAlive = true;
		isTrapped = false;
		untrappedAnimation = new Texture[8];
		trappedAnimation = new Texture[8];
		
		String path;
		for(int i = 0; i < 8; i++) {
			path = String.format("res/sprite/kunkun%d.png", i+1);
			untrappedAnimation[i] = Texture.loadImage(path, 0, 0, (int)(2.5*node.getNodeInformation().radius), (int)(4*node.getNodeInformation().radius));
		}
		for(int i = 0; i < 8; i++) {
			path = String.format("res/sprite/angryKunkun%d.png", i+1);
			trappedAnimation[i] = Texture.loadImage(path, 0, 0, (int)(2.5*node.getNodeInformation().radius), (int)(4*node.getNodeInformation().radius));
		}
	}
	
	public void render(Graphics2D g) {
		//can not use Texture's render since it depends on the nodeInfo which is dynamic
		if(isTrapped) {
			//AffineTransform transformNode = new AffineTransform(node.);
			AffineTransform transform = new AffineTransform(trappedAnimation[sequenceIndex].getScaleX(), 0.0, 0.0, trappedAnimation[sequenceIndex].getScaleY(), node.getNodeInformation().displayPos.y, node.getNodeInformation().displayPos.x - untrappedAnimation[sequenceIndex].getHeight() * 0.4);
			g.drawImage(trappedAnimation[sequenceIndex].getImage(), transform, null);
		}
		else {
			AffineTransform transform = new AffineTransform(untrappedAnimation[sequenceIndex].getScaleX(), 0.0, 0.0, untrappedAnimation[sequenceIndex].getScaleY(), node.getNodeInformation().displayPos.y, node.getNodeInformation().displayPos.x - untrappedAnimation[sequenceIndex].getHeight() * 0.4);
			g.drawImage(untrappedAnimation[sequenceIndex].getImage(), transform, null);	
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

		if (isAlive) {
			MapNode dir = null; 
			//whether change to surround from normal / death from surround
			if(!isTrapped) {
				s = new ShortestPath();
				MapNodeInfo info = node.getNodeInformation();	
				dir = s.computeDecision(info.abstractPos);
				if(dir==null) {
					isTrapped = true;
				}
			}
			if(isTrapped) {
				dir = moveSurround();
			}
			if(dir!=null && shouldMove) {
				node = dir;
			}
		}
	}
}
