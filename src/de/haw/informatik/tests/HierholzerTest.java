

package de.haw.informatik.tests;

import de.haw.informatik.algorithms.Hierholzer;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.EulerGraphRandomGenerator;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by finnmasurat on 12.06.15.
 */


public class HierholzerTest {

    GraphFileReader _gf;

    @Before
    public void setUp() {
        _gf = new GraphFileReader("bsp/nikolaus.graph");
    }

    @Test
    public void verticesCount() {

        Graph graph = EulerGraphRandomGenerator.constructGraph(10, 15);

        int verticesCount = graph.vertexSet().size();

        Set<EFVertex> eulerSet = new HashSet<>();
        List<EFVertex> eulerway = Hierholzer.getPath(graph);

        for (int i = 0; i < eulerway.size(); i++) {

            if (!eulerSet.contains(eulerway.get(i))) {
                eulerSet.add(eulerway.get(i));
            }


        }

        assertEquals(eulerSet.size(), verticesCount);
    }

    @Test
    public void nikolausTest() {


        Graph graph = _gf.getGraph();

        ArrayList<EFVertex> _hierholz = (ArrayList<EFVertex>) Hierholzer.getPath(graph);

        List<String> correctList = new ArrayList<>();

        correctList.add("D");
        correctList.add("C");
        correctList.add("E");
        correctList.add("D");
        correctList.add("B");
        correctList.add("A");
        correctList.add("C");
        correctList.add("B");
        correctList.add("E");


        assertEquals(_hierholz.toString(), correctList.toString());

    }

    @Test
    public void edgesCount() {

        Graph graph = EulerGraphRandomGenerator.constructGraph(10, 10);

        int edgeCount = graph.edgeSet().size();

        List<EFVertex> hierholzerWay = Hierholzer.getPath(graph);

        assertEquals(edgeCount + 1, hierholzerWay.size());


    }

    @Test
    public void eulerKreis() {

        Graph graph = EulerGraphRandomGenerator.constructGraph(9, 15);

        List<EFVertex> hierholzer = Hierholzer.getPath(graph);

        assertEquals(hierholzer.get(0), hierholzer.get(hierholzer.size() - 1));

    }
}
