package de.haw.informatik.tests;

import de.haw.informatik.tools.ConnectedGraphRandomGenerator;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

public class RandomGraphTest {

    private Graph _graph1;

    @Before
    public void setUp() {
        _graph1 = new ConnectedGraphRandomGenerator().getRandomConnectedGraph(10);
    }

    @Test
    public void testTotalWeightsAreEqual() {

    }

    @Test
    public void testTotalVertices() {

    }
}
