package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class Prim {

    // Map with all Vertices and the shortest Edge from the spanningTree to them
    public static Map<EFVertex, Double> map = new HashMap<>();

    public static Graph computeGraph(Graph graph, EFVertex startVertex) {

        PriorityQueue<EFVertex> leQueue = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(map.get(o1), map.get(o2));
        });


        // spanningTree-Graph which we will return
        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);


        // put all Vertices with POSITIV_INFINITY in the map, to check that it will definitly be change
        for (EFVertex ef : (Set<EFVertex>) graph.vertexSet()) {
            map.put(ef, Double.POSITIVE_INFINITY);
        }


        leQueue.add(startVertex);
        spanningTree.addVertex(startVertex);

        while (!leQueue.isEmpty()) {
            EFVertex currentVertex = leQueue.poll();
            Set<EFWeightedEdge> set = graph.edgesOf(currentVertex);

            // refreshing the distances from the spanningTree to the Vertices
            for (EFWeightedEdge e : set) {

                if (graph.getEdgeWeight(e) < map.get(graph.getEdgeTarget(e))) {
                    EFVertex temp = (EFVertex) graph.getEdgeSource(e);
                    ((EFVertex) graph.getEdgeTarget(e))._predecessor = temp;
                    map.remove(graph.getEdgeTarget(e));
                    map.put((EFVertex) graph.getEdgeTarget(e), graph.getEdgeWeight(e));
                }
            }

            // check which Vertex is the closest, direct neighbour to the spanningTree
            Map.Entry<EFVertex, Double> min = null;
            for (Map.Entry<EFVertex, Double> entry : map.entrySet()) {
                if (!spanningTree.containsVertex(entry.getKey()) && (min == null || entry.getValue() < min.getValue())) {

                    min = entry;
                }
            }

            // add this Vertex to the spanningTree with the edge to it
            if (min != null) {
                EFVertex target = min.getKey();
                spanningTree.addVertex(target);
                leQueue.add(target);
                EFWeightedEdge temporaryEdge = spanningTree.addEdge(target._predecessor, target);
                spanningTree.setEdgeWeight(temporaryEdge, min.getValue());

            }
        }

        return spanningTree;
    }


}


