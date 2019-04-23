package controller;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import org.junit.Assert;



public class FileHandlerTest {

	String[] string;
	
	@Before
	public void setUp() throws Exception {
		string = new String[] {"1","0","0","1"};
	}

	@Test
	public void test() {
		FileHandler fh = new FileHandler();
		boolean[] grid = new boolean[4];
		grid = fh.stringToBoolean(string);
		boolean[] testgrid = new boolean[] {true, false,false,true};
		Assert.assertTrue(Arrays.equals(testgrid, grid));
		
		
	}

}
