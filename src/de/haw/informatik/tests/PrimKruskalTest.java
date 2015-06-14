package de.haw.informatik.tests;

import de.haw.informatik.algorithms.Kruskal;
import de.haw.informatik.algorithms.Prim;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.ConnectedGraphRandomGenerator;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimKruskalTest {

    private Graph _graph1;

    @Before
    public void setUp() {
        _graph1 = new ConnectedGraphRandomGenerator().getRandomConnectedGraph(10, 10);
    }

    @Test
    public void testTotalWeightsAreEqual() {
        Prim.computeGraph(_graph1, (EFVertex) _graph1.vertexSet().toArray()[0]);
        double a = Prim.totalWeight;
        Kruskal.computeGraph(_graph1);
        double b = Kruskal.totalWeight;

        assertEquals(a, b, 1.0);
    }

    @Test
    public void testTotalVertices() {
        int a = Kruskal.computeGraph(_graph1).vertexSet().size();
        assertEquals(_graph1.vertexSet().size(), a);
    }
}
