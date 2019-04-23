package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainControllerTest {

	@Test
	public void test() {
		MainController mc = new MainController();
		String input1 = "0";
		String input2 = "9";
		boolean test1 = mc.isValidInput(input1);
		boolean test2 = mc.isValidInput(input2);
		assertTrue(test1);
		assertFalse(test2);
	}

}
