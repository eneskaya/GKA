package de.haw.informatik.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.haw.informatik.tools.GraphFileReader;

public class GraphFileReaderTest {

	GraphFileReader _gf1;
	GraphFileReader _gf2;
	GraphFileReader _gf3;
	GraphFileReader _gf4;
	GraphFileReader _gf5;
	GraphFileReader _gf6;

	
	@Before
	public void setUp() {
		_gf1 = new GraphFileReader("bsp/bsp1.graph");
		_gf2 = new GraphFileReader("bsp/bsp2.graph");
		_gf3 = new GraphFileReader("bsp/bsp3.graph");
		_gf4 = new GraphFileReader("bsp/bsp4.graph");
		_gf5 = new GraphFileReader("bsp/bsp5.graph");
		_gf6 = new GraphFileReader("bsp/bsp6.graph");
	}
	
	@Test
	public void testCheckHeader() {
		assertTrue(_gf1.checkIfHeaderIsPresentAndValid());
		assertFalse(_gf2.checkIfHeaderIsPresentAndValid());
		assertTrue(_gf3.checkIfHeaderIsPresentAndValid());
		assertTrue(_gf4.checkIfHeaderIsPresentAndValid());
		assertTrue(_gf5.checkIfHeaderIsPresentAndValid());
		assertTrue(_gf6.checkIfHeaderIsPresentAndValid());
	}
	
	@Test
	public void testCheckAttributeCodes() {
		assertEquals(1, _gf1.getGraphProperties());
		assertEquals(0, _gf2.getGraphProperties());
		assertEquals(7, _gf3.getGraphProperties());
		assertEquals(3, _gf4.getGraphProperties());
		assertEquals(5, _gf5.getGraphProperties());
		assertEquals(1, _gf6.getGraphProperties());
	}

}
