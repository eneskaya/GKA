package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFDefaultEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.Pseudograph;

import java.util.*;

@SuppressWarnings("unchecked")
public class EulerGraphGenerator {

    public static UndirectedGraph getGraph(int countVertices, int countEdges) {

        if (countEdges < countVertices)
            throw new IllegalArgumentException("There need to be more edges than vertices.");

        Random random = new Random();
        UndirectedGraph graph = new Pseudograph(EFDefaultEdge.class);

        for (int i = 0; i < countVertices; i++) {
            graph.addVertex(new EFVertex("v" + i));
        }

        for (int i = 0; i < countEdges; i++) {

            EFVertex v1 = (EFVertex) graph.vertexSet().toArray()[random.nextInt(countVertices)];
            EFVertex v2 = (EFVertex) graph.vertexSet().toArray()[random.nextInt(countVertices)];

            if (v1.equals(v2)) {
                i--;
                continue;
            }

            graph.addEdge(v1, v2);
        }

        Queue<EFVertex> verticesWithOddDegree = new LinkedList<>();
        Queue<EFVertex> isolated = new LinkedList<>();

        for (EFVertex v : (Set<EFVertex>) graph.vertexSet()) {
            if (graph.degreeOf(v) % 2 == 1) {
                verticesWithOddDegree.add(v);
            } else if (graph.degreeOf(v) == 0) {
                isolated.add(v);
            }
        }

        System.out.println(verticesWithOddDegree);

        while (!verticesWithOddDegree.isEmpty()) {

            EFVertex v1 = verticesWithOddDegree.poll();
            EFVertex v2 = verticesWithOddDegree.poll();

            graph.addEdge(v1, v2);
        }

        isolated.forEach(graph::removeVertex);

        System.out.println(graph.vertexSet().size());
        System.out.println(graph.edgeSet().size());

        System.out.println(EulerianCircuit.isEulerian(graph));

        return graph;
    }
}
