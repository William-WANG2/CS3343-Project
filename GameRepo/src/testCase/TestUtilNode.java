package testCase;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import util.Node;
import util.NodeInfo;

public class TestUtilNode {
	private Node node;
	private NodeInfo info
	//Test creating the node
	@Test
	public void tearDown() {node = null;}
	public void testCreation() {
		node = new Node(10, 20, false);
		assertEquals(10, node.getState().x);
		assertEquals(20, node.getState().y);
		assertEquals(false, node.getState().activated);
		assertEquals(false, node.getState().blocked);
	}
	//Test blocking when the node is activated
	@Test
	public void testBlock01() {
		node = new Node(20, 40, true);
		
		assertEquals(20, node.getState().x);
		assertEquals(40, node.getState().y);
		assertEquals(true, node.getState().activated);
		boolean res = node.block();
		assertEquals(false, res);
		assertEquals(false, node.getState().blocked);
	}
}
