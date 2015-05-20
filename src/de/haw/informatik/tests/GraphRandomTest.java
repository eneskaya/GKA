package de.haw.informatik.tests;

import de.haw.informatik.algorithms.AStar;
import de.haw.informatik.algorithms.Dijkstra;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.GraphRandomGenerator;
import org.jgrapht.Graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphRandomTest {

    Graph _big;

    int _vertexCount = 100;
    int _edgeCount = 6000;

    @Before
    public void setUp() {
        _big = GraphRandomGenerator.getRandomGraph(_vertexCount, _edgeCount);
    }

    @Test
    public void testCountEdgesAndVertices() {

        assertEquals(_big.vertexSet().size(), _vertexCount);
        assertEquals(_big.edgeSet().size(), _edgeCount);
    }

    @Test
    public void testBIG() {

        Object[] vertexArray = _big.vertexSet().toArray();

        for (int i = 0; i < vertexArray.length; i++) {

            int a = (int) Math.random() * _vertexCount;
            int b = (int) Math.random() * _vertexCount;

            AStar.computePath(_big, (EFVertex) vertexArray[a]);
            Dijkstra.computePath(_big, (EFVertex) vertexArray[a]);

            assertEquals(
                    AStar.getShortestPathTo((EFVertex) vertexArray[b]),
                    Dijkstra.getShortestPathTo((EFVertex) vertexArray[b])
            );

        }

    }
}