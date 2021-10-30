package gameObject;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import fileReader.*;
import keyValue.*;
import util.*;

public class Map implements FrameUpdate{
	//singleton pattern to use the map across the game
	private static Map instance = new Map();
	public static Map getInstance() {
		return instance;
	}
	//scale factor of the main game area
	private MapNode[][] map;
	private MapNode dummyNode; //used to represent the abstract destination in the shortest path algorithm
	private Vector2d colRowCount;
	
	private Map() {
		
	}
	
	public void initialize(int columnNodeCount, int rowNodeCount, int width, int height, int centerX, int centerY, String vocabularyPath) {
		
		map = new MapNode[columnNodeCount][rowNodeCount];
		dummyNode = new MapNode(0, 0, 0, 0, 0, new WordInfo("",""));
		colRowCount = new Vector2d(columnNodeCount, rowNodeCount);
		ArrayList<Info> wordList = new ArrayList<Info>(columnNodeCount * rowNodeCount);
		ReaderApp rdapp = new ReaderFactory();
		try {
			wordList = rdapp.getList(vocabularyPath, columnNodeCount, rowNodeCount);
		} catch (ExMapExceedWordSize e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//This algorithm is for square map only, please modify the interface.
		float radius = width / rowNodeCount; //radius for node
		float interval = radius * (float)0.2; //interval between node
		float sX = centerX - (rowNodeCount + 1) * (radius + interval);//left-top x coordinate
		float sY = centerY - (columnNodeCount + 1)* (radius + interval);//left-top y coordinate
		int cntWord = 0; //auxiliary variable to count the words that has been loaded
		//initialize the map
		for(int i=0; i<columnNodeCount; i++) {
			for(int j=0; j<rowNodeCount; j++) {
				//set x, y coordinates
				if(i%2 == 0) {
					map[i][j] = new MapNode(sY+interval+2*i*(radius+interval), sX+interval+2*j*(radius+interval), radius, i, j, wordList.get(cntWord));
				}
				else {
					map[i][j] = new MapNode(sY+interval+2*i*(radius+interval), sX+interval+2*j*(radius+interval)+radius, radius, i, j, wordList.get(cntWord)); //with offset to the right
				}
				cntWord++;
			}
		}
		for(int i=0; i<columnNodeCount; i++) {
			for(int j=0; j<rowNodeCount; j++) {
				//set adjacent nodes
				if(i%2 == 0) {
					if(i-1>=0) {
						if(j-1>=0) {
							map[i][j].addAdj(map[i-1][j-1]);
						}
						map[i][j].addAdj(map[i-1][j]);
					}
					if(i+1<columnNodeCount) {
						if(j-1>=0) {
							map[i][j].addAdj(map[i+1][j-1]);
						}
						map[i][j].addAdj(map[i+1][j]);
					}
					if(j-1>=0) {
						map[i][j].addAdj(map[i][j-1]);
					}
					if(j+1<rowNodeCount) {
						map[i][j].addAdj(map[i][j+1]);
					}
				}
				else {
					if(i-1>=0) {
						if(j+1<rowNodeCount) {
							map[i][j].addAdj(map[i-1][j+1]);
						}
						map[i][j].addAdj(map[i-1][j]);
					}
					if(i+1<columnNodeCount) {
						if(j+1<rowNodeCount) {
							map[i][j].addAdj(map[i+1][j+1]);
						}
						map[i][j].addAdj(map[i+1][j]);
					}
					if(j-1>=0) {
						map[i][j].addAdj(map[i][j-1]);
					}
					if(j+1<rowNodeCount) {
						map[i][j].addAdj(map[i][j+1]);
					}
				}
			}
		}
		//link border nodes to dummy destination
		for(int i=0; i<columnNodeCount; i++) {
			map[i][0].addAdj(dummyNode);
			map[i][rowNodeCount-1].addAdj(dummyNode);
			dummyNode.addAdj(map[i][0]);
			dummyNode.addAdj(map[i][rowNodeCount-1]);
		}
		for(int i=1; i<rowNodeCount-1; i++) {
			map[0][i].addAdj(dummyNode);
			map[columnNodeCount-1][i].addAdj(dummyNode);
			dummyNode.addAdj(map[0][i]);
			dummyNode.addAdj(map[columnNodeCount-1][i]);
		}
		for(int i=0; i<colRowCount.x; i++) {
			for(int j=0; j<colRowCount.y; j++) {
				map[i][j].enter();
			}
		}
	}
	
	public Vector2d getColRowCount() {
		return colRowCount;
	}
	public MapNode[][] getMap() {
		return map;
	}
	public MapNode getDummy() {
		return dummyNode;
	}

	@Override
	public void enter() {
	}

	@Override
	public void render(Graphics2D g) {
		for(int i=0; i<colRowCount.x; i++) {
			for(int j=0; j<colRowCount.y; j++) {
				map[i][j].render(g);
			}
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Mouse mouse, Key key) {
		if(mouse.mouseClicked) {
			for(int i=0; i<colRowCount.x; i++) {
				for(int j=0; j<colRowCount.y; j++) {
					map[i][j].update(mouse, key);
				}
			}
			if(mouse.mouseClicked) {
				mouse.mouseClicked = false;
			}
		}
	}
}
