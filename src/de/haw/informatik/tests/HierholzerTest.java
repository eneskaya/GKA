

package de.haw.informatik.tests;

import de.haw.informatik.algorithms.Hierholzer;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.EulerGraphGenerator;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HierholzerTest {

    GraphFileReader _gf;
    GraphFileReader _sanduhr;

    @Before
    public void setUp() {
        _gf = new GraphFileReader("bsp/nikolaus.graph");
        _sanduhr = new GraphFileReader("bsp/sanduhr.graph");
    }


    @Test
    public void verticesCount() {

        for (int i = 20; i < 100; i++) {
            Graph graph = EulerGraphGenerator.getGraph(i, i + 5);

            int verticesCount = graph.vertexSet().size();
            Set<EFVertex> eulerSet = new HashSet<>();

            if (EulerianCircuit.isEulerian((UndirectedGraph) graph)) {
                List<EFVertex> eulerway = Hierholzer.getPath(graph);

                for (int j = 0; j < eulerway.size(); j++) {

                    if (!eulerSet.contains(eulerway.get(j))) {
                        eulerSet.add(eulerway.get(j));
                    }


                }

                assertEquals(eulerSet.size(), verticesCount);
            }
        }
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
    public void sanduhrTest() {

        Graph graph = _sanduhr.getGraph();

        ArrayList<EFVertex> hierholzer = (ArrayList<EFVertex>) Hierholzer.getPath(graph);

        assertEquals(hierholzer.get(0), hierholzer.get(hierholzer.size() - 1));

    }



    @Test
    public void edgesCount() {

        Graph graph = EulerGraphGenerator.getGraph(9, 15);

        int edgeCount = graph.edgeSet().size();

        List<EFVertex> hierholzerWay = Hierholzer.getPath(graph);

        assertEquals(edgeCount + 1, hierholzerWay.size());
    }

    @Test
    public void eulerCircuit() {

        Graph graph = EulerGraphGenerator.getGraph(9, 15);

        List<EFVertex> hierholzer = Hierholzer.getPath(graph);

        assertEquals(hierholzer.get(0), hierholzer.get(hierholzer.size() - 1));

    }


    @Test
    public void generatedEulerBomb() {

        for (int i = 20; i < 100; i++) {
            Graph graph = EulerGraphGenerator.getGraph(i, i +1);
            int edgeCounter = graph.edgeSet().size();
            if (EulerianCircuit.isEulerian((UndirectedGraph) graph)) {
                List<EFVertex> hierholzer = Hierholzer.getPath(graph);
                assertEquals(hierholzer.get(0), hierholzer.get(hierholzer.size() - 1));
                assertEquals(hierholzer.size(), edgeCounter + 1);
            }

        }
    }


}
