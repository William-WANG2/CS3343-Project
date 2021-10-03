package testCase;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import util.*;
import gameObject.*;

public class TestUtilNode {
	private MapNode node;
	private MapNodeInfo info;
	//Test creating the node
	@Test
	public void testCreation() {
		node = new MapNode(10, 20);
		info = node.getState();
		assertEquals(10, info.x);
		assertEquals(20, info.y);
		assertEquals(false, info.activated);
		assertEquals(false, info.blocked);
	}
	//Test blocking when the node is activated
	@Test
	public void testBlock01() {
		node = new MapNode(20, 40);	
		node.activate();
		info = node.getState();
		assertEquals(20, info.x);
		assertEquals(40, info.y);
		assertEquals(true, info.activated);
		boolean res = node.block();
		assertEquals(false, res);
		assertEquals(false, info.blocked);
	}
	//Test blocking when the node is not activated
	@Test
	public void testBlock02() {
		node = new MapNode(20, 40);	
		info = node.getState();
		assertEquals(20, info.x);
		assertEquals(40, info.y);
		assertEquals(false, info.activated);
		boolean res = node.block();
		assertEquals(true, res);
		assertEquals(true, info.blocked);
	}
	//Test activating when the node is blocked
	@Test
	public void testActivate01() {
		node = new MapNode(20, 40);	
		info = node.getState();
		node.block();
		node.activate();
		assertEquals(false, info.activated);
	}
	//Test activating when the node is not blocked
	@Test
	public void testActivate02() {
		node = new MapNode(20, 40);	
		info = node.getState();
		node.activate();
		assertEquals(true, info.activated);
	}
}
