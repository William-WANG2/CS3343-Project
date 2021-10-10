/*This class aims to provide a functionality to computer the shortest path for the sprite to determine the next node*/
package algorithm;

import gameObject.*;
import util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ShortestPath {
	private Map map = Map.getInstance();
	//according to BFS, computer the next position to move
	private Vector2d[][] path = new Vector2d[map.getSize().x][map.getSize().y];
	private Vector2d res;
	//print the next node the sprite should go according "path"
	private Vector2d getNext(Vector2d end, Vector2d start) {
		if(path[end.x][end.y] == path[start.x][start.y]) {
			return end;
		}
		else {
			return getNext(path[end.x][end.y], start);
		}
	}
	//return last node in the computed shortest path
	private Vector2d BFS(Vector2d s) throws NoSuchElementException {
		//initialize the variables
		res = null;
		boolean visited[][] = new boolean[map.getSize().x][map.getSize().y];
		Queue<MapNode> Q = new LinkedList<MapNode>();
		visited[s.x][s.y] = true;
		Q.add(map.getMap()[s.x][s.y]);
		while(!Q.isEmpty()) {
			MapNode temp = Q.remove();
			ArrayList<MapNode> adj = temp.getAdjacency();
			for(MapNode n: adj) {
				if(n.getState().blocked || visited[n.getState().abstractPos.x][n.getState().abstractPos.y]) {
					break;
				}
				else {
					if(n == map.getDummy()) {
						res = temp.getState().abstractPos;
						return res;
					}
					else {
						visited[n.getState().abstractPos.x][n.getState().abstractPos.y] = true;
						Q.add(n);
						path[n.getState().abstractPos.x][n.getState().abstractPos.y] = temp.getState().abstractPos;
					}
				}
			}
		}
		return res;
	}
	//Return 0 is the sprite is trapped, otherwise, return 1 - 6 indicating where to move next
	public Vector2d computeDecision(Vector2d vec) {	
		Vector2d lastNode = BFS(vec);
		if(lastNode == null) {
			return lastNode; //The sprite is trapped
		}
		else {
			return getNext(vec, lastNode);
		}
	}
}
