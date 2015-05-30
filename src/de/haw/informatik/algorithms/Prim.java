package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class Prim {

    public static Map<EFVertex, Double> map = new HashMap<>();
    public static Set<EFVertex> visitedVertices = new HashSet<>();

    public static Graph computeGraph(Graph graph, EFVertex startVertex) {

        PriorityQueue<EFVertex> leQueue = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(map.get(o1), map.get(o2));
        });


        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);


        for (EFVertex ef : (Set<EFVertex>) graph.vertexSet()) {
            map.put(ef, Double.POSITIVE_INFINITY);
        }


        leQueue.add(startVertex);
        spanningTree.addVertex(startVertex);
        visitedVertices.add(startVertex);

        while (!leQueue.isEmpty()) {
            EFVertex currentVertex = leQueue.poll();
            Set<EFWeightedEdge> set = graph.edgesOf(currentVertex);

            EFVertex minWeightedEdge;
            for (EFWeightedEdge e : set) {

                if (graph.getEdgeWeight(e) < map.get(graph.getEdgeTarget(e))) {
                    map.replace((EFVertex) graph.getEdgeTarget(e), graph.getEdgeWeight(e));
                }
            }

            Map.Entry<EFVertex, Double> min = null;
            for (Map.Entry<EFVertex, Double> entry : map.entrySet()) {
                if (!visitedVertices.contains(entry) && ((entry.getKey().compareTo(min.getKey()) < 0) || min == null)) {

                    min = entry;
                }
            }

            EFVertex target = min.getKey();
            spanningTree.addVertex(target);
            visitedVertices.add(target);
            leQueue.add(target);
            spanningTree.addEdge(currentVertex, target);
        }

        return spanningTree;
    }


}


