package de.haw.informatik.tests;

import de.haw.informatik.algorithms.BreadthFirstSearch;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BFSTest {

    GraphFileReader _gf;
    BreadthFirstSearch _bfs;

    @Before
    public void setUp() {
        _gf = new GraphFileReader("bsp/bsp1.graph");
    }

    @Test
    public void testRightOutput() {

        Graph graph = _gf.getGraph();

        Set<EFVertex> vertices = graph.vertexSet();
        Object[] array = vertices.toArray();

        _bfs = new BreadthFirstSearch(_gf.getGraph(), (EFVertex) array[0], (EFVertex) array[5]);

        assertEquals(
                "Der Kürzeste Weg von a nach j ist:\n" +
                "a --> b --> j\n" +
                "über 2 Kante(n).", _bfs.doSearch());
    }

}
