package model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class GameSettingsTest {
	GameSettings gs;
	Color c;
	@Before
	public void setUp() throws Exception {
		gs= new GameSettings();
		c = GameSettings.getAliveCellColor();
	}

	@Test
	public void test() {
		Color c = Color.BLACK;
		GameSettings.setAliveCellColor(c);
		assertEquals(GameSettings.getAliveCellColor(),c);
	}

}
