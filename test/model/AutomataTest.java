package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AutomataTest {
	
	Integer[] birtharray, survarray;
	boolean[][] grid;
	
	@Before
	public void setUp() throws Exception {
		this.birtharray = new Integer[] {3};
		this.survarray = new Integer[] {2,3};
		grid = new boolean[3][3];
		for (int i=0; i<grid.length;i++) {
			for(int j=0; j<grid[0].length;j++) {
					this.grid[i][j]=true;
			}}
			
	}

	@Test
	public void test() {
		Automata au = new Automata(3,3,birtharray,survarray);
		au.setGrid(grid);
		int test = au.countNeighbours(1,1);
		assertEquals(8,test);
	}

}
