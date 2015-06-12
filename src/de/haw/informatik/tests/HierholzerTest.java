package de.haw.informatik.tests;

import de.haw.informatik.algorithms.BreadthFirstSearch;
import de.haw.informatik.algorithms.Hierholzer;
import de.haw.informatik.datatypes.EFDefaultEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by finnmasurat on 12.06.15.
 */
public class HierholzerTest {

    GraphFileReader _gf;
    BreadthFirstSearch _bfs;

    @Before
    public void setUp() {
        _gf = new GraphFileReader("bsp/nikolaus.graph");
    }

    public void VerticesCount() {
    }

    @Test
    public void nikolausTest() {
        Graph graph = _gf.getGraph();

        ArrayList<EFVertex> _hierholz = (ArrayList<EFVertex>) Hierholzer.getPath(graph);

    }
}
