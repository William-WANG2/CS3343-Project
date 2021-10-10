package gameObject;

import java.awt.Graphics2D;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import fileReader.XMLReader;
import keyValue.*;
import util.*;

public class Map implements FrameUpdate{
	//singleton pattern to use the map across the game
	private static Map instance = new Map();
	public static Map getInstance() {
		return instance;
	}
	//scale factor of the main game area
	static final float scale = 0.75f;
	private MapNode[][] map;
	private MapNode dummyNode; //used to represent the abstract destination in the shortest path algorithm
	private Vector2d size;
	private Map() {}
	public void initialize(int m, int n, int width, int height, String path) {
		map = new MapNode[m][n];
		dummyNode = new MapNode(0, 0, 0, 0, 0, new WordInfo("",""));
		size = new Vector2d(m, n);
		ArrayList<Info> wordList = new ArrayList<Info>(m*n);
		for(int i=0; i<m*n; i++) {
			wordList.add(new WordInfo("a", "a"));
		}
//		try {
//			wordList = XMLReader.convert(path, m, n);
//		} catch (ExMapExceedWordSize e) {
//			e.printStackTrace();
//		}
		float displayW = scale * width; //valid region to display the map
		float radius = (2 * displayW / (2.02f * n)); //radius for node
		float interval = (0.002f * displayW / 2.2f * n); //interval between node
		float offset = radius; //offset for odd row
		float sX = (0.5f * (1-scale) * width); //left-top x coordinate
		float sY = (0.5f * (1-scale) * height); //left-top y coordinate
		int cntWord = 0; //auxiliary variable to count the words that has been loaded
		//initialize the map
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				//set x, y coordinates
				if(i%2 == 0) {
					map[i][j] = new MapNode(sY+interval+2*i*(radius+interval), sX+interval+2*j*(radius+interval), radius, i, j, wordList.get(cntWord));
				}
				else {
					map[i][j] = new MapNode(sY+interval+2*i*(radius+interval), sX+interval+2*j*(radius+interval)+offset, radius, i, j, wordList.get(cntWord)); //with offset to the right
				}
				//set adjacent nodes
				if(i%2 == 0) {
					if(i-1>=0) {
						if(j-1>=0) {
							map[i][j].addAdj(map[i-1][j-1]);
						}
						map[i][j].addAdj(map[i-1][j]);
					}
					if(i+1<m) {
						if(j-1>=0) {
							map[i][j].addAdj(map[i+1][j-1]);
						}
						map[i][j].addAdj(map[i+1][j]);
					}
					if(j-1>=0) {
						map[i][j].addAdj(map[i][j-1]);
					}
					if(j+1<n) {
						map[i][j].addAdj(map[i][j+1]);
					}
				}
				else {
					if(i-1>=0) {
						if(j+1<n) {
							map[i][j].addAdj(map[i-1][j+1]);
						}
						map[i][j].addAdj(map[i-1][j]);
					}
					if(i+1<m) {
						if(j+1<n) {
							map[i][j].addAdj(map[i+1][j+1]);
						}
						map[i][j].addAdj(map[i+1][j]);
					}
					if(j-1>=0) {
						map[i][j].addAdj(map[i][j-1]);
					}
					if(j+1<n) {
						map[i][j].addAdj(map[i][j+1]);
					}
				}
				cntWord++;
			}
		}
		//link border nodes to dummy destination
		for(int i=0; i<m; i++) {
			map[i][0].addAdj(dummyNode);
			map[i][n-1].addAdj(dummyNode);
			dummyNode.addAdj(map[i][0]);
			dummyNode.addAdj(map[i][n-1]);
		}
		for(int i=1; i<n-1; i++) {
			map[0][i].addAdj(dummyNode);
			map[m-1][i].addAdj(dummyNode);
			dummyNode.addAdj(map[0][i]);
			dummyNode.addAdj(map[m-1][i]);
		}
	}
	
	public Vector2d getSize() {
		return size;
	}
	public MapNode[][] getMap() {
		return map;
	}
	public MapNode getDummy() {
		return dummyNode;
	}

	@Override
	public void enter() {
		for(int i=0; i<size.x; i++) {
			for(int j=0; j<size.y; j++) {
				map[i][j].enter();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		for(int i=0; i<size.x; i++) {
			for(int j=0; j<size.y; j++) {
				map[i][j].render(g);
			}
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Mouse mouse) {
		if(mouse.mouseClicked) {
			for(int i=0; i<size.x; i++) {
				for(int j=0; j<size.y; j++) {
					map[i][j].update(mouse);
				}
			}
			if(mouse.mouseClicked) {
				mouse.mouseClicked = false;
			}
			else {
				Dio.getInstance().update(mouse);
			}
		}
	}
}
