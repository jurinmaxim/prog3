package view;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CellPanelTest2 {

	CellPanel cp;
	@Before
	public void setUp() throws Exception {
	cp = new CellPanel();
	cp.deadCellColor = Color.BLACK;
	cp.isAlive = true;}
	
	@Test
	public void test() {
		cp.setDead();
		assertFalse(cp.isAlive);
		assertEquals(Color.BLACK,cp.deadCellColor);
	}

}
