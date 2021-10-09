package testCase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.*;
import gameObject.*;
import keyValue.*;

public class TestUtilNode {
	private MapNode node;
	private MapNodeInfo info;
	private Info wordInfo = new WordInfo("An animal under the see which you learn in primary school", "fish");
;
	//Test creating the node
	
	@Test
	public void testCreation() {
		node = new MapNode(10, 20, 1, 2, 3, wordInfo);
		info = node.getState();
		assertEquals(10, info.displayPos.x);
		assertEquals(20, info.displayPos.y);
		assertEquals(2, info.abstractPos.x);
		assertEquals(3, info.abstractPos.y);
		assertEquals(1, info.radius);
		assertEquals(false, info.activated);
		assertEquals(false, info.blocked);
		assertEquals("An animal under the see which you learn in primary school", info.greInfo.getDefin());
		assertEquals("fish", info.greInfo.getAns());
	}
	//Test blocking when the node is activated
	@Test
	public void testBlock01() {
		node = new MapNode(20, 40, 1, 2, 3, wordInfo);	
		node.activate();
		info = node.getState();
		assertEquals(true, info.activated);
		boolean res = node.block();
		assertEquals(false, res);
		assertEquals(false, info.blocked);
	}
	//Test blocking when the node is not activated
	@Test
	public void testBlock02() {
		node = new MapNode(20, 40, 1, 2, 3, wordInfo);	
		info = node.getState();
		assertEquals(false, info.activated);
		boolean res = node.block();
		assertEquals(true, res);
		assertEquals(true, info.blocked);
	}
	//Test activating when the node is blocked
	@Test
	public void testActivate01() {
		node = new MapNode(20, 40, 1, 2, 3, wordInfo);	
		info = node.getState();
		node.block();
		node.activate();
		assertEquals(false, info.activated);
	}
	//Test activating when the node is not blocked
	@Test
	public void testActivate02() {
		node = new MapNode(20, 40, 1, 2, 3, wordInfo);	
		info = node.getState();
		node.activate();
		assertEquals(true, info.activated);
	}
}
