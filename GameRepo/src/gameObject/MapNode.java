package gameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import keyValue.Info;
import testCase.TestDrawingScene;
import util.FrameUpdate;
import util.Mouse;
import util.ResourceLoader;
import util.Vector2d;

public class MapNode implements FrameUpdate{
	
	private MapNodeInfo info;
	private ArrayList<MapNode> adjacency;
	private static BufferedImage grey = null;
	private static BufferedImage orange = null;
	
	public MapNode(float x, float y, float r, int m, int n, Info gre) {
		info = new MapNodeInfo(x, y, r, m, n, gre);
		adjacency = new ArrayList<MapNode>();
	}
	//If the sprite is on the node, it can not be blocked.
	public boolean block() {
		if(info.activated) {
			return false;
		}
		else {
			info.blocked = true;
			return true;
		}
	}
	//If the node is currently blocked by the user -> can not occupied by the cat
	public boolean activate() {
		if(info.blocked) {
			return false;
		}
		else {
			info.activated = true;
			return true;
		}
	}
	public void addAdj(MapNode n) {
		adjacency.add(n);
	}
	public MapNodeInfo getState() {
		return info;
	}
	
	public ArrayList<MapNode> getAdjacency(){
		return adjacency;
		
	}
	

	@Override
	public void enter() {
		if(grey == null && orange == null) {
			InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/grey.png", "/circle/grey.png" );
			try {
				grey = ImageIO.read(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			stream = ResourceLoader.load(TestDrawingScene.class, "res/circle/orange.png", "/circle/orange.png" );
			try {
				orange = ImageIO.read(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Mouse mouse) {
		boolean isInGeo= Math.pow(mouse.mousePos.x - info.displayPos.y - info.radius,2) + Math.pow(mouse.mousePos.y - info.displayPos.x - info.radius, 2) < Math.pow(info.radius, 2);
		if(isInGeo && info.blocked==false) {
			if(mouse.mouseClicked) {
				info.blocked = true;
				BoxController.getInstance().update(info.greInfo.getAns(), 1);
				mouse.mouseClicked = false;
			}
			else {
				BoxController.getInstance().update(info.greInfo.getDefin(), 0);
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(!info.blocked) {
			g.drawImage(grey, (int)info.displayPos.y, (int)info.displayPos.x, (int)info.radius*2, (int)info.radius*2, null);
		}
		else {
			g.drawImage(orange, (int)info.displayPos.y, (int)info.displayPos.x, (int)info.radius*2, (int)info.radius*2, null);
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
