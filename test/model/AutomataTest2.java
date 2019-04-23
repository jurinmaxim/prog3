package model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class AutomataTest2 {

	Automata au;
	Integer[] birtharray, survarray;
	boolean [][] falsearray;
	@Before
	public void setUp() throws Exception {
		this.birtharray = new Integer[] {3};
		this.survarray = new Integer[] {2,3};
		au = new Automata(3,3,birtharray,survarray);
		falsearray = new boolean[3][3];
		for (int i=0; i<falsearray.length;i++) {
			for(int j=0; j<falsearray.length;j++) {
					this.falsearray[i][j]=false;}}
		
	}

	@Test
	public void test() {
		au.initialize();
		for (int i=0; i<falsearray.length;i++) {
			for(int j=0; j<falsearray.length;j++) {
					assertEquals(au.getGrid()[i][j],falsearray[i][j]);
		
	}

}}}
