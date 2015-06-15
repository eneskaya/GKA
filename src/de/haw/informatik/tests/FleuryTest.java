package de.haw.informatik.tests;

import de.haw.informatik.algorithms.Fleury;
import de.haw.informatik.datatypes.EFDefaultEdge;
import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.EulerGraphGenerator;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@SuppressWarnings("unchecked")
public class FleuryTest {

    UndirectedGraph<EFVertex, EFEdge> _graph1;

    @Before
    public void setUp() {

        _graph1 = new Pseudograph(EFDefaultEdge.class);

        for (int i = 0; i <= 5; i++) {
            _graph1.addVertex(new EFVertex("" + i));
        }

        List<EFVertex> vertices = new ArrayList<>(_graph1.vertexSet());

        _graph1.addEdge(vertices.get(0), vertices.get(1));
        _graph1.addEdge(vertices.get(1), vertices.get(2));
        _graph1.addEdge(vertices.get(2), vertices.get(3));
        _graph1.addEdge(vertices.get(3), vertices.get(4));
        _graph1.addEdge(vertices.get(4), vertices.get(5));
        _graph1.addEdge(vertices.get(5), vertices.get(0));
    }

    @Test
    public void testFleuryWithBuiltInMethods() {

        assertTrue(EulerianCircuit.isEulerian(_graph1));

        List list = EulerianCircuit.getEulerianCircuitVertices(_graph1);

        List list2 = Fleury.getCircuit(_graph1);

        assertEquals(list.size(), list2.size());
    }

    @Test
    public void testWithRandomGraph() {

        for (int i = 0; i < 50; i++) {

            UndirectedGraph testGraph = EulerGraphGenerator.getGraph(100, 110);

            assertEquals(
                    EulerianCircuit.getEulerianCircuitVertices(testGraph).size(),
                    Fleury.getCircuit(testGraph).size()
            );
        }
    }
}
