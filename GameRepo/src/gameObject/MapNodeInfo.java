package gameObject;

import util.*; 

public class MapNodeInfo {
	//position displayed
	public Vector2f displayPos;
	//abstract position
	public Vector2d abstractPos;
	public float radius;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public WordInfo wordInfo; 
	
	public MapNodeInfo(float x, float y, float radius, int mapRow, int mapColumn, WordInfo wordInformation) {
		this.displayPos = new Vector2f(x, y);
		this.abstractPos = new Vector2d(mapRow, mapColumn);
		this.blocked = false;
		this.radius = radius;
		this.wordInfo = wordInformation;
	}
	
	public WordInfo getWordInfo() {
		return wordInfo;
	}
}
