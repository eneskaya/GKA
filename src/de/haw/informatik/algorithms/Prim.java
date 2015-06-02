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

        // put all Vertices with POSITIV_INFINITY in the map, to check that it will definitly be change
        for (EFVertex ef : (Set<EFVertex>) graph.vertexSet()) {
            map.put(ef, Double.POSITIVE_INFINITY);
            leQueue.add(ef);

        }

        // spanningTree-Graph which we will return
        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);

        // setting startVertex on top of the PriorityQueue
        spanningTree.addVertex(startVertex);
        leQueue.remove(startVertex);
        map.remove(startVertex);
        map.put(startVertex, 0.0);
        leQueue.add(startVertex);


        while (!leQueue.isEmpty()) {

            EFVertex currentVertex = leQueue.poll();
            Set<EFWeightedEdge> set = graph.edgesOf(currentVertex);

            for (EFWeightedEdge e : set) {

                // refreshing the distances from the spanningTree to the Vertices
                if (graph.getEdgeWeight(e) < map.get(graph.getEdgeTarget(e))) {
                    leQueue.remove(graph.getEdgeTarget(e));
                    EFVertex temp = (EFVertex) graph.getEdgeSource(e);
                    ((EFVertex) graph.getEdgeTarget(e))._predecessor = temp;
                    map.remove(graph.getEdgeTarget(e));
                    map.put((EFVertex) graph.getEdgeTarget(e), graph.getEdgeWeight(e));
                    leQueue.add((EFVertex) graph.getEdgeTarget(e));
                }
                }

            // add the clostest direct neighbour to the spanningTree
            // if the spanningTree doesnt contains it already
            if (!spanningTree.containsVertex(currentVertex)) {
                spanningTree.addVertex(currentVertex);
                EFWeightedEdge temporaryEdge = spanningTree.addEdge(currentVertex._predecessor, currentVertex);
                spanningTree.setEdgeWeight(temporaryEdge, map.get(currentVertex));
            }
        }
        return spanningTree;
    }


}

