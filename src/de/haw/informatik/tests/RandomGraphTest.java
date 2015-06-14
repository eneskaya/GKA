package de.haw.informatik.tests;

import de.haw.informatik.tools.ConnectedGraphRandomGenerator;
import de.haw.informatik.tools.EulerGraphRandomGenerator;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RandomGraphTest {

    private Graph _graph1;

    @Before
    public void setUp() {
        _graph1 = new ConnectedGraphRandomGenerator().getRandomConnectedGraph(10, 10);
    }

    @Test
    public void testCorrectGenerationOfEulerGraph() {
        Graph graph = EulerGraphRandomGenerator.constructGraph(100, 200);

        assertEquals(200, graph.edgeSet().size());
        assertEquals(100, graph.vertexSet().size());
    }


    @Test
    public void testTotalVertices() {

    }
}
