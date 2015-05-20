package de.haw.informatik.tests;

import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void testCheckAttributeCodes() {
		assertEquals(1, _gf1.getGraphProperties());
		assertEquals(0, _gf2.getGraphProperties());
		assertEquals(7, _gf3.getGraphProperties());
		assertEquals(3, _gf4.getGraphProperties());
		assertEquals(5, _gf5.getGraphProperties());
		assertEquals(1, _gf6.getGraphProperties());
	}

	@Test
	public void testCheckIfCorrectTypeWasReturned() {
		assertTrue(_gf1.getGraph() instanceof DirectedPseudograph);
		assertTrue(_gf2.getGraph() instanceof Pseudograph);
		assertTrue(_gf3.getGraph() instanceof WeightedPseudograph);
		assertTrue(_gf4.getGraph() instanceof WeightedPseudograph);
		assertTrue(_gf5.getGraph() instanceof DefaultDirectedWeightedGraph);
		assertTrue(_gf6.getGraph() instanceof DirectedPseudograph);
	}

}
