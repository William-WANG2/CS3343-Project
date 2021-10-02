package util;

public class Map {
	//scale factor of the main game area
	static final double scale = 3/4;
	private Node[][] map;
	public Map(int m, int n, int width, int height) {
		map = new Node[m][n];
		int displayW = (int)(scale * width); //valid region to display the map
		int radius = (int)(2 * displayW / (2.2 * n)); //radius for node
		int interval = (int)(0.1 * displayW / 2.2 * n); //interval between node
		int offset = 3 * interval; //offset for odd row
		int sX = (int)(1/2 * (1-scale) * width); //left-top x coordinate
		int sY = (int)(1/2 * (1-scale) * width); //left-top y coordinate
		//initialize the map
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(i%2 == 0) {
					map[i][j] = new Node(sX+interval+2*j*(radius+interval), sY+interval+2*j*(radius+interval), false);
				}
				else {
					map[i][j] = new Node(sX+interval+2*j*(radius+interval)+offset, sY+interval+2*j*(radius+interval), false);
				}
			}
		}
	}
}
