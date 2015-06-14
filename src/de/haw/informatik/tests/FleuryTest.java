package de.haw.informatik.tests;

import de.haw.informatik.algorithms.Fleury;
import de.haw.informatik.datatypes.EFDefaultEdge;
import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.EulerGraphGenerator;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.Pseudograph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FleuryTest {

    @Test
    public void testWithBuiltInMethod() {
        UndirectedGraph graph = EulerGraphGenerator.getGraph(300, 500);

        List circuit1 = Fleury.getCircuit(graph);
        List circuit2 = EulerianCircuit.getEulerianCircuitVertices(graph);

        assertEquals(circuit1.size(), circuit2.size());
    }

    @Test
    public void testIsBridge() {

        GraphFileReader gf = new GraphFileReader("bsp/bridge.graph");
        Graph graph = gf.getGraph();
        UndirectedGraph uGraph = new Pseudograph(EFDefaultEdge.class);

        while (graph.vertexSet().iterator().hasNext()) {
            System.out.println(graph.vertexSet().size());

            uGraph.addVertex(graph.vertexSet().iterator().next());
        }

        while (graph.edgeSet().iterator().hasNext()) {
            EFEdge c = (EFEdge) graph.edgeSet().iterator().next();
            EFVertex v1 = (EFVertex) graph.getEdgeSource(c);
            EFVertex v2 = (EFVertex) graph.getEdgeTarget(c);

            uGraph.addEdge(v1, v2);
        }

        Fleury.isBridge(uGraph, (EFEdge) uGraph.edgeSet().iterator().next());
    }
}
