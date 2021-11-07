/*This class aims to provide a functionality to computer the shortest path for the sprite to determine the next node*/
package algorithm;

import gameObject.*;
import util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ShortestPath {
	
	private Map map;
	//according to BFS, computer the next position to move
	private Vector2d[][] path;
	private Vector2d res;
	//print the next node the sprite should go according "path"
	private Vector2d getNext(Vector2d end, Vector2d start) {
		if(path[end.x][end.y] == start) {
			return end;
		}
		else {
			return getNext(path[end.x][end.y], start);
		}
	}
	public ShortestPath() {
		map = Map.getInstance();
		path = new Vector2d[map.getColRowCount().x][map.getColRowCount().y];
	}
	
	//return last node in the computed shortest path
	private Vector2d BFS(Vector2d s) throws NoSuchElementException {
		//initialize the variables
		res = null;
		boolean visited[][] = new boolean[map.getColRowCount().x][map.getColRowCount().y];
		Queue<MapNode> Q = new LinkedList<MapNode>();
		Q.add(map.getMap()[s.x][s.y]);
		visited[s.x][s.y] = true;
		while(!Q.isEmpty()) {
			MapNode temp = Q.remove();
			visited[temp.getNodeInformation().abstractPos.x][temp.getNodeInformation().abstractPos.y] = true;
			ArrayList<MapNode> adj = temp.getAdjacency();
			for(MapNode n: adj) {
				if(visited[n.getNodeInformation().abstractPos.x][n.getNodeInformation().abstractPos.y] || n.getNodeInformation().blocked) {
					continue;
				}
				else {
					if(n == map.getDummy()) {
						res = temp.getNodeInformation().abstractPos;
						return res;
					}
					else {
						visited[n.getNodeInformation().abstractPos.x][n.getNodeInformation().abstractPos.y] = true;
						Q.add(n);
						path[n.getNodeInformation().abstractPos.x][n.getNodeInformation().abstractPos.y] = temp.getNodeInformation().abstractPos;
					}
				}
			}	
		}
		return res;
	}
	//Return 0 is the sprite is trapped, otherwise, return 1 - 6 indicating where to move next
	public MapNode computeDecision(Vector2d vec) {	
		Vector2d lastNode = BFS(vec);
		if(lastNode == null) {
			return null; //The sprite is trapped
		}
		else {
			return map.getMap()[getNext(lastNode, vec).x][getNext(lastNode, vec).y];
		}
	}
}
