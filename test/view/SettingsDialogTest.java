package view;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import model.GameSettings;

public class SettingsDialogTest {
	SettingsDialog sd;
	GameSettings gs;
	@Before
	public void setUp() throws Exception {
		sd = new SettingsDialog();
		gs = new GameSettings();
	}

	@Test
	public void test() {
		Color c1 = GameSettings.getAliveCellColor();
		sd.getSkinPresetComboBox().setSelectedIndex(1);
		sd.changeSkinPreset(sd.getSkinPresetComboBox());
		Color c2= GameSettings.getAliveCellColor();
		assertNotEquals(c1,c2);

	}

}
