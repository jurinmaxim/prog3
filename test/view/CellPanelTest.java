package view;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CellPanelTest {
	CellPanel cp;
	@Before
	public void setUp() throws Exception {
	cp = new CellPanel();
	cp.aliveCellColor = Color.WHITE;
	cp.isAlive = false;
	
	}

	@Test
	public void test() {
		cp.setAlive();
		assertTrue(cp.isAlive);
		assertEquals(Color.WHITE,cp.aliveCellColor);
	
	}

}
