package de.haw.informatik.tests;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.EulerGraphGenerator;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.EulerianCircuit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

public class RandomGraphTest {


    @Test
    public void testAllVerticesAreEvenDegree() {
        ArrayList<EFVertex> oddCounter = new ArrayList<>();
        UndirectedGraph graph = EulerGraphGenerator.getGraph(1000, 10000);

        Set<EFVertex> vertexSet = graph.vertexSet();
        for (EFVertex ef : vertexSet) {

            if (graph.edgesOf(ef).isEmpty()) {
                throw new IllegalArgumentException("Isolated EFVertex");
            } else if (graph.edgesOf(ef).size() % 2 == 1) {
                oddCounter.add(ef);
            }
        }

        assertTrue(oddCounter.isEmpty());
    }

    @Test
    public void testIsConnected() {
        boolean isConnected;
        UndirectedGraph graph = EulerGraphGenerator.getGraph(1000, 10000);
        ConnectivityInspector connectivityInspector = new ConnectivityInspector(graph);
        isConnected = connectivityInspector.isGraphConnected();
        assertTrue(isConnected);
    }

    @Test
    public void testEulerGraph() {
        for (int i = 0; i < 100; i++) {
            assertTrue(EulerianCircuit.isEulerian(EulerGraphGenerator.getGraph(100, 1000)));
        }
    }
}