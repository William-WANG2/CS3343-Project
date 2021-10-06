package gameObject;

import util.*;

public class Map {
	//singleton pattern to use the map across the game
	private static Map instance = new Map();
	public static Map getInstance() {
		return instance;
	}
	//scale factor of the main game area
	static final double scale = 3/4;
	private MapNode[][] map;
	private MapNode dummyNode; //used to represent the abstract destination in the shortest path algorithm
	private Vector2d size;
	public void initialize(int m, int n, int width, int height) {
		map = new MapNode[m][n];
		dummyNode = new MapNode(0, 0, 0, 0);
		size = new Vector2d(m, n);
		int displayW = (int)(scale * width); //valid region to display the map
		int radius = (int)(2 * displayW / (2.2 * n)); //radius for node
		int interval = (int)(0.1 * displayW / 2.2 * n); //interval between node
		int offset = 3 * interval; //offset for odd row
		int sX = (int)(1/2 * (1-scale) * width); //left-top x coordinate
		int sY = (int)(1/2 * (1-scale) * width); //left-top y coordinate
		//initialize the map
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				//set x, y coordinates
				if(i%2 == 0) {
					map[i][j] = new MapNode(sX+interval+2*j*(radius+interval), sY+interval+2*j*(radius+interval), i, j);
				}
				else {
					map[i][j] = new MapNode(sX+interval+2*j*(radius+interval)+offset, sY+interval+2*j*(radius+interval), i, j); //with offset to the right
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
						if(j+1>=0) {
							map[i][j].addAdj(map[i-1][j+1]);
						}
						map[i][j].addAdj(map[i-1][j]);
					}
					if(i+1<m) {
						if(j+1>=0) {
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
}
