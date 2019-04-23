package controller;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import view.CellPanel;

public class CellPanelGridControllerTest {
	CellPanel cp;
	CellPanelGridController cpg;
	@Before
	public void setUp() throws Exception {
		cpg = new CellPanelGridController(3,3);
		cpg.cellPointerColor = Color.GRAY;
		
	}

	@Test
	public void test() {
		
		cpg.paintMouseEnteredCell(1,1);
		Color bkg = cpg.getCellPanelAtPosition(1,1).getBackground();
		assertEquals(bkg,cpg.cellPointerColor);
		
	}

}
